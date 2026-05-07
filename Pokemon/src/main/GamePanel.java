package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

import entity.Pokemon;
public class GamePanel extends JPanel implements Runnable{
	int originalTileSize=16;
	int scale=3;
	public int tileSize=scale*originalTileSize;
	int FPS=60;
	int screenCol=16;
	int screenRow=12;
	int screenMaxCol=tileSize*screenCol;
	int screenMaxRow=tileSize*screenRow;
	int x1=4*tileSize,y1=8*tileSize;
	int x2=10*tileSize,y2=3*tileSize;
	
	Thread gameThread=new Thread();
	
	
	
	Pokemon pokemon1=new Pokemon(this,x1,y1);
	Pokemon pokemon2=new Pokemon(this,x2,y2);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenMaxCol,screenMaxRow));
		this.setBackground(Color.WHITE);
	}
	public void run() {
		double drawInterval=1000000000/FPS;
		double nextDrawTime=System.nanoTime()+drawInterval;
		while(gameThread!=null) {
			//Update: update informations such as player position.
			 //Draw: draw t he screen with the updated informations.
			update();
			repaint();   
			try {
				double remainingTime=nextDrawTime-System.nanoTime();
				remainingTime= remainingTime/1000000;
				
				if(remainingTime<0) {
					remainingTime=0;
				}
				Thread.sleep((long) remainingTime);
				nextDrawTime+=drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void update() {
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		pokemon1.draw(g2);
		pokemon2.draw(g2);
		
		
		g2.dispose();
	}
}
