package models.gift.tojsonserializers

import models.gift.Gift
import orm.giftgeneratedrepository.GiftToJsonSerializer

object GiftOfWordIndexToJsonSerializer {

    fun onSuccess(gifts: MutableList<Gift>): String {
        return GiftToJsonSerializer.serialize(gifts) {

        }.serializeToString()
    }


}