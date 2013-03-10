/**
 * 
 */
package poker;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
		Card[] cardArray = new Card[] {TEN_OF_SPADES, KING_OF_SPADES, JACK_OF_SPADES, QUEEN_OF_SPADES, ACE_OF_SPADES};
		Card[] multiplesExpected = new Card[] {};
		Card[] expectedCards = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_SPADES};
		
		
		testForConditionType(ConditionType.StraightFlush, cardArray, multiplesExpected, expectedCards);
	}
	
	@Test
	public void testCheckFlush() {
		Card[] cardArray = new Card[] {ACE_OF_SPADES, NINE_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, KING_OF_SPADES};
		Card[] multiplesExpected = new Card[] {};
		Card[] expectedCards = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, NINE_OF_SPADES};
		
		testForConditionType(ConditionType.Flush, cardArray, multiplesExpected, expectedCards);
	}
	
	@Test
	public void testCheckStraight() {
		Card[] cardArray = new Card[] {QUEEN_OF_SPADES, KING_OF_SPADES, TEN_OF_CLUBS, JACK_OF_SPADES, ACE_OF_SPADES};
		Card[] multiplesExpected = new Card[] {};
		Card[] expectedCards = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_CLUBS};
		
		testForConditionType(ConditionType.Straight, cardArray, multiplesExpected, expectedCards);
	}
	
	@Test
	public void testCheckFourOfAKind() {
		Card[] cardArray = new Card[] {JACK_OF_HEARTS, JACK_OF_CLUBS, QUEEN_OF_SPADES, JACK_OF_SPADES, JACK_OF_DIAMONDS};
		Card[] multiplesExpected = new Card[] {JACK_OF_HEARTS, JACK_OF_CLUBS, JACK_OF_SPADES, JACK_OF_DIAMONDS, QUEEN_OF_SPADES};
		Card[] expectedCards = new Card[] {JACK_OF_HEARTS, JACK_OF_CLUBS, JACK_OF_SPADES, JACK_OF_DIAMONDS, QUEEN_OF_SPADES};
		
		testForConditionType(ConditionType.FourOfAKind, cardArray, multiplesExpected, expectedCards);
	}
	
	@Test
	public void testFullHouse() {
		Card[] cardArray = new Card[] {JACK_OF_HEARTS, TEN_OF_CLUBS, TEN_OF_SPADES, JACK_OF_SPADES, JACK_OF_DIAMONDS};
		Card[] multiplesExpected = new Card[] {JACK_OF_HEARTS, JACK_OF_SPADES, JACK_OF_DIAMONDS, TEN_OF_CLUBS, TEN_OF_SPADES};
		Card[] expectedCards = new Card[] {JACK_OF_HEARTS,  JACK_OF_SPADES, JACK_OF_DIAMONDS, TEN_OF_CLUBS, TEN_OF_SPADES,};
		
		testForConditionType(ConditionType.FullHouse, cardArray, multiplesExpected, expectedCards);
	}

	private void testForConditionType(ConditionType conditionType, Card[] cardArray, Card[] multiplesExpected, Card[] expectedCards) {
		List<Card> list = Arrays.asList(cardArray);
		Hand mockHand = mock(Hand.class);
		
		MultiplesChecker mockMultiplesChecker = mock(MultiplesChecker.class);
		MultiplesCheckerFactory.getInstance().setMockChecker(mockMultiplesChecker);
		
		when(mockHand.getCards()).thenReturn(list).thenReturn(list).thenReturn(list).thenReturn(list);
		when(mockHand.iterator()).thenReturn(list.iterator()).thenReturn(list.iterator());
				
		List<Card> multiplesExpectedList = Arrays.asList(multiplesExpected);
		CheckResult fakeResult = new CheckResult(conditionType, new HandImpl(multiplesExpectedList));
		List<CheckResult> multiplesResults = new LinkedList<CheckResult>();
		multiplesResults.add(fakeResult);
		
		when(mockMultiplesChecker.checkMultiples()).thenReturn(multiplesResults);
		
		CheckResult result = (new CheckerImpl(mockHand)).check();
		
		List<Card> expectedCardsList = Arrays.asList(expectedCards);
		
		assertEquals(conditionType, result.getConditionType());
		assertEquals(expectedCardsList, result.getSupportingCards().getCards());
	}
}
