package edu.sjsu.cs.cs151.blackjack.Controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import edu.sjsu.cs.cs151.blackjack.Model.Card;
import edu.sjsu.cs.cs151.blackjack.Model.Dealer;
import edu.sjsu.cs.cs151.blackjack.Model.Gambler;
import edu.sjsu.cs.cs151.blackjack.Model.OldModel;
import edu.sjsu.cs.cs151.blackjack.Model.Model;
import edu.sjsu.cs.cs151.blackjack.Model.Player;
import edu.sjsu.cs.cs151.blackjack.View.View;

public class Controller {

	// Controller ctor is passed in this info from the App
	public Controller(View view, Model model, BlockingQueue<Message> queue) {
		this.view = view;
		this.model = model;
		this.info = new GameInfo(model);
		this.messageQueue = queue;
		valves.add(new DoBetValve());
		valves.add(new DoHitValve());
		valves.add(new DoStandValve());
	}

	/**
	 * Driver function for main game loop.
	 */
	public void mainLoop() {
		ValveResponse response = ValveResponse.EXECUTED;
		Message message = null;
		while (response != ValveResponse.FINISH) {
			try {
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
		info.update(updatedPlayer, updatedDealer, model);
	}

	//TODO: Get NewGameValve working
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
			model.deal();
			updateGameInfo();
			view.update(info); 
			view.showTableScreen();
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
			Player player = model.getPlayer();
			model.hit(player); // Add new card to players hand
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
			System.out.println("A Stand Call");
			// When player stands, it's the dealers turn
			model.getPlayer().endTurn(); // end player's turn
			Dealer dealer = model.getDealer();
			dealer.startTurn();
			boolean endTurn = false;
			while(!endTurn) {
				if(dealer.willHit()) {
					// Update view & game info every time the dealer hits
					model.hit(dealer);
					updateGameInfo();
					view.update(info);
					
					if(dealer.isBust()) { // dealer busts
						endTurn = true;
						model.endDealerTurn();
					}
					
				}
				else {
					dealer.endTurn();
					updateGameInfo();
					view.update(info);
					endTurn = true;
				}		// dealer stands
			}
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
	
	
	private BlockingQueue<Message> messageQueue; // stores messages to be processed by Valve
	private View view; // direct reference to View
	private Model model; // direct reference to Model
	private List<Valve> valves = new LinkedList<Valve>();

	/*
	 * Stores all the information about the model into one class, IE a lot of
	 * variables related to model
	 */
	private GameInfo info;
	
}
