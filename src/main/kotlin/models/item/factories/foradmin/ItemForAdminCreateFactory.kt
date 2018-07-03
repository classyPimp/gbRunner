package models.item.factories.foradmin

import models.item.Item
import models.item.ItemRequestParametersWrapper
import models.statmodifier.factories.StatModifierWhenItemForAdminCreateFactory

object ItemForAdminCreateFactory {
    fun create(params: ItemRequestParametersWrapper, itemBlueprint: Item): Item {
        return Item().also {
            it.blueprintId = itemBlueprint.id
            it.isBlueprint = false
            it.category = itemBlueprint.category
            it.subcategory = itemBlueprint.subcategory
            it.name = params.name
            it.description = params.description
            it.isAbility = itemBlueprint.isAbility
            it.statModifiers = params.statModifiers?.map {
                StatModifierWhenItemForAdminCreateFactory.create(it)
            }?.toMutableList()
        }
    }


}