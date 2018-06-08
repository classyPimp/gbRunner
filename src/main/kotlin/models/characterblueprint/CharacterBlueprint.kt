package models.characterblueprint

import orm.annotations.*
import orm.characterblueprintgeneratedrepository.CharacterBlueprintRecord
import java.sql.Timestamp
import org.jooq.generated.tables.Campaigns
import models.campaign.Campaign
import org.jooq.generated.tables.GenericGenericLinks
import models.genericgenericlink.GenericGenericLink
import org.jooq.generated.tables.CharacterBlueprints

@IsModel(jooqTable = CharacterBlueprints::class)
class CharacterBlueprint {

    val record: CharacterBlueprintRecord by lazy { CharacterBlueprintRecord(this) }

    @TableField(name = "ID")
    @IsPrimaryKey
    var id: Long? = null

    @TableField(name = "CREATED_AT")
    var createdAt: Timestamp? = null

    @TableField(name = "UPDATED_AT")
    var updatedAt: Timestamp? = null

    @TableField(name = "SPECIFIC_FOR_CAMPAIGN_ID")
    var specificForCampaignId: Long? = null

    @TableField(name = "STRENGTH")
    var strength: Int? = null

    @TableField(name = "LEVEL")
    var level: Int? = null

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

    @TableField(name = "SPIRIT_BASE")
    var spiritBase: Int? = null

    @TableField(name = "HITPOINTS_MAXIMUM")
    var hitpointsMaximum: Int? = null

    @TableField(name = "EFFORT_TOTAL")
    var effortTotal: Int? = null

    @TableField(name = "INFLUENCE_TOTAL")
    var influenceTotal: Int? = null

    @TableField(name = "DOMINION_TOTAL")
    var dominionTotal: Int? = null

    @TableField(name = "CURRENT_WEALTH")
    var currentWealth: Int? = null

    @TableField(name = "DOMINION_EARNED_PER_MONTH")
    var dominionEarnedPerMonth: Int? = null

    @TableField(name = "DESCRIPTION")
    var description: String? = null

    @BelongsTo(model = Campaign::class, fieldOnThis = "SPECIFIC_FOR_CAMPAIGN_ID", fieldOnThat = "ID")
    var specificForCampaign: Campaign? = null

    @HasManyAsPolymorphic(model = GenericGenericLink::class, fieldOnThis = "ID", fieldOnThat = "RIGHT_MODEL_ID", polymorphicTypeField = "RIGHT_MODEL_TYPE")
    var linksToCharacters: MutableList<GenericGenericLink>? = null


}

