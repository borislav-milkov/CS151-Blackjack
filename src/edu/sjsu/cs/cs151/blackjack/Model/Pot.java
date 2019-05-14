package edu.sjsu.cs.cs151.blackjack.Model;

/**
 * Class to handle the dealings of the bet amount
 * */
public class Pot {
	
	/**
	 * Constructs a pot with the specified value.
	 * @param value	 beginning value for pot to contain
	 */
	public Pot(int value) {
		this.value = value;
	}
	
	/**
	 * Constructs a pot with a default value of 0.
	 */
	public Pot() {
		this.value = 0;
	}
	
	/**
	 * Gets the current amount in the pot.
	 * @return	remaining value in pot
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Method to increase the pot size.
	 * @param value  value to add to pot
	 * */
	public void addToPot(int value) {
		this.value += value;
	}
	
	/**
	 * Resets the pot value back to 0.
	 */
	public void resetValue() {
		this.value = 0;
	}
	
	private int value;	// remaining balance of the pot
}
