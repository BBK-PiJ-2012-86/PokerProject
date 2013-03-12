package poker;

import poker.ComputerPlayer;
import poker.GameType;
import poker.HumanPlayer;

public interface PlayerFactory {
	
	public HumanPlayer createHumanPlayer(String username, GameType gameType);
	
	public ComputerPlayer createComputerPlayer(GameType gameType);
}
