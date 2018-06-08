package models.genericgenericlink

import models.genericgenericlink.GenericGenericLink
import utils.requestparameters.IParam

import java.sql.Timestamp


class GenericGenericLinkRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val leftModelId: Long? by lazy { requestParameters.get("leftModelId")?.long }
    val leftModelType: String? by lazy { requestParameters.get("leftModelType")?.string }
    val rightModelId: Long? by lazy { requestParameters.get("rightModelId")?.long }
    val rightModelType: String? by lazy { requestParameters.get("rightModelType")?.string }
    val category: String? by lazy { requestParameters.get("category")?.string }
    val subCategory: String? by lazy { requestParameters.get("subCategory")?.string }
    val currentStatus: String? by lazy { requestParameters.get("currentStatus")?.string }
    val description: String? by lazy { requestParameters.get("description")?.string }


}