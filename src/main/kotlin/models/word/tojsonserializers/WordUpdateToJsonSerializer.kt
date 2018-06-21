package models.word.tojsonserializers

import models.word.Word
import orm.wordgeneratedrepository.WordToJsonSerializer

object WordUpdateToJsonSerializer {

    fun onSuccess(word: Word): String {
        WordToJsonSerializer(word).let {

            return it.serializeToString()
        }
    }

    fun onError(word: Word): String {
        WordToJsonSerializer(word). let {
            it.includeErrors()
            return it.serializeToString()
        }
    }

}