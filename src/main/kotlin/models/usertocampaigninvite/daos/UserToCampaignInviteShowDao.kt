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

    fun activeForUser(userThatIsInvitedId: Long, campaignId: Long): UserToCampaignInvite? {
        return UserToCampaignInviteRecord.GET()
                .where(
                        table.USER_THAT_IS_INVITED_ID.eq(userThatIsInvitedId)
                                .and(
                                        table.IS_ACCEPTED.isNull
                                ).and(
                                        table.CAMPAIGN_ID.eq(campaignId)
                                )
                )
                .executeGetFirstOrNull()
    }

    fun byToken(invitationToken: String): UserToCampaignInvite? {
        return UserToCampaignInviteRecord.GET()
                .where(table.INVITATION_TOKEN.eq(invitationToken))
                .executeGetFirstOrNull()
    }


}