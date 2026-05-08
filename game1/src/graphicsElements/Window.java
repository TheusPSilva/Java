package graphicsElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.GamePanel;
import main.MouseHeadler;

public class Window {
	public int x,y,largura,altura;
	public String name;
	public int qtdPedra=0,qtdMadeira=0;
	public boolean ativo=false;
	public Font novaFonte = new Font("Arial", Font.BOLD, 24);
	GamePanel gp;
	MouseHeadler mh;
	Button tarefas;
	
	public Button coletarPedra;
	public Button coletarMadeira;
	public Button fechar;
	
	public Window(GamePanel gp,String name,int x,int y,int largura,int altura) {
		this.x=x;
		this.y=y;
		this.name=name;
		this.largura=largura;
		this.altura=altura;
	}
	public void draw(Graphics g2) {
		
			g2.setColor(new Color(0, 0, 255, 128));
			g2.fillRect(x, y, largura, altura);
			g2.setColor(Color.white);
			g2.setFont(novaFonte);
			g2.drawString(name,332,106);
			
			
	}
	public void inventory(Graphics g2) {
		g2.setFont(novaFonte);
		g2.drawString("Madeira: "+qtdMadeira, 10,40);
		g2.drawString("Pedra: "+qtdPedra, 10,80);
	}
	public void cicloTempo(Graphics g2,GamePanel gp){
		g2.setFont(novaFonte);
		g2.drawString("Dia: "+gp.dia, 1100,40);
		g2.drawString("Semana: "+gp.semana, 1100,80);
	}
	public void tratarClique(int mx, int my, Button botaoGatilho) {
	    if (botaoGatilho.foiClicado(mx, my)) {
	        this.ativo = !this.ativo;
	    }
	}
}
