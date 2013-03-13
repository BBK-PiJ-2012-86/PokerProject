package poker.hand_card;


public class CardFactory {	//not currently used
	
	private static final CardFactory instanceNormal = new CardFactory(false);
	private static final CardFactory instanceMock = new CardFactory(true);
	
	private boolean mock;
	
	private CardFactory(boolean mock) {
		this.mock = mock;
	}

	protected static CardFactory getInstance(boolean mock) {		//make package..?
		return (mock)? instanceMock: instanceNormal;
	}
	
	public static CardFactory getInstance() {
		return getInstance(false);
	}
	
	public Card getCard(Rank rank, Suit suit){
		if (mock) {
			//return a mock one
			return null;	//not done
		} else {
			return new Card (rank, suit);
		}
	}
	
}
