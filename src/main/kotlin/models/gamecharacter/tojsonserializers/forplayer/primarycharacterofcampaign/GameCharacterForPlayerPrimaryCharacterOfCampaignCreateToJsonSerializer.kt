package models.gamecharacter.tojsonserializers.forplayer.primarycharacterofcampaign

import models.gamecharacter.GameCharacter
import orm.gamecharactergeneratedrepository.GameCharacterToJsonSerializer

object GameCharacterForPlayerPrimaryCharacterOfCampaignCreateToJsonSerializer {

    fun onSuccess(gameCharacter: GameCharacter): String {
        GameCharacterToJsonSerializer(gameCharacter).let {

            return it.serializeToString()
        }
    }

    fun onError(gameCharacter: GameCharacter): String {
        GameCharacterToJsonSerializer(gameCharacter). let {


            it.includeErrors()
            return it.serializeToString()
        }
    }

}