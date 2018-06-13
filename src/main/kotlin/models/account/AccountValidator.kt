package models.account

import models.account.daos.AccountDaos
import orm.accountgeneratedrepository.AccountValidatorTrait

/**
 * Created by Муса on 20.11.2017.
 */
class AccountValidator(model: Account) : AccountValidatorTrait(model, model.record.validationManager) {

    fun userRegistrationCreateScenario() {
        validatePassword()
        validateEmail()
    }

    fun createScenario(): Boolean {
        validatePassword()
        validateEmail()
        return validationManager.isValid()
    }

    private fun validateEmail() {
        val email = model.email
        if (email == null) {
            validationManager.addEmailError("should be provided")
            return
        }
        emailTester().let {
            test ->
            test.shouldBeValidEmail(email)
        }
        if (AccountDaos.show.existsWithSuchEmail(email)) {
            validationManager.addEmailError("try another email")
            return
        }
    }

    private fun validatePassword() {
        val password = model.password
        if (password == null) {
            validationManager.addPasswordError("should be provided")
            return
        }
        passwordTester().let {
            test ->
            password.let {
                test("passwordConfirmation", "doesn't match") {
                    (it == model.passwordConfirmation)
                }
                test.shouldBeLongerThan(it, 3)
            }

        }
    }


}