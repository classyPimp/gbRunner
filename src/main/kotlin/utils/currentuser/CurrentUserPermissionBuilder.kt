package utils.currentuser

/**
 * Created by Муса on 15.06.2018.
 */
class CurrentUserPermissionBuilder(private val currentUser: ICurrentUser) {

    var isAuthorized: Boolean = true

    fun shouldBeLoggedIn(): CurrentUserPermissionBuilder {
        if (!currentUser.isLoggedIn()) {
            isAuthorized = false
        }
        return this
    }

    fun shouldHaveRole(roleName: String): CurrentUserPermissionBuilder {
        if (!currentUser.isLoggedIn()) {
            isAuthorized = false
            return this
        }
        val userModel = currentUser.userModel
        if (userModel == null) {
            isAuthorized = false
            return this
        }
        val userRoles = userModel.userRoles
        if (userRoles == null || userRoles.isEmpty()) {
            isAuthorized = false
            return this
        }
        userRoles.find {
            it.name == roleName
        }.let {
            if (it == null) {
                isAuthorized = false
                return this
            }
        }
        return this
    }

    inline fun should(block: ()->Boolean): CurrentUserPermissionBuilder {
        if (!block.invoke()) {
           isAuthorized = false
        }
        return this
    }


    inline fun ifNot(block: ()->Unit) {
        if (isAuthorized == false) {
            block.invoke()
        }
    }


}