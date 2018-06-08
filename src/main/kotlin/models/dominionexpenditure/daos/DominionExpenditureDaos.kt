package models.dominionexpenditure.daos

import org.jooq.generated.tables.DominionExpenditures
import models.dominionexpenditure.daos.DominionExpenditureShowDao
import models.dominionexpenditure.daos.DominionExpenditureIndexDao
import models.dominionexpenditure.daos.DominionExpenditureEditDao
import models.dominionexpenditure.daos.DominionExpenditureUpdateDao
import models.dominionexpenditure.daos.DominionExpenditureDestroyDao

object DominionExpenditureDaos {

    val show = DominionExpenditureShowDao

    val index = DominionExpenditureIndexDao

    val edit = DominionExpenditureEditDao

    val update = DominionExpenditureUpdateDao

    val destroy = DominionExpenditureDestroyDao

}