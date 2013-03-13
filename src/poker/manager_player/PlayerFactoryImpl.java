package poker.manager_player;


public class PlayerFactoryImpl implements PlayerFactory {

	private static Integer computerIDs;
	
	public PlayerFactoryImpl(){
		if(computerIDs == null){	//TODO: see note below
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
		computerIDs++;									//TODO: see note below
		return (ComputerPlayer) computerPlayer;
	}
	
	/*
	Suggesting this is bad practice:
	
	"This instance method writes to a static field. This is tricky to get correct if multiple instances
	are being manipulated, and generally bad practice. 
	
	Aside from the concurrency issues, it means that all of the instances in the JVM are accessing
	the same data, and would not allow two separate groups of instances. It would be better if you
	had a singleton "manager" object and passed it to each of the instances as a constructor
	parameter or at least as a setManager() method argument."
	 */
}
