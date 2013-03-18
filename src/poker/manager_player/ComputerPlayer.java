package poker.manager_player;


import java.util.List;

import poker.hand_card.Card;
import poker.hand_card.Decider;
import poker.hand_card.DeciderFactory;


/**
 * A ComputerPlayer plays poker with a Decider to make its card replacement decisions
 *
 */
public class ComputerPlayer extends Player {
	
	private Decider computerAI;

	public ComputerPlayer(String username, GameType gameType){
		super(username, gameType);
		DeciderFactory d = new DeciderFactory();
		computerAI = d.getDecider(gameType);
	}
	
	@Override
	public int exchangeCards() {
		List<Card> cards = computerAI.decide(check());
		removeCardsFromHand(cards);
		return cards.size();
	}
	
}
