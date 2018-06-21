package models.word.updaters

import models.word.Word
import models.word.WordRequestParametersWrapper


object WordUpdater {

    fun update(model: Word, params: WordRequestParametersWrapper) {
        model.record.let {
            it.description = params.description
            it.name = params.name
        }
    }

}