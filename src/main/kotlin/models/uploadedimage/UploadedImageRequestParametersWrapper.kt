package models.uploadedimage

import models.uploadedimage.UploadedImage
import utils.requestparameters.IParam

import java.sql.Timestamp


class UploadedImageRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val ownerId: Long? by lazy { requestParameters.get("ownerId")?.long }
    val ownerType: String? by lazy { requestParameters.get("ownerType")?.string }
    val fileName: String? by lazy { requestParameters.get("fileName")?.string }


}