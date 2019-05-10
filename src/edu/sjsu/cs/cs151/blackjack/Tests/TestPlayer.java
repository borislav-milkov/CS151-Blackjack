package edu.sjsu.cs.cs151.blackjack.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

import org.junit.*;

import edu.sjsu.cs.cs151.blackjack.Model.Card;
import edu.sjsu.cs.cs151.blackjack.Model.Player;
import edu.sjsu.cs.cs151.blackjack.Model.Pot;

/**
 * Tests the player class.
 */
public class TestPlayer {
	Player testPlayer;
	Pot testPot;
	
	@Before
	public void setUp() throws Exception {
		testPlayer = new Player("test");
		testPot = new Pot();
		
		testPlayer.addToHand(new Card("HEARTS", 5));
		testPlayer.addToHand(new Card("CLUBS", 8));
		testPlayer.addToHand(new Card("SPADES", 9));
	}

	@Test
	public void testBalance() {
		// Check correct starting balance
		assertEquals(1000, testPlayer.getChips());
		testPlayer.putInPot(testPot, 400);
		// Check balance is updating correctly
		assertEquals(600, testPlayer.getChips());
	}
	
	@Test
	public void testHand() {
		// Check correct hand value
		assertEquals(22, testPlayer.getHandValue());
		// Check bust functionality
		assertEquals(true, testPlayer.isBust());
	}

}
