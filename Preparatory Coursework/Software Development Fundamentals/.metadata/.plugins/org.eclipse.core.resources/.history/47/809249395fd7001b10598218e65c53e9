public class Squarelotron {
	
		//Instance variables
		int[][] squarelotron;
		int size;
		int startingValue = 1;
		
		//Constructor
		public Squarelotron(int n) {
			this.size = n;
			int value = startingValue;
			squarelotron = new int[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					this.squarelotron[i][j] = value;
					value++;
				}
			}
			
		}
		
		//Methods
		public boolean onRing(int i, int j, int ring) {
			boolean on = (i == ring-1 || j == ring-1 || i == size - ring || j == size - ring);
			boolean out = (i <= ring - 2 || i >= size + 1 -ring || j <= ring - 2 || j >= size + 1 - ring);
			return on && !out;
		}
		
		public Squarelotron upsideDownFlip(int ring) {
			Squarelotron newSquarelotron = new Squarelotron(size); //original Squarelotron shouldn't be modified
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					if(onRing(i,j,ring)) {
						//newSquarelotron.squarelotron[i][j] = this.squarelotron[j][i];
						newSquarelotron.squarelotron[i][j] = this.squarelotron[size - 1 - i][j];
					}
				}
			}
			return newSquarelotron;
		}
		
		public Squarelotron mainDiagonalFlip(int ring) {
			Squarelotron newSquarelotron = new Squarelotron(size); //original Squarelotron shouldn't be modified
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					if(onRing(i,j,ring)) {
						newSquarelotron.squarelotron[i][j] = this.squarelotron[j][i];
					}
				}
			}
			return newSquarelotron;
		}
		
		public void rotateRight(int numberOfTurns) {
			numberOfTurns = (numberOfTurns % 4 + 4)%4;
			for(int x = 0; x < numberOfTurns; x++) {
				Squarelotron newSquarelotron = new Squarelotron(size);
				for(int i = 0; i < size; i++) {
					for(int j = 0; j < size; j++) {
						newSquarelotron.squarelotron[j][size - 1 - i] = squarelotron[i][j];
					}
				}
				this.squarelotron = newSquarelotron.squarelotron;
			}
		}
		
		

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 6;
		Squarelotron sn = new Squarelotron(n);
		sn = sn.mainDiagonalFlip(4);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(sn.onRing(i, j, 4));
			}
			System.out.println();
		}

	}
	
}
