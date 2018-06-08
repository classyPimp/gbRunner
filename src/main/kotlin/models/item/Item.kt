package models.item

import org.jooq.generated.tables.Items
import orm.annotations.*
import orm.itemgeneratedrepository.ItemRecord
import java.sql.Timestamp
import models.itemblueprint.ItemBlueprint
import models.gamecharacter.GameCharacter

@IsModel(jooqTable = Items::class)
class Item {

    val record: ItemRecord by lazy { ItemRecord(this) }

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
    var subcategory: String? = null

    @TableField(name = "NAME")
    var name: String? = null

    @TableField(name = "DESCRIPTION")
    var description: String? = null

    @TableField(name = "BASE_AC")
    var baseAc: Int? = null

    @TableField(name = "DICE_COUNT")
    var diceCount: Int? = null

    @TableField(name = "DICE_VALUE")
    var diceValue: Int? = null

    @TableField(name = "DEPENDS_ON_ATTRIBUTE")
    var dependsOnAttribute: String? = null

    @TableField(name = "OWNER_ID")
    var ownerId: Long? = null

    @TableField(name = "IS_EQUIPPED")
    var isEquipped: Boolean? = null

    @TableField(name = "IS_IN_INVENTORY")
    var isInInventory: Boolean? = null

    @TableField(name = "ITEM_BLUEPRINT_ID")
    var itemBlueprintId: Long? = null

    @TableField(name = "CAMPAIGN_ID")
    var campaignId: Long? = null

    @BelongsTo(model = ItemBlueprint::class, fieldOnThis = "ITEM_BLUEPRINT_ID", fieldOnThat = "ID")
    var blueprint: ItemBlueprint? = null

    @BelongsTo(model = GameCharacter::class, fieldOnThis = "OWNER_ID", fieldOnThat = "ID")
    var gameCharacter: GameCharacter? = null


}

