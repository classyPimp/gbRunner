package models.campaign

import orm.campaigngeneratedrepository.CampaignValidatorTrait

class CampaignValidator(model: Campaign) : CampaignValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

}