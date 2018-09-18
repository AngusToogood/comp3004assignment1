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
		p.clear();
		assertEquals(0, p.size());
		d.clear();
		assertEquals(0, d.size());
	}
	public void testAceScore() {
		PlayerHand p = new PlayerHand();
		p.add("SA");
		assertEquals(11, p.score());
		p.add("CA");
		assertEquals(12, p.score());
		p.clear();
		
		p.add("DA");
		p.add("HK");
		assertEquals(21, p.score());
		p.clear();
		
		p.add("D9");
		p.add("D3");
		p.add("HA");
		assertEquals(13, p.score());
		
	}
}
