package poker;

import static org.junit.Assert.*;

import org.junit.Test;

public class RankTest {

	@Test
	public void test() {
		Rank rankA = Rank.Two;
		Rank rankB = Rank.Ace;
		Rank rankC = Rank.Jack;
		Rank rankD = Rank.Jack;
		
		assertTrue(rankA.compareTo(rankB)>0);	//NB orders by increasing, so want higher ranks valued lower
		assertTrue(rankB.compareTo(rankC)<0);
		assertTrue(rankC.compareTo(rankD)==0);
		
	}

}
