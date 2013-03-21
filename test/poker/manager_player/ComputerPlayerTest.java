package poker.manager_player;

import static org.junit.Assert.assertEquals;
import static poker.hand_card.TestCards.ACE_SPADE;
import static poker.hand_card.TestCards.JACK_CLUB;
import static poker.hand_card.TestCards.JACK_DIAMOND;
import static poker.hand_card.TestCards.KING_SPADE;
import static poker.hand_card.TestCards.QUEEN_SPADE;
import static poker.hand_card.TestCards.TEN_DIAMOND;

import org.junit.Before;
import org.junit.Test;

import poker.hand_card.Card;
import poker.hand_card.TestUtil;

public class ComputerPlayerTest {

	private ComputerPlayer computerPlayer;
	
	@Before
	public void setUp(){
		computerPlayer = new ComputerPlayer("Computer 1", AiType.NORMAL);
		computerPlayer.changeGameType(GameType.FIVE_CARD_DRAW);
	}
	
	@Test
	public void testExchangeCardsStraight() {
		Card[] cardsArray = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_CLUB, TEN_DIAMOND};
		computerPlayer.receiveCards(TestUtil.toLinkedList(cardsArray));
		int expected = 0;
		int actual = computerPlayer.exchangeCards();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testExchangeCardsPair() {
		Card[] cardsArray = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_CLUB, JACK_DIAMOND};
		computerPlayer.receiveCards(TestUtil.toLinkedList(cardsArray));
		int expected = 2;
		int actual = computerPlayer.exchangeCards();
		assertEquals(expected, actual);
	}	
	
	
}
