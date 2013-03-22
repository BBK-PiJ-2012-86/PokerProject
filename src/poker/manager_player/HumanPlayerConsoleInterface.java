package poker.manager_player;


import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import poker.hand_card.Card;
import poker.hand_card.Hand;

/**
 * A HumanPayer plays poker and needs input to make its card replacement decisions
 *
 */
public class HumanPlayerConsoleInterface implements HumanPlayerListener {

	private Scanner scanner = new Scanner(System.in, "UTF-8");
	private Hand hand = null;

	public void onReceiveCards(Hand hand){
		this.hand = hand;
		System.out.println("Your hand is...");
		System.out.println(hand);
	}

	public int getCountOfCardsToSwap(int max) {
		System.out.print("How many cards would you like to swap (max " + max + "): ");
		return getIntInRange(0,max);
	}
	
	public List<Card> selectCardsToRemove(int numberOfCards) {
		List<Card> cardsToRemove = new LinkedList<Card>();
		boolean needCard;
		for (int i = 0; i<numberOfCards; i++) {
			needCard = true;
			while(needCard) {
				Card card = selectACard();
				if (!cardsToRemove.contains(card)) {
					needCard = false;
					cardsToRemove.add(card);
				} else {
					System.out.println("You already picked that one. Try again.");
				}
			}
		}
		return cardsToRemove;
	}

	private Card selectACard() {
		int numCards = hand.getCards().size();
		System.out.print("Which card would you like to swap (From 1 - "+numCards+")?: ");
		int cardNo =  getIntInRange(1,numCards);
		return hand.getCardAt(cardNo-1);
	}

	private int getIntInRange(int min, int max) {
		int swap = 0;

		boolean needValid = true;
		boolean needInt = true;

		while (needValid) {
			while (needInt) {
				String s = scanner.nextLine();
				try{
					swap = Integer.parseInt(s);
					needInt = false;
				}
				catch(NumberFormatException ex){
					System.out.println("Please enter a single numerical digit e.g. 1");
					System.out.print("Try again: ");
				}
			}
			if (swap>max|| swap<min) {
				System.out.println("That is not a valid selection. Possible range "+min+" to "+max);
				System.out.print("Try again: ");
				needInt = true;
			} else {
				needValid = false;
			}
		}
		return swap;
	}
}