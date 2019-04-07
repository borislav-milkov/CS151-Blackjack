package edu.sjsu.cs.cs151.blackjack.App;

import java.util.*;

//
/**
 * Play a single-player game of Blackjack against an AI Dealer. 
 * Aces are low (for now). Player is prompted through the in-game console.
 * 
 * TODO: Eventually connect View and Controller to this class
 */
public class Blackjack {
	
	/**
	 * Driver function for main game loop.
	 * This could actually be moved outside of the Blackjack class if we want.
	 */
	public static void main(String[] args) {
		do {
			Blackjack newGame = Blackjack.startGame();  // Each new game is a new Blackjack object created in startGame()
			newGame.play();
		} while (Blackjack.playAgain());
	}
	
	/**
	 * Driver function for the gameplay logic.
	 */
	private void play() {
		dealPhase(); 	// Deal cards to all players
		playingPhase(); // Every player takes turns hitting till they stay or bust
		findWinner(); 	// Compute the winner of the table
	}

	/**
	 * Sets up and constructs a new game of Blackjack. 
	 * @return	next game of Blackjack to play
	 */
	private static Blackjack startGame() {
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

		// Add all in-game players to list
		players.add(user);
		players.add(dealer);
		
		// Return a new game of blackjack
		return new Blackjack();

	}

	/**
	 * Deals cards to player and dealer, displays the players received cards.
	 */
	private void dealPhase() {
		// dealer deals 2 cards to each player and themself
		for (Gambler player : players) {
			dealer.dealCards(player, 2);
		}
		// Show the user their hand
		user.displayHand();
	}

	/**
	 * Both player and dealer play their turn. 
	 * A turn involves hitting until the dealer or player stays or busts.
	 */
	private void playingPhase() {
		for (Gambler player : players) {
			boolean endTurn = false;
			// Player and Dealer take turns hitting until they stay or bust
			while (!endTurn) {
				boolean hit = player.willHit();
				if (hit) {
					dealer.dealCards(player, 1);
					if (player.isBust()) {
						System.out.println(player.getName() + " busted!");
						endTurn = true;
					} else {
						System.out.println(player.getName() + " stayed.");
						endTurn = true;
					}
				}
			}
		}

	}

	/**
	 * Computes the winner for a game of Blackjack.
	 */
	private void findWinner() {
		final String NULL = "";	// Initial winner value
		String winner = NULL;	// Start by assuming no winner
		int highestHand = 0;
		
		// Search all players for highest hand
		for (Gambler player : players) {
			int thisHand = player.getHandValue();
			String name = player.getName();
			
			System.out.println(name + "has score: " + thisHand);
			
			if(!player.isBust() && thisHand > highestHand) {
				winner = name;
				highestHand = thisHand;
			}
		}
		// Default case: no winner is found
		if (winner == NULL) 
			System.out.println("No winner, everybody has busted.");
		else
			System.out.println(winner + " wins the game!");
	}

	/**
	 * Prompts the user if they would like to play another game of Blackjack.
	 * Exits the game if the user doesn't want to play again.
	 * @return true if user wants to play again, false if user wants to quit
	 */
	private static boolean playAgain() {
		System.out.println("PLAY AGAIN? (Y/N): ");
		keyboard = new Scanner(System.in);
		while(true) {
			String in = keyboard.next().toLowerCase();
			switch(in) {
			case "no":
			case "n":
				return false;
			case "yes":
			case "y":
				return true;
			default:
				System.out.println("Type 'yes' or 'no': ");
			}
		}
	}

	private static Deck gameDeck; 			// Single deck for each game
	private static List<Gambler> players; 	// Players contains ALL in-game players, including the dealer
	private static Player user; 			// User interacting with the game
	private static Dealer dealer; 			// Opponent of the player
	private static Scanner keyboard; 		// Grabbed input from the user
}
