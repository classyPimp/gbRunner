package models.statmodifier

import orm.statmodifiergeneratedrepository.StatModifierValidatorTrait

class StatModifierValidator(model: StatModifier) : StatModifierValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

    fun whenItemForAdminCreateScenario() {
        validateCategory()
        validateSubCategory()
        ensureIsBluePrint()
    }

    private fun validateCategory() {
        val category = StatModifier.Categories.valueOf(model.category!!)
    }

    private fun validateSubCategory() {
        val category = StatModifier.Categories.valueOf(model.category!!)
        val subCategory = model.subCategory!!
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
    }

    private fun ensureIsBluePrint() {
        val isBlueprint = model.isBlueprint
        if (isBlueprint == null) {
            throw IllegalStateException()
        }
    }

}