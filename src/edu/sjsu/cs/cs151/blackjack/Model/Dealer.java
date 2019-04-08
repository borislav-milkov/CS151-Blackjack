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
	 */
	public void shuffleDeck(Deck gameDeck) {
		gameDeck.shuffle();
	}
	
	public void addToHand(Card card) {
		dealerHand.addCard(card);
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
	
	public String getName() {
		return this.name;
	}
	@Override
	public void displayHand() {
		System.out.println(dealerHand);
		
	}
	@Override
	public boolean isBust() {
		if(getHandValue() > 21) {
			return true;
		}
		return false;
	}
	
										// only the dealer has access to this deck
	private Hand dealerHand;			// Dealer's set of cards
	private String name;
	private int chips;					// Amount of chips owned by dealer
}
