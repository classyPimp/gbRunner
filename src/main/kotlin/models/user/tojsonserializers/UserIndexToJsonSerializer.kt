package models.user.tojsonserializers

import models.user.User
import orm.usergeneratedrepository.UserToJsonSerializer

object UserIndexToJsonSerializer {

    fun onSuccess(users: MutableList<User>): String {
        UserToJsonSerializer.serialize(users).let {
            return it.serializeToString()
        }
    }


}