package models.uploadedimage

import org.apache.commons.fileupload.FileItem
import orm.modelUtils.FileItemFileProperty
import java.io.File

/**
 * Created by Муса on 07.06.2018.
 */
class UploadedImageFileHandler(val model: UploadedImage): FileItemFileProperty() {
    override val baseUploadPath: String = "uploads"
    override val modelName = "uploadedImage"
    override val propertyName: String ="file"

    override val modelId: Long?
        get() =  model.id

    override val fileNameOnProperty: String?
        get() = model.fileName

    override fun handlePropertiesOnAssign(fileItem: FileItem) {
        model.record.let {
            it.fileName = fileItem.name
        }
    }

    override fun handlePropertiesOnAssign(file: File) {
        model.let {
            it.record.fileName = file.name
        }
    }

    override fun handlePropertiesOnDelete() {
        model.let {
            it.record.fileName = null
        }
    }

    override fun preprocessFile(file: File) {
        App.component.imageProcessorFactory().create(file, createEmptyFileWherePreprocessedFileWillBeStored("small_square")).let {
            it.resize(600, 600)
            it.execute()
        }
    }

    override fun validateFile(uploadedFile: File): Boolean {
        return true
    }

    override fun afterAssignedToUnpersitedModelAndItsSaved(file: File) {
        model.record.save()
    }
}