package models.usertocampaigninvite.tojsonserializers

import models.usertocampaigninvite.UserToCampaignInvite
import orm.usertocampaigninvitegeneratedrepository.UserToCampaignInviteToJsonSerializer

object UserToCampaignInviteCreateToJsonSerializer {

    fun onSuccess(userToCampaignInvite: UserToCampaignInvite): String {
        UserToCampaignInviteToJsonSerializer(userToCampaignInvite).let {

            return it.serializeToString()
        }
    }

    fun onError(userToCampaignInvite: UserToCampaignInvite): String {
        UserToCampaignInviteToJsonSerializer(userToCampaignInvite). let {


            it.includeErrors()
            return it.serializeToString()
        }
    }

}