package composers.word

import utils.composer.ComposerBase
import models.word.Word
import models.word.WordRequestParametersWrapper
import models.word.WordValidator
import models.word.daos.WordDaos
import models.word.updaters.WordUpdater
import orm.services.ModelInvalidError
import utils.composer.composerexceptions.InvalidRequestParametersError
import utils.requestparameters.IParam

class WordUpdateComposer(
        val params: IParam,
        val wordId: Long
) : ComposerBase() {

    lateinit var onSuccess: (Word)->Unit
    lateinit var onError: (Word)->Unit

    lateinit var wrappedParams: WordRequestParametersWrapper
    lateinit var wordToUpdate: Word

    override fun beforeCompose(){
        wrapParams()
        findAndSetWord()
        runUpdate()
        validate()
    }

    private fun wrapParams() {
        params.get("word")?.let {
            wrappedParams = WordRequestParametersWrapper(it)
        } ?: failImmediately(InvalidRequestParametersError())
    }


    private fun findAndSetWord() {
        WordDaos.show.forUpdate(wordId)?.let {
            wordToUpdate = it
        } ?: failImmediately(ModelInvalidError())
    }

    private fun runUpdate() {
        WordUpdater.update(wordToUpdate, wrappedParams)
    }

    private fun validate() {
        WordValidator(wordToUpdate).let {
            it.updateScenario()
            if (!it.validationManager.isValid()) {
                failImmediately(ModelInvalidError())
            }
        }
    }


    override fun compose(){

    }

    override fun fail(error: Throwable) {
        when(error) {
            is ModelInvalidError -> {
                onError.invoke(wordToUpdate)
            }
            else -> {
                throw error
            }
        }
    }

    override fun success() {
        onSuccess.invoke(wordToUpdate)
    }

}

