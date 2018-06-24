package controllers.item.inventory.ofplayer

import controllers.ApplicationControllerBase
import models.genericgenericlink.daos.GenericGenericLinkDaos
import models.item.daos.ItemDaos
import models.item.tojsonserializers.item.inventory.forplayer.ItemAsInventoryForPlayerIndexToJsonSerializer
import router.src.ServletRequestContext

class ItemAsInventoryForPlayerController(context: ServletRequestContext) : ApplicationControllerBase(context) {

    fun index() {
        val gameCharacterId = routeParams().get("gameCharacterId")!!.toLong()
        currentUser.checkPermission()
                .shouldBeLoggedIn()
                .should {
                    GenericGenericLinkDaos.show.userToGameCharacterAsPrimaryCharacterExistsFor(
                            userId =  currentUser.userModel!!.id!!,
                            gameCharacterId = gameCharacterId
                    )
                }

        val items = ItemDaos.index.forGameCharacterAsPrimiaryCharacterForPlayer(gameCharacterId)

        renderJson(
                ItemAsInventoryForPlayerIndexToJsonSerializer.onSuccess(items)
        )
    }

    fun createStartingGear() {

    }

    fun addItem() {

    }

    fun createItem() {

    }

    fun indexAvailableStartingGear() {
        val items = ItemDaos.index.availableStartingGear()
        renderJson(
                ItemAsInventoryForPlayerIndexToJsonSerializer.onSuccess(items)
        )
    }

}