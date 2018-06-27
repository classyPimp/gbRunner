package models.item.daos

import org.jooq.generated.tables.Items
import orm.itemgeneratedrepository.ItemRecord
import models.item.Item
import org.jooq.generated.Tables.STAT_MODIFIERS

object ItemShowDao {

    val table = Items.ITEMS

    fun byId(itemId: Long): Item? {
        return ItemRecord.GET()
                .preload {
                    it.statModifiers() {
                        it.where(STAT_MODIFIERS.IS_BLUEPRINT.isTrue)
                    }
                }
                .where(table.ID.eq(itemId))
                .executeGetFirstOrNull()
    }

    fun byIdPreloadingStatModifiers(blueprintId: Long): Item? {
        return ItemRecord.GET()
                .where(table.ID.eq(blueprintId))
                .executeGetFirstOrNull()
    }

    fun alreadyExistsWith(name: String): Boolean {
        ItemRecord.GET()
                .where(table.NAME.eq(name))
                .executeGetFirstOrNull().let {
                    return it != null
                }
    }


}