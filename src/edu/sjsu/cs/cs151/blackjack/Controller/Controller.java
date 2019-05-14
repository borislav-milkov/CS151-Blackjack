package edu.sjsu.cs.cs151.blackjack.Controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import edu.sjsu.cs.cs151.blackjack.Model.Dealer;
import edu.sjsu.cs.cs151.blackjack.Model.Model;
import edu.sjsu.cs.cs151.blackjack.Model.Player;
import edu.sjsu.cs.cs151.blackjack.View.View;

/**
 * Controller handles the actions sent between View and Model.
 */
public class Controller {

	/**
	 * Controller is constructed with the view, model and message queue.
	 * @param view	direct reference to view
	 * @param model	direct reference to model
	 * @param queue	direct reference to a message queue
	 */
	public Controller(View view, Model model, BlockingQueue<Message> queue) {
		this.view = view;
		this.model = model;
		this.info = new GameInfo(model);
		this.messageQueue = queue;
		valves.add(new DoBetValve());
		valves.add(new DoHitValve());
		valves.add(new DoStandValve());
		valves.add(new DoNewGameValve());
		valves.add(new DoDoubleValve());
		valves.add(new DoResetValve());
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

	/**
	 * Updates game info with any additional values or information.
	 */
	public void updateGameInfo() {
		Player updatedPlayer = model.getPlayer();
		Dealer updatedDealer = model.getDealer();
		info.update(updatedPlayer, updatedDealer, model);
	}

	/**
	 * Executes a set of New Game instructions.
	 * Restart the model, view and clear the queue in anticipation of a new game.
	 */
	private class DoNewGameValve implements Valve {

		@Override
		public ValveResponse execute(Message message) {
			if (message.getClass() != NewGameMessage.class) {
				return ValveResponse.MISS;
			}
			model = model.restart();
			info = new GameInfo(model);
			messageQueue.clear();
			view = view.restart(messageQueue);
			
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 * Executes a set of Bet instructions.
	 * User is prompted by the bet screen, chooses an amount, and is deducted
	 * the chosen amount from their current balance before receiving their cards.
	 */
	private class DoBetValve implements Valve {
		public ValveResponse execute(Message message) {
			if (message.getClass() != BetMessage.class) {
				return ValveResponse.MISS;
			}
			BetMessage betMsg = (BetMessage) message;
			model.bet(betMsg.getBetAmount());
			model.deal();
			model.getDealer().hideCards();
			updateGameInfo();
			view.repaint(info); 
			view.showTableScreen();
			// actions in View
			return ValveResponse.EXECUTED;
		}
	}

	/**
	 * Executes a set of Hit instructions.
	 * User is dealt a new card when this is executed.
	 */
	private class DoHitValve implements Valve {
		public ValveResponse execute(Message message)
		{
			if(message.getClass() != HitMessage.class) {
				return ValveResponse.MISS;
			}
			Player player = model.getPlayer();
			model.hit(player); // Add new card to players hand
			updateGameInfo();  // Update the now changed game info
			view.repaint(info); // Update the view to display the correct info
			return ValveResponse.EXECUTED;
		}
	}
	
	/**
	 * Executes a set of Stand instructions.
	 * User's turn ends when they stand and the dealer follows through 
	 * with its own turn when this is executed.
	 *
	 */
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
			dealer.showCards();
			updateGameInfo();
			view.repaint(info);
			boolean endTurn = false;
			while(!endTurn) {
				if(dealer.willHit()) {
					// Update view & game info every time the dealer hits
					model.hit(dealer);
					updateGameInfo();
					view.repaint(info);
					
				}
				else {
					dealer.showCards();
					dealer.endTurn();
					updateGameInfo();
					view.repaint(info);
					endTurn = true;
				}		// dealer stands
			}
			return ValveResponse.EXECUTED;
		}
	}
	
	/**
	 * Executes a set of Double Down instructions.
	 * User bets twice their original amount, is dealt one card 
	 * and then ends their turn when this is executed.
	 */
	private class DoDoubleValve implements Valve {
		public ValveResponse execute(Message message)
		{
			if(message.getClass() != DoubleMessage.class) {
				return ValveResponse.MISS;
			}
			
			model.bet(model.getPot()/2);
			new DoHitValve().execute(new HitMessage());
			new DoStandValve().execute(new StandMessage());
			return ValveResponse.EXECUTED;
		}

	}
	
	/**
	 * Executes a set of Reset instructions.
	 * User is taken to the betting screen again in anticipation for
	 * the next round of Blackjack when this is executed.
	 */
	private class DoResetValve implements Valve {
		public ValveResponse execute(Message message)
		{
			if(message.getClass() != ResetMessage.class) {
				return ValveResponse.MISS;
			}
			
			model.newRound();
			updateGameInfo();
			view.repaint(info);
			view.resetTable();
			view.showBetScreen();
			return ValveResponse.EXECUTED;
		}

	}
	
	
	private BlockingQueue<Message> messageQueue; 			// stores messages to be processed by Valve
	private View view; 										// direct reference to View
	private Model model; 									// direct reference to Model
	private List<Valve> valves = new LinkedList<Valve>();	// list of Valves for execution
	private GameInfo info;									// stores the relevant info for model
	
}
