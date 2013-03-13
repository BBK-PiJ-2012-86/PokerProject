package poker.hand_card;


public class DeckFactory {

	private static final int DEFAULT_SIZE = 1;
	private final int size;
	
	public DeckFactory(){
		size = DEFAULT_SIZE;
	}
	
	public DeckFactory(int size){
		this.size = size;
	}
	
	public Deck getDeck(){
		Deck deck = new DeckImpl(size);
		return deck;
	}
	
}
