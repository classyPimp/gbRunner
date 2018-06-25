package models.item.tojsonserializers.category.forformfeed

import models.item.Item
import orm.itemgeneratedrepository.ItemToJsonSerializer

object ItemCategoryForFormFeedIndexToJsonSerializer {

    fun onSuccess(items: MutableList<Item>): String {
        return ItemToJsonSerializer.serialize(items) {
            it.only { arrayOf(it.category, it.subcategory) }
        }.serializeToString()
    }

}