package controllers.usertocampaigninvite

import composers.usertocampaigninvite.UserToCampaignInviteAcceptInviteComposer
import composers.usertocampaigninvite.UserToCampaignInviteCreateComposer
import controllers.ApplicationControllerBase
import models.genericgenericlink.daos.GenericGenericLinkDaos
import models.usertocampaigninvite.tojsonserializers.UserToCampaignShowToJsonSerializer
import models.usertocampaigninvite.daos.UserToCampaignInviteDaos
import models.usertocampaigninvite.tojsonserializers.UserToCampaignInviteAcceptInviteToJsonSerializer
import models.usertocampaigninvite.tojsonserializers.UserToCampaignInviteCreateToJsonSerializer
import router.src.ServletRequestContext
import javax.servlet.http.HttpServletResponse.*

class UserToCampaignInviteController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun create() {
        val campaignId = routeParams().get("campaignId")?.toLongOrNull()

        if (campaignId == null) {
            sendError(SC_INTERNAL_SERVER_ERROR)
            return
        }

        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .should {
                    GenericGenericLinkDaos.show.ofCampaignToUserLinkByCampaignAndUserExists(
                            campaignId = campaignId,
                            userId = currentUser.userModel!!.id!!
                    )
                }.ifNot {
                    sendError(SC_UNAUTHORIZED)
                    return
                }

        val userThatInvitesId = currentUser.userModel!!.id!!
        val composer = UserToCampaignInviteCreateComposer(
                params = requestParams(),
                campaignId = campaignId,
                userThatInvitesId = userThatInvitesId
        )

        composer.onError = {
            renderJson(
                    UserToCampaignInviteCreateToJsonSerializer.onSuccess(it)
            )
        }

        composer.onSuccess = {
            renderJson(
                    UserToCampaignInviteCreateToJsonSerializer.onError(it)
            )
        }

        composer.run()

    }

    fun acceptInvite() {
        val inviteToken: String? = routeParams().get("invitationToken").toString()
        if (inviteToken == null) {
            sendError(SC_INTERNAL_SERVER_ERROR)
            return
        }

        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .ifNot {
                    sendError(SC_UNAUTHORIZED)
                    return
                }

        val composer = UserToCampaignInviteAcceptInviteComposer(inviteToken)

        composer.onError = {
            renderJson(
                    UserToCampaignInviteAcceptInviteToJsonSerializer.onError(it)
            )
        }

        composer.onSuccess = {
            renderJson(
                    UserToCampaignInviteAcceptInviteToJsonSerializer.onSuccess(it)
            )
        }

        composer.run()

    }

    fun show() {
        val invitationToken = routeParams().get("invitationToken")?.toString()
        if (invitationToken == null) {
            sendError(SC_INTERNAL_SERVER_ERROR)
            return
        }
        val userToCampaignInvite = UserToCampaignInviteDaos.show.byToken(invitationToken)

        if (userToCampaignInvite == null) {
            sendError(SC_NOT_FOUND)
            return
        }

        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .should {
                    userToCampaignInvite.userThatIsInvitedId!! == currentUser.userModel!!.id!!
                }
                .ifNot {
                    sendError(SC_UNAUTHORIZED)
                    return
                }

        renderJson(
                UserToCampaignShowToJsonSerializer.onSuccess(userToCampaignInvite)
        )

    }

}