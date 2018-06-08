package models.dominionexpenditure

import orm.dominionexpendituregeneratedrepository.DominionExpenditureValidatorTrait

class DominionExpenditureValidator(model: DominionExpenditure) : DominionExpenditureValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

}