package models.genericmodelproperty.daos

import org.jooq.generated.tables.GenericModelProperties
import models.genericmodelproperty.daos.GenericModelPropertyShowDao
import models.genericmodelproperty.daos.GenericModelPropertyIndexDao
import models.genericmodelproperty.daos.GenericModelPropertyEditDao
import models.genericmodelproperty.daos.GenericModelPropertyUpdateDao
import models.genericmodelproperty.daos.GenericModelPropertyDestroyDao

object GenericModelPropertyDaos {

    val show = GenericModelPropertyShowDao

    val index = GenericModelPropertyIndexDao

    val edit = GenericModelPropertyEditDao

    val update = GenericModelPropertyUpdateDao

    val destroy = GenericModelPropertyDestroyDao

}