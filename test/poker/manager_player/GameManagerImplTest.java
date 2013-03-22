package poker.manager_player;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static poker.hand_card.TestCards.ACE_SPADE;
import static poker.hand_card.TestCards.FIVE_CLUB;
import static poker.hand_card.TestCards.FIVE_SPADE;
import static poker.hand_card.TestCards.FOUR_CLUB;
import static poker.hand_card.TestCards.FOUR_SPADE;
import static poker.hand_card.TestCards.JACK_CLUB;
import static poker.hand_card.TestCards.JACK_DIAMOND;
import static poker.hand_card.TestCards.JACK_HEART;
import static poker.hand_card.TestCards.JACK_SPADE;
import static poker.hand_card.TestCards.KING_SPADE;
import static poker.hand_card.TestCards.NINE_SPADE;
import static poker.hand_card.TestCards.QUEEN_SPADE;
import static poker.hand_card.TestCards.SIX_CLUB;
import static poker.hand_card.TestCards.SIX_SPADE;
import static poker.hand_card.TestCards.TEN_CLUB;
import static poker.hand_card.TestCards.TEN_DIAMOND;
import static poker.hand_card.TestCards.TEN_SPADE;
import static poker.hand_card.TestCards.THREE_CLUB;
import static poker.hand_card.TestCards.TWO_CLUB;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import poker.hand_card.Card;
import poker.hand_card.TestUtil;

public class GameManagerImplTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private String eol = System.getProperty("line.separator");
	private GameListener listener;
	
	@Before
	public void setUp(){
		System.setOut(new PrintStream(outContent));
		listener = new GameConsoleListener();
	}
	
	@After
	public void tearDown() throws Exception {
		System.setOut(null);
	}
		
	@Test
	public void testChangeZeroFixed() {
		Card[] compArr = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_SPADE, TEN_SPADE};
		Card[] humArr = new Card[] {JACK_CLUB, JACK_DIAMOND, JACK_HEART, TEN_CLUB, TEN_DIAMOND};
		String humInput = "0";
		Card[] compSwap = new Card[]{};
		Card[] humSwap = new Card[]{};
		String expected = "Your hand is..."+eol
				+"[jack of clubs, jack of diamonds, jack of hearts, ten of clubs, ten of diamonds]"+eol
				+"How many cards would you like to swap (max 3): The winner(s):"+eol
				+"Computer 1"+eol
				+"Their hand(s):"+eol
				+"[ace of spades, king of spades, queen of spades, jack of spades, ten of spades]"+eol;

		fixedTest(compArr, humArr, compSwap, humSwap, humInput, expected);
	}
	
	@Test
	public void testHumChangeOneFixed() {
		Card[] compArr = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_SPADE, TEN_SPADE};
		Card[] humArr = new Card[] {JACK_CLUB, JACK_DIAMOND, JACK_HEART, TEN_CLUB, TEN_DIAMOND};
		String humInput = "1"+eol+"1";
		Card[] compSwap = new Card[]{};
		Card[] humSwap = new Card[]{TWO_CLUB};
		String expected = "Your hand is..."+eol
				+"[jack of clubs, jack of diamonds, jack of hearts, ten of clubs, ten of diamonds]"+eol
				+"How many cards would you like to swap (max 3): Which card would you like to swap (From 1 - 5)?: Your hand is..."+eol
				+"[jack of diamonds, jack of hearts, ten of clubs, ten of diamonds, two of clubs]"+eol
				+"The winner(s):"+eol
				+"Computer 1"+eol
				+"Their hand(s):"+eol
				+"[ace of spades, king of spades, queen of spades, jack of spades, ten of spades]"+eol;

		fixedTest(compArr, humArr, compSwap, humSwap, humInput, expected);
	}
	
	@Test
	public void testCompChangeThreeFixed() {
		Card[] compArr = new Card[] {FIVE_CLUB, SIX_SPADE, JACK_HEART, NINE_SPADE, THREE_CLUB};
		Card[] humArr = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_SPADE, FOUR_CLUB};
		String humInput = "0";
		Card[] compSwap = new Card[]{SIX_CLUB, TEN_CLUB, TEN_DIAMOND};
		Card[] humSwap = new Card[]{};
		String expected = "Your hand is..."+eol
				+"[ace of spades, king of spades, queen of spades, jack of spades, four of clubs]"+eol
				+"How many cards would you like to swap (max 3): The winner(s):"+eol
				+"Computer 1"+eol
				+"Their hand(s):"+eol
				+"[ten of clubs, ten of diamonds, jack of hearts, nine of spades, six of clubs]"+eol;

		fixedTest(compArr, humArr, compSwap, humSwap, humInput, expected);
	}
	
	@Test
	public void testDraw() {
		Card[] compArr = new Card[] {JACK_CLUB, TEN_CLUB, SIX_CLUB, FIVE_CLUB, FOUR_CLUB};
		Card[] humArr = new Card[] {JACK_SPADE, TEN_SPADE, SIX_SPADE, FIVE_SPADE, FOUR_SPADE};
		String humInput = "0";
		Card[] compSwap = new Card[]{};
		Card[] humSwap = new Card[]{};
		String expected = "Your hand is..."+eol
			+"[jack of spades, ten of spades, six of spades, five of spades, four of spades]"+eol
			+"How many cards would you like to swap (max 3): The winner(s):"+eol
			+"Computer 1"+eol
			+"Their hand(s):"+eol
			+"[jack of clubs, ten of clubs, six of clubs, five of clubs, four of clubs]"+eol
			+"Bob"+eol
			+"Their hand(s):"+eol
			+"[jack of spades, ten of spades, six of spades, five of spades, four of spades]"+eol;

		fixedTest(compArr, humArr, compSwap, humSwap, humInput, expected);
	}

	private void fixedTest(Card[] compArr, Card[] humArr, Card[] compSwap, Card[] humSwap, String humInput, String expected) {
		final List<List<Card>> handCards = new LinkedList<List<Card>>();
		List<Card> compCards = TestUtil.toLinkedList(compArr);
		List<Card> humCards = TestUtil.toLinkedList(humArr);
		handCards.add(compCards);
		handCards.add(humCards);
		
		final List<List<Card>> swapCards = new LinkedList<List<Card>>();
		List<Card> compSwapCards = TestUtil.toLinkedList(compSwap);
		List<Card> humSwapCards = TestUtil.toLinkedList(humSwap);
		swapCards.add(compSwapCards);
		swapCards.add(humSwapCards);
		
		fixedTestList(humInput, expected, handCards, swapCards);
	}

	private void fixedTestList(String humInput, String expected, 
			final List<List<Card>> handCards, final List<List<Card>> swapCards) {
		CardDealer mockCardDealer = makeMockDealer(handCards, swapCards);
		CardDealerFactory.getInstance().setMockCardDealer(mockCardDealer);
		
		GameManager gameManager = new GameManagerImpl(GameType.FIVE_CARD_DRAW, listener);
		ByteArrayInputStream in = new ByteArrayInputStream(humInput.getBytes());
		System.setIn(in);
		gameManager.addComputerPlayer(AiType.NORMAL);
		gameManager.addHumanPlayer("Bob");
		gameManager.playRound();
		
		String actual = outContent.toString();
		
		assertEquals(expected, actual);
	}

	@SuppressWarnings("unchecked")
	private CardDealer makeMockDealer(final List<List<Card>> handCards,
			final List<List<Card>> swapCards) {
		CardDealer mockCardDealer = mock(CardDealer.class);

		doAnswer(new Answer() {
			public Object answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				CircularLinkedList<Player> players = (CircularLinkedList<Player>) args[0];
				int i = 0;
				for(Player player: players){
					player.receiveCards(handCards.get(i));
					i++;
				}
				return null;
			}}).when(mockCardDealer).deal((CircularLinkedList<Player>) anyObject(), anyInt());
		
		doAnswer(new Answer() {
			public Object answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				CircularLinkedList<Player> players = (CircularLinkedList<Player>) args[0];
				List<Card> cards = new LinkedList<Card>();
				int playerNumber = 0;
				for(Player player: players){
					int cardsToSwap = player.exchangeCards();
					if(cardsToSwap > 0){
						cards = swapCards.get(playerNumber);
						player.receiveCards(cards);
					}
					playerNumber++;
				}
				return null;
			}}).when(mockCardDealer).playersChangeCards((CircularLinkedList<Player>) anyObject());
		return mockCardDealer;
	}
	
	//just check it runs normally
	@Test
	public void testChangeZero() {
		String input = "0"+eol;
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GameManager gameManager = new GameManagerImpl(GameType.FIVE_CARD_DRAW, listener);
		gameManager.addComputerPlayer(AiType.NORMAL);
		gameManager.addHumanPlayer("Bob");
		gameManager.playRound();
	}
	
	@Test
	public void testChangeOne() {
		String input = "1"+eol+"1";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		GameManager gameManager = new GameManagerImpl(GameType.FIVE_CARD_DRAW, listener);
		gameManager.addComputerPlayer(AiType.NORMAL);
		gameManager.addHumanPlayer("Bob");
		gameManager.playRound();
	}
}
