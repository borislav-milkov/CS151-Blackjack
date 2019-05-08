package edu.sjsu.cs.cs151.blackjack.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ModelV2 {
	private  Pot pot; // The betting pot
	private  Deck gameDeck; // Single deck for each game
	private  List<Gambler> players; // Players contains ALL in-game players, including the dealer
	private  Player user; // User interacting with the game
	private  Dealer dealer; // Opponent of the player
	

	private static boolean playerTurn;
	private static boolean dealerTurn;

	public ModelV2() {
		setupGame();
	}

	private void setupGame() {
		// Initialize player, dealer and relevant objects
		gameDeck = new Deck();
		players = new ArrayList<>();
		dealer = new Dealer();
		pot = new Pot();

		// shuffle cards
		gameDeck.shuffle();

		// TODO: Probably remove player name eventually
		user = new Player("");

		// Add all in-game players to list
		players.add(user);
		players.add(dealer);

		// Player always goes first
		playerTurn = true;
	}

	public void deal() {
		// dealer deals 2 cards to user and itself
		for (Gambler player : players)
			dealer.dealCards(gameDeck, player, 2);

		// TODO: update GameInfo here???
	}

	//TODO: subtract the bet currency from player's current balance
	public void bet(int value) {
		for (Gambler player : players)
			player.putInPot(pot, value);
	}

/*	private void play() {
		// End of players turn, so dealer plays
		if (!playerTurn) {
			while (dealerTurn) {
				boolean hit = dealer.willHit();
				if (hit) {
					dealer.dealCards(gameDeck, dealer, 1);
					if (dealer.isBust()) // end of the round if dealer busts
						dealerTurn = false;
				} else
					dealerTurn = false;
			}
		}

	}*/

	public void hit(Gambler player) {
		dealer.dealCards(gameDeck, player, 1);
	}

	public void stand() {
		playerTurn = false;
		dealerTurn = true;
		
		while (dealer.willHit()) {
			dealer.dealCards(gameDeck, dealer, 1);
		}

	}

	public void doubleDown() {
		// TODO: double the player's bet, hit, then stand
	}
	
	public void findWinner() {
		// TODO: implement findWinner
	}

	public boolean isGameOver() {
		if (!playerTurn && !dealerTurn)
			return true;
		else
			return false;
	}
	
	public boolean isPlayerTurn() {
		return playerTurn;
	}

	public ModelV2 restart() {
		return new ModelV2();
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
		return user.getChips();
	}
	
	public int getPot() {
		return pot.getValue();
	}
	
	public List<Card> getPlayerHand() {
		Hand user = this.user.getHand();
		return user.toList();
	}
	
	public List<Card> getDealerHand() {
		Hand dealer = this.dealer.getHand();
		return dealer.toList();
	}
	
	public int getPlayerScore() {
		return user.getHandValue();
	}
	
	public int getDealerScore() {
		return dealer.getHandValue();
	}
	
	public boolean isDealerTurn() {
		return dealerTurn;
	}

}
