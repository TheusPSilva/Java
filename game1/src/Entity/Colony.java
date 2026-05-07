package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import graphicsElements.Button;
import main.GamePanel;
import main.KeyHeadler;
import main.MouseHeadler;
import map.Rock;
import map.Storage;
import map.Tree;

public class Colony extends Entity {
	GamePanel gp;
	KeyHeadler kh;
	MouseHeadler mh;
	public int alvoX,alvoY,depositoX,depositoY,contadorInterno;
	public int speed=1;
	public boolean emCooldown=false;
	public String estado="IDLE";
	
	public Colony(GamePanel gp) {
		this.gp=gp;
		this.mh=gp.mouseH;
		setValues();
		pegarImagemJogador();
	}
	
	public void setValues() {
		x=gp.tileSize;
		y=gp.tileSize;
		speed=4;
		direction="down";
	}
	public void pegarImagemJogador() {
		try {
			//Load the images to the variables.
			up1=ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2=ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1=ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2=ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1=ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2=ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1=ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2=ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		if (emCooldown) {
	        cooldown();
	        return;   
	    }
		if (estado.equals("IDLE")) {
			spriteCounter++;
			if(spriteCounter>12) {
				if(spriteNum==1) {
					spriteNum=2;
				}
				else if(spriteNum==2) {
					spriteNum=1;
				}
				spriteCounter=0;
			}
			if(gp.menuT.coletarPedra.pressed==true) {
				localizarAlvo(1);
				irParaAlvo();
			}
			if(gp.menuT.coletarMadeira.pressed==true) {
				localizarAlvo(3);
				irParaAlvo();			
			}
		}
		else if (estado.equals("COLETOU")&& emCooldown==false) { 
			spriteCounter++;
			if(spriteCounter>12) {
				if(spriteNum==1) {
					spriteNum=2;
				}
				else if(spriteNum==2) {
					spriteNum=1;
				}
				spriteCounter=0;
			}
			localizarDeposito();
			irParaDeposito();
		}
	}
	public void irParaDeposito() {
		if(x<depositoX) { x+=speed; direction="right";}
		else if(x>depositoX) { x-=speed; direction="left";}
		if(y<depositoY) { y+=speed;direction="down";}
		else if(y>depositoY) { y-=speed;direction="up";}
		if(x==depositoX && y==depositoY && estado=="COLETOU" ) { 
			estado="IDLE";
			direction="down";
		}
	}
	public void irParaAlvo() {
		if(x<alvoX) x+=speed;
		else if(x>alvoX) x-=speed;
		if(y<alvoY) y+=speed;
		else if(y>alvoY) y-=speed;
		if(x==alvoX && y==alvoY && estado!="COLETOU") { 
			direction="down";
			emCooldown=true;
			cooldown();
			estado="COLETOU"; 
		
		}
	}
	public void setDestino(int dx, int dy) {
		this.alvoX=dx;
		this.alvoY=dy;
	}
	public void cooldown() {
		emCooldown = true;
	    contadorInterno++;
	    
	    if (contadorInterno >= 110) { 
	        emCooldown = false;
	        contadorInterno = 0; 
	        estado = "COLETOU"; 
	    }
	}
	public void localizarAlvo(int idRecurso) {
		int alvoMaisPerto=99999999,alvoAtual,difX=0,difY=0,tempX=-1,tempY=-1;
		for(int l=0;l<gp.linTela;l++) {
			for(int c=0;c<gp.colTela;c++) {
				 if(gp.tileM.mapTileNum[c][l]==idRecurso) {
					difX=(c*gp.tileSize)-x;
					difY=(l*gp.tileSize)-y;
					alvoAtual=(difX*difX)+(difY*difY);
					 
					if(alvoAtual<alvoMaisPerto) {
						alvoMaisPerto=alvoAtual;
						tempX=c*gp.tileSize;
						tempY=l*gp.tileSize;
					}
				}
			}
		}
		this.alvoX=tempX;
		this.alvoY=tempY;
		this.estado="IDLE";
	}
	public void localizarDeposito() {
		for(int l=0;l<gp.linTela;l++) {
			for(int c=0;c<gp.colTela;c++) {
				if(gp.tileM.mapTileNum[c][l]==4) {
					this.depositoX=c*gp.tileSize;
					this.depositoY=l*gp.tileSize;
					return;
				}
			}
		}
	}
	
	public void draw(Graphics g2) {
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
