package models.gift.daos

import org.jooq.generated.tables.Gifts
import orm.giftgeneratedrepository.GiftRecord
import models.gift.Gift
import org.jooq.generated.Tables.GIFTS

object GiftShowDao {

    val table = GIFTS

    fun byId(giftId: Long): Gift? {
        return GiftRecord.GET()
                .where(table.ID.eq(giftId))
                .executeGetFirstOrNull()
    }

    fun forUpdate(giftId: Long): Gift? {
        return GiftRecord.GET()
                .where(table.ID.eq(giftId))
                .executeGetFirstOrNull()
    }

    fun existsWithName(name: String): Boolean {
        val gift = GiftRecord.GET()
                .where(table.NAME.eq(name))
                .executeGetFirstOrNull()
        return (gift != null)
    }


}