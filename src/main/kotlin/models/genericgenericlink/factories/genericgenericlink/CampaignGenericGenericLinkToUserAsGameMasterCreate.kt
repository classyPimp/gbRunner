package models.genericgenericlink.factories.genericgenericlink

import models.genericgenericlink.GenericGenericLink
import models.user.User

object CampaignGenericGenericLinkToUserAsGameMasterCreate {
    fun create(gameMasterId: Long): MutableList<GenericGenericLink> {
        return mutableListOf(
                GenericGenericLink().also {
                    it.category = GenericGenericLink.Categories.UserToCampaignLink.GAME_MASTER.toString()
                    it.rightModelId = gameMasterId
                    it.rightModelType = User::class.simpleName
                }
        )
    }

}