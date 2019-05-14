package edu.sjsu.cs.cs151.blackjack.Model;

/**
 * The gambler interface designates common actions taken by any in-game player or dealer.
 */
public interface Gambler {
	
	/**
	 * Checks if a player wants to hit or not.
	 * @return  true if player wants to hit, false if player wants to stay
	 */
	public boolean willHit();
	
	/**
	 * Prints the cards owned by a player.
	 */
	public void displayHand();
	
	/**
	 * Adds a card to players hand.
	 * @param card	card to add
	 */
	public void addToHand(Card card);
	
	/**
	 * Gets the hand of the player.
	 * @return	hand of player
	 */
	public Hand getHand();
	
	/**
	 * Gets the value of a players hand.
	 * @return	sum of card values in player hand
	 */
	public int getHandValue();
	
	/**
	 * Checks if the player has busted or not.
	 * @return	true if the player has met conditions to bust
	 * 			false if the player hasn't busted
	 */
	public boolean isBust();
	
	/**
	 * Gets a string representation of player's name.
	 * @return	"YOU" for a player
	 * 			"DEALER" for a dealer
	 */
	public String getName();
	
	/**
	 * Adds an amount to the active in-game pot.
	 * @param pot	current in-game pot
	 * @param value	amount to add into pot
	 */
	public void putInPot(Pot pot,int value);
	
	/**
	 * The house collects the pot.
	 * @param pot	current in-game pot
	 */
	public void winPot(Pot pot);
	
	/**
	 * Checks if it's the current players turn.
	 * @return	true if it is currently their turn
	 */			
	public boolean getTurn();
	
	/**
	 * Gets the current balance of a player.
	 * @return	remaining balance
	 */
	public int getChips();
	
	/**
	 * Sets the balance of a player.
	 * @param chips	 new balance to set
	 */
	public void setChips(int chips);
	
	/**
	 * Empties the hand of the current player.
	 */
	public void clearHand();
	
	/**
	 * Returns a string representation of this player.
	 */
	public String toString();
}
