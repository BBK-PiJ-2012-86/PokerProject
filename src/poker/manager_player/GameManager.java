package poker.manager_player;

public interface GameManager {

	/**
	 * Adds a player to the game
	 * @param player the player to be added to the game
	 */
	public abstract void addPlayer(Player player);

	/**
	 * Play a round of poker
	 */
	public abstract void playRound();

}