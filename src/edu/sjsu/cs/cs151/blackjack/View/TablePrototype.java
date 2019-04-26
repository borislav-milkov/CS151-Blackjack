package edu.sjsu.cs.cs151.blackjack.View;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class TablePrototype {

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBlackjack = new JFrame();
		frmBlackjack.setTitle("Blackjack");
		frmBlackjack.getContentPane().setBackground(new Color(0, 100, 0));
		Image frameIcon = new ImageIcon(this.getClass().getResource("/cards_Icon.png")).getImage();
		frmBlackjack.setIconImage(frameIcon);
		frmBlackjack.setBounds(100, 100, 600, 400);
		frmBlackjack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBlackjack.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		/*
		 * Initialize button panel and add buttons.
		 */
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		frmBlackjack.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Double Down");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_2 = new JButton("Hit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnNewButton_1 = new JButton("Stand");
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		panel.add(btnNewButton_1);
		panel.add(btnNewButton_2);
		panel.add(btnNewButton);
		
		
		/*
		 * Initialize JMenu and add menu items.
		 */
		JMenuBar menuBar = new JMenuBar();
		frmBlackjack.setJMenuBar(menuBar);
		
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
	}

}
