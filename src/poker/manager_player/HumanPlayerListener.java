package poker.manager_player;

import java.util.List;

import poker.hand_card.Card;
import poker.hand_card.Hand;

public interface HumanPlayerListener {
	public void onReceiveCards(Hand hand);
	public int getCountOfCardsToSwap(int max);
	public List<Card> selectCardsToRemove(int numberOfCards);
}
