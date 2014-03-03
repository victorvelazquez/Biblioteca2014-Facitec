package com.biblioteca.componentes;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import com.biblioteca.componentes.manejadores.ManejadorTeclasMayusculas;
import com.biblioteca.componentes.manejadores.ManejadorTeclasTab;



public class MyTextFieldTexto extends JTextField implements FocusListener{
	private ManejadorTeclasTab manejadorTab;
	private ManejadorTeclasMayusculas manejadorMayusculas;
	
	
	public MyTextFieldTexto(int x, int y, int ancho){
		this.addFocusListener(this);
		this.manejadorTab = new ManejadorTeclasTab();
		this.manejadorMayusculas = new ManejadorTeclasMayusculas();
		this.addKeyListener(manejadorTab);
		this.addKeyListener(manejadorMayusculas);
		this.setBounds (x,y,ancho,25);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	}
	
	public MyTextFieldTexto(int x, int y, int ancho, int alto){
		this.addFocusListener(this);
		this.manejadorTab = new ManejadorTeclasTab();
		this.manejadorMayusculas = new ManejadorTeclasMayusculas();
		this.addKeyListener(manejadorTab);
		this.addKeyListener(manejadorMayusculas);
		this.setBounds (x,y,ancho,alto);
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	}

	@Override
	public void focusGained(FocusEvent e) {
		this.setBorder(BorderFactory.createLineBorder(new java.awt.Color(50,140,255), 2));
		this.selectAll();
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
	}

}
