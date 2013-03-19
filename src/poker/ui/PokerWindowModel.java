package poker.ui;

import poker.manager_player.ComputerPlayer;
import poker.manager_player.GameManager;
import poker.manager_player.GameManagerImpl;
import poker.manager_player.GameType;
import poker.manager_player.HumanPlayerConsoleInterface;

public class PokerWindowModel {		// need to do GameManagerImpl first
	
	private GameManager game;
	
	public void fakeSetUp() {
		game = new GameManagerImpl(GameType.FIVE_CARD_DRAW);
		ComputerPlayer computer = new ComputerPlayer("Bob", GameType.FIVE_CARD_DRAW);
		HumanPlayerConsoleInterface player = new HumanPlayerConsoleInterface("User", GameType.FIVE_CARD_DRAW);
		
		game.addPlayer(computer);
		game.addPlayer(player);
	}
	

}
