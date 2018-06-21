package composers.gamecharacter.forplayer.primarycharacterofcampaign

import utils.composer.ComposerBase
import models.gamecharacter.GameCharacter
import models.gamecharacter.GameCharacterRequestParametersWrapper
import models.gamecharacter.GameCharacterValidator
import models.gamecharacter.factories.forplayer.GameCharacterForPlayerCreateFactory
import orm.services.ModelInvalidError
import orm.utils.TransactionRunner
import utils.composer.composerexceptions.InvalidRequestParametersError
import utils.requestparameters.IParam

class GameCharacterForPlayerPrimaryCharacterOfCampaignCreateComposer(
        val params: IParam,
        val campaignId: Long,
        val playerId: Long
) : ComposerBase() {

    lateinit var onSuccess: (GameCharacter)->Unit
    lateinit var onError: (GameCharacter)->Unit

    lateinit var gameCharacterToCreate: GameCharacter
    lateinit var wrappedParams: GameCharacterRequestParametersWrapper

    override fun beforeCompose(){
        wrapParams()
        build()
        validate()
    }

    private fun wrapParams() {
        params.get("gameCharacter")?.let {
            wrappedParams = GameCharacterRequestParametersWrapper(it)
        } ?: failImmediately(InvalidRequestParametersError())
    }

    private fun build() {
        gameCharacterToCreate = GameCharacterForPlayerCreateFactory.create(
                params = wrappedParams,
                campaignId = campaignId,
                playerId = playerId
        )
    }

    private fun validate() {
        GameCharacterValidator(gameCharacterToCreate).let {
            it.createForPlayerAsPrimaryPlayerCharacterScenario()
            if (!it.validationManager.isValid()) {
                failImmediately(ModelInvalidError())
            }
        }
    }

    override fun compose(){
        TransactionRunner.run { tx ->
            gameCharacterToCreate.record.saveCascade(tx.inTransactionDsl)
        }
    }

    override fun fail(error: Throwable) {
        when(error) {
            is ModelInvalidError -> {
                onError.invoke(gameCharacterToCreate)
            }
            else -> {
                throw error
            }
        }
    }

    override fun success() {
        onSuccess.invoke(gameCharacterToCreate)
    }

}

