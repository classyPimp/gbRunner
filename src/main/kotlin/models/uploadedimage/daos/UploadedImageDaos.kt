package models.uploadedimage.daos

import org.jooq.generated.tables.UploadedImages
import models.uploadedimage.daos.UploadedImageShowDao
import models.uploadedimage.daos.UploadedImageIndexDao
import models.uploadedimage.daos.UploadedImageEditDao
import models.uploadedimage.daos.UploadedImageUpdateDao
import models.uploadedimage.daos.UploadedImageDestroyDao

object UploadedImageDaos {

    val show = UploadedImageShowDao

    val index = UploadedImageIndexDao

    val edit = UploadedImageEditDao

    val update = UploadedImageUpdateDao

    val destroy = UploadedImageDestroyDao

}