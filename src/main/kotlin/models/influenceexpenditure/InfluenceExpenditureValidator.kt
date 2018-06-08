package models.influenceexpenditure

import orm.influenceexpendituregeneratedrepository.InfluenceExpenditureValidatorTrait

class InfluenceExpenditureValidator(model: InfluenceExpenditure) : InfluenceExpenditureValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

}