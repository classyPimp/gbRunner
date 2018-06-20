package models.gamecharacter

import orm.annotations.*
import orm.gamecharactergeneratedrepository.GameCharacterRecord
import java.sql.Timestamp
import models.uploadedimage.UploadedImage
import models.characterblueprint.CharacterBlueprint
import models.genericgenericlink.GenericGenericLink
import org.jooq.generated.tables.GameCharacters

@IsModel(jooqTable = GameCharacters::class)
class GameCharacter {

    enum class Categories {
        PLAYER_PRIMARY_CHARACTER,
    }

    val record: GameCharacterRecord by lazy { GameCharacterRecord(this) }

    @TableField(name = "ID")
    @IsPrimaryKey
    var id: Long? = null

    @TableField(name = "CREATED_AT")
    var createdAt: Timestamp? = null

    @TableField(name = "UPDATED_AT")
    var updatedAt: Timestamp? = null

    @TableField(name = "CAMPAIGN_ID")
    var campaignId: Long? = null

    @TableField(name = "STRENGTH")
    var strength: Int? = null

    @TableField(name = "LEVEL")
    var level: Int? = null

    @TableField(name = "EXPERIENCE_POINTS")
    var experiencePoints: Int? = null

    @TableField(name = "DEXTERITY")
    var dexterity: Int? = null

    @TableField(name = "CONSTITUTION")
    var constitution: Int? = null

    @TableField(name = "WISDOM")
    var wisdom: Int? = null

    @TableField(name = "INTELLIGENCE")
    var intelligence: Int? = null

    @TableField(name = "CHARISMA")
    var charisma: Int? = null

    @TableField(name = "HARDINESS_BASE")
    var hardinessBase: Int? = null

    @TableField(name = "EVASION_BASE")
    var evasionBase: Int? = null

    @TableField(name = "HITPOINTS_MAXIMUM")
    var hitpointsMaximum: Int? = null

    @TableField(name = "HITPOINTS_CURRENT")
    var hitpointsCurrent: Int? = null

    @TableField(name = "EFFORT_TOTAL")
    var effortTotal: Int? = null

    @TableField(name = "EFFORT_AVAILABLE")
    var effortAvailable: Int? = null

    @TableField(name = "INFLUENCE_TOTAL")
    var influenceTotal: Int? = null

    @TableField(name = "INFLUENCE_AVAILABLE")
    var influenceAvailable: Int? = null

    @TableField(name = "DOMINION_TOTAL")
    var dominionTotal: Int? = null

    @TableField(name = "DOMINION_AVAILABLE")
    var dominionAvailable: Int? = null

    @TableField(name = "CURRENT_WEALTH")
    var currentWealth: Int? = null

    @TableField(name = "DOMINION_EARNED_PER_MONTH")
    var dominionEarnedPerMonth: Int? = null

    @TableField(name = "CATEGORY")
    var category: String? = null

    @TableField(name = "CHARACTER_BLUEPRINT_ID")
    var characterBlueprintId: Long? = null

    @TableField(name = "CHARACTER_SUB_CATEGORY")
    var characterSubCategory: String? = null

    @TableField(name = "NAME")
    var name: String? = null

    @TableField(name = "DESCRIPTION")
    var description: String? = null

    @HasMany(model = UploadedImage::class, fieldOnThis = "ID", fieldOnThat = "OWNER_ID")
    var uploadedImages: MutableList<UploadedImage>? = null

    @BelongsTo(model = CharacterBlueprint::class, fieldOnThis = "CHARACTER_BLUEPRINT_ID", fieldOnThat = "ID")
    var characterBlueprint: CharacterBlueprint? = null

    @HasManyAsPolymorphic(model = GenericGenericLink::class, fieldOnThis = "ID", fieldOnThat = "LEFT_MODEL_ID", polymorphicTypeField = "LEFT_MODEL_TYPE")
    var linksToUsers: MutableList<GenericGenericLink>? = null

}

