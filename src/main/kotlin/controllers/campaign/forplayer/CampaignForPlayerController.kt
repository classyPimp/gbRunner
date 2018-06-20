package controllers.campaign.forplayer

import controllers.ApplicationControllerBase
import models.campaign.daos.CampaignDaos
import models.campaign.tojsonserializers.forgamemaster.CampaignForGameMasterIndexToJsonSerializer
import models.campaign.tojsonserializers.forgamemaster.CampaignForGameMasterShowToJsonSerializer
import models.campaign.tojsonserializers.forplayer.CampaignForPlayerShowToJsonSerializer
import router.src.ServletRequestContext
import javax.servlet.http.HttpServletResponse

class CampaignForPlayerController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun index() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .ifNot {
                    head(HttpServletResponse.SC_UNAUTHORIZED)
                    return
                }

        val currentUserId = currentUser.userModel!!.id!!
        val campaigns = CampaignDaos.index.wherePlayerIs(currentUserId)

        renderJson(
                CampaignForGameMasterIndexToJsonSerializer.onSuccess(campaigns)
        )
    }

    fun show() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .ifNot {
                    head(HttpServletResponse.SC_UNAUTHORIZED)
                    return
                }

        val campaignId = routeParams().get("campaignId")?.toLongOrNull()
        if (campaignId == null) {
            sendError(404)
            return
        }

        val campaign = CampaignDaos.show.forPlayerShow(campaignId = campaignId, userAsPlayerId =  currentUser.userModel!!.id!!)

        if (campaign == null) {
            sendError(404)
            return
        }

        renderJson(
                CampaignForPlayerShowToJsonSerializer.onSuccess(campaign)
        )
    }

}