package poker.manager_player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static poker.hand_card.TestCards.ACE_SPADE;
import static poker.hand_card.TestCards.FIVE_CLUB;
import static poker.hand_card.TestCards.FOUR_CLUB;
import static poker.hand_card.TestCards.JACK_CLUB;
import static poker.hand_card.TestCards.JACK_HEART;
import static poker.hand_card.TestCards.JACK_SPADE;
import static poker.hand_card.TestCards.SIX_CLUB;
import static poker.hand_card.TestCards.SIX_SPADE;
import static poker.hand_card.TestCards.TEN_CLUB;
import static poker.hand_card.TestCards.TEN_SPADE;
import static poker.hand_card.TestCards.THREE_CLUB;
import static poker.hand_card.TestCards.TWO_CLUB;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import poker.hand_card.Card;
import poker.hand_card.Deck;
import poker.hand_card.DeckFactory;
import poker.hand_card.Rank;
import poker.hand_card.Suit;
import poker.hand_card.TestUtil;

public class PlayerTest {

	private Player player1;
	private Deck deck;
	private Player player2;
	
	@Before
	public void setUp(){
		player1 = new HumanPlayer("Ted", GameType.FIVE_CARD_DRAW);
		player2 = new HumanPlayer("Ruth", GameType.FIVE_CARD_DRAW);
		deck = DeckFactory.getDeckFactory().getDeck();
	}
	
	@Test
	public void testGetUsername() {
		assertEquals("Ted", player1.getUsername());
	}
	
	@Test
	public void testRecieveCards(){
		List<Card> cards = deck.dealCards(5);
		player1.recieveCards(cards);
		int expected = 5;
		int result = player1.getHand().getCards().size();
		assertEquals(expected, result);
	}
	
	@Test
	public void testRemoveCards(){
		List<Card> cards = deck.dealCards(5);
		player1.recieveCards(cards);
		player1.removeCards();
		assertTrue(player1.getHand().getCards().size() == 0);
	}
	
	@Test
	public void testComparatorSameHand(){
		Comparator<Player> comp = player1.getCheckResultRanking();
		List<Card> cardsForPlayer = new ArrayList<Card>();
		cardsForPlayer.add(new Card(Rank.ACE, Suit.HEARTS));
		cardsForPlayer.add(new Card(Rank.ACE, Suit.DIAMONDS));
		cardsForPlayer.add(new Card(Rank.SIX, Suit.SPADES));
		cardsForPlayer.add(new Card(Rank.SIX, Suit.CLUBS));
		cardsForPlayer.add(new Card(Rank.JACK, Suit.HEARTS));
		player1.recieveCards(cardsForPlayer);
		List<Card> cardsForPlayer2 = new ArrayList<Card>();
		cardsForPlayer2.add(new Card(Rank.ACE, Suit.SPADES));
		cardsForPlayer2.add(new Card(Rank.ACE, Suit.CLUBS));
		cardsForPlayer2.add(new Card(Rank.SIX, Suit.HEARTS));
		cardsForPlayer2.add(new Card(Rank.SIX, Suit.DIAMONDS));
		cardsForPlayer2.add(new Card(Rank.JACK, Suit.SPADES));
		player2.recieveCards(cardsForPlayer2);
		//int expected = 0;
		//int result = comp.compare(player, player2);
		//assertEquals(expected, result);
		assertTrue(comp.compare(player1, player2)==0);
	}
	
	@Test
	public void testComparatorJacksOverTens(){
		Card[] player1CardArray = new Card[] {JACK_SPADE, JACK_CLUB, SIX_CLUB, SIX_SPADE, JACK_HEART};
		player1.recieveCards(TestUtil.toLinkedList(player1CardArray));
		Card[] player2CardArray = new Card[] {TEN_SPADE, TEN_CLUB, SIX_CLUB, SIX_SPADE, JACK_HEART};
		player2.recieveCards(TestUtil.toLinkedList(player2CardArray));

		System.out.println(player1.check());	//wrong - using mock multiples checker???? only wrong when running all tests....
		
		Comparator<Player> comp = player1.getCheckResultRanking();		//should use mock checkers?
		assertTrue(comp.compare(player1, player2)>0);
	}
	
	@Test
	public void testComparatorLowStraightVersusFullHouse(){
		Card[] player1CardArray = new Card[] {ACE_SPADE, TWO_CLUB, THREE_CLUB, FOUR_CLUB, FIVE_CLUB};
		player1.recieveCards(TestUtil.toLinkedList(player1CardArray));

		List<Card> cardsForPlayer2 = new ArrayList<Card>();
		cardsForPlayer2.add(new Card(Rank.KING, Suit.SPADES));
		cardsForPlayer2.add(new Card(Rank.KING, Suit.CLUBS));
		cardsForPlayer2.add(new Card(Rank.SIX, Suit.HEARTS));
		cardsForPlayer2.add(new Card(Rank.SIX, Suit.DIAMONDS));
		cardsForPlayer2.add(new Card(Rank.SIX, Suit.SPADES));
		player2.recieveCards(cardsForPlayer2);
		//int expected = -2;
		//int result = comp.compare(player, player2);
		//assertEquals(expected, result);
		Comparator<Player> comp = player1.getCheckResultRanking();		//should use mock checkers?
		assertTrue(comp.compare(player1, player2)<0);
	}
}