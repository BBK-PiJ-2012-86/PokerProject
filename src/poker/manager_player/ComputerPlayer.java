package poker.manager_player;


import java.util.List;

import poker.hand_card.Card;
import poker.hand_card.Decider;
import poker.hand_card.DeciderFactory;


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
	
	
	
	// not needed - has a Comparator in Player instead
	/*@Override
	public int compare(Player p1, Player p2){
		CheckResult p1Result = p1.check();
		CheckResult p2Result = p2.check();
		Comparator<CheckResult> comparator = CheckResult.getComparator();
		return comparator.compare(p1Result, p2Result);
	} */

}
