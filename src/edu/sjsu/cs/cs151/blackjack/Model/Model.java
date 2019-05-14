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

	}
	/**
	 * Deal 2 cards to each player on the table. This is the initial deal after betting.
	 * */
	public void deal() {
		// dealer deals 2 cards to user and itself
		for (Gambler player : players)
			dealer.dealCards(gameDeck, player, 2);

	}
	/**
	 * Each player puts a certain amount into the pot.
	 * @param value
	 * 	The amount to be put in the pot by each player.
	 * */
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
	
	/**
	 * Deal a card to the player.
	 * @param player
	 * 	The player to which the card will be dealt to.
	 * */
	public void hit(Gambler player) {
		if(player.getTurn()) {
			dealer.dealCards(gameDeck, player, 1);
		}else {
			return;
		}
	}

	/**
	 * Checks who won the round, if it is over.
	 * @return winner
	 * 	The winner of the round.
	 * */
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
	

	public Model restart() {
		return new Model();
	}
	
	/**
	 * Clears players' hands and clears the winner. Re-shuffles deck. Allows for new round to commence.
	 * */
	public void newRound() {
		gameDeck = new Deck();
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
	/**
	 * returns the dealer in the game
	 * @return the dealer
	 * */
	public Dealer getDealer() {
		return dealer;
	}
	
	/**
	 * returns the player's balance
	 * @return player's chips
	 * */
	public int getPlayerBalance() {
		return user.getChips();
	}
	
	/**
	 * returns the amount in the pot 
	 * @return the amount in the pot
	 * */
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

}
