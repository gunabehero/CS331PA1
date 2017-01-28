// Ryan Gunawan
// CS331-01 Programming Assignment 1

import java.util.*;

public class PA1 {

	public static void main(String[] args) {
		Random generator = new Random();
		long initTime, phase1Time, phase2Time, phase3Time;
		
		// Establish the two sets of integers
		int[] set1 = new int[2000];
		int[] set2 = new int[2000];
		
		
		// Generation of random integer to fill the two sets
		for (int a = 0; a < set1.length; a++){
			set1[a] = generator.nextInt(100000);
		}
		for (int a = 0; a < set2.length; a++){
			set2[a] = generator.nextInt(100000);
		}
		
		// Sort integer sets
		int[] sorted1 = set1;
		Arrays.sort(sorted1);
		int[] sorted2 = set2;
		Arrays.sort(sorted2);
		
		System.out.println("Determine the intersection of two sets of integers.");
		/*
		// Output sets for debugging purposes
		System.out.println("\nSet 1: ");
		for (int ct = 0; ct < sorted1.length; ct++){
			System.out.print(sorted1[ct] + " ");
		}
		System.out.println("\nSet 2: ");
		for (int ct = 0; ct < sorted2.length; ct++){
			System.out.print(sorted2[ct] + " ");
		}
		*/
		// Record time to starting the determination of the intersection
		initTime = System.nanoTime();
		int[] sortedIntersection = intersect(sorted1,sorted2);
		// Subtract current time from initialized time to find the time it took
		//	  to find the intersection of the two sets
		phase1Time = System.nanoTime() - initTime;
		
		System.out.println("\nUsing Efficient Intersection: ");
		System.out.println("Time: " + phase1Time);
		for (int ct = 0; ct < sortedIntersection.length; ct++){
			System.out.print(sortedIntersection[ct] + " ");
		}
		
		// Record time to starting the determination of the intersection
		initTime = System.nanoTime();
		int[] hashIntersection = intersect2(set1,set2);
		// Subtract current time from initialized time to find the time it took
		//	  to find the intersection of the two sets
		phase2Time = System.nanoTime() - initTime;
		
		System.out.println("\n\nUsing HashSet Intersection: ");
		System.out.println("Time: " + phase2Time);
		for (int ct = 0; ct < hashIntersection.length; ct++){
			System.out.print(hashIntersection[ct] + " ");
		}
		
		// Record time to starting the determination of the intersection
		initTime = System.nanoTime();
		int[] treeIntersection = intersect3(set1,set2);
		// Subtract current time from initialized time to find the time it took
		//	  to find the intersection of the two sets
		phase3Time = System.nanoTime() - initTime;

		System.out.println("\n\nUsing TreeSet Intersection: ");
		System.out.println("Time: " + phase3Time);
		for (int ct = 0; ct < treeIntersection.length; ct++){
			System.out.print(treeIntersection[ct] + " ");
		}

	}
	
	public static int[] intersect(int[] a1, int[] a2){
		// Best-Case scenario: no possible intersections
		if (a1[0] > a2[a2.length-1] || a2[0] > a1[a1.length-1]){
			return null;
		}
		
		int i = 0, j = 0, k= 0; 
		int[] out;
		
		// Using worst-case: all integers match as output array
		if (a1.length > a2.length)
			out = new int[a2.length];
		else
			out = new int[a1.length];
		
		while(i < a1.length && j < a2.length){
			if (a1[i] > a2[j] ){
				j++;
			} else if(a1[i] < a2[j]) {
				i++;
			} else {
				out[k] = a1[i];
				i++;
				j++;
				k++;
			}		
		}

		return out;
	}
	
	public static int[] intersect2(int[] input1, int[] input2){
		// Implementation of HashSet to find intersection
		HashSet<Integer> s = new HashSet<Integer>();
		int[] out = new int[input1.length];
		int k = 0;
		for (int i = 0; i < input1.length-1; i++){
			s.add(input1[i]);
		}
		
		for(int j = 0; j < input2.length-1; j++){
			if ( s.contains(input2[j]) ){
				out[k] = input2[j];
				k++;
			}
			
		}
		return out;
		
	}
	
	public static int[] intersect3(int[] input1, int[] input2){
		// Implementation of TreeSet to find intersection 
		TreeSet<Integer> a = new TreeSet<Integer>();
		int[] out = new int[input1.length];
		int k = 0;
		for (int i = 0; i < input1.length-1; i++){
			a.add(input1[i]);
		}
		for (int j = 0; j < input2.length-1; j++){
			if ( a.contains(input2[j]) ){
				out[k] = input2[j];
				k++;
			}
		}
		return out;
	}

}
