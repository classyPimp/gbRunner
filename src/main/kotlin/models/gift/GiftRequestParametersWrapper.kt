package models.gift

import models.gift.Gift
import utils.requestparameters.IParam

import java.sql.Timestamp

import org.jooq.generated.tables.Campaigns
import models.campaign.Campaign
import models.campaign.CampaignRequestParametersWrapper
import org.jooq.generated.tables.Words
import models.word.Word
import models.word.WordRequestParametersWrapper

class GiftRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val wordId: Long? by lazy { requestParameters.get("wordId")?.long }
    val name: String? by lazy { requestParameters.get("name")?.string }
    val description: String? by lazy { requestParameters.get("description")?.string }
    val specificForCampaignId: Long? by lazy { requestParameters.get("specificForCampaignId")?.long }
    val category: String? by lazy { requestParameters.get("category")?.string }
    val specificForCampaign: CampaignRequestParametersWrapper? by lazy {
        requestParameters.get("specificForCampaign")?.let {
            CampaignRequestParametersWrapper(it)
        }
    }
    val word: WordRequestParametersWrapper? by lazy {
        requestParameters.get("word")?.let {
            WordRequestParametersWrapper(it)
        }
    }


}