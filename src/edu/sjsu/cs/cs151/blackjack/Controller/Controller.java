package edu.sjsu.cs.cs151.blackjack.Controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import edu.sjsu.cs.cs151.blackjack.Model.Card;
import edu.sjsu.cs.cs151.blackjack.Model.Dealer;
import edu.sjsu.cs.cs151.blackjack.Model.Gambler;
import edu.sjsu.cs.cs151.blackjack.Model.Model;
import edu.sjsu.cs.cs151.blackjack.Model.ModelV2;
import edu.sjsu.cs.cs151.blackjack.Model.Player;
import edu.sjsu.cs.cs151.blackjack.View.View;

public class Controller {
	private BlockingQueue<Message> messageQueue; // stores messages to be processed by Valve
	private View view; // direct reference to View
	private ModelV2 model; // direct reference to Model
	private List<Valve> valves = new LinkedList<Valve>();

	/*
	 * Stores all the information about the model into one class, IE a lot of
	 * variables related to model
	 */
	private GameInfo info;

	// Controller ctor is passed in this info from the App
	public Controller(View view, ModelV2 model, BlockingQueue<Message> queue) {
		this.view = view;
		this.model = model;
		this.info = new GameInfo(model);
		this.messageQueue = queue;
	}

	/**
	 * Driver function for main game loop. This will eventually be replaced by
	 * Valve<<interface>> stuff
	 */
	public void mainLoop() {
		ValveResponse response = ValveResponse.EXECUTED;
		Message message = null;
		while (response != ValveResponse.FINISH) {
			try {
				System.out.println("The queue has a size of: " + messageQueue.size());
				message = (Message) messageQueue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (Valve valve : valves) {
				response = valve.execute(message);
				if (response != ValveResponse.MISS) {
					break;
				}

			}
		}

	}

	// Updates the game info to the latest values
	// Most of the updating happens in GameInfo.java
	public void updateGameInfo() {
		Player updatedPlayer = model.getPlayer();
		Dealer updatedDealer = model.getDealer();
		info.update(updatedPlayer, updatedDealer);
	}

	private class DoNewGameValve implements Valve {

		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != NewGameMessage.class) {
				return ValveResponse.MISS;
			}
			model = model.restart();
			info = new GameInfo(model);
			messageQueue.clear();
			view.update(info);

			return ValveResponse.EXECUTED;

		}
	}

	private class DoBetValve implements Valve {
		public ValveResponse execute(Message message) {
			if (message.getClass() != BetMessage.class) {
				return ValveResponse.MISS;
			}
			BetMessage betMsg = (BetMessage) message;
			model.bet(betMsg.getBetAmount());
			updateGameInfo();
			view.update(info); 
			view.switchScreen();
			// actions in View
			return ValveResponse.EXECUTED;
		}
	}

	private class DoHitValve implements Valve {
		public ValveResponse execute(Message message)
		{
			if(message.getClass() != HitMessage.class) {
				return ValveResponse.MISS;
			}
			
			model.hit(model.getPlayer()); // Add new card to players hand
			updateGameInfo();  // Update the now changed game info
			view.update(info); // Update the view to display the correct info
			return ValveResponse.EXECUTED;
		}
	}
	
	private class DoStandValve implements Valve {
		public ValveResponse execute(Message message)
		{
			if(message.getClass() != StandMessage.class) {
				return ValveResponse.MISS;
			}
			// When player stands, it's the dealers turn
			Dealer dealer = model.getDealer();
			boolean endTurn = false;
			while(!endTurn) {
				if(dealer.willHit()) {
					// Update view & game info every time the dealer hits
					model.hit(dealer);
					updateGameInfo();
					view.update(info);
					
					if(dealer.isBust()) // dealer busts
						endTurn = true;
				}
				else
					endTurn = true;		// dealer stands
			}
			//TODO: determine the winners, display them to user
			return ValveResponse.EXECUTED;
		}
	}
	
	private class DoDoubleValve implements Valve {
		public ValveResponse execute(Message message)
		{
			if(message.getClass() != DoubleMessage.class) {
				return ValveResponse.MISS;
			}
			
			//TODO
			return ValveResponse.EXECUTED;
		}

	}
	
}
