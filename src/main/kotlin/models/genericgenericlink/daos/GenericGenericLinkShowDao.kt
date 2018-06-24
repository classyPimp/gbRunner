package models.genericgenericlink.daos

import models.campaign.Campaign
import models.gamecharacter.GameCharacter
import org.jooq.generated.tables.GenericGenericLinks
import orm.genericgenericlinkgeneratedrepository.GenericGenericLinkRecord
import models.genericgenericlink.GenericGenericLink
import models.user.User
import org.jooq.generated.Tables.GENERIC_GENERIC_LINKS

object GenericGenericLinkShowDao {

    val table = GENERIC_GENERIC_LINKS

    fun ofCampaignToUserLinkByCampaignAndUserExists(campaignId: Long, userId: Long): Boolean {
        val link = GenericGenericLinkRecord.GET()
                .where(
                        table.LEFT_MODEL_ID.eq(campaignId)
                                .and(
                                        table.LEFT_MODEL_TYPE.eq(Campaign::class.simpleName)
                                ).and(
                                        table.RIGHT_MODEL_ID.eq(userId)
                                ).and(
                                        table.RIGHT_MODEL_TYPE.eq(User::class.simpleName)
                                ).and(
                                        table.CATEGORY.eq(GenericGenericLink.Categories.UserToCampaignLink.GAME_MASTER.toString())
                                )
                )
                .limit(1)
                .execute()
                .firstOrNull()

        return (link != null)
    }

    fun userToCampaignLinkAsPlayerExistsFor(userId: Long, campaignId: Long): Boolean {
        val link = GenericGenericLinkRecord.GET()
                .where(table.RIGHT_MODEL_ID.eq(userId)
                        .and(
                                table.RIGHT_MODEL_TYPE.eq(User::class.simpleName)
                        ).and(
                                table.LEFT_MODEL_TYPE.eq(Campaign::class.simpleName)
                        ).and(
                                table.LEFT_MODEL_ID.eq(campaignId)
                        ).and(
                                table.CATEGORY.eq(GenericGenericLink.Categories.UserToCampaignLink.PLAYER.toString())
                        )
                ).executeGetFirstOrNull()

        return link != null
    }

    fun userToGameCharacterAsPrimaryCharacterExistsFor(userId: Long, gameCharacterId: Long): Boolean {
        val link = GenericGenericLinkRecord.GET()
                .where(
                        table.RIGHT_MODEL_ID.eq(userId)
                                .and(
                                        table.CATEGORY.eq(GenericGenericLink.Categories.UserToCharacterLink.PRIMARY_PLAYER_CHARACTER.toString())
                                )
                )
                .executeGetFirstOrNull()

        return link != null
    }

}