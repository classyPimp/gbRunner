package models.campaign.updaters.forgamemaster

import models.campaign.Campaign
import models.campaign.CampaignRequestParametersWrapper


object CampaignForGameMasterUpdater {

    fun update(model: Campaign, params: CampaignRequestParametersWrapper) {
        model.let {
            it.record.description = params.description
            it.record.name = params.name
        }
    }

}