package models.gameevent.daos

import org.jooq.generated.tables.GameEvents
import models.gameevent.daos.GameEventShowDao
import models.gameevent.daos.GameEventIndexDao
import models.gameevent.daos.GameEventEditDao
import models.gameevent.daos.GameEventUpdateDao
import models.gameevent.daos.GameEventDestroyDao

object GameEventDaos {

    val show = GameEventShowDao

    val index = GameEventIndexDao

    val edit = GameEventEditDao

    val update = GameEventUpdateDao

    val destroy = GameEventDestroyDao

}