package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import Entity.Colony;
import graphicsElements.Window;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	//Dimensions
	public final int scale=3;
	public final int originalTileSize=16;
	public final int tileSize=originalTileSize*scale;
	public int colTela=30;
	public int linTela=14;
	public int telaLarg=colTela*tileSize;
	public int telaAlt=linTela*tileSize;
	//FPS
	public final int FPS=45;
	//Essential
	Thread gameThread;
	public int hora=0,dia=0,semana=0;
	
	public KeyHeadler keyH=new KeyHeadler();
	public MouseHeadler mouseH=new MouseHeadler();
	//Screen
	public int menuTX=telaLarg/2-480;
	public int menuTY=tileSize*2;
	public Window menuT=new Window(this,menuTX,menuTY,480,480);
	//Entity
	Colony colony;
	//Map
	public TileManager tileM=new TileManager(this);
	
	
	public GamePanel(){
		this.setPreferredSize(new Dimension (telaLarg,telaAlt ));
		this.setBackground(Color.green);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.addMouseListener(mouseH);
		this.setFocusable(true);
		this.colony=new Colony(this);
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
				remainingTime=remainingTime/1000000;
				if(remainingTime<0) {
					remainingTime=0;
				}
				Thread.sleep((long)remainingTime);
				nextDrawTime+=drawInterval;
				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		timer();
	    colony.update();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		tileM.draw(g2);
		colony.draw(g2);
		menuT.inventory(g2);
		menuT.cicloTempo(g2, this);
		if(menuT.ativo)
			menuT.draw(g2);
		g2.dispose();
	}
	public void timer() {
		hora+=1;
		if(hora==100) {
		hora=0;
		dia+=1;
		}
		if(dia==7) {
			dia=0;
			semana+=1;
		}
	}
	
}
