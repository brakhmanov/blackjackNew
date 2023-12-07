package com.example.blackjacknew


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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

    fun onResetClick(view: View) {
        // Reset the game state
        player.clearHand()
        dealer.clearHand()
        deck.reset()

        // Deal new cards
        player.addToHand(deck.drawCard()!!)
        dealer.addToHand(deck.drawCard()!!)
        player.addToHand(deck.drawCard()!!)
        dealer.addToHand(deck.drawCard()!!)
        updateUI()
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
        playerHandTextView.text = "${player.name}'s Hand: ${player.hand.joinToString()}\nValue: ${player.calculateHandValue()}"
        dealerHandTextView.text = "Dealer's Hand: ${dealer.hand.joinToString()}\nValue: ${dealer.calculateHandValue()}"
    }

    private fun determineWinner() {
        // To detemine the winner and display the result

        val playerValue = player.calculateHandValue()
        val dealerValue = dealer.calculateHandValue()

        when {
            playerValue > 21 -> showMessage("Player busts! Dealer wins!")
            dealerValue > 21 -> showMessage("Dealer busts! Player wins!")
            playerValue == 21 && player.hand.size == 2 -> showMessage("Blackjack! Player wins!")
            dealerValue == 21 && dealer.hand.size == 2 -> showMessage("Blackjack! Dealer wins!")
            playerValue > dealerValue -> showMessage("Player wins!")
            playerValue < dealerValue -> showMessage("Dealer wins!")
            else -> showMessage("It's a tie!")
        }
    }

    private fun showMessage(message: String) {
        // display the winner message in a textView
    }

    private fun checkPlayerStatus() {
        val playerValue = player.calculateHandValue()
        if(playerValue == 21) {
            showMessage("Blackjack! Player wins!")
        } else if(playerValue > 21) {
            showMessage("Player busts! Dealer wins!")
        }
    }

}