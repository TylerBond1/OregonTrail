package trailv1.pkg;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * Health.java 
 * 
 * @author Grace Lawson
 * @version 1.0
 * @since [4/28/2024]
 * 
 * This class deals with the total health of the wagon. It can remove 
 * members from the wagon if they have died and calculate the total wagon health.
 * 
 * 
 * 
 */
public class Health {
	private ArrayList<WagonMember> wagonMembers;
	
	/**
     * Health class constructor 
     */ 
	public Health(ArrayList<WagonMember> wagonMembers) {
		this.wagonMembers = wagonMembers;
	}
	/**
     * Calculates the total wagon health
     * @return totalHeatlh the total health of everyone in the wagon
     */ 
	public int calculateTotalHealth(int initialHealth) {
        int totalHealth = initialHealth;
        //iterate over the ArrayList
        for (WagonMember member : wagonMembers) {
        	//calculating the total health of the wagon by adding each individual health
            totalHealth += member.getHealth();
        }
        
        if (wagonMembers.size() == 3) totalHealth += 140;
        if (wagonMembers.size() == 2) totalHealth += 280;
        if (wagonMembers.size() == 1) totalHealth += 420;
        if (wagonMembers.size() == 0) totalHealth += 560;
        
        return totalHealth;
    }
	
	/**
     * Removes someone from the wagon members list if they have died 
     * @param name the name of the person that has died 
     */ 
	public void hasDied(String name) {
        //iterate over the ArrayList to find matching names 
        Iterator<WagonMember> iterator = wagonMembers.iterator();
        while (iterator.hasNext()) {
            WagonMember member = iterator.next();
            //check if the item name matches the target name
            if (member.getName().equals(name)) {
                //remove the member from the array list if the name matches
                iterator.remove();
                break;
            }
        }
    }
	/**
     * Gets the health level of the party.
     * @return wagonMembers the arrayList of wagonMembers
     */
	public String getHealthLevel(double totalHealth){
		double averageHealth = totalHealth / wagonMembers.size();
		if(averageHealth < 34) {
			return "Good";			
		}
		else if (averageHealth > 34 && averageHealth < 66 ) {
			return "Fair";
		}
		else if(averageHealth > 66 && averageHealth < 105) {
			return "Poor";
		}
		else if(averageHealth > 105) {
			return "Very Poor";
		}
		return "";
	}
	/**
     * Returns the ArrayList of wagon members
     * @return wagonMembers the arrayList of wagonMembers
     */
	public ArrayList <WagonMember> getWagonMembers(){
		return wagonMembers;
	}
}