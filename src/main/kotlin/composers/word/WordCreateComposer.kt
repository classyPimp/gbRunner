package composers.word

import utils.composer.ComposerBase
import models.word.Word
import models.word.WordRequestParametersWrapper
import models.word.WordValidator
import models.word.factories.WordCreateFactory
import orm.services.ModelInvalidError
import utils.composer.composerexceptions.InvalidRequestParametersError
import utils.requestparameters.IParam

class WordCreateComposer(
        val params: IParam
) : ComposerBase() {

    lateinit var onSuccess: (Word)->Unit
    lateinit var onError: (Word)->Unit

    lateinit var wordToCreate: Word
    lateinit var wrappedParams: WordRequestParametersWrapper

    override fun beforeCompose(){
        wrapParams()
        build()
        validate()
    }

    private fun wrapParams() {
        params.get("word")?.let {
            wrappedParams = WordRequestParametersWrapper(it)
        } ?: failImmediately(InvalidRequestParametersError())
    }

    private fun build() {
        wordToCreate = WordCreateFactory.create(wrappedParams)
    }

    private fun validate() {
        WordValidator(wordToCreate).createScenario()
    }

    override fun compose(){
        wordToCreate.record.save()
    }

    override fun fail(error: Throwable) {
        when(error) {
            is ModelInvalidError -> {
                onError.invoke(wordToCreate)
            }
            else -> {
                throw error
            }
        }
    }

    override fun success() {
        onSuccess.invoke(wordToCreate)
    }

}

