package models.account.daos

import org.jooq.generated.tables.Accounts
import models.account.daos.AccountShowDao
import models.account.daos.AccountIndexDao
import models.account.daos.AccountEditDao
import models.account.daos.AccountUpdateDao
import models.account.daos.AccountDestroyDao

object AccountDaos {

    val show = AccountShowDao

    val index = AccountIndexDao

    val edit = AccountEditDao

    val update = AccountUpdateDao

    val destroy = AccountDestroyDao

}