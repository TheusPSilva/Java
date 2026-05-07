package graphicsElements;

import java.awt.Color;
import java.awt.Graphics;

import main.GamePanel;
import main.MouseHeadler;

public class Window {
	public int x,y,largura,altura;
	public boolean ativo=false;
	GamePanel gp;
	MouseHeadler mh;
	Button tarefas;
	
	public Button coletarPedra;
	public Button coletarMadeira;
	
	public Window(GamePanel gp,int x,int y,int largura,int altura) {
		this.x=x;
		this.y=y;
		this.largura=largura;
		this.altura=altura;
		coletarPedra=new Button(gp.menuTX+48,gp.menuTY+48,gp.tileSize,gp.tileSize,"",Color.red);
		coletarMadeira=new Button(gp.menuTX+48,gp.menuTY+gp.tileSize*3,gp.tileSize,gp.tileSize,"",Color.red);
	}
	public void draw(Graphics g2) {
			g2.setColor(Color.gray);
			g2.fillRect(x, y, largura, altura);
			coletarPedra.draw(g2);
			coletarMadeira.draw(g2);
			
		
	}
	public void tratarClique(int mx, int my, Button botaoGatilho) {
	    if (botaoGatilho.foiClicado(mx, my)) {
	        this.ativo = !this.ativo;
	    }
	}
}
