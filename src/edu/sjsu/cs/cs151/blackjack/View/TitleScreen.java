package edu.sjsu.cs.cs151.blackjack.View;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class TitleScreen extends JPanel {

	// Build JFrame and run it
	public static void main(String[] args) {
		// Init title text
		JLabel titleText = new JLabel("BLACKJACK");
		// Customize the font settings & appearance
		int fontSize = 100;
		Font font = new Font("Serif", Font.ITALIC, fontSize);
		titleText.setFont(font);
		titleText.setForeground(Color.RED);
		// Init Jframe 
		final int START_X = 500;
		final int START_Y = 250;
		JFrame frame = new JFrame("Title Screen");
		frame.getContentPane().setBackground( Color.BLACK );
		frame.getContentPane().add(titleText);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_X, FRAME_Y);
		frame.setVisible(true);
		titleText.setLocation(START_X, START_Y);
		// Animate text with Timer
		final int DELAY = 20;
		final int TEXT_VELOCITY = 2;
		Timer t = new Timer(DELAY, event -> { 
			titleText.setLocation(titleText.getLocation().x+TEXT_VELOCITY, titleText.getLocation().y); 
			// Text wrapping
			if (titleText.getLocation().x > FRAME_X)
				titleText.setLocation(0, titleText.getLocation().y);
			
		}); 
		
		t.start(); 
	}
	// J Frame Size
	final static int FRAME_X = 1000;
	final static int FRAME_Y = 500;
}
