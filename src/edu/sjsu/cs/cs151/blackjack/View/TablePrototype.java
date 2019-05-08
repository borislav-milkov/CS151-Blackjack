package edu.sjsu.cs.cs151.blackjack.View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.border.*;

import edu.sjsu.cs.cs151.blackjack.Model.*;
import edu.sjsu.cs.cs151.blackjack.Model.Card.Rank;
import edu.sjsu.cs.cs151.blackjack.Model.Card.Suit;

public class TablePrototype{
	private Map<String,ImageIcon> cardMap;
	private final int CARD_WIDTH = 150;
	private final int CARD_HEIGHT = 200;
	
	private JFrame frmBlackjack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablePrototype window = new TablePrototype();
					window.frmBlackjack.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
			
	}

	/**
	 * Create the application.
	 */
	public TablePrototype() {
		initializeCardIcons();
		initialize();
		
		
		
			
	}
	
	
	public JFrame getFrame() {
		return this.frmBlackjack;
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBlackjack = new JFrame();
		frmBlackjack.setPreferredSize(new Dimension(1500, 700));
		frmBlackjack.setSize(new Dimension(1200, 700));
		frmBlackjack.getContentPane().setMinimumSize(new Dimension(600, 400));
		frmBlackjack.setTitle("Blackjack");
		frmBlackjack.getContentPane().setBackground(new Color(0, 100, 0));
		Image frameIcon = new ImageIcon(this.getClass().getResource("/cards_Icon.png")).getImage();
		frmBlackjack.setIconImage(frameIcon);
		frmBlackjack.setBounds(100, 100, 1000, 601);
		frmBlackjack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBlackjack.pack();
		
		
		
		
		/*
		 * Initialize button panel and add buttons.
		 */
		frmBlackjack.getContentPane().setLayout(new CardLayout(0, 0));
		//TODO: Add all the updated table stuff into VIEW.java
		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablePanel.setBackground(new Color(0, 128, 0));
		frmBlackjack.getContentPane().add(tablePanel, "name_731835258992155");
		tablePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel btnPanel = new JPanel();
		btnPanel.setPreferredSize(new Dimension(10, 60));
		btnPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		tablePanel.add(btnPanel, BorderLayout.SOUTH);
		btnPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnStand = new JButton("Stand");
		btnPanel.add(btnStand);
		
		JButton btnHit = new JButton("Hit");
		btnPanel.add(btnHit);
		
		JButton btnDouble = new JButton("Double Down");
		btnPanel.add(btnDouble);
		
		JMenuBar menuBar = new JMenuBar();
		tablePanel.add(menuBar, BorderLayout.NORTH);
		menuBar.setMaximumSize(new Dimension(0, 10));
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		JMenuItem mntmRestart = new JMenuItem("Restart");
		mnGame.add(mntmRestart);
		
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
                instructions.getContentPane().add(instrPanel);
			}
		}
		
		JMenuItem mntmInstructions = new JMenuItem("Instructions");
		mnHelp.add(mntmInstructions);
		mntmInstructions.addActionListener(new instructionsAction());
		
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
                tipsAndTricks.getContentPane().add(tipsPanel);
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
		
		JLabel dealerCard1 = new JLabel("");
		dealerCard1.setIcon(cardMap.get("red_back"));
		dealerCard1.setBounds(293, 21, 150, 200);
		cardPanel.add(dealerCard1);
		
		JLabel dealerCard2 = new JLabel("");
		dealerCard2.setIcon(null);
		dealerCard2.setBounds(464, 21, 150, 200);
		cardPanel.add(dealerCard2);
		
		JLabel dealerCard3 = new JLabel("");
		dealerCard3.setIcon(null);
		dealerCard3.setBounds(635, 21, 150, 200);
		cardPanel.add(dealerCard3);
		
		JLabel dealerCard4 = new JLabel("");
		dealerCard4.setIcon(null);
		dealerCard4.setBounds(806, 21, 150, 200);
		cardPanel.add(dealerCard4);
		
		JLabel dealerCard5 = new JLabel("");
		dealerCard5.setIcon(null);
		dealerCard5.setBounds(977, 21, 150, 200);
		cardPanel.add(dealerCard5);
		
		JLabel playerCard1 = new JLabel("");
		playerCard1.setIcon(cardMap.get("ACE of SPADES"));
		playerCard1.setBounds(293, 316, 150, 200);
		cardPanel.add(playerCard1);
		
		JLabel playerCard2 = new JLabel("");
		playerCard2.setIcon(null);
		playerCard2.setBounds(464, 316, 150, 200);
		cardPanel.add(playerCard2);
		
		JLabel playerCard3 = new JLabel("");
		playerCard3.setIcon(null);
		playerCard3.setBounds(635, 316, 150, 200);
		cardPanel.add(playerCard3);
		
		JLabel playerCard4 = new JLabel("");
		playerCard4.setIcon(null);
		playerCard4.setBounds(806, 316, 150, 200);
		cardPanel.add(playerCard4);
		
		JLabel playerCard5 = new JLabel("");
		playerCard5.setIcon(null);
		playerCard5.setBounds(977, 316, 150, 200);
		cardPanel.add(playerCard5);
		
		JLabel balanceTableLabel = new JLabel("Balance: $");
		balanceTableLabel.setBounds(1203, 238, 200, 48);
		cardPanel.add(balanceTableLabel);
		balanceTableLabel.setForeground(new Color(255, 215, 0));
		balanceTableLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		
		JLabel lblPot = new JLabel("POT: $");
		lblPot.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPot.setIcon(new ImageIcon(this.getClass().getResource("/pot_Icon.png")));
		lblPot.setForeground(new Color(255, 215, 0));
		lblPot.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblPot.setBounds(558, 238, 325, 48);
		cardPanel.add(lblPot);
		
		JLabel lblResult = new JLabel("");
		lblResult.setForeground(new Color(255, 215, 0));
		lblResult.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		lblResult.setBounds(40, 238, 348, 48);
		cardPanel.add(lblResult);
		
		btnDouble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("IT's A Hit Boiss");
			}
		});
		
		
		/*
		 * Initialize JMenu and add menu items.
		 */
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
}
