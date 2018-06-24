package models.genericmodelproperty

import org.jooq.generated.tables.GenericModelProperties
import orm.annotations.*
import orm.genericmodelpropertygeneratedrepository.GenericModelPropertyRecord
import java.sql.Timestamp

@IsModel(jooqTable = GenericModelProperties::class)
class GenericModelProperty {

    val record: GenericModelPropertyRecord by lazy { GenericModelPropertyRecord(this) }

    @TableField(name = "ID")
    @IsPrimaryKey
    var id: Long? = null

    @TableField(name = "CREATED_AT")
    var createdAt: Timestamp? = null

    @TableField(name = "UPDATED_AT")
    var updatedAt: Timestamp? = null

    @TableField(name = "MODEL_ID")
    var modelId: Long? = null

    @TableField(name = "MODEL_TYPE")
    var modelType: String? = null

    @TableField(name = "VALUE")
    var value: String? = null

    @TableField(name = "NAME")
    var name: String? = null

    @TableField(name = "CATEGORY")
    var category: String? = null

    @TableField(name = "SUB_CATEGORY")
    var subCategory: String? = null


}

