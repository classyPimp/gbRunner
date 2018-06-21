package models.word.daos

import org.jooq.generated.tables.Words
import orm.wordgeneratedrepository.WordRecord
import models.word.Word

object WordShowDao {
    val table = Words.WORDS
    fun existsWithSuchName(name: String): Boolean {
        val word = WordRecord.GET()
                .where(table.NAME.eq(name))
                .executeGetFirstOrNull()
        return (word != null)
    }

    fun byId(wordId: Long): Word? {
        return WordRecord.GET()
                .where(table.ID.eq(wordId))
                .executeGetFirstOrNull()
    }

    fun forUpdate(wordId: Long): Word? {
        return WordRecord.GET()
                .where(table.ID.eq(wordId))
                .executeGetFirstOrNull()
    }

    fun exists(id: Long): Boolean {
        val word = WordRecord.GET()
                .where(table.ID.eq(id))
                .executeGetFirstOrNull()
        return word != null
    }


}