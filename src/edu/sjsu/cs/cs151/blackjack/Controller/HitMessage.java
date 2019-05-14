package edu.sjsu.cs.cs151.blackjack.Controller;

/**
 * Message class to communicate a hit input from the View to
 * the Controller.
 * */
public class HitMessage extends Message{
	
	/**
	 * Hit ctor.
	 */
	public HitMessage() {
		super("Hit");
	}
}
