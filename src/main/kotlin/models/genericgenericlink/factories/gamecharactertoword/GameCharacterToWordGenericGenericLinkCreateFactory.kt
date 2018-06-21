package models.genericgenericlink.factories.gamecharactertoword

import models.gamecharacter.GameCharacter
import models.genericgenericlink.GenericGenericLink
import models.genericgenericlink.GenericGenericLinkRequestParametersWrapper
import models.word.Word
import models.word.factories.WordCreateFactory

object GameCharacterToWordGenericGenericLinkCreateFactory {
    fun create(params: GenericGenericLinkRequestParametersWrapper): GenericGenericLink {
        return GenericGenericLink().also {
            it.rightModelId = params.word?.id
            it.rightModelType = Word::class.simpleName
            it.leftModelType = GameCharacter::class.simpleName
            it.word = params.word?.let {
                WordCreateFactory.create(it)
            }
        }
    }

}