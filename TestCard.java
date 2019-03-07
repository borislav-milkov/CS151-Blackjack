package tests;

import edu.sjsu.cs.cs151.blackjack.App.Card;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the Card class.
 */
public class TestCard {
	Card[] cards;

	@Before
	public void setUp() {
		cards = new Card[10];
		// Cards for testing
		cards[0] = new Card("HEARTS", 4);
		cards[1] = new Card("diamonds", 2);
		cards[2] = new Card("SpAdEs", 3);
		cards[3] = new Card("CLUBS", 5);
	}
	
	@Test
	public void testValidCards() {
		// Test Card 1: FOUR of HEARTS
		assertEquals(Card.Suit.HEARTS, cards[0].getSuit());
		assertEquals(Card.Rank.FOUR, cards[0].getRank());
		// Test Card 2: TWO of DIAMONDS
		assertEquals(Card.Suit.DIAMONDS, cards[1].getSuit());
		assertEquals(Card.Rank.TWO, cards[1].getRank());
		// Test Card 3: THREE of SPADES
		assertEquals(Card.Suit.SPADES, cards[2].getSuit());
		assertEquals(Card.Rank.THREE, cards[2].getRank());
		// Test Card 4: FIVE of CLUBS
		assertEquals(Card.Suit.CLUBS, cards[3].getSuit());
		assertEquals(Card.Rank.FIVE, cards[3].getRank());
	}

	@Test
	public void testInvalidCards() {
		// Test Card 5: Invalid Suit
		assertThrows(IllegalArgumentException.class, () -> {
			new Card("####", 4);
		});
		// Test Card 6: Invalid Rank
		assertThrows(IllegalArgumentException.class, () -> {
			new Card("HEARTS", 42);
		});
		// Test Card 7: Invalid Suit & Rank
		assertThrows(IllegalArgumentException.class, () -> {
			new Card("#@#$%", 420);
		});
	}
}
