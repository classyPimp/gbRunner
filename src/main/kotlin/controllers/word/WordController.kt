package controllers.word

import composers.word.WordCreateComposer
import composers.word.WordUpdateComposer
import controllers.ApplicationControllerBase
import models.userrole.PredefinedUserRoleManager
import models.word.daos.WordDaos
import models.word.tojsonserializers.WordCreateToJsonSerializer
import models.word.tojsonserializers.WordIndexToJsonSerializer
import models.word.tojsonserializers.WordShowToJsonSerializer
import models.word.tojsonserializers.WordUpdateToJsonSerializer
import router.src.ServletRequestContext
import javax.servlet.http.HttpServletResponse.*

class WordController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun create() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .shouldHaveRole(PredefinedUserRoleManager.PredefinedRoleNames.SUPER_USER.toString())
                .ifNot {
                    sendError(SC_UNAUTHORIZED)
                    return
                }

        val composer = WordCreateComposer(requestParams())

        composer.onError = {
            renderJson(
                    WordCreateToJsonSerializer.onError(it)
            )
        }
        composer.onSuccess = {
            renderJson(
                    WordCreateToJsonSerializer.onSuccess(it)
            )
        }
        composer.run()
    }

    fun show() {
        val wordId = routeParams().get("wordId")?.toLongOrNull()
        if (wordId == null) {
            sendError(SC_INTERNAL_SERVER_ERROR)
            return
        }
        val word = WordDaos.show.byId(wordId)
        if (word == null) {
            sendError(SC_NOT_FOUND)
            return
        }

        renderJson(
                WordShowToJsonSerializer.onSuccess(word)
        )
    }

    fun index() {
        val words = WordDaos.index.allPreloadingGifts()
        renderJson(
                WordIndexToJsonSerializer.onSuccess(words)
        )
    }

    fun update() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .shouldHaveRole(
                        PredefinedUserRoleManager.PredefinedRoleNames.SUPER_USER.toString()
                ).ifNot {
                    sendError(SC_UNAUTHORIZED)
                    return
                }

        val wordId = routeParams().get("wordId")!!.toLong()
        val composer = WordUpdateComposer(requestParams(), wordId)

        composer.onError = {
            renderJson(
                    WordUpdateToJsonSerializer.onError(it)
            )
        }

        composer.onSuccess = {
            renderJson(
                    WordUpdateToJsonSerializer.onSuccess(it)
            )
        }

        composer.run()
    }

}