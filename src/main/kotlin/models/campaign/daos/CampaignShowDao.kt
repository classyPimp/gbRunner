package models.campaign.daos

import org.jooq.generated.tables.Campaigns
import orm.campaigngeneratedrepository.CampaignRecord
import models.campaign.Campaign
import org.jooq.generated.Tables.GENERIC_GENERIC_LINKS

object CampaignShowDao {
    val table = Campaigns.CAMPAIGNS

    fun forGameMasterShow(campaignId: Long, gameMasterId: Long): Campaign? {
        return CampaignRecord.GET()
                .join {
                    it.linksToUsers()
                }
                .where(
                        table.ID.eq(campaignId).and(
                                GENERIC_GENERIC_LINKS.RIGHT_MODEL_ID.eq(gameMasterId)
                        )
                )
                .limit(1)
                .execute()
                .firstOrNull()
    }

    fun existsWithId(campaignId: Long): Boolean {
        val campaign = CampaignRecord.GET()
                .where(table.ID.eq(campaignId))
                .limit(1)
                .execute()
                .firstOrNull()

        return (campaign != null)
    }


}