package edu.sjsu.cs.cs151.blackjack.View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSlider;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class BettingScreen {

	private JFrame frame;
	private JTextField textField;
	private JSlider slider;
	private JPanel panel;

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
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public JPanel getBetPanel() {
		return this.panel;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.getContentPane().setBackground(new Color(0, 100, 0));
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(0,100,0));
		panel.setBounds(0, 0, 783, 546);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(282, 207, 95, 30);
		panel.add(textField);
		
		slider = new JSlider(0, 100, 50);
		slider.setBounds(135, 95, 72, 260);
		panel.add(slider);
		
		slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                textField.setText(String.valueOf(slider.getValue()));
            }

        });
		
		slider.setValue(10);
		slider.setMaximum(1000);
		slider.setBackground(new Color(0, 100, 0));
		slider.setOrientation(SwingConstants.VERTICAL);
		
		JLabel lblNewLabel = new JLabel("Bet Amount:");
		lblNewLabel.setBounds(282, 163, 95, 23);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(255, 215, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 19));
		
		JLabel lblBalance = new JLabel("Balance: $");
		lblBalance.setBounds(282, 306, 84, 23);
		panel.add(lblBalance);
		lblBalance.setForeground(new Color(255, 215, 0));
		lblBalance.setFont(new Font("Times New Roman", Font.ITALIC, 19));
		
		JButton btnBet = new JButton("BET");
		btnBet.setBounds(443, 206, 72, 31);
		panel.add(btnBet);
		btnBet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBet.setBackground(Color.GREEN);
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
		frame.setBounds(100, 100, 799, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
