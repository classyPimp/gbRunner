package composers.item.foradmin

import jdk.nashorn.internal.runtime.regexp.RegExpFactory.validate
import utils.composer.ComposerBase
import models.item.Item
import models.item.ItemRequestParametersWrapper
import models.item.ItemValidator
import models.item.daos.ItemDaos
import models.item.factories.foradmin.ItemForAdminCreateFactory
import models.itemblueprint.ItemBlueprint
import orm.services.ModelInvalidError
import orm.utils.TransactionRunner
import utils.composer.composerexceptions.InvalidRequestParametersError
import utils.requestparameters.IParam

class ItemForAdminCreateComposer(
        val params: IParam
) : ComposerBase() {

    lateinit var onSuccess: (Item)->Unit
    lateinit var onError: (Item)->Unit

    lateinit var itemToCreate: Item
    lateinit var itemBlueprint: Item
    lateinit var wrappedParams: ItemRequestParametersWrapper

    override fun beforeCompose(){
        wrapParams()
        preloadBluePrint()
        build()
        validate()
    }

    private fun wrapParams() {
        params.get("item")?.let {
            wrappedParams = ItemRequestParametersWrapper(it)
        } ?: failImmediately(InvalidRequestParametersError())
    }

    private fun preloadBluePrint() {
        val blueprintId = wrappedParams.blueprintId
        if (blueprintId == null) {
            throw IllegalStateException()
        }
        ItemDaos.show.byIdPreloadingStatModifiers(blueprintId)?.let {
            itemBlueprint = it
        } ?: throw IllegalStateException()
    }

    private fun build() {
        itemToCreate = ItemForAdminCreateFactory.create(wrappedParams, itemBlueprint)
    }

    private fun validate() {
        ItemValidator(itemToCreate).also {
            it.forAdminCreateScenario(itemBlueprint)
            if (!it.validationManager.isValid()) {
                failImmediately(ModelInvalidError())
            }
        }
    }

    override fun compose(){
        TransactionRunner.run {
            val tx = it.inTransactionDsl
            itemToCreate.record.saveCascade(tx)
        }
    }

    override fun fail(error: Throwable) {
        when(error) {
            is ModelInvalidError -> {
                onError(itemToCreate)
            }
            else -> {
                throw error
            }
        }
    }

    override fun success() {
        onSuccess.invoke(itemToCreate)
    }

}

