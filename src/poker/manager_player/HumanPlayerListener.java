package poker.manager_player;

import poker.hand_card.Card;
import poker.hand_card.Hand;

public interface HumanPlayerListener {
	public void onReceiveCards(Hand hand);
	public int getCountOfCardsToSwap(int max);
	public Card selectCardsToRemove();
}
