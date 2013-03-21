package poker.manager_player;

import org.junit.Before;
import org.junit.Test;

public class GameManagerImplTest {

	private GameManagerImpl gameManager;
	
	@Before
	public void setUp(){
		GameListener listener = new GameConsoleListener();
		gameManager = new GameManagerImpl(GameType.FIVE_CARD_DRAW, listener);
	}
	
	@Test
	public void test() {
		gameManager.addComputerPlayer(AiType.NORMAL);
		gameManager.addHumanPlayer("Bob");
		//gameManager.playRound();
	}
}
