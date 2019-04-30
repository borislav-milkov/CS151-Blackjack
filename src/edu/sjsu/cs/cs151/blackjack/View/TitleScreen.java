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
import javax.xml.soap.Node;

import edu.sjsu.cs.cs151.blackjack.Model.Blackjack;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

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
				
				JPanel panel = new JPanel();
				frame.getContentPane().add(panel, BorderLayout.NORTH);
						panel.setLayout(new BorderLayout(0, 0));
						
						JButton btnPlay = new JButton("PLAY!");
						frame.getContentPane().add(btnPlay, BorderLayout.SOUTH);
						// Init title text
								JLabel titleText = new JLabel("BLACKJACK");
								frame.getContentPane().add(titleText, BorderLayout.CENTER);
								titleText.setFont(font);
								titleText.setForeground(Color.RED);
								titleText.setLocation(START_X, START_Y);
						btnPlay.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								changeToBet(frame);
							}
						});
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
	
	private void changeToBet(JFrame frame) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(new BettingScreen().getBetPanel());
		frame.setVisible(true);
	}

	// Build JFrame and run it
	public static void main(String[] args) {
		new TitleScreen();
	}
	// J Frame Size
	final static int FRAME_X = 1000;
	final static int FRAME_Y = 500;
}
