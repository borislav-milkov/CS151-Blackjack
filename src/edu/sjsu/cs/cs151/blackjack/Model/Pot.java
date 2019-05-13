package edu.sjsu.cs.cs151.blackjack.Model;

/**
 * Class to handle the dealings of the bet amount
 * */
public class Pot {
	
	public Pot(int value) {
		this.value = value;
	}
	
	public Pot() {
		this.value = 0;
	}
	
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Method to increase the pot size
	 * */
	public void addToPot(int value) {
		this.value += value;
	}
	
	public void resetValue() {
		this.value = 0;
	}
	
	private int value;
}
