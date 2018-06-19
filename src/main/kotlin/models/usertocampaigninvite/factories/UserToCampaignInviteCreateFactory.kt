package models.usertocampaigninvite.factories

import models.usertocampaigninvite.UserToCampaignInvite
import models.usertocampaigninvite.UserToCampaignInviteRequestParametersWrapper

object UserToCampaignInviteCreateFactory {
    fun create(params: UserToCampaignInviteRequestParametersWrapper, campaignId: Long, userThatInvitesId: Long): UserToCampaignInvite {
        return UserToCampaignInvite().also {
            it.campaignId = campaignId
            it.userThatInvitesId = userThatInvitesId
            it.userThatIsInvitedId = params.userThatIsInvitedId
        }
    }

}