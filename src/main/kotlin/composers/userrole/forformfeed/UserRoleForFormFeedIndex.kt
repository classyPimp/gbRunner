package composers.userrole.forformfeed

import utils.composer.ComposerBase
import models.userrole.UserRole

class UserRoleForFormFeedIndex : ComposerBase() {

    lateinit var onSuccess: (UserRole)->Unit
    lateinit var onError: (UserRole)->Unit

    override fun beforeCompose(){

    }

    override fun compose(){

    }

    override fun fail(error: Throwable) {
        when(error) {

            else -> {
                throw error
            }
        }
    }

    override fun success() {
        onSuccess.invoke()
    }

}

