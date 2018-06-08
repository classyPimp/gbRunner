package models.influenceexpenditure.daos

import org.jooq.generated.tables.InfluenceExpenditures
import models.influenceexpenditure.daos.InfluenceExpenditureShowDao
import models.influenceexpenditure.daos.InfluenceExpenditureIndexDao
import models.influenceexpenditure.daos.InfluenceExpenditureEditDao
import models.influenceexpenditure.daos.InfluenceExpenditureUpdateDao
import models.influenceexpenditure.daos.InfluenceExpenditureDestroyDao

object InfluenceExpenditureDaos {

    val show = InfluenceExpenditureShowDao

    val index = InfluenceExpenditureIndexDao

    val edit = InfluenceExpenditureEditDao

    val update = InfluenceExpenditureUpdateDao

    val destroy = InfluenceExpenditureDestroyDao

}