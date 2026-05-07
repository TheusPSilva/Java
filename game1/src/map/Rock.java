package map;

import java.awt.Color;
import java.awt.Graphics;

import main.GamePanel;

public class Rock extends Object{
	GamePanel gp;
	public Rock(GamePanel gp) {
		this.gp=gp;
		this.x=gp.tileSize*5;
		this.y=gp.tileSize*7;
	}
	
	public void draw(Graphics g2) {
		g2.setColor(Color.white);
		g2.fillRect(x, y,gp.tileSize, gp.tileSize);
	}
}
