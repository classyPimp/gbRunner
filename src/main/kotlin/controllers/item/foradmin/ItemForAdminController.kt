package controllers.item.foradmin

import composers.item.foradmin.ItemForAdminCreateComposer
import controllers.ApplicationControllerBase
import models.item.daos.ItemDaos
import models.item.tojsonserializers.foradmin.ItemForAdminCreateToJsonSerializer
import models.item.tojsonserializers.foradmin.ItemForAdminIndexToJsonSerializer
import models.userrole.PredefinedUserRoleManager
import router.src.ServletRequestContext
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

class ItemForAdminController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun create() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .shouldHaveRole(
                        PredefinedUserRoleManager.PredefinedRoleNames.SUPER_USER.toString()
                )
                .ifNot {
                    sendError(SC_UNAUTHORIZED)
                    return
                }

        val composer = ItemForAdminCreateComposer(requestParams())

        composer.onError = {
            renderJson(
                ItemForAdminCreateToJsonSerializer.onError(it)
            )
        }

        composer.onSuccess = {
            renderJson(
                ItemForAdminCreateToJsonSerializer.onSuccess(it)
            )
        }

        composer.run()
    }

    fun index() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .shouldHaveRole(PredefinedUserRoleManager.PredefinedRoleNames.SUPER_USER.toString())

        val items = ItemDaos.index.forAdminIndex()

        renderJson(
                ItemForAdminIndexToJsonSerializer.onSuccess(items)
        )
    }

}