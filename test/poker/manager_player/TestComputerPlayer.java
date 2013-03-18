package poker.manager_player;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import poker.hand_card.Card;
import poker.hand_card.Rank;
import poker.hand_card.Suit;
import poker.manager_player.*;

public class TestComputerPlayer {

	private ComputerPlayer computerPlayer;
	private Card card1 = new Card(Rank.Six, Suit.Spades);
	private Card card2 = new Card(Rank.Seven, Suit.Diamonds);
	private Card card3 = new Card(Rank.Eight, Suit.Spades);
	private Card card4 = new Card(Rank.Nine, Suit.Spades);
	private Card card5 = new Card(Rank.Ten, Suit.Spades);
	private Card card6 = new Card(Rank.Queen, Suit.Spades);
	private Card card7 = new Card(Rank.Seven, Suit.Spades);
	private Card card8 = new Card(Rank.Seven, Suit.Clubs);
	private Card card9 = new Card(Rank.Seven, Suit.Hearts);
	private Card card10 = new Card(Rank.Ten, Suit.Diamonds);
	private List<Card> cards;
	
	@Before
	public void setUp(){
		computerPlayer = new PlayerFactoryImpl().createComputerPlayer(GameType.fiveCardDraw);
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
		int expected = 0;
		int result = computerPlayer.exchangeCards();
		assertEquals(expected, result);
	}
}
