import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
//import org.apache.commons.lang3.ArrayUtils;

public class MonsterGame {
	//CONSTANTS
	private static final int boardX = 10;
	private static final int boardY = 10;
	private static final char empty = '_';
	private static final String menu = "Select an action:\n"
			+ "p - Prints the board\n"
			+ "c - Create your Monster\n"
			+ "s - View or Edit Monster Stats\n"
			+ "a - Attack\n"
			+ "m - Move\n"
			+ "q - Quit the game\n";
	private static final Scanner userInput = new Scanner(System.in);
	
	//MONSTERGAME Fields
	private static char[][] board = new char[boardX][boardY];
	private static ArrayList<Monster> monsters = new ArrayList<Monster>();
	private static Monster player;
	
	//MONSTERGAME Methods
	public static void buildBoard() {
		for (char[] row : board) { //using a for each loop
			
			Arrays.fill(row,'_');
/*			Essentially executes the following for loop:
			for (int col = 0; col < col.length; col++) {
				row[col] = empty;
			}*/
		}
	}
	
	public static void printBoard() {
		System.out.println("Current Game Board: ");
		for (char[] row : board) {
			for (char gamePiece : row) {
				System.out.print("|"+gamePiece);
			}
			System.out.println("|");
		}
		System.out.println();
	}
	
	public static Monster newMonster() {
		Monster M = new Monster();
		int x,y;
		do {
			x = randXPosition();
			y = randYPosition();
		} while(board[y][x] != empty);
		M.setX(x);
		M.setY(y);
		placeMonster(M);
		monsters.add(M);
		return M;
	}
	
	public static Monster newMonster(String name) {
		Monster m = newMonster();
		m.setName(name);
		placeMonster(m);
		return m;
	}
	
	public static void moveMonsters() {
		///potential problem there may come a time when a monster is unable to move.
		for (Monster m : monsters) {
			if (m == player) {
				continue;
			} else {
				int move[] = {-1,-1};
				do {
					move = m.move();
				} while(!isValidMove(move, m));
				removeMonster(m);
				m.setX(move[0]);
				m.setY(move[1]);
				placeMonster(m);
			}
		}
	}
	
	public static void generateEnemies(int enemies) {
		for (int i = 0;i < enemies;i++) {
			newMonster();
		}
	}
	
	public static void editMonster(Monster m) {
		//TODO Edit monsters
		System.out.println("Edit Monster? [y/n]");
		if (userInput.nextLine().equalsIgnoreCase("y")) {
			System.out.println("Enter new monster name:");
			m.setName(userInput.nextLine());
		}
	}
	
	public static void viewStats(int[] position) {
		int x = position[0];
		int y = position[1];
		boolean found = false;
		for (Monster m : monsters) {
			if (m.getX()==x && m.getY() == y) {
				m.printStats();
				editMonster(m);
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("ERROR: There is no monster in position ("+x+","+y+")");
		}
		
	}
	
	public static void attack() {
		//TODO Attack other Monsters
	}
	
	public static void moveMyMonster() {
		//TODO Move player monster
	}
	
	//HELPER METHODS
	private static void placeMonster(Monster M) {
		board[M.getX()][M.getY()] = M.getName().charAt(0);
	}
	
	private static void removeMonster(Monster M) {
		board[M.getX()][M.getY()] = empty;
	}
	
	private static int randXPosition() {
		return (int) (Math.random() * (boardX -1));
	}
	
	private static int randYPosition() {
		return (int) (Math.random() * (boardY -1));
	}
	
	private static boolean isValidMove(int[] move, Monster M) {
		int x = move[0];
		int y = move[1];
		boolean nomove = (M.getX()==x && M.getY() == y);
		return (0<=x && x<boardX) && (0<=y && y<boardY) && (board[x][y] == empty || nomove);
		
	}
	
	
	
	//RUN IT
	public static void main(String[] args) {
		
		System.out.println("Welcome to Monster Hunter!");
		
		//Setting up the game
		buildBoard();
		
		int enemies = UserInput.readInt("Enter number of Enemies: ");
		generateEnemies(enemies);
		
		printBoard();

		String input = "";
		do {
			moveMonsters();
			System.out.println(menu);
			input = userInput.nextLine();

			if (input.toLowerCase().equals("p")) {
				printBoard();
			} else if (input.equalsIgnoreCase("c")) {
				System.out.println("Enter monster name:");
				player = newMonster(userInput.nextLine());
				player.setHealth(400);
				printBoard();
				
			} else if (input.equalsIgnoreCase("s")) {
				printBoard();
				int[] position = new int[2];
				position[0] = UserInput.readInt("Enter the x position of monster to view stats: ");
				position[1] = UserInput.readInt("Enter the y position of monster to view stats: ");
				viewStats(position);
				
			} else if (input.equalsIgnoreCase("a")) {
				attack();
				
			} else if (input.equalsIgnoreCase("m")) {
				printBoard();
				moveMyMonster();
				
			} else if (input.equalsIgnoreCase("q")) {
				System.out.println("Goodbye!");
				break;
				
			} else {
				System.out.printf("Invalid selection: %s\n",input);
			}
			System.out.println();
		} while (!input.equalsIgnoreCase("q")); 
			
		
	} //END OF MAIN

}
