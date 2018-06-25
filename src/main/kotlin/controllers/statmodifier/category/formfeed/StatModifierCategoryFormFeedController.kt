package controllers.statmodifier.category.formfeed

import controllers.ApplicationControllerBase
import models.statmodifier.StatModifier
import models.statmodifier.tojsonserializers.category.forformfeed.StatModifierCategoryFormFeedIndexToJsonSerializer
import router.src.ServletRequestContext

class StatModifierCategoryFormFeedController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun index() {
        val categories = StatModifier.Categories.values().map { it.toString() }
                .mapTo(mutableListOf()) { StatModifier().also { statModifier -> statModifier.category = it } }

        renderJson(
                StatModifierCategoryFormFeedIndexToJsonSerializer.onSuccess(categories)
        )
    }

    fun indexAttackSubcategories() {
        val attackSubCategories = StatModifier.AttackSubCategories.values()
                .map { it.toString() }
                .mapTo(mutableListOf()) {
                    StatModifier().also { statModifier -> statModifier.subCategory = it }
                }

        renderJson(
                StatModifierCategoryFormFeedIndexToJsonSerializer.onSuccess(attackSubCategories)
        )
    }

    fun indexArmorSubcategories() {
        val armorSubCategories = StatModifier.ArmorSubCategories.values()
                .map { it.toString() }
                .mapTo(mutableListOf()) {
                    StatModifier().also { statModifier -> statModifier.subCategory = it }
                }
        renderJson(
                StatModifierCategoryFormFeedIndexToJsonSerializer.onSuccess(armorSubCategories)
        )
    }

    fun savingThrowPenaltySubCategories() {
        val savingThrowPenaltySubCategories = StatModifier.SavingThrowPenaltySubCategories.values()
                .map { it.toString() }
                .mapTo(mutableListOf()) {
                    StatModifier().also { statModifier -> statModifier.subCategory = it }
                }

        renderJson(
                StatModifierCategoryFormFeedIndexToJsonSerializer.onSuccess(savingThrowPenaltySubCategories)
        )
    }

}