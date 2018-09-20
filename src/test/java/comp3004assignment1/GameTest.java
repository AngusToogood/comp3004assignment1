package comp3004assignment1;

import java.util.List;

import junit.framework.TestCase;

public class GameTest extends TestCase{
	public void testFileCommandInput() {
		Deck deck = BlackJack.fileToDeck("src/test/resources/commandTest1.txt");
		List<Character> playerCommands = BlackJack.fileToCommand("src/test/resources/commandTest1.txt");
		assertNotNull(deck);
		assertNotNull(playerCommands);
		assertEquals("Player did not win", true, BlackJack.play(deck, playerCommands));
	}
	public void testBlackjackTie() {
		Deck deck = BlackJack.fileToDeck("src/test/resources/blackjackTie.txt");
		List<Character> playerCommands = BlackJack.fileToCommand("src/test/resources/blackjackTie.txt");
		assertNotNull(deck);
		assertNotNull(playerCommands);
		assertEquals("Dealer did not win", false, BlackJack.play(deck, playerCommands));
	}
	public void testSoft17a() {
		Deck deck = BlackJack.fileToDeck("src/test/resources/soft17a.txt");
		List<Character> playerCommands = BlackJack.fileToCommand("src/test/resources/soft17a.txt");
		assertNotNull(deck);
		assertNotNull(playerCommands);
		assertEquals("Dealer did not win", false, BlackJack.play(deck, playerCommands));
	}
	public void testSoft17b() {
		Deck deck = BlackJack.fileToDeck("src/test/resources/soft17b.txt");
		List<Character> playerCommands = BlackJack.fileToCommand("src/test/resources/soft17b.txt");
		assertNotNull(deck);
		assertNotNull(playerCommands);
		assertEquals("Player did not win", true, BlackJack.play(deck, playerCommands));
	}
	public void testPlayerEdgeWin() {
		Deck deck = BlackJack.fileToDeck("src/test/resources/playerbj.txt");
		List<Character> playerCommands = BlackJack.fileToCommand("src/test/resources/playerbj.txt");
		assertNotNull(deck);
		assertNotNull(playerCommands);
		assertEquals("Player did not win", true, BlackJack.play(deck, playerCommands));
	}
	public void testDealerEdgeWin() {
		Deck deck = BlackJack.fileToDeck("src/test/resources/dealerbj.txt");
		List<Character> playerCommands = BlackJack.fileToCommand("src/test/resources/dealerbj.txt");
		assertNotNull(deck);
		assertNotNull(playerCommands);
		assertEquals("Dealer did not win", false, BlackJack.play(deck, playerCommands));
	}
	public void testPlayerBlackjack() {
		Deck deck = BlackJack.fileToDeck("src/test/resources/playerInitbj.txt");
		List<Character> playerCommands = BlackJack.fileToCommand("src/test/resources/playerInitbj.txt");
		assertNotNull(deck);
		assertNotNull(playerCommands);
		assertEquals("Player did not win", true, BlackJack.play(deck, playerCommands));
	}
	public void testPlayerSplit1() {
		//example file 4
		Deck deck = BlackJack.fileToDeck("src/test/resources/playerSplit1.txt");
		List<Character> playerCommands = BlackJack.fileToCommand("src/test/resources/playerSplit1.txt");
		assertNotNull(deck);
		assertNotNull(playerCommands);
		assertEquals("Player did not win", true, BlackJack.play(deck, playerCommands));
	}
	public void testPlayerSplit2() {
		Deck deck = BlackJack.fileToDeck("src/test/resources/playerSplit2.txt");
		List<Character> playerCommands = BlackJack.fileToCommand("src/test/resources/playerSplit2.txt");
		assertNotNull(deck);
		assertNotNull(playerCommands);
		assertEquals("Player did not lose", false, BlackJack.play(deck, playerCommands));
	}
	public void testDealerSplit1() {
		Deck deck = BlackJack.fileToDeck("src/test/resources/dealerSplit1.txt");
		List<Character> playerCommands = BlackJack.fileToCommand("src/test/resources/dealerSplit1.txt");
		assertNotNull(deck);
		assertNotNull(playerCommands);
		assertEquals("Dealer did not win", false, BlackJack.play(deck, playerCommands));
	}
	public void testDealerSplit2() {
		Deck deck = BlackJack.fileToDeck("src/test/resources/dealerSplit2.txt");
		List<Character> playerCommands = BlackJack.fileToCommand("src/test/resources/dealerSplit2.txt");
		assertNotNull(deck);
		assertNotNull(playerCommands);
		assertEquals("Dealer did not lose", true, BlackJack.play(deck, playerCommands));
	}
	public void testBothSplit1() {
		Deck deck = BlackJack.fileToDeck("src/test/resources/bothSplit1.txt");
		List<Character> playerCommands = BlackJack.fileToCommand("src/test/resources/bothSplit1.txt");
		assertNotNull(deck);
		assertNotNull(playerCommands);
		assertEquals("Dealer did not win", false, BlackJack.play(deck, playerCommands));
	}
	public void testBothSplit2() {
		Deck deck = BlackJack.fileToDeck("src/test/resources/bothSplit2.txt");
		List<Character> playerCommands = BlackJack.fileToCommand("src/test/resources/bothSplit2.txt");
		assertNotNull(deck);
		assertNotNull(playerCommands);
		assertEquals("Player did not win", true, BlackJack.play(deck, playerCommands));
	}
}
