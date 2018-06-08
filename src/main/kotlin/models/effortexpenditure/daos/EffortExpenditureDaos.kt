package models.effortexpenditure.daos

import org.jooq.generated.tables.EffortExpenditures
import models.effortexpenditure.daos.EffortExpenditureShowDao
import models.effortexpenditure.daos.EffortExpenditureIndexDao
import models.effortexpenditure.daos.EffortExpenditureEditDao
import models.effortexpenditure.daos.EffortExpenditureUpdateDao
import models.effortexpenditure.daos.EffortExpenditureDestroyDao

object EffortExpenditureDaos {

    val show = EffortExpenditureShowDao

    val index = EffortExpenditureIndexDao

    val edit = EffortExpenditureEditDao

    val update = EffortExpenditureUpdateDao

    val destroy = EffortExpenditureDestroyDao

}