package poker.manager_player;

public interface GameManager {

	public abstract void addPlayer(Player player);

	public abstract void deal();

	public abstract void playRound();

	public abstract void playersChangeCards();

	public abstract void evaluateWinner();

	public abstract void deletePlayerCards();

}