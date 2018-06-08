package models.itemblueprint.daos

import org.jooq.generated.tables.ItemBlueprints
import models.itemblueprint.daos.ItemBlueprintShowDao
import models.itemblueprint.daos.ItemBlueprintIndexDao
import models.itemblueprint.daos.ItemBlueprintEditDao
import models.itemblueprint.daos.ItemBlueprintUpdateDao
import models.itemblueprint.daos.ItemBlueprintDestroyDao

object ItemBlueprintDaos {

    val show = ItemBlueprintShowDao

    val index = ItemBlueprintIndexDao

    val edit = ItemBlueprintEditDao

    val update = ItemBlueprintUpdateDao

    val destroy = ItemBlueprintDestroyDao

}