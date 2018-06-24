package models.statmodifier.factories.ofitemblueprint

import models.statmodifier.StatModifier
import models.statmodifier.StatModifierRequestParametersWrapper

object StatModifierOfItemBluePrintCreateFactory {
    fun create(params: StatModifierRequestParametersWrapper): StatModifier {
        return StatModifier().also {
            it.category = params.category
            it.isBlueprint = true
            it.nonStandardValue = params.nonStandartValue
            it.value = params.value
            it.subCategory = params.subCategory
        }
    }

}