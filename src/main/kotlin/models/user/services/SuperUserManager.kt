package models.user.services

import models.account.Account
import models.user.User
import models.userrole.PredefinedUserRoleManager
import models.userrole.UserRole
import models.usertouserrolelink.UserToUserRoleLink
import org.jooq.generated.Tables.USERS
import org.jooq.generated.Tables.USER_ROLES
import orm.usergeneratedrepository.UserRecord
import orm.userrolegeneratedrepository.UserRoleRecord
import orm.utils.TransactionRunner
import utils.security.PasswordHashingService


/**
 * Created by Муса on 08.06.2018.
 */
object SuperUserManager {

    private val superUserRole: UserRole? by lazy {
        UserRoleRecord.GET()
                .where(USER_ROLES.NAME.eq(PredefinedUserRoleManager.PredefinedRoleNames.SUPER_USER.toString()))
                .limit(1)
                .execute()
                .firstOrNull().also {
                    if (it == null) {
                        throw IllegalStateException("UserRole SUPER_USER is not persisted in database")
                    }
                }
    }

    private val superUserInstance = User().also {
        it.name = "admin"
        it.userToUserRoleLinks = mutableListOf(
                UserToUserRoleLink().also {
                    it.userRoleId = superUserRole!!.id!!
                    it.userRole = superUserRole!!
                }
        )
        it.account = Account().also {
            it.password = PasswordHashingService.hashPassword("sudo")
        }
    }

    fun ensureSuperUserExistsInDatabaseOtherwiseCreateIt() {
        val superUser = UserRecord.GET()
                .join {
                    it.userToUserRoleLinks() {
                        it.userRole()
                    }
                }
                .where(
                        USERS.NAME.eq("admin")
                                .and(USER_ROLES.NAME.eq(PredefinedUserRoleManager.PredefinedRoleNames.SUPER_USER.toString()))
                )
                .preload {
                    it.userToUserRoleLinks() {
                        it.preload {
                            it.userRole()
                        }
                    }
                }
                .limit(1)
                .execute()
                .firstOrNull()


        if (superUser == null) {
            TransactionRunner.run {tx ->
                superUserInstance.record.saveCascade(tx.inTransactionDsl)
            }
            return
        }

        if (superUser.userToUserRoleLinks == null) {
            throw IllegalStateException("User with SUPER_USER UserRole is not persistent or link is not assigned")
        }

        if (superUser.userToUserRoleLinks!!.firstOrNull() == null) {
            throw IllegalStateException("User with SUPER_USER UserRole is not persistent or link is not assigned")
        }

        if (superUser.userToUserRoleLinks!!.first().userRole!!.name != PredefinedUserRoleManager.PredefinedRoleNames.SUPER_USER.toString()) {
            throw IllegalStateException("User with SUPER_USER UserRole is not persistent or link is not assigned")
        }

    }

}