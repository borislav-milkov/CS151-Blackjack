package edu.sjsu.cs.cs151.blackjack.Model;

import java.util.Scanner;

/**
 * The player class models the player for a blackjack table.
 */
public class Player implements Gambler {

	/**
	 * Constructs a Player with the specified name, 0 starting chips and
	 * an empty hand by default.
	 */
	public Player(String name) {
		this.name = name;
		this.chips = 1000;
		this.myTurn = true;
		playerHand = new Hand();
	}
	
	/**
	 * Initializes a Player with a name, desired amount of starting chips 
	 * and an empty hand by default.
	 * @param name		  alias of the player
	 * @param startChips  starting amount of chips
	 */
	public Player(String name, int startChips) {
		this.name = name;
		this.chips = startChips;
		this.myTurn = true;
		playerHand = new Hand();
	}
	
	/**
	 * Checks if a player wants to hit or not.
	 * @return  true if player wants to hit, false if player wants to stay
	 */
	@Override
	public boolean willHit() {
		return true;
	}
	
	/**
	 * Adds a card to the player's hand.
	 * @param card  card to add 
	 */
	public void addToHand(Card card) {
		playerHand.addCard(card);
	}
	
	/**
	 * Prints a formatted display of the cards in a player's hand.
	 */
	@Override
	public void displayHand() {
		System.out.println(name + "'s hand: " + playerHand.toString());
	}
	
	/**
	 * Gets the players current in-game bet.
	 * @return	players active bet
	 */
	public int getBet() {
		return this.bet;
	}
	
	/**
	 * Ends the players turn after it has finished.
	 */
	public void endTurn() {
		this.myTurn = false;
	}
	
	/**
	 * Stars the players turn.
	 */
	public void startTurn() {
		this.myTurn = true;
	}
	
	/* INTERFACE METHODS */
	public Hand getHand() {
		return this.playerHand;
	}
	
	public int getHandValue() {
		return this.playerHand.getValue();
	}
	
	public void clearHand() {
		this.playerHand = new Hand();
	}
	
	public int getChips() {
		return this.chips;
	}
	
	public void setChips(int chips) {
		this.chips = chips;
	}
	
	public void putInPot(Pot pot, int value) {
		this.bet = value;
		this.chips -= value;
		pot.addToPot(value);
	}
	
	public void winPot(Pot pot) {
		this.chips += pot.getValue();
		pot.resetValue();
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean getTurn() {
		return this.myTurn;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public boolean isBust() {
		if(getHandValue() > 21) {
			this.myTurn = false;
			return true;
		}
		return false;
	}
	
	private Hand playerHand;	// Player's set of cards
	private String name;		// Player's name
	private int chips;			// Amount of chips owned by player
	private int bet;			// Current active bet of the player
	private boolean myTurn;		// Keeps track of player's turn condition
}
