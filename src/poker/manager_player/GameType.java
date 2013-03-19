package poker.manager_player;

/**
 * The options for the types of poker games implemented
 *
 */
public enum GameType {
	FIVE_CARD_DRAW (5, 3);

    private final int cardsInHand;
    private final int maxCardsSwapped;
    
    GameType(int cardsInHand , int maxCardsSwapped) {
        this.cardsInHand = cardsInHand;
        this.maxCardsSwapped = maxCardsSwapped;
    }
    public int numCards() {
    	return cardsInHand;
    }
    public int maxCardsSwapped() {
    	return maxCardsSwapped;
    }
}
