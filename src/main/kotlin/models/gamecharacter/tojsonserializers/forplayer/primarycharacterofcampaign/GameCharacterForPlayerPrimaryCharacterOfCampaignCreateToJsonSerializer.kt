package models.gamecharacter.tojsonserializers.forplayer.primarycharacterofcampaign

import models.gamecharacter.GameCharacter
import orm.gamecharactergeneratedrepository.GameCharacterToJsonSerializer

object GameCharacterForPlayerPrimaryCharacterOfCampaignCreateToJsonSerializer {

    fun onSuccess(gameCharacter: GameCharacter): String {
        GameCharacterToJsonSerializer(gameCharacter).let {
            it.includeLinksToGifts()
            it.includeLinksToWords()
            return it.serializeToString()
        }
    }

    fun onError(gameCharacter: GameCharacter): String {
        GameCharacterToJsonSerializer(gameCharacter). let {
            it.includeLinksToGifts() {
                it.includeErrors()
                it.includeGift() {
                    it.includeErrors()
                }
            }
            it.includeLinksToWords() {
                it.includeErrors()
                it.includeWord() {
                    it.includeErrors()
                }
            }
            it.includeErrors()

            return it.serializeToString()
        }
    }

}