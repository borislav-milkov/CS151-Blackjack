package edu.sjsu.cs.cs151.blackjack.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSlider;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

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
		
		slider = new JSlider(0, 100, 50);
		
		textField = new JTextField();
		textField.setBounds(175, 131, 102, 35);
		
		slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                textField.setText(String.valueOf(slider.getValue()));
            }

        });
        textField.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent ke) {
                String typed = textField.getText();
                slider.setValue(0);
                if(!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                slider.setValue(value);
            }
        });
		frame.getContentPane().add(textField);
		
		slider.setValue(10);
		slider.setMaximum(1000);
		slider.setBackground(new Color(0, 100, 0));
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setBounds(23, 11, 142, 282);
		frame.getContentPane().add(slider);
		
		JLabel lblNewLabel = new JLabel("Bet Amount:");
		lblNewLabel.setForeground(new Color(255, 215, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 19));
		lblNewLabel.setBounds(175, 88, 107, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblBalance = new JLabel("Balance: $");
		lblBalance.setForeground(new Color(255, 215, 0));
		lblBalance.setFont(new Font("Times New Roman", Font.ITALIC, 19));
		lblBalance.setBounds(175, 227, 226, 24);
		frame.getContentPane().add(lblBalance);
		
		JButton btnBet = new JButton("BET");
		btnBet.setBackground(Color.GREEN);
		btnBet.setBounds(323, 137, 89, 23);
		frame.getContentPane().add(btnBet);
		frame.setBounds(100, 100, 799, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
