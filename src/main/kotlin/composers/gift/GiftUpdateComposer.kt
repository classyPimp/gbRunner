package composers.gift

import utils.composer.ComposerBase
import models.gift.Gift
import models.gift.GiftRequestParametersWrapper
import models.gift.GiftValidator
import models.gift.daos.GiftDaos
import models.gift.updaters.GiftUpdater
import orm.modelUtils.exceptions.ModelNotFoundError
import orm.services.ModelInvalidError
import utils.composer.composerexceptions.InvalidRequestParametersError
import utils.requestparameters.IParam

class GiftUpdateComposer(
        val params: IParam,
        val giftId: Long
) : ComposerBase() {

    lateinit var onSuccess: (Gift)->Unit
    lateinit var onError: (Gift)->Unit

    lateinit var wrappedParams: GiftRequestParametersWrapper
    lateinit var giftToUpdate: Gift

    override fun beforeCompose(){
        wrapParams()
        findAndSetGift()
        runUpdate()
        validate()
    }

    private fun wrapParams() {
        params.get("gift")?.let {
            wrappedParams = GiftRequestParametersWrapper(it)
        } ?: failImmediately(InvalidRequestParametersError())
    }

    private fun findAndSetGift() {
        GiftDaos.show.forUpdate(giftId)?.let {
            giftToUpdate = it
        } ?: failImmediately(ModelNotFoundError())
    }

    private fun runUpdate() {
        GiftUpdater.update(giftToUpdate, wrappedParams)
    }

    private fun validate() {
        GiftValidator(giftToUpdate).let {
            it.updateScenario()
            if (!it.validationManager.isValid()) {
                failImmediately(ModelInvalidError())
            }
        }
    }

    override fun compose(){
        giftToUpdate.record.save()
    }

    override fun fail(error: Throwable) {
        when(error) {
            is ModelInvalidError -> {
                onError.invoke(giftToUpdate)
            }
            else -> {
                throw error
            }
        }
    }

    override fun success() {
        onSuccess.invoke(giftToUpdate)
    }

}

