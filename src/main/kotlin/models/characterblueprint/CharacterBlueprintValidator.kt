package models.characterblueprint

import orm.characterblueprintgeneratedrepository.CharacterBlueprintValidatorTrait

class CharacterBlueprintValidator(model: CharacterBlueprint) : CharacterBlueprintValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

}