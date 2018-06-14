package models.userrole.daos

import org.jooq.generated.tables.UserRoles
import orm.userrolegeneratedrepository.UserRoleRecord
import models.userrole.UserRole

object UserRoleIndexDao {
    fun forFormFeedIndex(): MutableList<UserRole> {
        return UserRoleRecord.GET()
                .execute()
    }
}