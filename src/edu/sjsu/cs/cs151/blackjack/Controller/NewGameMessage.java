package edu.sjsu.cs.cs151.blackjack.Controller;

/**
 * Message class to communicate the start of a new game from the View to
 * the Controller.
 * */
public class NewGameMessage extends Message{
	
	public NewGameMessage() {
		super("New Game");
	}
	
}
