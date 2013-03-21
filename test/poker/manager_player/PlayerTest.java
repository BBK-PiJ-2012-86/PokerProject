package poker.manager_player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static poker.hand_card.TestCards.ACE_SPADE;
import static poker.hand_card.TestCards.JACK_CLUB;
import static poker.hand_card.TestCards.JACK_DIAMOND;
import static poker.hand_card.TestCards.JACK_HEART;
import static poker.hand_card.TestCards.JACK_SPADE;
import static poker.hand_card.TestCards.KING_SPADE;
import static poker.hand_card.TestCards.QUEEN_SPADE;
import static poker.hand_card.TestCards.SIX_CLUB;
import static poker.hand_card.TestCards.SIX_SPADE;
import static poker.hand_card.TestCards.TEN_CLUB;
import static poker.hand_card.TestCards.TEN_DIAMOND;
import static poker.hand_card.TestCards.TEN_SPADE;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import poker.hand_card.Card;
import poker.hand_card.TestUtil;

public class PlayerTest {

	private Player player1;
	private Player player2;
	
	
	@Before
	public void setUp(){
		HumanPlayerListener listener = mock(HumanPlayerListener.class);
		player1 = new HumanPlayer("Ted", listener);
		player2 = new HumanPlayer("Ruth", listener);
		player1.changeGameType(GameType.FIVE_CARD_DRAW);
		player2.changeGameType(GameType.FIVE_CARD_DRAW);
	}
	
	@Test
	public void testGetUsername() {
		assertEquals("Ted", player1.getUsername());
	}
	
	@Test
	public void testReceiveCards(){
		Card[] cardsArray1 = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_CLUB, JACK_DIAMOND};
		player1.receiveCards(TestUtil.toLinkedList(cardsArray1));
		int expected = 5;
		int actual = player1.getHand().getCards().size();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRemoveCards(){
		Card[] cardsArray1 = new Card[] {ACE_SPADE, KING_SPADE};
		player1.receiveCards(TestUtil.toLinkedList(cardsArray1));
		player1.removeCards();
		
		assertTrue(player1.getHand().getCards().size() == 0);
	}
	
	@Test
	public void testComparatorSameHandStrength(){
		Card[] cardsArray1 = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_CLUB, JACK_DIAMOND};
		player1.receiveCards(TestUtil.toLinkedList(cardsArray1));
		Card[] cardsArray2 = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_CLUB, JACK_HEART};
		player2.receiveCards(TestUtil.toLinkedList(cardsArray2));
		Comparator<Player> comp = Player.getCheckResultRanking();
		
		assertTrue(comp.compare(player1, player2)==0);
	}
	
	@Test
	public void testComparatorJacksOverTens(){
		Card[] player1CardArray = new Card[] {JACK_SPADE, JACK_CLUB, SIX_CLUB, SIX_SPADE, TEN_DIAMOND};
		player1.receiveCards(TestUtil.toLinkedList(player1CardArray));
		Card[] player2CardArray = new Card[] {TEN_SPADE, TEN_CLUB, SIX_CLUB, SIX_SPADE, JACK_HEART};
		player2.receiveCards(TestUtil.toLinkedList(player2CardArray));
		Comparator<Player> comp = Player.getCheckResultRanking();
		
		assertTrue(comp.compare(player1, player2)>0);
	}
	
}