package poker.manager_player;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;


import org.junit.Before;
import org.junit.Test;

import poker.manager_player.HumanPlayer;
import poker.manager_player.Player;

public class PokerListTest {

	private PokerList<Player> classUnderTest;
	
	@Before
	public void setUp(){
		classUnderTest = new PokerList<Player>();
	}
	
	@Test
	public void testAddOnePlayer() {

		int expected = 1;
		Player humanPlayer = mock(HumanPlayer.class);
		classUnderTest.add(humanPlayer);
		assertEquals(expected, classUnderTest.getSize());
	}
	
	@Test
	public void testAddPlayersToIncreaseArraySize(){
		for(int i = 0; i < 11; i++){
			Player humanPlayer = mock(HumanPlayer.class);
			classUnderTest.add(humanPlayer);
		}
		int expected = 11;
		assertEquals(expected, classUnderTest.getSize());
	}
	
	@Test
	public void testIteratorHasNext(){
		Player humanPlayer = mock(HumanPlayer.class);
		classUnderTest.add(humanPlayer);
		classUnderTest.add(humanPlayer);
		
		Iterator<Player> itr = classUnderTest.iterator();
		
		assertTrue(itr.hasNext());
	}
	
	@Test
	public void testDealerSwap(){
		Player humanPlayer = mock(HumanPlayer.class);
		classUnderTest.add(humanPlayer);
		classUnderTest.add(humanPlayer);
		classUnderTest.newDealer();
		int expected = 1;
		assertEquals(expected, classUnderTest.getDealer());
		
	}
	
	@Test
	public void testDealerSwapZeroAndBack(){
		Player humanPlayer = mock(HumanPlayer.class);
		for(int i = 0; i < 8; i++){
			classUnderTest.add(humanPlayer);
		}
		for(int i = 0; i < 10; i++){
			classUnderTest.newDealer();
		}
		int expected = 1;
		assertEquals(expected, classUnderTest.getDealer());
	}
	
	@Test
	public void testIteratorhasNextDealerRoundArray(){
		for(int i = 0; i < 11; i++){
			Player humanPlayer = mock(HumanPlayer.class);
			classUnderTest.add(humanPlayer);
		}
		for(int i = 0; i < 5; i++){
			classUnderTest.newDealer();
		}
		Iterator<Player> itr = classUnderTest.iterator();
		for(int i = 0; i < 10; i++){
			assertTrue(itr.hasNext());
		}
	}
	
	@Test
	public void testIteratorRemoveItemAfterDealer(){
		
		for(int i = 0; i < 10; i++){
			Player humanPlayer = mock(HumanPlayer.class);
			classUnderTest.add(humanPlayer);
		}
		
		Iterator<Player> itr = classUnderTest.iterator();
		
		for(int i = 0; i < 1; i++){
			itr.remove();
		}
		
		int expected = 9;
		assertEquals(expected, classUnderTest.getSize());
	}
	
	@Test
	public void testIteratorRemoverItemBeforeDealer(){
		for(int i = 0; i < 10; i++){
			Player humanPlayer = mock(HumanPlayer.class);
			classUnderTest.add(humanPlayer);
		}
		for(int i = 0; i < 5; i++){
			classUnderTest.newDealer();
		}
		
		Iterator<Player> itr = classUnderTest.iterator();
		
		for(int i = 0; i < 1; i++){
			itr.remove();
		}
		int expected = 9;
		assertEquals(expected, classUnderTest.getSize());
	}
	
	@Test
	public void testIteratorRemoveDealer(){
		for(int i = 0; i < 10; i++){
			Player humanPlayer = mock(HumanPlayer.class);
			classUnderTest.add(humanPlayer);
		}
		
		Iterator<Player> itr = classUnderTest.iterator();
		int counter = 0;
		while(itr.hasNext()){
			if(counter == 10){
				itr.remove();
			}
			counter++;
		}
		int expected = 9;
		assertEquals(expected, classUnderTest.getSize());
		
	}
}


