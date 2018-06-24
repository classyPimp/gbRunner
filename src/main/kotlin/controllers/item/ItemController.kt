package controllers.item

import controllers.ApplicationControllerBase
import models.userrole.PredefinedUserRoleManager
import router.src.ServletRequestContext

class ItemController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun create() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .shouldHaveRole(PredefinedUserRoleManager.PredefinedRoleNames.SUPER_USER.toString())

//        val composer = ItemBlueprintCreateComposer
    }

}