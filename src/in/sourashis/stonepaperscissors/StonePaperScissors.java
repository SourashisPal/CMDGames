package in.sourashis.stonepaperscissors;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Game class for StonePaperScissors game
 */
public class StonePaperScissors {

	/**
	 * Player 1
	 */
	private final Player player1;
	/**
	 * Player 2
	 */
	private final Player player2;
	/**
	 * No. of rounds in the game
	 */
	private final int rounds;
	/**
	 * Random object for generating random numbers
	 */
	private final Random random = new Random();
	/**
	 * true if the game is vs computer, false if the game is automatic for both users
	 */
	private final boolean vsComputer;
	/**
	 * Standard Scanner object for taking input
	 */
	private static final Scanner sc = Main.sc;

	/**
	 * Byte representation for stone
	 */
	private static final byte[][] STONE = {
		{0,0,0,1,1,1,1,0,0,0},
		{0,0,1,1,1,1,1,1,0,0},
		{0,1,1,1,1,1,1,1,1,0},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1},
		{0,1,1,1,1,1,1,1,1,0},
		{0,0,1,1,1,1,1,1,0,0},
		{0,0,0,1,1,1,1,0,0,0}
	};

	/**
	 * Byte representation for paper
	 */
	private static final byte[][] PAPER = {
		{0,1,1,1,1,1,1,1,1,0},
		{0,1,1,1,1,1,1,1,1,0},
		{0,1,1,1,1,1,1,1,1,0},
		{0,1,1,1,1,1,1,1,1,2},
		{0,1,1,1,1,1,1,1,1,0},
		{0,1,1,1,1,1,1,1,1,0},
		{0,1,1,1,1,1,1,1,1,0},
		{0,1,1,1,1,1,1,1,1,0},
		{0,1,1,1,1,1,1,1,1,0},
		{0,1,1,1,1,1,1,1,1,0}
	};

	/**
	 * Byte representation for scissors
	 */
	private static final byte[][] SCISSORS = {
		{0,1,0,0,0,0,0,0,0,0},
		{0,0,1,0,0,0,0,0,0,0},
		{0,0,0,1,0,0,0,0,0,0},
		{0,0,0,0,1,0,0,0,0,0},
		{0,0,0,0,0,1,0,0,0,0},
		{1,1,1,1,1,1,1,1,1,1},
		{0,0,0,0,0,1,1,0,0,1},
		{0,0,0,0,0,1,0,1,0,1},
		{0,0,0,0,0,1,0,0,1,1},
		{0,0,0,0,0,1,1,1,1,0}
	};

	/**
	 * Collection of all the objects of the game
	 */
	private static final byte[][][] OBJECTS = {
		STONE, PAPER, SCISSORS
	};

	/**
	 * Initializes the game with the required data
	 * @param player1 Player 1
	 * @param player2 Player 2
	 * @param rounds No. of rounds in the game
	 * @param vsComputer vsComputer or not
	 */
	public StonePaperScissors(Player player1, Player player2, int rounds, boolean vsComputer) {
		this.player1 = player1;
		this.player2 = player2;
		this.rounds = rounds;
		this.vsComputer = vsComputer;
	}

	/**
	 * Draws two objects chosen randomly
	 * @param first Index of object one
	 * @param second Index of object two
	 */
	private void draw(int first, int second) {
		for (int i = 0; i < 10; i++) {
			System.out.print(" ".repeat(3));
			for (byte c: OBJECTS[first][i]) {
				System.out.print(getString(c));
			}
			System.out.print(" ".repeat(6));
			for (byte c: OBJECTS[second][i]) {
				System.out.print(getString(c));
			}
			System.out.println();
		}
	}

	/**
	 * Returns the string according to the byte value
	 * @param c Byte value
	 * @return Corresponding string
	 */
	private String getString(byte c) {
		return c==1? "$ ": "  ";
	}

	/**
	 * Computes the result of a round, adds points and gives remarks
	 * @param first Index of first object
	 * @param second Index of second object
	 * @return Result of the round
	 */
	private String remarks(int first, int second) {
		return switch (first) {
			case 0 -> switch (second) {
				case 0 -> "Stone does nothing to stone";
				case 1 -> {
					player2.addPoint();
					yield "Paper covers stone";
				}
				case 2 -> {
					player1.addPoint();
					yield "Stone breaks scissors";
				}
				default -> null;
			};
			case 1 -> switch (second) {
				case 0 -> {
					player1.addPoint();
					yield "Paper covers stone";
				}
				case 1 -> "Paper does nothing to paper";
				case 2 -> {
					player2.addPoint();
					yield "Scissors cut paper";
				}
				default -> null;
			};
			case 2 -> switch (second) {
				case 0 -> {
					player2.addPoint();
					yield "Stone breaks scissors";
				}
				case 1 -> {
					player1.addPoint();
					yield "Scissors cut paper";
				}
				case 2 -> "Scissors do nothing to scissors";
				default -> null;
			};
			default -> null;
		};
	}

	/**
	 * Returns the choice of player 1.
	 * If it is automatic a random value is returned.
	 * If it is between the computer and the user, the choice of user is returned
	 * @return Choice of the user, or random if automatic
	 */
	private int getChoice() {
		if (vsComputer) {
			while (true) {
				System.out.print("Enter choice: ");
				int ch;
				try {
					ch = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Invalid Choice\n");
					sc.nextLine();
					continue;
				}
				switch (ch) {
					case 1, 2, 3 -> {
						System.out.println();
						return ch-1;
					}
					default -> System.out.println("Invalid Choice\n");
				}
			}
		} else {
			return random.nextInt(3);
		}
	}

	/**
	 * Gives instructions before starting of the game
	 */
	private void instruct() {
		if (vsComputer) {
			System.out.println("""
    1. Stone
    2. Paper
    3. Scissors
    You have to enter your choice
    """);
		}
	}

	/**
	 * Starts the game and continues it till the end
	 */
	public void start() {
		instruct();
		int pad1 = (26-player1.getName().length())/2;
		int pad2 = (26-player2.getName().length())/2;
		String heading = " ".repeat(pad1) +
				player1.getName() +
				" ".repeat(26 - player1.getName().length() - pad1) +
				" ".repeat(pad2) +
				player2.getName() +
				'\n';

		for (int i = 0; i < rounds; i++) {
			String round = "Round "+(i+1);
			System.out.println(" ".repeat(26-round.length()/2) + round + '\n');
			int choice1 = getChoice(), choice2 = random.nextInt(3);

			System.out.println(heading);
			draw(choice1, choice2);
			System.out.println('\n'+remarks(choice1, choice2));
			System.out.printf("%s - %d\t\t%s - %d\n\n", player1.getName(), player1.getPoints(), player2.getName(), player2.getPoints());
			if (!vsComputer) {
				System.out.print("Press enter to continue ");
				sc.nextLine();
			}
			System.out.println();
		}
	}

	/**
	 * Ends the game by showing the final results
	 */
	public void end() {
		System.out.print("Game ends - ");
		if (player1.getPoints() > player2.getPoints()) {
			System.out.printf("%s wins by %d points\n", player1.getName(), player1.getPoints()-player2.getPoints());
		} else if (player1.getPoints() < player2.getPoints()) {
			System.out.printf("%s wins by %d points\n", player2.getName(), player2.getPoints()-player1.getPoints());
		} else {
			System.out.println("Game tied");
		}
	}
}
