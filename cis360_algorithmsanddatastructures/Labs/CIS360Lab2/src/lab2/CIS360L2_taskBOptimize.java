package lab2;

public class CIS360L2_taskBOptimize {
	public static int addThem(int n, int[] A) {
		int i,j,k;
		j = 0;
		k=1;
		
		for(i = 0; i<n; i++) {
			j = j + A[i];
			k = 2*k;
		}
		return j + k;
	}

	public static void main(String[] args) {
		int n = 5;
		int[] A1 = {2,5,3,7,8};
		System.out.println(addThem(n, A1));

	}

}
