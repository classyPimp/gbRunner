package controllers.session

import composers.session.SessionCreateComposer
import controllers.ApplicationControllerBase
import models.user.tojsonserializers.user.session.UserSessionCreateToJsonSerializer
import router.src.ServletRequestContext

class SessionController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun create() {
        val params = requestParams()
        val composer = SessionCreateComposer(params)
        composer.onError = {
            renderJson(
                    UserSessionCreateToJsonSerializer.onError(it)
            )
        }
        composer.onSuccess = {
            currentUser.logIn(it)
            renderJson(
                    UserSessionCreateToJsonSerializer.onSuccess(it)
            )
        }
        composer.run()
    }

}