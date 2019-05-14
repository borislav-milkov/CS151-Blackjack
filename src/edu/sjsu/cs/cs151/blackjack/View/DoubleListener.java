package edu.sjsu.cs.cs151.blackjack.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

import edu.sjsu.cs.cs151.blackjack.Controller.DoubleMessage;
import edu.sjsu.cs.cs151.blackjack.Controller.Message;
import edu.sjsu.cs.cs151.blackjack.Controller.StandMessage;

/**
 * The listener to listen for the click on the 'Double Down' button. 
 * The message queue is passed from the View object
 * */
public class DoubleListener implements ActionListener {

	private BlockingQueue<Message> queue;
	
	/**
	 * Double Listener ctor.
	 * @param queue	 message queue for listener
	 */
	public DoubleListener(BlockingQueue<Message> queue) {
		this.queue = queue;
	}

	/**
	 * Queues a new double down message.
	 */
	@Override
	public void actionPerformed(ActionEvent standEvent) {
			try {
				queue.put(new DoubleMessage());
			}
			catch(InterruptedException exception){
				exception.printStackTrace();
			}
		
	}
}
