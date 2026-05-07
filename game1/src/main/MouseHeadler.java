package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHeadler implements MouseListener {
	public int mouseX,mouseY;
	public boolean clicou;
	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
		clicou=true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clicou=false;
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	
}
