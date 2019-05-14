package edu.sjsu.cs.cs151.blackjack.Controller;


/**
 * Message class to communicate a stand input from the View to
 * the Controller.
 * */
public class StandMessage extends Message{
	
	/**
	 * Stand ctor.
	 */
	public StandMessage() {
		super("Stand");
	}
	
}
