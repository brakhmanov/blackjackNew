package com.example.blackjacknew

class Deck {
    private val suits = listOf("Hearts", "Diamonds", "Clubs", "Spades")
    private val ranks = listOf("2","3","4","5","6","7","8","9","10","J","Q","K","A")
    private val cards = mutableListOf<Card>()

    init {
        suits.forEach {
            suit -> ranks.forEach {
                rank -> cards.add(Card(suit,rank))
        }
        }
        cards.shuffle()
    }

    fun drawCard(): Card? {
        return if (cards.isNotEmpty()) cards.removeAt(0)
        else null
    }

    fun reset () {
        cards.clear()
        suits.forEach {
            suit -> ranks.forEach {
                rank -> cards.add(Card(suit,rank))
        }
        }
        cards.shuffle()
    }
}