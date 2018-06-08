package models.item.daos

import org.jooq.generated.tables.Items
import models.item.daos.ItemShowDao
import models.item.daos.ItemIndexDao
import models.item.daos.ItemEditDao
import models.item.daos.ItemUpdateDao
import models.item.daos.ItemDestroyDao

object ItemDaos {

    val show = ItemShowDao

    val index = ItemIndexDao

    val edit = ItemEditDao

    val update = ItemUpdateDao

    val destroy = ItemDestroyDao

}