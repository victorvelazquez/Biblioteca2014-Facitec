package com.biblioteca.componentes.manejadores;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ManejadorTeclasMayusculas implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		Character c = e.getKeyChar();
		if(Character.isLetter(c)) {
			e.setKeyChar(Character.toUpperCase(c));
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	

	
}