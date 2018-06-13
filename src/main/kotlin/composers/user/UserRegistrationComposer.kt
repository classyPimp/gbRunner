package composers.user

import utils.composer.ComposerBase
import models.user.User
import models.user.UserRequestParametersWrapper
import models.user.UserValidator
import models.user.factories.UserRegistrationCreateFactory
import orm.services.ModelInvalidError
import orm.utils.TransactionRunner
import utils.composer.composerexceptions.InvalidRequestParametersError
import utils.requestparameters.IParam
import utils.security.PasswordHashingService


class UserRegistrationComposer(val params: IParam) : ComposerBase() {

    lateinit var onSuccess: (User)->Unit
    lateinit var onError: (User)->Unit

    lateinit var userToRegister: User
    lateinit var wrappedParams: UserRequestParametersWrapper

    override fun beforeCompose(){
        wrapParams()
        buildUser()
        validate()
        hashPassword()
    }

    private fun wrapParams() {
        params.get("user")?.let {
            wrappedParams = UserRequestParametersWrapper(it)
        } ?: failImmediately(InvalidRequestParametersError())
    }

    private fun buildUser() {
        userToRegister = UserRegistrationCreateFactory.create(wrappedParams)
    }

    private fun validate() {
        UserValidator(userToRegister).also {
            it.registrationCreateScenario()
            if (!userToRegister.record.validationManager.isValid()) {
                failImmediately(ModelInvalidError())
            }
        }
    }

    private fun hashPassword() {
        userToRegister.account!!.password = userToRegister.account!!.password!!.let {
            PasswordHashingService.hashPassword(it)
        }
    }

    override fun compose(){
        TransactionRunner.run { tx ->
            userToRegister.record.saveCascade(tx.inTransactionDsl)
        }
    }

    override fun fail(error: Throwable) {
        when(error) {
            is ModelInvalidError -> {
                onError(userToRegister.also {
                    it.account?.password = wrappedParams.account?.password
                })
            }
            else -> {
                throw error
            }
        }
    }

    override fun success() {
        onSuccess.invoke(userToRegister)
    }

}

