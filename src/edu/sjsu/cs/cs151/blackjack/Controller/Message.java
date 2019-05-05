package edu.sjsu.cs.cs151.blackjack.Controller;

import java.io.Serializable;

public abstract class Message implements Serializable{

	private String message;
	
	public Message(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return this.message;
	}
}
