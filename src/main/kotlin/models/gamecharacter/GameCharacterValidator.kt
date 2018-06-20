package models.gamecharacter

import models.genericgenericlink.GenericGenericLink
import models.genericgenericlink.GenericGenericLinkValidator
import orm.gamecharactergeneratedrepository.GameCharacterValidatorTrait

class GameCharacterValidator(model: GameCharacter) : GameCharacterValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

    fun createForPlayerAsPrimaryPlayerCharacterScenario() {
        validateGenericGenericLink()
    }

    private fun validateGenericGenericLink() {
        val userToCharacterLink = model.linksToUsers!!.first()
        GenericGenericLinkValidator(userToCharacterLink).let {
            it.userToGameCharacterAsPlayerPrimaryCharacterCreateScenario()
            if (!it.validationManager.isValid()) {
                throw IllegalStateException()
            }
        }
    }

}