package edu.sjsu.cs.cs151.blackjack.App;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import edu.sjsu.cs.cs151.blackjack.Controller.Controller;
import edu.sjsu.cs.cs151.blackjack.Controller.Message;
import edu.sjsu.cs.cs151.blackjack.Model.Model;
import edu.sjsu.cs.cs151.blackjack.View.View;

/** 
 * This class drives the Blackjack application and allows the user to play
 * a 1vs1 game of Blackjack against an AI dealer.
 * @author Devin Gonzales
 * @author Borislav Milkov
 *
 */
public class Blackjack {
	
	private static BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
	private static View view;
	private static Model model;

	public static void main(String[] args) {
		view = View.init(queue);
		model = new Model();
		
		Controller game = new Controller(view, model, queue);
		game.mainLoop();
		view.dispose();
		queue.clear();

	}

}
