package poker.manager_player;

import java.util.List;

public interface GameManager {

	public abstract void addPlayer(Player player);

	public abstract void deal();

	public abstract void playRound();

	public abstract void playersChangeCards();

	public abstract List<Player> evaluateWinner();

	public abstract void deletePlayerCards();

}