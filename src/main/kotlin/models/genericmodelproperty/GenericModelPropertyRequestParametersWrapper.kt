package models.genericmodelproperty

import models.genericmodelproperty.GenericModelProperty
import utils.requestparameters.IParam

import java.sql.Timestamp


class GenericModelPropertyRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val modelId: Long? by lazy { requestParameters.get("modelId")?.long }
    val modelType: String? by lazy { requestParameters.get("modelType")?.string }
    val value: String? by lazy { requestParameters.get("value")?.string }
    val name: String? by lazy { requestParameters.get("name")?.string }
    val category: String? by lazy { requestParameters.get("category")?.string }
    val subCategory: String? by lazy { requestParameters.get("subCategory")?.string }


}