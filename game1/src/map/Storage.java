package map;

import java.awt.Color;
import java.awt.Graphics;

import main.GamePanel;

public class Storage extends Object{
	GamePanel gp;
	public Storage(GamePanel gp) {
		this.gp=gp;
		this.x=gp.telaLarg/2-gp.tileSize;
		this.y=gp.linTela/2-gp.tileSize;
	}
	public void draw(Graphics g2) {
		g2.setColor(Color.orange);
		g2.fillRect(x, y,gp.tileSize, gp.tileSize);
	}
}
