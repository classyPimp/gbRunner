package models.word

import models.word.Word
import utils.requestparameters.IParam

import java.sql.Timestamp

import org.jooq.generated.tables.Campaigns
import models.campaign.Campaign
import models.campaign.CampaignRequestParametersWrapper
import org.jooq.generated.tables.GenericGenericLinks
import models.genericgenericlink.GenericGenericLink
import models.genericgenericlink.GenericGenericLinkRequestParametersWrapper

class WordRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val name: String? by lazy { requestParameters.get("name")?.string }
    val description: String? by lazy { requestParameters.get("description")?.string }
    val specificForCampaignId: Long? by lazy { requestParameters.get("specificForCampaignId")?.long }
    val specificForCampaign: CampaignRequestParametersWrapper? by lazy {
        requestParameters.get("specificForCampaign")?.let {
            CampaignRequestParametersWrapper(it)
        }
    }
    val linksToCharacters: MutableList<GenericGenericLinkRequestParametersWrapper>? by lazy {
    requestParameters.get("linksToCharacters")?.paramList()?.let {
        it.mapTo(mutableListOf<GenericGenericLinkRequestParametersWrapper>()) {
            GenericGenericLinkRequestParametersWrapper(it)
        }
    }
    }


}