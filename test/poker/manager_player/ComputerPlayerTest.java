package poker.manager_player;

import static org.junit.Assert.*;
import static poker.hand_card.TestCards.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import poker.hand_card.Card;
import poker.hand_card.Rank;
import poker.hand_card.Suit;
import poker.hand_card.TestUtil;

public class ComputerPlayerTest {

	private ComputerPlayer computerPlayer;
	
	@Before
	public void setUp(){
		computerPlayer = new ComputerPlayer("Computer 1", AiType.NORMAL);
	}
	
	@Test
	public void testExchangeCardsStraight() {
		Card[] cardsArray1 = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_CLUB, JACK_DIAMOND};
		computerPlayer.receiveCards(TestUtil.toLinkedList(cardsArray1));
		int expected = 0;
		int result = computerPlayer.exchangeCards();
		assertEquals(expected, result);
	}
	
	
}
