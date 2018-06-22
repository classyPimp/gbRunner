package models.gamecharacter

object GameCharacterRules {

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

    val maxAttributeValueAtCharacterCreation: Int = 18

    fun attributeCheck(attributeValue: Int, diceValue: Int, diceBonusOrPenalty: DiceBonusOrPenalty): Boolean {
        return (diceValue + diceBonusOrPenalty.value!!) > (21 - attributeValue)
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

    fun hardinessSavingThrow(character: GameCharacter, diceValue: Int): Boolean {
        val strengthModifier = computeBaseAttributeModifier(character.strength!!)
        val constitutionModifier =  computeBaseAttributeModifier(character.constitution!!)
        if (strengthModifier >= constitutionModifier) {
            return (diceValue > 15 - strengthModifier)
        } else {
            return diceValue > 15 - constitutionModifier
        }
    }
}