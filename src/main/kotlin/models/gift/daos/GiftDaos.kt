package models.gift.daos

import org.jooq.generated.tables.Gifts
import models.gift.daos.GiftShowDao
import models.gift.daos.GiftIndexDao
import models.gift.daos.GiftEditDao
import models.gift.daos.GiftUpdateDao
import models.gift.daos.GiftDestroyDao

object GiftDaos {

    val show = GiftShowDao

    val index = GiftIndexDao

    val edit = GiftEditDao

    val update = GiftUpdateDao

    val destroy = GiftDestroyDao

}