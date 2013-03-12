package Factories;

import poker.Decider;
import poker.DeciderImpl;
import poker.GameType;

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
