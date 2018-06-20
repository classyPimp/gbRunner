package composers.usertocampaigninvite

import utils.composer.ComposerBase
import models.usertocampaigninvite.UserToCampaignInvite
import models.usertocampaigninvite.UserToCampaignInviteRequestParametersWrapper
import models.usertocampaigninvite.UserToCampaignInviteValidator
import models.usertocampaigninvite.daos.UserToCampaignInviteDaos
import models.usertocampaigninvite.factories.UserToCampaignInviteCreateFactory
import orm.services.ModelInvalidError
import utils.composer.composerexceptions.InvalidRequestParametersError
import utils.requestparameters.IParam

class UserToCampaignInviteCreateComposer(
        val params: IParam,
        val campaignId: Long,
        val userThatInvitesId: Long
) : ComposerBase() {

    lateinit var onSuccess: (UserToCampaignInvite)->Unit
    lateinit var onError: (UserToCampaignInvite)->Unit

    lateinit var userToCampaignInviteToCreate: UserToCampaignInvite
    lateinit var wrappedParams: UserToCampaignInviteRequestParametersWrapper

    override fun beforeCompose(){
        wrapParams()
        build()
        validate()
    }

    private fun wrapParams() {
        params.get("userToCampaignInvite")?.let {
            wrappedParams = UserToCampaignInviteRequestParametersWrapper(it)
        } ?: failImmediately(InvalidRequestParametersError())
    }

    private fun build() {
        userToCampaignInviteToCreate = UserToCampaignInviteCreateFactory.create(
                params = wrappedParams, campaignId = campaignId, userThatInvitesId = userThatInvitesId
        )
        val previousActiveInviteForUser = UserToCampaignInviteDaos.show.activeForUser(
                userThatIsInvitedId = userToCampaignInviteToCreate.userThatIsInvitedId!!,
                campaignId = campaignId
        )
        if (previousActiveInviteForUser != null) {
            userToCampaignInviteToCreate = previousActiveInviteForUser
        }
    }

    private fun validate() {
        UserToCampaignInviteValidator(userToCampaignInviteToCreate).let {
            it.createScenario()
            if (!it.validationManager.isValid()) {
                failImmediately(ModelInvalidError())
            }
        }
    }

    override fun compose(){
        if (!userToCampaignInviteToCreate.record.isPersited()) {
            userToCampaignInviteToCreate.record.save()
        }
    }

    override fun fail(error: Throwable) {
        when(error) {
            is ModelInvalidError -> {
                onError(userToCampaignInviteToCreate)
            }
            else -> {
                throw error
            }
        }
    }

    override fun success() {
        onSuccess.invoke(userToCampaignInviteToCreate)
    }

}

