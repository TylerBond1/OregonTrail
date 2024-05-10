package trailv1.pkg;

/**
 * WagonMember.java
 * 
 * @author Tyler Bond
 * @version 1.0
 * @since [4/10/2024]
 * 
 * This class handles the four members in the wagon that aren't the main player.
 * These wagon members are subject to the various random trail events the player
 * will encounter on the trail. Because of this, each member has their own name
 * and health bar. Wagon member health starts at zero, and increases as they
 * take damage. When a wagon member's health becomes greater than 140, they are
 * considered dead.
 */
public class WagonMember {
	String name;
	int health = 0;
	
	/**
	 * 
	 * @param name String value representing the name of the wagon member.
	 */
	public WagonMember(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the name of the wagon member
	 * @return name the name of the wagon member
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the wagon member
	 * @param name the name of the wagon member.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the health of the wagon member
	 * @return health the integer health value of the wagon member
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Adds to the wagon member's health, representing damage taken
	 * @param damage integer value representing damage taken
	 */
	public void takeDamage(int damage) {
		health += damage;
	}
	
	/**
	 * checks to see if the wagon member's health is above 140, meaning they are dead
	 * @return boolean true if alive, false if dead
	 */
	public boolean isAlive() {
		if (health < 140) return true;
		else              return false;
	}

}