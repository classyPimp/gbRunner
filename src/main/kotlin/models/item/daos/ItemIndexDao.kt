package models.item.daos

import org.jooq.generated.tables.Items
import orm.itemgeneratedrepository.ItemRecord
import models.item.Item
import org.jooq.generated.Tables.STAT_MODIFIERS

object ItemIndexDao {

    val table = Items.ITEMS

    fun forGameCharacterAsPrimiaryCharacterForPlayer(gameCharacterId: Long): MutableList<Item> {
        return ItemRecord.GET()
                .preload {
                    it.statModifiers {
                        it.where(STAT_MODIFIERS.IS_BLUEPRINT.isTrue)
                    }
                }
                .where(
                        table.OWNER_ID.eq(gameCharacterId)
                )
                .execute()
    }

    fun availableStartingGear(): MutableList<Item> {
        return ItemRecord.GET()
                .preload {
                    it.statModifiers()
                }
                .where(
                        table.IS_BLUEPRINT.isTrue
                )
                .execute()
    }

    fun asBlueprintForAdmin(): MutableList<Item> {
        return ItemRecord.GET()
                .preload {
                    it.statModifiers()
                }
                .where(
                        table.IS_BLUEPRINT.isTrue
                )
                .execute()
    }

    fun forAdminIndex(): MutableList<Item> {
        return ItemRecord.GET()
                .preload {
                    it.statModifiers()
                }
                .where(
                        table.IS_BLUEPRINT.isNull
                                .or(table.IS_BLUEPRINT.isFalse)
                                .and(
                                        table.OWNER_ID.isNull
                                )
                )
                .execute()
    }


}