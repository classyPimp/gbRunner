package models.usertocampaigninvite.daos

import org.jooq.generated.tables.UserToCampaignInvites
import orm.usertocampaigninvitegeneratedrepository.UserToCampaignInviteRecord
import models.usertocampaigninvite.UserToCampaignInvite
import org.jooq.generated.Tables.USER_TO_CAMPAIGN_INVITES

object UserToCampaignInviteShowDao {

    val table = USER_TO_CAMPAIGN_INVITES

    fun byInviteToken(inviteToken: String): UserToCampaignInvite? {
        return UserToCampaignInviteRecord.GET()
                .where(
                        table.INVITATION_TOKEN.eq(inviteToken)
                )
                .limit(1)
                .execute()
                .firstOrNull()
    }


}