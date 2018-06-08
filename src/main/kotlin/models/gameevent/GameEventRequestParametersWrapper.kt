package models.gameevent

import models.gameevent.GameEvent
import utils.requestparameters.IParam

import java.sql.Timestamp

import org.jooq.generated.tables.Campaigns
import models.campaign.Campaign
import models.campaign.CampaignRequestParametersWrapper
import org.jooq.generated.tables.UploadedImages
import models.uploadedimage.UploadedImage
import models.uploadedimage.UploadedImageRequestParametersWrapper

class GameEventRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val category: String? by lazy { requestParameters.get("category")?.string }
    val subCategory: String? by lazy { requestParameters.get("subCategory")?.string }
    val description: String? by lazy { requestParameters.get("description")?.string }
    val campaignId: Long? by lazy { requestParameters.get("campaignId")?.long }
    val campaign: CampaignRequestParametersWrapper? by lazy {
        requestParameters.get("campaign")?.let {
            CampaignRequestParametersWrapper(it)
        }
    }
    val uploadedImages: MutableList<UploadedImageRequestParametersWrapper>? by lazy {
    requestParameters.get("uploadedImages")?.paramList()?.let {
        it.mapTo(mutableListOf<UploadedImageRequestParametersWrapper>()) {
            UploadedImageRequestParametersWrapper(it)
        }
    }
    }


}