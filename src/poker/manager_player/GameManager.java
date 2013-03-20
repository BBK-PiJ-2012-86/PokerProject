package poker.manager_player;

public interface GameManager {

	/**
	 * Play a round of poker
	 */
	public abstract void playRound();

	/**
	 * @param username the name of the human player to be added
	 */
	void addHumanPlayer(String username);

	/**
	 * @param aiType the AI this player will use to decide card swaps
	 */
	void addComputerPlayer(AiType aiType);

}