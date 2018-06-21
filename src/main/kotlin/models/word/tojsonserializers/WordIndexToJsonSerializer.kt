package models.word.tojsonserializers

import models.word.Word
import orm.wordgeneratedrepository.WordToJsonSerializer

object WordIndexToJsonSerializer {

    fun onSuccess(words: MutableList<Word>): String {
        return WordToJsonSerializer.serialize(words) {
            it.includeGifts()
        }.serializeToString()
    }

}