package main;
import javax.swing.*;
public class Main {

	public static void main(String[] args) {
		JFrame window= new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("Colony");
		window.setResizable(true);
		GamePanel gamepanel=new GamePanel();
		window.add(gamepanel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

	}

}
