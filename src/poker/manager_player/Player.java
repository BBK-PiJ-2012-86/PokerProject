package poker.manager_player;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.Getter;
import poker.hand_card.*;
import poker.manager_player.GameType;



public abstract class Player {
	
	//protected GameType gameType;		not needed?
	@Getter protected Hand hand;
	@Getter protected final String username;
	protected Checker checker;
	protected int maxCardSwapped;
	
	public Player(String username, GameType gameType) {
		//this.gameType = gameType;		not needed?
		this.username = username;
		this.checker = CheckerFactory.getInstance(gameType).getChecker();
		this.hand = new HandImpl();
		setMaxCardSwapped(gameType);
	}
	
	public void setMaxCardSwapped(GameType gameType){
		switch(gameType){
		case fiveCardDraw: maxCardSwapped = 3;
		break;
		default: maxCardSwapped = 3;
		break;
		}
	}
	
	public void removeCards(){
		hand.clearHand();
	}
	
	public void recieveCards(List<Card> cards){
		hand.addCards(cards);
	}
	
	public abstract int exchangeCards();
	
	public CheckResult check() {
		return checker.check(hand);
	}
	
	public void changeGameType(GameType gameType) {
		//this.gameType = type;
		checker = CheckerFactory.getInstance(gameType).getChecker();
	}
	
	
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
	
	public Comparator<Player> getCheckResultRanking(){
		return HAND_RANKING; 
	}
}
