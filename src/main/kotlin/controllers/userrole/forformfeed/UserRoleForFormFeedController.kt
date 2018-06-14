package controllers.userrole.forformfeed

import controllers.ApplicationControllerBase
import models.userrole.PredefinedUserRoleManager
import models.userrole.daos.UserRoleDaos
import models.userrole.tojsonserializers.forformfeed.UserRoleForFormFeedIndexToJsonSerializer
import router.src.ServletRequestContext
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

class UserRoleForFormFeedController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun index() {
        if (!currentUser.isLoggedIn()) {
            sendError(SC_UNAUTHORIZED)
            return
        }

        if (!currentUser.hasRole(PredefinedUserRoleManager.PredefinedRoleNames.SUPER_USER.toString())) {
            sendError(SC_UNAUTHORIZED)
            return
        }

        val userRoles = UserRoleDaos.index.forFormFeedIndex()

        renderJson(
                UserRoleForFormFeedIndexToJsonSerializer.onSuccess(userRoles)
        )
    }

}