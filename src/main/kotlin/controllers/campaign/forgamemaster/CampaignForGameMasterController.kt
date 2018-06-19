package controllers.campaign.forgamemaster

import composers.campaign.forgamemaster.CampaignForGameMasterCreateComposer
import composers.campaign.forgamemaster.CampaignForGameMasterUpdateComposer
import controllers.ApplicationControllerBase
import models.campaign.Campaign
import models.campaign.daos.CampaignDaos
import models.campaign.tojsonserializers.CampaignCreateToJsonSerializer
import models.campaign.tojsonserializers.CampaignForGameMasterEditToJsonSerializer
import models.campaign.tojsonserializers.forgamemaster.CampaignForGameMasterIndexToJsonSerializer
import models.campaign.tojsonserializers.forgamemaster.CampaignForGameMasterShowToJsonSerializer
import models.campaign.tojsonserializers.forgamemaster.CampaignForGameMasterUpdateToJsonSerializer
import models.genericgenericlink.GenericGenericLink
import models.genericgenericlink.daos.GenericGenericLinkDaos
import router.src.ServletRequestContext
import javax.servlet.http.HttpServletResponse.*

class CampaignForGameMasterController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun create() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .ifNot {
                    head(SC_UNAUTHORIZED)
                    return
                }

        val composer = CampaignForGameMasterCreateComposer(requestParams(), currentUser)
        composer.onError = {
            renderJson(
                    CampaignCreateToJsonSerializer.onError(it)
            )
        }
        composer.onSuccess = {
            renderJson(
                    CampaignCreateToJsonSerializer.onSuccess(it)
            )
        }
        composer.run()
    }

    fun index() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .ifNot {
                    head(SC_UNAUTHORIZED)
                    return
                }

        val currentUserId = currentUser.userModel!!.id!!
        val campaigns = CampaignDaos.index.whereGameMasterIs(currentUserId)

        renderJson(
                CampaignForGameMasterIndexToJsonSerializer.onSuccess(campaigns)
        )
    }

    fun show() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .ifNot {
                    head(SC_UNAUTHORIZED)
                    return
                }

        val campaignId = routeParams().get("campaignId")?.toLongOrNull()
        if (campaignId == null) {
            sendError(404)
            return
        }

        val campaign = CampaignDaos.show.forGameMasterShow(campaignId, currentUser.userModel!!.id!!)

        if (campaign == null) {
            sendError(404)
            return
        }

        renderJson(
                CampaignForGameMasterShowToJsonSerializer.onSuccess(campaign)
        )
    }

    fun edit() {
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
                }
                .ifNot {
                    sendError(SC_UNAUTHORIZED)
                    return
                }

        val campaign = CampaignDaos.edit.forGameMasterEdit(campaignId)

        if (campaign == null) {
            sendError(SC_NOT_FOUND)
            return
        }

        renderJson(
                CampaignForGameMasterEditToJsonSerializer.onSuccess(campaign)
        )
    }


    fun update() {

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
                }
                .ifNot {
                    sendError(SC_UNAUTHORIZED)
                    return
                }

        val composer = CampaignForGameMasterUpdateComposer(requestParams(), campaignId)
        
        composer.onNotFound = {
            sendError(SC_NOT_FOUND)
        }

        composer.onError = {
            renderJson(
                    CampaignForGameMasterUpdateToJsonSerializer.onError(it)
            )
        }

        composer.onSuccess = {
            renderJson(
                    CampaignForGameMasterUpdateToJsonSerializer.onSuccess(it)
            )
        }

        composer.run()
    }

}