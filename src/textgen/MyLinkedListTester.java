/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int n = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, n);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		// Note: Check contents and out of bounds
		
		// list1 tests
		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			list1.remove(3);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
				
		// shortList tests
		String a = shortList.remove(0);
		assertEquals("Remove: check b is correct ", "A", a);
		assertEquals("Remove: check element 0 is correct ", "B", shortList.get(0));
		assertEquals("Remove: check size is correct ", 1, shortList.size());
		
		try {
			shortList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			shortList.remove(3);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// emptyList tests
		try {
			emptyList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			emptyList.remove(1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// longerList tests
		int c = longerList.remove(5);
		assertEquals("Remove: check c is correct ", 5, c);
		assertEquals("Remove: check element 5 is correct ", (Integer) 6, longerList.get(5));
		assertEquals("Remove: check size is correct ", 9, longerList.size());
		
		try {
			longerList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			longerList.remove(11);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		
		shortList.add("hello");
		emptyList.add(0);
		longerList.add(12);
		list1.add(0);
		
		assertEquals("Add at shortList end: check last element is correct ", 
				"hello", shortList.get(shortList.size() - 1));
		assertEquals("Add at emptyList end: check last element is correct ", 
				(Integer) 0, emptyList.get(emptyList.size() - 1));
		assertEquals("Add at longerList end: check last element is correct ", 
				(Integer) 12, longerList.get(longerList.size() - 1));
		assertEquals("Add at list1 end: check last element is correct ", 
				(Integer) 0, list1.get(list1.size() - 1));
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Check size of shortList ", 2, shortList.size);
		assertEquals("Check size of emptyList ", 0, emptyList.size);
		assertEquals("Check size of longerList ", 10, longerList.size);
		assertEquals("Check size of list1 ", 3, list1.size);
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		
		// shortList tests
		shortList.add(1, "hello");
		assertEquals("Add: Check added element is correct ", "hello", shortList.get(1));
		assertEquals("Add: Check next element is correct ", "B", shortList.get(2));
		assertEquals("Add: Check list size ", 3, shortList.size());

		try {
			shortList.add(-1, "hello");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			shortList.add(4, "hello");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// emptyList tests
		try {
			emptyList.add(0, 0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			emptyList.add(-1, 0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			emptyList.add(1, 0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// longerList tests
		longerList.add(9, 12);
		assertEquals("Add: Check added element is correct ", (Integer) 12, longerList.get(9));
		assertEquals("Add: Check next element is correct ", (Integer) 9, longerList.get(10));
		assertEquals("Add: Check list size ", 11, longerList.size());
		
		try {
			longerList.add(-1, 12);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			longerList.add(11, 12);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// list1 tests
		list1.add(1, 0);
		assertEquals("Add: Check added element is correct ", (Integer) 0, list1.get(1));
		assertEquals("Add: Check next element is correct ", (Integer) 21, list1.get(2));
		assertEquals("Add: Check list size ", 4, list1.size());
		
		try {
			list1.add(-1, 0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			list1.add(4, 0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		shortList.set(1, "hello");
		assertEquals("Set: Check new element is correct ", "hello", shortList.get(1));
		assertEquals("Set: Check list size ", 2, shortList.size());
		
		try {
			shortList.set(-1, "hello");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		catch (NullPointerException e) {
			
		}
		
		try {
			shortList.set(3, "hello");
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		catch (NullPointerException e) {
			
		}
		
		try {
			emptyList.set(0, 0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		catch (NullPointerException e) {
			
		}
		
		longerList.set(0, 10);
		assertEquals("Set: Check new element is correct ", (Integer) 10, longerList.get(0));
		assertEquals("Set: Check list size ", 10, longerList.size());
		
		try {
			longerList.set(15, 12);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		catch (NullPointerException e) {
			
		}
		
		list1.set(0, 0);
		assertEquals("Set: Check new element is correct ", (Integer) 0, list1.get(0));
		assertEquals("Set: Check list size ", 3, list1.size());
		
		try {
			list1.set(3, 0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		catch (NullPointerException e) {
			
		}

	}
	
	
	// TODO: Optionally add more test methods.
	
}
