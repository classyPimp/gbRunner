package models.campaign

import org.jooq.generated.tables.Campaigns
import orm.annotations.*
import orm.campaigngeneratedrepository.CampaignRecord
import java.sql.Timestamp
import org.jooq.generated.tables.GenericGenericLinks
import models.genericgenericlink.GenericGenericLink
import models.uploadedimage.UploadedImage
import models.userrole.UserRole
import javax.xml.registry.infomodel.User

@IsModel(jooqTable = Campaigns::class)
class Campaign {

    val record: CampaignRecord by lazy { CampaignRecord(this) }

    @TableField(name = "ID")
    @IsPrimaryKey
    var id: Long? = null

    @TableField(name = "CREATED_AT")
    var createdAt: Timestamp? = null

    @TableField(name = "UPDATED_AT")
    var updatedAt: Timestamp? = null

    @TableField(name = "NAME")
    var name: String? = null

    @TableField(name = "DESCRIPTION")
    var description: String? = null

    @HasManyAsPolymorphic(model = UploadedImage::class, fieldOnThat = "OWNER_ID", fieldOnThis = "ID", polymorphicTypeField = "OWNER_TYPE")
    var uploadedImages: MutableList<UploadedImage>? = null

    @HasManyAsPolymorphic(model = GenericGenericLink::class, fieldOnThis = "ID", fieldOnThat = "LEFT_MODEL_ID", polymorphicTypeField = "LEFT_MODEL_TYPE")
    var linksToUsers: MutableList<GenericGenericLink>? = null

}

