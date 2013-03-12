package poker.hand_card;

import static org.junit.Assert.assertEquals;
import static poker.hand_card.TestCards.ACE_SPADE;
import static poker.hand_card.TestCards.FIVE_CLUB;
import static poker.hand_card.TestCards.FOUR_CLUB;
import static poker.hand_card.TestCards.JACK_DIAMOND;
import static poker.hand_card.TestCards.JACK_HEART;
import static poker.hand_card.TestCards.JACK_SPADE;
import static poker.hand_card.TestCards.KING_SPADE;
import static poker.hand_card.TestCards.QUEEN_SPADE;
import static poker.hand_card.TestCards.SIX_CLUB;
import static poker.hand_card.TestCards.TEN_CLUB;
import static poker.hand_card.TestCards.TEN_SPADE;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class DeciderImplTest {
	// pre-made cards like TEN_SPADE imported from TestCards to avoid repetition in tests
	// to consider mocking these as well - maybe a mcok making factory..?

	@Test
	public void testStraightFlush() {	// to do properly with mocks etc
		//make a CheckResult (for now) for a SF
		Card [] cardArray = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_SPADE, TEN_SPADE};
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
		Card [] cardArray = new Card[] {JACK_DIAMOND, JACK_SPADE, JACK_HEART, KING_SPADE, QUEEN_SPADE,};
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
		expected.add(QUEEN_SPADE);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testThreeOfAKindReturnTwo() {
		//make a CheckResult (for now) for a 3K
		Card [] cardArray = new Card[] {JACK_DIAMOND, JACK_SPADE, JACK_HEART, SIX_CLUB, FIVE_CLUB};
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
		expected.add(SIX_CLUB);
		expected.add(FIVE_CLUB);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTwoPair() {
		//make a CheckResult (for now) for TP
		Card [] cardArray = new Card[] {JACK_DIAMOND, JACK_SPADE, TEN_SPADE, TEN_CLUB, FIVE_CLUB};
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
		expected.add(FIVE_CLUB);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testPairReturnTwo() {
		//make a CheckResult (for now) for pair
		Card [] cardArray = new Card[] {JACK_DIAMOND, JACK_SPADE, QUEEN_SPADE, TEN_CLUB, FIVE_CLUB};
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
		
		expected.add(TEN_CLUB);
		expected.add(FIVE_CLUB);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testPairReturnThree() {
		//make a CheckResult (for now) for pair
		Card [] cardArray = new Card[] {JACK_DIAMOND, JACK_SPADE, SIX_CLUB, FIVE_CLUB, FOUR_CLUB};
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
		expected.add(SIX_CLUB);
		expected.add(FIVE_CLUB);
		expected.add(FOUR_CLUB);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testHighCardCloseToFlush() {
		//make a CheckResult (for now) for HC
		Card [] cardArray = new Card[] {ACE_SPADE, QUEEN_SPADE, JACK_SPADE, SIX_CLUB, FIVE_CLUB};
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
		expected.add(SIX_CLUB);
		expected.add(FIVE_CLUB);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testHighCardFarFromFlush() {
		//make a CheckResult (for now) for HC
		Card [] cardArray = new Card[] {ACE_SPADE, QUEEN_SPADE, JACK_DIAMOND, SIX_CLUB, FIVE_CLUB};
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
		expected.add(JACK_DIAMOND);
		expected.add(SIX_CLUB);
		expected.add(FIVE_CLUB);
		
		assertEquals(expected, actual);
	}
	
	
	
	
}
