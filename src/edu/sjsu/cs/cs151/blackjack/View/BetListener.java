package edu.sjsu.cs.cs151.blackjack.View;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import edu.sjsu.cs.cs151.blackjack.Controller.BetMessage;
import edu.sjsu.cs.cs151.blackjack.Controller.HitMessage;
import edu.sjsu.cs.cs151.blackjack.Controller.Message;

/**
 * The listener to listen for the click on the 'bet' button on the betting screen. 
 * The message queue is passed from the View object
 * 
 * @param queue
	 * 	A BlockingQueue<Message> to put the action event in
	 * @param betAmt
	 * 	The integer amount of the bet
	 * @param cardLay
	 * 	The card layout in the frame, used for switching the content panes
	 * @param frame
	 * 	The parent JFrame of the button, used for switching panes
 * */
public class BetListener implements ActionListener {

	private BlockingQueue<Message> queue;
	private JTextField betField;
	private CardLayout cardLay;
	private JFrame frame;
	
	/**
	 * Bet Listener ctor.
	 * @param queue	 message queue for listener
	 */
	public BetListener(BlockingQueue<Message> queue, JTextField betField, CardLayout cardLay, JFrame frame) {
		this.betField = betField;
		this.queue = queue;
		this.cardLay = cardLay;
		this.frame = frame;
	}

	/**
	 * Validates bet input and queues a new bet message.
	 */
	@Override
	public void actionPerformed(ActionEvent standEvent) {
			try {
				int betAmt = 0;
				if(!betField.getText().equals("")) {
					betAmt = Integer.parseInt(betField.getText());
				}
				
				queue.put(new BetMessage(betAmt));
			}
			catch(InterruptedException exception){
				exception.printStackTrace();
			}
		
	}

}