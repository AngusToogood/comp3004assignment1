package comp3004assignment1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;

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
	public void testFileInput() {
		Deck deck = BlackJack.fileToDeck("src/test/resources/fileReadTest.txt");
		assertEquals("Wrong number of cards", 4, deck.size());
		assertEquals("Wrong first card", "C3", deck.draw());
		assertEquals("Wrong second card", "HK", deck.draw());
		assertEquals("Wrong third card", "S2", deck.draw());
		assertEquals("Wrong fourth card", "D7", deck.draw());
	}
	public void testIncorrectFileInput() {
		assertEquals("Incorrect format accepted", null, BlackJack.fileToDeck("src/test/resources/badInput1.txt"));
		assertEquals("Blank file accepted", null, BlackJack.fileToDeck("src/test/resources/badInput2.txt"));
		assertEquals("Duplicate cards accepted", null, BlackJack.fileToDeck("src/test/resources/badInput3.txt"));
		assertEquals("Invalid cards accepted", null, BlackJack.fileToDeck("src/test/resources/badInput4.txt"));
	}
}
