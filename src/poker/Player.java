package poker;

import java.util.ArrayList;

public abstract class Player {
	
	private CheckerFactory checkerFactory;
	private HandImpl hand;
	
	public Player(CheckerFactory checkerFactory) {
		this.checkerFactory = checkerFactory;
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
		Checker checker = checkerFactory.getChecker(hand);
		return checker.check();
	}


}
