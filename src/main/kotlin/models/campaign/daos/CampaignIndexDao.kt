package models.campaign.daos

import org.jooq.generated.tables.Campaigns
import orm.campaigngeneratedrepository.CampaignRecord
import models.campaign.Campaign
import models.genericgenericlink.GenericGenericLink
import models.user.User
import org.apache.commons.lang3.mutable.Mutable
import org.jooq.generated.Tables.GENERIC_GENERIC_LINKS
import org.jooq.generated.tables.GenericGenericLinks

object CampaignIndexDao {

    fun whereGameMasterIs(gameMasterUserId: Long): MutableList<Campaign> {
        return CampaignRecord.GET()
                .join {
                    it.linksToUsers()
                }
                .where(
                        GENERIC_GENERIC_LINKS.CATEGORY.eq(GenericGenericLink.Categories.UserToCampaignLink.GAME_MASTER.toString())
                                .and(GENERIC_GENERIC_LINKS.RIGHT_MODEL_ID.eq(gameMasterUserId))
                )
                .execute()
    }

    fun wherePlayerIs(currentUserId: Long): MutableList<Campaign> {
        return CampaignRecord.GET()
                .join {
                    it.linksToUsers()
                }
                .where(
                        GENERIC_GENERIC_LINKS.CATEGORY.eq(GenericGenericLink.Categories.UserToCampaignLink.PLAYER.toString())
                                .and(
                                        GENERIC_GENERIC_LINKS.RIGHT_MODEL_ID.eq(currentUserId)
                                ).and(
                                        GENERIC_GENERIC_LINKS.RIGHT_MODEL_TYPE.eq(User::class.simpleName)
                                )
                )
                .execute()
    }
}