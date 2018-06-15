package models.campaign.daos

import org.jooq.generated.tables.Campaigns
import orm.campaigngeneratedrepository.CampaignRecord
import models.campaign.Campaign
import models.genericgenericlink.GenericGenericLink
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
                )
                .execute()
    }
}