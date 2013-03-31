package poker;
/*
 * Poker Project by Ruth Baylis and Ed Hogarth
 */
import poker.manager_player.AiType;
import poker.manager_player.GameConsoleListener;
import poker.manager_player.GameManager;
import poker.manager_player.GameManagerImpl;
import poker.manager_player.GameType;

public class GameScript {

	public static void main(String [] args){
		GameScript script = new GameScript();
		script.launch();
	}

	private void launch(){
		GameManager game = new GameManagerImpl(GameType.FIVE_CARD_DRAW, new GameConsoleListener());
		game.addComputerPlayer(AiType.NORMAL);
		game.addHumanPlayer("Ted");
		
		for(int i = 0; i < 1; i++){
			System.out.println("NEW GAME! Have fun :)");
			game.playRound();
			System.out.println();
		}
	}
}
