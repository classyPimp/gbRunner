package models.itemblueprint

import orm.itemblueprintgeneratedrepository.ItemBlueprintValidatorTrait

class ItemBlueprintValidator(model: ItemBlueprint) : ItemBlueprintValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

}