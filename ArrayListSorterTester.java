package assign05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayListSorterTester {
	ArrayList<Integer> intArray;
	ArrayList<Integer> intArraySorted;
	@BeforeEach
	void setUp() throws Exception {
	intArray = ArrayListSorter.generatePermuted(10);
	intArraySorted = ArrayListSorter.generateAscending(10);
	}
	
	@Test
	void mergeSortTest1() {
		ArrayListSorter.mergesort(intArray);
		assertArrayEquals(intArraySorted.toArray(), intArray.toArray());
	}
	
	@Test
	void quickSortTest1() {
		ArrayListSorter.quicksort(intArray);
		assertArrayEquals(intArraySorted.toArray(), intArray.toArray());
	}

}
