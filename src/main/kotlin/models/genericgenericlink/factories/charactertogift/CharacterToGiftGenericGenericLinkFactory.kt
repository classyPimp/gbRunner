package models.genericgenericlink.factories.charactertogift

import models.gamecharacter.GameCharacter
import models.genericgenericlink.GenericGenericLink
import models.genericgenericlink.GenericGenericLinkRequestParametersWrapper
import models.gift.Gift
import models.gift.factories.GiftCreateFactory

object CharacterToGiftGenericGenericLinkFactory {

    fun create(params: GenericGenericLinkRequestParametersWrapper): GenericGenericLink {
        return GenericGenericLink().also {
            it.rightModelType = Gift::class.simpleName
            it.rightModelId = params.gift?.id
            it.leftModelType = GameCharacter::class.simpleName
            it.gift = params.gift?.let {
                GiftCreateFactory.create(it)
            }
        }
    }

}