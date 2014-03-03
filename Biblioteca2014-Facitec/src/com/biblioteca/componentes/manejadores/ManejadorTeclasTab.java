package com.biblioteca.componentes.manejadores;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ManejadorTeclasTab implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			Robot Tecla = null;
			try {
				Tecla = new Robot();
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
			Tecla.keyPress(KeyEvent.VK_TAB);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	



	
}