package models.usertocampaigninvite

import models.usertocampaigninvite.UserToCampaignInvite
import utils.requestparameters.IParam

import java.sql.Timestamp

import org.jooq.generated.tables.Users
import models.user.User
import models.user.UserRequestParametersWrapper

class UserToCampaignInviteRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val campaignId: Long? by lazy { requestParameters.get("campaignId")?.long }
    val userThatIsInvitedId: Long? by lazy { requestParameters.get("userThatIsInvitedId")?.long }
    val userThatInvitesId: Long? by lazy { requestParameters.get("userThatInvitesId")?.long }
    val invitationToken: String? by lazy { requestParameters.get("invitationToken")?.string }
    val isAccepted: Timestamp? by lazy { requestParameters.get("isAccepted")?.timestamp }
    val isRejected: Timestamp? by lazy { requestParameters.get("isRejected")?.timestamp }
    val userToInvite: UserRequestParametersWrapper? by lazy {
        requestParameters.get("userToInvite")?.let {
            UserRequestParametersWrapper(it)
        }
    }
    val userThatInvites: UserRequestParametersWrapper? by lazy {
        requestParameters.get("userThatInvites")?.let {
            UserRequestParametersWrapper(it)
        }
    }


}