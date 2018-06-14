package models.userrole.tojsonserializers.forformfeed

import models.userrole.UserRole
import orm.userrolegeneratedrepository.UserRoleToJsonSerializer

object UserRoleForFormFeedIndexToJsonSerializer {

    fun onSuccess(userRoles: MutableList<UserRole>): String {
        return UserRoleToJsonSerializer.serialize(userRoles).toString()
    }

}