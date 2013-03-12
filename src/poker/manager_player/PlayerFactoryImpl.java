package poker.manager_player;


public class PlayerFactoryImpl implements PlayerFactory {

	private static Integer computerIDs;
	
	public PlayerFactoryImpl(){
		if(computerIDs == null){
			computerIDs = 1;
		}
	}
	
	public HumanPlayer createHumanPlayer(String username, GameType gameType){
		Player humanPlayer = new HumanPlayer(username, gameType);
		return (HumanPlayer) humanPlayer;
	}
	
	public ComputerPlayer createComputerPlayer(GameType gameType){
		String username  = "Computer " + computerIDs;
		Player computerPlayer = new ComputerPlayer(username, gameType);
		computerIDs++;
		return (ComputerPlayer) computerPlayer;
	}
}
