package models.userrole

import org.jooq.generated.Tables.USER_ROLES
import orm.userrolegeneratedrepository.UserRoleRecord
import orm.utils.TransactionRunner


/**
 * Created by Муса on 08.06.2018.
 */
object PredefinedUserRoleManager {

    enum class PredefinedRoleNames {
        SUPER_USER
    }

    private val predefinedUserRoles = mutableListOf<UserRole>(
            UserRole().also {
                it.name = PredefinedRoleNames.SUPER_USER.toString()
            }
    )

    fun ensurePredefinedRolesPersistedInDbOtherwiseCreateMissing() {
        val independentUserRoles = UserRoleRecord.GET().where(
                USER_ROLES.IS_SPECIFIC.isNull
                        .or(USER_ROLES.IS_SPECIFIC.eq(false))
        ).execute()

        val predefinedRolesByNameMap = mutableMapOf<String, UserRole>()
        predefinedUserRoles.forEach {
            predefinedRolesByNameMap[it.name!!] = it
        }

        val queriedIndependetRolesByNameMap = mutableMapOf<String, UserRole>()
        independentUserRoles.forEach {
            queriedIndependetRolesByNameMap[it.name!!] = it
        }

        val predefinedNamesSet = predefinedRolesByNameMap.keys.toSet()
        val queriedNamesSet = queriedIndependetRolesByNameMap.keys.toSet()

        val difference = predefinedNamesSet.minus(queriedNamesSet)

        TransactionRunner.run {
            difference.forEach { name ->
                UserRole().also {
                    it.name = name
                    it.isSpecific = false
                    it.record.save()
                }
            }
        }
    }

}