package controllers.item.ofgamecharacter.foradmin

import composers.item.ofgamecharacter.foradmin.ItemOfGameCharacterForAdminAddToInventoryComposer
import controllers.ApplicationControllerBase
import models.item.daos.ItemDaos
import models.userrole.PredefinedUserRoleManager
import router.src.ServletRequestContext

class ItemOfGameCharacterForAdminController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun index() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .shouldHaveRole(
                        PredefinedUserRoleManager.PredefinedRoleNames.SUPER_USER.toString()
                )

        val gameCharacterId = routeParams().get("gameCharacterId")!!.toLong()

        ItemDaos.index.forGameCharacterAsPrimiaryCharacterForPlayer(gameCharacterId)
    }

    fun addToInventory() {
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .shouldHaveRole(
                        PredefinedUserRoleManager.PredefinedRoleNames.SUPER_USER.toString()
                )

        val gameCharacterId = routeParams().get("gameCharacterId")!!.toLong()
        val itemId = routeParams().get("itemId")!!.toLong()

        val composer = ItemOfGameCharacterForAdminAddToInventoryComposer(
                itemId = itemId,
                gameCharacterId = gameCharacterId
        )

    }

}