package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;
import main.KeyHeadler;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	final int originalTileSize=16;
	final int scale=3;
	public final int tileSize=originalTileSize*scale;
	public final int maxScreenCol=16;//colunas
	public final int maxScreenRow=12;//linhas
	final int screenWidht=tileSize*maxScreenCol;//largura
	final int screenHeight=tileSize*maxScreenRow;//Altura 
	
	int FPS=60;  
	
	TileManager tileM=new TileManager(this);
	KeyHeadler keyH=new KeyHeadler();
	Thread gameThread;
	Player player=new Player(this,keyH);
	
	
	public GamePanel() { 
		this.setPreferredSize(new Dimension(screenWidht,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true); 
	}
	public void startGameThread() {
		gameThread=new Thread(this);
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
	//update the informations with the frames tax (FPS).
	public void update() {
		player.update();
	
	}
	//paint the tiles according to the updated informations.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		tileM.draw(g2);
		player.draw(g2);
		g2.dispose();
	}
}
