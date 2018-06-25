package models.statmodifier.tojsonserializers.category.forformfeed

import models.statmodifier.StatModifier
import orm.statmodifiergeneratedrepository.StatModifierToJsonSerializer

object StatModifierCategoryFormFeedIndexToJsonSerializer {

    fun onSuccess(statModifiers: MutableList<StatModifier>): String {
        return StatModifierToJsonSerializer.serialize(statModifiers) {
            it.only { arrayOf(it.category, it.subCategory) }
        }.serializeToString()
    }

}