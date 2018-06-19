package models.campaign.tojsonserializers

import models.campaign.Campaign
import orm.campaigngeneratedrepository.CampaignToJsonSerializer

object CampaignForGameMasterEditToJsonSerializer {

    fun onSuccess(campaign: Campaign): String {
        CampaignToJsonSerializer(campaign).let {

            return it.serializeToString()
        }
    }

    fun onError(campaign: Campaign): String {
        CampaignToJsonSerializer(campaign). let {


            it.includeErrors()
            return it.serializeToString()
        }
    }

}