package poker;

public class PlayerFactoryImpl implements PlayerFactory {

	private static Integer computerIDs;
	
	public PlayerFactoryImpl(){
		if(computerIDs == null){
			computerIDs = 1;
		}
	}
	
	public HumanPlayer createHumanPlayer(String username){
		Player humanPlayer = new HumanPlayer(username);
		return humanPlayer;
	}
	
	public ComputerPlayer createComputerPlayer(){
		Player computerPlayer = new ComputerPlayer(computerIDs);
		computerIDs++;
		return computerPlayers;
	}
}
