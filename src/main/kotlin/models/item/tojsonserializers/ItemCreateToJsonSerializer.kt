package models.item.tojsonserializers

import models.item.Item
import orm.itemgeneratedrepository.ItemToJsonSerializer

object ItemCreateToJsonSerializer {

    fun onSuccess(item: Item): String {
        ItemToJsonSerializer(item).let {

            return it.serializeToString()
        }
    }

    fun onError(item: Item): String {
        ItemToJsonSerializer(item). let {


            it.includeErrors()
            return it.serializeToString()
        }
    }

}