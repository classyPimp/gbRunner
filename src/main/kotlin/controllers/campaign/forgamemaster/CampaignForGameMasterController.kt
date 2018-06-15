package controllers.campaign.forgamemaster

import composers.campaign.forgamemaster.CampaignForGameMasterCreateComposer
import controllers.ApplicationControllerBase
import models.campaign.daos.CampaignDaos
import models.campaign.tojsonserializers.CampaignCreateToJsonSerializer
import models.campaign.tojsonserializers.CampaignForGameMasterIndexToJsonSerializer
import router.src.ServletRequestContext
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

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

}