package edu.sjsu.cs.cs151.blackjack.App;

/**
 * The Card class models one of 52 cards in a standard deck.
 * Each card has a suit and a rank.
 */
public class Card {
	/**
	 * The suit of a card with a constant value
	 * SPADES, HEARTS, DIAMONDS, or CLUBS.
	 */
	public enum Suit {
		SPADES, HEARTS, DIAMONDS, CLUBS
	};
	/**
	 * The rank of a card with a numerical value
	 * 1-13. Aces are low.
	 */
	public enum Rank {
		ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), 
		EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13);

		private int value;
		// Ctor for Rank that assigns its numerical value
		Rank(int value) {
			this.value = value;
		}

		private int getValue() {
			return this.value;
		}
	};

	private Suit suit;	// Suit of this card
	private Rank rank;	// Rank of this card

	/**
	 * Constructs a card with the specified suit and rank.
	 * Suit must be a defined constant. Rank must be between 1-13.
	 * Invalid parameters throw IllegalArgException.
	 * @param suit	suit of this card
	 * @param rank	rank of this card
	 */
	public Card(String suit, int rank) {
		this.suit = Suit.valueOf(suit.toUpperCase());
		// Find the correct Rank corresponding to its numerical value
		for (Rank r : Rank.values())
			if (rank == r.getValue()) {
				this.rank = r;
				return;
			}
		// Throw an error if an invalid input is given
		throw new IllegalArgumentException(rank + " is not a valid rank.");

	}
	// Returns this card's suit
	public Suit getSuit() {
		return this.suit;
	}
	// Returns this card's rank
	public Rank getRank() {
		return this.rank;
	}
	// Returns a string representation of this card
	public String toString() {
		return (rank + " of " + suit);
	}
}
