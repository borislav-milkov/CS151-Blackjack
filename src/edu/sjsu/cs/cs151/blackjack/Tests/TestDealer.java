package edu.sjsu.cs.cs151.blackjack.Tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import edu.sjsu.cs.cs151.blackjack.Model.Card;
import edu.sjsu.cs.cs151.blackjack.Model.Dealer;
import edu.sjsu.cs.cs151.blackjack.Model.Deck;
import edu.sjsu.cs.cs151.blackjack.Model.Player;

/**
 * Tests the Dealer class.
 */
public class TestDealer {
	Dealer testDealer;
	Deck testDeck;
	List<Player> testPlayers;

	@Before
	public void setUp() throws Exception {
		testDealer = new Dealer();
		testPlayers = new ArrayList<Player>();
		testDeck = new Deck();
		testPlayers.add(new Player("hitMe"));
		testPlayers.add(new Player("hitMe"));
		testPlayers.add(new Player("hitMe"));
	}

	@Test
	public void testDeal() {
		for(Player p : testPlayers) {
			assertEquals(true, p.getHandValue() == 0);	// all hands are empty before dealing
			testDealer.dealCards(testDeck, p, 1);
			assertEquals(true, p.getHandValue() > 0);	// all hands are not empty after dealing
		}
	}
	
	@Test
	public void testHit() {
		// Dealer should hit on an empty hand
		assertEquals(true, testDealer.getHandValue() == 0);
		assertEquals(true, testDealer.willHit());
		// Dealer should hit on 10
		testDealer.addToHand(new Card("DIAMONDS", 10));
		assertEquals(true, testDealer.willHit());
		// Dealer should not hit on 17
		testDealer.addToHand(new Card("SPADES", 7));
		assertEquals(false, testDealer.willHit());
	}

}
