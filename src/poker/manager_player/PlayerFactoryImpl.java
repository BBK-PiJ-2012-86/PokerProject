package poker.manager_player;



public class PlayerFactoryImpl implements PlayerFactory {
	
	private static final PlayerFactory INSTANCE = new PlayerFactoryImpl();

	public static PlayerFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public HumanPlayer createHumanPlayer(String username){
		Player humanPlayer = new HumanPlayer(username, new HumanPlayerConsoleInterface());
		return (HumanPlayer) humanPlayer;
	}
	
	@Override
	public ComputerPlayer createComputerPlayer(AiType aiType, int id){
		String username  = "Computer " + id;
		Player computerPlayer = new ComputerPlayer(username, aiType);
		return (ComputerPlayer) computerPlayer;
	}
	
}
