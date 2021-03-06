package edu.sjsu.cs.cs151.blackjack.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model of the game handles most of the game logic and interactions between 
 * dealer and player.
 */
public class Model {

	/**
	 * Constructs a model and sets up the game.
	 */
	public Model() {
		setupGame();
	}

	/**
	 * Sets up the preliminary game logic.
	 */
	private void setupGame() {
		// Initialize player, dealer and relevant objects
		gameDeck = new Deck();
		players = new ArrayList<>();
		dealer = new Dealer();
		pot = new Pot();

		// shuffle cards
		gameDeck.shuffle();

		// Set player name
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
	 * @return The winner of the round.
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
	
	/**
	 * Constructs a new model for playing a brand new game.
	 * @return	new model
	 */
	public Model restart() {
		return new Model();
	}
	
	/**
	 * Clears players' hands and clears the winner. Re-shuffles deck. Allows for new round to commence.
	 * */
	public void newRound() {
		gameDeck = new Deck();
		gameDeck.shuffle();
		user.clearHand();
		dealer.clearHand();
		winner = null;
		user.startTurn();
	}
	
	
	
	/**
	 * Gets a reference to the player.
	 * @return	direct reference to user
	 */
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
	
	/**
	 * returns the hand the player has
	 * @return a list of strings representing the player's cards
	 * */
	public List<String> getPlayerHand() {
		Hand user = this.user.getHand();
		return user.toList();
	}
	
	/**
	 * returns the hand the dealer has
	 * @return a list of strings representing the dealer's cards
	 * */
	public List<String> getDealerHand() {
		Hand dealer = this.dealer.getHand();
		return dealer.toList();
	}
	
	/**
	 * returns the player's score
	 * @return the player's score
	 * */
	public int getPlayerScore() {
		return user.getHandValue();
	}
	
	/**
	 * returns the dealer's score
	 * @return the dealer's score
	 * */
	public int getDealerScore() {
		return dealer.getHandValue();
	}
	
	/**
	 * returns the score of the dealer not counting his hidden card
	 * @return the dealer known score, from visible card
	 * */
	public int getHiddenScore() {
		// The first card is always the hidden card
		if(dealer.getHand().getFirst() == null) {
			return 0;
		}
		int hiddenCardValue = dealer.getHand().getFirst().getRankAsInt();
		return dealer.getHandValue() - hiddenCardValue;
	}
	
	/**
	 * returns whether it's the dealer's turn.
	 * @return is it the dealer's turn or not
	 * */
	public boolean isDealerTurn() {
		return dealer.getTurn();
	}
	
	private  Pot pot; 				// The betting pot
	private  Deck gameDeck; 		// Single deck for each game
	private  List<Gambler> players; // Players contains ALL in-game players, including the dealer
	private  Player user; 			// User interacting with the game
	private  Dealer dealer; 		// Opponent of the player
	private Gambler winner;			// Winner of the current roundj

}
