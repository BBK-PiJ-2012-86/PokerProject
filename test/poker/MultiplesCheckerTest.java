/**
 * 
 */
package poker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Matchers.anyObject;



import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * @author 86
 *
 */
public class MultiplesCheckerTest {
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

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFourOfAKind() {
		Card[] inputCards = new Card[] {JACK_OF_HEARTS, JACK_OF_CLUBS, QUEEN_OF_SPADES, JACK_OF_SPADES, JACK_OF_DIAMONDS};
		Card[] multiplesExpected = new Card[] {JACK_OF_HEARTS, JACK_OF_CLUBS, JACK_OF_SPADES, JACK_OF_DIAMONDS, QUEEN_OF_SPADES};
		Card[] rankSortedCards = new Card[] {JACK_OF_HEARTS, JACK_OF_CLUBS, JACK_OF_SPADES, JACK_OF_DIAMONDS, QUEEN_OF_SPADES};
				
		checkForConditionType(ConditionType.FourOfAKind, inputCards, rankSortedCards, multiplesExpected);
		
		/*doAnswer(new Answer<Object>() {
		        public Object answer(InvocationOnMock invocation) {
		            Object[] args = invocation.getArguments();
		        	inputList.addAll((List<Card>) args[0]);
		        	return null;
		        }
		    }).when(mockHand).addCards((List<Card>) anyObject());*/
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
				
		Hand sortedMockHand = mock(Hand.class);
		when(sortedMockHand.getCards()).thenReturn(rankSortedCardsList);
		
		Hand mockHand = mock(Hand.class);
		when(mockHand.iterator()).thenReturn(inputList.iterator());
		when(mockHand.sortByRank()).thenReturn(sortedMockHand);
		
		MultiplesChecker multiplesChecker = new MultiplesChecker();
		List<CheckResult> actual = multiplesChecker.checkMultiples(mockHand);
		
		assertEquals(1, actual.size());
		assertEquals(conditionType, actual.get(0).getConditionType());
		assertEquals(multiplesExpectedList, actual.get(0).getSupportingCards().getCards());
	}

}
