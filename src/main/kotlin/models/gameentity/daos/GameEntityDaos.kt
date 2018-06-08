package models.gameentity.daos

import org.jooq.generated.tables.GameEntities
import models.gameentity.daos.GameEntityShowDao
import models.gameentity.daos.GameEntityIndexDao
import models.gameentity.daos.GameEntityEditDao
import models.gameentity.daos.GameEntityUpdateDao
import models.gameentity.daos.GameEntityDestroyDao

object GameEntityDaos {

    val show = GameEntityShowDao

    val index = GameEntityIndexDao

    val edit = GameEntityEditDao

    val update = GameEntityUpdateDao

    val destroy = GameEntityDestroyDao

}