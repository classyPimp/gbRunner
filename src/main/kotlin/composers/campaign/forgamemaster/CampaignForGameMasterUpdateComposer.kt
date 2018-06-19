package composers.campaign.forgamemaster

import utils.composer.ComposerBase
import models.campaign.Campaign
import models.campaign.CampaignRequestParametersWrapper
import models.campaign.CampaignValidator
import models.campaign.daos.CampaignDaos
import models.campaign.updaters.forgamemaster.CampaignForGameMasterUpdater
import orm.modelUtils.exceptions.ModelNotFoundError
import orm.services.ModelInvalidError
import orm.utils.TransactionRunner
import utils.composer.composerexceptions.InvalidRequestParametersError
import utils.requestparameters.IParam

class CampaignForGameMasterUpdateComposer(val params: IParam, val campaignId: Long) : ComposerBase() {

    lateinit var onSuccess: (Campaign)->Unit
    lateinit var onError: (Campaign)->Unit
    lateinit var onNotFound: ()->Unit

    lateinit var campaignToUpdate: Campaign
    lateinit var wrappedParams: CampaignRequestParametersWrapper

    override fun beforeCompose(){
        findAndSetCampaignToUpdate()
        wrapParams()
        runUpdater()
        validate()
    }

    private fun findAndSetCampaignToUpdate() {
        CampaignDaos.update.forGameMasterUpdate(campaignId = campaignId)?.let {
            campaignToUpdate = it
        } ?: failImmediately(ModelNotFoundError())
    }

    private fun wrapParams() {
        params.get("campaign")?.let {
            wrappedParams = CampaignRequestParametersWrapper(it)
        } ?: failImmediately(InvalidRequestParametersError())
    }

    private fun runUpdater() {
        CampaignForGameMasterUpdater.update(campaignToUpdate, wrappedParams)
    }

    private fun validate() {
        CampaignValidator(campaignToUpdate).let {
            it.forGameMasterUpdateScenario()
            if (!it.validationManager.isValid()) {
                failImmediately(ModelInvalidError())
            }
        }
    }

    override fun compose(){
        TransactionRunner.run {tx ->
            campaignToUpdate.record.saveCascade(tx.inTransactionDsl)
        }
    }

    override fun fail(error: Throwable) {
        when(error) {
            is ModelNotFoundError -> {
                onNotFound()
            }
            is ModelInvalidError -> {
                onError(campaignToUpdate)
            }
            else -> {
                throw error
            }
        }
    }

    override fun success() {
        onSuccess.invoke(campaignToUpdate)
    }

}

