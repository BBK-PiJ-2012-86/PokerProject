package poker.manager_player;

import java.util.Comparator;
import java.util.List;

import lombok.Getter;
import poker.hand_card.*;



/**
 * Players play poker
 *
 */
public abstract class Player {
	
	//protected GameType gameType;		not needed?
	@Getter protected Hand hand;
	@Getter protected final String username;
	protected Checker checker;
	
	
	public Player(String username, GameType gameType) {
		//this.gameType = gameType;		not needed?
		this.username = username;		//think about uniqueness??
		this.checker = CheckerFactory.getInstance(gameType).getChecker();
		this.hand = new HandImpl();
	}
	
	/**
	 * Removes all cards from the players hand
	 */
	public void removeCards(){
		hand.clearHand();
	}
	
	/**
	 * Allows players to receive cards into their hand
	 * @param cards the cards to be received
	 */
	public void recieveCards(List<Card> cards){
		hand.addCards(cards);
	}
	
	/**
	 * @return the number of cards the player is swapping
	 */
	public abstract int exchangeCards();
	
	/**
	 * Checks the players hand cards fort he best available poker hand
	 * @return the result corresponding to the best available poker hand
	 */
	public CheckResult check() {
		return checker.check(hand);
	}
	
	/**
	 * Changes the type of poker game the player is playing
	 * @param gameType the new gameType
	 */
	public void changeGameType(GameType gameType) {
		//this.gameType = type;
		checker = CheckerFactory.getInstance(gameType).getChecker();
	}
	
	
	/**
	 * Removes a specified list of cards from the players hand
	 * @param cards the cards to be removed
	 */
	public void removeCardsFromHand(List<Card> cards){
		hand.removeCards(cards);
	}

	private static final Comparator<Player> HAND_RANKING = new Comparator<Player>(){
		
		@Override
		public int compare(Player p1, Player p2){
			CheckResult p1Result = p1.check();
			CheckResult p2Result = p2.check();
			Comparator<CheckResult> comparator = CheckResult.getComparator();
			return comparator.compare(p1Result, p2Result);
		}
	};
	
	/**
	 * @return a Comparator to compare players based on checking their poker hand strengths
	 */
	public Comparator<Player> getCheckResultRanking(){
		return HAND_RANKING; 
	}
	
}
