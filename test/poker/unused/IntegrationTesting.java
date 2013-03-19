package poker.unused;


import java.util.LinkedList;
import java.util.List;

import poker.hand_card.Card;
import poker.hand_card.CheckResult;
import poker.hand_card.Checker;
import poker.hand_card.CheckerFactory;
import poker.hand_card.Hand;
import poker.hand_card.HandImpl;
import poker.hand_card.Rank;
import poker.hand_card.Suit;
import poker.manager_player.GameType;




public class IntegrationTesting {		// I will change this to a JUnit test..
	
	private static final Card ACE_OF_SPADES = new Card(Rank.Ace, Suit.Spades);
	private static final Card KING_OF_SPADES = new Card(Rank.King, Suit.Spades);
	private static final Card QUEEN_OF_SPADES = new Card(Rank.Queen, Suit.Spades);
	private static final Card JACK_OF_SPADES = new Card(Rank.Jack, Suit.Spades);
	private static final Card TEN_OF_SPADES = new Card(Rank.Ten, Suit.Spades);
	private static final Card TEN_OF_HEARTS = new Card(Rank.Ten, Suit.Hearts);
	private static final Card SIX_OF_HEARTS = new Card(Rank.Six, Suit.Hearts);
	private static final Card SIX_OF_DIAMONDS = new Card(Rank.Six, Suit.Diamonds);

	public static void main (String[] args) {
		IntegrationTesting joined = new IntegrationTesting();
		joined.launch();
	}
	
	/*public void launch() {
		
		GameType type = GameType.FIVE_CARD_DRAW;
		
		// get a checker given the GameType
		Checker checker = CheckerFactory.getInstance().getChecker(type);
		
		//*** try it for a straight flush
		// make a list of relevant cards
		Card[] cardArray = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, JACK_OF_SPADES, TEN_OF_SPADES, QUEEN_OF_SPADES};
		List<Card> cardList = new LinkedList<Card>();
		for (Card card : cardArray) {
			cardList.add(card);
		}
		//make a hand out of those cards
		Hand hand = new HandImpl(cardList);
		// get the checker to check the hand
		CheckResult result = checker.check(hand);
		//print out the result
		System.out.println(result);
		
		//*** try it for a high card
		// make a list of relevant cards
		cardArray = new Card[] {TEN_OF_SPADES, KING_OF_SPADES, QUEEN_OF_SPADES, JACK_OF_SPADES, SIX_OF_HEARTS};
		cardList = new LinkedList<Card>();
		for (Card card : cardArray) {
			cardList.add(card);
		}
		//make a hand out of those cards
		Hand highCardHand = new HandImpl(cardList);
		// get the checker to check the hand
		result = checker.check(highCardHand);
		//print out the result
		System.out.println(result);
		
		//*** try it for a two pair
		// make a list of relevant cards
		cardArray = new Card[] {TEN_OF_SPADES, SIX_OF_DIAMONDS, QUEEN_OF_SPADES, TEN_OF_HEARTS, SIX_OF_HEARTS};
		cardList = new LinkedList<Card>();
		for (Card card : cardArray) {
			cardList.add(card);
		}
		//make a hand out of those cards
		hand = new HandImpl(cardList);
		// get the checker to check the hand
		result = checker.check(hand);
		//print out the result
		System.out.println(result);

	}*/
	
	public void launch() {
	
	GameType type = GameType.FIVE_CARD_DRAW;
	Checker checker = CheckerFactory.getInstance().getChecker(type);
	
	Card[] cardArray1 = new Card[] {ACE_OF_SPADES, KING_OF_SPADES, JACK_OF_SPADES, TEN_OF_SPADES, QUEEN_OF_SPADES};
	List<Card> cardList1 = new LinkedList<Card>();
	for (Card card : cardArray1) {
		cardList1.add(card);
	}
	Hand hand1 = new HandImpl(cardList1);
	CheckResult result1 = checker.check(hand1);
	System.out.println(result1);
	
	Card[] cardArray2 = new Card[] {new Card(Rank.Nine, Suit.Spades), KING_OF_SPADES, JACK_OF_SPADES, TEN_OF_SPADES, QUEEN_OF_SPADES};
	List<Card> cardList2 = new LinkedList<Card>();
	for (Card card : cardArray2) {
		cardList2.add(card);
	}
	Hand hand2 = new HandImpl(cardList2);
	CheckResult result2 = checker.check(hand2);
	System.out.println(result2);
	
	System.out.println(CheckResult.getComparator().compare(result1, result2));
	System.out.println(CheckResult.getComparator().compare(result2, result1));
}
		
}