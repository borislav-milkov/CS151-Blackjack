package edu.sjsu.cs.cs151.blackjack.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSlider;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;

public class BettingScreen {

	private JFrame frame;
	private JTextField textField;
	private JSlider slider;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BettingScreen window = new BettingScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BettingScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.getContentPane().setBackground(new Color(0, 100, 0));
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(175, 131, 102, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		slider = new JSlider();
		slider.setValue(500);
		slider.setMaximum(1000);
		slider.setBackground(new Color(0, 100, 0));
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setBounds(22, 11, 122, 239);
		frame.getContentPane().add(slider);
		
		JLabel lblNewLabel = new JLabel("Bet Amount:");
		lblNewLabel.setForeground(new Color(255, 215, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 19));
		lblNewLabel.setBounds(175, 88, 107, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblBalance = new JLabel("Balance: $");
		lblBalance.setForeground(new Color(255, 215, 0));
		lblBalance.setFont(new Font("Times New Roman", Font.ITALIC, 19));
		lblBalance.setBounds(198, 226, 226, 24);
		frame.getContentPane().add(lblBalance);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
