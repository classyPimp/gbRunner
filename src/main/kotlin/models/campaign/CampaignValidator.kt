package models.campaign

import models.genericgenericlink.GenericGenericLink
import models.genericgenericlink.GenericGenericLinkValidator
import orm.campaigngeneratedrepository.CampaignValidatorTrait

class CampaignValidator(model: Campaign) : CampaignValidatorTrait(model, model.record.validationManager) {

    fun forGameMasterCreateScenario(){
        validateName()
        validateDescription()
        validateCampaignGenericGenericLinkToGameMaster()
    }

    private fun validateName() {
        val name = model.name
        if (name == null) {
            validationManager.addNameError("should be provided")
            return
        }
    }

    private fun validateDescription() {
        val description = model.description
        if (description == null) {
            validationManager.addDescriptionError("should be provided")
            return
        }
    }

    private fun validateCampaignGenericGenericLinkToGameMaster() {
        val genericGenericLink = model.linksToUsers!!.first()
        GenericGenericLinkValidator(genericGenericLink).also {
            it.linkCampaignToUserAsGameMasterCreateScenario()
            if (!it.validationManager.isValid()) {
                validationManager.markAsHasNestedErrors()
            }
        }
    }

}