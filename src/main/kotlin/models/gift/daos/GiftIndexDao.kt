package models.gift.daos

import org.jooq.generated.tables.Gifts
import orm.giftgeneratedrepository.GiftRecord
import models.gift.Gift
import org.jooq.generated.Tables.GIFTS

object GiftIndexDao {

    val table = GIFTS

    fun ofWordIndex(wordId: Long): MutableList<Gift> {
        return GiftRecord.GET()
                .where(
                        table.WORD_ID.eq(wordId)
                )
                .execute()
    }


}