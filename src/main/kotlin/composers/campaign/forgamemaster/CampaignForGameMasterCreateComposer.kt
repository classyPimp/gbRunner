package composers.campaign.forgamemaster

import utils.composer.ComposerBase
import models.campaign.Campaign
import models.campaign.CampaignRequestParametersWrapper
import models.campaign.CampaignValidator
import models.campaign.factories.CampaignForFameMasterCreateFactory
import orm.services.ModelInvalidError
import orm.utils.TransactionRunner
import utils.composer.composerexceptions.InvalidRequestParametersError
import utils.currentuser.ICurrentUser
import utils.requestparameters.IParam

class CampaignForGameMasterCreateComposer(val params: IParam, val currentUser: ICurrentUser) : ComposerBase() {

    lateinit var onSuccess: (Campaign)->Unit
    lateinit var onError: (Campaign)->Unit

    lateinit var campaignToCreate: Campaign
    lateinit var wrappedParams: CampaignRequestParametersWrapper
    var gameMasterId: Long = currentUser.userModel!!.id!!

    override fun beforeCompose(){
        wrapParams()
        buildCampaign()
        validate()
    }

    private fun wrapParams() {
        params.get("campaign")?.let {
            wrappedParams = CampaignRequestParametersWrapper(it)
        } ?: failImmediately(InvalidRequestParametersError())
    }

    private fun buildCampaign() {
        campaignToCreate = CampaignForFameMasterCreateFactory.create(wrappedParams, gameMasterId)
    }

    private fun validate() {
        CampaignValidator(campaignToCreate).also {
            it.forGameMasterCreateScenario()
            if (!it.validationManager.isValid()) {
                failImmediately(ModelInvalidError())
            }
        }
    }

    override fun compose(){
        TransactionRunner.run {tx ->
            campaignToCreate.record.saveCascade(tx.inTransactionDsl)
        }
    }

    override fun fail(error: Throwable) {
        when(error) {
            is ModelInvalidError -> {
                this.onError(campaignToCreate)
            }
            else -> {
                throw error
            }
        }
    }

    override fun success() {
        this.onSuccess.invoke(campaignToCreate)
    }

}

