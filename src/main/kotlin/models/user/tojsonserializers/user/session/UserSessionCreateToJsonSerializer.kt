package models.user.tojsonserializers.user.session

import models.user.User
import orm.usergeneratedrepository.UserToJsonSerializer
import orm.userrolegeneratedrepository.UserRoleToJsonSerializer

object UserSessionCreateToJsonSerializer {

    fun onSuccess(user: User): String {
        User.pluckUserRolesFromUserToUserRoleLinkAndAssignToUserRoles(user)

        UserToJsonSerializer(user).let {
            it.includeAccount() {
                it.except { arrayOf(it.password) }
            }
            user.userRoles?.let {userRoles ->
                    it.set("userRoles", UserRoleToJsonSerializer.serialize(userRoles))
            }
            return it.serializeToString()
        }
    }

    fun onError(user: User): String {
        UserToJsonSerializer(user). let {
            it.includeAccount() {
                it.includeErrors()
            }
            it.includeErrors()
            return it.serializeToString()
        }
    }

}