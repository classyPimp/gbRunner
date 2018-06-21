package models.word

import models.word.daos.WordDaos
import orm.wordgeneratedrepository.WordValidatorTrait

class WordValidator(model: Word) : WordValidatorTrait(model, model.record.validationManager) {

    fun createScenario(){
        validateName()
        validateDescription()
    }

    fun updateScenario() {
        validateName()
        validateDescription()
    }

    fun whenGameCharacterForPlayerPrimaryCharacterCreateScenario() {
        validateExistance()
    }

    private fun validateExistance() {
        val id = model.id
        if (id == null) {
            throw IllegalStateException()
        }
        if (!WordDaos.show.exists(id)) {
            validationManager.addGeneralError("does not exist")
        }
    }


    private fun validateDescription() {
        val description = model.description
        if (description == null || description.isBlank()) {
            validationManager.addDescriptionError("should be provided")
            return
        }
    }

    private fun validateName() {
        val name = model.name
        if (name == null || name.isBlank()) {
            validationManager.addNameError("should be provided")
            return
        }
        if (WordDaos.show.existsWithSuchName(name)) {
            validationManager.addNameError("already exists")
            return
        }
    }


}
