package in.sourashis.stonepaperscissors;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class for StonePaperScissors game
 * @author Sourashis Pal
 */
public class Main {

	/**
	 * Standard Scanner object for taking input from the user
	 */
	public static Scanner sc = new Scanner(System.in);

	/**
	 * main() method of the game
	 * @param args Command-line arguments (Unused)
	 */
	public static void main(String[] args) {
		// Displaying heading
		String heading = "STONE PAPER SCISSORS";
		System.out.println();
		System.out.println("=".repeat(56));
		System.out.print(" ".repeat((56-heading.length())/2));
		System.out.println(heading);
		System.out.println("=".repeat(56));

		String player1, player2;
		int ch;
		boolean vsComputer;

		// Accepting details from the user
		while (true) {
			// Asking game mode
			System.out.print("""
    
    1. Automatic
    2. vs Computer
    Enter choice:\040""");
			try {
				ch = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid Choice");
				continue;
			} finally {
				sc.nextLine();
			}

			// Accepting names
			switch (ch) {
				case 1 -> {
					System.out.print("\nEnter Player 1 name: ");
					player1 = sc.nextLine();
					if (player1.length() > 14) {
						player1 = player1.substring(0, 14);
					}
					System.out.print("Enter Player 2 name: ");
					player2 = sc.nextLine();
					if (player2.length() > 14) {
						player2 = player2.substring(0, 14);
					}
					vsComputer = false;
				}
				case 2 -> {
					System.out.print("\nEnter your name: ");
					player1 = sc.nextLine();
					if (player1.length() > 14) {
						player1 = player1.substring(0, 14);
					}
					player2 = "Computer";
					vsComputer = true;
				}
				default -> {
					System.out.println("Invalid Choice");
					continue;
				}
			}
			break;
		}

		// Accepting no. of rounds
		int rounds;
		while (true) {
			try {
				System.out.print("\nEnter no. of rounds: ");
				rounds = sc.nextInt();
				if (rounds <= 0) {
					System.out.println("Rounds must be greater than 0");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input");
				continue;
			} finally {
				sc.nextLine();
			}
			break;
		}
		System.out.println();

		// Making game instance and starting it
		StonePaperScissors game = new StonePaperScissors(new Player(player1), new Player(player2), rounds, vsComputer);
		game.start();
		game.end();
	}
}
