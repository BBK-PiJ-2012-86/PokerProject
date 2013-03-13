/**
 * 
 */
package poker.hand_card;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static poker.hand_card.TestCards.ACE_SPADE;
import static poker.hand_card.TestCards.FIVE_CLUB;
import static poker.hand_card.TestCards.FOUR_CLUB;
import static poker.hand_card.TestCards.JACK_CLUB;
import static poker.hand_card.TestCards.JACK_DIAMOND;
import static poker.hand_card.TestCards.JACK_HEART;
import static poker.hand_card.TestCards.JACK_SPADE;
import static poker.hand_card.TestCards.KING_SPADE;
import static poker.hand_card.TestCards.NINE_SPADE;
import static poker.hand_card.TestCards.QUEEN_SPADE;
import static poker.hand_card.TestCards.TEN_CLUB;
import static poker.hand_card.TestCards.TEN_SPADE;
import static poker.hand_card.TestCards.THREE_CLUB;
import static poker.hand_card.TestCards.TWO_CLUB;

import java.util.List;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


/**
 * 
 *
 */
public class CheckerImplTest {
	// pre-made cards like TEN_SPADE imported from TestCards to avoid repetition in tests
	
	@Test
	public void testCheckStraightFlush() {
		Card[] inputCards = new Card[] {TEN_SPADE, KING_SPADE, JACK_SPADE, QUEEN_SPADE, ACE_SPADE};
		Card[] multiplesExpected = new Card[] {};
		Card[] rankSortedCards = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_SPADE, TEN_SPADE};
		Card[] expectedCards = rankSortedCards;
		
		testChecker(ConditionType.StraightFlush, inputCards, rankSortedCards, multiplesExpected, expectedCards);
	}
	
	@Test
	public void testCheckFlush() {
		Card[] inputCards = new Card[] {ACE_SPADE, NINE_SPADE, QUEEN_SPADE, JACK_SPADE, KING_SPADE};
		Card[] multiplesExpected = new Card[] {};
		Card[] rankSortedCards = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_SPADE, NINE_SPADE};
		Card[] expectedCards = rankSortedCards;
		
		testChecker(ConditionType.Flush, inputCards, rankSortedCards, multiplesExpected, expectedCards);
	}
	
	@Test
	public void testCheckStraight() {
		Card[] inputCards = new Card[] {QUEEN_SPADE, KING_SPADE, TEN_CLUB, JACK_SPADE, ACE_SPADE};
		Card[] multiplesExpected = new Card[] {};
		Card[] rankSortedCards = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_SPADE, TEN_CLUB};
		Card[] expectedCards = rankSortedCards;
		
		testChecker(ConditionType.Straight, inputCards, rankSortedCards, multiplesExpected, expectedCards);
	}
	
	@Test
	public void testCheckWheelStraight() {
		Card[] inputCards = new Card[] {FIVE_CLUB, ACE_SPADE, FOUR_CLUB, THREE_CLUB, TWO_CLUB};
		Card[] multiplesExpected = new Card[] {};
		Card[] rankSortedCards = new Card[] {ACE_SPADE, FIVE_CLUB, FOUR_CLUB, THREE_CLUB, TWO_CLUB};
		Card[] expectedCards = new Card[] {FIVE_CLUB, FOUR_CLUB, THREE_CLUB, TWO_CLUB, ACE_SPADE};
		
		testChecker(ConditionType.Straight, inputCards, rankSortedCards, multiplesExpected, expectedCards);
	}
	
	@Test
	public void testCheckFourOfAKind() {
		Card[] inputCards = new Card[] {JACK_HEART, JACK_CLUB, QUEEN_SPADE, JACK_SPADE, JACK_DIAMOND};
		Card[] multiplesExpected = new Card[] {JACK_HEART, JACK_CLUB, JACK_SPADE, JACK_DIAMOND, QUEEN_SPADE};
		Card[] rankSortedCards = new Card[] {QUEEN_SPADE, JACK_HEART, JACK_CLUB, JACK_SPADE, JACK_DIAMOND};
		Card[] expectedCards = new Card[] {JACK_HEART, JACK_CLUB, JACK_SPADE, JACK_DIAMOND, QUEEN_SPADE};
		
		testChecker(ConditionType.FourOfAKind, inputCards, rankSortedCards, multiplesExpected, expectedCards);
	}
	
	@Test
	public void testFullHouse() {
		Card[] inputCards = new Card[] {JACK_HEART, TEN_CLUB, TEN_SPADE, JACK_SPADE, JACK_DIAMOND};
		Card[] multiplesExpected = new Card[] {JACK_HEART, JACK_SPADE, JACK_DIAMOND, TEN_CLUB, TEN_SPADE};
		Card[] rankSortedCards = multiplesExpected;
		Card[] expectedCards = multiplesExpected;
		
		testChecker(ConditionType.FullHouse, inputCards, rankSortedCards, multiplesExpected, expectedCards);
	}

	private void testChecker(ConditionType conditionType, Card[] inputCards, Card[] rankSortedCards, Card[] multiplesExpected, Card[] expectedCards) {
		List<Card> inputList = TestUtil.toLinkedList(inputCards);
		List<Card> rankSortedCardsList = TestUtil.toLinkedList(rankSortedCards);
		List<Card> multiplesExpectedList = TestUtil.toLinkedList(multiplesExpected);
		List<Card> expectedCardsList = TestUtil.toLinkedList(expectedCards);
		
		testCheckerLists(conditionType, inputList, rankSortedCardsList, multiplesExpectedList, expectedCardsList);
	}

	private void testCheckerLists(ConditionType conditionType, List<Card> inputList, final List<Card> rankSortedCardsList,
			List<Card> multiplesExpectedList, List<Card> expectedCardsList) {
		
		MultiplesChecker mockMultiplesChecker = mock(MultiplesChecker.class);
		
		MultiplesCheckerFactory.getInstance().setMockChecker(mockMultiplesChecker);
		
		setFakeCheckMultiples(conditionType, multiplesExpectedList, mockMultiplesChecker);
		
		Hand sortedMockHand = mock(Hand.class);
		when(sortedMockHand.getCards()).thenReturn(rankSortedCardsList);
		when(sortedMockHand.iterator()).thenReturn(rankSortedCardsList.iterator());

		when(sortedMockHand.getCardAt(anyInt())).thenAnswer(new Answer<Card>() {
		    @Override
		    public Card answer(InvocationOnMock invocation) throws Throwable {
		      Object[] args = invocation.getArguments();
		      return rankSortedCardsList.get((int) args[0]);
		    }
		  });
		
		Hand mockHand = mock(Hand.class);
		when(mockHand.getCards()).thenReturn(inputList);
		when(mockHand.iterator()).thenReturn(inputList.iterator());
		when(mockHand.sortByRank()).thenReturn(sortedMockHand);
		
		CheckResult result = (new CheckerImpl()).check(mockHand);
		
		assertEquals(conditionType, result.getConditionType());
		assertEquals(expectedCardsList, result.getOrderedHand().getCards());
	}

	private void setFakeCheckMultiples(ConditionType conditionType, List<Card> multiplesExpectedList,
			MultiplesChecker mockMultiplesChecker) {
		CheckResult fakeResult;
		if (conditionType.equals(ConditionType.StraightFlush)||conditionType.equals(ConditionType.Straight)||conditionType.equals(ConditionType.Flush)) {
			fakeResult = new CheckResult(ConditionType.HighCard, new HandImpl(multiplesExpectedList));
		} else {
			fakeResult = new CheckResult(conditionType, new HandImpl(multiplesExpectedList));
		}
		when(mockMultiplesChecker.checkMultiples((Hand) anyObject())).thenReturn(fakeResult);
	}
}
