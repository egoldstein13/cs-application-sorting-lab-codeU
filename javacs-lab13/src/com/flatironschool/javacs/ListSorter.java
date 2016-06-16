/**
 * 
 */
package com.flatironschool.javacs;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
		for(T element:list)
			System.out.print(element+" ");
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        // filled in
		if(list.size()<=1)
	       		return list;
	    	List<T> list1=mergeSort(list.subList(0,list.size()/2),comparator);
	    	List<T> list2=mergeSort(list.subList(list.size()/2, list.size()),comparator);
	    	insertionSort(list1, comparator);
	   	insertionSort(list2, comparator);
	   	List<T> list3=new ArrayList<T>();
	   	merge(list1, list2, list3, comparator);
	    	return list3;
	}
	public void merge(List<T> list1, List<T> list2, List<T> list3, Comparator<T> comparator) {
		int temp1=0,temp2=0;
		while(temp1<list1.size() && temp2<list2.size()) {
			if( comparator.compare(list1.get(temp1),list2.get(temp2))<0) {
				list3.add(list1.get(temp1++));
			}
			else 
				list3.add(list2.get(temp2++));
		}
		while(temp1<list1.size())
			list3.add(list1.get(temp1++));
		while(temp2<list2.size())
			list3.add(list2.get(temp2++));
	}
	
	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
        // filled in
		PriorityQueue<T> pq=new PriorityQueue<T>(list.size(),comparator);
		for(T el : list)
			pq.offer(el);
		list.clear();
		T el=pq.poll();
		while(el!=null){
			list.add(el);
			el=pq.poll();
		}
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
        // filled in
        	PriorityQueue<T> pq = new PriorityQueue<T>(k,comparator);
		for(T el : list){
			if(pq.size()<k)
				pq.offer(el);
			else if(pq.size()==k && comparator.compare(el,pq.peek())>0){
				pq.poll();
				pq.offer(el);
			}
		}
		list.clear();
		T el=pq.poll();
                while(el!=null){
                        list.add(el);
                        el=pq.poll();
                }
		return list;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
