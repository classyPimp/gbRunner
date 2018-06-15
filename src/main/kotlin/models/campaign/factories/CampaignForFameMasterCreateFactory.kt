package models.campaign.factories

import models.campaign.Campaign
import models.campaign.CampaignRequestParametersWrapper
import models.genericgenericlink.GenericGenericLink
import models.genericgenericlink.factories.genericgenericlink.CampaignGenericGenericLinkToUserAsGameMasterCreate

object CampaignForFameMasterCreateFactory {
    fun create(wrappedParams: CampaignRequestParametersWrapper, gameMasterId: Long): Campaign {
        return Campaign().also {
            it.name = wrappedParams.name
            it.description = wrappedParams.description
            it.linksToUsers = CampaignGenericGenericLinkToUserAsGameMasterCreate.create(gameMasterId)
        }
    }

}