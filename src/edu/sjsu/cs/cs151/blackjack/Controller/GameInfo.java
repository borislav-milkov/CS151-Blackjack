package edu.sjsu.cs.cs151.blackjack.Controller;

import java.util.List;

import edu.sjsu.cs.cs151.blackjack.Model.Card;
import edu.sjsu.cs.cs151.blackjack.Model.Card.Rank;
import edu.sjsu.cs.cs151.blackjack.Model.Card.Suit;
import edu.sjsu.cs.cs151.blackjack.Model.Dealer;
import edu.sjsu.cs.cs151.blackjack.Model.Gambler;
import edu.sjsu.cs.cs151.blackjack.Model.Model;
import edu.sjsu.cs.cs151.blackjack.Model.Player;

/**
 * GameInfo records various information about the current state of the game at any given moment.
 */
public class GameInfo {
	/* Data for Model */
	private Player user;
	private Dealer dealer;
	private int playerBalance;
	private List<String> playerCards;
	private List<String> dealerCards;
	private boolean dealerFaceUp;
	private boolean playerBust;
	private boolean dealerBust;
	private Gambler winner;
	private int dealerScore;
	private int dealerHiddenScore;
	private int playerScore;
	private int pot;
	private int playerBet;
	
	/**
	 * Ctor initializes GameInfo from a game model.
	 * @param model		blackjack model to extract information from
	 */
	public GameInfo(Model model) {
		user = model.getPlayer();
		dealer = model.getDealer();
		playerBalance = model.getPlayerBalance();
		playerCards = model.getPlayerHand();
		dealerCards = model.getDealerHand();
		playerScore = model.getPlayerScore();
		dealerScore = model.getDealerScore();
		dealerHiddenScore = model.getHiddenScore();
		playerBet = user.getBet();
		pot = model.getPot();
		dealerFaceUp = false;
		playerBust = false;
		dealerBust = false;
		winner = null;
	}
	
	/**
	 * Updates the currently stored information based on changes in model.
	 * @param updatedPlayer		newly changed player
	 * @param updatedDealer		newly changed dealer
	 * @param model				newly changed game model
	 */
	public void update(Player updatedPlayer, Dealer updatedDealer, Model model) {
		// Update player
		user = updatedPlayer;
		playerBalance = updatedPlayer.getChips();
		playerCards = updatedPlayer.getHand().toList();
		playerScore = updatedPlayer.getHandValue();
		// Update dealer
		dealer = updatedDealer;
		dealerCards = updatedDealer.getHand().toList();
		dealerScore = updatedDealer.getHandValue();
		dealerHiddenScore = model.getHiddenScore();
		playerBet = updatedPlayer.getBet();
		playerBust = updatedPlayer.isBust();
		dealerBust = updatedDealer.isBust();
		// Update game and win status
		pot = model.getPot();
		dealerFaceUp = updatedDealer.getFaceUp();
		winner = model.findWinner();
	}
	
	// GETTERS // 
	
	/**
	 * Accessor for player.
	 * @return	reference to player
	 */
	public Player getPlayer() {
		return user;
	}
	/**
	 * Accessor for dealer.
	 * @return	reference to dealer
	 */
	public Dealer getDealer() {
		return dealer;
	}
	/**
	 * Gets the player balance.
	 * @return	remaining balance of player
	 */
	public int getPlayerBalance() {
		return playerBalance;
	}
	/**
	 * Gets the pot's balance.
	 * @return	remaining balance of the pot
	 */
	public int getPot() {
		return pot;
	}
	/**
	 * Gets the player's cards.
	 * @return	list of cards in player's hand
	 */
	public List<String> getPlayerCards() {
		return playerCards;
	}
	/**
	 * Gets the dealer's cards.
	 * @return	list of cards in dealer's hand
	 */
	public List<String> getDealerCards() {
		return dealerCards;
	}
	/**
	 * Gets the player's current score.
	 * @return	player's hand value
	 */
	public int getPlayerScore() {
		return playerScore;
	}
	/**
	 * Gets the dealer's current score.
	 * @return	dealer's hand value
	 */
	public int getDealerScore() {
		return dealerScore;
	}
	/**
	 * Get's the dealer's current score, not including
	 * a first card that is face-down.
	 * @return	dealer's hand value, excluding 1st card
	 */
	public int getDealersHiddenScore() {
		return dealerHiddenScore;
	}
	/**
	 * Check if the dealer has revealed his first card or not.
	 * @return	true if dealer has revealed
	 * 			false if dealer has hidden card
	 */
	public boolean getDealerFaceUp() {
		return dealerFaceUp;
	}
	/**
	 * Check if player has busted.
	 * @return	true if player score > 21
	 * 			false if player score <= 21
	 */
	public boolean isPlayerBust() {
		return playerBust;
	}
	/**
	 * Check if dealer has busted.
	 * @return	true if dealer score > 21
	 * 			false if dealer score <= 21
	 */
	public boolean isDealerBust() {
		return dealerBust;
	}
	/**
	 * Gets the current winner of a round.
	 * @return	name of round winner
	 */
	public String getWinner() {
		if(this.winner == null) {
			return null;
		}
		return this.winner.toString();
	}
	/**
	 * Accessor for card ranks.
	 * @return	array of ranks in a deck of cards
	 */
	public Rank[] getCardRanks() {
		return Card.Rank.values();
	}
	/**
	 * Accessor for card suits.
	 * @return	array of suits in a deck of cards
	 */
	public Suit[] getCardSuits() {
		return Card.Suit.values();
	}
	
}
