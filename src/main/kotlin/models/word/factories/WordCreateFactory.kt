package models.word.factories

import models.word.Word
import models.word.WordRequestParametersWrapper

object WordCreateFactory {
    fun create(wrappedParams: WordRequestParametersWrapper): Word {
        return Word().also {
            it.name = wrappedParams.name
            it.description = wrappedParams.description
        }
    }

}