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

}
