package models.item

import utils.requestparameters.IParam

import java.sql.Timestamp

import models.itemblueprint.ItemBlueprintRequestParametersWrapper
import models.gamecharacter.GameCharacterRequestParametersWrapper
import models.statmodifier.StatModifierRequestParametersWrapper
import orm.itemgeneratedrepository.ItemStringifiedNameForProperty

class ItemRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get(ItemStringifiedNameForProperty.id)?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get(ItemStringifiedNameForProperty.createdAt)?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get(ItemStringifiedNameForProperty.updatedAt)?.timestamp }
    val category: String? by lazy { requestParameters.get(ItemStringifiedNameForProperty.category)?.string }
    val subcategory: String? by lazy { requestParameters.get(ItemStringifiedNameForProperty.subcategory)?.string }
    val name: String? by lazy { requestParameters.get(ItemStringifiedNameForProperty.name)?.string }
    val description: String? by lazy { requestParameters.get(ItemStringifiedNameForProperty.description)?.string }
    val blueprintId: Long? by lazy { requestParameters.get(ItemStringifiedNameForProperty.blueprintId)?.long }
    val isBlueprint: Boolean? by lazy { requestParameters.get(ItemStringifiedNameForProperty.isBlueprint)?.boolean }
    val isAbility: Boolean? by lazy { requestParameters.get(ItemStringifiedNameForProperty.isAbility)?.boolean }
    val ownerId: Long? by lazy { requestParameters.get(ItemStringifiedNameForProperty.ownerId)?.long }
    val isEquipped: Boolean? by lazy { requestParameters.get(ItemStringifiedNameForProperty.isEquipped)?.boolean }
    val isInInventory: Boolean? by lazy { requestParameters.get(ItemStringifiedNameForProperty.isInInventory)?.boolean }
    val campaignId: Long? by lazy { requestParameters.get(ItemStringifiedNameForProperty.campaignId)?.long }
    val gameCharacter: GameCharacterRequestParametersWrapper? by lazy {
        requestParameters.get(ItemStringifiedNameForProperty.gameCharacter)?.let {
            GameCharacterRequestParametersWrapper(it)
        }
    }
    val statModifiers: MutableList<StatModifierRequestParametersWrapper>? by lazy {
        requestParameters.get(ItemStringifiedNameForProperty.statModifiers)?.paramList()?.mapTo(mutableListOf<StatModifierRequestParametersWrapper>()) {
            StatModifierRequestParametersWrapper(it)
        }
    }

}