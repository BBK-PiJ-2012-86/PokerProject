package poker.manager_player;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import poker.hand_card.Card;
import poker.hand_card.Rank;
import poker.hand_card.Suit;

public class ComputerPlayerTest {

	private ComputerPlayer computerPlayer;
	private Card card1 = new Card(Rank.SIX, Suit.SPADES);
	private Card card2 = new Card(Rank.SEVEN, Suit.DIAMONDS);
	private Card card3 = new Card(Rank.EIGHT, Suit.SPADES);
	private Card card4 = new Card(Rank.NINE, Suit.SPADES);
	private Card card5 = new Card(Rank.TEN, Suit.SPADES);
	private Card card6 = new Card(Rank.QUEEN, Suit.SPADES);
	private Card card7 = new Card(Rank.SEVEN, Suit.SPADES);
	private Card card8 = new Card(Rank.SEVEN, Suit.CLUBS);
	private Card card9 = new Card(Rank.SEVEN, Suit.HEARTS);
	private Card card10 = new Card(Rank.TEN, Suit.DIAMONDS);
	private Card card11 = new Card(Rank.FIVE, Suit.CLUBS);
	private Card card12 = new Card(Rank.TWO, Suit.SPADES);
	private Card card13 = new Card(Rank.FOUR, Suit.HEARTS);
	private Card card14 = new Card(Rank.ACE, Suit.CLUBS);
	private List<Card> cards;
	
	@Before
	public void setUp(){
		computerPlayer = new PlayerFactoryImpl().createComputerPlayer(GameType.FIVE_CARD_DRAW);
		cards = new ArrayList<Card>();
	}
	
	@Test
	public void testExchangeCardsStraight() {
		cards.add(card2);
		cards.add(card1);
		cards.add(card3);
		cards.add(card4);
		cards.add(card5);
		computerPlayer.recieveCards(cards);
		int expected = 0;
		int result = computerPlayer.exchangeCards();
		assertEquals(expected, result);
	}
	
	@Test
	public void testExchangeCardsFlush(){
		cards.add(card1);
		cards.add(card3);
		cards.add(card4);
		cards.add(card5);
		cards.add(card6);
		computerPlayer.recieveCards(cards);
		int expected = 0;
		int result = computerPlayer.exchangeCards();
		assertEquals(expected, result);
	}
	
	@Test
	public void testExchangeCardsStraightFlush(){
		cards.add(card1);
		cards.add(card3);
		cards.add(card4);
		cards.add(card5);
		cards.add(card7);
		computerPlayer.recieveCards(cards);
		int expected = 0;
		int result = computerPlayer.exchangeCards();
		assertEquals(expected, result);
	}
	
	@Test
	public void testExchangeCardsFullHouse(){
		cards.add(card2);
		cards.add(card7);
		cards.add(card8);
		cards.add(card5);
		cards.add(card10);
		computerPlayer.recieveCards(cards);
		int expected = 0;
		int result = computerPlayer.exchangeCards();
		assertEquals(expected, result);
	}
	
	@Test
	public void testExchangeCardsFourOfAKind(){
		cards.add(card2);
		cards.add(card7);
		cards.add(card8);
		cards.add(card9);
		cards.add(card1);
		computerPlayer.recieveCards(cards);
		int expected = 0;
		int result = computerPlayer.exchangeCards();
		assertEquals(expected, result);
	}
	
	@Test
	public void testExchangeCardsThreeOfAKindFacecard(){
		cards.add(card2);
		cards.add(card7);
		cards.add(card8);
		cards.add(card1);
		cards.add(card6);
		computerPlayer.recieveCards(cards);
		int expected = 1;
		int result = computerPlayer.exchangeCards();
		assertEquals(expected, result);
	}
	
	@Test
	public void testExchangeCardsThreeOfAKindExtraCardsLessThanSeven(){
		cards.add(card2);
		cards.add(card7);
		cards.add(card8);
		cards.add(card1);
		cards.add(card11);
		computerPlayer.recieveCards(cards);
		int expected = 2;
		int result = computerPlayer.exchangeCards();
		assertEquals(expected, result);
	}
	
	@Test
	public void testExchangeCardsTwoPair(){
		cards.add(card2);
		cards.add(card7);
		cards.add(card5);
		cards.add(card10);
		cards.add(card6);
		computerPlayer.recieveCards(cards);
		int expected = 1;
		int result = computerPlayer.exchangeCards();
		assertEquals(expected, result);
	}
	
	@Test
	public void testExchangeCardsPair(){
		cards.add(card2);
		cards.add(card7);
		cards.add(card3);
		cards.add(card11);
		cards.add(card6);
		computerPlayer.recieveCards(cards);
		int expected = 2;
		int result = computerPlayer.exchangeCards();
		assertEquals(expected, result);
	}
	
	@Test
	public void testExchangeCardsShotAtFlush(){
		cards.add(card1);
		cards.add(card6);
		cards.add(card3);
		cards.add(card12);
		cards.add(card10);
		computerPlayer.recieveCards(cards);
		int expected = 1;
		int result = computerPlayer.exchangeCards();
		assertEquals(expected, result);
	}
	
	@Test
	public void testExchangeCardsNoFlush(){
		cards.add(card2);
		cards.add(card12);
		cards.add(card10);
		cards.add(card13);
		cards.add(card14);
		computerPlayer.recieveCards(cards);
		int expected = 3;
		int result = computerPlayer.exchangeCards();
		assertEquals(expected, result);
	}
	
}
