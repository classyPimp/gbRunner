package controllers.item.asblueprint.foradmin

import controllers.ApplicationControllerBase
import models.item.daos.ItemDaos
import models.item.tojsonserializers.asblueprint.foradmin.ItemAsBlueprintForAdminIndexToJsonSerializer
import models.userrole.PredefinedUserRoleManager
import router.src.ServletRequestContext
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

class ItemAsBlueprintForAdminController(context: ServletRequestContext) : ApplicationControllerBase(context) {


    fun index() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .shouldHaveRole(PredefinedUserRoleManager.PredefinedRoleNames.SUPER_USER.toString())
                .ifNot {
                    sendError(SC_UNAUTHORIZED)
                    return
                }

        val bluePrintItems = ItemDaos.index.asBlueprintForAdmin()

        renderJson(
                ItemAsBlueprintForAdminIndexToJsonSerializer.onSuccess(bluePrintItems)
        )
    }


}