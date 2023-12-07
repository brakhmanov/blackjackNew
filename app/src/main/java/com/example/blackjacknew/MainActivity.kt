package com.example.blackjacknew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val player = Player("Player")
    private val dealer = Player("Dealer")
    private val deck = Deck()

    private lateinit var playerHandTextView: TextView
    private lateinit var dealerHandTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        playerHandTextView = findViewById(R.id.playerHandTextView)
        dealerHandTextView = findViewById(R.id.dealerHandTextView)


        // Initial deal
        player.addToHand(deck.drawCard()!!)
        dealer.addToHand(deck.drawCard()!!)
        player.addToHand(deck.drawCard()!!)
        dealer.addToHand(deck.drawCard()!!)

        updateUI()

    }

    fun onHitClick(view: View) {
        val card = deck.drawCard()
        if (card != null) {
            player.addToHand(card)
            updateUI()
        }
    }

    fun onStandClick(view: View) {
        dealerTurn()
        updateUI()
        determineWinner()
    }

    private fun dealerTurn() {
        while (dealer.calculateHandValue() < 17) {
            val card = deck.drawCard()
            if (card != null) {
                dealer.addToHand(card)
            }
        }
    }

    private fun updateUI() {

    }

}