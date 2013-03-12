/**
 * 
 */
package poker.unused;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import poker.unused.CircularLinkedListImpl;


/**
 * @author 86
 *
 */
public class CircularLinkedListTest {
	
	CircularLinkedList<Integer> list;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new CircularLinkedListImpl<Integer>();
	}

	@Test
	public void testAdd() {
		list.add(7);
		list.add(3);
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(list.isEmpty());
		
		list.add(7);
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void testIterator() {
		list.add(7);
		list.add(3);
		list.add(9);
		assertEquals("7,3,9,",list.toString());
	}
	
	@Test
	public void testRemove() {
		assertFalse(list.remove(7));
		
		list.add(7);
		assertTrue(list.remove(7));
		assertTrue(list.isEmpty());
		
		list.add(3);
		list.add(9);
		list.add(2);
		assertTrue(list.remove(9));
		list.add(6);
		assertTrue(list.remove(6));
		
		assertEquals("3,2,",list.toString());
	}
	
	@Test
	public void testMoveHead() {
		list.moveHead(1);
		
		list.add(3);
		list.add(9);
		list.add(2);
		list.moveHead(1);
		assertEquals("9,2,3,",list.toString());
		
		list.add(4);
		list.moveHead(2);
		assertEquals("3,4,9,2,",list.toString());
		
		list.moveHead(4);
		assertEquals("3,4,9,2,",list.toString());
	}
	
}
