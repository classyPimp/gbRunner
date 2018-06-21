package models.genericgenericlink

import models.campaign.Campaign
import models.characterblueprint.CharacterBlueprint
import models.gamecharacter.GameCharacter
import models.gift.Gift
import models.user.User
import models.word.Word
import org.jooq.generated.tables.GenericGenericLinks
import orm.annotations.*
import orm.genericgenericlinkgeneratedrepository.GenericGenericLinkRecord
import java.sql.Timestamp

@IsModel(jooqTable = GenericGenericLinks::class)
class GenericGenericLink {

    enum class Categories {
        ;
        enum class UserToCampaignLink {
            GAME_MASTER,
            PLAYER
        }
        enum class UserToCharacterLink {
            PRIMARY_PLAYER_CHARACTER
        }
    }


    val record: GenericGenericLinkRecord by lazy { GenericGenericLinkRecord(this) }

    @TableField(name = "ID")
    @IsPrimaryKey
    var id: Long? = null

    @TableField(name = "CREATED_AT")
    var createdAt: Timestamp? = null

    @TableField(name = "UPDATED_AT")
    var updatedAt: Timestamp? = null

    @TableField(name = "LEFT_MODEL_ID")
    var leftModelId: Long? = null

    @TableField(name = "LEFT_MODEL_TYPE")
    var leftModelType: String? = null

    @TableField(name = "RIGHT_MODEL_ID")
    var rightModelId: Long? = null

    @TableField(name = "RIGHT_MODEL_TYPE")
    var rightModelType: String? = null

    @TableField(name = "CATEGORY")
    var category: String? = null

    @TableField(name = "SUB_CATEGORY")
    var subCategory: String? = null

    @TableField(name = "CURRENT_STATUS")
    var currentStatus: String? = null

    @TableField(name = "DESCRIPTION")
    var description: String? = null

    @BelongsTo(model = User::class, fieldOnThat = "ID", fieldOnThis = "RIGHT_MODEL_ID")
    var user: User? = null

    @BelongsTo(model = Campaign::class, fieldOnThis = "LEFT_MODEL_ID", fieldOnThat = "ID")
    var campaign: Campaign? = null

    @BelongsTo(model = GameCharacter::class, fieldOnThat = "ID", fieldOnThis = "LEFT_MODEL_ID")
    var gameCharacter: GameCharacter? = null

    @BelongsTo(model = CharacterBlueprint::class, fieldOnThat = "ID", fieldOnThis = "RIGHT_MODEL_ID")
    var characterBlueprint: CharacterBlueprint? = null

    @BelongsTo(model = Word::class, fieldOnThat = "ID", fieldOnThis = "RIGHT_MODEL_ID")
    var word: Word? = null

    @BelongsTo(model = Gift::class, fieldOnThis = "RIGHT_MODEL_ID", fieldOnThat = "ID")
    var gift: Gift? = null

}

