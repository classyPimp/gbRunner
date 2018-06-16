package composers.campaign.forgamemaster

import utils.composer.ComposerBase
import models.campaign.Campaign

class CampaignForGameMasterUpdateComposer : ComposerBase() {

    lateinit var onSuccess: (Campaign)->Unit
    lateinit var onError: (Campaign)->Unit

    lateinit var campaignToUpdate: Campaign

    override fun beforeCompose(){

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
        onSuccess.invoke(campaignToUpdate)
    }

}

