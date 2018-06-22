package models.gamecharacter.tojsonserializers.forplayer.primarycharacterofcampaign

import models.gamecharacter.GameCharacter
import orm.gamecharactergeneratedrepository.GameCharacterToJsonSerializer

object GameCharacterForPlayerPrimaryCharacterOfCampaignShowToJsonSerializer {

    fun onSuccess(gameCharacter: GameCharacter): String {
        GameCharacterToJsonSerializer(gameCharacter).let {
            it.includeLinksToWords() {
                it.includeWord()
            }
            it.includeLinksToGifts() {
                it.includeGift()
            }
            return it.serializeToString()
        }
    }

    fun onError(gameCharacter: GameCharacter): String {
        GameCharacterToJsonSerializer(gameCharacter).let {
            it.includeErrors()
            return it.serializeToString()
        }
    }

}