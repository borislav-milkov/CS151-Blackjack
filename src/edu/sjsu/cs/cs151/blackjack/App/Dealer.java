package edu.sjsu.cs.cs151.blackjack.App;

/**
 * The Dealer class models a dealer for a blackjack table.
 */
public class Dealer {

	/**
	 * Constructs a Dealer with desired amount of starting chips and
	 * an empty hand by default.
	 * @param startChips  starting amount of chips
	 */
	public Dealer(int startChips) {
		this.chips = startChips;
		dealerHand = new Hand();
	}
	
	/**
	 * Deals n cards to a player.
	 * @param playerHand  player to deal cards
	 * @param n		  	  number of cards to deal
	 */
	public void dealCards(Player player, int n) {
		for(int i=0; i<n; i++) {
			try {
				player.addToHand(gameDeck.draw());
			} catch (Exception emptyDeckErr) {
				emptyDeckErr.getMessage();
			}
		}
	}
	
	/**
	 * Gives or takes chips from players appropriately
	 * @param player  player to give/take chips from
	 * @param amount  positive number to add, negative number to remove
	 */
	public void updatePlayerChips(Player player, int amount) {
		//TODO: implement PLAYER class, choose better method name
	}
	
	public void hit() {
		try {
			dealerHand.addCard(gameDeck.draw());
		} catch (Exception emptyDeckErr) {
			emptyDeckErr.getMessage();
		}
	}
	
	/**
	 * Shuffles the dealer's deck of cards.
	 */
	public void shuffleDeck() {
		gameDeck.shuffle();
	}
	
	public Hand getHand() {
		return this.dealerHand;
	}
	
	public int getHandValue() {
		return this.dealerHand.getValue();
	}
	
	// Accessor for dealer's chips
	public int getChips() {
		return this.chips;
	}
	// Mutator for dealer's chips
	public void setChips(int chips) {
		this.chips = chips;
	}
	
	
	private Deck gameDeck = new Deck();	// The primary deck for each round of blackjack
										// only the dealer has access to this deck
	private Hand dealerHand;			// Dealer's set of cards
	private int chips;					// Amount of chips owned by dealer
}
