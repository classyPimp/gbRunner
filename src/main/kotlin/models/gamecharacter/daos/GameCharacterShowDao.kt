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


}