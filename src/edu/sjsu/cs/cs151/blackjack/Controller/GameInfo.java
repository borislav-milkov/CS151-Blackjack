package edu.sjsu.cs.cs151.blackjack.Controller;

import java.util.List;

import edu.sjsu.cs.cs151.blackjack.Model.Card;
import edu.sjsu.cs.cs151.blackjack.Model.Dealer;
import edu.sjsu.cs.cs151.blackjack.Model.Gambler;
import edu.sjsu.cs.cs151.blackjack.Model.Hand;
import edu.sjsu.cs.cs151.blackjack.Model.Model;
import edu.sjsu.cs.cs151.blackjack.Model.ModelV2;
import edu.sjsu.cs.cs151.blackjack.Model.Player;

//TODO: Keep track of the current bet on the board
public class GameInfo {
	private Player user;
	private Dealer dealer;
	
	private int playerBalance;
	private String playerName;
	private String gameTally;
	private List<Card> playerCards;
	private List<Card> dealerCards;
	private boolean dealerFaceUp;
	
	private int dealerScore;
	private int playerScore;
	private int pot;
	
	//TODO: Add playerBet as a field, then add getPlayerBet() below
	private int playerBet;
	
	// Ctor initializes game info off of the current model
	public GameInfo(ModelV2 model) {
		user = model.getPlayer();
		dealer = model.getDealer();
		playerBalance = model.getPlayerBalance();
		playerCards = model.getPlayerHand();
		dealerCards = model.getDealerHand();
		playerScore = model.getPlayerScore();
		dealerScore = model.getDealerScore();
		pot = model.getPot();
		dealerFaceUp = false;
	}
	
	// Update the user & dealer info when they change
	public void update(Player updatedPlayer, Dealer updatedDealer) {
		// Update player
		user = updatedPlayer;
		playerBalance = updatedPlayer.getChips();
		playerCards = updatedPlayer.getHand().toList();
		playerScore = updatedPlayer.getHandValue();
		// Update dealer
		dealer = updatedDealer;
		dealerCards = updatedDealer.getHand().toList();
		dealerScore = updatedDealer.getHandValue();
	}
	
	// GETTERS //
	// These could be condensed into less functions probably // 
	
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
	
	public List<Card> getPlayerCards() {
		return playerCards;
	}
	
	public List<Card> getDealerCards() {
		return dealerCards;
	}
	
	public int getPlayerScore() {
		return playerScore;
	}
	
	public int getDealerScore() {
		return dealerScore;
	}
	
	public int getBalance() {
		return playerBalance;
	}
	
	public boolean getDealerFaceUp() {
		return dealerFaceUp;
	}
}
