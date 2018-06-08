package models.campaign

import models.campaign.Campaign
import utils.requestparameters.IParam

import java.sql.Timestamp

import org.jooq.generated.tables.GenericGenericLinks
import models.genericgenericlink.GenericGenericLink
import models.genericgenericlink.GenericGenericLinkRequestParametersWrapper

class CampaignRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val name: String? by lazy { requestParameters.get("name")?.string }
    val description: String? by lazy { requestParameters.get("description")?.string }


}