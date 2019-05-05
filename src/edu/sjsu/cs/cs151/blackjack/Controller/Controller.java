package edu.sjsu.cs.cs151.blackjack.Controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import edu.sjsu.cs.cs151.blackjack.Model.Model;
import edu.sjsu.cs.cs151.blackjack.View.View;

public class Controller {
	private BlockingQueue<Message> messageQueue; // stores messages to be processed by Valve
	private View view; // direct reference to View
	private Model model; // direct reference to Model
	private List<Valve> valves = new LinkedList<Valve>();
	
	/*
	 * Stores all the information about the model into one class, IE a lot of variables related to model
	 */
	private GameInfo info;
	
	// Controller ctor is passed in this info from the App
	public Controller(View view, Model model, BlockingQueue queue) {
		this.view = view;
		this.model = model;
		this.messageQueue = queue;
	}
	
	/**
	 * Driver function for main game loop.
	 * This will eventually be replaced by Valve<<interface>> stuff
	 */
	public void mainLoop() {
		ValveResponse response = ValveResponse.EXECUTED;
		Message message = null;
		while(response != ValveResponse.FINISH){
			try{
				message = (Message)messageQueue.take();
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
			for(Valve valve : valves)
			{
				response = valve.execute(message);
				if(response != ValveResponse.MISS) {
					break;
				}

			}
		}

	}
	
	private class DoBetValve implements Valve
	{
		public ValveResponse execute(Message message)
		{
			if(message.getClass() != BetMessage.class) {
				return ValveResponse.MISS;
			}
			BetMessage betMsg = (BetMessage) message;
			model.betPhase(betMsg.getBetAmount());
		//actions in View
		return ValveResponse.EXECUTED;
		}
	}
}
