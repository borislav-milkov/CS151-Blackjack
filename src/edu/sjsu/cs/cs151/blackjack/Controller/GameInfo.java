package edu.sjsu.cs.cs151.blackjack.Controller;

import java.util.List;

import edu.sjsu.cs.cs151.blackjack.Model.Card;
import edu.sjsu.cs.cs151.blackjack.Model.Card.Rank;
import edu.sjsu.cs.cs151.blackjack.Model.Card.Suit;
import edu.sjsu.cs.cs151.blackjack.Model.Dealer;
import edu.sjsu.cs.cs151.blackjack.Model.Gambler;
import edu.sjsu.cs.cs151.blackjack.Model.Hand;
import edu.sjsu.cs.cs151.blackjack.Model.OldModel;
import edu.sjsu.cs.cs151.blackjack.Model.Model;
import edu.sjsu.cs.cs151.blackjack.Model.Player;

/**
 * GameInfo records various information about the current state of the game at any given moment.
 */
public class GameInfo {
	private Player user;
	private Dealer dealer;
	
	private int playerBalance;
	private String playerName;
	private String gameTally;
	private List<String> playerCards;
	private List<String> dealerCards;
	private boolean dealerFaceUp;
	private boolean playerBust;
	private boolean dealerBust;
	private Gambler winner;
	private boolean gameOver;
	
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
		playerBet = user.getBet();
		playerBust = user.isBust();
		dealerBust = updatedDealer.isBust();
		// Update game and win status
		pot = 2*playerBet;
		dealerFaceUp = updatedDealer.getTurn();
		winner = model.findWinner();
	}
	
	// GETTERS // 
	
	public Player getPlayer() {
		return user;
	}
	
	public Dealer getDealer() {
		return dealer;
	}
	
	public int getPlayerBalance() {
		return playerBalance;
	}
	
	public int getPot() {
		return pot;
	}
	
	public List<String> getPlayerCards() {
		return playerCards;
	}
	
	public List<String> getDealerCards() {
		return dealerCards;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}
	
	public int getDealerScore() {
		return dealerScore;
	}
	
	public int getDealersHiddenScore() {
		return dealerHiddenScore;
	}
	
	public int getBalance() {
		return playerBalance;
	}
	
	public boolean getDealerFaceUp() {
		return dealerFaceUp;
	}
	
	public boolean isPlayerBust() {
		return playerBust;
	}
	
	public boolean isDealerBust() {
		return dealerBust;
	}
	
	public String getWinner() {
		if(this.winner == null) {
			return null;
		}
		return this.winner.toString();
	}
	
	public Rank[] getCardRanks() {
		return Card.Rank.values();
	}
	
	public Suit[] getCardSuits() {
		return Card.Suit.values();
	}
	
}
