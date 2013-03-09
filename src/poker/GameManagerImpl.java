package poker;

public class GameManagerImpl {
	
	private PokerList<Player> players;	//Consider CircularList ?
	private Deck deck;
	private final GameType type;
	private final Checker checker;
	
	public GameManagerImpl(GameType type) {
		this.type = type;
		checker = retrieveChecker(type);
	}
	
	public Checker getChecker() {
		return checker;
	}
	
	public void deal(){
		for(Player player: players){
			
		}
	}
	
	private Checker retrieveChecker(GameType type) {
		return CheckerFactory.getInstance().getChecker(type);
	}
	
}
