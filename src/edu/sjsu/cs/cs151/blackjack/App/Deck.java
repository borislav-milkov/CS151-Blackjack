package edu.sjsu.cs.cs151.blackjack.App;

import java.util.Collections;
import java.util.Stack;

import edu.sjsu.cs.cs151.blackjack.App.Card.Rank;
import edu.sjsu.cs.cs151.blackjack.App.Card.Suit;

/**
 * The Deck class models a standard deck of 52 cards
 */
public class Deck {
	/**
	 * The stack to hold the cards
	 */
	 private Stack<Card> cards;
	 
	 /**
		 * Constructs a deck with all 52 cards. 
		 * Unshuffled by default.
		 */
	 public Deck() {
		 cards = new Stack<>();
		 for (Suit s : Card.Suit.values()) {
			 for(Rank r : Card.Rank.values()) {
				 cards.push(new Card(s, r));
			 }
		 }
	 }
	 
	 //shuffle the deck
	 public void shuffle() {
		 Collections.shuffle(this.cards);
	 }
	 
	 /**
	  * draw a card from the deck
	  * throws an Exception if the deck is empty.
	  * */
	 public Card draw() throws Exception {
		 if(this.cards.isEmpty()) {
			 throw new Exception("Error: The deck is empty");
		 }
		 return cards.pop();
	 }
	 
	 //return the number of cards in the deck
	 public int size() {
		 return this.cards.size();
	 }
	 
	 public String toString() {
		 StringBuilder sb = new StringBuilder();
		 for (Card card : this.cards) {
			 sb.append(card);
			 sb.append("\n");
		 }
		 return sb.toString();
	 }
}
