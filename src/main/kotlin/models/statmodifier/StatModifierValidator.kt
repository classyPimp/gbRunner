package models.statmodifier

import orm.statmodifiergeneratedrepository.StatModifierValidatorTrait

class StatModifierValidator(model: StatModifier) : StatModifierValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

}