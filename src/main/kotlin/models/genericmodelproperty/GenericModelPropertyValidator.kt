package models.genericmodelproperty

import orm.genericmodelpropertygeneratedrepository.GenericModelPropertyValidatorTrait

class GenericModelPropertyValidator(model: GenericModelProperty) : GenericModelPropertyValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

}