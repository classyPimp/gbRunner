package models.word

import models.gift.Gift
import org.jooq.DSLContext
import org.jooq.generated.Tables.GIFTS
import org.jooq.generated.Tables.WORDS
import orm.giftgeneratedrepository.GiftRecord
import orm.utils.TransactionRunner
import orm.wordgeneratedrepository.WordRecord

/**
 * Created by Муса on 21.06.2018.
 */
object PredefinedWordManager {

    val predefinedWords = mutableListOf<Word>(
            Word().also {
                it.name = "Artifice"
                it.description = "some description"
                it.gifts = mutableListOf(
                        Gift().also {
                            it.name = "Faultless repair"
                            it.description = "gift description"
                            it.category = Gift.Categories.LESSER_GIFT.toString()
                        },
                        Gift().also {
                            it.name = "The Maker's eyes"
                            it.description =  "gift description"
                            it.category = Gift.Categories.GREATER_GIFT.toString()
                        }
                )
            },
            Word().also {
                it.name = "Beasts"
                it.description = "some description"
                it.gifts = mutableListOf(
                        Gift().also {
                            it.name = "Distant Howl"
                            it.description = "gift description"
                            it.category = Gift.Categories.LESSER_GIFT.toString()
                        },
                        Gift().also {
                            it.name = "Conquer the beast within"
                            it.description = "gift description"
                            it.category = Gift.Categories.GREATER_GIFT.toString()
                        }
                )
            },
            Word().also {
                it.name = "Bow"
                it.description = "some description"
                it.gifts = mutableListOf(
                        Gift().also {
                            it.name = "Bar the red dessence"
                            it.description = "gift description"
                            it.category = Gift.Categories.LESSER_GIFT.toString()
                        },
                        Gift().also {
                            it.name = "The inexorable shaft"
                            it.description = "gift description"
                            it.category = Gift.Categories.GREATER_GIFT.toString()
                        }
                )
            }
    )

    fun ensurePredefinedWordsAndGiftsArePersisted() {
        val predefinedWordsByNameMap = mutableMapOf<String, Word>()
        predefinedWords.forEach {
            predefinedWordsByNameMap[it.name!!] = it
        }

        val existingWords = WordRecord.GET()
                .where(
                        WORDS.NAME.`in`(predefinedWords.mapTo(mutableListOf<String>()){
                            it.name!!
                        })
                ).execute()

        val absentWords = predefinedWordsByNameMap.keys - existingWords.mapTo(mutableListOf<String>()) {it.name!!}

        TransactionRunner.run {tx ->
            absentWords.forEach {
                predefinedWordsByNameMap.get(it).also {
                    it!!.record.save(tx.inTransactionDsl)
                }
            }
        }

        existingWords.forEach {
            val predefinedWord = predefinedWordsByNameMap[it.name!!]
            predefinedWord!!.id = it.id!!
        }

        TransactionRunner.run {
            val tx = it.inTransactionDsl
            predefinedWords.forEach {
                ensurePredefinedGiftsArePersisted(it.id!!, it.gifts!!, tx)
            }
        }
    }

    private fun ensurePredefinedGiftsArePersisted(wordId: Long, gifts: MutableList<Gift>, instransactionDsl: DSLContext) {
        val predefinedGiftsByNameMap = mutableMapOf<String, Gift>()
        gifts.forEach {
            predefinedGiftsByNameMap[it.name!!] = it
        }

        val existingGifts = GiftRecord.GET()
                .where(GIFTS.NAME.`in`(predefinedGiftsByNameMap.keys))
                .execute()

        val absentNames = predefinedGiftsByNameMap.keys - existingGifts.map { it.name!! }

        absentNames.forEach {
            val gift = predefinedGiftsByNameMap[it]!!
            gift.wordId = wordId
            gift.record.save(instransactionDsl)
        }
    }

}