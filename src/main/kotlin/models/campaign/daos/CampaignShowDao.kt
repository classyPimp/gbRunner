package models.campaign.daos

import org.jooq.generated.tables.Campaigns
import orm.campaigngeneratedrepository.CampaignRecord
import models.campaign.Campaign
import models.genericgenericlink.GenericGenericLink
import models.user.User
import org.jooq.generated.Tables.GENERIC_GENERIC_LINKS

object CampaignShowDao {
    val table = Campaigns.CAMPAIGNS

    fun forGameMasterShow(campaignId: Long, gameMasterId: Long): Campaign? {
        return CampaignRecord.GET()
                .join {
                    it.linksToUsers()
                }
                .preload {
                    it.linksToUsers() {
                        it.preload {
                            it.user {
                                it.preload {
                                    it.account()
                                }
                            }
                        }
                    }
                }
                .where(
                        table.ID.eq(campaignId).and(
                                GENERIC_GENERIC_LINKS.RIGHT_MODEL_ID.eq(gameMasterId)
                                        .and(GENERIC_GENERIC_LINKS.RIGHT_MODEL_TYPE.eq(User::class.simpleName))
                                        .and(GENERIC_GENERIC_LINKS.CATEGORY.eq(GenericGenericLink.Categories.UserToCampaignLink.GAME_MASTER.toString()))
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

    fun forPlayerShow(campaignId: Long, userAsPlayerId: Long): Campaign? {
        return CampaignRecord.GET()
                .join {
                    it.linksToUsers()
                }
                .preload {
                    it.linksToUsers() {
                        it.preload {
                            it.user {
                                it.preload {
                                    it.account()
                                }
                            }
                        }
                    }
                }
                .where(
                        table.ID.eq(campaignId).and(
                                GENERIC_GENERIC_LINKS.RIGHT_MODEL_ID.eq(userAsPlayerId)
                        ).and(
                                GENERIC_GENERIC_LINKS.RIGHT_MODEL_TYPE.eq(User::class.simpleName)
                        ).and(
                                GENERIC_GENERIC_LINKS.CATEGORY.eq(GenericGenericLink.Categories.UserToCampaignLink.PLAYER.toString())
                        )
                )
                .limit(1)
                .execute()
                .firstOrNull()
    }


}