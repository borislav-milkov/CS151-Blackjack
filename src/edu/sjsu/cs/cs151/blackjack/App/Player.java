package edu.sjsu.cs.cs151.blackjack.App;

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
		this.chips = 0;
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
		playerHand = new Hand();
	}
	
	/**
	 * Checks if a player wants to hit or not.
	 * @return  true if player wants to hit, false if player wants to stay
	 */
	@Override
	public boolean willHit() {
		//TODO: Keyboard could eventually be replaced with a button or other input
		System.out.println(name + ": 'hit' or 'stay'? ");
		Scanner keyboard = new Scanner(System.in);
	
		while (true) {
			String in = keyboard.nextLine();
			switch(in) {
			case "hit":
				keyboard.close();
				return true;
			case "stay": 
				keyboard.close();
				return false;
			default:
				System.out.println("Type 'hit' or 'stay': ");
			}
		}
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
	
	public Hand getHand() {
		return this.playerHand;
	}
	
	public int getHandValue() {
		return this.playerHand.getValue();
	}
	
	public int getChips() {
		return this.chips;
	}
	
	public void setChips(int chips) {
		this.chips = chips;
	}
	
	public String getName() {
		return this.name;
	}
	@Override
	public boolean isBust() {
		if(getHandValue() > 21) {
			return true;
		}
		return false;
	}
	
	private Hand playerHand;	// Player's set of cards
	private String name;		// Player's name
	private int chips;			// Amount of chips owned by player
}
