package edu.sjsu.cs.cs151.blackjack.Tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import edu.sjsu.cs.cs151.blackjack.App.*;

/**
 * Tests the Hand class.
 */
public class TestHand {
	Hand testHand;
	Hand emptyTestHand;

	@Before
	public void setUp() {
		// Initialize a full hand of cards
		testHand = new Hand();

		testHand.addCard(new Card("DIAMONDS", 4));
		testHand.addCard(new Card("SPADES", 10));
		testHand.addCard(new Card("CLUBS", 2));
		testHand.addCard(new Card("HEARTS", 5));

		// Initialize an empty hand
		// An empty hand should have "0" value
		emptyTestHand = new Hand();
	}

	@Test
	public void testGetValue() {
		assertEquals(21, testHand.getValue());
		assertEquals(0, emptyTestHand.getValue());
	}

	@Test
	public void testToString() {
		// Test full hand
		String expectedString = "FOUR of DIAMONDS, " + "TEN of SPADES, " + "TWO of CLUBS, " + "FIVE of HEARTS";
		assertEquals(expectedString, testHand.toString());
		// Test empty hand
		expectedString = "";
		assertEquals(expectedString, emptyTestHand.toString());
	}

}
