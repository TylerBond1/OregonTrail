package trailv1.pkg;

/**
 *  Item.java
 *  
 *  @author: Tyler Bond
 *  @version: 1.0
 *  @since [4/2/2024]
 *  
 *  This class represents items and their respective quantities that will be handled in the wagon class.
 */
public class Item {
	    // quantity: total amount of a certain supply item (Units will vary)
		int quantity;
		private String name;
		
		/**
		 * Item class has two variables, name and quantity
		 * @param quantity integer value representing the quantity of the item
		 * @param name the name "id" of the item
		 */
		public Item(int quantity, String name) {
			this.quantity = quantity;
			this.name = name;
		}
		
		/**
		 * returns the integer quantity of an item
		 * @return quantity the quantity of an item.
		 */
		public int getQuantity() {
			return quantity;
		}
		
		/**
		 * subtracts a certain amount from an item's current quantity.
		 * @param quantity integer amount to be removed from the item's quantity
		 */
		public void subtractQuantity(int quantity) {
			this.quantity -= quantity;
		}
		
		/**
	     * Getter function for the item name
	     * @return name the name of the item
	     */ 
		public String getName() {
	        return name;
	    }
		
		/**
		 * sets the quantity of an item
		 * @param quantity integer amount that the quantity will be set to
		 */
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		/**
		 * adds to the quantity of an item
		 * @param quantity integer amount that the quantity will increase by
		 */
		public void addQuantity(int quantity) {
			this.quantity += quantity;
		}
}