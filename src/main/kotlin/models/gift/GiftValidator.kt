package models.gift

import models.gift.daos.GiftDaos
import orm.giftgeneratedrepository.GiftValidatorTrait

class GiftValidator(model: Gift) : GiftValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

    fun updateScenario() {
        validateName()
        validateDescription()
    }

    private fun validateName() {
        val name = model.name
        if (name == null || name.isBlank()) {
            validationManager.addNameError("should be provided")
            return
        }
        if (GiftDaos.show.existsWithName(name)) {
            validationManager.addNameError("such gift exists")
            return
        }
    }

    private fun validateDescription() {
        val description = model.description
        if (description == null || description.isBlank()) {
            validationManager.addDescriptionError("should be provided")
            return
        }
    }



}