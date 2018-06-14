package models.user.tojsonserializers.management

import models.user.User
import orm.usergeneratedrepository.UserToJsonSerializer

object UserManagementIndexToJsonSerializer {

    fun onSuccess(users: MutableList<User>): String {
        return UserToJsonSerializer.serialize(users) {
            it.includeAccount() {
                it.except { arrayOf(it.password) }
            }
            it.includeUserToUserRoleLinks() {
                it.includeUserRole()
            }
        }.toString()
    }

}