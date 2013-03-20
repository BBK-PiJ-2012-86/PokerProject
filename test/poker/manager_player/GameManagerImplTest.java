package poker.manager_player;

import static org.junit.Assert.*;

import java.util.List;

import poker.manager_player.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static poker.hand_card.TestCards.ACE_SPADE;
import static poker.hand_card.TestCards.FIVE_CLUB;
import static poker.hand_card.TestCards.FOUR_CLUB;
import static poker.hand_card.TestCards.JACK_SPADE;
import static poker.hand_card.TestCards.KING_SPADE;
import static poker.hand_card.TestCards.QUEEN_SPADE;
import static poker.hand_card.TestCards.TEN_SPADE;
import static poker.hand_card.TestCards.THREE_CLUB;
import static poker.hand_card.TestCards.TWO_CLUB;
import poker.hand_card.*;

public class GameManagerImplTest {

	private GameManagerImpl gameManager;
	private Player computerPlayer;
	private Player humanPlayer;
	
	@Before
	public void setUp(){
		GameListener listner = new GameConsoleListener();
		gameManager = new GameManagerImpl(GameType.FIVE_CARD_DRAW, listner);
		computerPlayer = mock(ComputerPlayer.class);
		humanPlayer = mock(HumanPlayer.class);
	}
	
	@Test
	public void testAddComputerPlayer() {
		gameManager.addComputerPlayer(AiType.NORMAL);
		assertTrue(gameManager.getPlayers().size() == 1);
	}
	
	@Test
	public void testAddHumanPlayer(){
		gameManager.addHumanPlayer("Ted");
		assertTrue(gameManager.getPlayers().size() == 1);
	}
	
	@Test
	public void testAddPlayer(){
		gameManager.addPlayer(computerPlayer);
		gameManager.addPlayer(humanPlayer);
		assertTrue(((GameManagerImpl)gameManager).getPlayers().size() == 2);
	}
	
	@Test
	public void testDeal(){
		PlayerFactory playerFactory = new PlayerFactoryImpl();
		Player computerPlayer1 = playerFactory.createComputerPlayer(AiType.NORMAL);
		Player humanPlayer1 = playerFactory.createHumanPlayer("Ted");
		gameManager.addPlayer(computerPlayer1);
		gameManager.addPlayer(humanPlayer1);
		gameManager.deal();
		int expected = 5;
		int result1 = computerPlayer1.getHand().getCards().size();
		int result2 = humanPlayer1.getHand().getCards().size();
		assertEquals(expected, result1, result2);
	}
	
	@Test
	public void testChangeCards(){
		when(computerPlayer.exchangeCards()).thenReturn(2);
		when(humanPlayer.exchangeCards()).thenReturn(1);
		gameManager.addPlayer(computerPlayer);
		gameManager.addPlayer(humanPlayer);
		gameManager.deal();
		gameManager.playersChangeCards();
		int expected = 52 - 13;
		int result = gameManager.getDeck().getCards().size();
		assertEquals(expected, result);
	}
	
	@Test
	public void testEvaluateWinnerEqualHand(){
		PlayerFactory playerFactory = new PlayerFactoryImpl();
		Player computerPlayer1 = playerFactory.createComputerPlayer(AiType.NORMAL);
		Player humanPlayer1 = playerFactory.createHumanPlayer("Ted");
		Card[] Cards = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_SPADE, TEN_SPADE};
		List<Card> list = TestUtil.toLinkedList(Cards);
		computerPlayer1.receiveCards(list);
		humanPlayer1.receiveCards(list);
		gameManager.addPlayer(computerPlayer1);
		gameManager.addPlayer(humanPlayer1);
		List<Player> result = gameManager.evaluateWinner();
		assertEquals(2, result.size());
	}
	
	@Test
	public void testEvaluateWinnerDifferentHand(){
		PlayerFactory playerFactory = new PlayerFactoryImpl();
		Player computerPlayer1 = playerFactory.createComputerPlayer(AiType.NORMAL);
		Player humanPlayer1 = playerFactory.createHumanPlayer("Ted");
		Card[] Cards = new Card[] {ACE_SPADE, KING_SPADE, QUEEN_SPADE, JACK_SPADE, TEN_SPADE};
		List<Card> list = TestUtil.toLinkedList(Cards);
		humanPlayer1.receiveCards(list);
		Cards = new Card[] {FIVE_CLUB, ACE_SPADE, FOUR_CLUB, THREE_CLUB, TWO_CLUB};
		list = TestUtil.toLinkedList(Cards);
		computerPlayer1.receiveCards(list);
		gameManager.addPlayer(computerPlayer1);
		gameManager.addPlayer(humanPlayer1);
		List<Player> result = gameManager.evaluateWinner();
		assertEquals(humanPlayer1, result.get(0));
	}

}
