/**
 * 
 */
package poker;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyObject;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * @author 86
 *
 */
public class CheckerImplTest {
	private static final Card ACE_OF_SPADES = new Card(Rank.Ace, Suit.Spades);
	private static final Card KING_OF_SPADES = new Card(Rank.King, Suit.Spades);
	private static final Card QUEEN_OF_SPADES = new Card(Rank.Queen, Suit.Spades);
	private static final Card JACK_OF_SPADES = new Card(Rank.Jack, Suit.Spades);
	private static final Card TEN_OF_SPADES = new Card(Rank.Ten, Suit.Spades);
	private static final Card NINE_OF_SPADES = new Card(Rank.Nine, Suit.Spades);
	
	private static final Card JACK_OF_HEARTS = new Card(Rank.Jack, Suit.Hearts);
	private static final Card JACK_OF_CLUBS = new Card(Rank.Jack, Suit.Clubs);
	private static final Card JACK_OF_DIAMONDS = new Card(Rank.Jack, Suit.Diamonds);
	
	private static final Card TEN_OF_CLUBS = new Card(Rank.Ten, Suit.Clubs);
	
	@Test
	public void testCheckStraightFlush() {
		Card[] inputCards = new Card[] {TEN_OF_SPADES, KING_OF_SPADES, JACK_OF_SPADES, QUEEN_OF_SPADES, ACE_OF_SPADES};
		Card[] multiplesExpected = new Card[] {};
		Card[] rankSortedCards = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_SPADES};
		Card[] expectedCards = rankSortedCards;
		
		
		testForConditionType(ConditionType.StraightFlush, inputCards, rankSortedCards, multiplesExpected, expectedCards);
	}
	
	@Test
	public void testCheckFlush() {
		Card[] inputCards = new Card[] {ACE_OF_SPADES, NINE_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, KING_OF_SPADES};
		Card[] multiplesExpected = new Card[] {};
		Card[] rankSortedCards = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, NINE_OF_SPADES};
		Card[] expectedCards = rankSortedCards;
		
		testForConditionType(ConditionType.Flush, inputCards, rankSortedCards, multiplesExpected, expectedCards);
	}
	
	@Test
	public void testCheckStraight() {
		Card[] inputCards = new Card[] {QUEEN_OF_SPADES, KING_OF_SPADES, TEN_OF_CLUBS, JACK_OF_SPADES, ACE_OF_SPADES};
		Card[] multiplesExpected = new Card[] {};
		Card[] rankSortedCards = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_CLUBS};
		Card[] expectedCards = rankSortedCards;
		
		testForConditionType(ConditionType.Straight, inputCards, rankSortedCards, multiplesExpected, expectedCards);
	}
	
	@Test
	public void testCheckFourOfAKind() {
		Card[] inputCards = new Card[] {JACK_OF_HEARTS, JACK_OF_CLUBS, QUEEN_OF_SPADES, JACK_OF_SPADES, JACK_OF_DIAMONDS};
		Card[] multiplesExpected = new Card[] {JACK_OF_HEARTS, JACK_OF_CLUBS, JACK_OF_SPADES, JACK_OF_DIAMONDS, QUEEN_OF_SPADES};
		Card[] rankSortedCards = new Card[] {QUEEN_OF_SPADES, JACK_OF_HEARTS, JACK_OF_CLUBS, JACK_OF_SPADES, JACK_OF_DIAMONDS};
		Card[] expectedCards = new Card[] {JACK_OF_HEARTS, JACK_OF_CLUBS, JACK_OF_SPADES, JACK_OF_DIAMONDS, QUEEN_OF_SPADES};
		
		testForConditionType(ConditionType.FourOfAKind, inputCards, rankSortedCards, multiplesExpected, expectedCards);
	}
	
	@Test
	public void testFullHouse() {
		Card[] inputCards = new Card[] {JACK_OF_HEARTS, TEN_OF_CLUBS, TEN_OF_SPADES, JACK_OF_SPADES, JACK_OF_DIAMONDS};
		Card[] multiplesExpected = new Card[] {JACK_OF_HEARTS, JACK_OF_SPADES, JACK_OF_DIAMONDS, TEN_OF_CLUBS, TEN_OF_SPADES};
		Card[] rankSortedCards = multiplesExpected;
		Card[] expectedCards = multiplesExpected;
		
		testForConditionType(ConditionType.FullHouse, inputCards, rankSortedCards, multiplesExpected, expectedCards);
	}

	private void testForConditionType(ConditionType conditionType, Card[] inputCards, Card[] rankSortedCards, Card[] multiplesExpected, Card[] expectedCards) {
		List<Card> inputList = Arrays.asList(inputCards);
		List<Card> rankSortedCardsList = Arrays.asList(rankSortedCards);
		List<Card> multiplesExpectedList = Arrays.asList(multiplesExpected);
		List<Card> expectedCardsList = Arrays.asList(expectedCards);
		
		testForConditionTypeLists(conditionType, inputList, rankSortedCardsList, multiplesExpectedList, expectedCardsList);
	}

	private void testForConditionTypeLists(ConditionType conditionType, List<Card> inputList, List<Card> rankSortedCardsList,
			List<Card> multiplesExpectedList, List<Card> expectedCardsList) {
		
		MultiplesChecker mockMultiplesChecker = mock(MultiplesChecker.class);
		
		MultiplesCheckerFactory.getInstance().setMockChecker(mockMultiplesChecker);
		
		setFakeCheckMultiples(conditionType, multiplesExpectedList, mockMultiplesChecker);
		
		Hand sortedMockHand = mock(Hand.class);
		when(sortedMockHand.getCards()).thenReturn(rankSortedCardsList).thenReturn(rankSortedCardsList);
		when(sortedMockHand.iterator()).thenReturn(rankSortedCardsList.iterator()).thenReturn(rankSortedCardsList.iterator());
		
		Hand mockHand = mock(Hand.class);
		when(mockHand.getCards()).thenReturn(inputList).thenReturn(inputList).thenReturn(inputList).thenReturn(inputList);
		when(mockHand.iterator()).thenReturn(inputList.iterator()).thenReturn(inputList.iterator());
		when(mockHand.sortByRank()).thenReturn(sortedMockHand).thenReturn(sortedMockHand);
		
		CheckResult result = (new CheckerImpl()).check(mockHand);
		
		assertEquals(conditionType, result.getConditionType());
		assertEquals(expectedCardsList, result.getSupportingCards().getCards());
	}

	private void setFakeCheckMultiples(ConditionType conditionType, List<Card> multiplesExpectedList,
			MultiplesChecker mockMultiplesChecker) {
		List<CheckResult> multiplesResults = new LinkedList<CheckResult>();
		if (!(conditionType.equals(ConditionType.StraightFlush)||conditionType.equals(ConditionType.Straight)||conditionType.equals(ConditionType.Flush))) {
			CheckResult fakeResult = new CheckResult(conditionType, new HandImpl(multiplesExpectedList));
			multiplesResults.add(fakeResult);
		}
		when(mockMultiplesChecker.checkMultiples((Hand) anyObject())).thenReturn(multiplesResults);
	}
}
