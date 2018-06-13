package models.user.factories.session

import models.account.Account
import models.user.User
import models.user.UserRequestParametersWrapper

object UserIntermediaryInstanceForSessionFactory {

    fun create(wrappedParams: UserRequestParametersWrapper): User {
        return User().also {
            it.name = wrappedParams.name
            it.account = Account().also {
                it.password = wrappedParams.account?.password
            }
        }
    }

}