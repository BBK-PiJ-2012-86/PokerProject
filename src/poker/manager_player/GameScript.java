package poker.manager_player;

public class GameScript {

	public static void main(String [] args){
		GameScript script = new GameScript();
		script.launch();
	}

	private void launch(){
		GameManager game = new GameManagerImpl(GameType.FIVE_CARD_DRAW, new GameConsoleListener());
		PlayerFactory pfactory = new PlayerFactoryImpl();	//should access through game
		game.addPlayer(pfactory.createHumanPlayer("Ted"/*, GameType.FIVE_CARD_DRAW*/));
		game.addPlayer(pfactory.createComputerPlayer(/*GameType.FIVE_CARD_DRAW,*/ AiType.NORMAL));
		for(int i = 0; i < 2; i++){
			System.out.println("NEW GAME! Have fun :)");
			game.playRound();
			System.out.println();
		}
	}
}
