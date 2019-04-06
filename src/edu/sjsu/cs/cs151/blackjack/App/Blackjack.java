package edu.sjsu.cs.cs151.blackjack.App;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TODO: Finish Blackjack
public class Blackjack {
	private Deck gameDeck;
	private List<Gambler> players;
	private Player user;
	private Dealer dealer;
	private Scanner keyboard;

	// Main game loop
	// This could actually be moved outside of the Blackjack class if we want
	public static void main(String[] args) {
		do {
			Blackjack game = Blackjack.startGame();
			game.play();
		}
		while(Blackjack.playAgain())
	}

	public void play() {
		dealPhase();    // Setup game, deal cards to all players
		playingPhase(); // Every player takes turns hitting till they stay or bust
		findWinner();   // Compute the winner of the table
	}

	public void startGame() {
		// Initialize player, dealer and relevant objects
		gameDeck = new Deck();
		players = new ArrayList<>();
		dealer = new Dealer();

		//
		// TODO: TITLE SCREEN goes here
		//

		// Prompt user for name
		System.out.println("Welcome to Blackjack!");
		System.out.println("Choose a name: ");
		keyboard = new Scanner(System.in);
		String name = keyboard.next();
		user = new Player(name);

		keyboard.close();
		// Add all in-game players to list
		players.add(user);
		players.add(dealer);

	}

	public void dealPhase() {
		// Every player starts by being dealt 2 cards
		for (Gambler player : players) {
			player.addToHand(gameDeck.draw());
			player.addToHand(gameDeck.draw());
		}
		// Show the user their hand
		// TODO: Implement displayHand in Player class
		user.displayHand();
	}

	public void playingPhase() {
		for (Gambler player : players) {
			boolean endTurn = false;
			player.displayHand();
			boolean hit = player.willHit();
			if (hit) {// TODO: rest of game logic}
			}
		}
	}
	
	//TODO: Implement findWinner()
	public void findWinner() {
		// Check the list of players, compute the highest non-bust score, 
		// print and congratulate the winner
	}

	//TODO: Implement playAgain()
	public boolean playAgain() {
		// Prompt the user if they want to play again
		// If yes, return true, else return false and exit
	}

}
