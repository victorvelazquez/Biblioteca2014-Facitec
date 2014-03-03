package com.biblioteca.componentes;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

public class MyBotonFormulario extends JButton implements FocusListener, MouseListener{
	private InputMap map;
	
	
	public MyBotonFormulario(){
		this.map = new InputMap();
		this.map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		this.map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
		this.setInputMap(0, map);
		this.addFocusListener(this);
		this.addMouseListener(this);
	}
	
	public MyBotonFormulario(String texto, int x, int y){
		this.map = new InputMap();
		this.map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		this.map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
		this.setInputMap(0, map);
		this.setText(texto);
		this.setBounds (x,y,90,35);
		this.addFocusListener(this);
		this.addMouseListener(this);
		
	}
	
	public MyBotonFormulario(String texto, int x, int y, int ancho, int alto){
		this.map = new InputMap();
		this.map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
		this.map.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
		this.setInputMap(0, map);
		this.setText(texto);
		this.setBounds (x,y,ancho,alto);
		this.addFocusListener(this);
		this.addMouseListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		this.setBorder(BorderFactory.createLineBorder(new java.awt.Color(50,140,255), 2));
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setBorder(BorderFactory.createLineBorder(new java.awt.Color(50,140,255), 2));
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
	}



}
