package com.biblioteca.componentes;

import javax.swing.JLabel;

public class MyLabelNormal extends JLabel {
	
	public MyLabelNormal(String texto){
		this.setFont(new java.awt.Font("Tahoma", 0, 16));
		this.setText(texto);
	}

}
