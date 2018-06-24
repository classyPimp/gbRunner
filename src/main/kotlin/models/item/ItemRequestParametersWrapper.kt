package models.item

import utils.requestparameters.IParam

import java.sql.Timestamp

import models.itemblueprint.ItemBlueprintRequestParametersWrapper
import models.gamecharacter.GameCharacterRequestParametersWrapper
import models.statmodifier.StatModifierRequestParametersWrapper

class ItemRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val category: String? by lazy { requestParameters.get("category")?.string }
    val subcategory: String? by lazy { requestParameters.get("subcategory")?.string }
    val name: String? by lazy { requestParameters.get("name")?.string }
    val description: String? by lazy { requestParameters.get("description")?.string }
    val blueprintId: Long? by lazy { requestParameters.get("blueprintId")?.long }
    val isBlueprint: Boolean? by lazy { requestParameters.get("isBlueprint")?.boolean }
    val isAbility: Boolean? by lazy { requestParameters.get("isAbility")?.boolean }
    val ownerId: Long? by lazy { requestParameters.get("ownerId")?.long }
    val isEquipped: Boolean? by lazy { requestParameters.get("isEquipped")?.boolean }
    val isInInventory: Boolean? by lazy { requestParameters.get("isInInventory")?.boolean }
    val campaignId: Long? by lazy { requestParameters.get("campaignId")?.long }
    val gameCharacter: GameCharacterRequestParametersWrapper? by lazy {
        requestParameters.get("gameCharacter")?.let {
            GameCharacterRequestParametersWrapper(it)
        }
    }
    val statModifiers: MutableList<StatModifierRequestParametersWrapper>? by lazy {
        requestParameters.get("statModifier")?.paramList()?.mapTo(mutableListOf<StatModifierRequestParametersWrapper>()) {
            StatModifierRequestParametersWrapper(it)
        }
    }

}