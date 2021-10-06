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
	intArray = ArrayListSorter.generatePermuted(10000);
	intArraySorted = ArrayListSorter.generateAscending(10000);
	}
	
	@Test
	void mergeSortTest1() {
		ArrayListSorter.mergesort(intArray);
		assertArrayEquals(intArray.toArray(),intArraySorted.toArray());
	}
	
	@Test
	void quickSortTest1() {
		ArrayListSorter.quicksort(intArray);
		assertArrayEquals(intArray.toArray(),intArraySorted.toArray());
	}

}
