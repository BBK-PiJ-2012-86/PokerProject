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

}
