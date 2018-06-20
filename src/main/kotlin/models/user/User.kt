package models.user

import models.account.Account
import models.genericgenericlink.GenericGenericLink
import models.uploadedimage.UploadedImage
import models.userrole.UserRole
import models.usertouserrolelink.UserToUserRoleLink
import org.jooq.generated.tables.UserToUserRoleLinks
import org.jooq.generated.tables.Users
import orm.annotations.*
import orm.modelUtils.FileItemFileProperty
import orm.usergeneratedrepository.UserRecord
import java.io.File
import java.sql.Timestamp
/**
 * Created by classypimp on 9/13/17.
 */
@IsModel(jooqTable = Users::class)
class User {

    companion object {
        fun pluckUserRolesFromUserToUserRoleLinkAndAssignToUserRoles(user: User) {
            user.userRoles = user.userToUserRoleLinks?.mapTo(mutableListOf<UserRole>()) {
                it.userRole!!
            }
        }
    }

    val record: UserRecord by lazy { UserRecord(this) }

    @TableField(name = "ID")
    @IsPrimaryKey
    var id: Long? = null

    @TableField(name = "NAME")
    var name: String? = null

    @TableField(name = "CREATED_AT")
    var createdAt: Timestamp? = null

    @TableField(name = "UPDATED_AT")
    var updatedAt: Timestamp? = null

    @HasOne(model = Account::class, fieldOnThis = "ID", fieldOnThat = "USER_ID")
    var account: Account? = null

    @HasMany(model = UserToUserRoleLink::class, fieldOnThat = "USER_ID", fieldOnThis = "ID")
    var userToUserRoleLinks: MutableList<UserToUserRoleLink>? = null

    var userRoles: MutableList<UserRole>? = null

    @HasManyAsPolymorphic(model = UploadedImage::class, fieldOnThat = "OWNER_ID", fieldOnThis = "ID", polymorphicTypeField = "OWNER_TYPE")
    var uploadedImages: MutableList<UploadedImage>? = null

    @HasManyAsPolymorphic(model = GenericGenericLink::class, fieldOnThis = "ID", fieldOnThat = "RIGHT_MODEL_ID", polymorphicTypeField = "RIGHT_MODEL_TYPE")
    var linksToCampaigns: MutableList<GenericGenericLink>? = null

    @HasManyAsPolymorphic(model = GenericGenericLink::class, fieldOnThis = "ID", fieldOnThat = "RIGHT_MODEL_ID", polymorphicTypeField = "RIGHT_MODEL_TYPE")
    var linksToGameCharacters: MutableList<GenericGenericLink>? = null
}



