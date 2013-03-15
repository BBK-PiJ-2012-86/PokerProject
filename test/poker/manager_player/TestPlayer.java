package poker.manager_player;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import poker.hand_card.Card;
import poker.hand_card.Deck;
import poker.hand_card.DeckFactory;

public class TestPlayer {

	private Player player;
	private Deck deck;
	
	@Before
	public void setUp(){
		player = new HumanPlayer("Ted", GameType.fiveCardDraw);
		deck = DeckFactory.getDeckFactory().getDeck();
	}
	
	@Test
	public void testGetUsername() {
		assertEquals("Ted", player.getUsername());
	}
	
	@Test
	public void testRecieveCards(){
		List<Card> cards = deck.dealCards(5);
		player.recieveCards(cards);
		int expected = 5;
		int result = player.getHand().getCards().size();
		assertEquals(expected, result);
	}
	
	@Test
	public void testRemoveCards(){
		List<Card> cards = deck.dealCards(5);
		player.recieveCards(cards);
		player.removeCards();
		assertTrue(player.getHand().getCards().size() == 0);
	}
}
