package models.word

import org.jooq.generated.tables.Words
import orm.annotations.*
import orm.wordgeneratedrepository.WordRecord
import java.sql.Timestamp
import org.jooq.generated.tables.Campaigns
import models.campaign.Campaign
import org.jooq.generated.tables.GenericGenericLinks
import models.genericgenericlink.GenericGenericLink
import models.gift.Gift

@IsModel(jooqTable = Words::class)
class Word {

    val record: WordRecord by lazy { WordRecord(this) }

    @TableField(name = "ID")
    @IsPrimaryKey
    var id: Long? = null

    @TableField(name = "CREATED_AT")
    var createdAt: Timestamp? = null

    @TableField(name = "UPDATED_AT")
    var updatedAt: Timestamp? = null

    @TableField(name = "NAME")
    var name: String? = null

    @TableField(name = "DESCRIPTION")
    var description: String? = null

    @TableField(name = "SPECIFIC_FOR_CAMPAIGN_ID")
    var specificForCampaignId: Long? = null

    @BelongsTo(model = Campaign::class, fieldOnThis = "SPECIFIC_FOR_CAMPAIGN_ID", fieldOnThat = "ID")
    var specificForCampaign: Campaign? = null

    @HasManyAsPolymorphic(model = GenericGenericLink::class, fieldOnThis = "ID", fieldOnThat = "RIGHT_MODEL_ID", polymorphicTypeField = "RIGHT_MODEL_TYPE")
    var linksToCharacters: MutableList<GenericGenericLink>? = null

    @HasMany(model = Gift::class, fieldOnThis = "ID", fieldOnThat = "WORD_ID")
    var gifts: MutableList<Gift>? = null

}

