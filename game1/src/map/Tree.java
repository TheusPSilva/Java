package map;

import java.awt.Color;
import java.awt.Graphics;

import main.GamePanel;

public class Tree extends Object {
	GamePanel gp;
	public Tree(GamePanel gp) {
		this.gp=gp;
		this.x=gp.tileSize*11;
		this.y=gp.tileSize*8;
	}
	public void draw(Graphics g2) {
		g2.setColor(Color.red);
		g2.fillRect(x, y,gp.tileSize, gp.tileSize);
	}
}
