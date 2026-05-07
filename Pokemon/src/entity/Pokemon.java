package entity;
import java.awt.Graphics;

import main.GamePanel;
public class Pokemon {
	GamePanel gp;
	
	public int nivel,speed;
	public double vida;
	public int x,y;

public Pokemon(GamePanel gp,int x,int y){
			this.gp=gp;
			this.x=x;
			this.y=y;
	
		setDefaultvalues();
}

public void setDefaultvalues() {
	
}
public void draw(Graphics g2) {
	
	g2.fillRect(x, y, gp.tileSize,gp.tileSize);	
}
}