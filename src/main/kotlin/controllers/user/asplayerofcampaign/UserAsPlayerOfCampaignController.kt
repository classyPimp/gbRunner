package controllers.user.asplayerofcampaign

import controllers.ApplicationControllerBase
import models.user.daos.UserDaos
import models.user.tojsonserializers.asplayerofcampaign.UserAsPlayerOfCampaignIndexToJsonSerializer
import router.src.ServletRequestContext
import javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR

class UserAsPlayerOfCampaignController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun index() {
        val campaignId = routeParams().get("campaignId")?.toLongOrNull()

        if (campaignId == null) {
            sendError(SC_INTERNAL_SERVER_ERROR)
            return
        }

        val users = UserDaos.index.asPlayerOfCampaign(campaignId)

        renderJson(
                UserAsPlayerOfCampaignIndexToJsonSerializer.onSuccess(users)
        )
    }

}