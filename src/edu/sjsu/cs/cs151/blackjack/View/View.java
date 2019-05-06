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
import edu.sjsu.cs.cs151.blackjack.Model.Card;
import edu.sjsu.cs.cs151.blackjack.Model.Card.Rank;
import edu.sjsu.cs.cs151.blackjack.Model.Card.Suit;

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

public class View extends JFrame {
	
	List<JLabel> dealerCardList;
	List<JLabel> playerCardList;
	int dScore;
	int pScore;
	
	public View(BlockingQueue<Message> queue) {
		
		this.queue = queue;
		/*
		 * FRAME INIT
		 */
		JFrame frame = new JFrame("Title Screen");
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_X, FRAME_Y);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		CardLayout cardLay = (CardLayout) frame.getContentPane().getLayout();

		
		/*
		 * TITLE SCREEN INIT
		 */
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBackground(Color.BLACK);
		frame.getContentPane().add(welcomePanel, "name_391590309353594");
		welcomePanel.setLayout(new BorderLayout(0, 0));

		JButton btnPlay = new JButton("PLAY!");
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
		Font font = new Font("Serif", Font.ITALIC, fontSize);
		titleText.setFont(font);
		titleText.setForeground(Color.RED);
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
		frame.getContentPane().add(betPanel, "name_391746775455469");

		textField = new JTextField();
		textField.setBounds(282, 207, 95, 30);
		betPanel.add(textField);

		JSlider slider = new JSlider(0, 1000, 10);
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
				slider.setValue(0);
				if (!typed.matches("\\d+") || typed.length() > 3) {
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

		JLabel label_1 = new JLabel("Balance: $");
		label_1.setForeground(new Color(255, 215, 0));
		label_1.setFont(new Font("Times New Roman", Font.ITALIC, 19));
		label_1.setBounds(282, 306, 84, 23);
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
		frame.getContentPane().add(tablePanel, "name_392882163153992");
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
		
		JMenuItem mntmRestart = new JMenuItem("Restart");
		mnGame.add(mntmRestart);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmInstructions = new JMenuItem("Instructions");
		mnHelp.add(mntmInstructions);
		
		JMenuItem mntmTipsTricks = new JMenuItem("Tips & Tricks");
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
		
		dealerCardList = new ArrayList<>();
		
		JLabel dealerCard1 = new JLabel("");
		dealerCard1.setIcon(cardMap.get("red_back"));
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
		
		playerCardList = new ArrayList<>();
		
		JLabel playerCard1 = new JLabel("");
		playerCard1.setIcon(cardMap.get("ACE of SPADES"));
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
		
		JLabel lblScoreDealer = new JLabel("SCORE: " + dScore);
		lblScoreDealer.setBounds(169, 127, 92, 26);
		cardPanel.add(lblScoreDealer);
		
		JLabel lblScorePlayer = new JLabel("SCORE: " + pScore);
		lblScorePlayer.setBounds(169, 426, 92, 26);
		cardPanel.add(lblScorePlayer);
	}
		


	
	public void update(GameInfo info) {
		//Note: these loops below have some bugs in the ordering of the card list
		// Update dealers cards to correct images
		for(JLabel cardIcon : dealerCardList)
			for(Card card : info.getDealerCards())
				cardIcon.setIcon(cardMap.get(card.toString()));
		
		// Update players cards to correct images
		for(JLabel cardIcon : playerCardList)
			for(Card card : info.getPlayerCards())
				cardIcon.setIcon(cardMap.get(card.toString()));
		
		// Update scores
		dScore = info.getDealerScore();
		pScore = info.getPlayerScore();
		
		// TODO: Update pot display goes here
		//
		//
		//
		
	}
	
	
	
	
	
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
		
		// Assign each .png file to an appropriate name
		// Key = "FIVE of HEARTS" -> Value = "/5H.png" image
		// Key = "ACE_LOW of DIAMONDS" -> Value = "/AD.png" image
		// etc.
		String card;
		int index = 0;
		for(Suit s: Card.Suit.values())
			for(Rank r: Card.Rank.values()) {
				card = r.toString() + " of " + s.toString();
				cardMap.put(card, cardImages.get(index++));
			}
		
		// Assign back of card it's image
		cardMap.put("red_back", resizeCard(new ImageIcon(this.getClass().getResource("/" + "red_back.png"))));
	}
	
	// Resizes a card to properly display it
	private ImageIcon resizeCard(ImageIcon card) {
		ImageIcon imageIcon = new ImageIcon(card.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_DEFAULT));
		return imageIcon;
	}
	
	public static View init(BlockingQueue<Message> queue) {
		return new View(queue);
	}
	
//	// Build JFrame and run it
//	public static void main(String[] args) {
//		new View();
//	}

	// J Frame Size
	final static int FRAME_X = 1500;
	final static int FRAME_Y = 700;
	private JTextField textField;
	// Card Display variables
	private Map<String,ImageIcon> cardMap;
	private BlockingQueue<Message> queue;
	private final int CARD_WIDTH = 150;
	private final int CARD_HEIGHT = 200;
}
