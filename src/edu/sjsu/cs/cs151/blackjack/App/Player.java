package edu.sjsu.cs.cs151.blackjack.App;

/**
 * The player class models the player for a blackjack table.
 */
public class Player {

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
	
	public Hand getHand() {
		return this.playerHand;
	}
	
	public int getHandValue() {
		return this.playerHand.getValue();
	}
	
	/**
	 * Adds a card to the player's hand.
	 * @param card  card to add 
	 */
	public void addToHand(Card card) {
		playerHand.addCard(card);
	}
	
	public int getChips() {
		return this.chips;
	}
	
	public void setChips(int chips) {
		this.chips = chips;
	}
	
	private Hand playerHand;	// Player's set of cards
	private String name;		// Player's name
	private int chips;			// Amount of chips owned by player
}
