package edu.sjsu.cs.cs151.blackjack.Model;

/**
 * The Dealer class models a dealer for a blackjack table.
 */
public class Dealer implements Gambler {

	/**
	 * No-args ctor constructs a Dealer with 0 starting chips and
	 * an empty hand by default.
	 */
	public Dealer() {
		this.name = "Dealer";
		this.chips = 0;
		dealerHand = new Hand();
	}
	
	/**
	 * Constructs a Dealer with desired amount of starting chips and
	 * an empty hand by default.
	 * @param startChips  starting amount of chips
	 */
	public Dealer(int startChips) {
		this.chips = startChips;
		this.name = "Dealer";
		this.myTurn = false;
		dealerHand = new Hand();
	}
	
	/**
	 * Deals n cards to a player.
	 * @param playerHand  player to deal cards
	 * @param n		  	  number of cards to deal
	 */
	public void dealCards(Deck gameDeck ,Gambler player, int n) {
		for(int i=0; i<n; i++) {
			try {
				player.addToHand(gameDeck.draw());
			} catch (Exception emptyDeckErr) {
				emptyDeckErr.getMessage();
			}
		}
		System.out.println(player.getName() + " was dealt " + n + " card(s)");
	}
	/**
	 * Checks if a dealer wants to hit or not. Dealers only hit if their hand value
	 * is below the designated limit.
	 * @return  true if dealer wants to hit, false if dealer wants to stay
	 */
	public boolean willHit() {
		final int DEALER_LIMIT = 17;
		if(getHandValue() < DEALER_LIMIT) return true;
		if(getHandValue() >= DEALER_LIMIT) return false;
		return true;
	}
	
	/**
	 * Shuffles the dealer's deck of cards.
	 * @param gameDeck	deck to shuffle
	 */
	public void shuffleDeck(Deck gameDeck) {
		gameDeck.shuffle();
	}
	
	/**
	 * Adds a card to the dealer's hand.
	 * @param card	new card for hand
	 */
	public void addToHand(Card card) {
		dealerHand.addCard(card);
	}
	
	/**
	 * Gets the dealer's hand.
	 * @return 	dealer's current hand
	 */
	public Hand getHand() {
		return this.dealerHand;
	}
	
	/**
	 * Gets the dealer's hand value.
	 * @return 	sum of the card values in dealer's hand
	 */
	public int getHandValue() {
		return this.dealerHand.getValue();
	}
	
	/**
	 * Removes all cards in a dealer's hand.
	 */
	public void clearHand() {
		this.dealerHand = new Hand();
	}
	
	/**
	 * Gets dealer's chips.
	 * @return 	dealer's current balance
	 */
	public int getChips() {
		return this.chips;
	}
	
	/**
	 * Sets dealer's owned chips.
	 * @param chips	 new amount of chips
	 */
	public void setChips(int chips) {
		this.chips = chips;
	}
	
	/**
	 * The house takes the money if the player loses.
	 */
	public void winPot(Pot pot) {
		pot.resetValue();
	}
	
	/**
	 * Reveals the dealer's face down card.
	 */
	public void showCards() {
		faceUp = true;
	}
	
	/**
	 * Hides the dealer's first face up card.
	 */
	public void hideCards() {
		faceUp = false;
	}
	
	/**
	 * Adds a designated amount to the pot.
	 * @param pot	pot to add 
	 * @param value	amount to add in pot
	 */
	public void putInPot(Pot pot, int value) {
		pot.addToPot(value);
	}
	
	/**
	 * Gets the dealer's name as a string.\
	 * ]
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the start of dealer's turn.
	 */
	public void startTurn() {
		this.myTurn = true;
	}
	
	/**
	 * Sets the end of dealer's turn.
	 */
	public void endTurn() {
		this.myTurn = false;
	}
	
	/**
	 * Gets the dealer's current turn status.
	 * @return 	true if it's dealers turn
	 * 			false if dealer turn is over or hasn't begun
	 */
	public boolean getTurn() {
		return this.myTurn;
	}
	
	/**
	 * Checks if the dealer has revealed his cards or not.
	 * @return	true if dealer card is face up
	 * 			false if dealer card is face down
	 */
	public boolean getFaceUp() {
		return this.faceUp;
	}
	
	/**
	 * Prints the dealer's hand (for debugging).
	 */
	@Override
	public void displayHand() {
		System.out.println(dealerHand);
	}
	
	/**
	 * Check if dealer has busted.
	 * @return	true if dealer score > 21
	 * 			false if dealer score <= 21
	 */
	@Override
	public boolean isBust() {
		if(getHandValue() > 21) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
										
	private Hand dealerHand;	// dealers set of cards
	private String name;		// name of the dealer
	private int chips;			// balance owned by the house
	private boolean myTurn;		// status of dealers turn
	private boolean faceUp;		// status of dealers card visibility
	
}
