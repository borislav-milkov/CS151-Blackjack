package edu.sjsu.cs.cs151.blackjack.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModelV2 {
	private static Pot pot; // The betting pot
	private static Deck gameDeck; // Single deck for each game
	private static List<Gambler> players; // Players contains ALL in-game players, including the dealer
	private static Player user; // User interacting with the game
	private static Dealer dealer; // Opponent of the player
	
	private static boolean playerTurn;
	private static boolean dealerTurn;
	
	public ModelV2() {
		
	}

	private void newGame() {
		// Initialize player, dealer and relevant objects
		gameDeck = new Deck();
		players = new ArrayList<>();
		dealer = new Dealer();
		pot = new Pot();

		// shuffle cards
		gameDeck.shuffle();

		// TODO: Probably remove player name eventually
		user = new Player("");

		// Add all in-game players to list
		players.add(user);
		players.add(dealer);

		// Player always goes first
		playerTurn = true;
	}

	private void deal() {
		// dealer deals 2 cards to each player and themself
		for (Gambler player : players)
			dealer.dealCards(gameDeck, player, 2);

		// TODO: update GameInfo here???
	}

	public void bet(int value) {
		for (Gambler player : players)
			player.putInPot(pot, value);
	}

	private void play() {
		// End of players turn, so dealer plays
		if (!playerTurn) {
			while (dealerTurn) {
				boolean hit = dealer.willHit();
				if (hit) {
					dealer.dealCards(gameDeck, dealer, 1);
					if (dealer.isBust()) // end of the round if dealer busts
						dealerTurn = false;
				}
				else
					dealerTurn = false;
			}
		}
		
		if(!playerTurn && !dealerTurn) {
			findWinner();
			gameOver();
		}
			
	}

	public void hit() {
		dealer.dealCards(gameDeck, user, 1);

		if (user.isBust())
			stand();
		// TODO: update GameInfo here???
	}

	public void stand() {
		playerTurn = false;
		dealerTurn = true;

		// TODO: update GameInfo here???
	}

	public void doubleDown() {
		// TODO: double the player's bet, hit, then stand
	}

}
