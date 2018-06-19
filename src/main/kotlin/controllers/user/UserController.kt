package controllers.user

import controllers.ApplicationControllerBase
import models.user.daos.UserDaos
import models.user.tojsonserializers.UserIndexToJsonSerializer
import router.src.ServletRequestContext

class UserController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun index() {
        val users = UserDaos.index.forIndex()

        renderJson(
                UserIndexToJsonSerializer.onSuccess(users)
        )
    }

}