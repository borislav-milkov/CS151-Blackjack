package edu.sjsu.cs.cs151.blackjack.Controller;

import java.util.List;

import edu.sjsu.cs.cs151.blackjack.Model.Card;
import edu.sjsu.cs.cs151.blackjack.Model.Gambler;
import edu.sjsu.cs.cs151.blackjack.Model.Hand;
import edu.sjsu.cs.cs151.blackjack.Model.Model;
import edu.sjsu.cs.cs151.blackjack.Model.ModelV2;

public class GameInfo {
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
		playerBalance = model.getPlayerBalance();
		playerCards = model.getPlayerHand();
		dealerCards = model.getDealerHand();
		playerScore = model.getPlayerScore();
		dealerScore = model.getDealerScore();
		pot = model.getPot();
	}
	
	// Update the user & dealer info when they change
	public GameInfo update() {
		//TODO: Implement update()
	}
	
	// GETTERS //
	
	
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
}
