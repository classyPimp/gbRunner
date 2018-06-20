package composers.usertocampaigninvite

import models.genericgenericlink.GenericGenericLink
import models.genericgenericlink.daos.GenericGenericLinkDaos
import models.genericgenericlink.factories.CampaignGenericGenericLinkToUserAsPlayerFactory
import utils.composer.ComposerBase
import models.usertocampaigninvite.UserToCampaignInvite
import models.usertocampaigninvite.UserToCampaignInviteRequestParametersWrapper
import models.usertocampaigninvite.UserToCampaignInviteValidator
import models.usertocampaigninvite.daos.UserToCampaignInviteDaos
import models.usertocampaigninvite.factories.UserToCampaignInviteCreateFactory
import orm.modelUtils.exceptions.ModelNotFoundError
import orm.services.ModelInvalidError
import orm.utils.TransactionRunner
import utils.composer.composerexceptions.InvalidRequestParametersError
import utils.requestparameters.IParam
import java.sql.Timestamp
import java.time.Instant

class UserToCampaignInviteAcceptInviteComposer(
    val inviteToken: String
) : ComposerBase() {

    lateinit var onSuccess: (UserToCampaignInvite)->Unit
    lateinit var onError: (UserToCampaignInvite)->Unit

    lateinit var userToCampaignInvite: UserToCampaignInvite
    lateinit var campaignToUserAsPlayerLink: GenericGenericLink

    override fun beforeCompose(){
        findAndSetUserToCampaignInvitePreloadingRequired()
        markInviteAsAccepted()
        createCampaignToUserLinkAsPlayer()
        validate()
    }

    private fun findAndSetUserToCampaignInvitePreloadingRequired() {
        UserToCampaignInviteDaos.show.byInviteToken(inviteToken)?.let {
            userToCampaignInvite = it.also {
                it.record.loadCampaign()
            }
        } ?: failImmediately(ModelNotFoundError())
    }

    private fun markInviteAsAccepted() {
        userToCampaignInvite.record.isAccepted = Timestamp.from(Instant.now())
    }

    private fun createCampaignToUserLinkAsPlayer() {
        CampaignGenericGenericLinkToUserAsPlayerFactory.create(
                campaignId = userToCampaignInvite.campaign!!.id!!,
                userId = userToCampaignInvite.userThatIsInvitedId!!
        ).let {
            campaignToUserAsPlayerLink = it
        }
    }

    private fun validate() {
        val userIsAlreadyPlayerOfThisCampaign = GenericGenericLinkDaos.show.userToCampaignLinkAsPlayerExistsFor(
                campaignId = userToCampaignInvite.campaignId!!,
                userId = userToCampaignInvite.userThatIsInvitedId!!
        )
        if (userIsAlreadyPlayerOfThisCampaign) {
            userToCampaignInvite.record.validationManager.addGeneralError("already player")
        }
    }

    override fun compose(){
        TransactionRunner.run { tx ->
            userToCampaignInvite.record.save(tx.inTransactionDsl)
            campaignToUserAsPlayerLink.record.save(tx.inTransactionDsl)
        }
    }

    override fun fail(error: Throwable) {
        when(error) {
            is ModelInvalidError -> {
                onError(userToCampaignInvite)
            }
            else -> {
                throw error
            }
        }
    }

    override fun success() {
        onSuccess.invoke(userToCampaignInvite)
    }

}

