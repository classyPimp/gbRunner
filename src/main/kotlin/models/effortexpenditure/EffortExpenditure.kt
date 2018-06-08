package models.effortexpenditure

import org.jooq.generated.tables.EffortExpenditures
import orm.annotations.*
import orm.effortexpendituregeneratedrepository.EffortExpenditureRecord
import java.sql.Timestamp
import org.jooq.generated.tables.GameCharacters
import models.gamecharacter.GameCharacter

@IsModel(jooqTable = EffortExpenditures::class)
class EffortExpenditure {

    val record: EffortExpenditureRecord by lazy { EffortExpenditureRecord(this) }

    @TableField(name = "ID")
    @IsPrimaryKey
    var id: Long? = null

    @TableField(name = "CREATED_AT")
    var createdAt: Timestamp? = null

    @TableField(name = "UPDATED_AT")
    var updatedAt: Timestamp? = null

    @TableField(name = "CATEGORY")
    var Category: String? = null

    @TableField(name = "SPENT_ON")
    var spentOn: String? = null

    @TableField(name = "AMOUNT_SPENT")
    var amountSpent: Int? = null

    @TableField(name = "GAME_CHARACTER_ID")
    var gameCharacterId: Long? = null

    @BelongsTo(model = GameCharacter::class, fieldOnThis = "GAME_CHARACTER_ID", fieldOnThat = "ID")
    var gameCharacter: GameCharacter? = null


}

