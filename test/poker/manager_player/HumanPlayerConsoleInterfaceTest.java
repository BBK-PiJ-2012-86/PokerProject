package poker.manager_player;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import poker.hand_card.Card;
import poker.hand_card.Hand;

public class HumanPlayerConsoleInterfaceTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	String eo1 = System.getProperty("line.separator");
	private HumanPlayerConsoleInterface hpci;

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		hpci = new HumanPlayerConsoleInterface();
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(null);
	}

	@Test
	public void testOnReceiveCards() {
		Hand mockHand = mock(Hand.class);
		when(mockHand.toString()).thenReturn("Hand");
		hpci.onReceiveCards(mockHand);
		String expected = "Your hand is..."+eo1+"Hand"+eo1;
		String actual = outContent.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetCountOfCardsToSwap() {
		int expectedInt = 0;
		//int actualInt = hpci.getCountOfCardsToSwap(3);
			//somehow need to set a mock scanner..
		String expectedStr = "How many cards would you like to swap (max 0): ";
		String actualStr = outContent.toString();
		assertEquals(expectedStr, actualStr);
		assertEquals(expectedInt, actualInt);
	}
	

	@Test
	public void testSelectCardsToRemove() {
		fail("Not yet implemented");
	}

}
