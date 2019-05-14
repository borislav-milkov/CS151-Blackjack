package edu.sjsu.cs.cs151.blackjack.Controller;

/**
 * Message class to communicate a bet input from the View to
 * the Controller.
 * */
public class BetMessage extends Message{

	/**
	 * Constructor with the bet amount and label as contents
	 * @param betAmt	amount to bet
	 */
	public BetMessage(int betAmt) {
		super("Bet");
		this.betAmt = betAmt;
	}
	
	/**
	 * Get the bet amount.
	 * @return		amount to bet
	 */
	public int getBetAmount() {
		return this.betAmt;
	}
	
	private int betAmt;
	
}
