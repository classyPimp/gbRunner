package models.gift

import org.jooq.generated.tables.Gifts
import orm.annotations.*
import orm.giftgeneratedrepository.GiftRecord
import java.sql.Timestamp
import org.jooq.generated.tables.Campaigns
import models.campaign.Campaign
import models.genericgenericlink.GenericGenericLink
import org.jooq.generated.tables.Words
import models.word.Word
import org.apache.commons.lang3.mutable.Mutable

@IsModel(jooqTable = Gifts::class)
class Gift {

    val record: GiftRecord by lazy { GiftRecord(this) }

    @TableField(name = "ID")
    @IsPrimaryKey
    var id: Long? = null

    @TableField(name = "CREATED_AT")
    var createdAt: Timestamp? = null

    @TableField(name = "UPDATED_AT")
    var updatedAt: Timestamp? = null

    @TableField(name = "WORD_ID")
    var wordId: Long? = null

    @TableField(name = "NAME")
    var name: String? = null

    @TableField(name = "DESCRIPTION")
    var description: String? = null

    @TableField(name = "SPECIFIC_FOR_CAMPAIGN_ID")
    var specificForCampaignId: Long? = null

    @BelongsTo(model = Campaign::class, fieldOnThis = "SPECIFIC_FOR_CAMPAIGN_ID", fieldOnThat = "ID")
    var specificForCampaign: Campaign? = null

    @BelongsTo(model = Word::class, fieldOnThis = "WORD_ID", fieldOnThat = "ID")
    var word: Word? = null

    @HasManyAsPolymorphic(model = GenericGenericLink::class, fieldOnThis = "ID", fieldOnThat = "RIGHT_MODEL_ID", polymorphicTypeField = "RIGHT_MODEL_TYPE")
    var linksToCharacters: MutableList<GenericGenericLink>? = null

}

