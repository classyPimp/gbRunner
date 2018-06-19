package models.campaign.tojsonserializers.forgamemaster

import models.campaign.Campaign
import orm.campaigngeneratedrepository.CampaignToJsonSerializer

object CampaignForGameMasterShowToJsonSerializer {

    fun onSuccess(campaign: Campaign): String {
        CampaignToJsonSerializer(campaign).let {
            it.includeLinksToUsers() {
                it.includeUser() {
                    it.includeAccount() {
                        it.only {
                            arrayOf(it.email)
                        }
                    }
                }
            }
            return it.serializeToString()
        }
    }

}