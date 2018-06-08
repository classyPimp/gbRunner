package models.word

import orm.wordgeneratedrepository.WordValidatorTrait

class WordValidator(model: Word) : WordValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        //
    }

}