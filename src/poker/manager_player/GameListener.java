package poker.manager_player;

import java.util.List;

public interface GameListener {
	public void announceWinner(List<Player> winners);
}
