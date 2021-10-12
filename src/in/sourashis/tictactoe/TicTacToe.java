package in.sourashis.tictactoe;

import java.util.*;

/**
 * The Game class for the TicTacToe game.
 * @author Souarshis Pal
 */
public class TicTacToe {

	/**
	 * Represents the board of the game
	 */
	private final char[][] board = new char[3][3];
	/**
	 * Represents the numbers representing input places for the game.
	 */
	private final char[][] left = new char[3][3];
	/**
	 * Standard Scanner object for taking input from the user.
	 */
	public static final Scanner sc = Main.sc;
	/**
	 * Format string for printing grids.
	 */
	private static final String format = "| %c | %c | %c |";
	/**
	 * Separating line between two rows of the grid.
	 */
	private static final String line = "+---+---+---+";
	/**
	 * The indent to be given for centering grids.
	 */
	private final String indent = " ".repeat((Main.COLS-line.length())/2);
	/**
	 * Represents the player name for the corresponding choice.
	 */
	private final Map<Character, String> choice = new HashMap<>();
	/**
	 * The current choice going on.
	 */
	private char current;

	/**
	 * Initializes fields and asks details from the user
	 */
	public TicTacToe() {
		// Initializing each cell of the board with space
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}

		// Asking credentials
		System.out.print("Enter Player 1 name: ");
		String player1 = sc.nextLine();
		System.out.print("Enter Player 2 name: ");
		String player2 = sc.nextLine();
		System.out.print("Enter choice of Player 1 (X, O): ");
		char ch = sc.next().toUpperCase().charAt(0); sc.nextLine();
		if (ch == 'X') {
			choice.put('X', player1);
			choice.put('O', player2);
			current = 'X';
		} else {
			choice.put('X', player2);
			choice.put('O', player1);
			current = 'O';
		}
	}

	/**
	 * Fills the current choice at the given input position
	 * @param ch Input position to fill in
	 */
	private void fill(int ch) {
		ch += 48;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (left[i][j] == ch) {
					board[i][j] = current;
					return;
				}
			}
		}
	}

	/**
	 * Checks whether anyone has won the game according to the current state of the board
	 * @return 'X' if the player with X has won, 'O' if the player with O has won, '\0' if no one has own
	 */
	private char check() {
		// Arrays for storing data of both diagonals of the board
		char[] diagonal1 = new char[3];
		char[] diagonal2 = new char[3];
		for (int i = 0; i < 3; i++) {
			// Checking rows
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
				return board[i][0];
			}
			// Checking columns
			if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ') {
				return board[0][i];
			}
			diagonal1[i] = board[i][i];
			diagonal2[i] = board[i][2-i];
		}
		// Checking left diagonal
		if (diagonal1[0] == diagonal1[1] && diagonal1[1] ==diagonal1[2] && diagonal1[0] != ' ') {
			return diagonal1[0];
		}
		// Checking right diagonal
		if (diagonal2[0] == diagonal2[1] && diagonal2[1] == diagonal2[2] && diagonal2[0] != ' ') {
			return diagonal2[0];
		}
		return '\0';
	}

	/**
	 * Displays the input grid along with input positions, so that the user can enter their choice in the board
	 */
	private void displayInput() {
		// Setting the positions
		int c = 49;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					left[i][j] = (char) (c++);
				} else {
					left[i][j] = ' ';
				}
			}
		}

		// Displaying grid
		System.out.println(indent+line);
		for (char[] row: left) {
			System.out.print(indent);
			System.out.printf(format+'\n', row[0], row[1], row[2]);
			System.out.println(indent+line);
		}
		System.out.println();
	}

	/**
	 * Prints the board of the game
	 */
	private void printBoard() {
		System.out.println();
		System.out.println(indent+line);
		for (char[] row: board) {
			System.out.print(indent);
			System.out.printf(format +'\n', row[0], row[1], row[2]);
			System.out.println(indent+line);
		}
		System.out.println();
	}

	/**
	 * Starts the game and runs it
	 */
	public void start() {
		int ch, placesFilled = 0;
		char result;

		// This loop will run until all places are filled or is broken due to winning
		while (placesFilled < 9) {
			System.out.println("\nTurn for "+choice.get(current)+" ("+current+")");
			displayInput();

			// Taking input of choice
			while (true) {
				try {
					System.out.print("\nEnter the value of the place you want to fill: ");
					ch = sc.nextInt();
					if (ch > 9-placesFilled || ch < 0) {
						System.out.println("\nInvalid Input");
						continue;
					}
				} catch (InputMismatchException e) {
					sc.nextLine();
					System.out.println("\nInvalid Input");
					continue;
				}
				break;
			}

			// Filling and printing board
			fill(ch);
			printBoard();
			result = check();
			if (result != '\0') {
				System.out.println(choice.get(result)+" wins");
				return;
			}

			// Toggling current choices and incrementing placesFilled
			if (current == 'X') {
				current = 'O';
			} else {
				current = 'X';
			}
			placesFilled++;
		}

		System.out.println("Match drawn");
	}
}
