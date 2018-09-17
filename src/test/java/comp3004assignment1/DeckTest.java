package comp3004assignment1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;

import junit.framework.TestCase;

public class DeckTest extends TestCase{
	public void testDraw() {
		Deck deck = new Deck();
		assertEquals("New Deck not full", 52, deck.size());
		assertEquals("First card of unshuffled deck not ace of spades",
				"SA", deck.draw());
		assertEquals("Drawing does not decrease size of deck",
				51, deck.size());
		assertEquals("Second card of unshuffled deck not 2 of spades",
				"S2", deck.draw());
		assertEquals("Drawing does not decrease size of deck",
				50, deck.size());
		//writing a test case for every card in the deck would take too long.
	}
	public void testShuffle() {
		Deck unshuffled = new Deck();
		Deck shuffled = new Deck();
		shuffled.shuffle();
		assertNotEquals("Deck does not shuffle", unshuffled, shuffled);
		assertEquals("Shuffled deck wrong size", 52, shuffled.size());
	}
}
