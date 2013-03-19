package poker.hand_card;


/**
 * A DeckFactory enables production of decks
 *
 */
public class DeckFactory {

	private static DeckFactory INSTANCE = new DeckFactory();
	private static final int DEFAULT_SIZE = 1;
	private final int size;
	
	/**
	 * @return a DeckFactory to enable deck production
	 */
	public static DeckFactory getDeckFactory() {
		return INSTANCE;
	}
	
	private DeckFactory(){
		size = DEFAULT_SIZE;
	}
	
	private DeckFactory(int size){
		this.size = size;
	}
	
	/**
	 * @return a deck of cards
	 */
	public Deck getDeck(){
		Deck deck = new DeckImpl(size);
		return deck;
	}
	
}
