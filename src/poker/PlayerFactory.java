package poker;

public interface PlayerFactory {
	
	public HumanPlayer createHumanPlayer(String username);
	
	public ComputerPlayer createComputerPlayer();
}
