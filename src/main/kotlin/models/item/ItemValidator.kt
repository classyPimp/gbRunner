package models.item

import orm.itemgeneratedrepository.ItemValidatorTrait

class ItemValidator(model: Item) : ItemValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

}