package poker.manager_player;

import static org.junit.Assert.*;

import poker.manager_player.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

}
