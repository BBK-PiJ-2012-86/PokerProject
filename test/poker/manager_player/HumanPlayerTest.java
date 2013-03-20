package poker.manager_player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class HumanPlayerTest {

	private HumanPlayer player;
	private HumanPlayerListener listener;
	
	@Before
	public void setUp(){
		listener = mock(HumanPlayerListener.class);
		player = new HumanPlayer("Sarah", listener);
		player.changeGameType(GameType.FIVE_CARD_DRAW);
	}
	
	@Test
	public void testExchangeCardsNone() {
		when(listener.getCountOfCardsToSwap(anyInt())).thenReturn(0);
		int expected = 0;
		int actual = player.exchangeCards();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testExchangeCardsOne() {
		when(listener.getCountOfCardsToSwap(anyInt())).thenReturn(1);
		int expected = 0;
		int actual = player.exchangeCards();
		
		assertEquals(expected, actual);
	}
	
}
