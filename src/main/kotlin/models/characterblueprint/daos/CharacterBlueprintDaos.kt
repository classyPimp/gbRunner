package models.characterblueprint.daos

import org.jooq.generated.tables.CharacterBlueprints

object CharacterBlueprintDaos {

    val show = CharacterBlueprintShowDao

    val index = CharacterBluePrintIndexDao

    val edit = CharacterBlueprintEditDao

    val update = CharacterBluePrintUpdateDao

    val destroy = CharacterBlueprintDestroyDao

}