package models.genericgenericlink.factories

import models.campaign.Campaign
import models.genericgenericlink.GenericGenericLink
import models.user.User

object CampaignGenericGenericLinkToUserAsPlayerFactory {

    fun create(campaignId: Long, userId: Long): GenericGenericLink {
        return GenericGenericLink().also {
            it.leftModelId = campaignId
            it.leftModelType = Campaign::class.simpleName
            it.rightModelType = User::class.simpleName
            it.rightModelId = userId
            it.category = GenericGenericLink.Categories.UserToCampaignLink.PLAYER.toString()
        }
    }

}