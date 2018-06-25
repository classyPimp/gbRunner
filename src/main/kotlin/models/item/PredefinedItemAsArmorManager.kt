package models.item

import models.statmodifier.StatModifier
import org.jooq.generated.Tables.ITEMS
import orm.itemgeneratedrepository.ItemRecord
import orm.utils.TransactionRunner

object PredefinedItemAsArmorManager {

    fun ensurePredefinedItemsAsArmorArePersisted() {
        val predefinedArmorsByNameMap = mutableMapOf<String, Item>()
        predefinedArmors.forEach {
            predefinedArmorsByNameMap[it.name!!] = it
        }

        val existingArmors = ItemRecord.GET()
                .where(
                        ITEMS.NAME.`in`(
                                predefinedArmorsByNameMap.keys
                        )
                ).execute()

        val absentArmors = predefinedArmorsByNameMap.keys - existingArmors.map { it.name!! }

        TransactionRunner.run {
            val tx = it.inTransactionDsl
            absentArmors.forEach {
                predefinedArmorsByNameMap[it]!!.also {
                    it.record.saveCascade(tx)
                }
            }
        }

    }

    val predefinedArmors = mutableListOf<Item>(
            Item().also {
                it.name = "None"
                it.category = Item.Categories.ARMOR.toString()
                it.subcategory = Item.ArmorSubCategories.NONE.toString()
                it.isBlueprint = true
                it.isAbility = false
                it.statModifiers = mutableListOf(
                        StatModifier().also {
                            it.category = StatModifier.Categories.ARMOR.toString()
                            it.subCategory = StatModifier.ArmorSubCategories.BASE_AC.toString()
                            it.value = 9
                        }
                )
            },
            Item().also {
                it.name = "Light"
                it.category = Item.Categories.ARMOR.toString()
                it.subcategory = Item.ArmorSubCategories.LIGHT.toString()
                it.isBlueprint = true
                it.isAbility = false
                it.statModifiers = mutableListOf(
                        StatModifier().also {
                            it.category = StatModifier.Categories.ARMOR.toString()
                            it.subCategory = StatModifier.ArmorSubCategories.BASE_AC.toString()
                            it.value = 7
                        }
                )
            },
            Item().also {
                it.name = "Medium"
                it.category = Item.Categories.ARMOR.toString()
                it.subcategory = Item.ArmorSubCategories.MEDIUM.toString()
                it.isBlueprint = true
                it.isAbility = false
                it.statModifiers = mutableListOf(
                        StatModifier().also {
                            it.category = StatModifier.Categories.ARMOR.toString()
                            it.subCategory = StatModifier.ArmorSubCategories.BASE_AC.toString()
                            it.value = 5
                        }
                )
            },
            Item().also {
                it.name = "Shield"
                it.category = Item.Categories.ARMOR.toString()
                it.subcategory = Item.ArmorSubCategories.SHIELD.toString()
                it.isBlueprint = true
                it.isAbility = false
                it.statModifiers = mutableListOf(
                        StatModifier().also {
                            it.category = StatModifier.Categories.ARMOR.toString()
                            it.subCategory = StatModifier.ArmorSubCategories.AC_BONUS_OR_PENALTY.toString()
                            it.value = -1
                        }
                )
            }
    )

}