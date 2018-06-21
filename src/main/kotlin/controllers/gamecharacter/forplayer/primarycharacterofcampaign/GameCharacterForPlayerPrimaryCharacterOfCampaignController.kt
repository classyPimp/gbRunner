package controllers.gamecharacter.forplayer.primarycharacterofcampaign

import composers.gamecharacter.forplayer.primarycharacterofcampaign.GameCharacterForPlayerPrimaryCharacterOfCampaignCreateComposer
import controllers.ApplicationControllerBase
import models.gamecharacter.GameCharacter
import models.gamecharacter.daos.GameCharacterDaos
import models.gamecharacter.tojsonserializers.forplayer.primarycharacterofcampaign.GameCharacterForPlayerPrimaryCharacterOfCampaignCreateToJsonSerializer
import models.gamecharacter.tojsonserializers.forplayer.primarycharacterofcampaign.GameCharacterForPlayerPrimaryCharacterOfCampaignShowToJsonSerializer
import models.genericgenericlink.daos.GenericGenericLinkDaos
import router.src.ServletRequestContext
import javax.servlet.http.HttpServletResponse

class GameCharacterForPlayerPrimaryCharacterOfCampaignController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun create() {
        val campaignId = routeParams().get("campaignId")?.toLongOrNull()
        if (campaignId == null) {
            sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            return
        }

        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .should {
                    GenericGenericLinkDaos.show.userToCampaignLinkAsPlayerExistsFor(userId = currentUser.userModel!!.id!!, campaignId = campaignId)
                }
                .ifNot {
                    sendError(HttpServletResponse.SC_UNAUTHORIZED)
                    return
                }

        val composer = GameCharacterForPlayerPrimaryCharacterOfCampaignCreateComposer(
                params = requestParams(),
                campaignId = campaignId,
                playerId = currentUser.userModel!!.id!!
        )

        composer.onError = {
            renderJson(
                    GameCharacterForPlayerPrimaryCharacterOfCampaignCreateToJsonSerializer.onError(it)
            )
        }

        composer.onSuccess = {
            renderJson(
                    GameCharacterForPlayerPrimaryCharacterOfCampaignCreateToJsonSerializer.onSuccess(it)
            )
        }

        composer.run()

    }


    fun show() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .ifNot {
                    sendError(HttpServletResponse.SC_UNAUTHORIZED)
                    return
                }
        val campaignId = routeParams().get("campaignId")?.toLongOrNull()
        if (campaignId == null){
            sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            return
        }
        val gameCharacter = GameCharacterDaos.show.findPrimaryPlayerChar(campaignId = campaignId, playerId = currentUser.userModel!!.id!!)

        if (gameCharacter == null) {
            renderJson(
                    GameCharacterForPlayerPrimaryCharacterOfCampaignShowToJsonSerializer.onError(GameCharacter().also {
                        it.record.validationManager.addGeneralError("NO_CHARACTER_CREATED")
                    })
            )
            return
        }

        renderJson(
                GameCharacterForPlayerPrimaryCharacterOfCampaignShowToJsonSerializer.onSuccess(gameCharacter)
        )
    }

}