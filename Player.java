package trailv1.pkg;

/**
 * Player.java
 * 
 * @author Tyler Bond
 * @version 1.0
 * @since [4/10/2024]
 * 
 * The player class handles all of the methods that involve the player
 * themselves, not including the other CPU members of the wagon. This class
 * handles player name, balance, and point multiplier.
 */
public class Player {
	int balance;
	int multiplier;
	String name;
	
	/**
	 * Class constructor
	 * @param balance the integer amount of money that the player has
	 * @param multiplier the coefficient of the player's points at the end of the game
	 * @param name the String name of the player
	 */
	public Player(int balance, int multiplier, String name) {
		this.balance    = balance;
		this.multiplier = multiplier;
		this.name       = name;
	}
	
	/**
	 * Gets the name of the player
	 * @return name the String name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the balance of the player
	 * @return balance the integer amount of money that the player has
	 */
	public int getBalance() {
		return balance;
	}
	
	/**
	 * Gets the multiplier of the player
	 * @return multiplier the coefficient of the player's points at the end of the game
	 */
	public int getMultiplier() {
		return multiplier;
	}
	
	/**
	 * Sets the name of the player
	 * @param name the String name of the player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the balance of the player
	 * @param balance the integer amount of money that the player has
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	/**
	 * Sets the multiplier of the player
	 * @param multiplier the coefficient of the player's points at the end of the game
	 */
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	
	/**
	 * Subtracts a dollar value from the player's balance, representing a purchase
	 * @param cost the integer value of the purchase made
	 */
	public void spendMoney(int cost) {
		balance -= cost;
	}

}