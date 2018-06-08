package models.gameevent

import org.jooq.generated.tables.GameEvents
import orm.annotations.*
import orm.gameeventgeneratedrepository.GameEventRecord
import java.sql.Timestamp
import org.jooq.generated.tables.Campaigns
import models.campaign.Campaign
import org.jooq.generated.tables.UploadedImages
import models.uploadedimage.UploadedImage

@IsModel(jooqTable = GameEvents::class)
class GameEvent {

    val record: GameEventRecord by lazy { GameEventRecord(this) }

    @TableField(name = "ID")
    @IsPrimaryKey
    var id: Long? = null

    @TableField(name = "CREATED_AT")
    var createdAt: Timestamp? = null

    @TableField(name = "UPDATED_AT")
    var updatedAt: Timestamp? = null

    @TableField(name = "CATEGORY")
    var category: String? = null

    @TableField(name = "SUB_CATEGORY")
    var subCategory: String? = null

    @TableField(name = "DESCRIPTION")
    var description: String? = null

    @TableField(name = "CAMPAIGN_ID")
    var campaignId: Long? = null

    @BelongsTo(model = Campaign::class, fieldOnThis = "CAMPAIGN_ID", fieldOnThat = "ID")
    var campaign: Campaign? = null

    @HasMany(model = UploadedImage::class, fieldOnThis = "ID", fieldOnThat = "OWNER_ID")
    var uploadedImages: MutableList<UploadedImage>? = null


}

