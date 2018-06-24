package composers.item.ofgamecharacter.foradmin

import models.gamecharacter.GameCharacter
import models.gamecharacter.daos.GameCharacterDaos
import utils.composer.ComposerBase
import models.item.Item
import models.item.ItemValidator
import models.item.daos.ItemDaos
import orm.modelUtils.exceptions.ModelNotFoundError
import orm.services.ModelInvalidError

class ItemOfGameCharacterForAdminAddToInventoryComposer(
        val itemId: Long,
        val gameCharacterId: Long
) : ComposerBase() {

    lateinit var onSuccess: (Item)->Unit
    lateinit var onError: (Item)->Unit

    lateinit var itemToAddToInventory: Item
    lateinit var gameCharacterWhomItemShouldBeAdded: GameCharacter

    override fun beforeCompose(){
        findAndSetItemToAddToInventory()
        findAndSetGameCharacterWhomItemShouldBeAdded()
        validate()
        updateItemOwner()
        //TODO: should add instant stat modifiers if they are applied the moment char acquires item
    }


    private fun findAndSetItemToAddToInventory() {
        ItemDaos.show.byId(itemId)?.let {
            itemToAddToInventory = it
        } ?: failImmediately(ModelNotFoundError("no such item"))
    }

    private fun findAndSetGameCharacterWhomItemShouldBeAdded() {
        GameCharacterDaos.show.finByIdNotPreloadingAnything(gameCharacterId)?.let {
            gameCharacterWhomItemShouldBeAdded = it
        } ?: failImmediately(ModelNotFoundError("no such game character"))
    }

    private fun validate() {
        ItemValidator(itemToAddToInventory).also {
            it.whenAdminAddScenario(gameCharacterId)
            if (!it.validationManager.isValid()) {
                failImmediately(ModelInvalidError())
            }
        }
    }

    private fun updateItemOwner() {
        itemToAddToInventory.record.ownerId = gameCharacterId
    }

    override fun compose(){
        itemToAddToInventory.record.save()
    }

    override fun fail(error: Throwable) {
        when(error) {
            is ModelNotFoundError -> {
                onError(
                        Item().also {
                            it.record.validationManager.addGeneralError(error.message!!)
                        }
                )
            }
            is ModelInvalidError -> {
                onError(
                        itemToAddToInventory
                )
            }
            else -> {
                throw error
            }
        }
    }

    override fun success() {
        onSuccess.invoke(itemToAddToInventory)
    }

}

