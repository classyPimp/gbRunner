package controllers.gift

import composers.gift.GiftUpdateComposer
import controllers.ApplicationControllerBase
import models.gift.daos.GiftDaos
import models.gift.tojsonserializers.GiftOfWordIndexToJsonSerializer
import models.gift.tojsonserializers.GiftShowToJsonSerializer
import models.gift.tojsonserializers.GiftUpdateToJsonSerializer
import models.userrole.PredefinedUserRoleManager
import router.src.ServletRequestContext
import javax.servlet.http.HttpServletResponse.SC_NOT_FOUND
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

class GiftController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun show() {
        val giftId = routeParams().get("giftId")!!.toLong()
        val gift = GiftDaos.show.byId(giftId)
        if (gift == null) {
            sendError(SC_NOT_FOUND)
            return
        }
        renderJson(
                GiftShowToJsonSerializer.onSuccess(gift)
        )
    }

    fun ofWordIndex() {
        val wordId = routeParams().get("wordId")!!.toLong()
        val gifts = GiftDaos.index.ofWordIndex(wordId)
        renderJson(
                GiftOfWordIndexToJsonSerializer.onSuccess(gifts)
        )

    }

    fun update() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .shouldHaveRole(PredefinedUserRoleManager.PredefinedRoleNames.SUPER_USER.toString())
                .ifNot {
                    sendError(SC_UNAUTHORIZED)
                    return
                }
        val giftId = routeParams().get("giftId")!!.toLong()

        val composer = GiftUpdateComposer(requestParams(), giftId)

        composer.onError = {
            renderJson(
                    GiftUpdateToJsonSerializer.onError(it)
            )
        }

        composer.onSuccess = {
            renderJson(
                    GiftUpdateToJsonSerializer.onSuccess(it)
            )
        }

        composer.run()


    }

}