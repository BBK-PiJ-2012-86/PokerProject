package poker.manager_player;


/**
 * A PlayerFactory produces players
 *
 */
public interface PlayerFactory {
	
	/**
	 * Creates a human poker player
	 * @param username the username the player will use
	 * @param gameType the type of poker the player is going to play
	 * @return the human player
	 */
	public HumanPlayer createHumanPlayer(String username, GameType gameType);
	
	/**
	 * Creates a computer poker player
	 * @param gameType the type of poker the player is going to play
	 * @return the computer player
	 */
	public ComputerPlayer createComputerPlayer(GameType gameType);
}
