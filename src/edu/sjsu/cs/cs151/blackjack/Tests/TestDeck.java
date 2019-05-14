package edu.sjsu.cs.cs151.blackjack.Tests;

import org.junit.*;

import edu.sjsu.cs.cs151.blackjack.Model.Card;
import edu.sjsu.cs.cs151.blackjack.Model.Deck;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the Deck class.
 */
public class TestDeck {
	Deck unshuffled_deck;
	Deck unshuffled_deck2;
	
	Deck shuffled_deck;
	Deck shuffled_deck2;
	
	@Before
	public void setUp() {
		unshuffled_deck = new Deck();
		unshuffled_deck2 = new Deck();
		
		shuffled_deck = new Deck();
		shuffled_deck2 = new Deck();
		shuffled_deck.shuffle();
		shuffled_deck2.shuffle();

	}
	
	@Test
	public void testDeckShuffle() {
		//test combinations of shuffled and unshuffled decks
		assertEquals(false, compareDecks(unshuffled_deck, shuffled_deck));
		
		assertEquals(true, compareDecks(new Deck(), new Deck()));
		
		assertEquals(true, compareDecks(unshuffled_deck2, new Deck()));
		
		assertEquals(false, compareDecks(shuffled_deck2, new Deck()));
	}
	
	//helper function to compare deck equality 
	private boolean compareDecks(Deck d1, Deck d2) {
		if (d1.size() == d2.size()) {
			for (int i=0; i<d1.size(); i++) {
				try {
					Card card1 = d1.draw();
					Card card2 = d2.draw();
					
					if(card1.getSuit() != card2.getSuit() || card1.getRank() != card2.getRank()) {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return true;
		}else {
			return false;
		}
	}
}
