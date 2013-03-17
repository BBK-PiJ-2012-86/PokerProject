package poker.manager_player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

public class TestPlayer {

	private Player player;
	private Deck deck;
	private Player player2;
	
	@Before
	public void setUp(){
		player = new HumanPlayer("Ted", GameType.fiveCardDraw);
		player2 = new HumanPlayer("Ruth", GameType.fiveCardDraw);
		deck = DeckFactory.getDeckFactory().getDeck();
	}
	
	@Test
	public void testGetUsername() {
		assertEquals("Ted", player.getUsername());
	}
	
	@Test
	public void testRecieveCards(){
		List<Card> cards = deck.dealCards(5);
		player.recieveCards(cards);
		int expected = 5;
		int result = player.getHand().getCards().size();
		assertEquals(expected, result);
	}
	
	@Test
	public void testRemoveCards(){
		List<Card> cards = deck.dealCards(5);
		player.recieveCards(cards);
		player.removeCards();
		assertTrue(player.getHand().getCards().size() == 0);
	}
	
	@Test
	public void testComparatorSameHand(){
		Comparator<Player> comp = player.getCheckResultRanking();
		List<Card> cardsForPlayer = new ArrayList<Card>();
		cardsForPlayer.add(new Card(Rank.Ace, Suit.Hearts));
		cardsForPlayer.add(new Card(Rank.Ace, Suit.Diamonds));
		cardsForPlayer.add(new Card(Rank.Six, Suit.Spades));
		cardsForPlayer.add(new Card(Rank.Six, Suit.Clubs));
		cardsForPlayer.add(new Card(Rank.Jack, Suit.Hearts));
		player.recieveCards(cardsForPlayer);
		List<Card> cardsForPlayer2 = new ArrayList<Card>();
		cardsForPlayer2.add(new Card(Rank.Ace, Suit.Spades));
		cardsForPlayer2.add(new Card(Rank.Ace, Suit.Clubs));
		cardsForPlayer2.add(new Card(Rank.Six, Suit.Hearts));
		cardsForPlayer2.add(new Card(Rank.Six, Suit.Diamonds));
		cardsForPlayer2.add(new Card(Rank.Jack, Suit.Spades));
		player2.recieveCards(cardsForPlayer2);
		int expected = 0;
		int result = comp.compare(player, player2);
		assertEquals(expected, result);
	}
	
	@Test
	public void testComparatorAcesOverKings(){
		Comparator<Player> comp = player.getCheckResultRanking();
		List<Card> cardsForPlayer = new ArrayList<Card>();
		cardsForPlayer.add(new Card(Rank.Ace, Suit.Hearts));
		cardsForPlayer.add(new Card(Rank.Ace, Suit.Diamonds));
		cardsForPlayer.add(new Card(Rank.Six, Suit.Spades));
		cardsForPlayer.add(new Card(Rank.Six, Suit.Clubs));
		cardsForPlayer.add(new Card(Rank.Jack, Suit.Hearts));
		player.recieveCards(cardsForPlayer);
		List<Card> cardsForPlayer2 = new ArrayList<Card>();
		cardsForPlayer2.add(new Card(Rank.King, Suit.Spades));
		cardsForPlayer2.add(new Card(Rank.King, Suit.Clubs));
		cardsForPlayer2.add(new Card(Rank.Six, Suit.Hearts));
		cardsForPlayer2.add(new Card(Rank.Six, Suit.Diamonds));
		cardsForPlayer2.add(new Card(Rank.Jack, Suit.Spades));
		player2.recieveCards(cardsForPlayer2);
		int expected = 1;
		int result = comp.compare(player, player2);
		assertEquals(expected, result);
	}
	
	@Test
	public void testComparatorLowStraightVersusFullHouse(){
		Comparator<Player> comp = player.getCheckResultRanking();
		List<Card> cardsForPlayer = new ArrayList<Card>();
		cardsForPlayer.add(new Card(Rank.Ace, Suit.Hearts));
		cardsForPlayer.add(new Card(Rank.Two, Suit.Diamonds));
		cardsForPlayer.add(new Card(Rank.Three, Suit.Spades));
		cardsForPlayer.add(new Card(Rank.Four, Suit.Clubs));
		cardsForPlayer.add(new Card(Rank.Five, Suit.Hearts));
		player.recieveCards(cardsForPlayer);
		List<Card> cardsForPlayer2 = new ArrayList<Card>();
		cardsForPlayer2.add(new Card(Rank.King, Suit.Spades));
		cardsForPlayer2.add(new Card(Rank.King, Suit.Clubs));
		cardsForPlayer2.add(new Card(Rank.Six, Suit.Hearts));
		cardsForPlayer2.add(new Card(Rank.Six, Suit.Diamonds));
		cardsForPlayer2.add(new Card(Rank.Six, Suit.Spades));
		player2.recieveCards(cardsForPlayer2);
		int expected = -2;
		int result = comp.compare(player, player2);
		assertEquals(expected, result);
	}
}