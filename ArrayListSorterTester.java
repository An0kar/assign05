package assign05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @author DeAngelo Phlippeau & Terence Hirsch
 * Tests the ArrayListSorter Class
 */

public class ArrayListSorterTester {
	
	ArrayList<Integer> intArray;
	ArrayList<Integer> intArraySorted;
	
	ArrayList<Integer> ascendingHundred;
	ArrayList<Integer> descendingHundred;
	ArrayList<Integer> permutedHundred;
	ArrayList<Integer> permutedHundredAndTen52s;
	ArrayList<Integer> permutedHundredAndTen52sAnswer;
	ArrayList<Integer> empty = new ArrayList<Integer>();
	ArrayList<Integer> empty2 = new ArrayList<Integer>();
	ArrayList<String> emptyString = new ArrayList<String>();
	ArrayList<String> emptyString2 = new ArrayList<String>();
	ArrayList<Integer> thousandFives = new ArrayList<Integer>();
	ArrayList<Integer> thousandFives2 = new ArrayList<Integer>();
	ArrayList<String> thousandFivesString = new ArrayList<String>();
	ArrayList<String> thousandFivesString2 = new ArrayList<String>();
	ArrayList<Integer> ascendingThousand;
	ArrayList<Integer> descendingThousand;
	ArrayList<Integer> permutedThousand;
	ArrayList<Integer> permutedThousandAndTen52s;
	ArrayList<Integer> permutedThousandAndTen52sAnswer;
	ArrayList<String> ascendingThousandString = new ArrayList<String>();
	ArrayList<String> descendingThousandString = new ArrayList<String>();
	ArrayList<String> permutedThousandString = new ArrayList<String>();
	ArrayList<String> oneString = new ArrayList<String>();
	ArrayList<Integer> oneInt = new ArrayList<Integer>();
	
	@BeforeEach
	void setUp() throws Exception {
	
		ascendingHundred = ArrayListSorter.generateAscending(100);
		descendingHundred = ArrayListSorter.generateDescending(100);
		permutedHundred = ArrayListSorter.generatePermuted(100);
		permutedHundredAndTen52s = ArrayListSorter.generateAscending(110);
		for (int i = 100; i < 110; i++) {
			permutedHundredAndTen52s.set(i, 52);
		}
		for(int i = 0; i < 1000; i++) {
			thousandFives.add(5);
			thousandFives2.add(5);
			thousandFivesString.add(String.valueOf(5));
			thousandFivesString2.add(String.valueOf(5));
		}
		permutedHundredAndTen52sAnswer = ArrayListSorter.generateAscending(100);
		for (int i = 0; i < 10; i++) {
			permutedHundredAndTen52sAnswer.add(51, 52);
		}

		ascendingThousand = ArrayListSorter.generateAscending(1000);
		descendingThousand = ArrayListSorter.generateDescending(1000);
		permutedThousand = ArrayListSorter.generatePermuted(1000);
		permutedThousandAndTen52s = ArrayListSorter.generateAscending(1100);
		for (int i = 1000; i < 1100; i++) {
			permutedThousandAndTen52s.set(i, 52);
		}
		permutedThousandAndTen52sAnswer = ArrayListSorter.generateAscending(1000);
		for (int i = 0; i < 100; i++) {
			permutedThousandAndTen52sAnswer.add(51, 52);
		}
		for(int i = 0; i < 1000; i++) {
			ascendingThousandString.add(String.valueOf(i));
		}
		Collections.sort(ascendingThousandString);
		for(int i = 999; i >= 0; i--) {
			descendingThousandString.add(String.valueOf(i));
		}
		Collections.sort(descendingThousandString, (s1, s2) -> {return s2.compareTo(s1);});

		for(int i = 0; i < 1000; i++) {
			permutedThousandString.add(String.valueOf(i));
		}
		Collections.shuffle(permutedThousandString);
	
	intArray = ArrayListSorter.generatePermuted(10);
	intArraySorted = ArrayListSorter.generateAscending(10);
	
	oneString.add("Hello");
	oneInt.add(42);
	}
	//Test Generate methods
	
	@Test
	void generateAscendingTest()
	{
		ArrayList<Integer> list = ArrayListSorter.generateAscending(1000);
		for(int i = 0; i < 999; i++)
			if(list.get(i) >= list.get(i + 1))
				fail();
		assertTrue(true);
	}
	@Test
	void generateDescendingTest()
	{
		ArrayList<Integer> list = ArrayListSorter.generateDescending(1000);
		for(int i = 0; i < 999; i++)
			if(list.get(i) <= list.get(i + 1))
				fail();
		assertTrue(true);
	}
	@Test 
	void generatePermutedTest() {
		ArrayList<Integer> list = ArrayListSorter.generatePermuted(1000);
		for(int i = 1; i <= 1000; i++) {
			if(!list.contains(i))
				fail();
		}
		assertTrue(true);
	}
	//Test with one element
	@Test 
	void testMergeSortFives() {
		ArrayListSorter.mergesort(thousandFives);
		assertArrayEquals(thousandFives2.toArray(), thousandFives.toArray());
	}
	@Test 
	void testQuickSortFives() {
		ArrayListSorter.quicksort(thousandFives);
		assertArrayEquals(thousandFives2.toArray(), thousandFives.toArray());
	}
	@Test
	void testMergeSortFivesString() {
		ArrayListSorter.mergesort(thousandFivesString);
		assertArrayEquals(thousandFivesString2.toArray(), thousandFivesString.toArray());
	}
	@Test
	void testQuickSortFivesString() {
		ArrayListSorter.quicksort(thousandFivesString);
		assertArrayEquals(thousandFivesString2.toArray(), thousandFivesString.toArray());
	}
	@Test 
	void testMergeSortOne() {
		ArrayListSorter.mergesort(oneInt);
		assertEquals(42, oneInt.get(0));
	}
	@Test 
	void testQuickSortOne() {
		ArrayListSorter.quicksort(oneInt);
		assertEquals(42, oneInt.get(0));
	}
	@Test
	void testMergeSortOneString() {
		ArrayListSorter.mergesort(oneString);
		assertEquals("Hello", oneString.get(0));
	}
	@Test
	void testQuickSortOneString() {
		ArrayListSorter.quicksort(oneString);
		assertEquals("Hello", oneString.get(0));
	}
	//Test Empty Arrays
	@Test
	void testMergeSortEmpty() {
		ArrayListSorter.mergesort(empty);
		assertArrayEquals(empty2.toArray(), empty.toArray());
	}
	@Test
	void testQuickSortEmpty() {
		ArrayListSorter.quicksort(empty);
		assertArrayEquals(empty2.toArray(), empty.toArray());
	}
	@Test
	void testMergeSortEmptyString() {
		ArrayListSorter.mergesort(emptyString);
		assertArrayEquals(emptyString2.toArray(), emptyString.toArray());
	}
	@Test
	void testQuickSortStringEmpty() {
		ArrayListSorter.quicksort(emptyString);
		assertArrayEquals(emptyString2.toArray(), emptyString.toArray());
	}
	//Test with ints
	@Test
	void mergeSortTestDecending() {
		ArrayListSorter.mergesort(descendingHundred);
		assertArrayEquals(ascendingHundred.toArray(), descendingHundred.toArray());
		
		ArrayListSorter.mergesort(descendingThousand);
		assertArrayEquals(ascendingThousand.toArray(), descendingThousand.toArray());	
	}
	@Test
	void quickSortTestDecending() {
		ArrayListSorter.quicksort(descendingHundred);
		assertArrayEquals(ascendingHundred.toArray(), descendingHundred.toArray());
		
		ArrayListSorter.quicksort(descendingThousand);
		assertArrayEquals(ascendingThousand.toArray(), descendingThousand.toArray());	
	}
	@Test
	void mergeSortTestPermuted() {
		ArrayListSorter.mergesort(permutedHundred);
		assertArrayEquals(ascendingHundred.toArray(), permutedHundred.toArray());
		
		ArrayListSorter.mergesort(permutedThousand);
		assertArrayEquals(ascendingThousand.toArray(), permutedThousand.toArray());	
	}
	@Test
	void quickSortTestPermuted() {
		ArrayListSorter.quicksort(permutedHundred);
		assertArrayEquals(ascendingHundred.toArray(), permutedHundred.toArray());
		
		ArrayListSorter.quicksort(permutedThousand);
		assertArrayEquals(ascendingThousand.toArray(), permutedThousand.toArray());	
	}
	@Test
	void mergeSortTestPermutedDup() {
		ArrayListSorter.mergesort(permutedHundredAndTen52s);
		assertArrayEquals(permutedHundredAndTen52sAnswer.toArray(), permutedHundredAndTen52s.toArray());
		
		ArrayListSorter.mergesort(permutedThousandAndTen52s);
		assertArrayEquals(permutedThousandAndTen52sAnswer.toArray(), permutedThousandAndTen52s.toArray());	
	}
	@Test
	void quickSortTestPermutedDup() {
		ArrayListSorter.quicksort(permutedHundredAndTen52s);
		assertArrayEquals(permutedHundredAndTen52sAnswer.toArray(), permutedHundredAndTen52s.toArray());
		
		ArrayListSorter.quicksort(permutedThousandAndTen52s);
		assertArrayEquals(permutedThousandAndTen52sAnswer.toArray(), permutedThousandAndTen52s.toArray());	
	}
	
	
	//Test with Strings
	void mergeSortTestDecendingString() {
		ArrayListSorter.mergesort(descendingThousandString);
		assertArrayEquals(ascendingThousandString.toArray(), descendingThousandString.toArray());	
	}
	@Test
	void quickSortTestDecendingString() {
		ArrayListSorter.quicksort(descendingThousandString);
		assertArrayEquals(ascendingThousandString.toArray(), descendingThousandString.toArray());	
	}
	
	void mergeSortTestPermutedString() {
		ArrayListSorter.mergesort(permutedThousandString);
		assertArrayEquals(ascendingThousandString.toArray(), permutedThousandString.toArray());	
	}
	@Test
	void quickSortTestPermutedString() {
		ArrayListSorter.quicksort(permutedThousandString);
		assertArrayEquals(ascendingThousandString.toArray(), permutedThousandString.toArray());	
	}
}