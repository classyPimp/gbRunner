package models.itemblueprint

import org.jooq.generated.tables.ItemBlueprints
import orm.annotations.*
import orm.itemblueprintgeneratedrepository.ItemBlueprintRecord
import java.sql.Timestamp
import org.jooq.generated.tables.Campaigns
import models.campaign.Campaign

@IsModel(jooqTable = ItemBlueprints::class)
class ItemBlueprint {

    val record: ItemBlueprintRecord by lazy { ItemBlueprintRecord(this) }

    @TableField(name = "ID")
    @IsPrimaryKey
    var id: Long? = null

    @TableField(name = "CREATED_AT")
    var createdAt: Timestamp? = null

    @TableField(name = "UPDATED_AT")
    var updatedAt: Timestamp? = null

    @TableField(name = "SPECIFIC_FOR_CAMPAIGN_ID")
    var specificForCampaignId: Long? = null

    @TableField(name = "CATEGORY")
    var category: String? = null

    @TableField(name = "SUBCATEGORY")
    var subcategory: String? = null

    @TableField(name = "NAME")
    var name: String? = null

    @TableField(name = "DESCRIPTION")
    var description: String? = null

    @TableField(name = "BASE_AC")
    var baseAc: Int? = null

    @TableField(name = "DICE_COUNT")
    var DiceCount: Int? = null

    @TableField(name = "DICE_VALUE")
    var DiceValue: Int? = null

    @TableField(name = "DEPENDS_ON_ATTRIBUTE")
    var DependsOnAttribute: String? = null

    @BelongsTo(model = Campaign::class, fieldOnThis = "SPECIFIC_FOR_CAMPAIGN_ID", fieldOnThat = "ID")
    var specificForCampaign: Campaign? = null


}

