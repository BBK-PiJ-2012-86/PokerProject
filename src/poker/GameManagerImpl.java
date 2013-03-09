package poker;

public class GameManagerImpl {
	
	private PokerList<Player> players;	//Consider CircularList ?
	private Deck deck;
	private final GameType type;
	private final CheckerFactory checkerFactory;
	
	public GameManagerImpl(GameType type) {
		this.type = type;
		checkerFactory = getCheckerFactory(type);
	}
	
	public void deal(){
		for(Player player: players){
			
		}
	}
	
	private CheckerFactory getCheckerFactory(GameType type) {
		return CheckerFactory.getInstance(type);
	}
	
}
