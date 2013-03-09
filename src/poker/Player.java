package poker;

import java.util.ArrayList;

public abstract class Player {
	
	private GameManagerImpl game;
	private Card[] hand;
	
	public Player(GameManagerImpl game) {
		this.game = game;
	}
	
	/*
	 * 
	 * Throws IllegalArgumentExcpetion if the player does not enter a valid number of cards
	 * to swap.
	 */
	
	public abstract int swapCards();
	
	public abstract ArrayList<Integer> swapOneCard();
	
	public abstract ArrayList<Integer> swapTwoCards();
	
	public abstract ArrayList<Integer> swapThreeCards();
	

	public CheckResult check() {
		return game.getChecker().check(hand);
	}


}
