package models.influenceexpenditure

import org.jooq.generated.tables.InfluenceExpenditures
import orm.annotations.*
import orm.influenceexpendituregeneratedrepository.InfluenceExpenditureRecord
import java.sql.Timestamp
import org.jooq.generated.tables.GameCharacters
import models.gamecharacter.GameCharacter

@IsModel(jooqTable = InfluenceExpenditures::class)
class InfluenceExpenditure {

    val record: InfluenceExpenditureRecord by lazy { InfluenceExpenditureRecord(this) }

    @TableField(name = "ID")
    @IsPrimaryKey
    var id: Long? = null

    @TableField(name = "CREATED_AT")
    var createdAt: Timestamp? = null

    @TableField(name = "UPDATED_AT")
    var updatedAt: Timestamp? = null

    @TableField(name = "CATEGORY")
    var category: String? = null

    @TableField(name = "SPENT_ON")
    var spentOn: String? = null

    @TableField(name = "AMOUNT_SPENT")
    var amountSpent: Int? = null

    @TableField(name = "GAME_CHARACTER_ID")
    var gameCharacterId: Long? = null

    @BelongsTo(model = GameCharacter::class, fieldOnThis = "GAME_CHARACTER_ID", fieldOnThat = "ID")
    var gameCharacter: GameCharacter? = null


}

