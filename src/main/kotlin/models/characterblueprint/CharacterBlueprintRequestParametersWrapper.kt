package models.characterblueprint

import utils.requestparameters.IParam

import java.sql.Timestamp

import models.campaign.CampaignRequestParametersWrapper
import models.genericgenericlink.GenericGenericLinkRequestParametersWrapper

class CharacterBlueprintRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val specificForCampaignId: Long? by lazy { requestParameters.get("specificForCampaignId")?.long }
    val strength: Int? by lazy { requestParameters.get("strength")?.int }
    val level: Int? by lazy { requestParameters.get("level")?.int }
    val dexterity: Int? by lazy { requestParameters.get("dexterity")?.int }
    val constitution: Int? by lazy { requestParameters.get("constitution")?.int }
    val wisdom: Int? by lazy { requestParameters.get("wisdom")?.int }
    val intelligence: Int? by lazy { requestParameters.get("intelligence")?.int }
    val charisma: Int? by lazy { requestParameters.get("charisma")?.int }
    val hardinessBase: Int? by lazy { requestParameters.get("hardinessBase")?.int }
    val evasionBase: Int? by lazy { requestParameters.get("evasionBase")?.int }
    val spiritBase: Int? by lazy { requestParameters.get("spiritBase")?.int }
    val hitpointsMaximum: Int? by lazy { requestParameters.get("hitpointsMaximum")?.int }
    val effortTotal: Int? by lazy { requestParameters.get("effortTotal")?.int }
    val influenceTotal: Int? by lazy { requestParameters.get("influenceTotal")?.int }
    val dominionTotal: Int? by lazy { requestParameters.get("dominionTotal")?.int }
    val currentWealth: Int? by lazy { requestParameters.get("currentWealth")?.int }
    val dominionEarnedPerMonth: Int? by lazy { requestParameters.get("dominionEarnedPerMonth")?.int }
    val description: String? by lazy { requestParameters.get("description")?.string }
    val specificForCampaign: CampaignRequestParametersWrapper? by lazy {
        requestParameters.get("specificForCampaign")?.let {
            CampaignRequestParametersWrapper(it)
        }
    }
    val linksToCharacters: MutableList<GenericGenericLinkRequestParametersWrapper>? by lazy {
    requestParameters.get("linksToCharacters")?.paramList()?.let {
        it.mapTo(mutableListOf<GenericGenericLinkRequestParametersWrapper>()) {
            GenericGenericLinkRequestParametersWrapper(it)
        }
    }
    }


}