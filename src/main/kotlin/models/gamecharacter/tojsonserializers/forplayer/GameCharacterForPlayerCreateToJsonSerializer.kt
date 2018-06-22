package models.gamecharacter.tojsonserializers.forplayer

import models.gamecharacter.GameCharacter
import orm.gamecharactergeneratedrepository.GameCharacterToJsonSerializer

/**
 * Created by Муса on 21.06.2018.
 */
object GameCharacterForPlayerCreateToJsonSerializer {

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