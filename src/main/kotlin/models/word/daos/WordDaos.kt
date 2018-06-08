package models.word.daos

import org.jooq.generated.tables.Words
import models.word.daos.WordShowDao
import models.word.daos.WordIndexDao
import models.word.daos.WordEditDao
import models.word.daos.WordUpdateDao
import models.word.daos.WordDestroyDao

object WordDaos {

    val show = WordShowDao

    val index = WordIndexDao

    val edit = WordEditDao

    val update = WordUpdateDao

    val destroy = WordDestroyDao

}