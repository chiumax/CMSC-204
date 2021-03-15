//package _solution;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicDoubleLinkedList_GFA_Test {
	BasicDoubleLinkedList<String> linkedString;
	BasicDoubleLinkedList<Double> linkedDouble;
	BasicDoubleLinkedList<Huh> linkedHuh;
	StringComparator comparator;
	DoubleComparator comparatorD;
	HuhComparator comparatorHuh;

	public Huh a = new Huh("bruh", "F150", 2005);
	public Huh b = new Huh("yeet", "Renegade", 2005);
	public Huh c = new Huh("AA", "Civic", 2005);
	public Huh d = new Huh("PO", "Outback", 2005);
	public Huh e = new Huh("POsdfgsdfg", "Silverado", 2005);
	public Huh f = new Huh("123412341234", "PTCruiser", 2005);

	public ArrayList<Huh> fill = new ArrayList<Huh>();

	@Before
	public void setUp() throws Exception {
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd("Hello");
		linkedString.addToEnd("World");
		comparator = new StringComparator();

		linkedDouble = new BasicDoubleLinkedList<Double>();
		linkedDouble.addToEnd(15.0);
		linkedDouble.addToEnd(100.0);
		comparatorD = new DoubleComparator();

		linkedHuh = new BasicDoubleLinkedList<Huh>();
		linkedHuh.addToEnd(b);
		linkedHuh.addToEnd(c);
		comparatorHuh = new HuhComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		linkedDouble = null;
		linkedHuh = null;
		comparatorD = null;
		comparator = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(2, linkedString.getSize());
		assertEquals(2, linkedDouble.getSize());
		assertEquals(2, linkedHuh.getSize());
	}

	@Test
	public void testAddToEnd() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("End");
		assertEquals("End", linkedString.getLast());

		assertEquals(c, linkedHuh.getLast());
		linkedHuh.addToEnd(d);
		assertEquals(d, linkedHuh.getLast());
	}

	@Test
	public void testAddToFront() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("Begin");
		assertEquals("Begin", linkedString.getFirst());

		assertEquals(b, linkedHuh.getFirst());
		linkedHuh.addToFront(a);
		assertEquals(a, linkedHuh.getFirst());
	}

	@Test
	public void testGetFirst() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());

		assertEquals(b, linkedHuh.getFirst());
		linkedHuh.addToFront(a);
		assertEquals(a, linkedHuh.getFirst());
	}

	@Test
	public void testGetLast() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("New");
		assertEquals("New", linkedString.getLast());

		assertEquals(c, linkedHuh.getLast());
		linkedHuh.addToEnd(d);
		assertEquals(d, linkedHuh.getLast());
	}

	@Test
	public void testToArrayList() {
		ArrayList<Huh> list;
		linkedHuh.addToFront(a);
		linkedHuh.addToEnd(d);
		list = linkedHuh.toArrayList();
		assertEquals(a, list.get(0));
		assertEquals(b, list.get(1));
		assertEquals(c, list.get(2));
		assertEquals(d, list.get(3));
	}

	@Test
	public void testIteratorSuccessfulNext() {
		linkedString.addToFront("Begin");
		linkedString.addToEnd("End");
		ListIterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("End", iterator.next());

		linkedHuh.addToFront(a);
		linkedHuh.addToEnd(d);
		ListIterator<Huh> iteratorHuh = linkedHuh.iterator();
		assertEquals(true, iteratorHuh.hasNext());
		assertEquals(a, iteratorHuh.next());
		assertEquals(b, iteratorHuh.next());
		assertEquals(c, iteratorHuh.next());
		assertEquals(true, iteratorHuh.hasNext());
		assertEquals(d, iteratorHuh.next());
	}

	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedHuh.addToFront(a);
		linkedHuh.addToEnd(d);
		ListIterator<Huh> iteratorHuh = linkedHuh.iterator();
		assertEquals(true, iteratorHuh.hasNext());
		assertEquals(a, iteratorHuh.next());
		assertEquals(b, iteratorHuh.next());
		assertEquals(c, iteratorHuh.next());
		assertEquals(d, iteratorHuh.next());
		assertEquals(true, iteratorHuh.hasPrevious());
		assertEquals(d, iteratorHuh.previous());
		assertEquals(c, iteratorHuh.previous());
		assertEquals(b, iteratorHuh.previous());
		assertEquals(a, iteratorHuh.previous());
	}

	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedHuh.addToFront(a);
		linkedHuh.addToEnd(d);
		ListIterator<Huh> iteratorHuh = linkedHuh.iterator();
		assertEquals(true, iteratorHuh.hasNext());
		assertEquals(a, iteratorHuh.next());
		assertEquals(b, iteratorHuh.next());
		assertEquals(c, iteratorHuh.next());
		assertEquals(true, iteratorHuh.hasNext());
		assertEquals(d, iteratorHuh.next());

		try {
			// no more elements in list
			iteratorHuh.next();
			assertTrue("Did not throw a NoSuchElementException", false);
		} catch (NoSuchElementException e) {
			assertTrue("Successfully threw a NoSuchElementException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}

	}

	@Test
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedHuh.addToFront(a);
		linkedHuh.addToEnd(d);
		ListIterator<Huh> iteratorHuh = linkedHuh.iterator();
		assertEquals(true, iteratorHuh.hasNext());
		assertEquals(a, iteratorHuh.next());
		assertEquals(b, iteratorHuh.next());
		assertEquals(c, iteratorHuh.next());
		assertEquals(d, iteratorHuh.next());
		assertEquals(true, iteratorHuh.hasPrevious());
		assertEquals(d, iteratorHuh.previous());
		assertEquals(c, iteratorHuh.previous());
		assertEquals(b, iteratorHuh.previous());
		assertEquals(a, iteratorHuh.previous());

		try {
			// no more elements in list
			iteratorHuh.previous();
			assertTrue("Did not throw a NoSuchElementException", false);
		} catch (NoSuchElementException e) {
			assertTrue("Successfully threw a NoSuchElementException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}

	}

	@Test
	public void testIteratorUnsupportedOperationException() {
		linkedHuh.addToFront(a);
		linkedHuh.addToEnd(d);
		ListIterator<Huh> iteratorHuh = linkedHuh.iterator();
		assertEquals(true, iteratorHuh.hasNext());
		assertEquals(a, iteratorHuh.next());
		assertEquals(b, iteratorHuh.next());
		assertEquals(c, iteratorHuh.next());
		assertEquals(true, iteratorHuh.hasNext());
		assertEquals(d, iteratorHuh.next());

		try {
			// remove is not supported for the iterator
			iteratorHuh.remove();
			assertTrue("Did not throw a UnsupportedOperationException", false);
		} catch (UnsupportedOperationException e) {
			assertTrue("Successfully threw a UnsupportedOperationException", true);
		} catch (Exception e) {
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}

	}

	@Test
	public void testRemove() {
		// remove the first
		assertEquals(b, linkedHuh.getFirst());
		assertEquals(c, linkedHuh.getLast());
		linkedHuh.addToFront(a);
		assertEquals(a, linkedHuh.getFirst());
		linkedHuh.remove(a, comparatorHuh);
		assertEquals(b, linkedHuh.getFirst());
		// remove from the end of the list
		linkedHuh.addToEnd(d);
		assertEquals(d, linkedHuh.getLast());
		linkedHuh.remove(d, comparatorHuh);
		assertEquals(c, linkedHuh.getLast());
		// remove from middle of list
		linkedHuh.addToFront(a);
		assertEquals(a, linkedHuh.getFirst());
		assertEquals(c, linkedHuh.getLast());
		linkedHuh.remove(b, comparatorHuh);
		assertEquals(a, linkedHuh.getFirst());
		assertEquals(c, linkedHuh.getLast());

	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(b, linkedHuh.getFirst());
		linkedHuh.addToFront(a);
		assertEquals(a, linkedHuh.getFirst());
		assertEquals(a, linkedHuh.retrieveFirstElement());
		assertEquals(b, linkedHuh.getFirst());
		assertEquals(b, linkedHuh.retrieveFirstElement());
		assertEquals(c, linkedHuh.getFirst());

		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
		assertEquals("New", linkedString.retrieveFirstElement());
		assertEquals("Hello", linkedString.getFirst());
		assertEquals("Hello", linkedString.retrieveFirstElement());
		assertEquals("World", linkedString.getFirst());

	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(c, linkedHuh.getLast());
		linkedHuh.addToEnd(d);
		assertEquals(d, linkedHuh.getLast());
		assertEquals(d, linkedHuh.retrieveLastElement());
		assertEquals(c, linkedHuh.getLast());

		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("New");
		assertEquals("New", linkedString.getLast());
		assertEquals("New", linkedString.retrieveLastElement());
		assertEquals("World", linkedString.getLast());
	}

	private class StringComparator implements Comparator<String> {

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}

	}

	private class DoubleComparator implements Comparator<Double> {

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}

	}

	private class HuhComparator implements Comparator<Huh> {

		@Override
		public int compare(Huh arg0, Huh arg1) {
			// Just put Huhs in alphabetic order by make
			return arg0.toString().compareTo(arg1.toString());
		}

	}

	private class Huh {
		String make;
		String model;
		int year;

		public Huh(String make, String model, int year) {
			this.make = make;
			this.model = model;
			this.year = year;
		}

		public String getMake() {
			return make;
		}

		public String getModel() {
			return model;
		}

		public int getYear() {
			return year;
		}

		public String toString() {
			return (getMake() + " " + getModel() + " " + getYear());
		}
	}
}
