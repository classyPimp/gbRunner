package models.itemblueprint

import models.itemblueprint.ItemBlueprint
import utils.requestparameters.IParam

import java.sql.Timestamp

import org.jooq.generated.tables.Campaigns
import models.campaign.Campaign
import models.campaign.CampaignRequestParametersWrapper

class ItemBlueprintRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val specificForCampaignId: Long? by lazy { requestParameters.get("specificForCampaignId")?.long }
    val category: String? by lazy { requestParameters.get("category")?.string }
    val subcategory: String? by lazy { requestParameters.get("subcategory")?.string }
    val name: String? by lazy { requestParameters.get("name")?.string }
    val description: String? by lazy { requestParameters.get("description")?.string }
    val baseAc: Int? by lazy { requestParameters.get("baseAc")?.int }
    val DiceCount: Int? by lazy { requestParameters.get("DiceCount")?.int }
    val DiceValue: Int? by lazy { requestParameters.get("DiceValue")?.int }
    val DependsOnAttribute: String? by lazy { requestParameters.get("DependsOnAttribute")?.string }
    val specificForCampaign: CampaignRequestParametersWrapper? by lazy {
        requestParameters.get("specificForCampaign")?.let {
            CampaignRequestParametersWrapper(it)
        }
    }


}