package models.statmodifier.daos

import org.jooq.generated.tables.StatModifiers
import models.statmodifier.daos.StatModifierShowDao
import models.statmodifier.daos.StatModifierIndexDao
import models.statmodifier.daos.StatModifierEditDao
import models.statmodifier.daos.StatModifierUpdateDao
import models.statmodifier.daos.StatModifierDestroyDao

object StatModifierDaos {

    val show = StatModifierShowDao

    val index = StatModifierIndexDao

    val edit = StatModifierEditDao

    val update = StatModifierUpdateDao

    val destroy = StatModifierDestroyDao

}