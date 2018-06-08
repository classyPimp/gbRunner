package models.uploadedimage

import org.jooq.generated.tables.UploadedImages
import orm.annotations.*
import orm.uploadedimagegeneratedrepository.UploadedImageRecord
import java.sql.Timestamp

@IsModel(jooqTable = UploadedImages::class)
class UploadedImage {

    val record: UploadedImageRecord by lazy { UploadedImageRecord(this) }

    @TableField(name = "ID")
    @IsPrimaryKey
    var id: Long? = null

    @TableField(name = "CREATED_AT")
    var createdAt: Timestamp? = null

    @TableField(name = "UPDATED_AT")
    var updatedAt: Timestamp? = null

    @TableField(name = "OWNER_ID")
    var ownerId: Long? = null

    @TableField(name = "OWNER_TYPE")
    var ownerType: String? = null

    @TableField(name = "FILE_NAME")
    var fileName: String? = null

}

