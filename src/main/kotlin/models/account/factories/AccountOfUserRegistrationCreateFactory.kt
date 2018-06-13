package models.account.factories

import models.account.Account
import models.account.AccountRequestParametersWrapper

object AccountOfUserRegistrationCreateFactory {

    fun create(params: AccountRequestParametersWrapper): Account {
        val account = Account()
        account.email = params.email
        account.password = params.password
        account.passwordConfirmation = params.passwordConfirmation
        return account
    }
}