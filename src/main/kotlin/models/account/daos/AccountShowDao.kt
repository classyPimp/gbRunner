package models.account.daos

import org.jooq.generated.tables.Accounts
import orm.accountgeneratedrepository.AccountRecord
import models.account.Account

object AccountShowDao {

    val table = Accounts.ACCOUNTS

    fun existsWithSuchEmail(email: String): Boolean {
        val accounts = AccountRecord.GET()
                .where(table.EMAIL.eq(email))
                .execute()
        return accounts.isNotEmpty()
    }

}