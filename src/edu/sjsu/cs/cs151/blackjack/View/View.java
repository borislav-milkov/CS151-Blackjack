package edu.sjsu.cs.cs151.blackjack.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.soap.Node;

import edu.sjsu.cs.cs151.blackjack.Controller.GameInfo;
import edu.sjsu.cs.cs151.blackjack.Controller.Message;

import java.util.*;
import java.util.concurrent.BlockingQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * A blackjack GUI implemented with Java swing, responsible for displaying information 
 * and allowing the user to interact with the game.
 *
 */
public class View extends JFrame {
	
	/**
	 * Restarts the GUI for a new game.
	 * @param queue		clean message queue for new game
	 * @return			new View
	 */
	public View restart(BlockingQueue<Message> queue) {
		frame.dispose();
		return new View(queue);
	}
	
	/**
	 * Constructs a new View with a given message queue.
	 * @param queue		clean message queue for new game
	 */
	public View(BlockingQueue<Message> queue) {
		this.queue = queue;
		
		/*
		 * FRAME INIT
		 */
		frame = new JFrame("Blackjack");
		Image frameIcon = new ImageIcon(this.getClass().getResource("/cards_Icon.png")).getImage();
		frame.setIconImage(frameIcon);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_X, FRAME_Y);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		cardLay = (CardLayout) frame.getContentPane().getLayout();

		
		/*
		 * TITLE SCREEN INIT
		 */
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBackground(new Color(0,100,0));
		frame.getContentPane().add(welcomePanel, "WELCOME");
		welcomePanel.setLayout(new BorderLayout(0, 0));

		JButton btnPlay = new JButton("PLAY!");
		btnPlay.setPreferredSize(new Dimension(30, 50));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("PLAY!");
				cardLay.next(frame.getContentPane());
			}
		});
		welcomePanel.add(btnPlay, BorderLayout.SOUTH);
		
		// Init title text
		final int START_X = 500;
		final int START_Y = 250;
		JLabel titleText = new JLabel("BLACKJACK");
		welcomePanel.add(titleText, BorderLayout.CENTER);
		int fontSize = 100;
		Font font = new Font("Serif", Font.BOLD, fontSize);
		titleText.setFont(font);
		titleText.setForeground(new Color(255, 215, 0));
		titleText.setLocation(START_X, START_Y);
		
		// Animate text with Timer
		final int DELAY = 20;
		final int TEXT_VELOCITY = 2;
		Timer t = new Timer(DELAY, event -> {
			titleText.setLocation(titleText.getLocation().x + TEXT_VELOCITY, titleText.getLocation().y);
			// Text wrapping
			if (titleText.getLocation().x > FRAME_X)
				titleText.setLocation(0, titleText.getLocation().y);
		});
		t.start();
		frame.setVisible(true);
		
		/*			 
		 * BETTING SCREEN INIT
		 */

		JPanel betPanel = new JPanel();
		betPanel.setLayout(null);
		betPanel.setBackground(new Color(0, 100, 0));
		frame.getContentPane().add(betPanel, "BET");

		textField = new JTextField();
		textField.setBounds(282, 207, 95, 30);
		betPanel.add(textField);

		JSlider slider = new JSlider(1, balance, 10);
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setBackground(new Color(0, 100, 0));
		slider.setBounds(135, 95, 72, 260);
		betPanel.add(slider);

		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				textField.setText(String.valueOf(slider.getValue()));
			}

		});

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent ke) {
				String typed = textField.getText();
				try {
					int bet = Integer.parseInt(typed);
					if(bet == 0) {bet = 1;}
				}catch(IllegalArgumentException e) {
					textField.setText("1"); // set text to 1 if illegal arguments are typed
				}
				slider.setValue(0);
				if (!typed.matches("\\d+") || Integer.parseInt(typed) < 1) {
					textField.setText("");
					return;
				}
				int value = Integer.parseInt(typed);
				slider.setValue(value);
			}
		});

		JLabel label = new JLabel("Bet Amount:");
		label.setForeground(new Color(255, 215, 0));
		label.setFont(new Font("Times New Roman", Font.ITALIC, 19));
		label.setBounds(282, 163, 95, 23);
		betPanel.add(label);

		JLabel label_1 = new JLabel("Balance: $" + balance);
		label_1.setForeground(new Color(255, 215, 0));
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 19));
		label_1.setBounds(282, 306, 187, 23);
		betPanel.add(label_1);

		JButton button = new JButton("BET");
		button.addActionListener(new BetListener(this.queue ,textField, cardLay, frame));
		button.setBackground(Color.GREEN);
		button.setBounds(443, 206, 72, 31);
		betPanel.add(button);
		
		/*
		 * GAME TABLE INIT
		 */
		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablePanel.setBackground(new Color(0, 128, 0));
		frame.getContentPane().add(tablePanel, "TABLE");
		tablePanel.setLayout(new BorderLayout(0, 0));
		
		// Initialize cards to be displayed
		initializeCardIcons();

		JPanel btnPanel = new JPanel();
		btnPanel.setPreferredSize(new Dimension(10, 60));
		btnPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tablePanel.add(btnPanel, BorderLayout.SOUTH);
		btnPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		// STAND & action listener
		JButton btnStand = new JButton("Stand");
		btnPanel.add(btnStand);
		btnStand.addActionListener(new StandListener(this.queue));
		
		// HIT & action listener
		JButton btnHit = new JButton("Hit");
		btnPanel.add(btnHit);
		btnHit.addActionListener(new HitListener(this.queue));
		
		// DOUBLE DOWN & action listener
		JButton btnDouble = new JButton("Double Down");
		btnPanel.add(btnDouble);
		btnDouble.addActionListener(new DoubleListener(this.queue));
		
		JMenuBar menuBar = new JMenuBar();
		tablePanel.add(menuBar, BorderLayout.NORTH);
		menuBar.setMaximumSize(new Dimension(0, 10));
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		//TODO: Get Restart working
		JMenuItem mntmRestart = new JMenuItem("Restart");
		mnGame.add(mntmRestart);
		mntmRestart.addActionListener(new NewGameListener(this.queue));
		
		/*
		 * JMENU Items & Actions
		 */
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		class instructionsAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent instructEvent) {
				ImageIcon instructionText = new ImageIcon(this.getClass().getResource("/instructions.jpg"));
				JFrame instructions = new JFrame("Instructions");
				instructions.setIconImage(frameIcon);
                instructions.setVisible(true);
                instructions.setSize(new Dimension(1500,2000));
                JLabel instrLabel = new JLabel();
                JPanel instrPanel = new JPanel();
                instrPanel.setBackground(Color.WHITE);
                instrLabel.setIcon(instructionText);
                instrPanel.add(instrLabel);
                instructions.add(instrPanel);
			}
		}
		
		JMenuItem mntmInstructions = new JMenuItem("Instructions");
		mntmInstructions.addActionListener(new instructionsAction());
		mnHelp.add(mntmInstructions);
		
		class tipsAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent instructEvent) {
				ImageIcon tipsText = new ImageIcon(this.getClass().getResource("/strategy.jpg"));
				ImageIcon resizedTips = new ImageIcon(tipsText.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT));
				JFrame tipsAndTricks = new JFrame("Blackjack Strategy");
				tipsAndTricks.setIconImage(frameIcon);
                tipsAndTricks.setVisible(true);
                tipsAndTricks.setSize(new Dimension(1600,850));
                JLabel tipsLabel = new JLabel();
                JPanel tipsPanel = new JPanel();
                
                tipsPanel.setBackground(Color.WHITE);
                tipsLabel.setIcon(resizedTips);
                tipsPanel.add(tipsLabel);
                tipsAndTricks.add(tipsPanel);
			}
		}
		
		JMenuItem mntmTipsTricks = new JMenuItem("Tips & Tricks");
		mntmTipsTricks.addActionListener(new tipsAction());
		mnHelp.add(mntmTipsTricks);
		
		JPanel cardPanel = new JPanel();
		cardPanel.setOpaque(false);
		tablePanel.add(cardPanel, BorderLayout.CENTER);
		cardPanel.setLayout(null);
		
		JLabel lblPlayer = new JLabel("PLAYER");
		lblPlayer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
		lblPlayer.setBounds(159, 401, 125, 26);
		cardPanel.add(lblPlayer);
		
		JLabel lblDealer = new JLabel("DEALER");
		lblDealer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
		lblDealer.setBounds(159, 103, 125, 26);
		cardPanel.add(lblDealer);
		
		/*
		 * JLABEL DISPLAY HANDLERS
		 */
		dealerCardList = new ArrayList<>();
		
		JLabel dealerCard1 = new JLabel("");
		//dealerCard1.setIcon(cardMap.get("red_back"));
		dealerCard1.setBounds(293, 21, 150, 200);
		cardPanel.add(dealerCard1);
		dealerCardList.add(dealerCard1);
		
		JLabel dealerCard2 = new JLabel("");
		dealerCard2.setIcon(null);
		dealerCard2.setBounds(464, 21, 150, 200);
		cardPanel.add(dealerCard2);
		dealerCardList.add(dealerCard2);
		
		JLabel dealerCard3 = new JLabel("");
		dealerCard3.setIcon(null);
		dealerCard3.setBounds(635, 21, 150, 200);
		cardPanel.add(dealerCard3);
		dealerCardList.add(dealerCard3);
		
		JLabel dealerCard4 = new JLabel("");
		dealerCard4.setIcon(null);
		dealerCard4.setBounds(806, 21, 150, 200);
		cardPanel.add(dealerCard4);
		dealerCardList.add(dealerCard4);
		
		JLabel dealerCard5 = new JLabel("");
		dealerCard5.setIcon(null);
		dealerCard5.setBounds(977, 21, 150, 200);
		cardPanel.add(dealerCard5);
		dealerCardList.add(dealerCard5);
		
		JLabel dealerCard6 = new JLabel("");
		dealerCard6.setIcon(null);
		dealerCard6.setBounds(977+171, 21, 150, 200);
		cardPanel.add(dealerCard6);
		dealerCardList.add(dealerCard6);
		
		playerCardList = new ArrayList<>();
		
		JLabel playerCard1 = new JLabel("");
		playerCard1.setIcon(null);
		playerCard1.setBounds(293, 316, 150, 200);
		cardPanel.add(playerCard1);
		playerCardList.add(playerCard1);
		
		JLabel playerCard2 = new JLabel("");
		playerCard2.setIcon(null);
		playerCard2.setBounds(464, 316, 150, 200);
		cardPanel.add(playerCard2);
		playerCardList.add(playerCard2);
		
		JLabel playerCard3 = new JLabel("");
		playerCard3.setIcon(null);
		playerCard3.setBounds(635, 316, 150, 200);
		cardPanel.add(playerCard3);
		playerCardList.add(playerCard3);
		
		JLabel playerCard4 = new JLabel("");
		playerCard4.setIcon(null);
		playerCard4.setBounds(806, 316, 150, 200);
		cardPanel.add(playerCard4);
		playerCardList.add(playerCard4);
		
		JLabel playerCard5 = new JLabel("");
		playerCard5.setIcon(null);
		playerCard5.setBounds(977, 316, 150, 200);
		cardPanel.add(playerCard5);
		playerCardList.add(playerCard5);
		
		JLabel playerCard6 = new JLabel("");
		playerCard6.setIcon(null);
		playerCard6.setBounds(977+171, 316, 150, 200);
		cardPanel.add(playerCard6);
		playerCardList.add(playerCard6);
		
		lblScoreDealer = new JLabel("SCORE: 0");
		lblScoreDealer.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblScoreDealer.setBounds(169, 127, 92, 26);
		cardPanel.add(lblScoreDealer);
		
		lblScorePlayer = new JLabel("SCORE: 0");
		lblScorePlayer.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblScorePlayer.setBounds(169, 426, 92, 26);
		cardPanel.add(lblScorePlayer);
		
		balanceTableLabel = new JLabel("Balance: $" + balance);
		balanceTableLabel.setBounds(1203, 238, 200, 48);
		cardPanel.add(balanceTableLabel);
		balanceTableLabel.setForeground(new Color(255, 215, 0));
		balanceTableLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		
		lblPot = new JLabel("POT: $" + pot);
		lblPot.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPot.setIcon(new ImageIcon(this.getClass().getResource("/pot_Icon.png")));
		lblPot.setForeground(new Color(255, 215, 0));
		lblPot.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblPot.setBounds(558, 238, 325, 48);
		cardPanel.add(lblPot);
		
		lblResult = new JLabel("");
		lblResult.setForeground(new Color(255, 215, 0));
		lblResult.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		lblResult.setBounds(40, 238, 348, 48);
		cardPanel.add(lblResult);
		
	}

	/**
	 * Redisplays the View with updated values from the passed in information.
	 * @param info	updated information from the current game state
	 */
	public void repaint(GameInfo info) {
		
		dealerFaceUp = dealerFaceUp? true : info.getDealerFaceUp();
		
		// Update dealers cards to correct images
		String[] dealerHand = (String[]) info.getDealerCards().stream().toArray();

		int dealerScore;

		if(dealerFaceUp) {
			for(int i = 0; i<dealerHand.length; i++) {
				// Display every card in the dealers hand at its proper location
				String currentCard = dealerHand[i];
				displayDealerCard(currentCard, i);
			}
			dealerScore = info.getDealerScore();
		}else {
			displayInitialDealerCards(dealerHand[1]);
			dealerScore = info.getDealersHiddenScore();
		}
		
		// Update players cards to correct images
		String[] playerHand = (String[]) info.getPlayerCards().stream().toArray();
		for(int i = 0; i<playerHand.length; i++) {
			String currentCard = playerHand[i];
			displayPlayerCard(currentCard, i);
		}
		// Update scoreboard
		lblScoreDealer.setText("SCORE: " + dealerScore);
		lblScorePlayer.setText("SCORE: " + info.getPlayerScore());
		
		balance = info.getBalance();
		balanceTableLabel.setText("Balance: $" + balance);
		
		pot = info.getPot();
		
		lblPot.setText("POT: $" + pot);
		playerBust = info.isPlayerBust();
		dealerBust = info.isDealerBust();
		winner = info.getWinner();
		// Print win status to user
		if(playerBust) {
			lblResult.setText("You Busted. Dealer Wins");
		}else if(dealerBust) {
			lblResult.setText("Dealer Busted. You Win!");
		}else if(winner != null){
			lblResult.setText(winner + " Win!");
		}
		
		
	}
	
	
	
	
	/**
	 * Updates the display of a dealer's card at the specified position.
	 * 
	 * EX:
	 * displayDealerCard("ACE of SPADES",0) will show an ace @ the dealer's first card on the board
	 * displayDealerCard("FOUR of CLUBS", 4) will show a four @ the dealer's fifth card on the board
	 * @param card		the card to display
	 * @param position	0-based location in the dealer's hand of the card
	 */
	private void displayDealerCard(String card, int position) {
		JLabel[] dealerCards = dealerCardList.stream().toArray(JLabel[] ::new);
		dealerCards[position].setIcon(cardMap.get(card));
	}
	
	/**
	 * Updates the display of a player's card at the specified position.
	 * 
	 * EX:
	 * displayDealerCard("ACE of SPADES",0) will show an ace @ the player's first card on the board
	 * displayDealerCard("FOUR of CLUBS", 4) will show a four @ the player's fifth card on the board
	 * @param card		the card to display
	 * @param position	0-based location in the player's hand of the card
	 */
	private void displayPlayerCard(String card, int position) {
		JLabel[] playerCards = playerCardList.stream().toArray(JLabel[] ::new);
		playerCards[position].setIcon(cardMap.get(card));
	}
	
	/**
	 * Displays the players first two cards, face up.
	 * @param card1		first card in player hand
	 * @param card2		second card in player hand
	 */
	private void displayInitialPlayerCards(String card1, String card2) {
		displayPlayerCard(card1, 0);	// face up
		displayPlayerCard(card2, 1);	// face up
	}
	
	/**
	 * Displays the dealers first two cards, the first card always begins face down.
	 * @param card2		second card in dealer hand
	 */
	private void displayInitialDealerCards(String card2) {
		// Display dealer's first two cards
		displayDealerCard("red_back", 0); // face down
		displayDealerCard(card2, 1);	  // face up
	}
	
	//TODO: double check changes made to this method didn't cause any bugs
	/**
	 * Initializes card images by mapping each card to it's respective icon.
	 *  
	 *  key = card name, value = .png file
	 *  EX:
		Key = "FIVE of HEARTS" -> Value = "/5H.png" image
		Key = "ACE_LOW of DIAMONDS" -> Value = "/AD.png" image
	 */
	private void initializeCardIcons() {
		cardMap = new HashMap<String, ImageIcon>();
		
		// Build the filepath strings for each .png file
		char[] suit = {'S', 'H', 'D', 'C'};
		String[] rank = {"A","2","3","4","5","6","7","8","9","10","J","Q","K","A"};
		ArrayList<ImageIcon> cardImages = new ArrayList<>();
		
		String filename;
		for(int i = 0; i<suit.length;i++)
			for(int j = 0; j<rank.length; j++) {
				 filename = rank[j] + suit[i] + ".png";
				 cardImages.add(resizeCard(new ImageIcon(this.getClass().getResource("/" + filename))));
			}
		
		// Build the keyset for the card map, and assign each card to it's appropriate key
		String[] suits = {"SPADES", "HEARTS", "DIAMONDS", "CLUBS"};
		String[] ranks = {"ACE_LOW", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", 
							"EIGHT", "NINE", "TEN", "JACK", "QUEEN", "KING", "ACE"};
		String card;
		int index = 0;
		for(String s: suits)
			for(String r: ranks) {
				card = r + " of " + s;
				cardMap.put(card, cardImages.get(index++));
			}
		
		// Assign back of card it's image
		cardMap.put("red_back", resizeCard(new ImageIcon(this.getClass().getResource("/" + "red_back.png"))));
	}
	
	/**
	 * Helper function to resize a card with the proper display values.
	 * @param card	card image to resize
	 * @return		resized card image
	 */
	private ImageIcon resizeCard(ImageIcon card) {
		ImageIcon imageIcon = new ImageIcon(card.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_DEFAULT));
		return imageIcon;
	}
	
	public static View init(BlockingQueue<Message> queue) {
		return new View(queue);
	}
	
	public void switchScreen() {
		cardLay.next(frame.getContentPane());
	}
	
	public void showTableScreen() {
		cardLay.show(frame.getContentPane(), "TABLE");
	}

	// JFrame vars
	private JFrame frame;
	private CardLayout cardLay;
	private final static int FRAME_X = 1500;
	private final static int FRAME_Y = 700;
	private JTextField textField;
	// Card Display vars
	private static final int CARD_WIDTH = 150;
	private static final int CARD_HEIGHT = 200;
	private Map<String,ImageIcon> cardMap;
	// View Update vars
	private BlockingQueue<Message> queue;
	private List<JLabel> dealerCardList;
	private List<JLabel> playerCardList;
	private JLabel lblScorePlayer;
	private JLabel lblScoreDealer;
	private JLabel balanceTableLabel;
	private Gambler winner;
	private JLabel lblPot;
	private JLabel lblResult;
	private int balance = 1000;
	private int pot;
	private boolean dealerFaceUp = false;
	private boolean playerBust;
	private boolean dealerBust;
}
