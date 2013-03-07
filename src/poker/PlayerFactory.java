package poker;

public interface PlayerFactory {
	
	public HumanPlayer createHumanPlayer(String username, int gameType);
	
	public ComputerPlayer createComputerPlayer(int gameType);
}
