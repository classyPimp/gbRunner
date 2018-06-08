package models.dominionexpenditure

import models.dominionexpenditure.DominionExpenditure
import utils.requestparameters.IParam

import java.sql.Timestamp

import org.jooq.generated.tables.GameCharacters
import models.gamecharacter.GameCharacter
import models.gamecharacter.GameCharacterRequestParametersWrapper

class DominionExpenditureRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val category: String? by lazy { requestParameters.get("category")?.string }
    val spentOn: String? by lazy { requestParameters.get("spentOn")?.string }
    val amountSpent: Int? by lazy { requestParameters.get("amountSpent")?.int }
    val gameCharacterId: Long? by lazy { requestParameters.get("gameCharacterId")?.long }
    val gameCharacter: GameCharacterRequestParametersWrapper? by lazy {
        requestParameters.get("gameCharacter")?.let {
            GameCharacterRequestParametersWrapper(it)
        }
    }


}