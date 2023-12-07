package com.example.blackjacknew

class Player(val name: String) {
    val hand = mutableListOf<Card>()

    fun addToHand(card: Card) {
        hand.add(card)
    }

    fun calculateHandValue(): Int {
        var sum = 0
        var numAces = 0

        for (card in hand) {
            when (card.rank) {
                "A" -> {
                    sum += 11
                    numAces++
                }
                "K","Q","J" -> sum += 10
                else -> sum += card.rank.toInt()
            }
        }
        while(sum > 21 && numAces > 0) {
            sum -= 10
            numAces--
        }
        return sum
    }

    fun clearHand() {
        hand.clear()
    }

}