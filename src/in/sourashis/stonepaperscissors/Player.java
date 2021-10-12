package in.sourashis.stonepaperscissors;

/**
 * Represents each player of the StonePaperScissors game
 * @author Sourashis Pal
 */
public class Player {
	/**
	 * Name of the player
	 */
	private final String name;
	/**
	 * Points of the player
	 */
	private int points;

	/**
	 * initializes the player with the given name
	 * @param name Name of the player
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the player
	 * @return Name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the points of the player
	 * @return Points of the player
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Adds one point to the player
	 */
	public void addPoint() {
		points++;
	}
}
