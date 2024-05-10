package trailv1.pkg;

/**
 * 
 */

import java.util.Random;

/**
 * River.java
 * 
 * Stores the relevant values a location if it is a river such as river Width, river depth,
 * and flow rate. It handles updating these values as you wait at a location and then 
 * calculating whether you were successful crossing the river when attempted.
 * 
 * @author Gabriello Casilio
 * @version 1.1
 */
public class River extends Location{

	private int riverWidth;
	private int originalRiverWidth;
	private int riverDepth;
	private int flowRate;
	private int originalFlowRate;
	private Wagon wagon;
	private Item[] removedItems = new Item[2];
	private int[] removedItemAmountLost = new int[2];
	private String failureResult;
	Random rand = new Random();

	/**
	 * Constructor takes the inputed values and stores them within the relevant places
	 * @param name			The name of the location
	 * @param distance		The distance to this location from the last location
	 * @param riverWidth	The initial width of the river
	 * @param riverDepth	The initial depth of the river
	 * @param flowRate		The initial flow rate of the river
	 */
	public River(String name, int distance, int riverWidth, int riverDepth, int flowRate, Wagon wagon) {
		// TODO Auto-generated constructor stub
		super(name, distance);
		this.riverWidth = riverWidth;
		this.originalRiverWidth = riverWidth;
		this.riverDepth = riverDepth;
		this.flowRate = flowRate;
		this.originalFlowRate = flowRate;
		this.wagon = wagon;
	}

	/**
	 * Updates the depth of the river if the player chooses to wait or is forced to 
	 * at the location
	 */
	public void updateRiverDepth() {
		
		//randomly decides if the river is getting deeper or shallower
		int changeChoice = rand.nextInt(2);
		
		//Shrinks the depth except the river cannot get shallower than 1.5 feet
		if (changeChoice == 0) {
			
			if (riverDepth <= 2) {
				return;
			}
			else {
				riverDepth -= rand.nextInt(3);
			}
		}
		
		//Expands the depth except the river cannot get deeper than 10 feet
		if(changeChoice == 1) {
			
			if (riverDepth > 10) {
				return;
			}
			else if (riverDepth <= 10){
				riverDepth += rand.nextInt(3);
			}
		}
	}
	
	/**
	 * Returns the current depth of the river
	 * @return	The depth of the river
	 */
	public int getRiverDepth() {
		return riverDepth;
	}
	
	/**
	 * Updates the width of the river if the player chooses to wait or is forced to 
	 * at the location
	 */
	public void updateRiverWidth() {
		
		//randomly decides if the river is getting wider or narrower
		int changeChoice = rand.nextInt(2);
		
		//Shrinks the width, the river cannot get narrower than half what it started at
		if (changeChoice == 0) {
			if (riverWidth <= (originalRiverWidth / 2)) {
				return;
			}
			else {
				riverWidth -= rand.nextInt(15);
			}
		}
		
		//Expands the width, the river cannot get wider than twice what it started at
		if(changeChoice == 1) {
			if (riverWidth >= (originalRiverWidth * 2)) {
				return;
			}
			else {
				riverWidth += rand.nextInt(15);
			}
		}
	}
	
	/**
	 * Returns the current width of the river
	 * @return	The width of the river
	 */
	public int getRiverWidth() {
		return riverWidth;
	}
	
	/**
	 * Updates the flow rate of the river if the player chooses to wait or is forced to 
	 * at the location
	 */
	public void updateFlowRate() {
		
		
		int changeChoice = rand.nextInt(2);
		
		//Slows the rate, the river cannot get slower than 0.3 miles per hour
		if (changeChoice == 0) {
			if (flowRate <= 2) {
				return;
			}
			else {
				flowRate -= rand.nextInt(2);
			}
		}
		
		//Hastens the rate, cannot go faster than three times its original and less than 20 mph
		if(changeChoice == 1) {
			if (flowRate >= (originalFlowRate * 3) && flowRate <= 20) {
				return;
			}
			else {
				flowRate += rand.nextInt(2);
			}
		}
	}
	
	/**
	 * provides the flow rate of the river
	 * @return flowRate the integer flow rate in mph of the river.
	 */
	public int getFlowRate() {
		return flowRate;
	}

	/**
	 * Calculates whether the player was successful in an attempt to ford a river based
	 * on the current conditions
	 * @return false if unsuccessful, true if successful
	 */
	public boolean fordRiver() {
		int crossChance;
		
		//when shallower than 2 feet and slower than 1 mph, always successful
		if (riverDepth <= 3) {
			if (flowRate <= 2) {
				return true;
			}
		}
		
		//otherwise calculates success chance based on flow rate and width
		else if (riverDepth > 2 && riverDepth < 6) {
			crossChance = 20 + flowRate + (riverWidth / 100);
			System.out.println(crossChance);
			
			//needs to be less than 40 to be successful
			if( crossChance >= 40) {
				return false;
			}
			else {
				return true;
			}
		}
		else if (riverDepth > 6) {
			return false;
		}
		
		return false;
	}
	
	/**
	 * Calculates whether the player was successful in an attempt to float across
	 * a river based on the current conditions
	 * @return	false if unsuccessful, true if successful
	 */
	public boolean floatAcrossRiver() {
		
		int crossChance;
		
		//when less than 1 ft deep cannot float across
		if (riverDepth <= 2) {
			return false;
		}
		//otherwise, when less than 2 ft deep it is always successful
		else if (riverDepth < 2) {
			return true;
		}
		//otherwise, it calculates success based on the flow rate
		else {
			crossChance = (int) (((double) (flowRate / 10.0)) * 100);
			System.out.println(crossChance);
			
			if (crossChance > rand.nextInt(100)) {
				return false;
			}
			else {
				return true;
			}
		}
	}
	
	
	/**
	 * Calculates whether the player was successful in an attempt to take a 
	 * ferry across a river based on the current conditions
	 * @return	false if unsuccessful, true if successful
	 */
	public boolean ferryAcross() {
		
		//if the river is shallower than 4 ft it is always successful
		if (riverDepth <= 4) {
			return true;
		}
		//otherwise based on what the flow rate is compares against a percent chance
		else if (riverDepth > 4) {
			if (flowRate <= 3) {
				if (rand.nextInt(100) < 4) {
					System.out.println("false");
					return false;
				}
				else {
					return true;
				}
			}
			else if (flowRate > 3 && flowRate <= 15) {
				if (rand.nextInt(100) < (5 + flowRate)) {
					System.out.println(5 + flowRate);
					return false;
				}
				else {
					return true;
				}
			}
			else if (flowRate > 15 && flowRate <=20) {
				if (rand.nextInt(100) < ((10 + flowRate) * 1.5)) {
					return false;
				}
				else {
					return true;
				}
			}
		}
		System.out.println("WHY IS IT DOWN HERE?");
		return false;
	}
	
	/*
	 * Removes a random index and amount of items from the Players' wagon 
	 * if they fail to cross the given river.
	 */
	public void removeItems(){
		
		if (flowRate <= 3) {
			failureResult = "swamped";
		}
		
		else {
			
			int[] removedIndex = new int[2];
			failureResult = "capsized";
			
			System.out.println("wagon size: " + wagon.getSupplies().size());
			
			int cycle = 0;
			do {
				removedIndex[0] = (int)rand.nextInt((int) wagon.getSupplies().size());
				removedIndex[1] = (int)rand.nextInt((int) wagon.getSupplies().size());
				++cycle;
			} while(removedIndex[0] == removedIndex[1] || wagon.getSupplies().get(removedIndex[0]).getQuantity() == 0 || wagon.getSupplies().get(removedIndex[1]).getQuantity() == 0 || cycle < 1000);
			
			for(int i = 0; i < 2; ++i) {
				System.out.println("Removed Index:   " + removedIndex[i]);
			}
			
			
			for(int i = 0; i < 2; i++) {
				
				int minLoss = (int) Math.ceil(wagon.getSupplies().get(removedIndex[i]).getQuantity() * 0.1);
				int maxLoss = (int) Math.ceil(wagon.getSupplies().get(removedIndex[i]).getQuantity() * 0.75) + 1;
				System.out.println("Supplies calc from:   " + wagon.getSupplies().get(removedIndex[i]).getQuantity() + "   Min loss calc:   " + minLoss + "   Max Loss calc:   " + maxLoss);
				removedItemAmountLost[i] = rand.nextInt(minLoss,maxLoss);
				
				if(wagon.getSupplies().get(removedIndex[i]).getQuantity() == 0) {
					minLoss = 0;
					maxLoss = 0;
					removedItemAmountLost[i] = 0;
				}
				else if(wagon.getSupplies().get(removedIndex[i]).getQuantity() <= 4) {
					minLoss = 1;
					removedItemAmountLost[i] = rand.nextInt(minLoss,maxLoss);
				}
				
				System.out.println("Amount removed:   " + removedItemAmountLost[i]);
				
				System.out.println("The removed things were:   " + wagon.getSupplies().get(removedIndex[i]).getQuantity() + " min loss: " + minLoss + " max loss: " + maxLoss);
				System.out.println("Original Amount:   " + wagon.getSupplies().get(removedIndex[i]).getQuantity() + "   Amount LEft: " + (wagon.getSupplies().get(removedIndex[i]).getQuantity() - removedItemAmountLost[i]));
				
				
				if (wagon.getSupplies().get(removedIndex[i]).getQuantity() - removedItemAmountLost[i] <= 0 ) {
					removedItemAmountLost[i] = wagon.getSupplies().get(removedIndex[i]).getQuantity();
					wagon.editSupplies(wagon.getSupplies().get(removedIndex[i]).getName(), "remove", wagon.getSupplies().get(removedIndex[i]).getQuantity());
				}
				else {
					wagon.editSupplies(wagon.getSupplies().get(removedIndex[i]).getName(), "remove", removedItemAmountLost[i]);
				}
				
				removedItems[i] = (wagon.getSupplies().get(removedIndex[i]));
				
				
				System.out.println("Removed Item:   " + removedItems[i].getName());

			}
		}
	}
	
	
	/**
	 * Returns the last group of items that were changed from a river to cross a river
	 * @return the last group of items that were changed from a river to cross a river
	 */
	public Item[] getRemovedItems() {
		return removedItems;
	}
	
	
	/**
	 * Returns the amount removed from the last group of items
	 * @return the amount removed from the last group of items
	 */
	public int[] getRemovedItemAmount() {
		return removedItemAmountLost;
	}
	
	/**
	 * If an attempt to cross a river was made returns the result of the failure. If the 
	 * flow rate is less than 4 it returns "swamped" which means the supplies are just
	 * wet but if the flow rate is greater than 4 it returns "capsized" which means some
	 * supplies were lost
	 * @return the value of failureResult
	 */
	public String getFailureResult() {
		return failureResult;
	}
}