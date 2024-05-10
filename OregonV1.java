package trailv1.pkg;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
/**
 * OregonV1.java
 * 
 * @author Tyler Bond & Austin Hutton
 * @version 1.0
 * @since [4/2/2024]
 * 
 * This class handles every other class in the Oregon Trail project, and combines
 * them into one playable GUI. The GUI is accomplished with card layout, and
 * uses JTextFields and JButtons to get user input.
 */
public class OregonV1 implements ItemListener {
	JPanel cards; // a panel that uses card layout
	// String IDs for the various cards.
	final static String TITLESCREEN     = "Card with title";
	final static String DIFFICULTY      = "Card with difficulty";
	final static String PLAYERNAME      = "Card with player name";
	final static String WAGONNAME       = "Card with member names";
	final static String DEPARTURE       = "Card with departure text";
	final static String SHOPGUY         = "Card with shop guy";
	final static String SHOP            = "Card with shop";
	
	// Mid game cards
	final static String LOCATIONDISPLAY = "Card with location display";
	final static String TRAILACTIONS    = "Card with trail actions";
	final static String FORTACTIONS     = "Card with fort actions";
	final static String LANDMARKACTIONS = "Card with landmark actions";
	final static String RIVEROPTIONS    = "Card with river options";
	final static String TRAIL           = "Card with trail";
	
	// Trail Option Cards
	final static String TALKTOPEOPLE    = "Card with conversations";
	final static String CHECKSUPPLIES   = "Card with display of supplies";
	final static String MAP             = "Card with map";
	final static String TRADE           = "Card with trading";
	final static String FOODRATIONS     = "Card with food rations";
	final static String TRAVELSPEED     = "Card with travel speed";
	final static String STOPTOREST      = "Card with rest options";
	
	// River Cards
	final static String RIVER           = "Card with river information";
	final static String RIVERANIMATION  = "Card with river animation";
	final static String RIVERRESULT     = "Card with river result";
	
    // Misc text cards
	final static String TEXTCARD1       = "Card with Text 1";
	final static String TEXTCARD2       = "Card with Text 2";
	
	// End Game Cards
	final static String DEATH           = "Card with death screen";
	final static String VICTORY         = "Card with victory screen";
	
	// Shop Variables
	int horses;
	int clothing;
	int ammunition;
	int wagonWheel;
	int wagonAxel;
	int food;
	int wagonTongue;
	private int maxValue;
	private int totalHorseCost = 0;
	private int totalClothingCost = 0;
	private int totalBulletsOfFreedomCost = 0;
	private int totalWagonWheelCost = 0;
	private int totalWagonAxleCost = 0;
	private int totalPoundsOfFoodCost = 0;
	private int totalWagonToungeCost = 0;
	JTextField horseField;
	JTextField clothingField;
	JTextField bulletsOfFreedomField;
	JTextField spareWagonWheelField;
	JTextField spareWagonAxlesField;
	JTextField poundsOfFoodField;
	JTextField spareWagonToungeField;
	JLabel horseCostLbl;
	JLabel clothingCostLbl;
	JLabel bulletsOfFreedomCostLbl;
	JLabel spareWagonWheelCostLbl;
	JLabel spareWagonAxleCostLbl;
	JLabel spareWagonToungeCostLbl;
	JLabel poundsOfFoodCostLbl;
	JLabel totalCostLbl;
	JLabel lblBalance;
	
	// misc global variables
	public boolean endCard8    = false;
	public String errorMessage = "";
	
	// Wagon variables
	public Wagon Wagon      = new Wagon();
	public Item Horses      = new Item(0, "Horses");
	public Item Food        = new Item(0, "Food");
	public Item Clothing    = new Item(0, "Clothing");
	public Item Ammunition  = new Item(0, "Ammunition");
	public Item WagonWheel  = new Item(0, "WagonWheel");
	public Item WagonTongue = new Item(0, "WagonTongue");
	public Item WagonAxel   = new Item(0, "WagonAxel");
	
	// Trail variables
	Trail Trail                = new Trail(Wagon);
	JLabel lblTravelImage      = new JLabel("");
	JLabel lblDistanceTraveled = new JLabel("0");
	JLabel lblToNextLandmark   = new JLabel("110");
	JLabel lblDate             = new JLabel("[date]");
	JLabel lblLandmarkName     = new JLabel("");
	JLabel lblFoodRations      = new JLabel("Filling");
	JLabel lblFoodPounds       = new JLabel("0");
	JLabel lblTravelSpeed      = new JLabel("A Steady Pace");
	String trailEvent          = "";
	JLabel lblHealth           = new JLabel("Good");
	int totalHealth            = 0;
	int addedHealth            = 0;
	
	// Trail Option Variables
	String cardReturn        = TRAILACTIONS;
	JLabel lblHorsesSply     = new JLabel("0");
	JLabel lblClothingSply   = new JLabel("0");
	JLabel lblAmmunitionSply = new JLabel("0");
	JLabel lblFoodSply       = new JLabel("0");
	JLabel lblWheelSply      = new JLabel("0");
	JLabel lblTongueSply     = new JLabel("0");
	JLabel lblAxelSply       = new JLabel("0");
	JLabel lblMoneySply      = new JLabel("0");
	JLabel lblRest20         = new JLabel("[date]");
	JLabel lblMap            = new JLabel("");
	JTextArea txtroffer      = new JTextArea();
	JTextArea textArea       = new JTextArea();
	Trade NewTrader;
	
	// River Variables
	JLabel lblDepth         = new JLabel("[Depth]");
	JLabel lblFlowRate      = new JLabel("[Speed]");
	JLabel lblWidth         = new JLabel("[Width]");
	JLabel lblFerryPrice    = new JLabel("[Price]");
	JLabel lblresult        = new JLabel("[Result]");
	JLabel lblItemsLost     = new JLabel("[Items Lost]");
	JLabel lblItemsLost2    = new JLabel("[Items Lost]");
	JLabel lblRiverCrossing = new JLabel("");
	JTextArea textAreaRiver = new JTextArea();
	River River1            = new River("Kansas River Crossing", 110, 300, 3, 4, Wagon);
	River River2            = new River("Green River Crossing", 57, 400, 4, 8, Wagon);
	River River3            = new River("Snake River Crossing", 182, 500, 5, 13, Wagon);
	River River4            = new River("The Dalles", 125, 600, 7, 18, Wagon);
	River CurrentRiver;
	Timer Clock;
	boolean riverResult;
	int clockCycle = 0;
	
	// textCard return string
	public String textCard1 = "";
	public String textCard2 = "";
	
	// Create an empty player object
	public Player Player1 = new Player(0, 0, "Anna Maria Morris");
	
	// Create four empty wagon member objects and put them into an array for the Health class
	public WagonMember Member1 = new WagonMember("");
	public WagonMember Member2 = new WagonMember("");
	public WagonMember Member3 = new WagonMember("");
	public WagonMember Member4 = new WagonMember("");
	ArrayList<WagonMember> WagonMembers = new ArrayList<WagonMember>();
	Health WagonCrew = new Health(WagonMembers);
	
	// GUI and Image variables
	String filename2;
	String filename4;
	ImageIcon trail;
	
	/**
	 * Creates the card layout GUI, allowing the program to have multiple screens,
	 * also making the application much easier to program
	 * @param pane the JFrame that is created when the application runs
	 */
	public void addComponentToPane(Container pane) {
		
		// Pseudo Global Declarations:
		JTextArea txtrTextCard1 = new JTextArea();
		txtrTextCard1.setLineWrap(true);
		txtrTextCard1.setWrapStyleWord(true);
		
		// create the cards
		
		// TITLESCREEN
		JPanel card1 = new JPanel();
        card1.setBackground(new Color(0, 0, 0));
        card1.setLayout(null);
        // set size
        card1.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 1 START***
        
        JLabel lblTitleScreen = new JLabel("Oregon Trail");
        lblTitleScreen.setForeground(new Color(254, 255, 255));
        lblTitleScreen.setFont(new Font("Bodoni 72 Oldstyle", Font.BOLD, 50));
        lblTitleScreen.setBounds(217, 45, 298, 64);
        card1.add(lblTitleScreen);
        
        JLabel lblTravel = new JLabel("1 - Travel The Trail");
        lblTravel.setForeground(new Color(254, 255, 255));
        lblTravel.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblTravel.setBounds(134, 191, 278, 37);
        card1.add(lblTravel);
        
        JLabel lblLearn = new JLabel("");
        lblLearn.setForeground(new Color(254, 255, 255));
        lblLearn.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblLearn.setBounds(134, 240, 402, 37);
        card1.add(lblLearn);
        
        JLabel lblResponse = new JLabel("Your Response: ");
        lblResponse.setForeground(new Color(254, 255, 255));
        lblResponse.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblResponse.setBounds(134, 401, 210, 37);
        card1.add(lblResponse);
        
        textField = new JTextField();
        textField.setBackground(new Color(0, 0, 0));
        textField.setForeground(new Color(254, 255, 255));
        textField.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        textField.setBounds(342, 401, 70, 36);
        card1.add(textField);
        textField.setColumns(10);
        
        JLabel lblNO = new JLabel("");
        lblNO.setForeground(new Color(255, 38, 0));
        lblNO.setFont(new Font("Chalkboard", Font.BOLD, 50));
        lblNO.setBounds(548, 215, 140, 93);
        card1.add(lblNO);
        
        // ***CARD 1 END***
        
        // CARD 1 MAPPING EVENTS
        
        JLabel lblWagonLeader_1 = new JLabel("");
        
        JButton btnEnter = new JButton("ENTER");
        btnEnter.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if (textField.getText().equals("1")) {
        			CardLayout cl = (CardLayout)(cards.getLayout());
            		cl.show(cards, DIFFICULTY);
            		lblNO.setText("");
        		}
        		
        		if (textField.getText().equals("42")) {
        			quickStart();
        		}
        		
        		// Sets the player name
        		lblWagonLeader_1.setText(Player1.getName());
        	}
        });
        btnEnter.setForeground(new Color(0, 0, 0));
        btnEnter.setBackground(new Color(254, 255, 255));
        btnEnter.setBounds(424, 401, 98, 37);
        card1.add(btnEnter);
        
        //DIFFICULTY
        JPanel card2 = new JPanel();
        card2.setBackground(new Color(0, 0, 0));
        card2.setLayout(null);
        //Sets size
        card2.setPreferredSize(new Dimension(500, 500));
        
        // ***CARD 2 START***
        
        JLabel lblNewLabel = new JLabel("You are married into a US militairy family");
        lblNewLabel.setForeground(new Color(254, 255, 255));
        lblNewLabel.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblNewLabel.setBounds(78, 48, 666, 44);
        card2.add(lblNewLabel);
        
        JLabel lblTravellingToOregon = new JLabel("travelling to Oregon.");
        lblTravellingToOregon.setForeground(new Color(254, 255, 255));
        lblTravellingToOregon.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblTravellingToOregon.setBounds(78, 91, 666, 44);
        card2.add(lblTravellingToOregon);
        
        JLabel lblYouMay = new JLabel("Is your husband a(n)...");
        lblYouMay.setForeground(new Color(254, 255, 255));
        lblYouMay.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblYouMay.setBounds(78, 130, 309, 70);
        card2.add(lblYouMay);
        
        JLabel lblBe = new JLabel("1 - Officer");
        lblBe.setForeground(new Color(254, 255, 255));
        lblBe.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblBe.setBounds(150, 212, 378, 34);
        card2.add(lblBe);
        
        JLabel lblBe_2 = new JLabel("2 - Captain");
        lblBe_2.setForeground(new Color(254, 255, 255));
        lblBe_2.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblBe_2.setBounds(150, 252, 386, 34);
        card2.add(lblBe_2);
        
        JLabel lblBe_1 = new JLabel("3 - Soldier");
        lblBe_1.setForeground(new Color(254, 255, 255));
        lblBe_1.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblBe_1.setBounds(150, 292, 386, 34);
        card2.add(lblBe_1);
        
        JLabel lblFind = new JLabel("4 - Find out the differences");
        lblFind.setForeground(new Color(254, 255, 255));
        lblFind.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblFind.setBounds(150, 335, 358, 34);
        card2.add(lblFind);
        
        JLabel lblBetweenTheseChoices = new JLabel("between these choices");
        lblBetweenTheseChoices.setForeground(new Color(254, 255, 255));
        lblBetweenTheseChoices.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblBetweenTheseChoices.setBounds(204, 362, 358, 34);
        card2.add(lblBetweenTheseChoices);
        
        JLabel lblResponse_1 = new JLabel("Your Response: ");
        lblResponse_1.setForeground(new Color(254, 255, 255));
        lblResponse_1.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblResponse_1.setBounds(132, 424, 210, 37);
        card2.add(lblResponse_1);
        
        textField_1 = new JTextField();
        textField_1.setForeground(new Color(254, 255, 255));
        textField_1.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        textField_1.setColumns(10);
        textField_1.setBackground(Color.BLACK);
        textField_1.setBounds(342, 424, 70, 36);
        card2.add(textField_1);
        
        // ***CARD 2 END***
        
        // CARD 2 MAPPING EVENTS
        // WILL HAVE TO CHANGE TO KEYBOARD EVENTS
        
        JButton btnEnter_1 = new JButton("ENTER");
        btnEnter_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (textField_1.getText().equals("1")) {
        			CardLayout cl = (CardLayout)(cards.getLayout());
            		cl.show(cards, WAGONNAME);
            		// Set player information
            		Player1.setBalance(1600);
            		Player1.setMultiplier(1);
        		}
        		
        		if (textField_1.getText().equals("2")) {
        			CardLayout cl = (CardLayout)(cards.getLayout());
            		cl.show(cards, WAGONNAME);
            		// Set player information
            		Player1.setBalance(1000);
            		Player1.setMultiplier(2);
        		}
        		
        		if (textField_1.getText().equals("3")) {
        			CardLayout cl = (CardLayout)(cards.getLayout());
            		cl.show(cards, WAGONNAME);
            		// Set player information
            		Player1.setBalance(500);
            		Player1.setMultiplier(3);
        		}
        		
        		if (textField_1.getText().equals("4")) {
        			CardLayout cl = (CardLayout)(cards.getLayout());
            		cl.show(cards, TEXTCARD1);
            		txtrTextCard1.setText("Traveling to Oregon isn't easy!  But if you're married to an Officer, "
            				+             "you'll have more money than a Captain or Soldier, and you'll have your own tent."
            				+             " However, the harder you have to try, the more "
            				+             "points you deserve! Therefore, the Soldier earns the greatest number"
            				+             " of points and the Officer earns the least.");
            		// Tells textCard what card to return back to.
            		textCard1 = "Card with difficulty";
        		}
        	}
        });
        btnEnter_1.setForeground(Color.BLACK);
        btnEnter_1.setBackground(new Color(254, 255, 255));
        btnEnter_1.setBounds(424, 424, 98, 37);
        card2.add(btnEnter_1);
        
        //PLAYERNAME
        JPanel card3 = new JPanel();
        card3.setBackground(new Color(0, 0, 0));
        card3.setLayout(null);
        // set size
        card3.setPreferredSize(new Dimension(750, 500));

		// ***CARD 3 START***
        
        JLabel lblImage01 = new JLabel("");
        String filename = "/images/trailImage.png";
        ImageIcon trailImage = new ImageIcon(this.getClass().getResource(filename));
        lblImage01.setIcon(trailImage);
        lblImage01.setForeground(new Color(254, 255, 255));
        lblImage01.setBounds(6, 52, 738, 198);
        card3.add(lblImage01);
        
        JLabel lblWhatIsThe = new JLabel("What is the first name of the");
        lblWhatIsThe.setForeground(new Color(254, 255, 255));
        lblWhatIsThe.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblWhatIsThe.setBounds(57, 262, 377, 39);
        card3.add(lblWhatIsThe);
        
        JLabel lblWagonLeader = new JLabel("wagon leader?");
        lblWagonLeader.setForeground(new Color(254, 255, 255));
        lblWagonLeader.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblWagonLeader.setBounds(57, 290, 179, 39);
        card3.add(lblWagonLeader);
        
        nameField = new JTextField();
        nameField.setForeground(new Color(254, 255, 255));
        nameField.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        nameField.setBackground(new Color(0, 0, 0));
        nameField.setBounds(250, 291, 184, 35);
        card3.add(nameField);
        nameField.setColumns(10);
        
        // ***CARD 3 END***
        
        // CARD 3 MAPPING EVENTS
        // WILL HAVE TO CHANGE TO KEYBOARD EVENTS
        
        JButton btnENTER = new JButton("ENTER");
        btnENTER.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Set player name
        		Player1.setName(nameField.getText());
        		// Display WagonName card
        		lblWagonLeader_1.setText(Player1.getName());
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, WAGONNAME);
        	}
        });
        btnENTER.setBounds(446, 290, 93, 38);
        card3.add(btnENTER);
        
        // TEXTCARD1
        JPanel card4 = new JPanel();
        card4.setBackground(new Color(0, 0, 0));
        card4.setLayout(null);
        // set size
        card4.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 4 START***
        
        txtrTextCard1.setEditable(false);
        txtrTextCard1.setForeground(new Color(254, 255, 255));
        txtrTextCard1.setBackground(new Color(0, 0, 0));
        txtrTextCard1.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        txtrTextCard1.setText("");
        txtrTextCard1.setBounds(57, 65, 633, 349);
        card4.add(txtrTextCard1);
        
        // ***CARD 4 END***
        
        // CARD 4 MAPPING EVENTS
        // WILL HAVE TO CHANGE TO KEYBOARD EVENTS
        
        JButton btnEnter_2 = new JButton("ENTER");
        btnEnter_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, textCard1);
        		// Clears text area for next use
        		txtrTextCard1.setText("");
        	}
        });
        btnEnter_2.setForeground(Color.BLACK);
        btnEnter_2.setBackground(new Color(254, 255, 255));
        btnEnter_2.setBounds(325, 436, 98, 37);
        card4.add(btnEnter_2);
        
        // WAGONNAME
        JPanel card5 = new JPanel();
        card5.setBackground(new Color(0, 0, 0));
        card5.setLayout(null);
        // set Size
        card5.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 5 START***
        
        JLabel lblImage01_1 = new JLabel("");
        lblImage01_1.setForeground(new Color(254, 255, 255));
        lblImage01_1.setBounds(6, 52, 738, 198);
        lblImage01_1.setIcon(trailImage);
        card5.add(lblImage01_1);
        
        JLabel lblWhatIsThe_1 = new JLabel("What are the first names of the");
        lblWhatIsThe_1.setForeground(new Color(254, 255, 255));
        lblWhatIsThe_1.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblWhatIsThe_1.setBounds(57, 262, 409, 39);
        card5.add(lblWhatIsThe_1);
        
        JLabel lblText = new JLabel("four other members in your party?");
        lblText.setForeground(new Color(254, 255, 255));
        lblText.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblText.setBounds(57, 293, 442, 39);
        card5.add(lblText);
        
        JLabel lblNewLabel_01 = new JLabel("1.");
        lblNewLabel_01.setForeground(new Color(254, 255, 255));
        lblNewLabel_01.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblNewLabel_01.setBounds(57, 331, 29, 39);
        card5.add(lblNewLabel_01);
        
        lblWagonLeader_1.setForeground(new Color(254, 255, 255));
        lblWagonLeader_1.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblWagonLeader_1.setBounds(98, 331, 370, 39);
        card5.add(lblWagonLeader_1);
        
        JLabel lblNewLabel_01_1 = new JLabel("2.");
        lblNewLabel_01_1.setForeground(new Color(254, 255, 255));
        lblNewLabel_01_1.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblNewLabel_01_1.setBounds(57, 360, 29, 39);
        card5.add(lblNewLabel_01_1);
        
        JLabel lblNewLabel_01_2 = new JLabel("3.");
        lblNewLabel_01_2.setForeground(new Color(254, 255, 255));
        lblNewLabel_01_2.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblNewLabel_01_2.setBounds(57, 393, 29, 39);
        card5.add(lblNewLabel_01_2);
        
        JLabel lblNewLabel_01_3 = new JLabel("4.");
        lblNewLabel_01_3.setForeground(new Color(254, 255, 255));
        lblNewLabel_01_3.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblNewLabel_01_3.setBounds(57, 423, 29, 39);
        card5.add(lblNewLabel_01_3);
        
        JLabel lblNewLabel_01_4 = new JLabel("5.");
        lblNewLabel_01_4.setForeground(new Color(254, 255, 255));
        lblNewLabel_01_4.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblNewLabel_01_4.setBounds(57, 456, 29, 39);
        card5.add(lblNewLabel_01_4);
        
        member1Field = new JTextField();
        member1Field.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        member1Field.setForeground(new Color(254, 255, 255));
        member1Field.setBackground(new Color(0, 0, 0));
        member1Field.setBounds(98, 365, 209, 28);
        card5.add(member1Field);
        member1Field.setColumns(10);
        
        member2Field = new JTextField();
        member2Field.setForeground(new Color(254, 255, 255));
        member2Field.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        member2Field.setColumns(10);
        member2Field.setBackground(Color.BLACK);
        member2Field.setBounds(98, 398, 209, 28);
        card5.add(member2Field);
        
        member3Field = new JTextField();
        member3Field.setForeground(new Color(254, 255, 255));
        member3Field.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        member3Field.setColumns(10);
        member3Field.setBackground(Color.BLACK);
        member3Field.setBounds(96, 432, 209, 28);
        card5.add(member3Field);
        
        member4Field = new JTextField();
        member4Field.setForeground(new Color(254, 255, 255));
        member4Field.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        member4Field.setColumns(10);
        member4Field.setBackground(Color.BLACK);
        member4Field.setBounds(96, 461, 209, 28);
        card5.add(member4Field);
        
        // ***CARD 5 END***
        
        // CARD 5 MAPPING EVENTS
        // WILL HAVE TO CHANGE TO KEYBOARD EVENTS
        
        JButton btnENTER_1 = new JButton("ENTER");
        btnENTER_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Set Names from TextFields
        		Member1.setName(member1Field.getText());
        		Member2.setName(member2Field.getText());
        		Member3.setName(member3Field.getText());
        		Member4.setName(member4Field.getText());
        		// Show next card
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, DEPARTURE);
        	}
        });
        btnENTER_1.setBounds(317, 460, 93, 38);
        card5.add(btnENTER_1);
        
        // DEPARTURE
        JPanel card6 = new JPanel();
        card6.setBackground(new Color(0, 0, 0));
        card6.setLayout(null);
        // set Size
        card6.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 6 START***
        
        JTextArea txtrItIs = new JTextArea();
        txtrItIs.setLineWrap(true);
        txtrItIs.setText("It is 1848. Your jumping off place for Oregon is Independence, Missouri. You must decide which month to leave Independence.");
        txtrItIs.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        txtrItIs.setForeground(new Color(254, 255, 255));
        txtrItIs.setBackground(new Color(0, 0, 0));
        txtrItIs.setBounds(54, 63, 640, 114);
        card6.add(txtrItIs);
        
        JLabel lblNewLabel_1 = new JLabel("1. March");
        lblNewLabel_1.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblNewLabel_1.setForeground(new Color(254, 255, 255));
        lblNewLabel_1.setBounds(107, 207, 200, 27);
        card6.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("2. April");
        lblNewLabel_1_1.setForeground(new Color(254, 255, 255));
        lblNewLabel_1_1.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblNewLabel_1_1.setBounds(107, 233, 200, 27);
        card6.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_2 = new JLabel("3. May");
        lblNewLabel_1_2.setForeground(new Color(254, 255, 255));
        lblNewLabel_1_2.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblNewLabel_1_2.setBounds(107, 260, 200, 27);
        card6.add(lblNewLabel_1_2);
        
        JLabel lblNewLabel_1_3 = new JLabel("4. June");
        lblNewLabel_1_3.setForeground(new Color(254, 255, 255));
        lblNewLabel_1_3.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblNewLabel_1_3.setBounds(107, 287, 200, 27);
        card6.add(lblNewLabel_1_3);
        
        JLabel lblNewLabel_1_4 = new JLabel("5. July");
        lblNewLabel_1_4.setForeground(new Color(254, 255, 255));
        lblNewLabel_1_4.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblNewLabel_1_4.setBounds(107, 314, 200, 27);
        card6.add(lblNewLabel_1_4);
        
        JLabel lblNewLabel_1_5 = new JLabel("6. Ask for Advice");
        lblNewLabel_1_5.setForeground(new Color(254, 255, 255));
        lblNewLabel_1_5.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblNewLabel_1_5.setBounds(107, 339, 229, 27);
        card6.add(lblNewLabel_1_5);
        
        JLabel lblResponse_1_1 = new JLabel("Your Response: ");
        lblResponse_1_1.setForeground(new Color(254, 255, 255));
        lblResponse_1_1.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblResponse_1_1.setBounds(88, 444, 210, 37);
        card6.add(lblResponse_1_1);
        
        textField_2 = new JTextField();
        textField_2.setForeground(new Color(254, 255, 255));
        textField_2.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        textField_2.setColumns(10);
        textField_2.setBackground(Color.BLACK);
        textField_2.setBounds(289, 445, 70, 36);
        card6.add(textField_2);
        
        JTextArea txtrTextCard2 = new JTextArea();
        txtrTextCard2.setLineWrap(true);
        txtrTextCard2.setWrapStyleWord(true);
        
        // ***CARD 6 END***
        
        // CARD 6 MAPPING EVENTS
        // WILL HAVE TO CHANGE TO KEYBOARD EVENTS
        
        JButton btnEnter_1_1 = new JButton("ENTER");
        btnEnter_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String response = textField_2.getText();
        		if (!response.equals("6")) {
        			if (response.equals("1")) {
        				Trail.setMonth(3);
        			}
        			if (response.equals("2")) {
        				Trail.setMonth(4);
        			}
        			if (response.equals("3")) {
        				Trail.setMonth(5);
        			}
        			if (response.equals("4")) {
        				Trail.setMonth(6);
        			}
        			if (response.equals("5")) {
        				Trail.setMonth(7);
        			}
        			CardLayout cl = (CardLayout)(cards.getLayout());
            		cl.show(cards, TEXTCARD1);
        			txtrTextCard1.setText("Before leaving Independence you should buy equipment and supplies. You have $"
        					+ Integer.toString(Player1.getBalance())
        					+ " in cash, but you don't have to spend it all now.");
        			textCard1 = "Card with Text 2";
        			txtrTextCard2.setText("You can buy whatever you need at Matt's General Store.");
        			textCard2 = "Card with shop guy";
        		}
        		else {
        			CardLayout cl = (CardLayout)(cards.getLayout());
            		cl.show(cards, TEXTCARD1);
        			txtrTextCard1.setText("You attend a public meeting held for 'folks with the California - Oregon fever.' You're told:"
        					+ " If you leave too early, there won't be any grass for your oxen to eat. If you leave too late, you may not"
        					+ " get to Oregon before winter comes. If you leave at just the right time, there will be green grass and the"
        					+ " weather will still be cool.");
        			textCard1 = "Card with departure text";
        		}
        	}
        });
        btnEnter_1_1.setForeground(Color.BLACK);
        btnEnter_1_1.setBackground(new Color(254, 255, 255));
        btnEnter_1_1.setBounds(364, 444, 98, 37);
        card6.add(btnEnter_1_1);
        
        // TEXTCARD2
        JPanel card7 = new JPanel();
        card7.setBackground(new Color(0, 0, 0));
        card7.setLayout(null);
        // set Size
        card7.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 7 START***
        
        txtrTextCard2.setEditable(false);
        txtrTextCard2.setForeground(new Color(254, 255, 255));
        txtrTextCard2.setBackground(new Color(0, 0, 0));
        txtrTextCard2.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        txtrTextCard2.setText("");
        txtrTextCard2.setBounds(57, 65, 633, 349);
        card7.add(txtrTextCard2);
        
        // **CARD 7 END***
        
        // CARD 7 MAPPING EVENTS
        // WILL HAVE TO CHANGE TO KEYBOARD EVENTS
        
        JButton btnEnter_7 = new JButton("ENTER");
        btnEnter_7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, textCard2);
        		// Clears text area for next use
        		txtrTextCard2.setText("");
        	}
        });
        btnEnter_7.setForeground(Color.BLACK);
        btnEnter_7.setBackground(new Color(254, 255, 255));
        btnEnter_7.setBounds(325, 436, 98, 37);
        card7.add(btnEnter_7);
        
        // SHOPGUY
        JPanel card8 = new JPanel();
        card8.setBackground(new Color(0, 0, 0));
        card8.setLayout(null);
        // set Size
        card8.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 8 START***
        
        JTextArea txtrHelloIAm = new JTextArea();
        txtrHelloIAm.setWrapStyleWord(true);
        txtrHelloIAm.setText("Hello, I am Matt. So you're going to Oregon! I can  fix you up with what you need.");
        txtrHelloIAm.setLineWrap(true);
        txtrHelloIAm.setForeground(new Color(254, 255, 255));
        txtrHelloIAm.setBackground(new Color(0, 0, 0));
        txtrHelloIAm.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        txtrHelloIAm.setBounds(22, 18, 708, 79);
        card8.add(txtrHelloIAm);
        
        JLabel lblMatt = new JLabel("New label");
        String filename0 = "/images/Matt.png";
        ImageIcon mattImage = new ImageIcon(this.getClass().getResource(filename0));
        lblMatt.setIcon(mattImage);
        lblMatt.setBounds(48, 32, 179, 403);
        card8.add(lblMatt);
        
        JLabel lblItem1 = new JLabel("- A team of oxen to pull your wagon");
        lblItem1.setForeground(new Color(254, 255, 255));
        lblItem1.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblItem1.setBounds(189, 166, 470, 27);
        card8.add(lblItem1);
        
        JLabel lblItem2 = new JLabel("- Clothing for both summer and winter");
        lblItem2.setForeground(new Color(254, 255, 255));
        lblItem2.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblItem2.setBounds(189, 253, 500, 27);
        card8.add(lblItem2);
        
        JLabel lblItem3 = new JLabel("");
        lblItem3.setForeground(new Color(254, 255, 255));
        lblItem3.setFont(new Font("AppleMyungjo", Font.BOLD, 28));
        lblItem3.setBounds(189, 338, 500, 27);
        card8.add(lblItem3);
        
        // ***CARD 8 END***
        
        // CARD 8 MAPPING EVENTS
        // WILL HAVE TO CHANGE TO KEYBOARD EVENTS
        
        JButton btnEnter_8 = new JButton("ENTER");
        btnEnter_8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!endCard8) {
        			lblItem1.setText("- Plenty of food for the trip");
        			lblItem2.setText("- Ammunition for your rifles");
        			lblItem3.setText("- Spare parts for your wagon");
        			card8switch();
        		}
        		else {
        			lblBalance.setText("$" + Player1.getBalance() + ".00");
        			CardLayout cl = (CardLayout)(cards.getLayout());
            		cl.show(cards, SHOP);
        		}
        	}
        });
        btnEnter_8.setForeground(Color.BLACK);
        btnEnter_8.setBackground(new Color(254, 255, 255));
        btnEnter_8.setBounds(325, 436, 98, 37);
        card8.add(btnEnter_8);
        
        // SHOP
        JPanel card9 = new JPanel();
        card9.setBackground(new Color(0, 0, 0));
        card9.setLayout(null);
        // set Size
        card9.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 9 START***
    	
    	Wagon.createLoadedWagon(Horses, Food, Clothing, Ammunition, WagonTongue, WagonWheel, WagonAxel);
        
        JLabel lblNewLabell = new JLabel("Horses\r\n");
		lblNewLabell.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabell.setForeground(new Color(254, 255, 255));
		lblNewLabell.setBounds(223, 90, 106, 32);
		card9.add(lblNewLabell);
		
		JLabel lblNewLabell_1 = new JLabel("Items\r\n");
		lblNewLabell_1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabell_1.setForeground(new Color(254, 255, 255));
		lblNewLabell_1.setBounds(223, 52, 106, 27);
		card9.add(lblNewLabell_1);
		
		JLabel lblNewLabel_2 = new JLabel("Max.");
		lblNewLabel_2.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_2.setForeground(new Color(254, 255, 255));
		lblNewLabel_2.setBounds(33, 49, 62, 32);
		card9.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Buy");
		lblNewLabel_3.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_3.setForeground(new Color(254, 255, 255));
		lblNewLabel_3.setBounds(122, 52, 49, 26);
		card9.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("20");
		lblNewLabel_4.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_4.setForeground(new Color(254, 255, 255));
		lblNewLabel_4.setBounds(43, 90, 49, 32);
		card9.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("50");
		lblNewLabel_5.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_5.setForeground(new Color(254, 255, 255));
		lblNewLabel_5.setBounds(42, 135, 40, 27);
		card9.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Clothing");
		lblNewLabel_6.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_6.setForeground(new Color(254, 255, 255));
		lblNewLabel_6.setBounds(223, 134, 108, 32);
		card9.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("2000");
		lblNewLabel_7.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_7.setForeground(new Color(254, 255, 255));
		lblNewLabel_7.setBounds(33, 173, 62, 27);
		card9.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Ammunition");
		lblNewLabel_8.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_8.setForeground(new Color(254, 255, 255));
		lblNewLabel_8.setBounds(223, 170, 240, 32);
		card9.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("3");
		lblNewLabel_9.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_9.setForeground(new Color(254, 255, 255));
		lblNewLabel_9.setBounds(46, 211, 49, 27);
		card9.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Spare Wagon Wheel");
		lblNewLabel_10.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_10.setForeground(new Color(254, 255, 255));
		lblNewLabel_10.setBounds(223, 208, 238, 32);
		card9.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("3");
		lblNewLabel_11.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_11.setForeground(new Color(254, 255, 255));
		lblNewLabel_11.setBounds(46, 249, 49, 27);
		card9.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Spare Wagon Axles");
		lblNewLabel_12.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_12.setForeground(new Color(254, 255, 255));
		lblNewLabel_12.setBounds(223, 252, 238, 32);
		card9.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("2000");
		lblNewLabel_13.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_13.setForeground(new Color(254, 255, 255));
		lblNewLabel_13.setBounds(33, 330, 62, 26);
		card9.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Pounds of Food\r\n");
		lblNewLabel_14.setForeground(new Color(254, 255, 255));
		lblNewLabel_14.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_14.setBounds(223, 330, 238, 27);
		card9.add(lblNewLabel_14);
		
		horseCostLbl = new JLabel("0");
		horseCostLbl.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		horseCostLbl.setForeground(new Color(254, 255, 255));
		horseCostLbl.setBounds(606, 95, 106, 23);
		card9.add(horseCostLbl);
		
		clothingCostLbl = new JLabel("0");
		clothingCostLbl.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		clothingCostLbl.setForeground(new Color(254, 255, 255));
		clothingCostLbl.setBounds(606, 137, 106, 23);
		card9.add(clothingCostLbl);
		
		bulletsOfFreedomCostLbl = new JLabel("0");
		bulletsOfFreedomCostLbl.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		bulletsOfFreedomCostLbl.setForeground(new Color(254, 255, 255));
		bulletsOfFreedomCostLbl.setBounds(606, 175, 106, 23);
		card9.add(bulletsOfFreedomCostLbl);
		
		spareWagonWheelCostLbl = new JLabel("0");
		spareWagonWheelCostLbl.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		spareWagonWheelCostLbl.setForeground(new Color(254, 255, 255));
		spareWagonWheelCostLbl.setBounds(606, 213, 106, 23);
		card9.add(spareWagonWheelCostLbl);
		
		spareWagonAxleCostLbl = new JLabel("0");
		spareWagonAxleCostLbl.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		spareWagonAxleCostLbl.setForeground(new Color(254, 255, 255));
		spareWagonAxleCostLbl.setBounds(606, 251, 106, 23);
		card9.add(spareWagonAxleCostLbl);
		
		spareWagonToungeCostLbl = new JLabel("0");
		spareWagonToungeCostLbl.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		spareWagonToungeCostLbl.setForeground(new Color(254, 255, 255));
		spareWagonToungeCostLbl.setBounds(606, 287, 106, 23);
		card9.add(spareWagonToungeCostLbl);
		
		poundsOfFoodCostLbl = new JLabel("0");
		poundsOfFoodCostLbl.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		poundsOfFoodCostLbl.setForeground(new Color(254, 255, 255));
		poundsOfFoodCostLbl.setBounds(606, 332, 106, 23);
		card9.add(poundsOfFoodCostLbl);
		
		totalCostLbl = new JLabel("$0.00");
		totalCostLbl.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		totalCostLbl.setForeground(new Color(254, 255, 255));
		totalCostLbl.setBounds(586, 393, 158, 27);
		card9.add(totalCostLbl);
		
		JLabel lblNewLabel_14_1 = new JLabel("You Have:");
        lblNewLabel_14_1.setForeground(new Color(254, 255, 255));
        lblNewLabel_14_1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel_14_1.setBounds(61, 382, 117, 27);
        card9.add(lblNewLabel_14_1);
        
        lblBalance = new JLabel("0");
        lblBalance.setForeground(new Color(254, 255, 255));
        lblBalance.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblBalance.setBounds(61, 421, 117, 27);
        card9.add(lblBalance);
		
		horseField = new JTextField();
		horseField.setForeground(new Color(254, 255, 255));
		horseField.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		horseField.setBackground(new Color(0, 0, 0));
		horseField.setText("0");
		horseField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checks if the user inputs the correct amount of horses.
				String maxHorseFieldString = horseField.getText();
				maxValue = Integer.parseInt(maxHorseFieldString);
				if(maxValue > 20) {
					errorMessage = "Can't carry more than 20 Horses.";
					errorMessage(errorMessage);
				}
			}
		});
		horseField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Calculates the price of the horse and updates it into totalCost.
				if(e.getKeyCode() == 10) {
					String horseCostString = horseField.getText();
					horses = Integer.parseInt(horseCostString);
					totalHorseCost = horses * 20;
					if(horses > 20) {
						horseCostLbl.setText("Invalid");
					}
					else {
						horseCostLbl.setText("" + totalHorseCost);
						totalCostLbl.setText("$" + update() + ".00");
					}
				}	
				
				
			}
		});
		horseField.setBounds(102, 90, 96, 30);
		card9.add(horseField);
		horseField.setColumns(10);
		
		clothingField = new JTextField();
		clothingField.setForeground(new Color(254, 255, 255));
		clothingField.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		clothingField.setBackground(new Color(0, 0, 0));
		clothingField.setText("0");
		clothingField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Checks if the user input the correct amount of clothing sets.
				String maxClothingFieldString = clothingField.getText();
				maxValue = Integer.parseInt(maxClothingFieldString);
				if(maxValue > 50) {
					errorMessage = "Can't carry more than 50 sets of clothing.";
					errorMessage(errorMessage);
				}
			}
			
		});
		clothingField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Calculates the cost of the clothing sets and then updates the totalCost.
				if(e.getKeyCode() == 10) {
					String clothingCostString = clothingField.getText();
					clothing = Integer.parseInt(clothingCostString);
					totalClothingCost = clothing * 10;
					if(clothing > 50) {
						clothingCostLbl.setText("Invalid");
					}
					else {
						clothingCostLbl.setText("" + totalClothingCost);
						totalCostLbl.setText("$" + update() + ".00");
					}
				}
			}
		});
		clothingField.setBounds(102, 135, 96, 27);
		card9.add(clothingField);
		clothingField.setColumns(10);
		
		bulletsOfFreedomField = new JTextField();
		bulletsOfFreedomField.setForeground(new Color(254, 255, 255));
		bulletsOfFreedomField.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		bulletsOfFreedomField.setBackground(new Color(0, 0, 0));
		bulletsOfFreedomField.setText("0");
		bulletsOfFreedomField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checks to see if the user input the correct amount of bullets of freedom.
				String maxBulletsOfFreedomString = bulletsOfFreedomField.getText();
				maxValue = Integer.parseInt(maxBulletsOfFreedomString);
				if(maxValue > 2000) {
					errorMessage = "Can't have more than 2000 ammunition.";
					errorMessage(errorMessage);
				}
			}
		});
		bulletsOfFreedomField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Calculates the cost of the bullets of freedom and then updates the totalCost.
				if(e.getKeyCode() == 10) {
				String bulletsOfFreedomCostString = bulletsOfFreedomField.getText();
				ammunition = Integer.parseInt(bulletsOfFreedomCostString);
				totalBulletsOfFreedomCost = ammunition * 2;
				if(ammunition > 2000) {
					bulletsOfFreedomCostLbl.setText("Invalid");
				}
				else {
					bulletsOfFreedomCostLbl.setText("" + totalBulletsOfFreedomCost); 
					totalCostLbl.setText("$" + update() + ".00");
				}
				}
			}
		});
		bulletsOfFreedomField.setBounds(102, 173, 96, 27);
		card9.add(bulletsOfFreedomField);
		bulletsOfFreedomField.setColumns(10);
		
		spareWagonWheelField = new JTextField();
		spareWagonWheelField.setForeground(new Color(254, 255, 255));
		spareWagonWheelField.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		spareWagonWheelField.setBackground(new Color(0, 0, 0));
		spareWagonWheelField.setText("0");
		spareWagonWheelField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checks to see if the user input the correct amount of wagon wheels.
				String maxSpareWaonWheelString = spareWagonWheelField.getText();
				maxValue = Integer.parseInt(maxSpareWaonWheelString);
				if(maxValue > 3) {
					errorMessage = "Can't carry more than 3 wagon wheels.";
					errorMessage(errorMessage);
				}
			}
		});
		spareWagonWheelField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Calculates the cost of the wagon wheel and then updates totalCost.
				if(e.getKeyCode() == 10) {
				String spareWagonWheelCostString = spareWagonWheelField.getText();
				wagonWheel = Integer.parseInt(spareWagonWheelCostString);
				totalWagonWheelCost = wagonWheel * 10;
				if(wagonWheel > 3) {
					spareWagonWheelCostLbl.setText("Invalid");
				}
				else {
					spareWagonWheelCostLbl.setText("" + totalWagonWheelCost);
					totalCostLbl.setText("$" + update() + ".00");
				}
				}
			}
		});
		spareWagonWheelField.setBounds(102, 211, 96, 27);
		card9.add(spareWagonWheelField);
		spareWagonWheelField.setColumns(10);
		
		spareWagonAxlesField = new JTextField();
		spareWagonAxlesField.setForeground(new Color(254, 255, 255));
		spareWagonAxlesField.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		spareWagonAxlesField.setBackground(new Color(0, 0, 0));
		spareWagonAxlesField.setText("0");
		spareWagonAxlesField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checks to see if user input the correct number of wagon axle.
				String maxSpareWagonAxlesString = spareWagonAxlesField.getText();
				maxValue = Integer.parseInt(maxSpareWagonAxlesString);
				if(maxValue > 3) {
					errorMessage = "Can't carry more than 3 wagon axles.";
					errorMessage(errorMessage);
				}
				
			}
		});
		spareWagonAxlesField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Calculates the cost of the wagon axle and then updates the totalCost
				if(e.getKeyCode() == 10) {
				String spareWagonAxleString = spareWagonAxlesField.getText();
				wagonAxel = Integer.parseInt(spareWagonAxleString);
				totalWagonAxleCost = wagonAxel * 10;
				if(wagonAxel > 3) {
					spareWagonAxleCostLbl.setText("Invalid");
				}
				else {
					spareWagonAxleCostLbl.setText("" + totalWagonAxleCost);
					totalCostLbl.setText("$" + update() + ".00");
				}
				}
			}
		});
		spareWagonAxlesField.setBounds(102, 249, 96, 27);
		card9.add(spareWagonAxlesField);
		spareWagonAxlesField.setColumns(10);
		
		poundsOfFoodField = new JTextField();
		poundsOfFoodField.setForeground(new Color(254, 255, 255));
		poundsOfFoodField.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		poundsOfFoodField.setBackground(new Color(0, 0, 0));
		poundsOfFoodField.setText("0");
		poundsOfFoodField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Calculates the cost of food and then updates the totalCost
				if(e.getKeyCode() == 10) {
				String poundsOfFoodCostString = poundsOfFoodField.getText();
				food = Integer.parseInt(poundsOfFoodCostString);
				totalPoundsOfFoodCost = (int) (food * .20);
				if(food > 2000) {
					poundsOfFoodCostLbl.setText("Invalid");
				}
				else {
					poundsOfFoodCostLbl.setText("" + totalPoundsOfFoodCost);
					totalCostLbl.setText("$" + update() + ".00");
				}
				}
			}
		});
		poundsOfFoodField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checks if the user inputs the correct amount of food.
				String poundsOfFoodString = poundsOfFoodField.getText();
				maxValue = Integer.parseInt(poundsOfFoodString);
				if(maxValue > 2000) {
					errorMessage = "Can't carry more than 2000 pounds of food.";
					errorMessage(errorMessage);
				}
			}
		});
		poundsOfFoodField.setBounds(102, 330, 96, 27);
		card9.add(poundsOfFoodField);
		poundsOfFoodField.setColumns(10);
		
		spareWagonToungeField = new JTextField();
		spareWagonToungeField.setForeground(new Color(254, 255, 255));
		spareWagonToungeField.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		spareWagonToungeField.setBackground(new Color(0, 0, 0));
		spareWagonToungeField.setText("0");
		spareWagonToungeField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checks if the user input the correct number of wagon tounge.
				String spareWagonToungeString = spareWagonToungeField.getText();
				maxValue = Integer.parseInt(spareWagonToungeString);
				if(maxValue > 3) {
					errorMessage = "Can't carry more than 3 wagon tongues.";
					errorMessage(errorMessage);
				}
			}
		});
		spareWagonToungeField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Calculates the cost of the wagon tounge then updates the totalCost.
				if(e.getKeyCode() == 10) {
				String spareWagonToungeCostString = spareWagonToungeField.getText();
				wagonTongue = Integer.parseInt(spareWagonToungeCostString);
				totalWagonToungeCost = wagonTongue * 10;
				if(wagonTongue > 3) {
					spareWagonToungeCostLbl.setText("Invalid");
				}
				else {
					spareWagonToungeCostLbl.setText("" + totalWagonToungeCost);
					totalCostLbl.setText("$" + update() + ".00");
				}
				}
			}
		});
		spareWagonToungeField.setBounds(102, 290, 96, 27);
		card9.add(spareWagonToungeField);
		spareWagonToungeField.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Unit Price");
		lblNewLabel_15.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_15.setForeground(new Color(254, 255, 255));
		lblNewLabel_15.setBounds(467, 54, 116, 22);
		card9.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("$20.00\r\n");
		lblNewLabel_16.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_16.setForeground(new Color(254, 255, 255));
		lblNewLabel_16.setBounds(477, 90, 117, 26);
		card9.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("$10.00");
		lblNewLabel_17.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_17.setForeground(new Color(254, 255, 255));
		lblNewLabel_17.setBounds(477, 135, 117, 22);
		card9.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("$2.00");
		lblNewLabel_18.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_18.setForeground(new Color(254, 255, 255));
		lblNewLabel_18.setBounds(477, 173, 106, 26);
		card9.add(lblNewLabel_18);
		
		JLabel lblNewLabel_19 = new JLabel("$10.00\r\n");
		lblNewLabel_19.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_19.setForeground(new Color(254, 255, 255));
		lblNewLabel_19.setBounds(476, 211, 118, 26);
		card9.add(lblNewLabel_19);
		
		JLabel lblNewLabel_19_1 = new JLabel("$10.00\r\n");
		lblNewLabel_19_1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_19_1.setForeground(new Color(254, 255, 255));
		lblNewLabel_19_1.setBounds(476, 250, 118, 26);
		card9.add(lblNewLabel_19_1);
		
		JLabel lblNewLabel_20 = new JLabel("3\r\n");
		lblNewLabel_20.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_20.setForeground(new Color(254, 255, 255));
		lblNewLabel_20.setBounds(46, 287, 49, 32);
		card9.add(lblNewLabel_20);
		
		
		
		JLabel lblNewLabel_21 = new JLabel("Spare Wagon Tongue\r\n");
		lblNewLabel_21.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_21.setForeground(new Color(254, 255, 255));
		lblNewLabel_21.setBounds(223, 295, 251, 27);
		card9.add(lblNewLabel_21);
		
		JLabel lblNewLabel_19_1_1 = new JLabel("$10.00\r\n");
		lblNewLabel_19_1_1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_19_1_1.setForeground(new Color(254, 255, 255));
		lblNewLabel_19_1_1.setBounds(476, 287, 118, 26);
		card9.add(lblNewLabel_19_1_1);
		
		JLabel lblNewLabel_22 = new JLabel("$0.20\r\n");
		lblNewLabel_22.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_22.setForeground(new Color(254, 255, 255));
		lblNewLabel_22.setBounds(477, 333, 106, 20);
		card9.add(lblNewLabel_22);
		
		JLabel lblNewLabel_23 = new JLabel("Cost\r\n");
		lblNewLabel_23.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		lblNewLabel_23.setForeground(new Color(254, 255, 255));
		lblNewLabel_23.setBounds(604, 55, 108, 21);
		card9.add(lblNewLabel_23);
		
		JLabel totalCostLabelHolder = new JLabel("Total Cost:");
		totalCostLabelHolder.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
		totalCostLabelHolder.setForeground(new Color(254, 255, 255));
		totalCostLabelHolder.setBounds(453, 393, 130, 27);
		card9.add(totalCostLabelHolder);
        
        // ***CARD 9 END***
        
        // CARD 9 MAPPING EVENTS
        // WILL HAVE TO CHANGE TO KEYBOARD EVENTS
        
		JButton btnPurchase = new JButton("PURCHASE");
        btnPurchase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (Player1.getBalance() < update()) {
        			errorMessage = "You don't have enough money to make this purchase.";
        			errorMessage(errorMessage);
        		}
        		else {
        			Player1.setBalance(Player1.getBalance()-update());
        			makePurchase();
        			resetShop();
        			updateSupplies();
        			// Checks to see if this shop instance is the initial buying of supplies
        			if (Trail.dailyTravel() == 0) {
        				// Sets the Pounds Of Food Display
        				lblFoodPounds.setText(String.valueOf(Food.getQuantity()));
        				
        				// Sets the first location as Independence
        				lblLandmarkName.setText("Independence " + Trail.calculateDate());
        				
        				//Next card
        				CardLayout cl = (CardLayout)(cards.getLayout());
            			cl.show(cards, TEXTCARD1);
            			txtrTextCard1.setText("You are left with $" + Player1.getBalance() + ".00 after purchasing supplies. Your crew leaves"
            					+ " from Independence in the morning.");
            			
            			// Tells textCard what card to travel to.
            			textCard1 = LOCATIONDISPLAY;
        			}
        			// Determines that the player is shopping mid-game, and at a fort.
        			else {
        				// Sets the Pounds Of Food Display
        				lblFoodPounds.setText(String.valueOf(Food.getQuantity()));
        				
        				CardLayout cl = (CardLayout)(cards.getLayout());
        				cl.show(cards, FORTACTIONS);
        			}
        		}
        	}
        });
        btnPurchase.setBounds(345, 394, 96, 31);
        card9.add(btnPurchase);
        
        // LOCATIONDISPLAY
        JPanel card10 = new JPanel();
        card10.setBackground(new Color(0, 0, 0));
        card10.setLayout(null);
        // set Size
        card10.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 10 START***
        
        lblLandmarkName.setHorizontalAlignment(SwingConstants.CENTER);
        lblLandmarkName.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblLandmarkName.setForeground(new Color(254, 255, 255));
        lblLandmarkName.setBounds(26, 437, 696, 44);
        card10.add(lblLandmarkName);
        
        JLabel lblLandmarkImage = new JLabel("");
        // Update this to make an array of images that display as the game goes on
        // Must use trail class / location function
        String filename1 = "/images/Independence.png";
        ImageIcon location = new ImageIcon(this.getClass().getResource(filename1));
        lblLandmarkImage.setIcon(location);
        lblLandmarkImage.setBounds(6, 6, 738, 419);
        card10.add(lblLandmarkImage);
        
        // ***CARD 10 END***
        
        // CARD 10 MAPPING EVENTS
        
        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Simulate Travel button
        		filename2 = "/images/TrailImage01.png";
        		ImageIcon trailAnimation = new ImageIcon(this.getClass().getResource(filename2));
                lblTravelImage.setIcon(trailAnimation);
                lblDistanceTraveled.setText(Integer.toString(Trail.dailyTravel()));
                lblToNextLandmark.setText(Integer.toString(Trail.distanceToNextLandmark()));
                
                // Update date
                lblDate.setText(Trail.calculateDate());
                
        		//Next card determined whether the player is at a fort or not.
                if (lblLandmarkName.getText().contains("Fort")) {
                	// Tells the action card which card to return to
                	cardReturn = FORTACTIONS;
                	// Shows the FORTACTIONS card
                	CardLayout cl = (CardLayout)(cards.getLayout());
                	cl.show(cards, FORTACTIONS);
                }
                // Determines whether the current landmark is a river
                else if (lblLandmarkName.getText().contains("River") || lblLandmarkName.getText().contains("Dalles")) {
                	// Sets the CurrentRiver object to the river that the player is at
                	String label = lblLandmarkName.getText();
                	if (label.contains("Kansas River Crossing")) {
                		CurrentRiver = River1;
                		lblFerryPrice.setText("20");
                	}
                	if (label.contains("Green River Crossing"))  {
                		CurrentRiver = River2;
                		lblFerryPrice.setText("25");
                	}
                	if (label.contains("Snake River Crossing"))  {
                		CurrentRiver = River3;
                		lblFerryPrice.setText("30");
                	}
                	if (label.contains("The Dalles"))            {
                		CurrentRiver = River4;
                		lblFerryPrice.setText("35");
                	}
                	
                	// Sets the RIVER card with the information of the current river
                	setRiverInfo();
                	
                	// Shows the RIVER card
                	CardLayout cl = (CardLayout)(cards.getLayout());
                	cl.show(cards, RIVER);
                }
                
                // Determines whether the player is at Independence
                else if (lblLandmarkName.getText().contains("Independence")) {
                	cardReturn = TRAILACTIONS;
                	// Shows the TRAILACTIONS card
                	CardLayout cl = (CardLayout)(cards.getLayout());
                	cl.show(cards, TRAILACTIONS);
                }
                // Checks if the player has won
                else if (lblLandmarkName.getText().contains("Oregon")) {
                	// Shows the VICTORY card
                	CardLayout cl = (CardLayout)(cards.getLayout());
                	cl.show(cards, VICTORY);
                }
                else {
                	// Tells the action card which card to return to
                	cardReturn = LANDMARKACTIONS;
                	// Shows the LANDMARKACTIONS card
                	CardLayout cl = (CardLayout)(cards.getLayout());
                	cl.show(cards, LANDMARKACTIONS);
                }
        	}
        });
        btnOK.setBounds(677, 442, 45, 40);
        card10.add(btnOK);
        
        // TRAIL
        JPanel card11 = new JPanel();
        card11.setBackground(new Color(0, 0, 0));
        card11.setLayout(null);
        // set Size
        card11.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 11 START***
        
        filename2 = "/images/TrailImage01.png";
        ImageIcon trail = new ImageIcon(this.getClass().getResource(filename2));
        lblTravelImage.setIcon(trail);
        lblTravelImage.setBounds(6, 6, 738, 254);
        card11.add(lblTravelImage);
        
        JLabel lblDisplay07 = new JLabel("Pounds Of Food:");
        lblDisplay07.setForeground(new Color(254, 255, 255));
        lblDisplay07.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblDisplay07.setBounds(26, 467, 180, 27);
        card11.add(lblDisplay07);
        
        lblFoodPounds.setForeground(new Color(254, 255, 255));
        lblFoodPounds.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblFoodPounds.setBounds(323, 467, 134, 27);
        card11.add(lblFoodPounds);
        
        JLabel lblDisplay01 = new JLabel("Miles Traveled:");
        lblDisplay01.setForeground(new Color(254, 255, 255));
        lblDisplay01.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblDisplay01.setBounds(26, 311, 202, 27);
        card11.add(lblDisplay01);
        
        JLabel lblDisplay02 = new JLabel("Miles To Next Landmark:");
        lblDisplay02.setForeground(new Color(254, 255, 255));
        lblDisplay02.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblDisplay02.setBounds(26, 345, 287, 27);
        card11.add(lblDisplay02);
        
        JLabel lblDisplay03 = new JLabel("Food Rations:");
        lblDisplay03.setForeground(new Color(254, 255, 255));
        lblDisplay03.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblDisplay03.setBounds(26, 379, 149, 27);
        card11.add(lblDisplay03);
        
        JLabel lblDisplay04 = new JLabel("Travel Speed:");
        lblDisplay04.setForeground(new Color(254, 255, 255));
        lblDisplay04.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblDisplay04.setBounds(26, 410, 149, 27);
        card11.add(lblDisplay04);
        
        JLabel lblDisplay05 = new JLabel("Health:");
        lblDisplay05.setForeground(new Color(254, 255, 255));
        lblDisplay05.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblDisplay05.setBounds(26, 441, 81, 27);
        card11.add(lblDisplay05);
        
        lblDistanceTraveled.setForeground(new Color(254, 255, 255));
        lblDistanceTraveled.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblDistanceTraveled.setBounds(323, 311, 103, 27);
        card11.add(lblDistanceTraveled);
        
        lblToNextLandmark.setForeground(new Color(254, 255, 255));
        lblToNextLandmark.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblToNextLandmark.setBounds(323, 345, 103, 27);
        card11.add(lblToNextLandmark);
        
        lblFoodRations.setForeground(new Color(254, 255, 255));
        lblFoodRations.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblFoodRations.setBounds(323, 379, 134, 27);
        card11.add(lblFoodRations);
        
        lblTravelSpeed.setForeground(new Color(254, 255, 255));
        lblTravelSpeed.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblTravelSpeed.setBounds(319, 410, 224, 27);
        card11.add(lblTravelSpeed);
        
        lblHealth.setForeground(new Color(254, 255, 255));
        lblHealth.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblHealth.setBounds(323, 441, 134, 27);
        card11.add(lblHealth);
        
        lblDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblDate.setForeground(new Color(254, 255, 255));
        lblDate.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblDate.setBounds(242, 272, 263, 27);
        card11.add(lblDate);
        
        // **CARD 11 END***
        
        // CARD 11 MAPPING EVENTS
        
        JButton btnTravel = new JButton("Travel");
        btnTravel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Change travel image to simulate "animation"
        		if (filename2.equals("/images/TrailImage01.png")) {
        			filename2 = "/images/TrailImage02.png";
        		}
        		else filename2 = "/images/TrailImage01.png";
        		
        		if (Trail.getCurrentLandmark().contains("River")) {
                	if (Trail.distanceToNextLandmark() < (2*Trail.getTravelSpeed())) {
                		filename2 = "/images/TrailImage03.png";
                	}
                }
        		
        		ImageIcon trailAnimation = new ImageIcon(this.getClass().getResource(filename2));
                lblTravelImage.setIcon(trailAnimation);
                
                // Change the location display card by updating the location name and image once
                // The wagon has reached the next location.
                if (Trail.distanceToNextLandmark() < Trail.getTravelSpeed()) {
                	lblLandmarkName.setText(Trail.getCurrentLandmark() + ", " + Trail.calculateDate());
                	String filename3 = "/images/" + Trail.getCurrentLandmark() + ".png";
                	ImageIcon location = new ImageIcon(this.getClass().getResource(filename3));
                	lblLandmarkImage.setIcon(location);
                	CardLayout cl = (CardLayout)(cards.getLayout());
            		cl.show(cards, LOCATIONDISPLAY);
                }
                
                lblDistanceTraveled.setText(Integer.toString(Trail.dailyTravel()));
                
                lblToNextLandmark.setText(Integer.toString(Trail.distanceToNextLandmark()));
                
                // Update date
                Trail.incrementDay();
                lblDate.setText(Trail.calculateDate());
                
                // Update Food Pounds and test if food is gone
                Trail.updateFood(Food);
                if (Food.getQuantity() == 0) addedHealth += 8;
                lblFoodPounds.setText(String.valueOf(Food.getQuantity()));
                
                // Random Trail Events
                trailEvent = Trail.randomTrailEvent(WagonCrew);
                
                // Detect that an event has happened
                if (!trailEvent.equals("")) {
                	trailEventMessage(trailEvent);
                }
                
                // Update Health
                
                // Travel Speed health addition
                
                if (Trail.getTravelSpeed() < 12) addedHealth += 1;
                if (Trail.getTravelSpeed() < 16) addedHealth += 2;
                if (Trail.getTravelSpeed() < 20) addedHealth += 3;
                
                // Food Rations health addition
                addedHealth += (Trail.getFoodRations() * -1 + 3);
                
                // Make the additions
                totalHealth = WagonCrew.calculateTotalHealth(addedHealth);
                Wagon.setHealth(totalHealth);
                lblHealth.setText(WagonCrew.getHealthLevel(totalHealth));
                
                // Check for party deaths
                for (int i = 0; i < WagonMembers.size(); i++) {
                	if (!WagonCrew.getWagonMembers().get(i).isAlive()) {
                		// Remove the dead person from the wagon
                		String name = WagonCrew.getWagonMembers().get(i).getName();
                		WagonCrew.hasDied(name);
                		Trail.decrementWagonMembers();
                		
                		String text = name + " has died.";
                		trailEventMessage(text);
                	}
                }
                
                if (hasLost()) {
                	CardLayout cl = (CardLayout)(cards.getLayout());
                	cl.show(cards, DEATH);
                }
                
                // Update "Check Supplies" Window
                updateSupplies();
        	}
        });
        btnTravel.setBounds(555, 313, 117, 40);
        card11.add(btnTravel);
        
        JButton btnTrailOptions = new JButton("Trail Options");
        btnTrailOptions.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardReturn = TRAILACTIONS;
        		CardLayout cl = (CardLayout)(cards.getLayout());
            	cl.show(cards, TRAILACTIONS);
        	}
        });
        btnTrailOptions.setBounds(555, 401, 117, 40);
        card11.add(btnTrailOptions);
        
        // TRAILACTIONS
        JPanel card12 = new JPanel();
        card12.setBackground(new Color(0, 0, 0));
        card12.setLayout(null);
        // set Size
        card12.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 12 START***
        
        JButton btnRest = new JButton("Stop to Rest");
        btnRest.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Shows the STOPTOREST card and updates the date label
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, STOPTOREST);
        		
        		lblRest20.setText(Trail.calculateDate());
        	}
        });
        btnRest.setBounds(124, 291, 117, 50);
        card12.add(btnRest);
        
        JButton btnSupplies = new JButton("Check Supplies");
        btnSupplies.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Shows the CHECKSUPPLIES card
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, CHECKSUPPLIES);
        	}
        });
        btnSupplies.setBounds(323, 291, 117, 50);
        card12.add(btnSupplies);
        
        JButton btnMap = new JButton("Check Map");
        btnMap.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Updates the map image and shows the MAP card
        		setMapImage();
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, MAP);
        	}
        });
        btnMap.setBounds(518, 291, 117, 50);
        card12.add(btnMap);
        
        JButton btnTravelSpeed = new JButton("Change Travel Speed");
        btnTravelSpeed.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Shows the TRAVELSPEED card
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, TRAVELSPEED);
        	}
        });
        btnTravelSpeed.setBounds(124, 167, 174, 50);
        card12.add(btnTravelSpeed);
        
        JButton btnFoodRations = new JButton("Change Food Rations");
        btnFoodRations.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Shows the FOODRATIONS card
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, FOODRATIONS);
        	}
        });
        btnFoodRations.setBounds(461, 167, 174, 50);
        card12.add(btnFoodRations);
        
        JLabel lblTrailOptions = new JLabel("Trail Options");
        lblTrailOptions.setHorizontalAlignment(SwingConstants.CENTER);
        lblTrailOptions.setForeground(new Color(254, 255, 255));
        lblTrailOptions.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblTrailOptions.setBounds(279, 55, 202, 27);
        card12.add(lblTrailOptions);
        
        JButton btnTrade = new JButton("Trade");
        btnTrade.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		// Creates a new trader
        		Trade Trader = new Trade(Wagon);
        		NewTrader = Trader;
        		
        		// Create new trade items and set the trade card
        		// text area to display to the player
        		NewTrader.loadTraderItems();
        		NewTrader.createTradeItems();
        		txtroffer.setText(getTradeString());
        		
        		// Shows the TRADE card and sets the new trade items.
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, TRADE);
        	}
        });
        btnTrade.setBounds(323, 167, 117, 50);
        card12.add(btnTrade);
        
        JButton btnContinueOnTrail = new JButton("Continue On Trail");
        btnContinueOnTrail.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, TRAIL);
        	}
        });
        btnContinueOnTrail.setBounds(290, 408, 174, 50);
        card12.add(btnContinueOnTrail);
        
        // ***CARD 12 END***
        
        // FORTACTIONS
        JPanel card13 = new JPanel();
        card13.setBackground(new Color(0, 0, 0));
        card13.setLayout(null);
        // set Size
        card13.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 13 START***
        
        JButton btnRest1 = new JButton("Stop to Rest");
        btnRest1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Shows the STOPTOREST card and updates the date label
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, STOPTOREST);
        		
        		lblRest20.setText(Trail.calculateDate());
        	}
        });
        btnRest1.setBounds(63, 291, 117, 50);
        card13.add(btnRest1);
        
        JButton btnSupplies1 = new JButton("Check Supplies");
        btnSupplies1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Shows the CHECKSUPPLIES card
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, CHECKSUPPLIES);
        	}
        });
        btnSupplies1.setBounds(192, 291, 117, 50);
        card13.add(btnSupplies1);
        
        JButton btnMap1 = new JButton("Check Map");
        btnMap1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Updates the map image and shows the MAP card
        		setMapImage();
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, MAP);
        	}
        });
        btnMap1.setBounds(585, 291, 117, 50);
        card13.add(btnMap1);
        
        JButton btnTravelSpeed1 = new JButton("Change Travel Speed");
        btnTravelSpeed1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Shows the TRAVELSPEED card
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, TRAVELSPEED);
        	}
        });
        btnTravelSpeed1.setBounds(124, 167, 174, 50);
        card13.add(btnTravelSpeed1);
        
        JButton btnFoodRations1 = new JButton("Change Food Rations");
        btnFoodRations1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Shows the FOODRATIONS card
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, FOODRATIONS);
        	}
        });
        btnFoodRations1.setBounds(461, 167, 174, 50);
        card13.add(btnFoodRations1);
        
        JLabel lblTrailOptions1 = new JLabel("Fort Options");
        lblTrailOptions1.setHorizontalAlignment(SwingConstants.CENTER);
        lblTrailOptions1.setForeground(new Color(254, 255, 255));
        lblTrailOptions1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblTrailOptions1.setBounds(279, 55, 202, 27);
        card13.add(lblTrailOptions1);
        
        JButton btnTrade1 = new JButton("Trade");
        btnTrade1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		// Creates a new trader
        		Trade Trader = new Trade(Wagon);
        		NewTrader = Trader;
        		
        		// Create new trade items and set the trade card
        		// text area to display to the player
        		NewTrader.loadTraderItems();
        		NewTrader.createTradeItems();
        		txtroffer.setText(getTradeString());
        		
        		// Shows the TRADE card and sets the new trade items.
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, TRADE);
        	}
        });
        btnTrade1.setBounds(323, 167, 117, 50);
        card13.add(btnTrade1);
        
        JButton btnContinueOnTrail_1 = new JButton("Continue On Trail");
        btnContinueOnTrail_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, TRAIL);
        	}
        });
        btnContinueOnTrail_1.setBounds(293, 397, 174, 50);
        card13.add(btnContinueOnTrail_1);
        
        JButton btnShop = new JButton("Shop");
        btnShop.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Shows the SHOP card
        		lblBalance.setText("$" + Player1.getBalance() + ".00");
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, SHOP);
        	}
        });
        btnShop.setBounds(456, 291, 117, 50);
        card13.add(btnShop);
        
        JButton btnTalk = new JButton("Talk To People");
        btnTalk.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

        		// Sets the conversation text area to the current conversation
        		textArea.setText(Trail.getConversation());
        		
        		// Shows the TALKTOPEOPLE card and sets the new trade items.
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, TALKTOPEOPLE);
        	}
        });
        btnTalk.setBounds(323, 291, 117, 50);
        card13.add(btnTalk);
        
        // ***CARD 13 END***
        
        // TRAILACTIONS
        JPanel card14 = new JPanel();
        card14.setBackground(new Color(0, 0, 0));
        card14.setLayout(null);
        // set Size
        card14.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 14 START***
        
        JButton btnRest2 = new JButton("Stop to Rest");
        btnRest2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Shows the STOPTOREST card and updates the date label
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, STOPTOREST);
        		
        		lblRest20.setText(Trail.calculateDate());
        	}
        });
        btnRest2.setBounds(63, 291, 117, 50);
        card14.add(btnRest2);
        
        JButton btnSupplies2 = new JButton("Check Supplies");
        btnSupplies2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Shows the CHECKSUPPLIES card
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, CHECKSUPPLIES);
        	}
        });
        btnSupplies2.setBounds(244, 291, 117, 50);
        card14.add(btnSupplies2);
        
        JButton btnMap2 = new JButton("Check Map");
        btnMap2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Updates the map image and shows the MAP card
        		setMapImage();
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, MAP);
        	}
        });
        btnMap2.setBounds(585, 291, 117, 50);
        card14.add(btnMap2);
        
        JButton btnTravelSpeed2 = new JButton("Change Travel Speed");
        btnTravelSpeed2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Shows the TRAVELSPEED card
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, TRAVELSPEED);
        	}
        });
        btnTravelSpeed2.setBounds(124, 167, 174, 50);
        card14.add(btnTravelSpeed2);
        
        JButton btnFoodRations2 = new JButton("Change Food Rations");
        btnFoodRations2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Shows the FOODRATIONS card
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, FOODRATIONS);
        	}
        });
        btnFoodRations2.setBounds(461, 167, 174, 50);
        card14.add(btnFoodRations2);
        
        JLabel lblTrailOptions2 = new JLabel("Trail Options");
        lblTrailOptions2.setHorizontalAlignment(SwingConstants.CENTER);
        lblTrailOptions2.setForeground(new Color(254, 255, 255));
        lblTrailOptions2.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblTrailOptions2.setBounds(279, 55, 202, 27);
        card14.add(lblTrailOptions2);
        
        JButton btnTrade2 = new JButton("Trade");
        btnTrade2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		// Creates a new trader
        		Trade Trader = new Trade(Wagon);
        		NewTrader = Trader;
        		
        		// Create new trade items and set the trade card
        		// text area to display to the player
        		NewTrader.loadTraderItems();
        		NewTrader.createTradeItems();
        		txtroffer.setText(getTradeString());
        		
        		// Shows the TRADE card and sets the new trade items.
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, TRADE);
        	}
        });
        btnTrade2.setBounds(323, 167, 117, 50);
        card14.add(btnTrade2);
        
        JButton btnContinueOnTrail_2 = new JButton("Continue On Trail");
        btnContinueOnTrail_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, TRAIL);
        	}
        });
        btnContinueOnTrail_2.setBounds(293, 397, 174, 50);
        card14.add(btnContinueOnTrail_2);
        
        JButton btnTalk1 = new JButton("Talk To People");
        btnTalk1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		// Sets the conversation text area to the current conversation
        		textArea.setText(Trail.getConversation());
        		
        		// Shows the TALKTOPEOPLE card and sets the new trade items.
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, TALKTOPEOPLE);
        	}
        });
        btnTalk1.setBounds(398, 291, 117, 50);
        card14.add(btnTalk1);
        
        // ***CARD 14 END***
        
        // FOODRATIONS
        JPanel card15 = new JPanel();
        card15.setBackground(new Color(0, 0, 0));
        card15.setLayout(null);
        // set Size
        card15.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 15 START***
        
        JLabel lblNewLabel_8_1 = new JLabel("Set Food Rations To...");
        lblNewLabel_8_1.setForeground(new Color(254, 255, 255));
        lblNewLabel_8_1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel_8_1.setBounds(250, 121, 250, 32);
        card15.add(lblNewLabel_8_1);
        
        JLabel lblNewLabel_8_1_1 = new JLabel("1. FIlling");
        lblNewLabel_8_1_1.setForeground(new Color(254, 255, 255));
        lblNewLabel_8_1_1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel_8_1_1.setBounds(250, 165, 250, 32);
        card15.add(lblNewLabel_8_1_1);
        
        JLabel lblNewLabel_8_1_1_1 = new JLabel("2. Meager");
        lblNewLabel_8_1_1_1.setForeground(new Color(254, 255, 255));
        lblNewLabel_8_1_1_1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel_8_1_1_1.setBounds(250, 209, 250, 32);
        card15.add(lblNewLabel_8_1_1_1);
        
        JLabel lblNewLabel_8_1_1_1_1 = new JLabel("3. Bare Bones");
        lblNewLabel_8_1_1_1_1.setForeground(new Color(254, 255, 255));
        lblNewLabel_8_1_1_1_1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel_8_1_1_1_1.setBounds(250, 253, 250, 32);
        card15.add(lblNewLabel_8_1_1_1_1);
        
        JLabel lblNewLabel_8_1_1_1_1_1 = new JLabel("Your Response:");
        lblNewLabel_8_1_1_1_1_1.setForeground(new Color(254, 255, 255));
        lblNewLabel_8_1_1_1_1_1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel_8_1_1_1_1_1.setBounds(33, 353, 202, 32);
        card15.add(lblNewLabel_8_1_1_1_1_1);
        
        textField_3 = new JTextField();
        textField_3.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        textField_3.setForeground(new Color(254, 255, 255));
        textField_3.setBackground(new Color(0, 0, 0));
        textField_3.setBounds(225, 353, 130, 32);
        card15.add(textField_3);
        textField_3.setColumns(10);
        
        // ***CARD 15 END***
        
        // CARD 15 MAPPING EVENTS
        
        JButton btnEnter15 = new JButton("ENTER");
        btnEnter15.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selection = Integer.parseInt(textField_3.getText());
        		
        		// Sets the food rations based on the user's selection
        		if (selection == 1) {
        			Trail.setFoodRations(3);
        			lblFoodRations.setText("Filling");
        		}
        		if (selection == 2) {
        			Trail.setFoodRations(2);
        			lblFoodRations.setText("Meager");
        		}
        		if (selection == 3) {
        			Trail.setFoodRations(1);
        			lblFoodRations.setText("Bare Bones");
        		}
        		
        		// Returns to previous option card.
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, cardReturn);
        	}
        });
        btnEnter15.setBounds(371, 353, 117, 34);
        card15.add(btnEnter15);
        
        // TRAVELSPEED
        JPanel card16 = new JPanel();
        card16.setBackground(new Color(0, 0, 0));
        card16.setLayout(null);
        // set Size
        card16.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 16 START***
        
        JLabel lblNewLabel_8_1_2 = new JLabel("Set Travel Speed To...");
        lblNewLabel_8_1_2.setForeground(new Color(254, 255, 255));
        lblNewLabel_8_1_2.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel_8_1_2.setBounds(238, 136, 250, 32);
        card16.add(lblNewLabel_8_1_2);
        
        JLabel lblNewLabel_8_1_1_2 = new JLabel("1. A Strenuous Pace");
        lblNewLabel_8_1_1_2.setForeground(new Color(254, 255, 255));
        lblNewLabel_8_1_1_2.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel_8_1_1_2.setBounds(238, 179, 250, 32);
        card16.add(lblNewLabel_8_1_1_2);
        
        JLabel lblNewLabel_8_1_1_2_1 = new JLabel("2. A Steady Pace");
        lblNewLabel_8_1_1_2_1.setForeground(new Color(254, 255, 255));
        lblNewLabel_8_1_1_2_1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel_8_1_1_2_1.setBounds(238, 223, 250, 32);
        card16.add(lblNewLabel_8_1_1_2_1);
        
        JLabel lblNewLabel_8_1_1_2_2 = new JLabel("3. A Grueling Pace");
        lblNewLabel_8_1_1_2_2.setForeground(new Color(254, 255, 255));
        lblNewLabel_8_1_1_2_2.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel_8_1_1_2_2.setBounds(238, 267, 250, 32);
        card16.add(lblNewLabel_8_1_1_2_2);
        
        JLabel lblNewLabel_8_1_1_1_1_1_1 = new JLabel("Your Response:");
        lblNewLabel_8_1_1_1_1_1_1.setForeground(new Color(254, 255, 255));
        lblNewLabel_8_1_1_1_1_1_1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel_8_1_1_1_1_1_1.setBounds(33, 353, 202, 32);
        card16.add(lblNewLabel_8_1_1_1_1_1_1);
        
        textField_4 = new JTextField();
        textField_4.setForeground(new Color(254, 255, 255));
        textField_4.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        textField_4.setColumns(10);
        textField_4.setBackground(Color.BLACK);
        textField_4.setBounds(229, 353, 130, 32);
        card16.add(textField_4);
        
        // ***CARD 16 END***
        
        // CARD 16 MAPPING EVENTS
        
        JButton btnEnter16 = new JButton("ENTER");
        btnEnter16.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selection = Integer.parseInt(textField_4.getText());
        		
        		if (selection == 1) {
        			lblTravelSpeed.setText("A Strenuous Pace");
        			Trail.setTravelSpeed(20);
        		}
        		if (selection == 2) {
        			lblTravelSpeed.setText("A Steady Pace");
        			Trail.setTravelSpeed(16);
        		}
        		if (selection == 3) {
        			lblTravelSpeed.setText("A Grueling Pace");
        			Trail.setTravelSpeed(12);
        		}
        		
        		// Returns to previous option card.
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, cardReturn);
        	}
        });
        btnEnter16.setBounds(371, 355, 117, 34);
        card16.add(btnEnter16);
        
        // CHECKSUPPLIES
        JPanel card17 = new JPanel();
        card17.setBackground(new Color(0, 0, 0));
        card17.setLayout(null);
        // set Size
        card17.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 17 START***
        
        JLabel lblNewLabel17 = new JLabel("Supplies:");
        lblNewLabel17.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel17.setForeground(new Color(254, 255, 255));
        lblNewLabel17.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel17.setBounds(267, 36, 202, 32);
        card17.add(lblNewLabel17);
        
        JLabel lblHorses = new JLabel("Horses:");
        lblHorses.setForeground(new Color(254, 255, 255));
        lblHorses.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblHorses.setBounds(68, 113, 202, 32);
        card17.add(lblHorses);
        
        JLabel lblNewLabel17_1_1 = new JLabel("Sets of Clothing:");
        lblNewLabel17_1_1.setForeground(new Color(254, 255, 255));
        lblNewLabel17_1_1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel17_1_1.setBounds(68, 148, 202, 32);
        card17.add(lblNewLabel17_1_1);
        
        JLabel lblNewLabel17_1_2 = new JLabel("Ammunition:");
        lblNewLabel17_1_2.setForeground(new Color(254, 255, 255));
        lblNewLabel17_1_2.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel17_1_2.setBounds(68, 184, 202, 32);
        card17.add(lblNewLabel17_1_2);
        
        JLabel lblNewLabel17_1_3 = new JLabel("Pounds of Food:");
        lblNewLabel17_1_3.setForeground(new Color(254, 255, 255));
        lblNewLabel17_1_3.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel17_1_3.setBounds(68, 219, 202, 32);
        card17.add(lblNewLabel17_1_3);
        
        JLabel lblNewLabel17_1_4 = new JLabel("Spare Wagon Wheels:");
        lblNewLabel17_1_4.setForeground(new Color(254, 255, 255));
        lblNewLabel17_1_4.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel17_1_4.setBounds(68, 257, 274, 32);
        card17.add(lblNewLabel17_1_4);
        
        JLabel lblNewLabel17_1_5 = new JLabel("Spare Wagon Tongues:");
        lblNewLabel17_1_5.setForeground(new Color(254, 255, 255));
        lblNewLabel17_1_5.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel17_1_5.setBounds(68, 292, 274, 32);
        card17.add(lblNewLabel17_1_5);
        
        JLabel lblNewLabel17_1_6 = new JLabel("Spare Wagon Axels:");
        lblNewLabel17_1_6.setForeground(new Color(254, 255, 255));
        lblNewLabel17_1_6.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel17_1_6.setBounds(68, 330, 274, 32);
        card17.add(lblNewLabel17_1_6);
        
        JLabel lblNewLabel17_1_6_1 = new JLabel("Money:");
        lblNewLabel17_1_6_1.setForeground(new Color(254, 255, 255));
        lblNewLabel17_1_6_1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel17_1_6_1.setBounds(68, 370, 274, 32);
        card17.add(lblNewLabel17_1_6_1);
        
        lblMoneySply.setForeground(new Color(254, 255, 255));
        lblMoneySply.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblMoneySply.setBounds(354, 370, 105, 32);
        card17.add(lblMoneySply);
        
        lblTongueSply.setForeground(new Color(254, 255, 255));
        lblTongueSply.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblTongueSply.setBounds(354, 292, 105, 32);
        card17.add(lblTongueSply);
        
        lblAxelSply.setForeground(new Color(254, 255, 255));
        lblAxelSply.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblAxelSply.setBounds(354, 330, 105, 32);
        card17.add(lblAxelSply);
        
        lblWheelSply.setForeground(new Color(254, 255, 255));
        lblWheelSply.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblWheelSply.setBounds(354, 257, 105, 32);
        card17.add(lblWheelSply);
        
        lblFoodSply.setForeground(new Color(254, 255, 255));
        lblFoodSply.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblFoodSply.setBounds(354, 219, 105, 32);
        card17.add(lblFoodSply);
        
        lblAmmunitionSply.setForeground(new Color(254, 255, 255));
        lblAmmunitionSply.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblAmmunitionSply.setBounds(354, 184, 105, 32);
        card17.add(lblAmmunitionSply);
        
        lblClothingSply.setForeground(new Color(254, 255, 255));
        lblClothingSply.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblClothingSply.setBounds(354, 148, 105, 32);
        card17.add(lblClothingSply);
        
        lblHorsesSply.setForeground(new Color(254, 255, 255));
        lblHorsesSply.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblHorsesSply.setBounds(354, 113, 105, 32);
        card17.add(lblHorsesSply);
        
        // ***CARD 17 END***
        
        // CARD 17 MAPPING EVENTS
        
        JButton btnOK17 = new JButton("OK");
        btnOK17.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Returns to previous option card.
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, cardReturn);
        	}
        });
        btnOK17.setBounds(331, 414, 65, 49);
        card17.add(btnOK17);
        
        // TRADE
        JPanel card18 = new JPanel();
        card18.setBackground(new Color(0, 0, 0));
        card18.setLayout(null);
        // set Size
        card18.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 18 START***
        
        JLabel lblSomebodyWouldLike = new JLabel("Somebody would like to trade with you.");
        lblSomebodyWouldLike.setHorizontalAlignment(SwingConstants.CENTER);
        lblSomebodyWouldLike.setForeground(new Color(254, 255, 255));
        lblSomebodyWouldLike.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblSomebodyWouldLike.setBounds(123, 35, 511, 32);
        card18.add(lblSomebodyWouldLike);
        txtroffer.setLineWrap(true);
        txtroffer.setEditable(false);
        
        txtroffer.setText("[offer]");
        txtroffer.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        txtroffer.setBackground(new Color(0, 0, 0));
        txtroffer.setForeground(new Color(254, 255, 255));
        txtroffer.setBounds(123, 164, 511, 181);
        card18.add(txtroffer);
        
        // ***CARD 18 END***
        
        // CARD 18 MAPPING EVENTS
        
        JButton btnTrade18 = new JButton("Make Trade");
        btnTrade18.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Make the offered trade and update the supplies
        		NewTrader.tradeItems();
        		updateSupplies();
        		
        		// Returns to previous option card.
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, cardReturn);
        	}
        });
        btnTrade18.setBounds(189, 390, 117, 34);
        card18.add(btnTrade18);
        
        JButton btnExit18 = new JButton("Stop Trading");
        btnExit18.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Returns to previous option card.
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, cardReturn);
        	}
        });
        btnExit18.setBounds(470, 393, 117, 34);
        card18.add(btnExit18);
        
        // TALKTOPEOPLE
        JPanel card19 = new JPanel();
        card19.setBackground(new Color(0, 0, 0));
        card19.setLayout(null);
        // set Size
        card19.setPreferredSize(new Dimension(750, 500));
        textArea.setWrapStyleWord(true);
        
        // ***CARD 19 START***
        
        textArea.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        textArea.setBackground(new Color(0, 0, 0));
        textArea.setForeground(new Color(254, 255, 255));
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setBounds(64, 49, 610, 385);
        card19.add(textArea);
        
        // ***CARD 19 END***
        
        // CARD 19 MAPPING EVENTS
        
        JButton btnOK19 = new JButton("OK");
        btnOK19.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Returns to previous option card.
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, cardReturn);
        	}
        });
        btnOK19.setBounds(342, 436, 48, 34);
        card19.add(btnOK19);
        
        // STOPTOREST
        JPanel card20 = new JPanel();
        card20.setBackground(new Color(0, 0, 0));
        card20.setLayout(null);
        // set Size
        card20.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 20 START***
        
        lblRest20.setHorizontalAlignment(SwingConstants.CENTER);
        lblRest20.setForeground(new Color(254, 255, 255));
        lblRest20.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblRest20.setBounds(243, 70, 250, 32);
        card20.add(lblRest20);
        
        JLabel lblNewLabel20_1 = new JLabel("How many days will you be resting?");
        lblNewLabel20_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel20_1.setForeground(new Color(254, 255, 255));
        lblNewLabel20_1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel20_1.setBounds(152, 177, 413, 32);
        card20.add(lblNewLabel20_1);
        
        textField20 = new JTextField();
        textField20.setForeground(new Color(254, 255, 255));
        textField20.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        textField20.setColumns(10);
        textField20.setBackground(Color.BLACK);
        textField20.setBounds(299, 282, 130, 32);
        card20.add(textField20);
        
        JLabel lblNewLabel20 = new JLabel("Your Response:");
        lblNewLabel20.setForeground(new Color(254, 255, 255));
        lblNewLabel20.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel20.setBounds(119, 282, 168, 32);
        card20.add(lblNewLabel20);
        
        // ***CARD 20 END***
        
        // CARD 20 MAPPING EVENTS
        
        JButton btnEnter20 = new JButton("ENTER");
        btnEnter20.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int numberOfDays = Integer.parseInt(textField20.getText());
        		
        		// Increment the day & display on the label
        		for (int i = 0; i < numberOfDays; i++) {
        			// Increment the day
        			Trail.incrementDay();
        			// Update the food item
        			Trail.updateFood(Food);
        			lblRest20.setText(Trail.calculateDate());
        			// Add effects to health
        			addedHealth -= 16;
        			totalHealth = WagonCrew.calculateTotalHealth(addedHealth);
                    Wagon.setHealth(totalHealth);
                    lblHealth.setText(WagonCrew.getHealthLevel(totalHealth));
        		}
        	}
        });
        btnEnter20.setBounds(441, 280, 117, 34);
        card20.add(btnEnter20);
        
        JButton btnOK20 = new JButton("OK");
        btnOK20.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Returns to previous option card.
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, cardReturn);
        	}
        });
        btnOK20.setBounds(342, 436, 48, 34);
        card20.add(btnOK20);
        
        // MAP
        JPanel card21 = new JPanel();
        card21.setBackground(new Color(0, 0, 0));
        card21.setLayout(null);
        // set Size
        card21.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 21 START***
        
        lblMap.setBounds(68, 52, 600, 354);
        card21.add(lblMap);
        
        // ***CARD 21 END***
        
        // CARD 21 MAPPING EVENTS
        
        JButton btnOK21 = new JButton("OK");
        btnOK21.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Returns to previous option card.
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, cardReturn);
        	}
        });
        btnOK21.setBounds(342, 436, 48, 34);
        card21.add(btnOK21);
        
        // RIVER
        JPanel card22 = new JPanel();
        card22.setBackground(new Color(0, 0, 0));
        card22.setLayout(null);
        // set Size
        card22.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 22 START***
        
        JLabel lblNewLabel22 = new JLabel("The River Is...");
        lblNewLabel22.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel22.setForeground(new Color(254, 255, 255));
        lblNewLabel22.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel22.setBounds(243, 70, 250, 32);
        card22.add(lblNewLabel22);
        
        lblDepth.setForeground(new Color(254, 255, 255));
        lblDepth.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblDepth.setBounds(243, 138, 108, 32);
        card22.add(lblDepth);
        
        JLabel lblNewLabel22_1 = new JLabel("ft deep");
        lblNewLabel22_1.setForeground(new Color(254, 255, 255));
        lblNewLabel22_1.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel22_1.setBounds(385, 138, 108, 32);
        card22.add(lblNewLabel22_1);
        
        lblWidth.setForeground(new Color(254, 255, 255));
        lblWidth.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblWidth.setBounds(243, 182, 108, 32);
        card22.add(lblWidth);
        
        JLabel lblNewLabel22_2 = new JLabel("ft across");
        lblNewLabel22_2.setForeground(new Color(254, 255, 255));
        lblNewLabel22_2.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel22_2.setBounds(385, 182, 108, 32);
        card22.add(lblNewLabel22_2);
        
        lblFlowRate.setForeground(new Color(254, 255, 255));
        lblFlowRate.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblFlowRate.setBounds(243, 226, 108, 32);
        card22.add(lblFlowRate);
        
        JLabel lblNewLabel22_3 = new JLabel("mph fast");
        lblNewLabel22_3.setForeground(new Color(254, 255, 255));
        lblNewLabel22_3.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel22_3.setBounds(385, 226, 108, 32);
        card22.add(lblNewLabel22_3);
        
        JLabel lblNewLabel22_4 = new JLabel("Will You...");
        lblNewLabel22_4.setForeground(new Color(254, 255, 255));
        lblNewLabel22_4.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel22_4.setBounds(288, 270, 149, 32);
        card22.add(lblNewLabel22_4);
        
        JLabel lblNewLabel22_5 = new JLabel("1. Attempt to ford the river");
        lblNewLabel22_5.setForeground(new Color(254, 255, 255));
        lblNewLabel22_5.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel22_5.setBounds(76, 305, 520, 32);
        card22.add(lblNewLabel22_5);
        
        JLabel lblNewLabel22_6 = new JLabel("2. Caulk the wagon and float it across");
        lblNewLabel22_6.setForeground(new Color(254, 255, 255));
        lblNewLabel22_6.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel22_6.setBounds(76, 337, 520, 32);
        card22.add(lblNewLabel22_6);
        
        JLabel lblNewLabel22_7 = new JLabel("3. Ferry Across for $");
        lblNewLabel22_7.setForeground(new Color(254, 255, 255));
        lblNewLabel22_7.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel22_7.setBounds(76, 368, 237, 32);
        card22.add(lblNewLabel22_7);
        
        JLabel lblNewLabel22_8 = new JLabel("4. Wait to see if conditions improve");
        lblNewLabel22_8.setForeground(new Color(254, 255, 255));
        lblNewLabel22_8.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel22_8.setBounds(76, 397, 520, 32);
        card22.add(lblNewLabel22_8);
        
        JLabel lblNewLabel22_9 = new JLabel("Your Response:");
        lblNewLabel22_9.setForeground(new Color(254, 255, 255));
        lblNewLabel22_9.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblNewLabel22_9.setBounds(117, 446, 168, 32);
        card22.add(lblNewLabel22_9);
        
        lblFerryPrice.setForeground(new Color(254, 255, 255));
        lblFerryPrice.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblFerryPrice.setBounds(314, 368, 108, 32);
        card22.add(lblFerryPrice);
        
        textField_22 = new JTextField();
        textField_22.setForeground(new Color(254, 255, 255));
        textField_22.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        textField_22.setColumns(10);
        textField_22.setBackground(Color.BLACK);
        textField_22.setBounds(295, 446, 130, 32);
        card22.add(textField_22);
        
        // ***CARD 22 END***
        
        // CARD 22 MAPPING EVENTS
        
        JButton btnEnter22 = new JButton("ENTER");
        btnEnter22.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selection = Integer.parseInt(textField_22.getText());
        		
        		// Exception handling
        		if (selection != 4) {
        			filename4 = "/Images/RiverCrossing" + selection + ".png";
        		}
        		else filename4 = "/Images/RiverCrossing1.png";
        		
        		ImageIcon riverImage = new ImageIcon(this.getClass().getResource(filename4));
        		lblRiverCrossing.setIcon(riverImage);
        		
        		// Ford the river
        		if (selection == 1) {
        			// Calculate success for fording the river
        			riverResult = CurrentRiver.fordRiver();
        		}
        		// Float across (caulk the wagon)
        		else if (selection == 2) {
        			// Calculate success for floating across
        			riverResult = CurrentRiver.floatAcrossRiver();
        		}
        		// Ferry across
        		else if (selection == 3) {
        			// Calculate success for ferrying across and subtract cost from player balance
        			riverResult = CurrentRiver.ferryAcross();
        			Player1.setBalance(Player1.getBalance() - Integer.parseInt(lblFerryPrice.getText()));
        			updateSupplies();
        		}
        		// Wait to see if conditions approve
        		else if (selection == 4) {
        			// Update river conditions and increment day
        			CurrentRiver.updateRiverDepth();
        			CurrentRiver.updateRiverWidth();
        			CurrentRiver.updateFlowRate();
        			Trail.incrementDay();
        		}
        		
        		// Wait to see if conditions improve
        		if (selection == 4) {
        			lblWidth.setText(Integer.toString(CurrentRiver.getRiverWidth()));
        	 		lblDepth.setText(Integer.toString(CurrentRiver.getRiverDepth()));
        	 		lblFlowRate.setText(Integer.toString(CurrentRiver.getFlowRate()));
        		}
        		
        		// Checks whether the player's attempt to cross the river was successful or not.
        		else if (selection == 1 || selection == 2 || selection == 3) {
        			if (riverResult) {
        				lblresult.setText("Success!");
        				textAreaRiver.setText("You have successfully crossed the river!");
        				lblItemsLost.setText("");
        				lblItemsLost2.setText("");
        			}
        			else if (!riverResult) {
        				// Set the result label
        				lblresult.setText("Uh Oh!");
        				
        				// Remove or swamp the items based on how bad the failure was.
        				CurrentRiver.removeItems();
        				
        				// Retrieve the quantity and items of the lost items
        				Item[] LostItems     = CurrentRiver.getRemovedItems();
        				int[] ItemQuantities = CurrentRiver.getRemovedItemAmount();
        				
        				// Set the JLabels to format the lost items
        				lblItemsLost.setText("- " + Integer.toString(ItemQuantities[0]) + " " + LostItems[0].getName());
        				lblItemsLost2.setText("- " + Integer.toString(ItemQuantities[1]) + " " + LostItems[1].getName());
        				updateSupplies();
        			    
        				// Set the text area to the appropriate text based on the river failure result.
        				// Swamped means the items are wet but still usable, but capsized means the items are lost.
        				if (CurrentRiver.getFailureResult().equals("swamped")) {
        					textAreaRiver.setText("You failed to successfully cross the river. The following items are "
        							+ "wet, but still useable: ");
        				}
        			
        				else if (CurrentRiver.getFailureResult().equals("capsized")) {
        					textAreaRiver.setText("You failed to successfully cross the river. The followign items"
        							+ "were lost: ");
        				}
        			}
        			
        			// Simulates the river crossing "animation" based on the river crossing result
        			Clock = new javax.swing.Timer(1500, new ActionListener() {
        				public void actionPerformed(ActionEvent evt) {
        					
        					if (clockCycle == 2) {
        						// Show RIVERRESULT card
        	            		CardLayout cl = (CardLayout)(cards.getLayout());
        	            		cl.show(cards, RIVERRESULT);
        	            		clockCycle = 0;
        						Clock.stop();
        					}
        					
        					// River success, sets the next image of the animation
        					if (riverResult) {
        						String filename = "/Images/RiverSuccess.png";
        						ImageIcon riverResult = new ImageIcon(this.getClass().getResource(filename));
        						lblRiverCrossing.setIcon(riverResult);
        					}
        					// River failure, sets the next image of the animation
        					else {
        						String filename = "/Images/RiverFailure.png";
        						ImageIcon riverResult = new ImageIcon(this.getClass().getResource(filename));
        						lblRiverCrossing.setIcon(riverResult);
        					}
        					clockCycle++;
        				}
        				});
        				Clock.start();
        				
        			// Show RIVERANIMAITON card
            		CardLayout cl = (CardLayout)(cards.getLayout());
            		cl.show(cards, RIVERANIMATION);
        		}
        	}
        });
        btnEnter22.setBounds(437, 444, 117, 34);
        card22.add(btnEnter22);
        
        // RIVERANIMATION
        JPanel card23 = new JPanel();
        card23.setBackground(new Color(0, 0, 0));
        card23.setLayout(null);
        // set Size
        card23.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 23 START***
        
        lblRiverCrossing.setBounds(197, 59, 350, 350);
        card23.add(lblRiverCrossing);
        
        // ***CARD 23 END***
        
        // RIVERRESULT
        JPanel card24 = new JPanel();
        card24.setBackground(new Color(0, 0, 0));
        card24.setLayout(null);
        // set Size
        card24.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 24 START***
        
        lblresult.setHorizontalAlignment(SwingConstants.CENTER);
        lblresult.setForeground(new Color(254, 255, 255));
        lblresult.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblresult.setBounds(243, 86, 250, 32);
        card24.add(lblresult);
        
        textAreaRiver.setText("[text]");
        textAreaRiver.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        textAreaRiver.setEditable(false);
        textAreaRiver.setLineWrap(true);
        textAreaRiver.setBackground(new Color(0, 0, 0));
        textAreaRiver.setForeground(new Color(254, 255, 255));
        textAreaRiver.setBounds(133, 130, 477, 101);
        card24.add(textAreaRiver);
        
        lblItemsLost.setHorizontalAlignment(SwingConstants.CENTER);
        lblItemsLost.setForeground(new Color(254, 255, 255));
        lblItemsLost.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblItemsLost.setBounds(167, 276, 384, 32);
        card24.add(lblItemsLost);
        
        lblItemsLost2.setHorizontalAlignment(SwingConstants.CENTER);
        lblItemsLost2.setForeground(new Color(254, 255, 255));
        lblItemsLost2.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblItemsLost2.setBounds(167, 322, 384, 32);
        card24.add(lblItemsLost2);
        
        // ***CARD 24 END***
        
        // CARD 24 MAPPING EVENTS
        
        JButton btnOK24 = new JButton("OK");
        btnOK24.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Show the TRAIL card
        		CardLayout cl = (CardLayout)(cards.getLayout());
        		cl.show(cards, TRAIL);
        	}
        });
        btnOK24.setBounds(304, 423, 117, 34);
        card24.add(btnOK24);
        
        // DEATH
        JPanel card25 = new JPanel();
        card25.setBackground(new Color(0, 0, 0));
        card25.setLayout(null);
        // set Size
        card25.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 25 START***
        
        JLabel lblDeath = new JLabel("You Are Dead");
        lblDeath.setHorizontalAlignment(SwingConstants.CENTER);
        lblDeath.setForeground(new Color(254, 255, 255));
        lblDeath.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblDeath.setBounds(244, 228, 250, 32);
        card25.add(lblDeath);
        
        // ***CARD 25 END***
        
        // VICTORY
        JPanel card26 = new JPanel();
        card26.setBackground(new Color(0, 0, 0));
        card26.setLayout(null);
        // set Size
        card26.setPreferredSize(new Dimension(750, 500));
        
        // ***CARD 26 START***
        
        JLabel lblVictory = new JLabel("Congradulations!");
        lblVictory.setHorizontalAlignment(SwingConstants.CENTER);
        lblVictory.setForeground(new Color(254, 255, 255));
        lblVictory.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblVictory.setBounds(243, 171, 250, 32);
        card26.add(lblVictory);
        
        JLabel lblYouMadeIt = new JLabel("You made it to Oregon!");
        lblYouMadeIt.setHorizontalAlignment(SwingConstants.CENTER);
        lblYouMadeIt.setForeground(new Color(254, 255, 255));
        lblYouMadeIt.setFont(new Font("AppleMyungjo", Font.BOLD, 24));
        lblYouMadeIt.setBounds(142, 256, 457, 32);
        card26.add(lblYouMadeIt);
        
        // ***CARD 26 END***
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card1 , TITLESCREEN);
        cards.add(card2 , DIFFICULTY);
        cards.add(card3 , PLAYERNAME);
        cards.add(card4 , TEXTCARD1);
        cards.add(card5 , WAGONNAME);
        cards.add(card6 , DEPARTURE);
        cards.add(card8 , SHOPGUY);
        cards.add(card7 , TEXTCARD2);
        cards.add(card9 , SHOP);
        cards.add(card10, LOCATIONDISPLAY); 
        cards.add(card11, TRAIL);
        cards.add(card12, TRAILACTIONS);
        cards.add(card13, FORTACTIONS);
        cards.add(card14, LANDMARKACTIONS);
        cards.add(card15, FOODRATIONS);
        cards.add(card16, TRAVELSPEED);
        cards.add(card17, CHECKSUPPLIES);
        cards.add(card18, TRADE);
        cards.add(card19, TALKTOPEOPLE);
        cards.add(card20, STOPTOREST);
        cards.add(card21, MAP);
        cards.add(card22, RIVER);
        cards.add(card23, RIVERANIMATION);
        cards.add(card24, RIVERRESULT);
        cards.add(card25, DEATH);
        cards.add(card26, VICTORY);
        
        pane.add(cards, BorderLayout.CENTER);
        
	}

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField nameField;
	private JTextField member1Field;
	private JTextField member2Field;
	private JTextField member3Field;
	private JTextField member4Field;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_22;
	private JTextField textField20;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}

	/**
	 * Create the application.
	 */
	public OregonV1() {
		initialize();
		createWagonHealth();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// MISCELLANEOUS METHODS USED IN THE PROGRAM
	
	/**
	 * Method used to reuse card8 in order to save on space,
	 * when the card is done being used, the endCard8 value is set 
	 * to true, and false otherwise
	 */
	public void card8switch() {
    	if (endCard8) endCard8 = false;
    	else endCard8 = true;
    }
	
	/**
	 * Creates a pre-loaded game with wagon items, player names, money, and starting date
	 * for ease of testing. Implemented as a "cheat code" (enter 42 at the title screen).
	 */
	public void quickStart() {
		Wagon.createLoadedWagon(Horses, Food, Clothing, Ammunition, WagonTongue, WagonWheel, WagonAxel);
		Horses.setQuantity(10);
		Clothing.setQuantity(15);
		Ammunition.setQuantity(50);
		Food.setQuantity(2000);
		WagonTongue.setQuantity(3);
		WagonWheel.setQuantity(3);
		WagonAxel.setQuantity(3);
		Player1.setBalance(940);
		updateSupplies();
		
		Player1.setMultiplier(1);
		Player1.setName("Anna");
		
		Member1.setName("George");
		Member2.setName("Steven");
		Member3.setName("Abraham");
		Member4.setName("Martha");
		
		Trail.setMonth(4);
		
		// Sets the Pounds Of Food Display
		lblFoodPounds.setText(String.valueOf(Food.getQuantity()));
		
		// Sets the first location as Independence
		lblLandmarkName.setText("Independence " + Trail.calculateDate());
		
		CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, LOCATIONDISPLAY);
	}
	
	/**
	 * Adds the wagon members to the array used in the health class
	 */
	public void createWagonHealth() {
		WagonMembers.add(Member1);
		WagonMembers.add(Member2);
		WagonMembers.add(Member3);
		WagonMembers.add(Member4);
	}
	
	/**
	 * Calculates the file name of the current map image 
	 * based on current player location, and displays it
	 * on the map label in the map card.
	 */
	public void setMapImage() {
		String filename = "";
		int mapNumber = 0;
		int distance = Trail.getCurrentLocation();
		
		// Get the map number index based on the player's current location
		if (distance >= 0)   mapNumber = 1;
		if (distance > 110)  mapNumber = 2;
		if (distance > 330)  mapNumber = 3;
		if (distance > 580)  mapNumber = 4;
		if (distance > 667)  mapNumber = 5;
		if (distance > 857)  mapNumber = 6;
		if (distance > 914)  mapNumber = 7;
		if (distance > 1057) mapNumber = 8;
		if (distance > 1114) mapNumber = 9;
		if (distance > 1296) mapNumber = 10;
		if (distance > 1409) mapNumber = 11;
		if (distance > 1569) mapNumber = 12;
		if (distance > 1694) mapNumber = 13;
		
		filename = "/Images/Map" + Integer.toString(mapNumber) + ".png";
		
		ImageIcon Map = new ImageIcon(this.getClass().getResource(filename));
		lblMap.setIcon(Map);
	}
	
	/**
	 * Formats the string for the trade card text area
	 * @return body of text containing the trader's offer
	 * based on the Trade class
	 */
	public String getTradeString() {
		String text = "They offer " + NewTrader.getTheirTradeItems() + " "
				+ "for " + NewTrader.getYourTradeItems();
		
		return text;
	}
	
	/**
	 * Changes the current card that is being shown on the screen
	 */
	public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }
	
	/**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("OregonV1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
        //Create and set up the content pane.
        OregonV1 demo = new OregonV1();
        demo.addComponentToPane(frame.getContentPane());
         
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * update the toalCost through all the labels of the items and adds them together
     * @return totalCost the total cost of the purchase
     */
 	public int update() {
 		int totalCost = 0;
 		System.out.println(totalCost);
 		totalCost += Integer.parseInt(horseCostLbl.getText());
 		totalCost += Integer.parseInt(clothingCostLbl.getText());
 		totalCost += Integer.parseInt(bulletsOfFreedomCostLbl.getText());
 		totalCost += Integer.parseInt(spareWagonWheelCostLbl.getText());
 		totalCost += Integer.parseInt(spareWagonAxleCostLbl.getText());
 		totalCost += Integer.parseInt(spareWagonToungeCostLbl.getText());
 		totalCost += Integer.parseInt(poundsOfFoodCostLbl.getText());
 		return totalCost;
 	}
 	
 	/**
 	 * Adds the shop items to the wagon without resetting their value.
 	 */
 	public void makePurchase() {
 		// Add the items to the wagon
 		Horses.addQuantity(horses);
 		Clothing.addQuantity(clothing);
 		Food.addQuantity(food);
 		WagonWheel.addQuantity(wagonWheel);
 		WagonTongue.addQuantity(wagonTongue);
 		WagonAxel.addQuantity(wagonAxel);
 		Ammunition.addQuantity(ammunition);
 		
 		// Set quantity of items back to zero
 		horses      = 0;
 		clothing    = 0;
 		food        = 0;
 		wagonWheel  = 0;
 		wagonTongue = 0;
 		wagonAxel   = 0;
 		ammunition  = 0;
 	}
 	
 	/**
 	 * Creates an error message dialogue box
 	 * @param text the body of text to be displayed
 	 */
 	public void errorMessage(String text) {
 		String title = "Attention:";
 		int type = JOptionPane.ERROR_MESSAGE;
		JOptionPane.showMessageDialog(null, text, title, type);
		errorMessage = "";
 	}
 	
 	/**
 	 * Creates a warning message dialogue box
 	 * @param text the body of text to be displayed
 	 */
 	public void trailEventMessage(String text) {
 		String title = "Oh No!";
 		int type = JOptionPane.WARNING_MESSAGE;
 		JOptionPane.showMessageDialog(null, text, title, type);
 	}
 	
 	/**
 	 * Resets the shop text fields after purchase, preparing it for its next use.
 	 */
 	public void resetShop() {
 		horseField.setText("0");
 		clothingField.setText("0");
 		bulletsOfFreedomField.setText("0");
 		spareWagonWheelField.setText("0");
 		spareWagonAxlesField.setText("0");
 		poundsOfFoodField.setText("0");
 		spareWagonToungeField.setText("0");
 	}
 	
 	/**
 	 * Updates the JLabels in the "Check Travel Supplies" window in the "Trail Options" menu.
 	 */
 	public void updateSupplies() {
 		lblHorsesSply.setText(Integer.toString(Horses.getQuantity()));
 		lblClothingSply.setText(Integer.toString(Clothing.getQuantity()));
 		lblAmmunitionSply.setText(Integer.toString(Ammunition.getQuantity()));
 		lblFoodSply.setText(Integer.toString(Food.getQuantity()));
 		lblWheelSply.setText(Integer.toString(WagonWheel.getQuantity()));
 		lblTongueSply.setText(Integer.toString(WagonTongue.getQuantity()));
 		lblAxelSply.setText(Integer.toString(WagonAxel.getQuantity()));
 		lblMoneySply.setText("$" + Integer.toString(Player1.getBalance()));
 	}
 	
 	/**
 	 * Updates the JLabels in the RIVER card when a player reaches a river.
 	 */
 	public void setRiverInfo() {
 		lblWidth.setText(Integer.toString(CurrentRiver.getRiverWidth()));
 		lblDepth.setText(Integer.toString(CurrentRiver.getRiverDepth()));
 		lblFlowRate.setText(Integer.toString(CurrentRiver.getFlowRate()));
 	}
 	
 	/**
 	 * Checks to see if the player has lost or not based on health
 	 * @return boolean true if the player has lost, false otherwise
 	 */
 	public boolean hasLost() {
 		// Is the wagon health over the threshold for losing?
		if (WagonCrew.calculateTotalHealth(addedHealth) > 630) return true;
		else return false;
	}
}
