package models.usertocampaigninvite

import models.campaign.daos.CampaignDaos
import models.user.daos.UserDaos
import orm.usertocampaigninvitegeneratedrepository.UserToCampaignInviteValidatorTrait

class UserToCampaignInviteValidator(model: UserToCampaignInvite) : UserToCampaignInviteValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        validateUserThatIsInvitedId()
        validateCampaignId()
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

}