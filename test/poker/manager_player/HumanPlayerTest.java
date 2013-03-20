package poker.manager_player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static poker.hand_card.TestCards.ACE_SPADE;
import static poker.hand_card.TestCards.NINE_SPADE;
import static poker.hand_card.TestCards.QUEEN_SPADE;
import static poker.hand_card.TestCards.SIX_CLUB;
import static poker.hand_card.TestCards.TEN_CLUB;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import poker.hand_card.Card;
import poker.hand_card.TestUtil;

public class HumanPlayerTest {

	private HumanPlayer player;
	private HumanPlayerListener listener;
	
	@Before
	public void setUp(){
		listener = mock(HumanPlayerListener.class);
		player = new HumanPlayer("Sarah", listener);
		player.changeGameType(GameType.FIVE_CARD_DRAW);
		Card[] cardArray = new Card[] {ACE_SPADE, QUEEN_SPADE, TEN_CLUB, NINE_SPADE, SIX_CLUB};
		player.receiveCards(TestUtil.toLinkedList(cardArray));
	}
	
	@Test
	public void testExchangeCardsNone() {
		when(listener.getCountOfCardsToSwap(anyInt())).thenReturn(0);
		int expected = 0;
		int actual = player.exchangeCards();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testExchangeCardsOne() {
		when(listener.getCountOfCardsToSwap(anyInt())).thenReturn(1);
		List<Card> FakeCardsToRemove = new LinkedList<Card>();
		FakeCardsToRemove.add(ACE_SPADE);
		when(listener.selectCardsToRemove(anyInt())).thenReturn(FakeCardsToRemove);
		int expectedInt = 1;
		int actualInt = player.exchangeCards();
		assertEquals(expectedInt, actualInt);
		
		Card[] cardArray = new Card[] {QUEEN_SPADE, TEN_CLUB, NINE_SPADE, SIX_CLUB};
		List<Card> expectedCards = TestUtil.toLinkedList(cardArray);
		List<Card> actualCards = player.getHand().getCards();		//mock the Hand as well??
		assertEquals(expectedCards, actualCards);
	}
	
}
