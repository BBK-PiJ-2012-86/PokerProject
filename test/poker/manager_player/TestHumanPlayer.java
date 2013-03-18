package poker.manager_player;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import poker.hand_card.*;
import poker.ui.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TestHumanPlayer {

	private HumanPlayer player;
	
	@Before
	public void setUp(){
		PlayerFactory playerFactory = new PlayerFactoryImpl();
		player = playerFactory.createHumanPlayer("Ted", GameType.fiveCardDraw);
		List<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Rank.Two, Suit.Spades));
		cards.add(new Card(Rank.Eight, Suit.Spades));
		cards.add(new Card(Rank.Jack, Suit.Diamonds));
		cards.add(new Card(Rank.Nine, Suit.Hearts));
		cards.add(new Card(Rank.King, Suit.Spades));
		player.recieveCards(cards);
	}
	
	@Test
	public void testSelectCardsToRemove() {
		UserInput userInput = mock(UserInput.class);
		when(userInput.getInteger()).thenReturn(3);
		player.setUserInput(userInput);
		Card expected = new Card(Rank.Jack, Suit.Diamonds);
		Card result = player.selectCardsToRemove();
		assertEquals(expected, result);
	}
	
	@Test
	public void removeFirstCard(){
		UserInput userInput = mock(UserInput.class);
		when(userInput.getInteger()).thenReturn(5);
		player.setUserInput(userInput);
		Card expected = new Card(Rank.King, Suit.Spades);
		Card result = player.selectCardsToRemove();
		assertEquals(expected, result);
	}
	
	@Test
	public void RemoveLastCard(){
		UserInput userInput = mock(UserInput.class);
		when(userInput.getInteger()).thenReturn(1);
		player.setUserInput(userInput);
		Card expected = new Card(Rank.Two, Suit.Spades);
		Card result = player.selectCardsToRemove();
		assertEquals(expected, result);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSelectCardsToRemoveIntegerSelectionOutOfBounds(){
		UserInput userInput = mock(UserInput.class);
		when(userInput.getInteger()).thenReturn(6);
		player.setUserInput(userInput);
		player.selectCardsToRemove();
	}
	
	@Test
	public void testExchangeCardsNoCardsSwaped(){
		UserInput userInput = mock(UserInput.class);
		when(userInput.getInteger()).thenReturn(0);
		player.setUserInput(userInput);
		int expected = 0;
		int result = player.exchangeCards();
		assertEquals(expected, result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testExchangeCardsMoreThanThree(){
		UserInput userInput = mock(UserInput.class);
		when(userInput.getInteger()).thenReturn(4);
		player.setUserInput(userInput);
		player.exchangeCards();
	}
	
	@Test
	public void testExchangeOneCard(){
		UserInput userInput = mock(UserInput.class);
		when(userInput.getInteger()).thenReturn(1).thenReturn(3);
		player.setUserInput(userInput);
		int expected = 1;
		int result = player.exchangeCards();
		assertEquals(expected, result);
	}
	
	@Test
	public void testExchangeTwoCards(){
		UserInput userInput = mock(UserInput.class);
		when(userInput.getInteger()).thenReturn(2).thenReturn(3).thenReturn(2);
		player.setUserInput(userInput);
		int expected = 2;
		int result = player.exchangeCards();
		assertEquals(expected, result);
	}

	@Test
	public void testExchangeThreeCards(){
		UserInput userInput = mock(UserInput.class);
		when(userInput.getInteger()).thenReturn(3).thenReturn(3).thenReturn(2).thenReturn(1);
		player.setUserInput(userInput);
		int expected = 3;
		int result = player.exchangeCards();
		assertEquals(expected, result);
	}
}
