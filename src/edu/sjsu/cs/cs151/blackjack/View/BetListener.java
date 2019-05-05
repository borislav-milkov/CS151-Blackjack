package edu.sjsu.cs.cs151.blackjack.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

import edu.sjsu.cs.cs151.blackjack.Controller.BetMessage;
import edu.sjsu.cs.cs151.blackjack.Controller.HitMessage;
import edu.sjsu.cs.cs151.blackjack.Controller.Message;

/**
 * The listener to listen for the click on the 'bet' button on the betting screen. 
 * The message queue is passed from the View object
 * */
public class BetListener implements ActionListener {

	private BlockingQueue<Message> queue;
	
	public BetListener(BlockingQueue<Message> queue) {
		this.queue = queue;
	}

	@Override
	public void actionPerformed(ActionEvent standEvent) {
			try {
				queue.put(new BetMessage());
			}
			catch(InterruptedException exception){
				exception.printStackTrace();
			}
		
	}

}