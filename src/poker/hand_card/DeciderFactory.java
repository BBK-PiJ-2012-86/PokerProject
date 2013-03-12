package poker.hand_card;

import poker.manager_player.GameType;

public class DeciderFactory {
	
	public Decider getDecider(GameType gameType){
		Decider result;
		switch (gameType){
		case fiveCardDraw: result = new DeciderImpl();
		break;
		default: result = new DeciderImpl();
		break;
		}
		return result;
	}
}
