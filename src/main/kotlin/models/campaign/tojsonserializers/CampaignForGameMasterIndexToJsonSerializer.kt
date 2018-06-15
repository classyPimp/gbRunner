package models.campaign.tojsonserializers

import models.campaign.Campaign
import orm.campaigngeneratedrepository.CampaignToJsonSerializer

object CampaignForGameMasterIndexToJsonSerializer {

    fun onSuccess(campaigns: MutableList<Campaign>): String {
        CampaignToJsonSerializer.serialize(campaigns).let {
            return it.toString()
        }
    }

}