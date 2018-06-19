package models.campaign.daos

import org.jooq.generated.tables.Campaigns
import orm.campaigngeneratedrepository.CampaignRecord
import models.campaign.Campaign
import org.jooq.generated.Tables.CAMPAIGNS

object CampaignUpdateDao {

    val table = CAMPAIGNS

    fun forGameMasterUpdate(campaignId: Long): Campaign? {
        return CampaignRecord.GET()
                .where(
                        table.ID.eq(campaignId)
                )
                .limit(1)
                .execute()
                .firstOrNull()
    }


}