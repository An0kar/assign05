package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

	/**
	* @author DeAngelo Phlippeau & Terence Hirsch
 	* Sorts an array list using merge sort until the insertion threshold is met
 	* then the sort method is changed to insertion sort for the remaining array list
 	* 
 	* Sorts an array list using quick sort
 	* the pivot of the quick sort can being selected based on the given pivot strategy
 	* 
 	*/


public class ArrayListSorter {
	
	
	 /**
	  * minimum size the array list can be while using merge sort algorithm 
	  * before swapping to the insertion sort algorithm
	  * 
	  */
	
	protected static int INSERTION_THRESHOLD = 10;
	/**
	 * the given pivot selection strategy using the numerical values of 1-3
	 * 1- the pivot is selected based on a random index within the given array list using java.util.random
	 * 2- the pivot is selected based of the median of the front, back, and middle indexes of the array list
	 * 3- the pivot is a fixed index that will be the middle index of the array list.
	 * 
	 */
	protected static int PIVOT_STRATEGY = 1;
	/**
	 * This is the driver code for merge sort that pre-allocates a temporary list for merge space
	 * This driver code also calls the recursive method of mergesort with the given starting point
	 * of 0 the length of the array list as well as the pre-allocated temporary arraylist 
	 * 
	 * @param <T>
	 * @param list - ArrayList that will be sorted using the merge sort algorithm
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list) {
		// pre-allocated a temporary list for merge space
		ArrayList<T> tempList = new ArrayList<T>();
		for(int i = 0; i < list.size(); i++) {
			tempList.add(null);
		}
		mergesort(list, 0, list.size(), tempList);
	}
	/**
	 * This is the recursive method of merge sort that separates the given arraylist into to separate array lists
	 * This is done by taking the first half of the array given by the front index start point and the end index of (startPoint+endPoint)/2.
	 * The second half of the array list is given by the front index starting at (startPoint+endPoint)/2 and the end index of endPoint.
	 * These two haves and then recursively called until the Insertion Threshold is met where the given array segments will be sorted using
	 * the insertion sort algorithm.
	 * 
	 * @param <T>
	 * @param list - The given arraylist that will be sorted by using the merge sort algorithm.
	 * @param startPoint - The given int that tells mergesort where the lower bound of the array list is
	 * @param endPoint - The given int that tells mergesort where the upper bound of the array list is
	 * @param tempList - The previously allocated space required for merge sort
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list, int startPoint, int endPoint, ArrayList<T> tempList) {
		//calls insertionSort on the subarray when the size threshold is met
		if((endPoint - startPoint) <= INSERTION_THRESHOLD) {
			insertionSort(list, startPoint, endPoint);
			return;
		}
		//first half
		mergesort(list, startPoint, (startPoint+endPoint)/2, tempList);
		//second half
		mergesort(list, (startPoint+endPoint)/2, endPoint, tempList);
		merge(list, startPoint, endPoint, tempList);
	}
	/**
	 * This method merges the sorted subarrays by comparing the first and last index 
	 * of the subaray and combine them using the temporary space.
	 * 
	 * @param <T>
	 * @param list - The given array list that will be merged
	 * @param startPoint - first index of the subarray 
	 * @param endPoint - last array of the subarray
	 * @param tempList - temporary space that is used while merging the array
	 */
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> list, int startPoint, int endPoint, ArrayList<T> tempList){
		//startPoint is begining of "left list"
		//endpoint/2 is exclusive end of "left list"
		//endpoint/2 is beginning of "right list"
		//endpoint is exclusive end of "right list"
		
		//create variables
		int leftIndex = startPoint, rightIndex = (startPoint+endPoint)/2, tempIndex = startPoint;
		//loop until all elements in either right list or left list have been added
		while(leftIndex < (startPoint+endPoint)/2 && rightIndex < endPoint) {
			if(list.get(leftIndex).compareTo(list.get(rightIndex)) < 0) {
				tempList.set(tempIndex, list.get(leftIndex));
				leftIndex++;
			}
			else {
				tempList.set(tempIndex, list.get(rightIndex));
				rightIndex++;
			}
			tempIndex++;
		}
		//if left list was all added, add the rest of right list
		if(leftIndex == (startPoint+endPoint)/2) {
			for(int i = rightIndex; i < endPoint; i++, tempIndex++) {
				tempList.set(tempIndex, list.get(i));
			}
		}
		//if right list was all added, add the rest of left list
		else {
			for(int i = leftIndex; i < (startPoint+endPoint)/2; i++, tempIndex++) {
				tempList.set(tempIndex, list.get(i));
			}
		}
		//copy from temp to list.
		for(int i = startPoint; i < endPoint; i++) {
			list.set(i, tempList.get(i));
		}
	}
	/**
	 * This method is called from merge sort when the insertion sort threshold is met
	 * and sorts the given subarray list using the insertion sort algorithm
	 * 
	 * @param <T>
	 * @param list - the given subarray list that will be sorted using the insertion sort algorithm
	 * @param startPoint - the starting index 
	 * @param endPoint - the final index 
	 */
	
	private  static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> list, int startPoint, int endPoint) {
		for(int i = startPoint + 1; i < endPoint; i++) {
			T val = list.get(i);
			int j;
			for(j = i - 1; j >= startPoint && list.get(j).compareTo(val) > 0; j--)
				list.set(j + 1, list.get(j));
			list.set(j + 1, val);
		}
	}
	/**
	 * This is the driver code for quick sort by
	 * sending quick sort the array list, the starting index, and the final index given by the size of the array list
	 * 
	 * @param <T>
	 * @param list - the given array list that will be sent to the recursive quicksort method to be sorted
	 */
	
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list) {
		quicksort(list, 0, list.size());
	}
	/**
	 * This method recursively separates the array at the partition until the size of the subarray is 1 or less
	 * 
	 * @param <T>
	 * @param list - the given array list that will be separated and sorted using the quick sort algorithm
	 * @param startPoint - first index of the array list that will be separated at the partition
	 * @param endPoint - last index of the array list that will be separated at the partition
	 */
	private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list, int startPoint, int endPoint) {
	if(endPoint - startPoint <= 1) 
		return;
	
	int partition = partition(list, startPoint, endPoint);
	quicksort(list, startPoint, partition);
	quicksort(list, partition + 1, endPoint);
	}
/**
 * This method chooses a pivot and sorts the elements in list bases of if they are less or more when compared to the pivot
 * all elements will be  to the pivot until all element left of the pivot are less of the pivot 
 * and all elements to the right of the pivot are greater than the pivot.
 * 
 * @param <T>
 * @param list - the given sub array that will be sorted based on there value when compared to the pivot
 * @param startPoint - the first index of the subarray.
 * @param endPoint - the final index of the subarray.
 * @return int - representing the new middle of the array.
 */
	private static <T extends Comparable<? super T>> int partition(ArrayList<T> list, int startPoint, int endPoint){
		
		int pivot = choosePivot(list, startPoint, endPoint);
		T temp = list.get(endPoint-1);
		//move out of the way
		list.set(endPoint-1, list.get(pivot));
		list.set(pivot,  temp);
		//
		pivot = endPoint - 1;
		
		int leftIndex = startPoint;
		int rightIndex = endPoint -2;
		
		while (leftIndex <= rightIndex) {
			if(list.get(leftIndex).compareTo(list.get(pivot)) > 0 && list.get(rightIndex).compareTo(list.get(pivot)) <= 0) {
				temp = list.get(rightIndex);
				list.set(rightIndex, list.get(leftIndex));
				list.set(leftIndex,  temp);
				leftIndex++;
				rightIndex--;
			}
			else {
				if(list.get(leftIndex).compareTo(list.get(pivot)) > 0) {
					rightIndex--;
				}
				else if(list.get(rightIndex).compareTo(list.get(pivot)) <= 0) {
					leftIndex++;
				}
				else {
					leftIndex++;
					rightIndex--;
				}
			}
			
		}
		temp = list.get(leftIndex);
		list.set(leftIndex, list.get(pivot));
		list.set(pivot,  temp);
		
		return leftIndex;
	   
	}
	/**
	 * This method chooses a pivot based of the given pivot strategy.
	 * 
	 * case 1 - chooses a pivot based on a random number with in the range of the of start point and the end point 
	 * then increases that values to allow it to be with the index of the sub array.
	 * 
	 * case 2 - chooses a pivot based of the median number of the first, middle, and final index 
	 * based on comparing the elements located in those indexes.
	 * 
	 * case 3 - chooses a pivot by returning the middle index of the given subarray.
	 * 
	 * @param <T>
	 * @param list - the given subarray that the index of the will be within.
	 * @param startPoint - first index of the given subarray.
	 * @param endPoint - final index of the given subaray.
	 * @return - int that indexes the location of the pivot if the pivot straty is not between 1-3 returns -1
	 */

	private static <T extends Comparable<? super T>> int choosePivot(ArrayList<T> list, int startPoint, int endPoint) {
		
		switch(PIVOT_STRATEGY) {
		case 1:
			Random rand= new Random();
	        int pivot = rand.nextInt(endPoint-startPoint)+startPoint;
	        return pivot;
		case 2: 
			// Variable creation
			T front = list.get(startPoint);
			T middle = list.get((startPoint + endPoint) / 2);
			T back = list.get(endPoint - 2);
			// used to find the median of the elements at the 3 indexes.
			if((middle.compareTo(front) > 0 || middle.compareTo(back) > 0) && (middle.compareTo(front) <= 0 || middle.compareTo(back) <= 0)) {
				return (startPoint + endPoint) / 2;
			}
			else if ((back.compareTo(middle) > 0 || back.compareTo(front) > 0) && (middle.compareTo(front) <= 0 || back.compareTo(middle) <= 0)) {
				return endPoint - 2;
			}
			return startPoint;
		case 3: 
			return (startPoint + endPoint) / 2;
		}
		//will never reach this if pivot selection is between 1,2,3.
		return -1;
	}
	/**
	 * Created a ascending array list of ints of given size starting at 1 and ending at size
	 * 
	 * @param size - the given size of the array list 
	 * @return ArrayList: of given size in ascending order
	 */
	public static ArrayList<Integer> generateAscending(int size) {
		 ArrayList<Integer> intArray = new ArrayList();
		 for(int i=1; i <= size; i++)
			 intArray.add(i);
		return intArray;
	}
	/**
	 * Creates a ascending array list of int of given size and randomizes the order of the elements in array list
	 * 
	 * @param size - the given size of the array list 
	 * @return ArrayList: of given size in random order
	 */
	public static ArrayList<Integer> generatePermuted(int size){
		 ArrayList<Integer> intArray = new ArrayList();
		 for(int i=1; i <= size; i++)
			 intArray.add(i);
		 Collections.shuffle(intArray);
		return intArray;
	}
	/**
	 * Creates a descending array list of ints of given size starting a size ending at one 
	 * 
	 * @param size - the given size of the array list 
	 * @return ArrayList: of given size in descending order
	 */
	public static ArrayList<Integer> generateDescending(int size) {
		 ArrayList<Integer> intArray = new ArrayList();
		 for(int i=size; i >=1 ; i--)
			 intArray.add(i);
		return intArray;	
	}
}