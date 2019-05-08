package edu.sjsu.cs.cs151.blackjack.Controller;

import java.io.Serializable;

public abstract class Message implements Serializable{
	
	public Message(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	private String message;
}
