package poker.ui;

import poker.manager_player.CircularArrayList;
import poker.manager_player.ComputerPlayer;
import poker.manager_player.GameManager;
import poker.manager_player.GameManagerImpl;
import poker.manager_player.GameType;
import poker.manager_player.HumanPlayer;
import poker.manager_player.Player;

public class PokerWindowModel {		// need to do GameManagerImpl first
	
	private GameManager game;
	
	public void fakeSetUp() {
		CircularArrayList<Player> players = new CircularArrayList<>();
		ComputerPlayer computer = new ComputerPlayer("Bob", null);
		HumanPlayer player = new HumanPlayer("User", null);
		players.add((Player) computer);
		players.add((Player) player);
		game = new GameManagerImpl(GameType.FIVE_CARD_DRAW, players);
	}
}
