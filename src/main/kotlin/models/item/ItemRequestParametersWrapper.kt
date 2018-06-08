package models.item

import utils.requestparameters.IParam

import java.sql.Timestamp

import models.itemblueprint.ItemBlueprintRequestParametersWrapper
import models.gamecharacter.GameCharacterRequestParametersWrapper

class ItemRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val category: String? by lazy { requestParameters.get("category")?.string }
    val subcategory: String? by lazy { requestParameters.get("subcategory")?.string }
    val name: String? by lazy { requestParameters.get("name")?.string }
    val description: String? by lazy { requestParameters.get("description")?.string }
    val baseAc: Int? by lazy { requestParameters.get("baseAc")?.int }
    val diceCount: Int? by lazy { requestParameters.get("diceCount")?.int }
    val diceValue: Int? by lazy { requestParameters.get("diceValue")?.int }
    val dependsOnAttribute: String? by lazy { requestParameters.get("dependsOnAttribute")?.string }
    val ownerId: Long? by lazy { requestParameters.get("ownerId")?.long }
    val isEquipped: Boolean? by lazy { requestParameters.get("isEquipped")?.boolean }
    val isInInventory: Boolean? by lazy { requestParameters.get("isInInventory")?.boolean }
    val itemBlueprintId: Long? by lazy { requestParameters.get("itemBlueprintId")?.long }
    val campaignId: Long? by lazy { requestParameters.get("campaignId")?.long }
    val blueprint: ItemBlueprintRequestParametersWrapper? by lazy {
        requestParameters.get("blueprint")?.let {
            ItemBlueprintRequestParametersWrapper(it)
        }
    }
    val gameCharacter: GameCharacterRequestParametersWrapper? by lazy {
        requestParameters.get("gameCharacter")?.let {
            GameCharacterRequestParametersWrapper(it)
        }
    }


}