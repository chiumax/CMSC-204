//package _solution;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class SortedDoubleLinkedList_GFA_Test {
	SortedDoubleLinkedList<String> sortedLinkedString;
	SortedDoubleLinkedList<Double> sortedLinkedDouble;
	SortedDoubleLinkedList<Huh> sortedLinkedHuh;
	StringComparator comparator;
	DoubleComparator comparatorD;
	HuhComparator comparatorHuh;
	
	public Huh a=new Huh("Ford", "F150", 2005);
	public Huh b=new Huh("Jeep", "Renegade", 2005);
	public Huh c=new Huh("Honda", "Civic", 2005);
	public Huh d=new Huh("Subaru", "Outback", 2005);
	public Huh e=new Huh("Chevrolet", "Silverado", 2005);
	public Huh f=new Huh("Chrysler", "PTCruiser", 2005);
	//alphabetic order: e f a c b d
	
	@Before
	public void setUp() throws Exception {
		comparator = new StringComparator();
		sortedLinkedString = new SortedDoubleLinkedList<String>(comparator);
		
		comparatorD = new DoubleComparator();
		sortedLinkedDouble = new SortedDoubleLinkedList<Double>(comparatorD);
		
		comparatorHuh = new HuhComparator();
		sortedLinkedHuh = new SortedDoubleLinkedList<Huh>(comparatorHuh);
		
	}

	@After
	public void tearDown() throws Exception {
		comparator = null;
		comparatorD = null;
		comparatorHuh = null;
		sortedLinkedString = null;
		sortedLinkedDouble = null;
		sortedLinkedHuh = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedString.addToEnd("Hello");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			sortedLinkedString.addToFront("Hello");
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testIteratorSuccessfulNext() {
		sortedLinkedHuh.add(a);
		sortedLinkedHuh.add(b);
		sortedLinkedHuh.add(c);
		sortedLinkedHuh.add(d);
		ListIterator<Huh> iterator = sortedLinkedHuh.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(a, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(true, iterator.hasNext());
	}

	@Test
	public void testIteratorSuccessfulStringPrevious() {
		sortedLinkedString.add("Begin");
		sortedLinkedString.add("World");
		sortedLinkedString.add("Hello");
		sortedLinkedString.add("Zebra");
		ListIterator<String> iterator = sortedLinkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals("Zebra", iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals("Zebra", iterator.previous());
		assertEquals("World", iterator.previous());
		assertEquals("Hello", iterator.previous());
	}
	@Test
	public void testIteratorSuccessfulHuhPrevious() {
		sortedLinkedHuh.add(e);
		sortedLinkedHuh.add(c);
		sortedLinkedHuh.add(b);
		sortedLinkedHuh.add(d);
		//ArrayList<Huh> HuhList = sortedLinkedHuh.toArrayList();
		//alphabetic order: e f a c b d
		ListIterator<Huh> iterator = sortedLinkedHuh.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(e, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(d, iterator.previous());
		assertEquals(b, iterator.previous());
		assertEquals(c, iterator.previous());
	}
	@Test
	public void testIteratorSuccessfulDoubleNext() {
		sortedLinkedDouble.add(new Double(8));
		sortedLinkedDouble.add(new Double(6));
		sortedLinkedDouble.add(new Double(1));
		sortedLinkedDouble.add(new Double(2));
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(new Double(1), iterator.next());
		assertEquals(new Double(2), iterator.next());
		assertEquals(new Double(6), iterator.next());
		assertEquals(true, iterator.hasNext());	}
	
	@Test
	public void testIteratorSuccessfulDoublePrevious() {
		sortedLinkedDouble.add(new Double(5));
		sortedLinkedDouble.add(new Double(10));
		sortedLinkedDouble.add(new Double(8));
		sortedLinkedDouble.add(new Double(2));
		ListIterator<Double> iterator = sortedLinkedDouble.iterator();
		assertEquals(new Double(2), iterator.next());
		assertEquals(new Double(5), iterator.next());
		assertEquals(new Double(8), iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(new Double(10), iterator.previous());
		assertEquals(new Double(8), iterator.previous());
		assertEquals(true, iterator.hasPrevious());
	}
	
	@Test
	public void testIteratorNoSuchElementException() {
		sortedLinkedHuh.add(e);
		sortedLinkedHuh.add(c);
		sortedLinkedHuh.add(b);
		sortedLinkedHuh.add(d);
		//ArrayList<Huh> HuhList = sortedLinkedHuh.toArrayList();
		//alphabetic order: e f a c b d
		ListIterator<Huh> iterator = sortedLinkedHuh.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(e, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(d, iterator.next());
		try{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	@Test
	public void testIteratorUnsupportedOperationExceptionString() {
		sortedLinkedHuh.add(e);
		sortedLinkedHuh.add(c);
		sortedLinkedHuh.add(b);
		sortedLinkedHuh.add(d);
		//ArrayList<Huh> HuhList = sortedLinkedHuh.toArrayList();
		//alphabetic order: e f a c b d
		ListIterator<Huh> iterator = sortedLinkedHuh.iterator();
		
		try{
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddHuh() {
		//alphabetic order: e f a c b d
		sortedLinkedHuh.add(a);
		sortedLinkedHuh.add(b);
		sortedLinkedHuh.add(c);
		assertEquals(a, sortedLinkedHuh.getFirst());
		assertEquals(b, sortedLinkedHuh.getLast());
		sortedLinkedHuh.add(d);
		sortedLinkedHuh.add(e);
		assertEquals(e, sortedLinkedHuh.getFirst());
		assertEquals(d, sortedLinkedHuh.getLast());
		//deletes Elephant from linked list
		assertEquals(d,sortedLinkedHuh.retrieveLastElement());
		assertEquals(b, sortedLinkedHuh.getLast());
	}

	@Test
	public void testRemoveFirstHuh() {
		//alphabetic order: e f a c b d
		sortedLinkedHuh.add(b);
		sortedLinkedHuh.add(c);
		assertEquals(c, sortedLinkedHuh.getFirst());
		assertEquals(b, sortedLinkedHuh.getLast());
		sortedLinkedHuh.add(a);
		assertEquals(a, sortedLinkedHuh.getFirst());
		// remove the first
		sortedLinkedHuh.remove(a, comparatorHuh);
		assertEquals(c, sortedLinkedHuh.getFirst());
	}
	
	@Test
	public void testRemoveEndHuh() {
		//alphabetic order: e f a c b d
		sortedLinkedHuh.add(b);
		sortedLinkedHuh.add(f);
		assertEquals(f, sortedLinkedHuh.getFirst());
		assertEquals(b, sortedLinkedHuh.getLast());
		sortedLinkedHuh.add(d);
		assertEquals(d, sortedLinkedHuh.getLast());
		//remove from the end of the list
		sortedLinkedHuh.remove(d, comparatorHuh);
		assertEquals(b, sortedLinkedHuh.getLast());
	}

	@Test
	public void testRemoveMiddleHuh() {
		//alphabetic order: e f a c b d
		sortedLinkedHuh.add(a);
		sortedLinkedHuh.add(b);
		assertEquals(a, sortedLinkedHuh.getFirst());
		assertEquals(b, sortedLinkedHuh.getLast());
		sortedLinkedHuh.add(f);
		assertEquals(f, sortedLinkedHuh.getFirst());
		assertEquals(b, sortedLinkedHuh.getLast());
		assertEquals(3,sortedLinkedHuh.getSize());
		//remove from middle of list
		System.out.println(sortedLinkedHuh.remove(a, comparatorHuh));
		assertEquals(f, sortedLinkedHuh.getFirst());
		assertEquals(b, sortedLinkedHuh.getLast());
		assertEquals(2,sortedLinkedHuh.getSize());
	}

	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class HuhComparator implements Comparator<Huh>
	{

		@Override
		public int compare(Huh arg0, Huh arg1) {
			// Just put Huhs in alphabetic order by make
			return arg0.getMake().compareTo(arg1.getMake());
		}		
	}
	
	private class Huh{
		String make;
		String model;
		int year;
		
		public Huh(String make, String model, int year){
			this.make = make;
			this.model = model;
			this.year = year;
		}
		
		public String getMake(){
			return make;
		}
		public String getModel(){
			return model;
		}
		public int getYear(){
			return year;
		}
		
		public String toString() {
			return (getMake()+" "+getModel()+" "+getYear());
		}
	}
}
