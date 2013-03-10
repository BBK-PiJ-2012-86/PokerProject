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
		List<Card> inputList = Arrays.asList(inputCards);
		List<Card> multiplesExpectedList = Arrays.asList(multiplesExpected);
		Hand mockHand = mock(Hand.class);
		when(mockHand.getCards()).thenReturn(inputList).thenReturn(inputList).thenReturn(inputList).thenReturn(inputList);
		when(mockHand.iterator()).thenReturn(inputList.iterator()).thenReturn(inputList.iterator());
		Hand mockResultHand = mock(Hand.class);
		when(mockResultHand.getCards()).thenReturn(multiplesExpectedList).thenReturn(multiplesExpectedList).thenReturn(multiplesExpectedList).thenReturn(multiplesExpectedList);
				/// hand.addCards problem.........!
		
		doAnswer(new Answer<Object>() {
		        public Object answer(InvocationOnMock invocation) {
		            Object[] args = invocation.getArguments();
		            return "called with arguments: " + args;		// hum hum confused
		        }
		    }).when(mockHand).addCards((List<Card>) anyObject());	//consider
		
		//
		MultiplesChecker multiplesChecker = new MultiplesChecker();
		List<CheckResult> actual = multiplesChecker.checkMultiples(mockHand);
		
		CheckResult fourJacks = new CheckResult(ConditionType.FourOfAKind, mockResultHand);
		List<CheckResult> expected = new LinkedList<CheckResult>();
		expected.add(fourJacks);
		
		assertEquals(expected, actual);
	}

}
