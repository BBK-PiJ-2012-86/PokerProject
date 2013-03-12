package poker.hand_card;

import static org.junit.Assert.*;


import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import poker.hand_card.Card;
import poker.hand_card.CheckResult;
import poker.hand_card.ConditionType;
import poker.hand_card.Decider;
import poker.hand_card.DeciderImpl;
import poker.hand_card.Hand;
import poker.hand_card.HandImpl;
import poker.hand_card.Rank;
import poker.hand_card.Suit;

public class DeciderImplTest {
	private static final Card ACE_OF_SPADES = new Card(Rank.Ace, Suit.Spades);
	private static final Card KING_OF_SPADES = new Card(Rank.King, Suit.Spades);
	private static final Card QUEEN_OF_SPADES = new Card(Rank.Queen, Suit.Spades);
	private static final Card JACK_OF_SPADES = new Card(Rank.Jack, Suit.Spades);
	private static final Card TEN_OF_SPADES = new Card(Rank.Ten, Suit.Spades);
	
	private static final Card JACK_OF_HEARTS = new Card(Rank.Jack, Suit.Hearts);
	private static final Card JACK_OF_DIAMONDS = new Card(Rank.Jack, Suit.Diamonds);
	
	private static final Card TEN_OF_CLUBS = new Card(Rank.Ten, Suit.Clubs);
	
	private static final Card SIX_OF_CLUBS = new Card(Rank.Six, Suit.Clubs);
	private static final Card FIVE_OF_CLUBS = new Card(Rank.Five, Suit.Clubs);
	private static final Card FOUR_OF_CLUBS = new Card(Rank.Four, Suit.Clubs);

	@Test
	public void testStraightFlush() {	// to do properly with mocks etc
		//make a CheckResult (for now) for a SF
		Card [] cardArray = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, TEN_OF_SPADES};
		List<Card> cardList = new LinkedList<Card>();
		for (Card card : cardArray) {
			cardList.add(card);
		}
		Hand hand = new HandImpl(cardList);
		CheckResult checkResult = new CheckResult(ConditionType.StraightFlush, hand);
		
		//make a Decider and get it to decide
		Decider decider = new DeciderImpl();
		List<Card> actual = decider.decide(checkResult);
		List<Card> expected = new LinkedList<Card>();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testThreeOfAKindReturnOne() {
		//make a CheckResult (for now) for a SF
		Card [] cardArray = new Card[] {JACK_OF_DIAMONDS, JACK_OF_SPADES, JACK_OF_HEARTS, KING_OF_SPADES, QUEEN_OF_SPADES,};
		List<Card> cardList = new LinkedList<Card>();
		for (Card card : cardArray) {
			cardList.add(card);
		}
		Hand hand = new HandImpl(cardList);
		CheckResult checkResult = new CheckResult(ConditionType.ThreeOfAKind, hand);
		
		//make a Decider and get it to decide
		Decider decider = new DeciderImpl();
		List<Card> actual = decider.decide(checkResult);
		List<Card> expected = new LinkedList<Card>();
		expected.add(QUEEN_OF_SPADES);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testThreeOfAKindReturnTwo() {
		//make a CheckResult (for now) for a 3K
		Card [] cardArray = new Card[] {JACK_OF_DIAMONDS, JACK_OF_SPADES, JACK_OF_HEARTS, SIX_OF_CLUBS, FIVE_OF_CLUBS};
		List<Card> cardList = new LinkedList<Card>();
		for (Card card : cardArray) {
			cardList.add(card);
		}
		Hand hand = new HandImpl(cardList);
		CheckResult checkResult = new CheckResult(ConditionType.ThreeOfAKind, hand);
		
		//make a Decider and get it to decide
		Decider decider = new DeciderImpl();
		List<Card> actual = decider.decide(checkResult);
		List<Card> expected = new LinkedList<Card>();
		expected.add(SIX_OF_CLUBS);
		expected.add(FIVE_OF_CLUBS);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTwoPair() {
		//make a CheckResult (for now) for TP
		Card [] cardArray = new Card[] {JACK_OF_DIAMONDS, JACK_OF_SPADES, TEN_OF_SPADES, TEN_OF_CLUBS, FIVE_OF_CLUBS};
		List<Card> cardList = new LinkedList<Card>();
		for (Card card : cardArray) {
			cardList.add(card);
		}
		Hand hand = new HandImpl(cardList);
		CheckResult checkResult = new CheckResult(ConditionType.TwoPair, hand);
		
		//make a Decider and get it to decide
		Decider decider = new DeciderImpl();
		List<Card> actual = decider.decide(checkResult);
		List<Card> expected = new LinkedList<Card>();
		expected.add(FIVE_OF_CLUBS);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testPairReturnTwo() {
		//make a CheckResult (for now) for pair
		Card [] cardArray = new Card[] {JACK_OF_DIAMONDS, JACK_OF_SPADES, QUEEN_OF_SPADES, TEN_OF_CLUBS, FIVE_OF_CLUBS};
		List<Card> cardList = new LinkedList<Card>();
		for (Card card : cardArray) {
			cardList.add(card);
		}
		Hand hand = new HandImpl(cardList);
		CheckResult checkResult = new CheckResult(ConditionType.Pair, hand);
		
		//make a Decider and get it to decide
		Decider decider = new DeciderImpl();
		List<Card> actual = decider.decide(checkResult);
		List<Card> expected = new LinkedList<Card>();
		
		expected.add(TEN_OF_CLUBS);
		expected.add(FIVE_OF_CLUBS);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testPairReturnThree() {
		//make a CheckResult (for now) for pair
		Card [] cardArray = new Card[] {JACK_OF_DIAMONDS, JACK_OF_SPADES, SIX_OF_CLUBS, FIVE_OF_CLUBS, FOUR_OF_CLUBS};
		List<Card> cardList = new LinkedList<Card>();
		for (Card card : cardArray) {
			cardList.add(card);
		}
		Hand hand = new HandImpl(cardList);
		CheckResult checkResult = new CheckResult(ConditionType.Pair, hand);
		
		//make a Decider and get it to decide
		Decider decider = new DeciderImpl();
		List<Card> actual = decider.decide(checkResult);
		List<Card> expected = new LinkedList<Card>();
		expected.add(SIX_OF_CLUBS);
		expected.add(FIVE_OF_CLUBS);
		expected.add(FOUR_OF_CLUBS);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testHighCardCloseToFlush() {
		//make a CheckResult (for now) for HC
		Card [] cardArray = new Card[] {ACE_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, SIX_OF_CLUBS, FIVE_OF_CLUBS};
		List<Card> cardList = new LinkedList<Card>();
		for (Card card : cardArray) {
			cardList.add(card);
		}
		Hand hand = new HandImpl(cardList);
		CheckResult checkResult = new CheckResult(ConditionType.HighCard, hand);
		
		//make a Decider and get it to decide
		Decider decider = new DeciderImpl();
		List<Card> actual = decider.decide(checkResult);
		List<Card> expected = new LinkedList<Card>();
		expected.add(SIX_OF_CLUBS);
		expected.add(FIVE_OF_CLUBS);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testHighCardFarFromFlush() {
		//make a CheckResult (for now) for HC
		Card [] cardArray = new Card[] {ACE_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_DIAMONDS, SIX_OF_CLUBS, FIVE_OF_CLUBS};
		List<Card> cardList = new LinkedList<Card>();
		for (Card card : cardArray) {
			cardList.add(card);
		}
		Hand hand = new HandImpl(cardList);
		CheckResult checkResult = new CheckResult(ConditionType.HighCard, hand);
		
		//make a Decider and get it to decide
		Decider decider = new DeciderImpl();
		List<Card> actual = decider.decide(checkResult);
		List<Card> expected = new LinkedList<Card>();
		expected.add(JACK_OF_DIAMONDS);
		expected.add(SIX_OF_CLUBS);
		expected.add(FIVE_OF_CLUBS);
		
		assertEquals(expected, actual);
	}
	
	
	
	
}
