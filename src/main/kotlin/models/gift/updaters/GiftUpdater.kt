package models.gift.updaters

import models.gift.Gift
import models.gift.GiftRequestParametersWrapper


object GiftUpdater {

    fun update(model: Gift, params: GiftRequestParametersWrapper) {
        model.record.also {
            it.name = params.name
            it.description = params.description
        }
    }

}