package models.statmodifier

import org.jooq.generated.tables.StatModifiers
import orm.annotations.*
import orm.statmodifiergeneratedrepository.StatModifierRecord
import java.sql.Timestamp
import models.gamecharacter.GameCharacter
import models.item.Item

@IsModel(jooqTable = StatModifiers::class)
class StatModifier {

    enum class Categories {
        ATTACK,
        ARMOR,
        SAVING_THROW_PENALTY,
    }

    enum class AttackSubCategories {
        PER_DICE_DAMAGE,
        ATTACK_DICE_COUNT,
        OVERRIDDEN_ATTACK_DICE_COUNT,
        OVERRIDDEN_PER_DICE_DAMAGE,
        ATTACK_BONUS,
        ADD_TO_RESULT_MODIFIER_OF_WHICH_BETTER_STRENGTH_OR_DEXTERITY,
        ADD_TO_RESULT_MODIFIER_OF_STRENGTH,
        ADD_TO_RESULT_MODIFIER_OF_DEXTERITY,
    }

    enum class ArmorSubCategories {
        BASE_AC,
        AC_BONUS_OR_PENALTY
    }

    enum class SavingThrowPenaltySubCategories {
        HARDINESS_SAVING_THROW_PENALTY,
        EVASION_SAVING_THROW_PENALTY,
        SPIRIT_SAVING_THROW_PENALTY,
    }

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
    var nonStandardValue: String? = null

    @BelongsTo(model = GameCharacter::class, fieldOnThis = "GAME_CHARACTER_ID", fieldOnThat = "ID")
    var gameCharacter: GameCharacter? = null

    @BelongsTo(model = Item::class, fieldOnThis = "ITEM_ID", fieldOnThat = "ID")
    var item: Item? = null


}

