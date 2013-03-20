package poker.manager_player;

import poker.singleton.ComputerIDs;


public class PlayerFactoryImpl implements PlayerFactory {

	
	public HumanPlayer createHumanPlayer(String username){
		Player humanPlayer = new HumanPlayer(username, new HumanPlayerConsoleInterface());
		return (HumanPlayer) humanPlayer;
	}
	
	public ComputerPlayer createComputerPlayer(AiType aiType){
		String username  = "Computer " + ComputerIDs.getInstance().getCounter();
		Player computerPlayer = new ComputerPlayer(username, aiType);
		return (ComputerPlayer) computerPlayer;
	}
	
}
