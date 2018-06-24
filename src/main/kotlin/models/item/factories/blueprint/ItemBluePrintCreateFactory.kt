package models.item.factories.blueprint

import models.item.Item
import models.item.ItemRequestParametersWrapper
import models.statmodifier.factories.ofitemblueprint.StatModifierOfItemBluePrintCreateFactory

object ItemBluePrintCreateFactory {
    fun create(params: ItemRequestParametersWrapper): Item {
        return Item().also {
            it.name = params.name
            it.category = params.category
            it.statModifiers = params.statModifiers?.map {
                StatModifierOfItemBluePrintCreateFactory.create(it)
            }?.toMutableList()

        }
    }

}