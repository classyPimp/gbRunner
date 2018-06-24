package models.item.tojsonserializers.item.inventory.forplayer

import models.item.Item
import orm.itemgeneratedrepository.ItemToJsonSerializer

object ItemAsInventoryForPlayerIndexToJsonSerializer {

    fun onSuccess(items: MutableList<Item>): String {
        return ItemToJsonSerializer.serialize(items) {
            it.includeStatModifiers()
        }.serializeToString()
    }

}