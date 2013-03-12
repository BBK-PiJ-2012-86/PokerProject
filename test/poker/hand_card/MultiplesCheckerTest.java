/**
 * 
 */
package poker.hand_card;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import poker.hand_card.Card;
import poker.hand_card.CheckResult;
import poker.hand_card.ConditionType;
import poker.hand_card.Hand;
import poker.hand_card.MultiplesChecker;
import poker.hand_card.Rank;
import poker.hand_card.Suit;

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
	
	private static final Card JACK_OF_HEARTS = new Card(Rank.Jack, Suit.Hearts);
	private static final Card JACK_OF_CLUBS = new Card(Rank.Jack, Suit.Clubs);
	private static final Card JACK_OF_DIAMONDS = new Card(Rank.Jack, Suit.Diamonds);
	
	private static final Card TEN_OF_CLUBS = new Card(Rank.Ten, Suit.Clubs);
	private static final Card TEN_OF_DIAMONDS = new Card(Rank.Ten, Suit.Diamonds);

	@Test
	public void testFourOfAKind() {
		Card[] inputCards = new Card[] {JACK_OF_HEARTS, JACK_OF_CLUBS, QUEEN_OF_SPADES, JACK_OF_SPADES, JACK_OF_DIAMONDS};
		Card[] rankSortedCards = new Card[] {QUEEN_OF_SPADES, JACK_OF_HEARTS, JACK_OF_CLUBS, JACK_OF_SPADES, JACK_OF_DIAMONDS};
		Card[] multiplesExpected = new Card[] {JACK_OF_HEARTS, JACK_OF_CLUBS, JACK_OF_SPADES, JACK_OF_DIAMONDS, QUEEN_OF_SPADES};
		
		checkForConditionType(ConditionType.FourOfAKind, inputCards, rankSortedCards, multiplesExpected);
	}
	
	@Test
	public void testThreeOfAKind() {
		Card[] inputCards = new Card[] {JACK_OF_HEARTS, JACK_OF_CLUBS, QUEEN_OF_SPADES, JACK_OF_SPADES, ACE_OF_SPADES};
		Card[] rankSortedCards = new Card[] {ACE_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_HEARTS, JACK_OF_CLUBS, JACK_OF_SPADES};
		Card[] multiplesExpected = new Card[] {JACK_OF_HEARTS, JACK_OF_CLUBS, JACK_OF_SPADES, ACE_OF_SPADES, QUEEN_OF_SPADES};
		
		checkForConditionType(ConditionType.ThreeOfAKind, inputCards, rankSortedCards, multiplesExpected);		
	}
	
	@Test
	public void testFullHouseOfAKind() {
		Card[] inputCards = new Card[] {TEN_OF_DIAMONDS, JACK_OF_CLUBS, TEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_CLUBS};
		Card[] rankSortedCards = new Card[] {JACK_OF_CLUBS, JACK_OF_SPADES, TEN_OF_DIAMONDS, TEN_OF_SPADES, TEN_OF_CLUBS};
		Card[] multiplesExpected = new Card[] {TEN_OF_DIAMONDS, TEN_OF_SPADES, TEN_OF_CLUBS, JACK_OF_CLUBS, JACK_OF_SPADES};
		
		checkForConditionType(ConditionType.FullHouse, inputCards, rankSortedCards, multiplesExpected);		
	}
	
	@Test
	public void testTwoPair() {
		Card[] inputCards = new Card[] {JACK_OF_DIAMONDS, TEN_OF_CLUBS, JACK_OF_SPADES, TEN_OF_SPADES, KING_OF_SPADES};
		Card[] rankSortedCards = new Card[] {KING_OF_SPADES, JACK_OF_DIAMONDS, JACK_OF_SPADES, TEN_OF_CLUBS, TEN_OF_SPADES};
		Card[] multiplesExpected = new Card[] {JACK_OF_DIAMONDS, JACK_OF_SPADES, TEN_OF_CLUBS, TEN_OF_SPADES, KING_OF_SPADES};
		
		checkForConditionType(ConditionType.TwoPair, inputCards, rankSortedCards, multiplesExpected);		
	}
	
	@Test
	public void testPair() {
		Card[] inputCards = new Card[] {JACK_OF_DIAMONDS, TEN_OF_CLUBS, KING_OF_SPADES, TEN_OF_SPADES, QUEEN_OF_SPADES};
		Card[] rankSortedCards = new Card[] {KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_DIAMONDS, TEN_OF_CLUBS, TEN_OF_SPADES };
		Card[] multiplesExpected = new Card[] {TEN_OF_CLUBS, TEN_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_DIAMONDS };
		
		checkForConditionType(ConditionType.Pair, inputCards, rankSortedCards, multiplesExpected);		
	}
	
	@Test
	public void testHighCard() {
		Card[] inputCards = new Card[] {JACK_OF_DIAMONDS, TEN_OF_CLUBS, KING_OF_SPADES, ACE_OF_SPADES, QUEEN_OF_SPADES};
		Card[] rankSortedCards = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_DIAMONDS, TEN_OF_CLUBS };
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
	
	/*doAnswer(new Answer<Object>() {
	    public Object answer(InvocationOnMock invocation) {
	        Object[] args = invocation.getArguments();
	    	inputList.addAll((List<Card>) args[0]);
	    	return null;
	    }
	}).when(mockHand).addCards((List<Card>) anyObject());*/

}
