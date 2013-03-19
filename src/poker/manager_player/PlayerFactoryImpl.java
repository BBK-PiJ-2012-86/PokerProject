package poker.manager_player;

import poker.singleton.ComputerIDs;


public class PlayerFactoryImpl implements PlayerFactory {

	
	public HumanPlayer createHumanPlayer(String username, GameType gameType){
		Player humanPlayer = new HumanPlayer(username, gameType, new HumanPlayerConsoleInterface());
		return (HumanPlayer) humanPlayer;
	}
	
	public ComputerPlayer createComputerPlayer(GameType gameType){
		String username  = "Computer " + ComputerIDs.getInstance().getCounter();
		Player computerPlayer = new ComputerPlayer(username, gameType);
		return (ComputerPlayer) computerPlayer;
	}
	
}
