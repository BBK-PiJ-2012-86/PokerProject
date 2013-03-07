package poker;

public class PlayerFactoryImpl implements PlayerFactory {

	private static Integer computerIDs;
	
	public PlayerFactoryImpl(){
		if(computerIDs == null){
			computerIDs = 1;
		}
	}
	
	private int getNumberOfCards(int gameType){
		int cards;
		switch (gameType){
		case 1: cards = 5;
		break;
		default: cards = 5;
		break;
		}
		return cards;
	}
	
	public HumanPlayer createHumanPlayer(String username, int gameType){
		int cards = getNumberOfCards(gameType);
		Player humanPlayer = new HumanPlayer(username, cards);
		return humanPlayer;
	}
	
	public ComputerPlayer createComputerPlayer(int gameType){
		int cards = getNumberOfCards(gameType);
		Player computerPlayer = new ComputerPlayer(computerIDs, gameType);
		computerIDs++;
		return computerPlayer;
	}
}
