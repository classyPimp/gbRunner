package models.gamecharacter.tojsonserializers.forplayer

import models.gamecharacter.GameCharacter
import orm.gamecharactergeneratedrepository.GameCharacterToJsonSerializer

/**
 * Created by Муса on 21.06.2018.
 */
object GameCharacterForPlayerShowToJsonSerializer {

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