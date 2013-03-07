package poker;

import java.util.ArrayList;

public class HumanPlayer implements Player {

	private Card[] hand;
	private String username;
	
	public HumanPlayer(String username, int cards){
		Card[] hand = new Card[cards];
		this.username = username;
	}
	
	@Override
	public boolean swapCards() {
		System.out.println("How many cards would you like to swap (max 3)?");
		String str = System.console().readLine();
		Integer swap = Integer.parseInt(str);
		switch (swap){
		case 0: return false;
		break;
		case 1: swapOneCard();
		return true;
		break;
		case 2: swapTwoCards();
		return true;
		break;
		case 3: swapThreeCards();
		return true;
		break;
		default: throw new IllegalArgumentException("That is not a valid selection");
		}
	}
	
	public Integer swapCard(){
		System.out.println("Which card would you like to swap (From 1 - 5)?");
		String str = System.console().readLine();
		Integer swap = Integer.parseInt(str);
		if(swap > 5){
			throw new IllegalArgumentException("That is not a valid selection");
		}
		return swap - 1;
	}
	
	/*
	 * (non-Javadoc)
	 * @see poker.Player#swapOneCard()
	 */
	public ArrayList<Integer> swapOneCard(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(swapCard());
		return result;
	}
	
	public ArrayList<Integer> swapTwoCards(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(swapCard());
		result.add(swapCard());
		return result;
	}
	
	public ArrayList<Integer> swapThreeCards(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(swapCard());
		result.add(swapCard());
		result.add(swapCard());
		return result;
	}
	
	public void displayCards(){
		for(Card card: hand){
			System.out.println(card.toString());
		}
	}

}
