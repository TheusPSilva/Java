package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import graphicsElements.Button;
import graphicsElements.Window;
import main.GamePanel;
import main.KeyHeadler;
import main.MouseHeadler;

public class Colony extends Entity {
	GamePanel gp;
	KeyHeadler kh;
	MouseHeadler mh;
	public Button menu;
	public Button fechar;
	public Button coletarPedra;
	public Button coletarMadeira;
	public Window menuT;
	public int alvoX,alvoY,depositoX,depositoY,contadorInterno;
	public int speed=1;
	public int idTarefa;
	public boolean emCooldown=false;
	public String estado="IDLE";//IDLE, COLETOU,DEPOSITOU
	
	public Colony(GamePanel gp,String name,int x,int y) {
		this.gp=gp;
		this.mh=gp.mouseH;
		this.name=name;
		this.x=x;
		this.y=y;
		menuT=new Window(gp,name,240,96,288,288);
		menu=new Button(x,y,gp.tileSize,gp.tileSize,"",new Color(0, 0, 0, 0));
		fechar=new Button(gp.menuTX*2,gp.menuTY,gp.tileSize,gp.tileSize,"X",Color.white);
		coletarPedra=new Button(gp.menuTX+48,gp.menuTY+48,gp.tileSize,gp.tileSize,"",new Color(255, 0, 0, 150));
		coletarMadeira=new Button(gp.menuTX+48,gp.menuTY+gp.tileSize*3,gp.tileSize,gp.tileSize,"",new Color(255, 0, 0, 150));
		
		setValues();
		pegarImagemJogador();
	}
	
	public void setValues() {
		speed=4;
		direction="idle";
		
	}
	public void pegarImagemJogador() {
		try {
			idle=ImageIO.read(getClass().getResourceAsStream("/playerNoClothes/boyN_idle_1.png"));
			up1=ImageIO.read(getClass().getResourceAsStream("/playerNoClothes/boyN_up_1.png"));
			up2=ImageIO.read(getClass().getResourceAsStream("/playerNoClothes/boyN_up_2.png"));
			down1=ImageIO.read(getClass().getResourceAsStream("/playerNoClothes/boyN_down_1.png"));
			down2=ImageIO.read(getClass().getResourceAsStream("/playerNoClothes/boyN_down_2.png"));
			left1=ImageIO.read(getClass().getResourceAsStream("/playerNoClothes/boyN_left_1.png"));
			left2=ImageIO.read(getClass().getResourceAsStream("/playerNoClothes/boyN_left_2.png"));
			right1=ImageIO.read(getClass().getResourceAsStream("/playerNoClothes/boyN_right_1.png"));
			right2=ImageIO.read(getClass().getResourceAsStream("/playerNoClothes/boyN_right_2.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		animacao();
		atualizarPosicaoBotao();
		checarBotaoclicado();
		
		
		if (emCooldown) {
	        cooldown();
	        return;   
	    }
		if (estado!="COLETOU") {
			
			if(coletarPedra.pressed==true) {
				localizarAlvo(1);
				irParaAlvo();
				
			}
			if(coletarMadeira.pressed==true) {
				localizarAlvo(3);
				irParaAlvo();
				
			}
		}
		else if (estado.equals("COLETOU")&& emCooldown==false) { 
			localizarDeposito();
			irParaDeposito();
			atualizarQtdRecurso();
		}
	}
	public void irParaDeposito() {
		if(x<depositoX) { x+=speed; direction="right";}
		else if(x>depositoX) { x-=speed; direction="left";}
		if(y<depositoY) { y+=speed;direction="down";}
		else if(y>depositoY) { y-=speed;direction="up";}
		if(x==depositoX && y==depositoY && estado.equals("COLETOU") && !estado.equals("DEPOSITOU")) { 
			estado="DEPOSITOU";
			direction="idle";
		}
	}
	public void irParaAlvo() {
		if(x<alvoX) x+=speed;
		else if(x>alvoX) x-=speed;
		if(y<alvoY) y+=speed;
		else if(y>alvoY) y-=speed;
		if(x==alvoX && y==alvoY && estado!="COLETOU") { 
			direction="idle";
			emCooldown=true;
			cooldown();
			estado="COLETOU"; 
		
		}
	}
	public void atualizarQtdRecurso() {
		if(idTarefa==1 && estado.equals("DEPOSITOU"))
			gp.estatisticas.qtdPedra+=1;
		if(idTarefa==3 && estado.equals("DEPOSITOU"))
			gp.estatisticas.qtdMadeira+=1;
	}
	public void atualizarPosicaoBotao() {
		this.menu.x=x;
		this.menu.y=y;
	}
	public void checarBotaoclicado() {
		if (gp.mouseH.clicou) {
	        menuT.tratarClique(gp.mouseH.mouseX, gp.mouseH.mouseY,menu);
	        
	        if (menuT.ativo) {
	            
	            if (coletarPedra.foiClicado(gp.mouseH.mouseX, gp.mouseH.mouseY)) {
	                if(coletarPedra.pressed == false) {
	                	coletarPedra.pressed=true;
	                	coletarPedra.text="X";
	                	coletarMadeira.text="";
	                	coletarMadeira.pressed = false;
	                	if(estado!="COLETOU")
	                		estado="MINERANDO";
	                }
	                else if(coletarPedra.pressed == true) {
	                	coletarPedra.pressed=false;
	                	coletarPedra.text="";
	                	if(estado!="COLETOU")
	                		estado="IDLE";
	                }
	            } 
	            else if (coletarMadeira.foiClicado(gp.mouseH.mouseX, gp.mouseH.mouseY)) {
	                if(coletarMadeira.pressed == false) {
		                coletarMadeira.pressed=true;
		                coletarMadeira.text="X";
		                coletarPedra.text="";
		                coletarPedra.pressed = false;
		                if(!estado.equals("COLETOU"))
	                		estado="SERRANDO";
	                }
	                else if(coletarMadeira.pressed == true) {
	                	coletarMadeira.pressed=false;
	                	coletarMadeira.text="";
	                	if(!estado.equals("COLETOU"))
	                		estado="IDLE";
	                }
	            }
	            else if(fechar.foiClicado(gp.mouseH.mouseX, gp.mouseH.mouseY))
	            	menuT.ativo=false;
	        }
	    }
		
		
	}
	public void animacao() {
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
		this.idTarefa=idRecurso;
		this.alvoX=tempX;
		this.alvoY=tempY;
		this.estado="TRABALHANDO";
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
	public void desenharMenuT(Graphics g2) {
		menu.draw(g2);
		if(menuT.ativo) {
			menuT.draw(g2);
			coletarPedra.drawBotaoTarefas(g2);
			coletarMadeira.drawBotaoTarefas(g2);
			fechar.draw(g2);
		}
	}
	public void draw(Graphics g2) {
		desenharMenuT(g2);
		BufferedImage image=null;
		switch(direction) {
		case "idle":
			if(spriteNum==1) {
				image=idle;
			}
			if(spriteNum==2) {
				image=idle;
			}
			break;
		
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
