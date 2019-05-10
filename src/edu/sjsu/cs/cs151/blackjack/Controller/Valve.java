package edu.sjsu.cs.cs151.blackjack.Controller;

/**
 * The Valve interface performs certain actions in response to a message.
 */
interface Valve
{
	/** Executes an instruction given a message.
	 * @param message	message sent from View to execute
	* */
	public ValveResponse execute(Message message);
}

