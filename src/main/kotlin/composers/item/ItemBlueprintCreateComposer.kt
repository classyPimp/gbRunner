package composers.item

import utils.composer.ComposerBase
import models.item.Item
import models.item.ItemRequestParametersWrapper
import models.item.factories.blueprint.ItemBluePrintCreateFactory
import utils.composer.composerexceptions.InvalidRequestParametersError
import utils.requestparameters.IParam

class ItemBlueprintCreateComposer(
        val params: IParam
) : ComposerBase() {

    lateinit var onSuccess: (Item)->Unit
    lateinit var onError: (Item)->Unit

    lateinit var itemToCreate: Item
    lateinit var wrappedParams: ItemRequestParametersWrapper

    override fun beforeCompose(){
        wrapParams()
        build()
        validate()
    }

    private fun wrapParams() {
        params.get("item")?.let {
            wrappedParams = ItemRequestParametersWrapper(it)
        } ?: failImmediately(InvalidRequestParametersError())
    }

    private fun build() {
        itemToCreate = ItemBluePrintCreateFactory.create(wrappedParams)
    }

    private fun validate() {

    }

    override fun compose(){

    }

    override fun fail(error: Throwable) {
        when(error) {

            else -> {
                throw error
            }
        }
    }

    override fun success() {
        onSuccess.invoke(itemToCreate)
    }

}

