package comp3004assignment1;

import junit.framework.TestCase;

public class HandTest extends TestCase {
	public void testDraw() {
		PlayerHand p = new PlayerHand();
		DealerHand d = new DealerHand();
		Deck deck = BlackJack.fileToDeck("src/test/resources/fileReadTest.txt");
		p.add(deck.draw());
		p.add(deck.draw());
		assertEquals(2, p.size());
		assertEquals("Player: C3 HK (13)", p.toString());
		d.add(deck.draw());
		d.add(deck.draw());
		assertEquals(2, d.size());
		d.turn = false;
		assertEquals("Dealer: S2 ** ", d.toString());
		d.turn = true;
		assertEquals("Dealer: S2 D7 (9)", d.toString());
	}
}
