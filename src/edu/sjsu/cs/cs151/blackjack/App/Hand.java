package edu.sjsu.cs.cs151.blackjack.App;

import java.util.ArrayList;


/**
 * 
 * Hand class aggregates the cards owned by a player.
 * Cards are aggregated as a list
 * 
 */
public class Hand {
	
	/**
	 * Computes and returns the value of a given hand.
	 * @return handValue  sum of each card's rank in a hand
	 */
	public int getValue() {
		int handValue = 0;
		for (Card card : hand)
			handValue += card.getRankAsInt();
		
		return handValue;
	}
	
	/**
	 * Adds a card to the hand.
	 * @param card  the card to add
	 */
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	/**
	 * Returns the hand.
	 * @return hand  list of cards
	 */
	public ArrayList<Card> getHand() {
		return this.hand;
	}
	
	/**
	 * Returns a string representation of a hand's cards.
	 */
	public String toString() {
		// Empty hands return an empty string
		if (this.hand.isEmpty())
			return "";
		
		StringBuilder handOfCards = new StringBuilder();
		for (Card card : hand)
			handOfCards.append(card.toString() + ", ");
		// Trim the last comma and whitespace
		handOfCards.delete(handOfCards.length()-2, handOfCards.length());
		
		return handOfCards.toString();
	}
	
	private ArrayList<Card> hand = new ArrayList<>();
}
