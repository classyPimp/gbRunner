package models.gamecharacter

import models.genericgenericlink.GenericGenericLinkValidator
import models.gift.Gift
import models.gift.GiftRules
import models.word.WordRules
import orm.gamecharactergeneratedrepository.GameCharacterValidatorTrait
import kotlin.math.absoluteValue

class GameCharacterValidator(model: GameCharacter) : GameCharacterValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

    fun createForPlayerAsPrimaryPlayerCharacterScenario() {
        validateGenericGenericLink()
        validateLinksToGifts()
        validateThatThereAreNoDuplicateGifts()
        validateLinksToWords()
        validateThatThereAreNoDuplicateWords()
        validateAmountOfWords()
        validatePointsSpentOnGifts()
        validateName()
        validateStrengthOnCreate()
        validateDexterityOnCreate()
        validateConstitutionOnCreate()
        validateWisdomOnCreate()
        validateIntelligenceOnCreate()
        validateCharismaOnCreate()
    }

    private fun validateGenericGenericLink() {
        val userToCharacterLink = model.linksToUsers!!.first()
        GenericGenericLinkValidator(userToCharacterLink).let {
            it.userToGameCharacterAsPlayerPrimaryCharacterCreateScenario()
            if (!it.validationManager.isValid()) {
                throw IllegalStateException()
            }
        }
    }

    private fun validateLinksToGifts() {
        val links = model.linksToGifts
        if (links == null) {
            return
        }
        links.forEach { link ->
            GenericGenericLinkValidator(link).let {
                it.whenPrimaryCharacterForPlayerCreateScenarioAslinkToGift()
                if (!it.validationManager.isValid()) {
                    validationManager.markAsHasNestedErrors()
                }
            }
        }
    }

    private fun validateThatThereAreNoDuplicateGifts() {
        val links = model.linksToGifts
        if (links == null) {
            return
        }
        val duplicates = links.map {it.gift!!.name!!}.groupingBy { it }.eachCount().filter { it.value > 1 }
        if (duplicates.keys.isNotEmpty()) {
            validationManager.addGeneralError("duplicate gifts selected: ${duplicates.keys.joinToString { "${it}, " }}")
        }
    }

    private fun validateLinksToWords() {
        val links = model.linksToWords
        if (links == null) {
            return
        }
        links.forEach { link ->
            GenericGenericLinkValidator(link).let {
                it.whenPrimaryCharacterForPlayerCreateScenarioAslinkToWord()
                if (!it.validationManager.isValid()) {
                    validationManager.markAsHasNestedErrors()
                }
            }
        }
    }

    private fun validateThatThereAreNoDuplicateWords() {
        val words = model.linksToWords
        if (words == null) {
            return
        }
        val duplicates = words.map {it.word!!.name!!}.groupingBy { it }.eachCount().filter { it.value > 1 }
        if (duplicates.keys.isNotEmpty()) {
            validationManager.addGeneralError("duplicate words selected: ${duplicates.keys.joinToString { "${it}, " }}")
        }
    }

    private fun validateAmountOfWords() {
        val links = model.linksToWords
        val maxWordsSize = WordRules.availableWordsNumberAtCharacterCreation
        if (links == null) {
            validationManager.addGeneralError("you should add exactly ${maxWordsSize} words")
            return
        }
        if (maxWordsSize != links.size) {
            if (maxWordsSize < links.size) {
                validationManager.addGeneralError("you can add only ${links.size - maxWordsSize} words")
            }
            if (maxWordsSize > links.size) {
                validationManager.addGeneralError("you should add ${maxWordsSize - links.size} more words")
            }
        }

    }

    private fun validatePointsSpentOnGifts() {
        var maxPoints = GiftRules.availableGiftPointsAtCharacterCreation
        val links =model.linksToGifts
        if (links == null) {
            validationManager.addGeneralError("you must spend ${maxPoints} on gifts")
            return
        }
        links.forEach {
            it.gift?.let {
                if (it.category == Gift.Categories.GREATER_GIFT.toString()) {
                    maxPoints -= GiftRules.costOfGreaterGift
                } else if (it.category == Gift.Categories.LESSER_GIFT.toString()) {
                    maxPoints -= GiftRules.costOfLesserGift
                } else {
                    throw IllegalStateException()
                }
            }
        }
        if (maxPoints != 0) {
            if (maxPoints > 0) {
                validationManager.addGeneralError("you must spend ${maxPoints} more points on gifts")
            }
            if (maxPoints < 0) {
                validationManager.addGeneralError("remove gifts on ${maxPoints.absoluteValue} points")
            }
        }
    }

    private fun validateName() {
        val name = model.name
        if (name == null || name.isBlank()) {
            validationManager.addNameError("should be provided")
            return
        }
    }

    private fun validateStrengthOnCreate() {
        val strength = model.strength
        if (strength == null) {
            validationManager.addStrengthError("should be set")
            return
        }
        if (strength > GameCharacterRules.MAX_ATTRIBUTE_VALUE_AT_CHARACTER_CREATION) {
            validationManager.addStrengthError("max value ${GameCharacterRules.MAX_ATTRIBUTE_VALUE_AT_CHARACTER_CREATION}")
        }
    }

    private fun validateDexterityOnCreate() {
        val dexterity = model.dexterity
        if (dexterity == null) {
            validationManager.addDexterityError("should be set")
            return
        }
        if (dexterity > GameCharacterRules.MAX_ATTRIBUTE_VALUE_AT_CHARACTER_CREATION) {
            validationManager.addStrengthError("max value ${GameCharacterRules.MAX_ATTRIBUTE_VALUE_AT_CHARACTER_CREATION}")
        }
    }

    private fun validateConstitutionOnCreate() {
        val Constitution = model.constitution
        if (Constitution == null) {
            validationManager.addConstitutionError("should be set")
            return
        }
        if (Constitution > GameCharacterRules.MAX_ATTRIBUTE_VALUE_AT_CHARACTER_CREATION) {
            validationManager.addStrengthError("max value ${GameCharacterRules.MAX_ATTRIBUTE_VALUE_AT_CHARACTER_CREATION}")
        }
    }

    private fun validateWisdomOnCreate() {
        val wisdom = model.wisdom
        if (wisdom == null) {
            validationManager.addWisdomError("should be set")
            return
        }
        if (wisdom > GameCharacterRules.MAX_ATTRIBUTE_VALUE_AT_CHARACTER_CREATION) {
            validationManager.addStrengthError("max value ${GameCharacterRules.MAX_ATTRIBUTE_VALUE_AT_CHARACTER_CREATION}")
        }
    }

    private fun validateIntelligenceOnCreate() {
        val intelligence = model.intelligence
        if (intelligence == null) {
            validationManager.addIntelligenceError("should be set")
            return
        }
        if (intelligence > GameCharacterRules.MAX_ATTRIBUTE_VALUE_AT_CHARACTER_CREATION) {
            validationManager.addStrengthError("max value ${GameCharacterRules.MAX_ATTRIBUTE_VALUE_AT_CHARACTER_CREATION}")
        }
    }

    private fun validateCharismaOnCreate() {
        val charisma = model.charisma
        if (charisma == null) {
            validationManager.addCharismaError("should be set")
            return
        }
        if (charisma > GameCharacterRules.MAX_ATTRIBUTE_VALUE_AT_CHARACTER_CREATION) {
            validationManager.addStrengthError("max value ${GameCharacterRules.MAX_ATTRIBUTE_VALUE_AT_CHARACTER_CREATION}")
        }
    }



}