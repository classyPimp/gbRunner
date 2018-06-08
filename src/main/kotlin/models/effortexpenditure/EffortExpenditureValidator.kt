package models.effortexpenditure

import orm.effortexpendituregeneratedrepository.EffortExpenditureValidatorTrait

class EffortExpenditureValidator(model: EffortExpenditure) : EffortExpenditureValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

}