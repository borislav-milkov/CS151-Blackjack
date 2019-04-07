package edu.sjsu.cs.cs151.blackjack.App;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TODO: Finish Blackjack
public class Blackjack {
	private static Deck gameDeck;
	private static List<Gambler> players;
	private static Player user;
	private static Dealer dealer;
	private static Scanner keyboard;

	// Main game loop
	// This could actually be moved outside of the Blackjack class if we want
	public static void main(String[] args) {
		do {
			Blackjack.startGame();
			Blackjack.play();
		}
		while(Blackjack.playAgain());
	}

	private static void play() {
		dealPhase();    // Setup game, deal cards to all players
		playingPhase(); // Every player takes turns hitting till they stay or bust
		findWinner();   // Compute the winner of the table
	}

	private static void startGame() {
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

	private static void dealPhase() {
		// dealer deals 2 cards to each player
		for (Gambler player : players) {
			dealer.dealCards(player, 2);
		}
		dealer.dealCards(dealer, 2); // dealer deals his own cards
		// Show the user their hand
		// TODO: Implement displayHand in Player class
		user.displayHand();
	}

	private static void playingPhase() {
		for (Gambler player : players) {
			boolean endTurn = false;
			player.displayHand();
			boolean hit = player.willHit();
			
			while(hit && !player.isBust()) {
				dealer.dealCards(player, 1);
			}
			
		}
	}
	
	//TODO: Implement findWinner()
	private static void findWinner() {
		// Check the list of players, compute the highest non-bust score, 
		// print and congratulate the winner
	}

	//TODO: Implement playAgain()
	private static boolean playAgain() {
		// Prompt the user if they want to play again
		// If yes, return true, else return false and exit
	}

}
