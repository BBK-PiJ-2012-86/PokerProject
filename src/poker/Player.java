package poker;

import java.util.ArrayList;

public interface Player {
	
	/*
	 * 
	 * Throws IllegalArgumentExcpetion if the player does not enter a valid number of cards
	 * to swap.
	 */
	
	public int swapCards();
	
	public ArrayList<Integer> swapOneCard();
	
	public ArrayList<Integer> swapTwoCards();
	
	public ArrayList<Integer> swapThreeCards();
}
