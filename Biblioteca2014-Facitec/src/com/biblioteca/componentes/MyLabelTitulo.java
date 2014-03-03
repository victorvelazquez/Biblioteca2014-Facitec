package com.biblioteca.componentes;

import javax.swing.JLabel;

public class MyLabelTitulo extends JLabel {
	
	public MyLabelTitulo(String texto){
		this.setFont(new java.awt.Font("Tahoma", 0, 24));
		this.setText(texto);
	}

}
