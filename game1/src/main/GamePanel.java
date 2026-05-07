package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Colony;
import graphicsElements.Button;
import graphicsElements.Window;
import map.Rock;
import map.Storage;
import map.Tree;

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
	public KeyHeadler keyH=new KeyHeadler();
	public MouseHeadler mouseH=new MouseHeadler();
	//Screen
	public int menuTX=telaLarg/2-480;
	public int menuTY=tileSize*2;
	public Window menuT=new Window(this,menuTX,menuTY,480,480);
	Button botaoTarefas= new Button(tileSize*0,tileSize*1,tileSize,tileSize,"Tarefas",Color.gray);
	//Entity
	Colony colony;
	//Map
	public Rock rock;
	public Tree tree;
	public Storage storage;
	
	
	public GamePanel(){
		this.setPreferredSize(new Dimension (telaLarg,telaAlt ));
		this.setBackground(Color.green);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.addMouseListener(mouseH);
		this.setFocusable(true);
		this.storage=new Storage(this);
		this.rock=new Rock(this);
		this.tree=new Tree(this);
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
		
		if (mouseH.clicou) {
	        menuT.tratarClique(mouseH.mouseX, mouseH.mouseY, botaoTarefas);
	        
	        if (menuT.ativo) {
	            
	            if (menuT.coletarPedra.foiClicado(mouseH.mouseX, mouseH.mouseY)) {
	                menuT.coletarPedra.pressed = true;
	                menuT.coletarMadeira.pressed = false;
	            } 
	            else if (menuT.coletarMadeira.foiClicado(mouseH.mouseX, mouseH.mouseY)) {
	                menuT.coletarMadeira.pressed = true;
	                menuT.coletarPedra.pressed = false;
	            }
	        }

	        mouseH.clicou = false;
	    }
	    colony.update();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		rock.draw(g2);
		storage.draw(g2);
		tree.draw(g2);
		colony.draw(g2);
		botaoTarefas.draw(g2);
		if(menuT.ativo==true)
		menuT.draw(g2);
		g2.dispose();
	}
	
}
