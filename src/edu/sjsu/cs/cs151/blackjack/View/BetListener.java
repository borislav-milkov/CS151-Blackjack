package edu.sjsu.cs.cs151.blackjack.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

import edu.sjsu.cs.cs151.blackjack.Controller.HitMessage;
import edu.sjsu.cs.cs151.blackjack.Controller.Message;

public class BetListener implements ActionListener {

	private BlockingQueue<Message> queue;
	
	public BetListener(BlockingQueue<Message> queue) {
		this.queue = queue;
	}

	@Override
	public void actionPerformed(ActionEvent standEvent) {
			try {
				queue.put(new HitMessage());
			}
			catch(InterruptedException exception){
				exception.printStackTrace();
			}
		
	}

}