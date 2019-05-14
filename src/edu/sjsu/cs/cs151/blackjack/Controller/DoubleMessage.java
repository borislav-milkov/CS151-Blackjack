package edu.sjsu.cs.cs151.blackjack.Controller;


/**
 * Message class to communicate a double down input from the View to
 * the Controller.
 * */
public class DoubleMessage extends Message{
	
	/**
	 * Double message ctor.
	 */
	public DoubleMessage() {
		super("DoubleDown");
	}
}
