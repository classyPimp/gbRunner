package models.user.tojsonserializers

import models.user.User
import orm.usergeneratedrepository.UserToJsonSerializer

object UserRegistrationCreateToJsonSerializer {

    fun onSuccess(user: User): String {
        UserToJsonSerializer(user).let {
            it.includeAccount() {
                it.except {
                    arrayOf(it.password)
                }
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