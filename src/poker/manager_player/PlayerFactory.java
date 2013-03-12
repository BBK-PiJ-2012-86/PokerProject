package poker.manager_player;


public interface PlayerFactory {
	
	public HumanPlayer createHumanPlayer(String username, GameType gameType);
	
	public ComputerPlayer createComputerPlayer(GameType gameType);
}
