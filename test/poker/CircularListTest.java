/**
 * 
 */
package poker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author 86
 *
 */
public class CircularListTest {
	
	CircularList<Integer> list;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new CircularList<Integer>();
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
		String str = "";
		for (Integer i : list) {
			str+=i;
		}
		assertEquals("739",str);
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
		
		String str = "";
		for (Integer i : list) {
			str+=i;
		}
		assertEquals("32",str);
	}
	
}
