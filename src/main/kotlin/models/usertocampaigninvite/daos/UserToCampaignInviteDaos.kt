package models.usertocampaigninvite.daos

import org.jooq.generated.tables.UserToCampaignInvites
import models.usertocampaigninvite.daos.UserToCampaignInviteShowDao
import models.usertocampaigninvite.daos.UserToCampaignInviteIndexDao
import models.usertocampaigninvite.daos.UserToCampaignInviteEditDao
import models.usertocampaigninvite.daos.UserToCampaignInviteUpdateDao
import models.usertocampaigninvite.daos.UserToCampaignInviteDestroyDao

object UserToCampaignInviteDaos {

    val show = UserToCampaignInviteShowDao

    val index = UserToCampaignInviteIndexDao

    val edit = UserToCampaignInviteEditDao

    val update = UserToCampaignInviteUpdateDao

    val destroy = UserToCampaignInviteDestroyDao

}