package models.user.tojsonserializers

import models.user.User
import orm.usergeneratedrepository.UserToJsonSerializer

object Create {

    fun onSuccess(user: User): String {
        UserToJsonSerializer(user).let {
            it.includeAccount() {
                it.set("passwordConfirmation", it.model.passwordConfirmation)
            }
            return it.serializeToString()
        }
    }

    fun onError(user: User): String {
        UserToJsonSerializer(user). let {
            it.includeAccount() {
                it.includeErrors()
                it.set("passwordConfirmation", it.model.passwordConfirmation)
            }
            it.includeErrors()
            return it.serializeToString()
        }
    }

}