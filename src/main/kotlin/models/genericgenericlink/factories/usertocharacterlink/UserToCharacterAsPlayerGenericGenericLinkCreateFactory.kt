package models.genericgenericlink.factories.usertocharacterlink

import models.gamecharacter.GameCharacter
import models.genericgenericlink.GenericGenericLink
import models.user.User

object UserToCharacterAsPlayerGenericGenericLinkCreateFactory {
    fun create(playerId: Long): MutableList<GenericGenericLink> {
        val playerToCharacterAsPrimaryPlayerCharacter = GenericGenericLink().also {
            it.rightModelId = playerId
            it.rightModelType = User::class.simpleName
            it.category = GenericGenericLink.Categories.UserToCharacterLink.PRIMARY_PLAYER_CHARACTER.toString()
            it.leftModelType = GameCharacter::class.simpleName
        }
        return mutableListOf(playerToCharacterAsPrimaryPlayerCharacter)
    }

}