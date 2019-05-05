package edu.sjsu.cs.cs151.blackjack.Controller;

interface Valve
{
	/** performs certain action in response to message
	* */
	public ValveResponse execute(Message message);
}

