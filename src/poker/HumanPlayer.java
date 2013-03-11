package poker;

import java.util.ArrayList;

public class HumanPlayer implements Player {

	private Hand hand;
	private String username;
	
	public HumanPlayer(String username, int cards){
		Card[] hand = new Card[cards];
		this.username = username;
	}
	
	@Override
	public int swapCards() {
		System.out.println("How many cards would you like to swap (max 3)?");
		String str = System.console().readLine();
		Integer swap = Integer.parseInt(str);
		if(swap <= 3){
			return swap;
		}else{
			throw new IllegalArgumentException("That is not a valid selection");
		}
	}
	
	/*
	 * Returns the number (index in the array) of the card the player would like to swap
	 * 
	 * Throws Illegal Argument Exception if the number entered by the user is greater than
	 * the length of the array or less than one (because an index out of bounds exception will
	 * be thrown later).
	 */
	
	public Integer cardSwap(){
		System.out.println("Which card would you like to swap (From 1 - 5)?");
		String str = System.console().readLine();
		Integer swap = Integer.parseInt(str);
		if(swap > hand.length || swap < 1){
			throw new IllegalArgumentException("That is not a valid selection");
		}
		return swap - 1;
	}
	
	public ArrayList<Integer> swapOneCard(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(cardSwap());
		return result;
	}
	
	public ArrayList<Integer> swapTwoCards(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(cardSwap());
		result.add(cardSwap());
		return result;
	}
	
	public ArrayList<Integer> swapThreeCards(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(cardSwap());
		result.add(cardSwap());
		result.add(cardSwap());
		return result;
	}
	
	public void displayCards(){
		System.out.println("Your hand is:");
		for(Card card: hand){
			System.out.println(card.toString());
		}
	}
	
	public void changeCards(ArrayList<Integer> cardNumbers, ArrayList<Card> replacements){
		for(int i = 0; i < cardNumbers.size(); i++){
			hand[cardNumbers.get(i)] = replacements.get(i);
		}
	}
	


}
