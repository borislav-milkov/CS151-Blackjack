package edu.sjsu.cs.cs151.blackjack.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Model {

	public Model() {
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
		user = new Player("You");

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

	}

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
		if(player.getTurn()) {
			dealer.dealCards(gameDeck, player, 1);
		}else {
			return;
		}
	}

	public void stand() {
		playerTurn = false;
		dealerTurn = true;

	}

	
	public Gambler findWinner() {
		if(!user.getTurn() && !dealer.getTurn()) {
			if(user.isBust()) {
				dealer.winPot(pot);
				return dealer;
			}else if(dealer.isBust()) {
				user.winPot(pot);
				return user;
			}else {
				winner =  user.getHandValue() < dealer.getHandValue() ? dealer : user;
				winner.winPot(this.pot);
				return winner;
			}
		}else {
			return null;
		}
	}
	
	
	public boolean isPlayerTurn() {
		return playerTurn;
	}
	
	public void dealerTurn() {
		dealerTurn = true;
	}
	
	public void endDealerTurn() {
		dealerTurn = false;
	}

	public Model restart() {
		return new Model();
	}
	
	public void newRound() {
		user.clearHand();
		dealer.clearHand();
		winner = null;
		user.startTurn();
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
	
	public List<String> getPlayerHand() {
		Hand user = this.user.getHand();
		return user.toList();
	}
	
	public List<String> getDealerHand() {
		Hand dealer = this.dealer.getHand();
		return dealer.toList();
	}
	
	public int getPlayerScore() {
		return user.getHandValue();
	}
	
	public int getDealerScore() {
		return dealer.getHandValue();
	}
	
	public int getHiddenScore() {
		// The first card is always the hidden card
		if(dealer.getHand().getFirst() == null) {
			return 0;
		}
		int hiddenCardValue = dealer.getHand().getFirst().getRankAsInt();
		return dealer.getHandValue() - hiddenCardValue;
	}
	
	public boolean isDealerTurn() {
		return dealer.getTurn();
	}
	
	private  Pot pot; // The betting pot
	private  Deck gameDeck; // Single deck for each game
	private  List<Gambler> players; // Players contains ALL in-game players, including the dealer
	private  Player user; // User interacting with the game
	private  Dealer dealer; // Opponent of the player
	private Gambler winner;
	

	private static boolean playerTurn;
	private static boolean dealerTurn;

}
