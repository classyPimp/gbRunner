package models.gift.factories

import models.gift.Gift
import models.gift.GiftRequestParametersWrapper
import utils.requestparameters.IParam

object GiftCreateFactory {
    fun create(params: GiftRequestParametersWrapper): Gift {
        return Gift().also {
            it.name = params.name
            it.description = params.description
        }
    }

}