package poker.manager_player;

import java.util.ArrayList;
import java.util.List;

import poker.hand_card.Card;

public class HumanPlayer extends Player{
	private final HumanPlayerListener listener;

	public HumanPlayer(String username/*, GameType gameType*/, HumanPlayerListener listener) {
		super(username/*, gameType*/);
		this.listener = listener;
	}

	@Override
	public void receiveCards(List<Card> cards) {
		hand.addCards(cards);
		listener.onReceiveCards(hand);
	}

	@Override
	public int exchangeCards() {
		int maxCardsSwapped = gameType.maxCardsSwapped();
		
		int swap = listener.getCountOfCardsToSwap(maxCardsSwapped);
		if(swap > 0){
			List<Card> cards = new ArrayList<Card>();
			for(int i = 0; i < swap; i++){
				cards.add(listener.selectCardsToRemove());	//needs to check it is a different card  ??
			}
			removeCardsFromHand(cards);
		}
		return swap;
	}
	
}
