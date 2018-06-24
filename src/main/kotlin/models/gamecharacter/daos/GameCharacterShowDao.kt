package models.gamecharacter.daos

import models.gamecharacter.GameCharacter
import models.genericgenericlink.GenericGenericLink
import org.jooq.generated.Tables.GAME_CHARACTERS
import org.jooq.generated.Tables.GENERIC_GENERIC_LINKS
import orm.gamecharactergeneratedrepository.GameCharacterRecord

object GameCharacterShowDao {

    val table = GAME_CHARACTERS

    fun findPrimaryPlayerChar(campaignId: Long, playerId: Long): GameCharacter? {
        return GameCharacterRecord.GET()
                .join {
                    it.linksToUsers()
                }
                .preload {
                    it.linksToGifts() {
                        it.where(GENERIC_GENERIC_LINKS.CATEGORY.eq(GenericGenericLink.Categories.CharacterToGiftLink.CHARACTER_TO_GIFT.toString()))
                        it.preload {
                            it.gift()
                        }
                    }
                    it.linksToWords() {
                        it.where(GENERIC_GENERIC_LINKS.CATEGORY.eq(GenericGenericLink.Categories.CharacterToWordLink.CHARACTER_TO_WORD.toString()))
                        it.preload {
                            it.word()
                        }
                    }
                }
                .where(
                        GENERIC_GENERIC_LINKS.CATEGORY.eq(GenericGenericLink.Categories.UserToCharacterLink.PRIMARY_PLAYER_CHARACTER.toString())
                                .and(
                                        GENERIC_GENERIC_LINKS.RIGHT_MODEL_ID.eq(playerId)
                                ).and(
                                        table.CAMPAIGN_ID.eq(campaignId)
                                )
                )
                .executeGetFirstOrNull()
    }

    fun finByIdNotPreloadingAnything(gameCharacterId: Long): GameCharacter? {
        return GameCharacterRecord.GET()
                .where(GAME_CHARACTERS.ID.eq(gameCharacterId))
                .executeGetFirstOrNull()
    }


}