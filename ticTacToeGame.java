import java.io.*;
import java.util.Random;
/**
 * A simple single player tic-tac-toe game where you are player "X" and the computer 
 * is player "O". Moves will be made by using the keyboard numbers 1-9 (inclusive), 
 * and each number corresponds to its own spot on the board. 
 * <br> <center> [1][2][3]
 * <br> [4][5][6]
 * <br> [7][8][9] </center>
 * <br> In order to win a player must take 3 consecutive spots in a row (horizontally, 
 * vertically, or diagonally). The program terminates once a player has won or
 * all spots on the board have been taken and the game ends in a tie. The computer 
 * game will not make any intelligent moves as it is determined by a pseudo
 * random number generator. 
 * @author Lia Zheng 
 */

public class ticTacToeGame {
	/**
	 * Array containing visual representation of gameboard.
	 */
	public static String[] gameboardArray = {"[1]", "[2]", "[3]", "[4]", "[5]", "[6]", "[7]", "[8]", "[9]"};
	
	/**
	 * Array containing int values of winning gameboard states.
	 */
	public static int[][] winsArray = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
	
	
	/**
	 * Populates board at start of game. Shows numerical position values.
	 */
	public static void populateBoard() {
		printBoard();
		for (int i=0; i < gameboardArray.length; i++) {
			gameboardArray[i] = "[ ]";
		}
	}
	/**
	 * Prints board at current state.
	 */
	public static void printBoard() {
		for (int i=0; i < 7; i = i + 3) {
			System.out.println(gameboardArray[i] + gameboardArray[i+1] + gameboardArray[i+2]);
		}
	}
	
	/**
	 * Returns boolean of whether player is in a winning state or not.
	 * @param player String representation of player "X" or player "O"
	 */
	
	public static boolean checkWin(String player) {
		for (int i=0; i < winsArray.length ; i++) {
			if (player == gameboardArray[winsArray[i][0]] && player == gameboardArray[winsArray[i][1]] && player == gameboardArray[winsArray[i][2]]) {
				printBoard();
				System.out.println("Player " + player + " wins.");
				System.exit(0);
			}
		}
		
		return false;
	}
	/**
	 * Pseudo-randomly generates a move for computer player "O".
	 * @throws IOException
	 */
	public static void randMove() throws IOException {
		
		Random rand = new Random();
	    int randPos = rand.nextInt(8);
	    while (gameboardArray[randPos] == "[O]" || gameboardArray[randPos] == "[X]") {
	    	 randPos = rand.nextInt(8) ;
	     }
	    System.out.println("Player O has made their move.");
	    updateBoard(randPos, "[O]");
	}
	
	/**
	 * Updates gameboard array with appropriate move.
	 * @param pos int value of position
	 * @param player String representation of player "X" or player "O"
	 * @throws IOException 
	 */
	public static void updateBoard(int pos, String player) throws IOException {
		while (gameboardArray[pos] == "[X]" || gameboardArray[pos] == "[O
]") {
			System.out.println("Sorry that spot is taken, try again:");
			pos = askInput() - 1;
		}
		gameboardArray[pos] = player;
		checkWin(player);
	}
	
	/**
	 * Returns user input.
	 * @throws IOException 
	 */
	
	public static int askInput() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
	    BufferedReader br = new BufferedReader(isr);
	    String input = br.readLine();
	    while (input.contentEquals("") || input.length()>1 || input.contentEquals("0")) {
			System.out.println("Please enter a valid input:");
		    input = br.readLine();
		}
	    try {
	    Integer.parseInt(input);
	    } catch (NumberFormatException  NFE) {
	    	System.out.println("Please enter a number on your keypad:");
	    	return askInput();
	    }
	    int num = Integer.valueOf(input);
	    return num;
	}
	
	/**
	 * Runs game.
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 1;
		System.out.println("Welcome to TicTacToe! This game is played using numbered positions 1-9.");
		System.out.println("Each number corresponds with the position of your move. (e.g. 1 corresponds with the top left corner)");
		System.out.println("You are player X. In order to win, you must get 3 in a row.");
	    populateBoard();
	    while(i < 5) {
	    System.out.print("Enter your move: ");
	    try {
			int pos = askInput();
			updateBoard(pos-1, "[X]");
			randMove();
			printBoard();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    
	    System.out.println("Game over. A tie has occured.");
	    System.exit(0);
	}

}
