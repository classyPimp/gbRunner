package controllers.user

import composers.user.UserRegistrationComposer
import controllers.ApplicationControllerBase
import models.user.User
import models.user.tojsonserializers.UserRegistrationCreateToJsonSerializer
import orm.usergeneratedrepository.UserToJsonSerializer
import router.src.ServletRequestContext

class UserRegistrationController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun create() {
        val params = requestParams()
        val composer = UserRegistrationComposer(params)
        composer.onError = {
            renderJson(
                    UserRegistrationCreateToJsonSerializer.onError(it)
            )
        }
        composer.onSuccess = {
            currentUser.logIn(it)
            User.pluckUserRolesFromUserToUserRoleLinkAndAssignToUserRoles(it)
            renderJson(
                    UserRegistrationCreateToJsonSerializer.onSuccess(it)
            )
        }
        composer.run()
    }

}