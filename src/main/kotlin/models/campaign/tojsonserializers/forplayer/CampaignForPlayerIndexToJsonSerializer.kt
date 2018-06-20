package models.campaign.tojsonserializers.forplayer

import models.campaign.Campaign
import orm.campaigngeneratedrepository.CampaignToJsonSerializer

object CampaignForPlayerIndexToJsonSerializer {

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