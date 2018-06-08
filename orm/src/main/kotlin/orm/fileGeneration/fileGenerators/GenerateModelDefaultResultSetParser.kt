package orm.fileGeneration.fileGenerators

import freemarker.template.Template
import orm.configs.templatingEngine.TemplateFilePathsConfig
import orm.fileGeneration.GeneratedFileFactory
import orm.fileGeneration.TemplateFactory
import orm.services.TemplateFileWriterService
import orm.templateDataModels.model.ModelDataModel
import java.io.File

/**
 * Created by classypimp on 9/19/17.
 */
class GenerateModelDefaultResultSetParser(val modelDataModel: ModelDataModel) {

    fun run(){
        val file: File = GeneratedFileFactory.createDefault(
                packageName = modelDataModel.packagesBean.baseGenerated,
                fileName = "${modelDataModel.modelClass}DefaultResultSetParser.kt"
        )

        val templateName: String = TemplateFilePathsConfig.modelDefaultResultSetParser
        val template: Template = TemplateFactory.createTemplate(templateName)

        TemplateFileWriterService.writeTemplate(template, modelDataModel, file)
    }

}