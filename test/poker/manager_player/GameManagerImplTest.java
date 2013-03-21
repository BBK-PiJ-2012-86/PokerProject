package poker.manager_player;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

public class GameManagerImplTest {

	private GameManagerImpl gameManager;
	private String eol = System.getProperty("line.separator");
	
	@Before
	public void setUp(){
		GameListener listener = new GameConsoleListener();
		gameManager = new GameManagerImpl(GameType.FIVE_CARD_DRAW, listener);
	}
	
	@Test
	public void testChangeZero() {
		String input = "0"+eol;
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		gameManager.addComputerPlayer(AiType.NORMAL);
		gameManager.addHumanPlayer("Bob");
		gameManager.playRound();	//need to get inside this..
	}
	
	@Test
	public void testChangeOne() {
		String input = "1"+eol+"1";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		gameManager.addComputerPlayer(AiType.NORMAL);
		gameManager.addHumanPlayer("Bob");
		gameManager.playRound();
	}
}
