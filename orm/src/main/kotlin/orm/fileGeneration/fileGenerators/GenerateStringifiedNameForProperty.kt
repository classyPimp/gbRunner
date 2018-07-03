package orm.fileGeneration.fileGenerators

import freemarker.template.Template
import orm.configs.templatingEngine.TemplateFilePathsConfig
import orm.fileGeneration.GeneratedFileFactory
import orm.fileGeneration.TemplateFactory
import orm.services.TemplateFileWriterService
import orm.templateDataModels.model.ModelDataModel
import java.io.File

/**
 * Created by Муса on 03.07.2018.
 */
class GenerateStringifiedNameForProperty(val modelDataModel: ModelDataModel) {

    fun run(){
        val file: File = GeneratedFileFactory.createDefault(
                packageName = modelDataModel.packagesBean.baseGenerated,
                fileName = "${modelDataModel.modelClass}StringifiedNameForProperty"
        )

        val templateName: String = TemplateFilePathsConfig.modelStringifiedNameForProperty
        val template: Template = TemplateFactory.createTemplate(templateName)

        TemplateFileWriterService.writeTemplate(template, modelDataModel, file)
    }

}