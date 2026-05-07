package entity;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Colon extends Entity{
	GamePanel gp;
	public Colon(GamePanel gp) {
		this.gp=gp;
		setDefaultValues();
		getColonImage();
	}
	public void setDefaultValues() {
		x=100;
		y=100;
		speed=4;
		direction="down";
	}
	public void getColonImage() {
		try {
			up1=ImageIO.read(getClass().getResourceAsStream("/colon/boy_up_1.png"));
			up2=ImageIO.read(getClass().getResourceAsStream("/colon/boy_up_2.png"));
			down1=ImageIO.read(getClass().getResourceAsStream("/colon/boy_down_1.png"));
			down2=ImageIO.read(getClass().getResourceAsStream("/colon/boy_down_2.png"));
			left1=ImageIO.read(getClass().getResourceAsStream("/colon/boy_left_1.png"));
			left2=ImageIO.read(getClass().getResourceAsStream("/colon/boy_left_2.png"));
			right1=ImageIO.read(getClass().getResourceAsStream("/colon/boy_right_1.png"));
			right2=ImageIO.read(getClass().getResourceAsStream("/colon/boy_right_2.png"));
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		
	}
	public void draw(Graphics g2) {
		/*g2.setColor(Color.white);
		g2.fillRect(x,y,gp.tileSize,gp.tileSize);*/
		BufferedImage image=null;
		switch(direction) {
		case "up":
			if(spriteNum==1) {
				image=up1;
			}
			if(spriteNum==2) {
				image=up2;
			}
			break;
		
		case "down":
			if(spriteNum==1) {
				image=down1;
			}
			if(spriteNum==2) {
				image=down2;
			}
			break;
		
		case "left":
			if(spriteNum==1) {
				image=left1;
			}
			if(spriteNum==2) {
				image=left2;
			}
			break;
		
		case "right":
			if(spriteNum==1) {
				image=right1;
			}
			if(spriteNum==2) {
				image=right2;
			}
			break;
		}
		g2.drawImage(image, x, y,gp.tileSize,gp.tileSize,null);
	}
}
