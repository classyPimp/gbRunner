package models.user

import models.account.AccountValidator
import orm.usergeneratedrepository.UserValidatorTrait

/**
 * Created by Муса on 20.11.2017.
 */
class UserValidator(model: User) : UserValidatorTrait(model, model.record.validationManager) {

    fun createScenario(): Boolean {
        validateName()
        validateAccount()

        return this.validationManager.isValid()
    }

    private fun validateAccount() {
        accountTester().let {
            test ->
            test.shouldNotBeNull(model.account)
            model.account?.let {
                testGeneral {
                    AccountValidator(it).createScenario()
                }
            }
        }
    }

    private fun validateName() {
        nameTester().let {
            test->
            if (test.shouldNotBeNull(model.name)) {
                model.name?.let {
                    test.shouldBeLongerThan(it, 3)
                }
            }
        }
    }



}