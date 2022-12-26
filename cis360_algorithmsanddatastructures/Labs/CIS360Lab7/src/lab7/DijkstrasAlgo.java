package lab7;

import java.util.*;

public class DijkstrasAlgo {
	
	public static final int INFINITY = 999;
	public static final int INITIALIZE = 15;
	public static final int PRACTICE_PROBLEM = 10;
	public static final int END = -1;
	
	//Just like Floyd's create a method to construct a path between nodes
	//This will also find the closest unvisited node required to construct the shortest path
	public static int getShortestDistances(int[] distance, boolean[] includeVertex) {
		int min = INFINITY;
		int minIndex = END;
		
		for(int i = 0; i < distance.length; i++) {
			if(distance[i] < min) {
				if(includeVertex[i] == false) {
					//visited[i] = minIndex;
					min = distance[i];
					minIndex = i;
				}
			}
		}
		return minIndex;
	}
	
	public static void dijkstra(int[][] adjacencyMatrix, int s) {
		int[] distances = new int[INITIALIZE];
		
		boolean[] includeVertex = new boolean[INITIALIZE];
		
		for(int i = 0; i < INITIALIZE; i++) {
			distances[i] = INFINITY;
			includeVertex[i] = false;
		}
		
		distances[s] = 0; //Distance from source vertex to itself will always be zero
		
		for(int i = 0; i < INITIALIZE-1; i++) {
			int d = getShortestDistances(distances,includeVertex);
			
			includeVertex[d] = true;
			
			for(int j = 0; j <INITIALIZE; j++) {
				if(distances[d] + adjacencyMatrix[d][j] < distances[j] && 
					distances[d] != INFINITY && !includeVertex[j] && 
					adjacencyMatrix[d][j] != 0) {
					distances[j] = distances[d] + adjacencyMatrix[d][j];
				}
			}
		}
		System.out.println("Vertex \t\t Distance from Source");
		for (int i = 0; i < INITIALIZE; i++) {
			System.out.println(i + " \t\t " + distances[i]);
		}  
		Arrays.toString(distances);
	}
	
	public static void dijkstra2(int[][] adjacencyMatrix, int s) {
		int[] distances = new int[PRACTICE_PROBLEM];
		
		boolean[] includeVertex = new boolean[PRACTICE_PROBLEM];
		
		for(int i = 0; i < PRACTICE_PROBLEM; i++) {
			distances[i] = INFINITY;
			includeVertex[i] = false;
		}
		
		distances[s] = 0; //Distance from source vertex to itself will always be zero
		
		for(int i = 0; i < PRACTICE_PROBLEM-1; i++) {
			int d = getShortestDistances(distances,includeVertex);
			
			includeVertex[d] = true;
			
			for(int j = 0; j < PRACTICE_PROBLEM; j++) {
				if(distances[d] + adjacencyMatrix[d][j] < distances[j] && 
					distances[d] != INFINITY && !includeVertex[j] && 
					adjacencyMatrix[d][j] != 0) {
					distances[j] = distances[d] + adjacencyMatrix[d][j];
				}
			}
		}
		System.out.println("Vertex \t\t Distance from Source");
		for (int i = 0; i < PRACTICE_PROBLEM; i++) {
			System.out.println(i + " \t\t " + distances[i]);
		}    
		Arrays.toString(distances);
	}

	public static void main(String[] args) {
		
		int n = INFINITY;
		
		int[][] graph1 = new int[][] {{0,12, n, n, n, n, n, n, n, 25, n, 95, n, n, n},
		                               {n,0, 16, n, n, 60, n, n, n, n, n, 66, n, n, n},
		                               {91,n,0, 86, n, n, n, 46, n, 30, n, n, 58, n, n},
		                               {n,n,87,0, 2, 69, 31, n, n, n, n, n, n, n, n},
		                               {n,n,n,n,0, 11, n, n, n, 48, n, n, n, n, n},
		                               {n, n, 49, n,21,0, 6, 85, n, n, n, n, n, n, 92},
		                               {n, n, n, n, n, n, 0, 85, n, n, 8, n, n, n, n},
		                               {n, n, n, 84, n, n, n, 0, 31, n, n, n, n, 48, n},
		                               {n, n, n, n, n, n, n, n, 0, 12, n, n, n, 33, n},
		                               {n, n, n, 12, n, n, n, n, n, 0, 16, 55, 44, n, n},
		                               {n, n, n, n, n, n, n, n, n, n, 0, 55, 60, n, n},
		                               {n, n, 13, n, n, n,n, 19, n, 72,n, 0, 8,n, 5},
		                               {n, n, n, n, 16, n, n, n, n, n, n, n, 0, 44, n},
		                               {n, n, n, n, n, n, n, n, n, n, n, n, n, 0, 1},
		                               {n, n, n, 11, n, n, n, n, n, n, n, n, n, n, 0}};
		
		int[][] graph2 = new int[][] {{0, n, 40, n, 77, 30, n, 73, n, n, n, 33, 13, 48, 25},
		                              {n, 0, 57, 35, 95, 65, 38, n, 19, n, n, 26, 5, n, 60},
		                              {n, 94, 0, 68, n, 18, n, n, 80, n, n, n, 28, n, n},
		                              {n, n, 16, 0, n, 64, 2, 95, n, n, 60, n, n, 53, n},
		                              {13, 14, n, n, 0, n, n, 19, n, n, 69, 63, 18, n, n},
		                              {34, n, 57, 25, n, 0, n, 91, 14, 99, n, 10, 22, 98, n},
		                              {n, n, 73, 29, n, 83, 0, 96, 43, 20, 83, n, 46, 91, n},
		                              {48, n, 4, 32, n, n, n, 0, n, 97, n, n, 96, n, 63},
		                              {61, n, n, 52, n, 81, 97, 39, 0, n, 28, 52, n, 84, n},
		                              {32, 80, 96, 26, 16, n, n, 20, 96, 0, n, 7, n, 93, n},
		                              {n, 76, n, 95, n, 71, 16, n, 57, n, 0, 16, 41, 6, n},
		                              {n, 28, n, n, 8, n, n, n, 9, n, n, 0, 16, 79, 42},
		                              {n, 3, n, 61, n, n, n, 91, n, n, 71, n, 0, 35, n},
		                              {31, 37, 62, 35, n, 31, n, 49, 45, n, n, 6, n, 0, 93},
		                              {n, 43, n, 98, 27, n, n, 64, 99, n, n, 19, 31, n, 0}};
		                              
		int[][] graph3 = new int[][] {{0,n,45,n,10,28,n,n,25,n},
			                          {n,0,32,n,17,n,n,n,n,n},
			                          {45,32,0,n,n,n,n,n,n,n},
			                          {n,n,n,0,18,n,5,n,n,n},
			                          {10,17,n,18,0,n,n,3,n,n},
			                          {28,n,n,n,n,0,n,n,n,6},
			                          {n,n,n,5,n,n,0,59,n,n},
			                          {n,n,n,n,3,n,59,0,4,n},
			                          {25,n,n,n,n,n,n,4,0,12},
			                          {n,n,n,n,n,6,n,n,12,0}};
	   
		 
		 
		
		 long runTime1;
		 long startTime1 = System.nanoTime();
		 dijkstra(graph1, 0);
		 long endTime1 = System.nanoTime();
		 runTime1 = endTime1 - startTime1;
		 
		 System.out.println("Total Runtime: " + runTime1 + "\n");
		 
		 long runTime2;
		 long startTime2 = System.nanoTime();
		 dijkstra(graph2, 0);
		 long endTime2 = System.nanoTime();
		 runTime2 = endTime2 - startTime2;
		 
		 System.out.println("Total Runtime: " + runTime2 + "\n");
		 
		 long runTime3;
		 long startTime3 = System.nanoTime();
		 dijkstra2(graph3, 4);
		 long endTime3 = System.nanoTime();
		 runTime3 = endTime3 - startTime3;
		 
		 System.out.println("Total Runtime: " + runTime3 + "\n");
		 
		 

	}

}
