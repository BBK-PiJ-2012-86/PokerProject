package poker.manager_player;

import static org.junit.Assert.*;

import java.util.Iterator;


import org.junit.Before;
import org.junit.Test;

/* TODO: note this warning - not the end of the world here, but still good to do nicely I think
 
 "SBSC: Method concatenates strings using + in a loop (SBSC_USE_STRINGBUFFER_CONCATENATION)
The method seems to be building a String using concatenation in a loop. In each iteration,
the String is converted to a StringBuffer/StringBuilder, appended to, and converted back to a
String. This can lead to a cost quadratic in the number of iterations, as the growing string
is recopied in each iteration.
Better performance can be obtained by using a StringBuffer explicitly.

For example:

  // This is bad
  String s = "";
  for (int i = 0; i < field.length; ++i) {
    s = s + field[i];
  }

  // This is better
  StringBuffer buf = new StringBuffer();
  for (int i = 0; i < field.length; ++i) {
    buf.append(field[i]);
  }
  String s = buf.toString();
 */



public class CircularArrayListTest {

	private CircularArrayList<Integer> classUnderTest;
	
	@Before
	public void setUp(){
		classUnderTest = new CircularArrayList<Integer>();
	}
	
	@Test
	public void testAddOnePlayer() {

		classUnderTest.add(1); 
		assertEquals(1, classUnderTest.getSize());
	}
	
	@Test
	public void testAddPlayersToIncreaseArraySize(){
		for(int i = 0; i < 11; i++){
			classUnderTest.add(i);
		}
		int expected = 11;
		assertEquals(expected, classUnderTest.getSize());
	}
	
	@Test
	public void testIteratorHasNext(){
		classUnderTest.add(1);
		classUnderTest.add(3);
		
		Iterator<Integer> itr = classUnderTest.iterator();
		assertTrue(itr.hasNext());
	}
	
	@Test
	public void testIteratorHasNext2(){
		classUnderTest.add(1);
		classUnderTest.add(3);
		classUnderTest.add(4);
		classUnderTest.add(5);
		
		Iterator<Integer> itr = classUnderTest.iterator();
		int n = 0;
		String str = "";
		while(itr.hasNext()){
			str += itr.next();
			n++;
		}
		assertEquals(4,n);
		assertEquals("3451", str);
	}
	
	@Test
	public void testDealerSwap(){
		classUnderTest.add(1);
		classUnderTest.add(2);
		classUnderTest.newDealer();
		assertEquals(1, classUnderTest.getDealer());
		Integer expected = 2;
		assertEquals(expected, classUnderTest.getTheDealer());
	}
	
	@Test
	public void testDealerSwapZeroAndBack(){
		for(int i = 0; i < 8; i++){
			classUnderTest.add(i);
		}
		for(int i = 0; i < 10; i++){
			classUnderTest.newDealer();
		}
		Integer expected = 2;
		assertEquals(expected, classUnderTest.getTheDealer());
	}
	
	@Test
	public void testIteratorhasNextDealerRoundArray(){
		for(int i = 0; i < 11; i++){
			classUnderTest.add(i);
		}
		for(int i = 0; i < 5; i++){
			classUnderTest.newDealer();
		}
		Iterator<Integer> itr = classUnderTest.iterator();
		String str = "";
		while(itr.hasNext()){
			str += itr.next();
		}
		assertEquals("678910012345", str);
	}
	
	@Test
	public void testRemoveItemAfterDealer(){
		
		for(int i = 0; i < 10; i++){
			classUnderTest.add(i);
		}
		
		Iterator<Integer> itr = classUnderTest.iterator();
		while(itr.hasNext()){
			Integer i = itr.next();
			if(i.equals(3)){
				classUnderTest.remove(i);
			}
		}
		String str = "";
		itr = classUnderTest.iterator();
		while(itr.hasNext()){
			str += itr.next().toString();
		}
		assertEquals("124567890", str);
	}
	
	@Test
	public void testRemoverItemBeforeDealer(){
		for(int i = 0; i < 10; i++){
			classUnderTest.add(i);
		}
		for(int i = 0; i < 5; i++){
			classUnderTest.newDealer();
		}
		
		Iterator<Integer> itr = classUnderTest.iterator();
		while(itr.hasNext()){
			Integer i = itr.next();
			if(i.equals(2)){
				classUnderTest.remove(i);
			}
		}
		itr = classUnderTest.iterator();
		String str = "";
		while(itr.hasNext()){
			str += itr.next();
		}
		assertEquals("678901345", str);
	}
	
	@Test
	public void testRemoveDealer(){
		for(int i = 0; i < 10; i++){
			classUnderTest.add(i);
		}
		assertTrue(classUnderTest.remove(0));
		String str = "";
		Iterator<Integer> itr = classUnderTest.iterator();
		while(itr.hasNext()){
			str += itr.next();
		}
		assertEquals("234567891", str);
	}
}


