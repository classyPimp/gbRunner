package composers.campaign

import utils.composer.ComposerBase
import models.campaign.Campaign

class CampaignCreateComposer : ComposerBase() {

    lateinit var onSuccess: (Campaign)->Unit
    lateinit var onError: (Campaign)->Unit

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
        onSuccess.invoke()
    }

}

