package poker.manager_player;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static poker.hand_card.TestCards.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import poker.hand_card.Card;
import poker.hand_card.Hand;
import poker.hand_card.TestUtil;

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
	public void testGetCountOfCardsToSwapZero() {
		ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
		//System.setIn(in);
		hpci.setScanner(new Scanner(in));
		
		int expectedInt = 0;
		int actualInt = hpci.getCountOfCardsToSwap(3);
		assertEquals(expectedInt, actualInt);
		
		String expectedStr = "How many cards would you like to swap (max 3): ";
		String actualStr = outContent.toString();
		assertEquals(expectedStr, actualStr);
	}
	
	@Test
	public void testGetCountOfCardsToSwapOne() {
		ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
		//System.setIn(in);
		hpci.setScanner(new Scanner(in));
		
		int expectedInt = 1;
		int actualInt = hpci.getCountOfCardsToSwap(3);
		assertEquals(expectedInt, actualInt);
		
		String expectedStr = "How many cards would you like to swap (max 3): ";
		String actualStr = outContent.toString();
		assertEquals(expectedStr, actualStr);
	}
	
	@Test
	public void testGetCountOfCardsToSwapWrongNumber() {
		String input = "9"+eo1+"0";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		//System.setIn(in);
		hpci.setScanner(new Scanner(in));
		
		int expectedInt = 0;
		int actualInt = hpci.getCountOfCardsToSwap(3);
		assertEquals(expectedInt, actualInt);
		
		String expectedStr = "How many cards would you like to swap (max 3): "+
				"That is not a valid selection. Possible range 0 to 3"+eo1+
				"Try again: ";
		String actualStr = outContent.toString();
		assertEquals(expectedStr, actualStr);
	}

	@Test
	public void testGetCountOfCardsToSwapString() {
		String input = "foo"+eo1+"0";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		//System.setIn(in);
		hpci.setScanner(new Scanner(in));
		
		int expectedInt = 0;
		int actualInt = hpci.getCountOfCardsToSwap(3);
		assertEquals(expectedInt, actualInt);
		
		String expectedStr = "How many cards would you like to swap (max 3): "+
				"Please enter a single numerical digit e.g. 1"+eo1+
				"Try again: ";
		String actualStr = outContent.toString();
		assertEquals(expectedStr, actualStr);
	}
	
	@Test
	public void testSelectCardsToRemoveZero() {
		List<Card> expectedCards = new LinkedList<Card>();
		List<Card> actualCards = hpci.selectCardsToRemove(0);
		assertEquals(expectedCards, actualCards);
		
		String expectedStr = "";
		String actualStr = outContent.toString();
		assertEquals(expectedStr, actualStr);
	}
	
	@Test
	public void testSelectCardsToRemoveOne() {
		Hand mockHand = mock(Hand.class);
		Card[] cardArray = new Card[]{ACE_SPADE,KING_SPADE, QUEEN_SPADE, JACK_SPADE};
		List<Card> cards = TestUtil.toLinkedList(cardArray);
		when(mockHand.getCards()).thenReturn(cards);
		when(mockHand.toString()).thenReturn("Hand");
		when(mockHand.getCardAt(0)).thenReturn(ACE_SPADE);
		hpci.onReceiveCards(mockHand);
		
		String input = "1"+eo1+"1";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		hpci.setScanner(new Scanner(in));
		
		List<Card> expectedCards = new LinkedList<Card>();
		expectedCards.add(ACE_SPADE);
		List<Card> actualCards = hpci.selectCardsToRemove(1);
		assertEquals(expectedCards, actualCards);
		
		String expectedStr = "Your hand is..."+eo1
				+"Hand" +eo1
				+"Which card would you like to swap (From 1 - 4)?: ";
		String actualStr = outContent.toString();
		assertEquals(expectedStr, actualStr);
	}
	
	@Test
	public void testSelectCardsToRemoveRepeated() {
		Hand mockHand = mock(Hand.class);
		Card[] cardArray = new Card[]{ACE_SPADE,KING_SPADE, QUEEN_SPADE, JACK_SPADE};
		List<Card> cards = TestUtil.toLinkedList(cardArray);
		when(mockHand.getCards()).thenReturn(cards);
		when(mockHand.toString()).thenReturn("Hand");
		when(mockHand.getCardAt(0)).thenReturn(ACE_SPADE);
		hpci.onReceiveCards(mockHand);
		
		String input = "1"+eo1+"1"+eo1+"2";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		hpci.setScanner(new Scanner(in));
		
		List<Card> expectedCards = new LinkedList<Card>();
		expectedCards.add(ACE_SPADE);
		expectedCards.add(KING_SPADE);
		List<Card> actualCards = hpci.selectCardsToRemove(2);
		assertEquals(expectedCards, actualCards);		// not working 
									// not picking up second card..
		
		String expectedStr = "Your hand is..."+eo1
				+"Hand" +eo1
				+"Which card would you like to swap (From 1 - 4)?: ";
		String actualStr = outContent.toString();
		assertEquals(expectedStr, actualStr);
	}

}
