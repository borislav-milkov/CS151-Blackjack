package edu.sjsu.cs.cs151.blackjack.App;

/**
 * The gambler interface designates common actions taken by any in-game player.
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
	
	public void addToHand(Card card);
	public Hand getHand();
	public int getHandValue();
	
	//TODO: Are chips really necessary to play?
	public int getChips();
	public void setChips(int chips);
}
