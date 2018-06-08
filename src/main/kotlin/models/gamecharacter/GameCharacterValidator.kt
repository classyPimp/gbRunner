package models.gamecharacter

import orm.gamecharactergeneratedrepository.GameCharacterValidatorTrait

class GameCharacterValidator(model: GameCharacter) : GameCharacterValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

}