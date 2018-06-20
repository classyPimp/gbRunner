package models.usertocampaigninvite

import models.campaign.daos.CampaignDaos
import models.genericgenericlink.daos.GenericGenericLinkDaos
import models.user.daos.UserDaos
import models.usertocampaigninvite.daos.UserToCampaignInviteDaos
import orm.usertocampaigninvitegeneratedrepository.UserToCampaignInviteValidatorTrait

class UserToCampaignInviteValidator(model: UserToCampaignInvite) : UserToCampaignInviteValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        validateUserThatIsInvitedId()
        validateCampaignId()
        validateIfUserAlreadyIsAPlayer()
    }

    private fun validateCampaignId() {
        val campaignId = model.campaignId
        if (campaignId == null) {
            throw IllegalStateException()
        }
        if (!CampaignDaos.show.existsWithId(campaignId)) {
            validationManager.addCampaignIdError("does not exist")
        }
    }

    private fun validateUserThatIsInvitedId() {
        val userThatIsInvitedId = model.userThatIsInvitedId
        if (userThatIsInvitedId == null) {
            throw IllegalStateException()
        }
        if (!UserDaos.show.isExists(userThatIsInvitedId)) {
            validationManager.addUserThatIsInvitedIdError("no such user")
        }
    }

    private fun validateIfUserAlreadyIsAPlayer() {
        val userId = model.userThatIsInvitedId ?: return
        val campaignId = model.campaignId ?: return
        if (GenericGenericLinkDaos.show.userToCampaignLinkAsPlayerExistsFor(userId = userId, campaignId = campaignId)) {
            validationManager.addGeneralError("user is player already for this campaign")
        }
    }


}