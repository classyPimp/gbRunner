package models.statmodifier

import org.jooq.generated.tables.StatModifiers
import orm.annotations.*
import orm.statmodifiergeneratedrepository.StatModifierRecord
import java.sql.Timestamp
import models.gamecharacter.GameCharacter
import models.item.Item

@IsModel(jooqTable = StatModifiers::class)
class StatModifier {

    val record: StatModifierRecord by lazy { StatModifierRecord(this) }

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

    @TableField(name = "GAME_CHARACTER_ID")
    var gameCharacterId: Long? = null

    @TableField(name = "ITEM_ID")
    var itemId: Long? = null

    @TableField(name = "IS_BLUEPRINT")
    var isBlueprint: Boolean? = null

    @TableField(name = "VALUE")
    var value: Int? = null

    @TableField(name = "NON_STANDARD_VALUE")
    var nonStandartValue: String? = null

    @BelongsTo(model = GameCharacter::class, fieldOnThis = "GAME_CHARACTER_ID", fieldOnThat = "ID")
    var gameCharacter: GameCharacter? = null

    @BelongsTo(model = Item::class, fieldOnThis = "ITEM_ID", fieldOnThat = "ID")
    var item: Item? = null


}

