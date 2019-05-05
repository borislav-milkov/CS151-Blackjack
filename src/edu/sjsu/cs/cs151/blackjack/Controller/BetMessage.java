package edu.sjsu.cs.cs151.blackjack.Controller;

/**
 * Message class to communicate a bet input from the View to
 * the Controller.
 * */
public class BetMessage extends Message{
	private int betAmt;
	
	//Constructor with the bet amount and label as contents
	public BetMessage(int betAmt) {
		super("Bet");
		this.betAmt = betAmt;
	}
	
	//get how big the bet is
	public int getBetAmount() {
		return this.betAmt;
	}
	
}
