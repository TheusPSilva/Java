package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import entity.Colon;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	final int originalTileSize=16;
	final int scale=3;
	public final int tileSize=scale*originalTileSize;
	public final int maxScreenCol=16;
	public final int maxScreenRow=12;
	final int screenWidht=tileSize*maxScreenCol;
	final int screenHeight=tileSize*maxScreenRow;
	
	int FPS=60;
	TileManager tileM=new TileManager(this);
	Colon colon=new Colon(this);
	Colon colon2=new Colon(this);
	Thread gameThread;
	
	public GamePanel(){
		this.setPreferredSize(new Dimension(screenWidht,screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
	}
	public void startGameThread() {
		gameThread=new Thread (this);
		gameThread.start();
		
	}
	@Override
	public void run() {
		double drawInterval=1000000000/FPS;
		double nextDrawTime=System.nanoTime()+drawInterval;
		
		while(gameThread!=null) {
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
		tileM.draw(g2);
		colon.draw(g2);
		colon2.draw(g2);
		g2.dispose();
	}

}
