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
	HumanPlayer createHumanPlayer(String username);
	
	/**
	 * Creates a computer poker player
	 * @param aiType the type of AI used to decide card swaps
	 * @param gameId ide that the computer player will have withing the game that creates it
	 * @return the computer player
	 */
	ComputerPlayer createComputerPlayer(AiType aiType, int gameId);

}
