package edu.sjsu.cs.cs151.blackjack.Controller;

/**
 * Message class to communicate a reset of the game from the View to
 * the Controller.
 * */
public class ResetMessage extends Message{
	
	/**
	 * Reset ctor.
	 */
	public ResetMessage() {
		super("Hit");
	}
}