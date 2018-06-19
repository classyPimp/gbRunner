package models.usertocampaigninvite

import models.campaign.Campaign
import org.jooq.generated.tables.UserToCampaignInvites
import orm.annotations.*
import orm.usertocampaigninvitegeneratedrepository.UserToCampaignInviteRecord
import java.sql.Timestamp
import org.jooq.generated.tables.Users
import models.user.User

@IsModel(jooqTable = UserToCampaignInvites::class)
class UserToCampaignInvite {

    val record: UserToCampaignInviteRecord by lazy { UserToCampaignInviteRecord(this) }

    @TableField(name = "ID")
    @IsPrimaryKey
    var id: Long? = null

    @TableField(name = "CREATED_AT")
    var createdAt: Timestamp? = null

    @TableField(name = "UPDATED_AT")
    var updatedAt: Timestamp? = null

    @TableField(name = "CAMPAIGN_ID")
    var campaignId: Long? = null

    @TableField(name = "USER_THAT_IS_INVITED_ID")
    var userThatIsInvitedId: Long? = null

    @TableField(name = "USER_THAT_INVITES_ID")
    var userThatInvitesId: Long? = null

    @TableField(name = "INVITATION_TOKEN")
    var invitationToken: String? = null

    @TableField(name = "IS_ACCEPTED")
    var isAccepted: Timestamp? = null

    @TableField(name = "IS_REJECTED")
    var isRejected: Timestamp? = null

    @BelongsTo(model = User::class, fieldOnThis = "USER_THAT_IS_INVITED_ID", fieldOnThat = "ID")
    var userToInvite: User? = null

    @BelongsTo(model = User::class, fieldOnThis = "USER_THAT_INVITES_ID", fieldOnThat = "ID")
    var userThatInvites: User? = null

    @BelongsTo(model = Campaign::class, fieldOnThat = "ID", fieldOnThis = "CAMPAIGN_ID")
    var campaign: Campaign? = null


}

