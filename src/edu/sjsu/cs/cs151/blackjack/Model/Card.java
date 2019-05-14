package edu.sjsu.cs.cs151.blackjack.Model;

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
	 * The rank of a card with a numerical value 2-10. 
	 * All face cards are worth 10, aces are worth 1 or 11.
	 */
	public enum Rank {
		TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), 
		EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);

		private int value;
		
		/**
		 * Constructs each rank with it's initial value.
		 * @param value		integer worth of a rank
		 */
		Rank(int value) {
			this.value = value;
		}
		
		/**
		 * Accessor for rank value.
		 * @return		integer value of rank
		 */
		private int getValue() {
			return this.value;
		}
	};

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
	
	/**
	 * Constructs a card with a specific suit and rank
	 * @param suit	suit of this card
	 * @param rank	rank of this card
	 */
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	/**
	 * Gets a card's suit.
	 * @return		suit of this card
	 */
	public Suit getSuit() {
		return this.suit;
	}
	
	/**
	 * Sets a card's rank.
	 * @param r		new rank of this card
	 */
	public void setRank(Card.Rank r) {
		this.rank = r;
	}
	
	/**
	 * Gets a card's rank.
	 * @return		rank of this card
	 */
	public Rank getRank() {
		return this.rank;
	}
	
	/**
	 * Gets a card's rank as an integer.
	 * @return		integer value of this card
	 */
	public int getRankAsInt() {
		return this.rank.getValue();
	}
	
	/**
	 * Returns a string representation of this card.
	 * IE "ACE of SPADES", "TWO of HEARTS" etc.
	 */
	public String toString() {
		return (rank + " of " + suit);
	}
	

	private Suit suit;	// Suit of this card
	private Rank rank;	// Rank of this card
}
