package models.item.tojsonserializers.foradmin

import models.item.Item
import orm.itemgeneratedrepository.ItemToJsonSerializer

object ItemForAdminIndexToJsonSerializer {

    fun onSuccess(items: MutableList<Item>): String {
        return ItemToJsonSerializer.serialize(items) {
            it.includeStatModifiers()
        }.serializeToString()
    }

}