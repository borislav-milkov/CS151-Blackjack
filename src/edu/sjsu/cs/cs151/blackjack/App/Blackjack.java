package edu.sjsu.cs.cs151.blackjack.App;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import edu.sjsu.cs.cs151.blackjack.Controller.Controller;
import edu.sjsu.cs.cs151.blackjack.Controller.Message;
import edu.sjsu.cs.cs151.blackjack.Model.Model;
import edu.sjsu.cs.cs151.blackjack.View.View;

//TODO: Use toolkit to get frame
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
