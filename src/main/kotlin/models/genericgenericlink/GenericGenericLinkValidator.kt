package models.genericgenericlink

import models.user.User
import orm.genericgenericlinkgeneratedrepository.GenericGenericLinkValidatorTrait

class GenericGenericLinkValidator(model: GenericGenericLink) : GenericGenericLinkValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

    fun linkCampaignToUserAsGameMasterCreateScenario() {
        validateCategory(GenericGenericLink.Categories.UserToCampaignLink.GAME_MASTER.toString())
        validateUserId()
        validateUserType()
    }

    private fun validateCategory(categoryName: String) {
        val category = model.category
        if (category == null || category != categoryName) {
            throw IllegalStateException("invalid category")
        }
    }

    private fun validateUserId() {
        val userId = model.rightModelId
        if (userId == null) {
            throw IllegalStateException("rightModelId (user) not assigned")
        }
    }

    private fun validateUserType() {
        val userType = model.rightModelType
        if (userType == null || userType != User::class.simpleName) {
            throw IllegalStateException("invalid rightModelType (user)")
        }
    }


}