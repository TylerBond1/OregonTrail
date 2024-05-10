package trailv1.pkg;

import java.util.ArrayList;
import java.util.Random;

/**
 * Trade.java 
 * 
 * @author Grace Lawson 
 * @version 1.0
 * @since [4/10/2024]
 * 
 * This class handles the trading of the Oregon Trail game. This class "makes the decision" of whether anyone wants to trade
 * and what items they want from you in return for their items. 
 * 
 * 
 */

public class Trade {
	private Wagon wagon;

    public Trade(Wagon wagon) {
        this.wagon = wagon;
    }
	ArrayList <Item> Supplies = new ArrayList<Item>();

	Random rand = new Random();
	private int yourTradeItemAmount;
	private String yourTradeItemString;
	private int theirTradeItemAmount;
	private String theirTradeItemString;
	
	ArrayList<Item> TradeSupplies = new ArrayList<Item>();
	//creates the wagon's items
	Item Oxen        = new Item(3, "Horses");
	Item Food        = new Item(400, "Food");
	Item Clothing    = new Item(6, "Clothing");
	Item Ammunition  = new Item(200, "Ammunition");
	Item WagonWheel  = new Item(2, "WagonWheel");
	Item WagonTongue = new Item(2, "WagonTongue");
	Item WagonAxel   = new Item(2, "WagonAxel");
	
	/**
     * Loads the trader items each time into their own wagon (to be loaded each time)
     */ 
	public void loadTraderItems() {
		TradeSupplies.add(Oxen);
		TradeSupplies.add(Food);
		TradeSupplies.add(Clothing);
		TradeSupplies.add(Ammunition);
		TradeSupplies.add(WagonWheel);
		TradeSupplies.add(WagonTongue);
		TradeSupplies.add(WagonAxel);
	}
	/**
     * Determines whether there is anyone willing to trade
     * @return decision yes if someone wants to trade, no otherwise 
     */ 
	public String doYouWantToTrade() {
		String decision;
		int random = rand.nextInt(10) + 1;
		if (random == 1) {
			decision = "no";
		}
		else {
			decision = "yes";
		}
		return decision;
	}
	/**
     * Determines the items to trade and their amounts 
     * @return void
     */ 
	public void createTradeItems() {
		
		int theirSuppliesIndex;
	    do {
	    	//calculates the random item and item amount the trader wants to give you 
	        theirSuppliesIndex = rand.nextInt(TradeSupplies.size());
	        theirTradeItemString = TradeSupplies.get(theirSuppliesIndex).getName();
	        theirTradeItemAmount = rand.nextInt(TradeSupplies.get(theirSuppliesIndex).getQuantity()) + 1;
	    } while (theirTradeItemAmount == 0); //makes sure the item amount is not 0

	    //generate random item and amount for your trade item
	    int suppliesIndex;
	    do {
	        suppliesIndex = rand.nextInt(wagon.getNumberOfItems());
	        yourTradeItemString = wagon.getSupplies().get(suppliesIndex).getName();
	        yourTradeItemAmount = rand.nextInt(wagon.getSupplies().get(suppliesIndex).getQuantity()) + 1;
	        //ensures the amount is not zero and it is not the same item as the trader
	    } while (yourTradeItemAmount == 0 || yourTradeItemString.equals(theirTradeItemString));
	}
	/**
     * Updates the wagon's contents
     * @return void
     */ 
	public void tradeItems(){
		//removes your items from your wagon
		wagon.editSupplies(yourTradeItemString, "remove", yourTradeItemAmount);
		//adds the items to your wagon
		wagon.editSupplies(theirTradeItemString, "add", theirTradeItemAmount);
	}
	
	/**
     * Gets their trade items and amount
     * @return String formatted string with item name and amount
     */ 
	public String getTheirTradeItems() {
		return theirTradeItemAmount + " " + theirTradeItemString;
	}
	
	/**
     * Gets your trade items and amount
     * @return String formatted string with item name and amount
     */ 
	public String getYourTradeItems() {
		return yourTradeItemAmount + " " + yourTradeItemString;
	}
}