package poker.manager_player;

import java.util.List;

public class GameConsoleListener implements GameListener {

	@Override
	public void announceWinner(List<Player> winners){
		System.out.println("The winner(s):");
		for(Player player: winners){
			System.out.println(player.getUsername());
			System.out.println("Their hand(s):");
			System.out.println(player.getHand());
		}
	}

}
