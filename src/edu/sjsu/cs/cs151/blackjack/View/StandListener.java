package edu.sjsu.cs.cs151.blackjack.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;
import edu.sjsu.cs.cs151.blackjack.Controller.Message;
import edu.sjsu.cs.cs151.blackjack.Controller.StandMessage;

/**
 * The listener to listen for the click on the 'stand' button. 
 * The message queue is passed from the View object.
 * */
public class StandListener implements ActionListener {
	
	private BlockingQueue<Message> queue;
	
	public StandListener(BlockingQueue<Message> queue) {
		this.queue = queue;
	}

	@Override
	public void actionPerformed(ActionEvent standEvent) {
			try {
				queue.put(new StandMessage());
			}
			catch(InterruptedException exception){
				exception.printStackTrace();
			}
		
	}


}
