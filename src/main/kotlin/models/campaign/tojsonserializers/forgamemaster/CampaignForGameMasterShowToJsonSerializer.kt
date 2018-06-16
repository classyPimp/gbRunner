package models.campaign.tojsonserializers.forgamemaster

import models.campaign.Campaign
import orm.campaigngeneratedrepository.CampaignToJsonSerializer

object CampaignForGameMasterShowToJsonSerializer {

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