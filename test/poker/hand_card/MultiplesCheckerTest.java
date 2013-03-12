/**
 * 
 */
package poker.hand_card;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 
 *
 */
public class MultiplesCheckerTest {

	@Test
	public void testFourOfAKind() {
		Card[] inputCards = new Card[] {JACK_HEART, JACK_CLUB, QUEEN_SPADE, JACK_SPADE, JACK_DIAMOND};
		Card[] rankSortedCards = new Card[] {QUEEN_SPADE, JACK_HEART, JACK_CLUB, JACK_SPADE, JACK_DIAMOND};
		Card[] multiplesExpected = new Card[] {JACK_HEART, JACK_CLUB, JACK_SPADE, JACK_DIAMOND, QUEEN_SPADE};
		
		checkForConditionType(ConditionType.FourOfAKind, inputCards, rankSortedCards, multiplesExpected);
	}
	
	@Test
	public void testThreeOfAKind() {
		Card[] inputCards = new Card[] {JACK_HEART, JACK_CLUB, QUEEN_SPADE, JACK_SPADE, ACE_SPADE};
		Card[] rankSortedCards = new Card[] {ACE_SPADE, QUEEN_SPADE, JACK_HEART, JACK_CLUB, JACK_SPADE};
		Card[] multiplesExpected = new Card[] {JACK_HEART, JACK_CLUB, JACK_SPADE, ACE_SPADE, QUEEN_SPADE};
		
		checkForConditionType(ConditionType.ThreeOfAKind, inputCards, rankSortedCards, multiplesExpected);		
	}
	
	@Test
	public void testFullHouseOfAKind() {
		Card[] inputCards = new Card[] {TEN_DIAMOND, JACK_CLUB, TEN_SPADE, JACK_SPADE, TEN_CLUB};
		Card[] rankSortedCards = new Card[] {JACK_CLUB, JACK_SPADE, TEN_DIAMOND, TEN_SPADE, TEN_CLUB};
		Card[] multiplesExpected = new Card[] {TEN_DIAMOND, TEN_SPADE, TEN_CLUB, JACK_CLUB, JACK_SPADE};
		
		checkForConditionType(ConditionType.FullHouse, inputCards, rankSortedCards, multiplesExpected);		
	}
	
	@Test
	public void testTwoPair() {
		Card[] inputCards = new Card[] {JACK_DIAMOND, TEN_CLUB, JACK_SPADE, TEN_SPADE, KING_SPADE};
		Card[] rankSortedCards = new Card[] {KING_SPADE, JACK_DIAMOND, JACK_SPADE, TEN_CLUB, TEN_SPADE};
		Card[] multiplesExpected = new Card[] {JACK_DIAMOND, JACK_SPADE, TEN_CLUB, TEN_SPADE, KING_SPADE};
		
		checkForConditionType(ConditionType.TwoPair, inputCards, rankSortedCards, multiplesExpected);		
	}
	
	@Test
	public void testPair() {
		Card[] inputCards = new Card[] {JACK_DIAMOND, TEN_CLUB, KING_SPADE, TEN_SPADE, QUEEN_SPADE};
		Card[] rankSortedCards = new Card[] {KING_SPADE, QUEEN_SPADE, JACK_DIAMOND, TEN_CLUB, TEN_SPADE };
		Card[] multiplesExpected = new Card[] {TEN_CLUB, TEN_SPADE, KING_SPADE, QUEEN_SPADE, JACK_DIAMOND };
		
		checkForConditionType(ConditionType.Pair, inputCards, rankSortedCards, multiplesExpected);		
	}
	
	@Test
	public void testHighCard() {
		Card[] inputCards = new Card[] {JACK_DIAMOND, TEN_CLUB, KING_SPADE, ACE_SPADE, QUEEN_SPADE};
		Card[] rankSortedCards = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_DIAMOND, TEN_CLUB };
		Card[] multiplesExpected = rankSortedCards;
		
		checkForConditionType(ConditionType.HighCard, inputCards, rankSortedCards, multiplesExpected);		
	}

	private void checkForConditionType(ConditionType conditionType, Card[] inputCards, Card[] rankSortedCards, Card[] multiplesExpected) {
		List<Card> inputList = new LinkedList<Card>();
		for (Card card : inputCards) {
			inputList.add(card);
		}
		
		List<Card> multiplesExpectedList = new LinkedList<Card>();
		for (Card card : multiplesExpected) {
			multiplesExpectedList.add(card);
		}
		
		List<Card> rankSortedCardsList = new LinkedList<Card>();
		for (Card card : rankSortedCards) {
			rankSortedCardsList.add(card);
		}
				
		checkForConditionTypeList(conditionType, inputList, multiplesExpectedList, rankSortedCardsList);
	}

	private void checkForConditionTypeList(ConditionType conditionType, List<Card> inputList, List<Card> multiplesExpectedList, List<Card> rankSortedCardsList) {
		Hand sortedMockHand = mock(Hand.class);
		when(sortedMockHand.getCards()).thenReturn(rankSortedCardsList);
		
		Hand mockHand = mock(Hand.class);
		when(mockHand.iterator()).thenReturn(inputList.iterator());
		when(mockHand.sortByRank()).thenReturn(sortedMockHand);
		
		MultiplesChecker multiplesChecker = new MultiplesChecker();
		CheckResult actual = multiplesChecker.checkMultiples(mockHand);
		
		assertEquals(conditionType, actual.getConditionType());
		assertEquals(multiplesExpectedList, actual.getSupportingCards().getCards());
	}

}
