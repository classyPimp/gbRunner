package models.user

import models.account.AccountValidator
import models.user.daos.UserDaos
import orm.usergeneratedrepository.UserValidatorTrait

/**
 * Created by Муса on 20.11.2017.
 */
class UserValidator(model: User) : UserValidatorTrait(model, model.record.validationManager) {

    fun registrationCreateScenario() {
        validateName()
        validateAccount()
    }


    private fun validateAccount() {
        val account = model.account
        account ?: throw IllegalStateException("no account for user provided")

        AccountValidator(account).also {
            it.userRegistrationCreateScenario()
            if (!it.validationManager.isValid()) {
                validationManager.markAsHasNestedErrors()
            }
        }
    }

    private fun validateName() {
        val name = model.name
        if (name == null) {
            validationManager.addNameError("should be provided")
            return
        }
        if (name.length < 3) {
            validationManager.addNameError("too short, should be at least 3 characters long")
        }
        if (UserDaos.show.existsWithSuchName(name)) {
            validationManager.addNameError("such name exists")
        }
    }



}