package main;

import java.awt.Graphics;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame window=new JFrame();
	
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.setTitle("Game 1");
		window.setResizable(false);
		
		GamePanel gamepanel= new GamePanel();
		window.add(gamepanel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	
		gamepanel.startGameThread();
	}
}
