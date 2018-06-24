package models.statmodifier.factories

import models.statmodifier.StatModifier
import models.statmodifier.StatModifierRequestParametersWrapper

object StatModifierWhenItemForAdminCreateFactory {
    @Deprecated("planned to use but then cancelled, just leaving be")
    fun cloneFrom(statModifiersToClone: MutableList<StatModifier>): MutableList<StatModifier> {
        return statModifiersToClone.map {toCloneModifier ->
            StatModifier().also {
                it.category = toCloneModifier.category
                it.subCategory = toCloneModifier.subCategory
                it.value = toCloneModifier.value
                it.isBlueprint = true
                it.nonStandardValue = toCloneModifier.nonStandardValue
            }
        }.toMutableList()
    }

    fun create(params: StatModifierRequestParametersWrapper): StatModifier {
        return StatModifier().also {
            it.category = params.category
            it.subCategory = params.subCategory
            it.value = params.value
            it.isBlueprint = true
            it.nonStandardValue = params.nonStandartValue
        }
    }

}