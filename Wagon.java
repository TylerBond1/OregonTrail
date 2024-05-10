package trailv1.pkg;

import java.util.ArrayList;

/**
 *  Wagon.java
 *  
 *  @author Tyler Bond and Austin Hutton
 *  @version 1.0
 *  @since [4/10/2024]
 *  
 *  The wagon class handles the overall health values "good, fair, poor". It also handles the party's total supplies
 *  and the methods that are used to access and edit those supplies. This version of the wagon class is incomplete, and not 
 *  used in our current MVP version of Oregon Trail, but will be used in the final version after further testing and 
 *  implementation.
 */
public class Wagon {
	int health = 0;
	ArrayList<Item> Supplies = new ArrayList<Item>();
	
	/**
	 * Class constructor
	 */
	public Wagon() {
		
	}
		
	/** 
	 * adds pre-made items to the wagon object.
	 * @param Horses number of horses in the player's wagon
	 * @param Food number of pounds of food in the player's wagon
	 * @param Clothing number of sets of clothing in the player's wagon
	 * @param Ammunition number of rounds of ammunition in the player's wagon
	 * @param WagonTongue number of spare wagon tongues in the player's wagon
	 * @param WagonWheel number of spare wagon wheels in the player's wagon
	 * @param WagonAxel number of spare wagon axels in the player's wagon
	 */
	public void createLoadedWagon(Item Horses, Item Food, Item Clothing, Item Ammunition, Item WagonTongue, Item WagonWheel, Item WagonAxel) {
		Supplies.add(Horses);
		Supplies.add(Food);
		Supplies.add(Clothing);
		Supplies.add(Ammunition);
		Supplies.add(WagonTongue);
		Supplies.add(WagonWheel);
		Supplies.add(WagonAxel);
	}
	
	/**
	 * adds a new supply to the wagon
	 * @param item Item object to be added to the wagon
	 */
	public void addSupply(Item item) {
		Supplies.add(item);
	}
	
	/**
	 * Sets a given supply in the wagon
	 * @param item Item object existing in the wagon to have its quantity set
	 * @param quantity the quantity that the item will be set to
	 */
	public void setSupply(String item, int quantity) {
		// Sort through the array list to locate the specified item and update its quantity.
				if (item.equals("Horses")) {
					Supplies.get(0).setQuantity(quantity);
				}
				if (item.equals("Food")) {
					Supplies.get(1).setQuantity(quantity);
				}
				if (item.equals("Clothing")) {
					Supplies.get(2).setQuantity(quantity);
				}
				if (item.equals("Ammunition")) {
					Supplies.get(3).setQuantity(quantity);
				}
				if (item.equals("WagonTongue")) {
					Supplies.get(4).setQuantity(quantity);
				}
				if (item.equals("WagonWheel")) {
					Supplies.get(5).setQuantity(quantity);
				}
				if (item.equals("WagonAxel")) {
					Supplies.get(6).setQuantity(quantity);
				}
	}
	
	/**
	 * edits existing supplies within the wagon
	 * @param item existing Item in the wagon
	 * @param function string "add" or "remove" telling the method what to do with the quantity
	 * @param quantity amount to be added or removed from the original item quantity
	 */
	public void editSupplies(String item, String function, int quantity) {
		// Determine whether you are adding or removing from an item
		if (function.equals("add"))    quantity *= -1;
		if (function.equals("remove")) quantity *=  1;
		// Sort through the array list to locate the specified item and update its quantity.
		if (item.equals("Horses")) {
			Supplies.get(0).subtractQuantity(quantity);
		}
		if (item.equals("Food")) {
			Supplies.get(1).subtractQuantity(quantity);
		}
		if (item.equals("Clothing")) {
			Supplies.get(2).subtractQuantity(quantity);
		}
		if (item.equals("Ammunition")) {
			Supplies.get(3).subtractQuantity(quantity);
		}
		if (item.equals("WagonTongue")) {
			Supplies.get(4).subtractQuantity(quantity);
		}
		if (item.equals("WagonWheel")) {
			Supplies.get(5).subtractQuantity(quantity);
		}
		if (item.equals("WagonAxel")) {
			Supplies.get(6).subtractQuantity(quantity);
		}
	}
	
	/**
	 * returns the quantity of an item in the wagon.
	 * @param item the name of the item in the wagon
	 * @return the quantity of an item in the wagon
	 */
	public int getItemQuantity(String item) {
		// Sort through available items in the wagon via string ID.
		if      (item.equals("Horses")) {
			return Supplies.get(0).getQuantity();
		}
		else if (item.equals("Food")) {
			return Supplies.get(1).getQuantity();
		}
		else if (item.equals("Clothing")) {
			return Supplies.get(2).getQuantity();
		}
		else if (item.equals("Ammunition")) {
			return Supplies.get(3).getQuantity();
		}
		else if (item.equals("WagonTongue")) {
			return Supplies.get(4).getQuantity();
		}
		else if (item.equals("WagonWheel")) {
			return Supplies.get(5).getQuantity();
		}
		else if (item.equals("WagonAxel")) {
			return Supplies.get(6).getQuantity();
		}
		else return 0;
	}
	
	/**
	 * modifies the health instance variable
	 * @param function string "add" or "remove" telling the method what to do with the quantity
	 * @param quantity amount of health to add or remove from the wagon's health
	 */
	public void editHealth(String function, int quantity) {
		// Determine whether you are adding or removing from health
		if (function.equals("add"))    quantity *=  1;
		if (function.equals("remove")) quantity *= -1;
		// Modify the health variable
		health += quantity;
	}
	
	/**
	 * sets the wagon health to a specified value
	 * @param health specified integer representing wagon health
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
     * Getter method for the wagon supplies 
     * @return Supplies the array list of supplies 
     */ 
	public ArrayList <Item> getSupplies(){
		return Supplies;
	}
	
	/**
     * Formats the supplies into an appropriate list 
     * @return String the string formatted for the JLabel
     */ 
	public String getSuppliesFormatted() {
		//builds a string of the item names and their corresponding quantity
		StringBuilder labelText = new StringBuilder();
        for (int i = 0; i < Supplies.size(); i++) {
            Item item = Supplies.get(i);
            String itemName = item.getName();
            int itemQuantity = item.getQuantity();
            labelText.append(itemName).append(": ").append(itemQuantity);
            if (i < Supplies.size() - 1) {
                labelText.append(", " + "\n");
            }
            
        }
        return labelText.toString();
	}
	
	/**
     * Gets the number of items in the wagon
     * @return int the number of items in the wagon
     */ 
	public int getNumberOfItems() {
		return Supplies.size();
	}

	
	
}