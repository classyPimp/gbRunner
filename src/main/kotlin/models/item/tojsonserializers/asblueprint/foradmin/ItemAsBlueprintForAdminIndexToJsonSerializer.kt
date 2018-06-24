package models.item.tojsonserializers.asblueprint.foradmin

import models.item.Item
import orm.itemgeneratedrepository.ItemToJsonSerializer

object ItemAsBlueprintForAdminIndexToJsonSerializer {

    fun onSuccess(items: MutableList<Item>): String {
        return ItemToJsonSerializer.serialize(items) {
            it.includeStatModifiers()
        }.serializeToString()
    }


}