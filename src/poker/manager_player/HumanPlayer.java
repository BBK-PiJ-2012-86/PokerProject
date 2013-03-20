package poker.manager_player;

import java.util.List;

import poker.hand_card.Card;

public class HumanPlayer extends Player{
	private final HumanPlayerListener listener;

	public HumanPlayer(String username, HumanPlayerListener listener) {
		super(username);
		this.listener = listener;
	}

	@Override
	public void receiveCards(List<Card> cards) {
		super.receiveCards(cards);
		listener.onReceiveCards(hand);
	}

	@Override
	public int exchangeCards() {
		int maxCardsSwapped = gameType.maxCardsSwapped();
		
		int swap = listener.getCountOfCardsToSwap(maxCardsSwapped);
		if(swap > 0){
			List<Card> cards = listener.selectCardsToRemove(swap);
			removeCardsFromHand(cards);
		}
		return swap;
	}
	
}
