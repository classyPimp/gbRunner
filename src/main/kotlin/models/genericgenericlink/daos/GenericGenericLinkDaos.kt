package models.genericgenericlink.daos

import org.jooq.generated.tables.GenericGenericLinks
import models.genericgenericlink.daos.GenericGenericLinkShowDao
import models.genericgenericlink.daos.GenericGenericLinkIndexDao
import models.genericgenericlink.daos.GenericGenericLinkEditDao
import models.genericgenericlink.daos.GenericGenericLinkUpdateDao
import models.genericgenericlink.daos.GenericGenericLinkDestroyDao

object GenericGenericLinkDaos {

    val show = GenericGenericLinkShowDao

    val index = GenericGenericLinkIndexDao

    val edit = GenericGenericLinkEditDao

    val update = GenericGenericLinkUpdateDao

    val destroy = GenericGenericLinkDestroyDao

}