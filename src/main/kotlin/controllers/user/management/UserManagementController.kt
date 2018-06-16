package controllers.user.management

import controllers.ApplicationControllerBase
import models.user.daos.UserDaos
import models.user.tojsonserializers.management.UserManagementIndexToJsonSerializer
import models.userrole.PredefinedUserRoleManager
import models.userrole.UserRole
import router.src.ServletRequestContext
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

class UserManagementController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun index() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .shouldHaveRole(PredefinedUserRoleManager.PredefinedRoleNames.SUPER_USER.toString())
                .ifNot {
                    sendError(SC_UNAUTHORIZED)
                    return
                }

        val users = UserDaos.index.forManagementIndex()
        renderJson(
                UserManagementIndexToJsonSerializer.onSuccess(users)
        )
    }

}