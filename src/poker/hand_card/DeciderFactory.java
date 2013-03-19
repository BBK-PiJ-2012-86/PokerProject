package poker.hand_card;

import poker.manager_player.GameType;

/**
 * A DeciderFactory returns a Decider dependent on the game type and AI type (??)
 *
 */
public class DeciderFactory {
	
	/**
	 * @param gameType the type of poker to be played
	 * @return a Decider to enable swap choosing
	 */
	public Decider getDecider(GameType gameType){
		Decider result;
		switch (gameType){
		case FIVE_CARD_DRAW:
			result = new DeciderImpl();
			break;
		default: result = new DeciderImpl();
			break;
		}
		return result;
	}
}
