package models.item

import models.statmodifier.StatModifier

object PredefinedItemsAsWeaponManager {

    val predefinedWeapons = mutableListOf<Item>(
            Item().also {
                it.name = "Light"
                it.isBlueprint = true
                it.category = Item.Categories.WEAPON.toString()
                it.subcategory = Item.WeaponSubCategories.LIGHT.toString()
                it.isBlueprint = true
                it.isAbility = false
                it.statModifiers = mutableListOf(
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.ATTACK_DICE_COUNT.toString()
                            it.isBlueprint = true
                            it.value = 1
                        },
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.PER_DICE_DAMAGE.toString()
                            it.value = 6
                        },
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.ADD_TO_RESULT_MODIFIER_OF_WHICH_BETTER_STRENGTH_OR_DEXTERITY.toString()
                        }
                )

            },
            Item().also {
                it.name = "Unarmed"
                it.isBlueprint = true
                it.category = Item.Categories.WEAPON.toString()
                it.subcategory = Item.WeaponSubCategories.UNARMED.toString()
                it.isBlueprint = true
                it.isAbility = false
                it.statModifiers = mutableListOf(
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.ATTACK_DICE_COUNT.toString()
                            it.isBlueprint = true
                            it.value = 1
                        },
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.PER_DICE_DAMAGE.toString()
                            it.value = 2
                        },
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.ADD_TO_RESULT_MODIFIER_OF_WHICH_BETTER_STRENGTH_OR_DEXTERITY.toString()
                        }
                )

            },
            Item().also {
                it.name = "Medium"
                it.isBlueprint = true
                it.category = Item.Categories.WEAPON.toString()
                it.subcategory = Item.WeaponSubCategories.MEDIUM.toString()
                it.isBlueprint = true
                it.isAbility = false
                it.statModifiers = mutableListOf(
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.ATTACK_DICE_COUNT.toString()
                            it.isBlueprint = true
                            it.value = 1
                        },
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.PER_DICE_DAMAGE.toString()
                            it.value = 8
                        },
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.ADD_TO_RESULT_MODIFIER_OF_STRENGTH.toString()
                        }
                )
            },
            Item().also {
                it.name = "Heavy"
                it.isBlueprint = true
                it.category = Item.Categories.WEAPON.toString()
                it.subcategory = Item.WeaponSubCategories.HEAVY.toString()
                it.isBlueprint = true
                it.isAbility = false
                it.statModifiers = mutableListOf(
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.ATTACK_DICE_COUNT.toString()
                            it.isBlueprint = true
                            it.value = 1
                        },
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.PER_DICE_DAMAGE.toString()
                            it.value = 10
                        },
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.ADD_TO_RESULT_MODIFIER_OF_STRENGTH.toString()
                        }
                )

            },
            Item().also {
                it.name = "One handed ranged"
                it.isBlueprint = true
                it.category = Item.Categories.WEAPON.toString()
                it.subcategory = Item.WeaponSubCategories.ONE_HAND_RANGED.toString()
                it.isBlueprint = true
                it.isAbility = false
                it.statModifiers = mutableListOf(
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.ATTACK_DICE_COUNT.toString()
                            it.isBlueprint = true
                            it.value = 1
                        },
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.PER_DICE_DAMAGE.toString()
                            it.value = 6
                        },
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.ADD_TO_RESULT_MODIFIER_OF_DEXTERITY.toString()
                        }
                )

            },
            Item().also {
                it.name = "Two handed ranged"
                it.isBlueprint = true
                it.category = Item.Categories.WEAPON.toString()
                it.subcategory = Item.WeaponSubCategories.TWO_HAND_RANGED.toString()
                it.isBlueprint = true
                it.isAbility = false
                it.statModifiers = mutableListOf(
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.ATTACK_DICE_COUNT.toString()
                            it.isBlueprint = true
                            it.value = 1
                        },
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.PER_DICE_DAMAGE.toString()
                            it.value = 8
                        },
                        StatModifier().also {
                            it.category = StatModifier.Categories.ATTACK.toString()
                            it.subCategory = StatModifier.AttackSubCategories.ADD_TO_RESULT_MODIFIER_OF_DEXTERITY.toString()
                        }
                )

            }
    )

}