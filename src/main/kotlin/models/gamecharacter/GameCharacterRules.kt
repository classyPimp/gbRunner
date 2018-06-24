package models.gamecharacter

import models.statmodifier.StatModifier

object GameCharacterRules {

    const val SAVING_THROW_BASE_VALUE: Int = 15
    const val ATTRIBUTE_CHECK_BASE_VALUE: Int = 21
    const val MAX_ATTRIBUTE_VALUE_AT_CHARACTER_CREATION: Int = 18

    class DiceBonusOrPenalty {
        var value: Int? = null
        fun noBonusOrPenalty() {
            this.value = 0
        }
        fun standardBonus() {
            this.value = 4
        }
        fun standardPenalty() {
            this.value = -4
        }
        fun heroicPenalty() {
            this.value = -8
        }

    }

    fun makeAttributeCheck(attributeValue: Int, diceValue: Int, diceBonusOrPenalty: DiceBonusOrPenalty): Boolean {
        return (diceValue + diceBonusOrPenalty.value!!) > (ATTRIBUTE_CHECK_BASE_VALUE - attributeValue)
    }

    fun computeBaseAttributeModifier(attributeValue: Int): Int {
        when (attributeValue) {
            in 0..3 -> {
               return -3
            }
            in 4..5 -> {
                return -2
            }
            in 6..8 -> {
                return -1
            }
            in 9..12 -> {
                return 0
            }
            in 13..15 -> {
                return 1
            }
            in 16..17 -> {
                return 2
            }
            18 -> {
                return 3
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }

    fun makeHardinessSavingThrow(character: GameCharacter, diceValue: Int): Boolean {
        val strengthModifier = computeBaseAttributeModifier(character.strength!!)
        val constitutionModifier =  computeBaseAttributeModifier(character.constitution!!)
        if (strengthModifier >= constitutionModifier) {
            return (diceValue > SAVING_THROW_BASE_VALUE - strengthModifier)
        } else {
            return diceValue > SAVING_THROW_BASE_VALUE - constitutionModifier
        }
    }

    fun makeEvasionSavingThrow(character: GameCharacter, diceValue: Int): Boolean {
        val dexterityModifier = computeBaseAttributeModifier(character.dexterity!!)
        val intelligenceModifier = computeBaseAttributeModifier(character.intelligence!!)
        if (dexterityModifier >= intelligenceModifier) {
            return diceValue > SAVING_THROW_BASE_VALUE - dexterityModifier
        } else {
            return diceValue > SAVING_THROW_BASE_VALUE- intelligenceModifier
        }
    }

    fun makeWisdomSavingThrow(character: GameCharacter, diceValue: Int): Boolean {
        val charismaModifier = computeBaseAttributeModifier(character.charisma!!)
        val wisdomModifier =  computeBaseAttributeModifier(character.wisdom!!)
        if (charismaModifier >= wisdomModifier) {
            return diceValue > SAVING_THROW_BASE_VALUE - charismaModifier
        } else {
            return diceValue > SAVING_THROW_BASE_VALUE - wisdomModifier
        }
    }

    fun computeDamageTaken(damageRolled: Int): Int {
        if (damageRolled < 0) {
            return 0
        }
        return when (damageRolled) {
            in 0..1 -> {
                0
            }
            in 2..5 -> {
                1
            }
            in 6..9 -> {
                2
            }
            else -> {
                4
            }
        }
    }

    fun checkIfAttackSuccess(attacker: GameCharacter, foe: GameCharacter, toHitDice: Int): Boolean {
        val attackCategory = StatModifier.Categories.ATTACK.toString()
        val armorCategory = StatModifier.Categories.ARMOR.toString()

        val attackStatModifiers = attacker.statModifiers?.filter {
            it.category == attackCategory
        }

        var toHitModifier = 0

        var foeAc = 0

        attackStatModifiers?.forEach {
            val subCategory = StatModifier.AttackSubCategories.valueOf(it.category!!)
            when (subCategory) {
                StatModifier.AttackSubCategories.ATTACK_BONUS -> {
                    toHitModifier += it.value!!
                }
                StatModifier.AttackSubCategories.ADD_TO_RESULT_MODIFIER_OF_DEXTERITY -> {
                    toHitModifier += this.computeBaseAttributeModifier(attacker.dexterity!!)
                }
                StatModifier.AttackSubCategories.ADD_TO_RESULT_MODIFIER_OF_STRENGTH -> {
                    toHitModifier += this.computeBaseAttributeModifier(attacker.strength!!)
                }
                StatModifier.AttackSubCategories.ADD_TO_RESULT_MODIFIER_OF_WHICH_BETTER_STRENGTH_OR_DEXTERITY -> {
                    if (attacker.strength!! > attacker.dexterity!!) {
                        toHitModifier += computeBaseAttributeModifier(attacker.strength!!)
                    } else {
                        toHitModifier += computeBaseAttributeModifier(attacker.dexterity!!)
                    }
                }
                else -> {
                }
            }
        }

        val foeArmorStatModifiers = foe.statModifiers?.filter {
            it.category ==  armorCategory
        }

        foeArmorStatModifiers?.forEach {
            val category = StatModifier.ArmorSubCategories.valueOf(it.category!!)
            when (category) {
                StatModifier.ArmorSubCategories.BASE_AC -> {
                    foeAc += it.value!!
                }
                StatModifier.ArmorSubCategories.AC_BONUS_OR_PENALTY -> {
                    foeAc += it.value!!
                }
            }
        }

        return (toHitDice + toHitModifier) > foeAc

    }

    fun inflictDamage(attacker: GameCharacter, foe: GameCharacter, diceThrown: MutableList<Int>) {
        val attackCategory = StatModifier.Categories.ATTACK.toString()
        var damageBonus = 0
        var inflictedDamage = 0

        val attackStatModifiers = attacker.statModifiers?.filter {
            it.category == attackCategory
        }

        attackStatModifiers?.forEach {
            val subCategory = StatModifier.AttackSubCategories.valueOf(it.category!!)
            when (subCategory) {
                StatModifier.AttackSubCategories.ADD_TO_RESULT_MODIFIER_OF_DEXTERITY -> {
                    damageBonus += this.computeBaseAttributeModifier(attacker.dexterity!!)
                }
                StatModifier.AttackSubCategories.ADD_TO_RESULT_MODIFIER_OF_STRENGTH -> {
                    damageBonus += this.computeBaseAttributeModifier(attacker.strength!!)
                }
                StatModifier.AttackSubCategories.ADD_TO_RESULT_MODIFIER_OF_WHICH_BETTER_STRENGTH_OR_DEXTERITY -> {
                    if (attacker.strength!! > attacker.dexterity!!) {
                        damageBonus += computeBaseAttributeModifier(attacker.strength!!)
                    } else {
                        damageBonus += computeBaseAttributeModifier(attacker.dexterity!!)
                    }
                }
                else -> {
                }
            }
        }

        diceThrown.forEach {
            inflictedDamage += (computeDamageTaken(it) + damageBonus)
        }

        foe.hitpointsCurrent = foe.hitpointsCurrent!! - inflictedDamage

    }


}