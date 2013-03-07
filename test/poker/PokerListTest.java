package poker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PokerListTest {

	private PokerList<Player> classUnderTest;
	private PlayerFactory playerFactory;
	
	
	@Before
	public void setUp(){
		classUnderTest = new CircularLinkedList();
		playerFactory = new PlayerFactoryImpl();
		Player humanPlayer = playerFactory.createHuman();
		classUnderTest.add(humanPlayer);
		Player computerPlayer = playerFactory.createComputer()
		classUnderTest.add(computerPlayer);
	}
	@Test
	public void test() {
		
	}

}
