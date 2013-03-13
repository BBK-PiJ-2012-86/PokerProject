/**
 * 
 */
package poker.hand_card;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyObject;
import static poker.hand_card.TestCards.ACE_SPADE;
import static poker.hand_card.TestCards.JACK_CLUB;
import static poker.hand_card.TestCards.JACK_DIAMOND;
import static poker.hand_card.TestCards.JACK_HEART;
import static poker.hand_card.TestCards.JACK_SPADE;
import static poker.hand_card.TestCards.KING_SPADE;
import static poker.hand_card.TestCards.QUEEN_SPADE;
import static poker.hand_card.TestCards.TEN_CLUB;
import static poker.hand_card.TestCards.TEN_DIAMOND;
import static poker.hand_card.TestCards.TEN_SPADE;

import java.util.List;

import org.junit.Test;

public class MultiplesCheckerTest {

	@Test
	public void testFourOfAKind() {
		Card[] inHand = new Card[] {JACK_HEART, JACK_CLUB, QUEEN_SPADE, JACK_SPADE, JACK_DIAMOND};
		Card[] rankSorted = new Card[] {QUEEN_SPADE, JACK_HEART, JACK_CLUB, JACK_SPADE, JACK_DIAMOND};
		Card[] expected = new Card[] {JACK_HEART, JACK_CLUB, JACK_SPADE, JACK_DIAMOND, QUEEN_SPADE};
		
		testMultiples(ConditionType.FourOfAKind, inHand, rankSorted, expected);
	}
	
	@Test
	public void testThreeOfAKind() {
		Card[] inHand = new Card[] {JACK_HEART, JACK_CLUB, QUEEN_SPADE, JACK_SPADE, ACE_SPADE};
		Card[] rankSorted = new Card[] {ACE_SPADE, QUEEN_SPADE, JACK_HEART, JACK_CLUB, JACK_SPADE};
		Card[] expected = new Card[] {JACK_HEART, JACK_CLUB, JACK_SPADE, ACE_SPADE, QUEEN_SPADE};
		
		testMultiples(ConditionType.ThreeOfAKind, inHand, rankSorted, expected);
	}
	
	@Test
	public void testFullHouse() {
		Card[] inHand = new Card[] {TEN_DIAMOND, JACK_CLUB, TEN_SPADE, JACK_SPADE, TEN_CLUB};
		Card[] rankSorted = new Card[] {JACK_CLUB, JACK_SPADE, TEN_DIAMOND, TEN_SPADE, TEN_CLUB};
		Card[] expected = new Card[] {TEN_DIAMOND, TEN_SPADE, TEN_CLUB, JACK_CLUB, JACK_SPADE};
		
		testMultiples(ConditionType.FullHouse, inHand, rankSorted, expected);		
	}
	
	@Test
	public void testTwoPair() {
		Card[] inHand = new Card[] {JACK_DIAMOND, TEN_CLUB, JACK_SPADE, TEN_SPADE, KING_SPADE};
		Card[] rankSorted = new Card[] {KING_SPADE, JACK_DIAMOND, JACK_SPADE, TEN_CLUB, TEN_SPADE};
		Card[] expected = new Card[] {JACK_DIAMOND, JACK_SPADE, TEN_CLUB, TEN_SPADE, KING_SPADE};
		
		testMultiples(ConditionType.TwoPair, inHand, rankSorted, expected);		
	}
	
	@Test
	public void testPair() {
		Card[] inHand = new Card[] {JACK_DIAMOND, TEN_CLUB, KING_SPADE, TEN_SPADE, QUEEN_SPADE};
		Card[] rankSorted = new Card[] {KING_SPADE, QUEEN_SPADE, JACK_DIAMOND, TEN_CLUB, TEN_SPADE };
		Card[] expected = new Card[] {TEN_CLUB, TEN_SPADE, KING_SPADE, QUEEN_SPADE, JACK_DIAMOND };
		
		testMultiples(ConditionType.Pair, inHand, rankSorted, expected);		
	}
	
	@Test
	public void testHighCard() {
		Card[] inHand = new Card[] {JACK_DIAMOND, TEN_CLUB, KING_SPADE, ACE_SPADE, QUEEN_SPADE};
		Card[] rankSorted = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_DIAMOND, TEN_CLUB };
		Card[] expected = rankSorted;
		
		testMultiples(ConditionType.HighCard, inHand, rankSorted, expected);		
	}

	private void testMultiples(ConditionType conditionType, Card[] inHand, Card[] rankSorted, Card[] expected) {
		List<Card> inHandList = TestUtil.toLinkedList(inHand);
		List<Card> rankSortedList = TestUtil.toLinkedList(rankSorted);
		List<Card> expectedList = TestUtil.toLinkedList(expected);

		testMultiplesList(conditionType, inHandList, rankSortedList, expectedList);
	}

	private void testMultiplesList(ConditionType conditionType, List<Card> inHandList, List<Card> rankSortedList, List<Card> expectedList) {
		Hand sortedMockHand = mock(Hand.class);
		when(sortedMockHand.getCards()).thenReturn(rankSortedList);
		
		Hand mockHand = mock(Hand.class);
		when(mockHand.iterator()).thenReturn(inHandList.iterator());
		when(mockHand.sortByRank()).thenReturn(sortedMockHand);
		//when(mockHand.moveCardsToStartOthersRankOrder((List<Card>) anyObject()).thenReturn(sortedMockHand);	//doAnswer..
		
		MultiplesChecker multiplesChecker = new MultiplesChecker();
		CheckResult actual = multiplesChecker.checkMultiples(mockHand);
		
		assertEquals(conditionType, actual.getConditionType());
		assertEquals(expectedList, actual.getSupportingCards().getCards());
	}

}
