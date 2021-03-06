package orm.fileGeneration.fileGenerators

import freemarker.template.Template
import orm.configs.templatingEngine.TemplateFilePathsConfig
import orm.fileGeneration.GeneratedFileFactory
import orm.fileGeneration.TemplateFactory
import orm.services.TemplateFileWriterService
import orm.templateDataModels.model.ModelDataModel
import java.io.File

/**
 * Created by Муса on 25.10.2017.
 */
class GenerateModelDeleteQueryBuilder(val modelDataModel: ModelDataModel) {
    fun run(){
        val file: File = GeneratedFileFactory.createDefault(
                packageName = modelDataModel.packagesBean.baseGenerated,
                fileName = "${modelDataModel.modelClass}DeleteQueryBuilder.kt"
        )

        val templateName: String = TemplateFilePathsConfig.modelDeleteQueryBuilder
        val template: Template = TemplateFactory.createTemplate(templateName)

        TemplateFileWriterService.writeTemplate(template, modelDataModel, file)
    }
}