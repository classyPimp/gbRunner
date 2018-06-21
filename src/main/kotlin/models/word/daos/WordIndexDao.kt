package models.word.daos

import org.jooq.generated.tables.Words
import orm.wordgeneratedrepository.WordRecord
import models.word.Word
import org.jooq.generated.Tables.WORDS

object WordIndexDao {
    val table = WORDS

    fun allPreloadingGifts(): MutableList<Word> {
        return WordRecord.GET()
                .preload {
                    it.gifts()
                }
                .execute()
    }

}