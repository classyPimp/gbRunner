package models.gift.tojsonserializers

import models.gift.Gift
import orm.giftgeneratedrepository.GiftToJsonSerializer

object GiftUpdateToJsonSerializer {

    fun onSuccess(gift: Gift): String {
        GiftToJsonSerializer(gift).let {

            return it.serializeToString()
        }
    }

    fun onError(gift: Gift): String {
        GiftToJsonSerializer(gift). let {


            it.includeErrors()
            return it.serializeToString()
        }
    }

}