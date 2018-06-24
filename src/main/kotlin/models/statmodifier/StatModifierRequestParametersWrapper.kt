package models.statmodifier

import models.statmodifier.StatModifier
import utils.requestparameters.IParam

import java.sql.Timestamp

import org.jooq.generated.tables.StatModifiers
import models.gamecharacter.GameCharacter
import models.gamecharacter.GameCharacterRequestParametersWrapper
import models.item.Item
import models.item.ItemRequestParametersWrapper

class StatModifierRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val category: String? by lazy { requestParameters.get("category")?.string }
    val subCategory: String? by lazy { requestParameters.get("subCategory")?.string }
    val gameCharacterId: Long? by lazy { requestParameters.get("gameCharacterId")?.long }
    val itemId: Long? by lazy { requestParameters.get("itemId")?.long }
    val isBlueprint: Boolean? by lazy { requestParameters.get("isBlueprint")?.boolean }
    val value: Int? by lazy { requestParameters.get("value")?.int }
    val nonStandartValue: String? by lazy { requestParameters.get("nonStandardValue")?.string }
    val gameCharacter: GameCharacterRequestParametersWrapper? by lazy {
        requestParameters.get("gameCharacter")?.let {
            GameCharacterRequestParametersWrapper(it)
        }
    }
    val item: ItemRequestParametersWrapper? by lazy {
        requestParameters.get("item")?.let {
            ItemRequestParametersWrapper(it)
        }
    }


}