package controllers.usertocampaigninvite

import composers.usertocampaigninvite.UserToCampaignInviteAcceptInviteComposer
import composers.usertocampaigninvite.UserToCampaignInviteCreateComposer
import controllers.ApplicationControllerBase
import models.genericgenericlink.daos.GenericGenericLinkDaos
import models.usertocampaigninvite.tojsonserializers.UserToCampaignInviteCreateToJsonSerializer
import router.src.ServletRequestContext
import javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

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
        val inviteToken: String? = routeParams().get("inviteToken")
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

    }

}