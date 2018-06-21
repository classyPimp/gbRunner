package models.gamecharacter.factories.forplayer

import models.gamecharacter.GameCharacter
import models.gamecharacter.GameCharacterRequestParametersWrapper
import models.genericgenericlink.GenericGenericLink
import models.genericgenericlink.factories.charactertogift.CharacterToGiftGenericGenericLinkFactory
import models.genericgenericlink.factories.gamecharactertoword.GameCharacterToWordGenericGenericLinkCreateFactory
import models.genericgenericlink.factories.usertocharacterlink.UserToCharacterAsPlayerGenericGenericLinkCreateFactory
import models.word.factories.WordCreateFactory

object GameCharacterForPlayerCreateFactory {
    fun create(params: GameCharacterRequestParametersWrapper, campaignId: Long, playerId: Long): GameCharacter {
        return GameCharacter().also {
            it.campaignId = campaignId
            it.strength = params.strength
            it.level = 1
            it.experiencePoints = 0
            it.dexterity = params.dexterity
            it.constitution = params.constitution
            it.wisdom = params.wisdom
            it.intelligence = params.intelligence
            it.charisma = params.charisma
            it.hardinessBase = 0
            it.evasionBase = 0
            it.hitpointsMaximum = 1
            it.hitpointsCurrent = 1
            it.effortTotal = 0
            it.effortAvailable = 0
            it.influenceTotal = 0
            it.influenceAvailable = 0
            it.dominionTotal = 0
            it.dominionAvailable = 0
            it.currentWealth = 0
            it.dominionEarnedPerMonth = 0
            it.category = GameCharacter.Categories.PLAYER_PRIMARY_CHARACTER.toString()
            it.name = params.name
            it.description = params.description
            it.effortTotal = 0
            it.effortAvailable = 0
            it.linksToUsers = UserToCharacterAsPlayerGenericGenericLinkCreateFactory.create(playerId = playerId)
            it.linksToGifts = params.linksToGifts?.mapTo(mutableListOf<GenericGenericLink>()) {
                CharacterToGiftGenericGenericLinkFactory.create(it)
            }
            it.linksToWords = params.linksToWords?.mapTo(mutableListOf()) {
                GameCharacterToWordGenericGenericLinkCreateFactory.create(it)
            }
        }
    }


}