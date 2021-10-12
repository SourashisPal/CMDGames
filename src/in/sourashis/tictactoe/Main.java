package in.sourashis.tictactoe;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * This class acts as the Main class for the TicTacTow game.
 * @author Sourashis Pal
 */
public class Main {

	/**
	 * No. of cols on which formatting is to be done.
	 */
	public static final int COLS = 60;
	/**
	 * Standard Scanner object for taking input
	 */
	public static final Scanner sc = new Scanner(System.in);

	/**
	 * Shows the game manual to user.
	 * @throws IOException Any error while reading file
	 */
	private static void showManual() throws IOException {
		Scanner sc = new Scanner(Objects.requireNonNull(Main.class.getResourceAsStream("/in/sourashis/tictactoe/rules.txt")));
		while (sc.hasNextLine()) {
			System.out.println(sc.nextLine());
		}
		sc.close();
	}

	/**
	 * main() which starts the game
	 * @param args Command-line arguments (Unused)
	 */
	public static void main(String[] args) {
		// Displaying heading
		String heading = "TIC TAC TOE";
		System.out.println();
		System.out.println("=".repeat(COLS));
		System.out.print(" ".repeat(COLS/2-heading.length()/2));
		System.out.println(heading);
		System.out.println("=".repeat(COLS));

		// Asking the user if he/she wants to read the manual
		System.out.print("\nWant to read manual (Y/n): ");
		char ch = sc.next().charAt(0); sc.nextLine();
		System.out.println();
		if (ch == 'y' || ch == 'Y') {
			try {
				showManual();
				System.out.println();
			} catch (IOException e) {
				System.out.println("\nSorry! Some error occurred\n");
			}
		}

		// Continuous loop for the game
		while (true) {
			new TicTacToe().start();
			System.out.print("\nWant to play again (Y/n): ");
			ch = sc.next().charAt(0); sc.nextLine();
			if (ch == 'y' || ch == 'Y') {
				System.out.println("\nOK! Game starting...");
				System.out.println('\n'+"=".repeat(COLS));
				System.out.println();
				continue;
			}
			break;
		}
		System.out.println('\n'+"=".repeat(COLS));
		System.out.println("\nGoodbye\n");
	}
}
