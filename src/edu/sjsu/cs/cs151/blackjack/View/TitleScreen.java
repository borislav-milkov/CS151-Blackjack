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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.soap.Node;

import java.util.*;
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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class TitleScreen extends JFrame {
	public TitleScreen() {
				// Customize the font settings & appearance
				int fontSize = 100;
				Font font = new Font("Serif", Font.ITALIC, fontSize);
				// Init Jframe 
				final int START_X = 500;
				final int START_Y = 250;
				JFrame frame = new JFrame("Title Screen");
				frame.getContentPane().setBackground( Color.BLACK );
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(FRAME_X, FRAME_Y);
				frame.getContentPane().setLayout(new CardLayout(0, 0));
				CardLayout cardLay = (CardLayout) frame.getContentPane().getLayout();
				
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
										JLabel titleText = new JLabel("BLACKJACK");
										welcomePanel.add(titleText, BorderLayout.CENTER);
										titleText.setFont(font);
										titleText.setForeground(Color.RED);
										titleText.setLocation(START_X, START_Y);
										
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
										button.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												System.out.println("betting!");
												cardLay.next(frame.getContentPane());
											}
										});
										button.setBackground(Color.GREEN);
										button.setBounds(443, 206, 72, 31);
										betPanel.add(button);
										
										JPanel tablePanel = new JPanel();
										tablePanel.setBackground(new Color(0, 128, 0));
										frame.getContentPane().add(tablePanel, "name_392882163153992");
										tablePanel.setLayout(new BorderLayout(0, 0));
										
										JPanel panel_2 = new JPanel();
										tablePanel.add(panel_2, BorderLayout.SOUTH);
										panel_2.setLayout(new GridLayout(0, 3, 0, 0));
										
										JButton button_1 = new JButton("Stand");
										panel_2.add(button_1);
										
										JButton button_2 = new JButton("Hit");
										panel_2.add(button_2);
										
										JButton button_3 = new JButton("Double Down");
										panel_2.add(button_3);
										
										JMenuBar menuBar = new JMenuBar();
										menuBar.setMaximumSize(new Dimension(0, 10));
										tablePanel.add(menuBar, BorderLayout.NORTH);
										
										JMenu menu = new JMenu("Game");
										menuBar.add(menu);
										
										JMenuItem menuItem = new JMenuItem("Restart");
										menu.add(menuItem);
										
										JMenu menu_1 = new JMenu("Help");
										menuBar.add(menu_1);
										
										JMenuItem menuItem_1 = new JMenuItem("Instructions");
										menu_1.add(menuItem_1);
										
										JMenuItem menuItem_2 = new JMenuItem("Tips & Tricks");
										menu_1.add(menuItem_2);
			
				//Animate text with Timer
				final int DELAY = 20;
				final int TEXT_VELOCITY = 2;
				Timer t = new Timer(DELAY, event -> { 
					titleText.setLocation(titleText.getLocation().x+TEXT_VELOCITY, titleText.getLocation().y); 
					// Text wrapping
					if (titleText.getLocation().x > FRAME_X)
						titleText.setLocation(0, titleText.getLocation().y);
					
				}); 
				
				t.start(); 
				frame.setVisible(true);
	}
	
	// Build JFrame and run it
	public static void main(String[] args) {
		new TitleScreen();
	}
	// J Frame Size
	final static int FRAME_X = 1000;
	final static int FRAME_Y = 500;
	private JTextField textField;
}
