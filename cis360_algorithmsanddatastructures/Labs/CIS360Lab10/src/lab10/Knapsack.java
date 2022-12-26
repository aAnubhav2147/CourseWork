package lab10;

import java.util.*;

public class Knapsack {
	static int count = 0;
	
static void knapsack(int n, int[] p, int[] w, int W,int maxProfit) {
		PriorityQueue<node> pq = new PriorityQueue<node>();
		node u = new node();
		node v = new node();
		
		v.level = -1; //Set to -1 in order to compensate for the zero indexed
                      //pseudo-code translation
		v.profit = 0;
		v.weight = 0;
		maxProfit = 0;
		v.bound = boundCalculation(v,n,p,w,W);
		pq.add(v);
		
		do {
			v = pq.remove();
			
			if(v.bound>maxProfit) {
				//Left Node
				u = new node();
				u.level = v.level + 1;
				u.weight = v.weight + w[u.level];
				u.profit = v.profit + p[u.level];
				count++;
				if(u.weight <= W && u.profit > maxProfit) {
					maxProfit = u.profit;
				}
				u.bound = boundCalculation(u,n,p,w,W);
				if(u.bound > maxProfit) {
					pq.add(u);
					count++;
				}
				//Right node
				u = new node();
				u.level = v.level+1;
				u.weight = v.weight;
				u.profit = v.profit;
				u.bound = boundCalculation(u, n, p, w, W);
				if(u.bound > maxProfit) {
					pq.add(u);
					count++;
				}
				pq.remove(v);
			}
			System.out.println("maxprofit " + maxProfit);
		}while(!pq.isEmpty());
}
	
static int boundCalculation(node u,int n, int p[], int w[], int W){
		int i,j;
		int totalWeight;
		int result;
		
		if(u.weight >= W) {
			return 0;
		}
		else {
			result = u.profit;
			i = u.level + 1;
			totalWeight = u.weight;
			while(i<n && totalWeight + w[i] <= W){
				totalWeight = totalWeight + w[i];
				result = result + p[i];
				i++;
			}
			j = i;
			if(j < n) {
				result = result + (W-totalWeight)*(p[j]/w[j]);
			}
			return result;
		}
}
	
public static void main(String[] args) {
		//Driver method for the best-first 0-1 Knapsack problem
	
		int p[] = {20, 30, 35, 12, 3};
		int w[] = {2, 5, 7, 3, 1};
		int W = 13;
		int n = 5;
		int maxprofit = 0;
		count = 0;
		knapsack(n, p, w, W, maxprofit);
		System.out.println("node count " + count + "\n");
		
        int[] p2 = { 20, 30, 24, 40, 32, 12};
        int[] w2 = { 1, 2, 2, 8, 8, 4 };
        int W2 = 10;
        int n2 =6;
        
        knapsack(n2,p2,w2,W2, maxprofit);
        System.out.println("node count " + count + "\n");
        
        int p3[] = {40, 30, 65, 50};
        int w3[] = {2, 5, 13, 25};
        int W3 = 16;
        int n3 = 4;
        
        knapsack(n3, p3, w3, W3, maxprofit);
        System.out.println("node count " + count + "\n");

	}

}
