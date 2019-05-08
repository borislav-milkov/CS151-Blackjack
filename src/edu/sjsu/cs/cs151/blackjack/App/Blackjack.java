package edu.sjsu.cs.cs151.blackjack.App;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import edu.sjsu.cs.cs151.blackjack.Controller.Controller;
import edu.sjsu.cs.cs151.blackjack.Controller.Message;
import edu.sjsu.cs.cs151.blackjack.Model.ModelV2;
import edu.sjsu.cs.cs151.blackjack.View.View;

public class Blackjack {
	
	private static BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
	private static View view;
	private static ModelV2 model;


	public static void main(String[] args) {
		view = View.init(queue);
		model = new ModelV2();
		
		Controller game = new Controller(view,model, queue);
		game.mainLoop();
		view.dispose();
		queue.clear();

	}

}
