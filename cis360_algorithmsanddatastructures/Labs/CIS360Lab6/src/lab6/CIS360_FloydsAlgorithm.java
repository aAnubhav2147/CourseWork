package lab6;

import java.util.*;

public class CIS360_FloydsAlgorithm {
	private static final int INITIALIZE = 6; //the number of vertices to be set
	public static final int  INFINITY = 99999;
	
	//Attempt 2 - Implement by taking only the adjacency matrix as the main argument
	
	public static void floydsAlgo(int[][] weights,int[][]paths) {
		//Initialize the distance matrix assuming no intermediate vertices exist
		int[][] distances = new int[INITIALIZE][INITIALIZE];
		
		//Initialize the path matrix as a shallow copy of the adjacency matrix
		//int paths = weights[0][0];
		//int[][] paths = new int[INITIALIZE][INITIALIZE];
		
		int i,j,k;
		
		//Set the distance matrix same as the adjacency matrix assuming no intermediate vertices exist
		for(i = 0; i < INITIALIZE; i++) {
			for(j = 0; j < INITIALIZE; j++) {
				distances[i][j] = weights[i][j];
				paths[i][j] = 0;
			}
		}
		
		//This is to aid in the path construction once the intermediate nodes start getting added
		//The main utility of this function is when the getShortestPath function is called
		for(i = 0; i < INITIALIZE; i++) {
			for(j = 0; j < INITIALIZE; j++) {
				paths[i][j] = i;
				if(i != j && weights[i][j] == 0) {
					paths[i][j] = -Integer.MAX_VALUE;
					weights[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		//Start adding intermediate vertices as you proceed. The first for loop is the one which actually starts adding 
		//the intermediate vertices for implementing the Floyd's Algorithm
		for(k = 0; k < INITIALIZE; k++) {
			for(i = 0; i < INITIALIZE; i++) {
				for(j = 0; j< INITIALIZE; j++) {
					if(distances[i][k] + distances[k][j] < distances[i][j]) {
						//paths[i][j] = k;
						paths[i][j] = paths[k][j];
						distances[i][j] = distances[i][k] + distances[k][j];
					}
				}
			}
		}
		
		System.out.println("The below is the Distance matrix with the distances b/w all nodes. \n");
		printMatrix(distances);
		System.out.println();
		System.out.println("The below is the matrix showing the intermediate vertices which can help to get the shortes paths. \n");
		printMatrix(paths);
		//getPath(i,j);
}
	
	/* Attempt 1 - Strictly adhere to the book algorithm verbatim
	public static void floydsAlgo(int numberOfVertices, int[][]weights, int[][]distances, int[][]paths) {
		int i,j,k;
		
		for(i = 1; i <= numberOfVertices; i++) {
			for(j = 1; j <= numberOfVertices; j++) {
				paths[i][j] = 0;
			}
			distances = weights;
			for(k = 1; k <= numberOfVertices; k++) {
				for(i = 1; i <= numberOfVertices; i++) {
					for(j = 1; j <= numberOfVertices; j++) {
						if(distances[i][k] + distances[k][j] < distances[i][j]) {
							paths[i][j] = k;
							distances[i][j] = distances[i][k] + distances[k][j];
						}
					}
				}
				
			for(i = 1; i <= numberOfVertices; i++) {
				System.out.print("\t" + i);
			}
			System.out.println();
			
			for(i = 1; i <= numberOfVertices; i++) {
				System.out.print(i + "\t");
				for(j = 1; j <= numberOfVertices; j++) {
					System.out.print(distances[i][j] + "\t");
				}
				System.out.println();
			}
			
			}
		}
		
		//printPath(distances);
	} */
	
	public static void printMatrix(int[][] tempArr) {
		int n = INFINITY;
		for(int q = 1; q < INITIALIZE; q++) {
			for(int r = 1; r < INITIALIZE ; r++) {
				//matrix[q][r] = input.nextInt();
				if(tempArr[q][r] == n) {
					System.out.print("INF"); //Redundant line. Doesn't work. Ignore.
				}
				else {
					System.out.print(Arrays.deepToString(tempArr)); //Prints out a 2-D matrix
				} 
				System.out.println();
			}
		}
	}
	
	/*
	public static int getPath(int i, int j) {
		int[][] tempArr = new int[i][j];
		
		if(tempArr[i][j] == i || tempArr[i][j] == j) {
			return i;
		}
		else {
			return getPath(i, tempArr[i][j]) + getPath(tempArr[i][j],j); 
		}
	}
*/
	//Use this function to create the shortest paths between any two nodes
	public static int getShortestPath(int[][]paths, int i, int j) {
		//paths = new int[INITIALIZE][INITIALIZE];
		//System.out.println(i);
		//System.out.println(j);
		printMatrix(paths);
		if(i == j) {
			return i;
		}
		
		else if(paths[i][j] == -Integer.MAX_VALUE) {
			System.out.println(i + "-" + j);
			return 0;	
		}
		
		else {
			getShortestPath(paths,i,paths[i][j]);
			System.out.println(j);
			return j;
			
		}
	}
	public static void main(String[] args) {
		//Driver code for Floyd's Algorithm check
		
		int n = INFINITY;
		
		System.out.println("Enter the Adjacency Matrix: \n");
		
		//Manually enter the required adjacency matrix for the desired graph
		
		/*
		//Adjacency matrix for Q5 & Q6 - Section 3.2 (Neapolitan)
		int[][] matrix = {{0,4,n,n,n,10,n},
				          {3,0,n,18,n,n,n},
				          {n,6,0,n,n,n,n},
				          {n,5,15,0,2,19,5},
				          {n,n,12,1,0,n,n},
				          {n,n,n,n,n,0,10},
				          {n,n,n,8,n,n,0}}; */
		
		//Graph A - Directed
		int[][] matrix = {{0,5,n,11,n,n},
				          {7,0,1,n,n,n},
				          {n,n,0,n,1,n},
				          {n,n,n,0,n,20},
				          {n,1,3,1,0,6},
				          {n,n,n,n,n,0}};
		
		//Graph A - Undirected
		int[][] matrixB = {{0,5,n,1,3,n},
						   {5,0,11,5,7,n},
						   {n,11,0,n,4,13},
						   {1,5,n,0,n,4},
						   {3,7,4,n,0,4},
						   {n,n,13,4,4,0}};
		
		int[][]paths = new int[INITIALIZE][INITIALIZE];
		
		
		//Scanner input = new Scanner(System.in);
		
		//System.out.println("Enter the number of vertices: ");
		//n = input.nextInt();
		
		//matrix = new int[INITIALIZE][INITIALIZE];
		System.out.println(Arrays.deepToString(matrix) + "\n"); //Prints out a 2-D matrix
		System.out.println();
		
		
		
		//System.out.print(Arrays.toString(matrix));
		
		//floydsAlgo(matrix,paths); // For directed graph
		floydsAlgo(matrixB,paths);  // For undirected graph
		
		System.out.println("The shortest path from i to j goes through the following intermediate nodes:  \n");
		getShortestPath(paths,5,0);
		
		//getPath gp = new getPath(n);
		
		

	}

}
