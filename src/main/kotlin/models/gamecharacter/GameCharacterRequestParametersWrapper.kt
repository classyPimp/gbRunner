package models.gamecharacter

import utils.requestparameters.IParam

import java.sql.Timestamp

import models.uploadedimage.UploadedImageRequestParametersWrapper
import models.characterblueprint.CharacterBlueprintRequestParametersWrapper
import models.genericgenericlink.GenericGenericLinkRequestParametersWrapper
import models.word.Word

class GameCharacterRequestParametersWrapper(val requestParameters: IParam) {

    val id: Long? by lazy { requestParameters.get("id")?.long }
    val createdAt: Timestamp? by lazy { requestParameters.get("createdAt")?.timestamp }
    val updatedAt: Timestamp? by lazy { requestParameters.get("updatedAt")?.timestamp }
    val campaignId: Long? by lazy { requestParameters.get("campaignId")?.long }
    val strength: Int? by lazy { requestParameters.get("strength")?.int }
    val level: Int? by lazy { requestParameters.get("level")?.int }
    val experiencePoints: Int? by lazy { requestParameters.get("experiencePoints")?.int }
    val dexterity: Int? by lazy { requestParameters.get("dexterity")?.int }
    val constitution: Int? by lazy { requestParameters.get("constitution")?.int }
    val wisdom: Int? by lazy { requestParameters.get("wisdom")?.int }
    val intelligence: Int? by lazy { requestParameters.get("intelligence")?.int }
    val charisma: Int? by lazy { requestParameters.get("charisma")?.int }
    val hardinessBase: Int? by lazy { requestParameters.get("hardinessBase")?.int }
    val evasionBase: Int? by lazy { requestParameters.get("evasionBase")?.int }
    val hitpointsMaximum: Int? by lazy { requestParameters.get("hitpointsMaximum")?.int }
    val hitpointsCurrent: Int? by lazy { requestParameters.get("hitpointsCurrent")?.int }
    val effortTotal: Int? by lazy { requestParameters.get("effortTotal")?.int }
    val effortAvailable: Int? by lazy { requestParameters.get("effortAvailable")?.int }
    val influenceTotal: Int? by lazy { requestParameters.get("influenceTotal")?.int }
    val influenceAvailable: Int? by lazy { requestParameters.get("influenceAvailable")?.int }
    val dominionTotal: Int? by lazy { requestParameters.get("dominionTotal")?.int }
    val dominionAvailable: Int? by lazy { requestParameters.get("dominionAvailable")?.int }
    val currentWealth: Int? by lazy { requestParameters.get("currentWealth")?.int }
    val dominionEarnedPerMonth: Int? by lazy { requestParameters.get("dominionEarnedPerMonth")?.int }
    val category: String? by lazy { requestParameters.get("category")?.string }
    val characterBlueprintId: Long? by lazy { requestParameters.get("characterBlueprintId")?.long }
    val characterSubCategory: String? by lazy { requestParameters.get("characterSubCategory")?.string }
    val name: String? by lazy { requestParameters.get("name")?.string }
    val description: String? by lazy { requestParameters.get("description")?.string }
    val uploadedImages: MutableList<UploadedImageRequestParametersWrapper>? by lazy {
    requestParameters.get("uploadedImages")?.paramList()?.let {
        it.mapTo(mutableListOf<UploadedImageRequestParametersWrapper>()) {
            UploadedImageRequestParametersWrapper(it)
        }
    }
    }
    val characterBlueprint: CharacterBlueprintRequestParametersWrapper? by lazy {
        requestParameters.get("characterBlueprint")?.let {
            CharacterBlueprintRequestParametersWrapper(it)
        }
    }
    val linksToUsers: MutableList<GenericGenericLinkRequestParametersWrapper>? by lazy {
        requestParameters.get("linksToUsers")?.paramList()?.let {
            it.mapTo(mutableListOf<GenericGenericLinkRequestParametersWrapper>()) {
                GenericGenericLinkRequestParametersWrapper(it)
            }
        }
    }

    val linksToWords: MutableList<GenericGenericLinkRequestParametersWrapper>? by lazy {
        requestParameters.get("linkstToWords")?.paramList()?.let {
            it.mapTo(mutableListOf<GenericGenericLinkRequestParametersWrapper>()) {
                GenericGenericLinkRequestParametersWrapper(it)
            }
        }
    }

    val linksToGifts: MutableList<GenericGenericLinkRequestParametersWrapper>? by lazy {
        requestParameters.get("linkstToGifts")?.paramList()?.let {
            it.mapTo(mutableListOf<GenericGenericLinkRequestParametersWrapper>()) {
                GenericGenericLinkRequestParametersWrapper(it)
            }
        }
    }



}