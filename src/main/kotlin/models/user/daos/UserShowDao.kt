package models.user.daos

import org.jooq.generated.tables.Users
import orm.usergeneratedrepository.UserRecord
import models.user.User
import org.jooq.generated.Tables.ACCOUNTS
import org.jooq.generated.Tables.USER_ROLES
import org.jooq.generated.tables.Accounts

object UserShowDao {

    val table = Users.USERS

    fun isExists(userId: Long): Boolean {
        val user = UserRecord.GET()
            .where(table.ID.eq(userId))
            .limit(1)
            .execute()
            .firstOrNull()

        return user != null
    }

    fun existsWithSuchName(name: String): Boolean {
        val users = UserRecord.GET()
                .where(table.NAME.eq(name))
                .execute()

        return users.isNotEmpty()
    }
    fun byId(id: Long): User? {
        return UserRecord.GET()
                .where(table.ID.eq(id))
                .limit(1)
                .execute()
                .firstOrNull()
    }

    fun findUserWithNameOrEmail(name: String): User? {
        return UserRecord.GET()
                .join {
                    it.account()
                }
                .where(
                        table.NAME.eq(name).or(
                                    ACCOUNTS.EMAIL.eq(name)
                            )
                )
                .preload {
                    it.account()
                    it.userToUserRoleLinks() {
                        it.join {
                            it.userRole()
                        }.where(
                                USER_ROLES.IS_SPECIFIC.isNull.or(USER_ROLES.IS_SPECIFIC.isFalse)
                        )

                        it.preload {
                            it.userRole()
                        }
                    }
                }
                .limit(1)
                .execute()
                .firstOrNull()
    }

}