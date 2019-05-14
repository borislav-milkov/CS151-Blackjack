package edu.sjsu.cs.cs151.blackjack.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

import edu.sjsu.cs.cs151.blackjack.Controller.ResetMessage;
import edu.sjsu.cs.cs151.blackjack.Controller.Message;
/**
 * The listener to listen for the click on the 'restart' or 'play again' button. 
 * The message queue is passed from the View object
 * */
public class NewRoundListener implements ActionListener {

	private BlockingQueue<Message> queue;
	
	/**
	 * New Round Listener ctor.
	 * @param queue	 message queue for listener
	 */
	public NewRoundListener(BlockingQueue<Message> queue) {
		this.queue = queue;
	}

	/**
	 * Queues a new New Round message.
	 */
	@Override
	public void actionPerformed(ActionEvent resetRoundEvent) {
			try {
				queue.put(new ResetMessage());
			}
			catch(InterruptedException exception){
				exception.printStackTrace();
			}
		
	}

}