package models.item.tojsonserializers.foradmin

import models.item.Item
import orm.itemgeneratedrepository.ItemToJsonSerializer

object ItemForAdminCreateToJsonSerializer {

    fun onSuccess(item: Item): String {
        ItemToJsonSerializer(item).let {
            it.includeStatModifiers()
            return it.serializeToString()
        }
    }

    fun onError(item: Item): String {
        ItemToJsonSerializer(item). let {
            it.includeStatModifiers() {
                it.includeErrors()
            }
            it.includeErrors()
            return it.serializeToString()
        }
    }

}