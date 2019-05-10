package edu.sjsu.cs.cs151.blackjack.Controller;

import java.io.Serializable;

/**
 * Message represents an instruction being sent between the Controller, View and Model.
 * Series of messages are stored in a queue, to be processed by an appropriate Valve.
 */
public abstract class Message implements Serializable{
	
	public Message(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	private String message;
}
