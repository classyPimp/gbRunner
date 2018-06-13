package models.user.factories

import models.account.factories.AccountOfUserRegistrationCreateFactory
import models.user.User
import models.user.UserRequestParametersWrapper

object UserRegistrationCreateFactory {

    fun create(params: UserRequestParametersWrapper): User {
        val user = User()
        user.name = params.name
        user.account = params.account?.let {
            AccountOfUserRegistrationCreateFactory.create(it)
        }
        return user
    }

}