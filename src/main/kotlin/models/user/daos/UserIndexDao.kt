package models.user.daos

import models.campaign.Campaign
import org.jooq.generated.tables.Users
import orm.usergeneratedrepository.UserRecord
import models.user.User
import org.apache.commons.lang3.mutable.Mutable
import org.jooq.generated.Tables.GENERIC_GENERIC_LINKS
import org.jooq.generated.Tables.USERS
import org.jooq.impl.DSL

object UserIndexDao {

    val table = USERS

    fun byQuery(query: String?): MutableList<User> {
        if (query == null) {
            return UserRecord.GET()
                    .execute()

        }
        return UserRecord.GET()
                .where(
                        DSL.trueCondition()
                                .and("{0} ~* {1}", table.NAME, DSL.`val`(query))
                )
                .execute()
    }

    fun forManagementIndex(): MutableList<User> {
        return UserRecord.GET()
                .preload {
                    it.userToUserRoleLinks() {
                        it.preload {
                            it.userRole()
                        }
                    }
                    it.account()
                }
                .execute()
    }

    fun forIndex(): MutableList<User> {
        return UserRecord.GET()
                .execute()
    }

    fun asPlayerOfCampaign(campaignId: Long): MutableList<User> {
        return UserRecord.GET()
                .join {
                    it.linksToCampaigns()
                }
                .where(
                        GENERIC_GENERIC_LINKS.LEFT_MODEL_ID.eq(campaignId)
                                .and(
                                        GENERIC_GENERIC_LINKS.LEFT_MODEL_TYPE.eq(Campaign::class.simpleName)
                                )
                )
                .execute()
    }

}