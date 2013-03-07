package poker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PokerListTest {

	private PokerList<Player> classUnderTest;
	private PlayerFactory playerFactory;
	
	
	@Before
	public void setUp(){
		PokerList<Player> classUnderTest = new PokerList<Player>();
		playerFactory = new PlayerFactoryImpl();
		Player humanPlayer = playerFactory.createHumanPlayer("Ed");
		classUnderTest.add(humanPlayer);
		Player computerPlayer = playerFactory.createComputerPlayer()
		classUnderTest.add(computerPlayer);
	}
	@Test
	public void test() {
		
	}

}
