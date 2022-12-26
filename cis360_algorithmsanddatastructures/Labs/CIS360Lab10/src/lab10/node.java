package lab10;

public class node implements Comparable<node> {
	int level;
	int profit;
	int weight;
	int bound;

@Override	
public int compareTo(node n) {
		if(n.bound > bound) {
			return 1;
		}
		else if(n.bound == bound) {
			return 0;
		}
		else return -1;
	}

}
