package models.gift

import orm.giftgeneratedrepository.GiftValidatorTrait

class GiftValidator(model: Gift) : GiftValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

}