package poker.hand_card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;


public class DeckImpl implements Deck {

	@Getter private final List<Card> cards;
	
	public DeckImpl(int size){
		cards = new ArrayList<Card>();
		for(int i = 0; i < size; i++){
			for(Suit suit: Suit.values()){
				for(Rank rank: Rank.values()){
					Card card = new Card(rank, suit);
					cards.add(card);
				}
				
			}
		}
	}
		
	@Override
	public void shuffleDeck(){
		Collections.shuffle(cards);
	}
	
}
