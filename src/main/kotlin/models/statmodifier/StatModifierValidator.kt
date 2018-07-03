package models.statmodifier

import models.item.Item
import orm.statmodifiergeneratedrepository.StatModifierValidatorTrait

class StatModifierValidator(model: StatModifier) : StatModifierValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

    fun whenItemForAdminCreateScenario(itemCategory: Item.Categories?) {
        validateCategory()
        validateSubCategory()
        ensureIsBluePrint()
        validateThatCategoryIsAppropriateForItemCategory(itemCategory)
    }

    private fun validateCategory() {
        model.category.let {
            if (it == null || it.isBlank()) {
                validationManager.addCategoryError("should be set")
                return
            }
            try {
                StatModifier.Categories.valueOf(it)
            } catch (error: IllegalArgumentException) {
                validationManager.addCategoryError("no such category exists")
            }
        }
    }

    private fun validateSubCategory() {
        if (model.category.isNullOrBlank()) {
            return
        }
        val category: StatModifier.Categories
        try {
             category = StatModifier.Categories.valueOf(model.category!!)
        } catch (error: IllegalArgumentException) {
             return
        }

        val subCategory = model.subCategory

        if (subCategory == null || subCategory.isBlank()) {
            validationManager.addSubCategoryError("should be set")
            return
        }
        try {
            when (category) {
                StatModifier.Categories.ATTACK -> {
                    StatModifier.AttackSubCategories.valueOf(subCategory)
                }
                StatModifier.Categories.ARMOR -> {
                    StatModifier.AttackSubCategories.valueOf(subCategory)
                }
                StatModifier.Categories.SAVING_THROW_PENALTY -> {
                    StatModifier.SavingThrowPenaltySubCategories.valueOf(subCategory)
                }
            }
        } catch (error: IllegalArgumentException) {
            validationManager.addSubCategoryError("no such sub category exists")
        }
    }

    private fun ensureIsBluePrint() {
        val isBlueprint = model.isBlueprint ?: throw IllegalStateException()
    }

    private fun validateThatCategoryIsAppropriateForItemCategory(itemCategory: Item.Categories?) {
        if (model.category.isNullOrBlank()
                || itemCategory == null
        ) {
            return
        }
        val category: StatModifier.Categories
        try {
            category = StatModifier.Categories.valueOf(model.category!!)
        } catch (error: IllegalArgumentException) {
            return
        }

        when (itemCategory) {
            Item.Categories.WEAPON -> {
                if (category != StatModifier.Categories.ATTACK) {
                    validationManager.addCategoryError("invalid category for weapon item")
                    return
                }
            }
            Item.Categories.ARMOR -> {
                if (category != StatModifier.Categories.ARMOR || category != StatModifier.Categories.SAVING_THROW_PENALTY) {
                    validationManager.addCategoryError("invalid category for armor")
                }
            }
            else -> {

            }
        }

    }

}