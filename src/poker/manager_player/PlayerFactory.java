package poker.manager_player;


/**
 * A PlayerFactory produces players
 *
 */
public interface PlayerFactory {
	
	/**
	 * Creates a human poker player
	 * @param username the username the player will use
	 * @return the human player
	 */
	public HumanPlayer createHumanPlayer(String username);
	
	/**
	 * Creates a computer poker player
	 * @param aiType the type of AI used to decide card swaps
	 * @return the computer player
	 */
	public ComputerPlayer createComputerPlayer(AiType aiType);
}
