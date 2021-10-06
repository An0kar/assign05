package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class ArrayListSorter {
	
	 
	
	protected static int INSERTION_THRESHOLD = 10;
	
	protected static int PIVOT_STRATEGY = 1;
	
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list) {
		ArrayList<T> tempList = new ArrayList<T>();
		for(int i = 0; i < list.size(); i++) {
			tempList.add(null);
		}
		mergesort(list, 0, list.size(), tempList);
	}
	
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list, int startPoint, int endPoint, ArrayList<T> tempList) {
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
	
	private  static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> list, int startPoint, int endPoint) {
		for(int i = startPoint + 1; i < endPoint; i++) {
			T val = list.get(i);
			int j;
			for(j = i - 1; j >= startPoint && list.get(j).compareTo(val) > 0; j--)
				list.set(j + 1, list.get(j));
			list.set(j + 1, val);
		}
	}
	
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list) {
		quicksort(list, 0, list.size());
	}
	
	private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list, int startPoint, int endPoint) {
	if(endPoint - startPoint <= 1) 
		return;
	
	int partition = partition(list, startPoint, endPoint);
	quicksort(list, startPoint, partition);
	quicksort(list, partition + 1, endPoint);
	}

	
	
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

	private static <T extends Comparable<? super T>> int choosePivot(ArrayList<T> list, int startPoint, int endPoint) {
		
		switch(PIVOT_STRATEGY) {
		case 1:
			
		case 2: 
			T front = list.get(startPoint);
			T middle = list.get((startPoint + endPoint) / 2);
			T back = list.get(endPoint - 2);
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
		
		return -1;
		
	}
	
	public static ArrayList<Integer> generateAscending(int size) {
		 ArrayList<Integer> intArray = new ArrayList();
		 for(int i=1; i <= size; i++)
			 intArray.add(i);
		return intArray;
		
	}
	
	public static ArrayList<Integer> generatePermuted(int size){
		 ArrayList<Integer> intArray = new ArrayList();
		 for(int i=1; i <= size; i++)
			 intArray.add(i);
		 Collections.shuffle(intArray);
		return intArray;
	}
	
	public static ArrayList<Integer> generateDescending(int size) {
		 ArrayList<Integer> intArray = new ArrayList();
		 for(int i=size; i >=1 ; i--)
			 intArray.add(i);
		return intArray;
		
	}
	
}