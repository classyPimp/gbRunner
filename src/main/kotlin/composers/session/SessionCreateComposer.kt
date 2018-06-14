package composers.session

import models.user.User
import models.user.UserRequestParametersWrapper
import models.user.daos.UserDaos
import models.user.factories.session.UserIntermediaryInstanceForSessionFactory
import orm.services.ModelInvalidError
import utils.composer.ComposerBase
import utils.composer.composerexceptions.InvalidRequestParametersError
import utils.requestparameters.IParam
import utils.security.PasswordHashingService


class SessionCreateComposer(val params: IParam) : ComposerBase() {

    lateinit var onSuccess: (User)->Unit
    lateinit var onError: (User)->Unit

    lateinit var userToLogin: User
    lateinit var wrappedParams: UserRequestParametersWrapper
    lateinit var intermediaryUserInstance: User

    override fun beforeCompose(){
        wrapParams()
        buildIntermediaryUserInstance()
        checkIfUserAuthorized()
    }

    private fun wrapParams() {
        params.get("user")?.let {
            wrappedParams = UserRequestParametersWrapper(it)
        } ?: failImmediately(InvalidRequestParametersError())
    }

    private fun buildIntermediaryUserInstance() {
        intermediaryUserInstance = UserIntermediaryInstanceForSessionFactory.create(wrappedParams)
    }


    private fun checkIfUserAuthorized() {
        if (intermediaryUserInstance.name == null) {
            intermediaryUserInstance.record.validationManager.addNameError("you didn't type in")
            failImmediately(ModelInvalidError())
            return
        }

        if (intermediaryUserInstance.account?.password == null) {
            intermediaryUserInstance.record.validationManager.markAsHasNestedErrors()
            intermediaryUserInstance.account!!.record.validationManager.addPasswordError("you didn't type in")
            failImmediately(ModelInvalidError())
            return
        }

        val user = UserDaos.show.findUserWithNameOrEmail(wrappedParams.name!!)
        if (user == null) {
            intermediaryUserInstance.record.validationManager.addNameError("combination of credentials and password is not correct")
            failImmediately(ModelInvalidError())
            return
        }

        userToLogin = user
        val passwordToTest = intermediaryUserInstance.account!!.password!!
        val hashedPassword = userToLogin.account!!.password!!

        PasswordHashingService.checkPassword(passwordToTest, hashedPassword).let {
            if (!it)  {
                intermediaryUserInstance.record.validationManager.addNameError("combination of credentials and password is not correct")
                failImmediately(ModelInvalidError())
                return
            }
        }

    }

    override fun compose(){

    }

    override fun fail(error: Throwable) {
        when(error) {
            is ModelInvalidError -> {
                onError(intermediaryUserInstance)
            }
            else -> {
                throw error
            }
        }
    }

    override fun success() {
        onSuccess.invoke(userToLogin)
    }

}

