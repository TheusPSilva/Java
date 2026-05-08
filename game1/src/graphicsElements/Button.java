package graphicsElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Button {
	public int x,y,largura,altura;
	public Font novaFonte = new Font("Arial", Font.BOLD, 24);
	public Font selecionado = new Font("Arial", Font.BOLD, 40);
	public String  text="";
	public Color cor;
	public boolean pressed=false;
	
	public Button(int x,int y,int largura,int altura, String text,Color cor) {
		this.x=x;
		this.y=y;
		this.largura=largura;
		this.altura=altura;
		this.text=text;
		this.cor=cor;
	}
	public void draw(Graphics g2) {
		g2.setColor(cor);
		g2.fillRect(x, y, largura, altura);
		
		g2.setColor(Color.white);
		g2.drawString(text, x+10, y+(largura/2)+5);
		
	}
	public void drawBotaoTarefas(Graphics g2) {
		g2.setColor(cor);
		g2.fillRect(x, y, largura, altura);
		g2.setColor(Color.black);
		g2.setFont(selecionado);
		g2.drawString(text, x+10, y+(largura/2)+5);
	}
	public boolean foiClicado(int mouseX,int mouseY) {
		return new Rectangle(x, y, largura, altura).contains(mouseX, mouseY);
	}
}
