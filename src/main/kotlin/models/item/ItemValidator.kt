package models.item

import com.sun.javaws.exceptions.InvalidArgumentException
import models.item.daos.ItemDaos
import models.statmodifier.StatModifierValidator
import orm.itemgeneratedrepository.ItemValidatorTrait

class ItemValidator(model: Item) : ItemValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

    fun whenAdminAddScenario(gameCharacterId: Long) {
        validateThatItemDoesNotBelongToAnyoneAlready()
        validateThatItemIsNotAlreadyBelongsToGameCharacter(gameCharacterId)
    }

    fun forAdminCreateScenario(itemBlueprint: Item) {
        validateName()
        validateNameUniqueness()
        validateThatItemDoesNotBelongToAnyoneAlready()
        validateBlueprintId()
        validateCategory()
        validateSubCategory()
        validateStatModifiersWhenItemForAdminCreate()
    }

    @Deprecated("was going to use but cancelled, leaving be")
    private fun validateIsClonedProperly(itemBlueprint: Item) {
        if (
                model.bluePrintId != itemBlueprint.id
            || model.category != itemBlueprint.category
            || model.isAbility != itemBlueprint.isAbility
            || model.subcategory != itemBlueprint.subcategory
            || model.ownerId != null
        ) {
            throw IllegalStateException()
        }

        val statModidifiers = model.statModifiers
        val clonedFromStatModifiers = itemBlueprint.statModifiers

        if (statModidifiers == null) {
            if (clonedFromStatModifiers != null) {
                throw IllegalStateException()
            } else {
                return
            }
        } else {
            if (clonedFromStatModifiers == null) {
                throw IllegalStateException()
            } else if (statModidifiers.size != clonedFromStatModifiers.size) {
                 throw IllegalStateException()
            }
        }


        var index = 0
        while (index < statModidifiers!!.size) {
            val clonedOne = statModidifiers[index]
            val clonedFrom = clonedFromStatModifiers[index]
            if (clonedOne.category != clonedFrom.category
                || clonedOne.subCategory != clonedFrom.subCategory
                || clonedOne.value != clonedFrom.value
                || clonedOne.nonStandardValue != clonedFrom.nonStandardValue
            ) {
                throw IllegalStateException()
            }
        }
    }

    private fun validateName() {
        val name = model.name
        if (name == null || name.isBlank()) {
            validationManager.addNameError("should be set")
        }
    }

    private fun validateNameUniqueness() {
        val name = model.name
        if (name == null || name.isBlank()) {
            return
        }

        if (ItemDaos.show.alreadyExistsWith(name = name)) {
            validationManager.addNameError("item with such name already exists")
        }

    }

    private fun validateThatItemDoesNotBelongToAnyoneAlready() {
        val ownerId = model.ownerId
        if (ownerId != null) {
            validationManager.addGeneralError("item has owner already")
        }
    }

    private fun validateBlueprintId() {
        val blueprintId = model.bluePrintId
        if (blueprintId == null) {
            throw IllegalStateException()
        }
    }

    private fun validateThatItemIsNotAlreadyBelongsToGameCharacter(gameCharacterId: Long) {
        val ownerId = model.id
        if (ownerId == gameCharacterId) {
            validationManager.addGeneralError("item already belongs to this character")
        }
    }

    private fun validateCategory() {
        val category = model.category
        if (category == null) {
            validationManager.addCategoryError("should be set")
            return
        }
        try {
            Item.Categories.valueOf(category)
        } catch (error: IllegalArgumentException) {
            throw IllegalStateException("no such category")
        }
    }

    private fun validateSubCategory() {
        val subCategory = model.subcategory
        if (subCategory == null) {
            validationManager.addSubcategoryError("should be set")
            return
        }
        val category = Item.Categories.valueOf(model.category!!)

        when (category) {
            Item.Categories.WEAPON -> {
                Item.WeaponSubCategories.valueOf(subCategory)
            }
            Item.Categories.ARMOR -> {
                Item.ArmorSubCategories.valueOf(subCategory)
            }
            Item.Categories.ABILITY -> {
                Item.AbilitySubCategories.valueOf(subCategory)
            }
        }
    }

    private fun validateStatModifiersWhenItemForAdminCreate() {
        var category: Item.Categories? = null
        if (!model.category.isNullOrBlank()) {
            try {
                category = Item.Categories.valueOf(model.category!!)
            } catch (error: InvalidArgumentException) {

            }
        }

        model.statModifiers?.forEach {
            StatModifierValidator(it).also {
                it.whenItemForAdminCreateScenario(itemCategory = category)
                if (!it.validationManager.isValid()) {
                    validationManager.markAsHasNestedErrors()
                }
            }
        }

    }


}