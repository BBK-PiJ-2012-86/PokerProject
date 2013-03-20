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
	public synchronized void receiveCards(List<Card> cards) {
		hand.addCards(cards);
		listener.onReceiveCards(hand);
	}

	@Override
	public synchronized int exchangeCards() {
		int maxCardsSwapped = gameType.maxCardsSwapped();
		
		int swap = listener.getCountOfCardsToSwap(maxCardsSwapped);
		if(swap > maxCardsSwapped){
			throw new IllegalArgumentException("Maximum number of cards is " + maxCardsSwapped);	//change to just loop and ask again?
		}
		if(swap > 0){
			List<Card> cards = new ArrayList<Card>();
			for(int i = 0; i < swap; i++){
				cards.add(listener.selectCardsToRemove());
			}
			removeCardsFromHand(cards);
		}
		return swap;
	}
	
}
