package trailv1.pkg;

/**
 * Location.java
 * 
 * @author Gabe Casilio
 * @version 1.0
 * @since [4/10/2024]
 * 
 * This class handles the locations of the landmarks represented
 * by integers in a linked hashmap in the Trail.java class.
 * 
 */
public class Location {

	private int distance;
	private String name;

	/**
	 * 
	 */
	public Location(String name, int distance) {
		// TODO Auto-generated constructor stub
		this.distance = distance;
		this.name = name;
	}
	
	public String getLocationName() {
		return name;
	}

	public int getDistance() {
		return distance;
	}
	
	public void updateDistance(int travelSpeed) {
		distance += travelSpeed;
	}
}