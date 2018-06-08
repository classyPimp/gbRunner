package models.campaign.daos

import org.jooq.generated.tables.Campaigns
import models.campaign.daos.CampaignShowDao
import models.campaign.daos.CampaignIndexDao
import models.campaign.daos.CampaignEditDao
import models.campaign.daos.CampaignUpdateDao
import models.campaign.daos.CampaignDestroyDao

object CampaignDaos {

    val show = CampaignShowDao

    val index = CampaignIndexDao

    val edit = CampaignEditDao

    val update = CampaignUpdateDao

    val destroy = CampaignDestroyDao

}