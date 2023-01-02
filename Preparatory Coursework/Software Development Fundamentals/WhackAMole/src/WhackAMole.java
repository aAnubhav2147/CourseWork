import java.util.*;

public class WhackAMole {
	
	//Instance Variables
	int score = 0;
	//int molesLeft = 10;
	int molesLeft;
	int attemptsLeft = 50;
	char[][] moleGrid; //Declaring a 2D array
	char c = '*';
	char m = 'M';
	static boolean check = false;
	
	//Coordinate Variables
	//int xCoord;
	//int yCoord;
	static int x = 0;
	static int y = 0;
	
	//Constructor
	public WhackAMole(int numAttempts, int gridDimension) {
		this.score = score;
		this.molesLeft = molesLeft;
		this.attemptsLeft = numAttempts;
		this.moleGrid = new char[gridDimension][gridDimension];
		for(char[] d : moleGrid) {
			Arrays.fill(d, c);
		}
		
	}
	
	public boolean place(int x, int y) {
		this.x = x;
		this.y = y;
		//Can't place a mole where there is already one present
		if(moleGrid[x][y] == m) { 
			return false;
		}
		moleGrid[x][y] = m; //Placing a mole
		molesLeft++;
		return true;				
	}
	
	public void whack(int x, int y) {
		this.x = x;
		this.y = y;
		if(moleGrid[x][y] == m) {
			score+=100;
			molesLeft--;
			moleGrid[x][y] = 'W'; 
		}
		attemptsLeft--;				
	}
	
	public void printGridToUser() {
		for(char[] d : moleGrid) {
			for(char s : d) {
				if(s != 'W') {
					System.out.print(c);
				}
				else {
					System.out.print('W');
				}
			}
			System.out.println();
		}
	}
	
	public void printGrid() {
		for(char[] d : moleGrid) {
			for(char e : d) {
				if(e == m) {
					System.out.print(m);
				}
				else if(e == 'W'){
					System.out.print('W');
				}
				else {
					System.out.print(c);
				}
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		WhackAMole game = new WhackAMole(50,10);
		Random r = new Random();
		for(int i = 0; i < 10; i++) {
			game.place(r.nextInt(10), r.nextInt(10));
		}
		
		Scanner input = new Scanner(System.in);
		System.out.println("BEGIN GAME & GET WHACKING!!");
		
		while(game.attemptsLeft > 0 && game.molesLeft > 0) {
			System.out.println("Attempts Left: " + game.attemptsLeft);
			System.out.println("Score: " + game.score);
			game.printGridToUser();
			System.out.println("Enter Numeric Co-ordinates");
			
			if(input.hasNextInt()) {
				if(input.hasNextInt()) {
					 x = input.nextInt();
					check = true;
				}
				else {
					System.out.println("Invalid input entered. Terminating...");
	     		    break;
				}
				if(input.hasNextInt()) {
					y = input.nextInt();
					check = true;
				}
				else {
					System.out.println("Invalid input entered. Terminating...");
	     		    break;
				}							
			}
			else {
				System.out.println("Invalid input entered. Terminating...");
     		    break;
			}
			
			if(x == -1 && y == -1){
				game.printGrid();
				System.out.println("You Gave Up!!");
				break;
			}
			
			game.whack(x,y);
						
		}
		//System.out.println("High Score: " + score);
		System.out.println("Game Over!!!");
			
	}
	
}
