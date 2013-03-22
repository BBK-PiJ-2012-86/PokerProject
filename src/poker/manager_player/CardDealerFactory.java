package poker.manager_player;



public class CardDealerFactory {

	private static final CardDealerFactory INSTANCE = new CardDealerFactory();

	/**
	 * @return a CardDealerFactory
	 */
	public static CardDealerFactory getInstance() {
		return INSTANCE;
	}
	
	private CardDealer mock = null;

	public CardDealer getCardDealer() {
		if(mock != null) {
			return mock;
		}
		else {
			return new CardDealer();
		}
	}
	public void setMockCardDealer(CardDealer mock) {
		this.mock = mock;
	}
	
	public void setNotMockChecker() {
		this.mock = null;
	}
}
