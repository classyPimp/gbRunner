package models.usertocampaigninvite.tojsonserializers

import models.usertocampaigninvite.UserToCampaignInvite
import orm.usertocampaigninvitegeneratedrepository.UserToCampaignInviteToJsonSerializer

object UserToCampaignShowToJsonSerializer {

    fun onSuccess(userToCampaignInvitation: UserToCampaignInvite): String {
        UserToCampaignInviteToJsonSerializer(userToCampaignInvitation).let {

            return it.serializeToString()
        }
    }

}