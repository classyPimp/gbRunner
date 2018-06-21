package models.genericgenericlink

import models.gamecharacter.GameCharacter
import models.gift.Gift
import models.gift.GiftValidator
import models.user.User
import models.word.Word
import models.word.WordValidator
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

    fun whenPrimaryCharacterForPlayerCreateScenarioAslinkToGift() {
        validateGiftLinkData()
        validateGameCharacterId()
        validateGiftWhenGameCharacterForPLayerAsPrimaryCharacterCreated()
        validateCategory(GenericGenericLink.Categories.CharacterToGiftLink.CHARACTER_TO_GIFT.toString())
    }

    fun whenPrimaryCharacterForPlayerCreateScenarioAslinkToWord() {
        validateWordLinkData()
        validateGameCharacterId()
        validateWordWhenGameCharacterForPlayerAsPrimaryCharacterCreate()
        validateCategory(GenericGenericLink.Categories.CharacterToWordLink.CHARACTER_TO_WORD.toString())
    }

    private fun validateWordLinkData() {
        if (model.rightModelId == null) {
            throw IllegalStateException()
        }
        if (model.rightModelType != Word::class.simpleName) {
            throw IllegalStateException()
        }
    }

    private fun validateWordWhenGameCharacterForPlayerAsPrimaryCharacterCreate() {
        val word = model.word
        if (word == null) {
            throw IllegalStateException()
        }
        WordValidator(word).let {
            it.whenGameCharacterForPlayerPrimaryCharacterCreateScenario()
            if (!it.validationManager.isValid()) {
                validationManager.markAsHasNestedErrors()
            }
        }
    }


    private fun validateGiftLinkData() {
        if (model.rightModelId == null) {
            throw IllegalStateException()
        }
        if (model.rightModelType != Gift::class.simpleName) {
            throw IllegalStateException()
        }
    }

    private fun validateGameCharacterId() {
        if (model.rightModelType != GameCharacter::class.simpleName) {
            throw IllegalStateException()
        }
    }


    private fun validateGiftWhenGameCharacterForPLayerAsPrimaryCharacterCreated() {
        val gift = model.gift
        if (gift == null) {
            throw IllegalStateException()
        }
        GiftValidator(gift).let {
            it.whenGameCharacterForPlayerPrimaryCharacterCreateScenario()
            if (!it.validationManager.isValid()) {
                validationManager.markAsHasNestedErrors()
            }
        }
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

    fun userToGameCharacterAsPlayerPrimaryCharacterCreateScenario() {
        validateUserType()
        validateUserId()
        validateCategory(GenericGenericLink.Categories.UserToCharacterLink.PRIMARY_PLAYER_CHARACTER.toString())
    }


}