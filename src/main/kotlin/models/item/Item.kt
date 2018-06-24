package models.item

import org.jooq.generated.tables.Items
import orm.annotations.*
import orm.itemgeneratedrepository.ItemRecord
import java.sql.Timestamp
import models.gamecharacter.GameCharacter
import models.statmodifier.StatModifier

@IsModel(jooqTable = Items::class)
class Item {

    enum class Categories {
        WEAPON,
        ARMOR,
        ABILITY,
    }

    enum class WeaponSubCategories {
        UNARMED,
        LIGHT,
        MEDIUM,
        HEAVY,
        ONE_HAND_RANGED,
        TWO_HAND_RANGED
    }

    enum class ArmorSubCategories {
        NONE,
        LIGHT,
        MEDIUM,
        HEAVY,
        SHIELD,
    }

    enum class AbilitySubCategories {
        SPENDS_EFFORT_ON_TOGGLE,
        CONSTANT,
    }

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

    @TableField(name = "OWNER_ID")
    var ownerId: Long? = null

    @TableField(name = "IS_EQUIPPED")
    var isEquipped: Boolean? = null

    @TableField(name = "IS_BLUEPRINT")
    var isBlueprint: Boolean? = null

    @TableField(name = "IS_IN_INVENTORY")
    var isInInventory: Boolean? = null

    @TableField(name = "IS_ABILITY")
    var isAbility: Boolean? = null

    @TableField(name = "BLUEPRINT_ID")
    var bluePrintId: Long? = null

    @TableField(name = "CAMPAIGN_ID")
    var campaignId: Long? = null

    @BelongsTo(model = GameCharacter::class, fieldOnThis = "OWNER_ID", fieldOnThat = "ID")
    var gameCharacter: GameCharacter? = null

    @HasMany(model = StatModifier::class, fieldOnThis = "ID", fieldOnThat = "ITEM_ID")
    var statModifiers: MutableList<StatModifier>? = null

    @BelongsTo(model = Item::class, fieldOnThat = "ID", fieldOnThis = "BLUEPRINT_ID")
    var blueprint: Item? = null

    @HasMany(model = Item::class, fieldOnThis = "ID", fieldOnThat = "BLUEPRINT_ID")
    var itemsOfThisBlueprint: MutableList<Item>? = null

}

