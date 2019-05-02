package edu.sjsu.cs.cs151.blackjack.Controller;

import java.util.concurrent.BlockingQueue;

import edu.sjsu.cs.cs151.blackjack.Model.Model;
import edu.sjsu.cs.cs151.blackjack.View.View;

public class Controller {
	private BlockingQueue<Message> messageQueue; // stores messages to be processed by Valve
	private View view; // direct reference to View
	private Model model; // direct reference to Model
	
	/*
	 * Stores all the information about the model into one class, IE a lot of variables related to model
	 */
	private GameInfo info;
	
	// Controller ctor is passed in this info from the App
	public Controller(View view, Model model, BlockingQueue queue) {
		this.view = view;
		this.model = model;
		this.messageQueue = queue;
	}
	
	/**
	 * Driver function for main game loop.
	 * This will eventually be replaced by Valve<<interface>> stuff
	 */
	public static void mainLoop() {
		do {
			Model newGame = Model.startGame();  // Each new game is a new Blackjack object created in startGame()
			newGame.play();
		} while (Model.playAgain());
		System.out.println("Goodbye!");
	}
}
