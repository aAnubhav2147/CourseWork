package hw6;

import java.util.*;

public class DoctorHoliday {
static final int V = 13; 	
	
	private String[] Nodes;	

	public DoctorHoliday(String[] Nodes){
		this.Nodes=Nodes; //Constructor to initialize the nodes for varying arguments	
	}

	public boolean breadthFirstSearch(int residualGraph[][], int vS, int vT, int p[]) {
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; i++) {
			visited[i] = false;
		}
		
		// Create a queue, enqueue source vertex and mark
        // source vertex as visited

		LinkedList<Integer> queue = new LinkedList<Integer>();	
		queue.add(vS);	
		visited[vS] = true;	
		p[vS]=-1;	//mark source vertex as visited		
		
		//Conduct BFS to check which nodes have been visited 
		while (!queue.isEmpty()) {
			int u = queue.remove();		
			for (int v=0; v<V; v++) {	
				if (visited[v]==false && residualGraph[u][v] > 0) {	
					queue.add(v);
					p[v] = u;		
					visited[v] = true;
				}
			}
		}
		return visited[vT];	
	}

	public int maxFlow(int graph[][], int vS, int vT) {
		int maxFlow = 0; //Initially there is no flow
		int path[] = new int[V]; //Store the paths post conducting the BFS	
		int x = 0;	
		int y = 0;

		int residualGraph[][] = new int[V][V]; //Creates the residual graph
		for (x = 0; x < V; x++){		
			for (y = 0; y < V; y++){
				residualGraph[x][y] = graph[x][y];
			}
		}

		while (breadthFirstSearch(residualGraph, vS, vT, path)) {		
			String pathString = "";	//Use this to print out the augmented path	

			int pathFlow = Integer.MAX_VALUE;		
			for (y=vT; y != vS; y=path[y]) {		
				x = path[y];		
				pathFlow = Math.min(pathFlow, residualGraph[x][y]);		

				pathString = " -> "+ Nodes[y] + pathString; //Augmenting Path
			}
			pathString= "S"+pathString;	//add S to show the flow out of source	
			System.out.println("Residual graph.. \n"+ pathString);
			System.out.println("Flow added = " + pathFlow + "\n");
			
			// update residual capacities of the edges and
            // reverse edges along the path

			for (y=vT; y != vS; y=path[y]) {	
				x = path[y];
				residualGraph[x][y] -= pathFlow;		
				residualGraph[y][x] += pathFlow;		
			}

			maxFlow += pathFlow;		
		}

		return maxFlow;
	}
	
public static void main(String[] args) {
		// Nodes and gMatrix are basis the question stem
			Random random = new Random();
			String[] Nodes = {"S","P1","P2","P3","D1","D2","D3","D4","H1","H2","H3","H4","T"};	
//			int gMatrix[][] =new int[][] {
//				{0,3,3,3,3,0,0,0,0,0,0,0,0}, 
//				{0,0,0,0,0,1,1,1,0,0,0,0,0},
//				{0,0,0,0,0,0,0,0,1,1,1,0,0},
//				{0,0,0,0,0,0,0,0,0,0,0,1,1},
//				{0,0,0,0,0,0,0,0,0,0,0,0,0},
//				{0,0,0,2,0,0,0,0,0,0,0,0,0},
//				{0,0,0,0,0,0,0,0,0,0,0,0,0},
//				{0,0,0,0,0,0,0,0,3,0,0,0,0},
//				{0,0,0,0,0,0,0,0,0,0,0,0,0},
//				{0,0,0,0,0,0,0,0,0,0,0,0,0},
//				{0,0,0,0,0,0,0,0,0,0,0,0,0},
//				{0,0,0,0,0,0,0,1,1,1,1,0,0}
//			};
//			
//			//fill the array with random numbers
//			for(int i = 0; i < V; i++) {
//						gMatrix[i][i] = random.nextInt(3); 
//						//the number in parenthesis basically generates a number between 0 to the numeral entered
//						//This increases the probability of finding the key value input
//			}
			
			int gMatrix[][] =new int[V][V];			
			//fill the array with random numbers
			for(int i = 0; i < V; i++) {
				for(int j = 0; j < V; j++) {
						gMatrix[i][j] = random.nextInt(10); 
		//the number in parenthesis basically generates a number between 0 to the numeral entered

			}
		}
		
			System.out.print(Arrays.deepToString(gMatrix)); //Print out the 2-D array
			
			
			DoctorHoliday mFlowFinder = new DoctorHoliday(Nodes);

			int vS = 0;
			int vT = V-1;
			System.out.println(" Max Flow = " + mFlowFinder.maxFlow(gMatrix, vS, vT));


	}

}
