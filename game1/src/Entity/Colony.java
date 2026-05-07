package Entity;

import java.awt.Color;
import java.awt.Graphics;

import graphicsElements.Button;
import main.GamePanel;
import main.KeyHeadler;
import main.MouseHeadler;
import map.Rock;
import map.Storage;
import map.Tree;

public class Colony {
	GamePanel gp;
	Storage st;
	Rock rk;
	Tree tr;
	KeyHeadler kh;
	MouseHeadler mh;
	public int x,y;
	public int alvoX,alvoY,depositoX,depositoY,contadorInterno;
	public int speed=1;
	public boolean emCooldown=false;
	public String estado="IDLE";
	
	public Colony(GamePanel gp) {
		this.gp=gp;
		this.rk=gp.rock;
		this.st=gp.storage;
		this.mh=gp.mouseH;
		this.tr=gp.tree;
		setValues();
	}
	
	public void setValues() {
		x=gp.tileSize;
		y=gp.tileSize;
		speed=4;
	}
	public void update() {
		if (emCooldown) {
	        cooldown();
	        return;   
	    }
		if (estado.equals("IDLE")) {
			if(gp.menuT.coletarPedra.pressed==true) {
				setDestino(rk.x,rk.y);
				irParaAlvo();
				
			}
			if(gp.menuT.coletarMadeira.pressed==true) {
				setDestino(tr.x,tr.y);
				irParaAlvo();
				
				
			}
			
		}
            
		else if (estado.equals("COLETOU")&& emCooldown==false) { 
			setDeposito();
			irParaDeposito();
		}
	}
	public void irParaDeposito() {
		if(x<st.x) x+=speed;
		else if(x>st.x) x-=speed;
		if(y<st.y) y+=speed;
		else if(y>st.y) y-=speed;
		if(x==st.x && y==st.y && estado=="COLETOU" ) { 
			estado="IDLE";
		}
	}
	public void irParaAlvo() {
		if(x<alvoX) x+=speed;
		else if(x>alvoX) x-=speed;
		if(y<alvoY) y+=speed;
		else if(y>alvoY) y-=speed;
		if(x==alvoX && y==alvoY && estado!="COLETOU") { 
			
			emCooldown=true;
			cooldown();
			estado="COLETOU"; 
		
		}
	}
	public void setDestino(int dx, int dy) {
		this.alvoX=dx;
		this.alvoY=dy;
	}
	public void setDeposito() {
		this.depositoX=st.x;
		this.depositoY=st.y;
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
	
	public void draw(Graphics g2) {
		g2.setColor(Color.black);
		g2.fillRect(x, y,gp.tileSize, gp.tileSize);
	}
}
