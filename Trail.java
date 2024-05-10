package trailv1.pkg;

import java.util.*;

/**
 * Trail.java 
 * 
 * @author Grace Lawson and Gabe Casilio
 * @version 1.0
 * @since [4/2/2024]
 * 
 * This class handles the trail of the Oregon Trail game. In this class, there are methods to calculate the 
 * current location, the distance to next landmark, call random trail events, and set the action options
 * when the player arrives at a landmark. 
 * 
 * 
 * 
 */
public class Trail {
	private LinkedHashMap<String, Integer> Landmarks = new LinkedHashMap<>();
	private HashMap<String, ArrayList<String>> LandmarkConversations = new HashMap<>();

	private String currentLandmark;
	private int milesToNextLandmark;
	private int day = 1;
	public  int month;
	private String monthString = "problem";
	private int currentLocation = 0;
	private int travelSpeed = 16;
	private int nextLandmarkLocation;
	private int foodRations = 3; 
	private int currentMessage = 0;
	public int wagonMembers = 5;
	Wagon wagon;
	Item foodItem = new Item(500, "Food");

	/**
     * Constructor for the trail class
     */ 
	public Trail(Wagon wagon) {
		Landmarks.put("Kansas River Crossing", 110);
		Landmarks.put("Fort Kearney", 330);
		Landmarks.put("Chimney Rock", 580);
		Landmarks.put("Fort Laramie", 667);
		Landmarks.put("Independence Rock", 857);
		Landmarks.put("Green River Crossing", 914);
		Landmarks.put("Soda Springs", 1057);
		Landmarks.put("Fort Hall", 1114);
		Landmarks.put("Snake River Crossing", 1296);
		Landmarks.put("Fort Boise", 1409);
		Landmarks.put("Blue Mountains", 1569);
		Landmarks.put("The Dalles", 1694);
		Landmarks.put("Willamette Valley, Oregon", 1788);
		
		ArrayList<String> landmarkList1 = new ArrayList<>();
		landmarkList1.add("Dr.McDougal tells you:\n"
				+ "“Hello, Mrs. Morris, I assume you’ve seen my new carriage and"
				+ "Pony? I just purchased them before leaving Independence. I "
				+ "will be traveling alongside you and your family to Oregon, so "
				+ "please feel free to relax in the carriage while we pack the rest "
				+ "of the travelers’ belongings.”");		
		landmarkList1.add("Mrs. Graham exclaims: \n"
				+ "“It will be so nice to have a travel partner such as yourself. "
				+ "The doctor speaks highly of your friendship, and I believe that "
				+ " friendship will be very valuable along the trip!”");
		landmarkList1.add("A man tells you: \n"
				+ "“This is the first river we've had to cross so far! "
				+ "I'll admit, it does make me nervous. Especially with "
				+ "such a full wagon. More to lose I suppose. But, we've "
				+ "got to stay hopeful.”");
		
		ArrayList<String> landmarkList2 = new ArrayList<>();
		landmarkList2.add("A soldier says to you: \n"
				+ "“I heard you got caught up a few days at Big Turkey Creek. "
				+ "Sometimes it feels like the rain never stops, doesn’t it? "
				+ "Luckily, this fort is on elevated ground so you won’t have "
				+ "to worry about flooding here. This is a well-traveled section "
				+ "of the trail. Sometimes, we see as many as 500 ox teams go through here a day.”");		
		landmarkList2.add("A friendly woman says:\n"
				+ "“We're only a few hundred miles in to the journey, but I miss "
				+ "having a roof over my family's head. There's still many days left "
				+ "until the journey is complete. On the bright side, we'll certainly be "
				+ "grateful when we get there!”");		
		landmarkList2.add("A traveler says to you:\n"
				+ "“Boy, can't miss your group rolling up! Might take a while for "
				+ "all of you to cross the river, but I'm sure traveling with the "
				+ "military has its perks. Thieves won't want to mess with you, "
				+ "that's for sure!”");
		
		ArrayList<String> landmarkList3 = new ArrayList<>();
		landmarkList3.add("An old friend expresses enthusiastically: \n"
				+ "“Finally made it across the plains! Being able to see Chimney Rock "
				+ "from miles and miles away felt like a boost after all the troubles "
				+ "we have faced. I’m glad to see you in good health! You almost look "
				+ "better than when I last saw you in Independence Rock. Major Morris "
				+ "mentioned you had little wood on the plains, so I loaded some in your "
				+ "wagon to repay you for the sugar you gave us.”");
		landmarkList3.add("A soldier traveling with you says: \n"
				+ "“Hopefully we won't be dealing with the wind now that the ground "
				+ "isn't as flat. Even when I wasn't on watch, I could hardly sleep "
				+ "with the wind whistling and the sound of the wagons shaking. I hope "
				+ "we got all of your paper that blew away! It would be a shame to have "
				+ "missing pages in your diary.”");
		landmarkList3.add("Dr.McDougal pulls you aside and says:\n"
				+ "“Your husband mentioned to me that you have been feelingn naseous the "
				+ "last few days in the mornings. Just remember that you can always "
				+ "come to me if you aren't feeling well.”");
		
		ArrayList<String> landmarkList4 = new ArrayList<>();
		landmarkList4.add("Mrs. Smith tells you:\n"
				+ "“Oh, Mrs. Morris, thank you for the much needed charcoal to cook "
				+ "my supper with. I caught word that some of the soldiers will be "
				+ "back with firewood in the morning, hopefully they make haste so "
				+ "we can have a warm night before continuing the trail.”");		
		landmarkList4.add("The shop cashier on a break tells you:\n"
				+ "“Your group might want to stock up while you can. "
				+ "You won't hit another fort for a few hundred miles. "
				+ "Times between forts can be rough, especially as the weather gets warmer.”");
		landmarkList4.add("Mrs. Graham says:\n"
				+ "“A few days of rest here might do us good. The next section "
				+ "of the trail is sure to be long and difficult. We might "
				+ "get a few good nights of sleep and have our wits about us "
				+ "before we set off again.”");
		
		ArrayList<String> landmarkList5 = new ArrayList<>();
		landmarkList5.add("A traveler says to you: \n"
				+ "“If your party is in need of rest, this is the spot to do it. "
				+ "You should go up and carve your name. Might struggle to find a "
				+ "spot to do it. It’s pretty crowded, even with it being 1,900 feet long. "
				+ "After this, the trail gets a little murky, so be sure to look at your map!”");		
		landmarkList5.add("Mr. McDougal approaches you:\n"
				+ "“Quite the resting spot for a pioneering American don’t you think Mrs. Morris? "
				+ "It is quite the sight to behold but it’s only too bad that I’ve lost my chairs "
				+ "to that mud. It is lucky that you are such an interesting source of conversation. "
				+ "Do you presume that we could go mark our names on the rock along with your husband, "
				+ "like many other pioneers have before?”");
		landmarkList5.add("You write a letter to your father:\n"
				+ "“We had a pleasant journey today with nice weather. We have "
				+ "just arrived at Independence Rock, and it is a sight "
				+ "to see! I miss you dearly, and I'll write again soon.”");
		
		ArrayList<String> landmarkList6 = new ArrayList<>();
		landmarkList6.add("A friendly woman tells you:\n"
				+ "“We decided to wait a few days to see if the conditions "
				+ "improve. We lost 2 oxen the last time we tried to float "
				+ "across, and we can't afford those losses again. Some folks "
				+ "recommend spending the money on the ferry, but I'm not "
				+ "too keen on doing that.”");
		landmarkList6.add("A man says:\n"
				+ "“I would walk all the way around the river if I could. "
				+ "I'm always dreading crossing when we approach. My "
				+ "brother's travel group lost everything here and had "
				+ "to travel back to Fort Loramie for supplies.”");
		landmarkList6.add("An exasperated merchant approaches you and Mrs. Smith: "
				+ "“I can’t believe the weather we are having! The crossing is impassable "
				+ "right now and we have been stuck here for three days already! If only "
				+ "the rain would let up, then we could cross. Or if only the ferryman hadn’t raised "
				+ "their prices so much. I hope you do not have to wait much longer with me.”");
		
		ArrayList<String> landmarkList7 = new ArrayList<>();
		landmarkList7.add("Dr.McDougal tells you:\n"
				+ "“Upon leaving from the Green River, we were forced to stop a mere "
				+ "hour into our travels when we encountered creeks that could sink "
				+ "a horse in their mud. It took almost four hours to build a safe "
				+ "passage across.”");
		landmarkList7.add("A soldier tells you:\n"
				+ "“The dinner you prepared for everyone last night was fantastic. "
				+ "I know it isn't easy cooking for that many people with the lack of "
				+ "supplies, but somehow you managed a great meal.”");
		landmarkList7.add("A woman says to you:\n"
				+ "“We have been with a lack of sleep the last few nights. A part of it "
				+ "is an eagerness to just get going, but my family finds it particularly "
				+ "hard to sleep out in the open as we have been. I must admit we are nervous "
				+ "about thieves.”");
		
		ArrayList<String> landmarkList8 = new ArrayList<>();
		landmarkList8.add("You write a letter to your father:\n"
				+ "“I bought the most beautiful pair of mocassins from a Native American "
				+ "shop yesterday. I know they aren't very pratical for this trip, "
				+ "but I knew I'd never find such shoes again. I will wait until we "
				+ "arrive in Oregon to wear them.”");
		landmarkList8.add("Dr. McDougal tells you:"
				+ "“It might be best for us to rest here a few days where provisions are "
				+ "available. Many of us are feeling ill, and I think it best if we took "
				+ "a break. The trail is greuling, especially when the weather is bad, as "
				+ "it has been.”");
		landmarkList8.add("A man tells you:"
				+ "“My group could not have made it ten more miles without reaching a fort. "
				+ "We have been eating scraps for days and traveling at a quick pace to "
				+ "make it here as soon as possible. We've lost nobody yet, and I hope "
				+ "to keep it that way.”");
			
		ArrayList<String> landmarkList9 = new ArrayList<>();
		landmarkList9.add("Mrs. Graham says to you:\n"
				+ "“You're a blessing for making those bullets yesterday. We were low on meat, "
				+ "but we can hunt for more to save some money at the shops. It feels like we're "
				+ "in the home stretch now. I just can't wait to be there and sleep soundly again.”");
		landmarkList9.add("Dr. McDougal approaches you:\n"
				+ "“I have prepared a tea for you that will help with your nasuea. "
				+ "Being pregnant on the trail is no easy task, so we need to keep "
				+ "your strength up. Please, let me know if you need anything.”");
		landmarkList9.add("You say to your husband:\n"
				+ "“As much as I miss home and having a real bed, "
				+ "there's some things I've tried out here that I "
				+ "never realized I liked so much. I enjoy "
				+ "hard work outside and negotiating with people. "
				+ "This journey has given me a new sense of independence.”");
		
		ArrayList<String> landmarkList10 = new ArrayList<>();
		landmarkList10.add("Your husband says to you:\n"
				+ "“I'm so proud of how you have handled the trip while pregnant. "
				+ "Traveling it in general is no easy task, but you have managed "
				+ "it in your condition so impressively while also helping us out. "
				+ "None of us would be here today as healthy as we are without you.”");
		landmarkList10.add("A soldier stationed in the base tells Anna:\n"
				+ "“The flooding here is becoming an issue and making it difficult "
				+ "to work a. The fort has already been relocated some distance "
				+ "a couple of times and I hope we don’t need to again. To make it all worse, I "
				+ "can’t believe one of the sentinels shot someone last night when they wouldn’t "
				+ "return the hail.”");
		landmarkList10.add("You write a letter to your father:\n"
				+ "“There has been much illness on the trail. Many of the soldiers have "
				+ "acquired sickness. Luckily, we have doctors traveling with us, but I "
				+ "help out as much as I am able. It pains me to see them strain themselves "
				+ "each day to make our travel goals, but they insist.”");
		
		ArrayList<String> landmarkList11 = new ArrayList<>();
		landmarkList11.add("Dr. Magruder tells you:\n"
				+ "“It’s almost that as soon as we arrived at Cottonwood Creek "
				+ "yesterday, there were nothing but awful storms raging throughout "
				+ "the night. The tents provided minimal protection from the "
				+ "elements, which didn’t make for the best of sleep...”");
		landmarkList11.add("You tell Mrs. Graham:\n"
				+ "“I feel ill thinking about all of the people that leave their homes "
				+ "and never reach their new one. All of the graves along the trail "
				+ "are heartbreaking. I pray that we all stay healthy the last bit of "
				+ "the trip. I'm not sure what I would do if I lost my husband or any of you.”");
		landmarkList11.add("A soldier tells you:\n"
				+ "“I have bought you and your husband some coffee, as a thanks for everything "
				+ "you have done for me on this trip. I should hardly have any clothes intact "
				+ "were it not for you, and I might've been left behind at a fort had you not "
				+ "dedicated yourself to caring for me while I was ill.”");
			
		ArrayList<String> landmarkList12 = new ArrayList<>();
		landmarkList12.add("A woman tells you:\n"
				+ "“I've just told my family that we are to float the rest of the journey down the "
				+ "Columbia River. Our oxen are sick, poor things, and we haven't the money for new "
				+ "ones nor the energy to walk and carry our belongings. I've heard the river is "
				+ "dangerous, but we do not have much of a choice. ”");
		landmarkList12.add("A letter from your father says:\n"
				+ "“I am glad to hear that you and your husband have taken good care of each "
				+ "other on your journey. I am sorry to hear of the losses you have witnessed, "
				+ "and the belongings you have lost. However, I am proud to hear how you much "
				+ "more yourself you became and the new interests you have found. Safe travels, "
				+ "my daughter.”");
		landmarkList12.add("You tell your husband:\n"
				+ "“I did not feel as though your soldiers respected me as an individual "
				+ "at the start of our trip. I feel they merely thought of me as your wife. "
				+ "But now, I think their opinion of me has changed, which I am glad for. "
				+ "I am blessed to have traveled with this group, and even more blessed that "
				+ "you and I are healthy.”");
		
		ArrayList<String> landmarkList13 = new ArrayList<>();
		landmarkList13.add("Mrs. Graham says to you: \n"
				+ "“Oh, you have been a wonderful companion across the trail! I will "
				+ "cherish your generosity and our time spent in the wagon together. "
				+ "Thank goodness we have made it. We will no longer need to worry about "
				+ "our tents blowing over at night or intense heat during the day. "
				+ " We may now set up a home!”");	
		landmarkList13.add("“Dr. McDougal says:"
				+ "“It has been a pleasure to travel with you and your husband. "
				+ "I know traveling the trail was difficult while pregnant, "
				+ "but you handled it marvelously. I will still be available "
				+ "for anything you need.”");
		landmarkList13.add("Your husband says to you:"
				+ "“We made it! This is where we'll raise our child. I could "
				+ "not be more grateful for us having made it here safely. "
				+ "We can start building our home.”");
		
		
		//Landmark 1 will be Chimney Rock and Landmark 2 will Fort Loramie 
		LandmarkConversations.put("Kansas River Crossing", landmarkList1);
		LandmarkConversations.put("Fort Kearney", landmarkList2);
		LandmarkConversations.put("Chimney Rock", landmarkList3);
		LandmarkConversations.put("Fort Laramie", landmarkList4);
		LandmarkConversations.put("Independence Rock", landmarkList5);
		LandmarkConversations.put("Green River Crossing", landmarkList6);
		LandmarkConversations.put("Soda Springs", landmarkList7);
		LandmarkConversations.put("Fort Hall", landmarkList8);
		LandmarkConversations.put("Snake River Crossing", landmarkList9);
		LandmarkConversations.put("Fort Boise", landmarkList10);
		LandmarkConversations.put("Blue Mountains", landmarkList11);
		LandmarkConversations.put("The Dalles", landmarkList12);
		LandmarkConversations.put("Willamette Valley, Oregon", landmarkList13);
		this.wagon = wagon;
		
	}
	/**
     * Calculates the distance to next landmark
     * @return milesToNextLandmark the miles to next landmark
     */ 
	public int distanceToNextLandmark() {
			//for each to iterate through the hashmap
		    for (Map.Entry<String, Integer> entry : Landmarks.entrySet()) {    
		        nextLandmarkLocation = entry.getValue();

		        //next landmark found 
		        if (nextLandmarkLocation > currentLocation) {
		            break;
		        }	
		        //set current landmark variable if a player has arrived
		        if (nextLandmarkLocation == currentLocation) {
		            currentLandmark = entry.getKey();

		            Landmarks.remove(currentLandmark);
		            break;
		        }
		    }
		    //calculate miles to next landmark
		    milesToNextLandmark = nextLandmarkLocation - currentLocation;
		    return milesToNextLandmark;
		}
	
	/**
	 * Gets the name of the current landmark
	 * @return Landmark the string value of the current landmark.
	 */
	public String getCurrentLandmark() {
		String Landmark = "Error";
		for (Map.Entry<String, Integer> entry : Landmarks.entrySet()) {
			Landmark = entry.getKey();
			break;
		}
		return Landmark;
	}
	
	/**
	 * gets the player's total traveled distance
	 * @return currentLocation the total distance traveled by the player.
	 */
	public int getCurrentLocation() {
		return currentLocation;
	}
	
	/**
	 * Sets the month variable in the Trail class
	 * @param month represents the current month with an integer.
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	
	/**
	 * Sets the foodRations variable in the Trail class
	 * @param foodRations amount of food consumed per person per day
	 */
	public void setFoodRations(int foodRations) {
		this.foodRations = foodRations;
	}
	
	/**
	 * used to get the food rations number
	 * @return pounds of food per person per day
	 */
	public int getFoodRations() {
		return foodRations;
	}
	
	/**
     * Handles the daily travel of the player
     * @return currentLocation the current location of the player
     */ 
	public int dailyTravel() {
	    if (milesToNextLandmark < travelSpeed) {
	        //update the current location to the next landmark
	        currentLocation = nextLandmarkLocation;
	        
	    } else {
	        //increment current location by travelSpeed
	        currentLocation += travelSpeed; 
	        //decrement miles to next landmark
	        milesToNextLandmark -= travelSpeed; 
	    }
	    return currentLocation;
	}
	
	/**
	 * gets the wagon's current travel speed
	 * @return travelSpeed integer value representing miles traveled per day
	 */
	public int getTravelSpeed() {
		return travelSpeed;
	}
	
	
	/**
     * Calculates the month
     * @param month value that represents string value of month
     * @return void 
     */ 
	public void calculateMonth() {
		//goes through each month and sets the appropriate string value
		switch(month) {
		case 1: 
			monthString = "January";
			break;
		case 2:
			monthString = "February";
			break;
		case 3: 
			monthString = "March";
			break;
		case 4: 
			monthString = "April";
			break;
		case 5:
			monthString = "May";
			break;
		case 6: 
			monthString = "June";
			break;
		case 7:
			monthString = "July";
			break;
		case 8:
			monthString = "August";
			break;
		case 9:
			monthString = "September";
			break;
		case 10:
			monthString = "October";
			break;
		case 11:
			monthString = "November";
			break;
		case 12:
			monthString = "December";
			break;
		}
	}
	
	/**
     * Calculates the date 
     * @return date the current date in the game 
     */ 
	public String calculateDate() {
		//arrays of the number of days in a month 
		int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		//sets the day value and month value 
	    while (day > daysInMonth[month - 1]) { 
	        day -= daysInMonth[month - 1]; 

	        month++;
	        if (month > 12) {
	            month = 1;
	        }
	    }
	    //gets the string month value 
	    calculateMonth();
	    //formats the date string 
	    String date = monthString + " " + Integer.toString(day) + ", 1848";
	    return date;
	}
	
	/**
	 * Increments the day variable in the Trail class.
	 */
	public void incrementDay() {
		this.day++;
	}
	/**
     * Handles random trail events using Random
     * @param frame the Jframe containing the gui
     * @return void 
     */ 
	public String randomTrailEvent(Health WagonMembers) {
		//creates the return string
		String text = "";
		//creates a random number
		Random rand = new Random();
		int random = rand.nextInt(32) + 1;
		//if the random number is a certain value, a certain random event will be called
		int randomWagonMemberIndex = rand.nextInt(WagonMembers.getWagonMembers().size());
		WagonMember randomWagonMember = WagonMembers.getWagonMembers().get(randomWagonMemberIndex);
		if(random == 2) {
			 randomWagonMember.takeDamage(10);
			 text = randomWagonMember.getName() + " has been bitten by a snake.";	
		}
		else if (random ==3) {
			text = "You found wild fruit!";	
			wagon.editSupplies("Food", "add", 40);
		}
		else if (random == 4) {
			randomWagonMember.takeDamage(20);
			text = randomWagonMember.getName() + " has broken their arm.";	
		}
		else if(random == 5) {
			randomWagonMember.takeDamage(50);
			text = randomWagonMember.getName() +  " has cholera.";	
		}
		else if(random == 6) {
			 for(WagonMember member: WagonMembers.getWagonMembers()) {
				 member.takeDamage(20);
			 }
			text = "Bad water.";	
		}
		else text = "";
		
		return text;
	}
	
	/**
	 * Decrements the wagon member variable when one of them dies
	 */
	public void decrementWagonMembers() {
		wagonMembers -= 1;
	}
	
	/**
	 * Updates the pounds of food per day
	 * @param Food the food item in the wagon
	 */
	public void updateFood(Item Food) {
		if (Food.getQuantity() >= foodRations) {
			Food.subtractQuantity(foodRations * wagonMembers);
		}
		else Food.setQuantity(0);
	}
	
	/**
	 * Gets the current landmark conversation
	 * @return currentConversation string containing the current conversation
	 */
	public String getConversation() {
		ArrayList<String> conversations = LandmarkConversations.get(currentLandmark);
        if (currentMessage > 2) {
        	currentMessage = 0;
        }
        String currentConversation = conversations.get(currentMessage);
        currentMessage++;
        
        return currentConversation;
	}
	
	/**
	 * Sets the travel speed variable in the Trail class
	 * @param travelSpeed amount of miles traveled per day
	 */
	public void setTravelSpeed(int travelSpeed) {
		this.travelSpeed = travelSpeed;
	}
	
	
} 