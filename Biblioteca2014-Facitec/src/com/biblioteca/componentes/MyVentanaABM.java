package com.biblioteca.componentes;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.interfases.ControladorABM;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyVentanaABM extends JDialog implements KeyListener, ActionListener, MouseListener{
	private MyLabelTitulo lTitulo;
	private JSeparator separador;
	private MyLabelNormal lBuscar;
	private MyTextFieldTexto tBuscar;
	private DefaultTableModel modelo;
	private JTable tabla;
	private JScrollPane scroll;
	private JPanel panelMantenimiento;
	private int filas;
	private MyBotonFormulario botonNuevo;
	private MyBotonFormulario botonModificar;
	private MyBotonFormulario botonEliminar;
	private MyBotonFormulario botonGuardar;
	private MyBotonFormulario botonCancelar;
	private MyBotonFormulario botonSalir;
	private ControladorABM controlador;


	public MyVentanaABM(ControladorABM controlador, String titulo, String[] columna){

		//-> Para salir con la tecla ESC
		this.getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), "Cancel");

		getRootPane().getActionMap().put("Cancel", new javax.swing.AbstractAction(){
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				salir();
			}
		});

		//-> Para confirmar al cerrar ventana
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						salir();
					}
				}
				);



		getContentPane().setLayout(null);
		this.setSize(800, 600);
		this.controlador = controlador;
		this.lTitulo = new MyLabelTitulo(titulo);
		this.lTitulo.setBounds (10,10,600,30);
		this.lTitulo.setFont(new java.awt.Font("Tahoma", 0, 24));
		this.separador = new JSeparator();
		this.separador.setBackground(Color.BLACK);
		this.separador.setBounds(10, 50, 760, 2);
		this.lBuscar = new MyLabelNormal("Buscar:");
		this.lBuscar.setBounds(10, 80, 100, 25);
		this.tBuscar = new MyTextFieldTexto(70, 80, 240);
		this.tBuscar.addKeyListener(this);

		// crear tabla
		this.scroll = new JScrollPane();
		this.scroll.setBounds(10, 110, 300, 445);
		//String columna[]=new String[]{"Id","Descripción"};
		this.modelo = new DefaultTableModel(null, columna);
		this.tabla=new JTable(this.modelo){
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false; //return false: Desabilitar edición de celdas.
			}};
			this.tabla.setModel(modelo);
			//this.tabla.getColumnModel().getColumn(1).setPreferredWidth(500);
			this.scroll.setViewportView(tabla);
			this.getContentPane().add(scroll);
			this.getTabla().addMouseListener(this);
			this.getTabla().addKeyListener(this);

			this.panelMantenimiento = new JPanel();
			this.panelMantenimiento.setLayout(null);
			this.panelMantenimiento.setBounds(410, 110, 370, 410);
			TitledBorder title1;
			title1 = BorderFactory.createTitledBorder("Mantenimiento");
			this.panelMantenimiento.setBorder(title1);



			this.botonNuevo = new MyBotonFormulario("Nuevo", 315, 130);
			this.botonNuevo.addActionListener(this);
			this.botonModificar = new MyBotonFormulario("Modificar", 315, 170);
			this.botonModificar.addActionListener(this);
			this.botonEliminar = new MyBotonFormulario("Eliminar", 315, 210);
			this.botonEliminar.addActionListener(this);
			this.botonGuardar = new MyBotonFormulario("Guardar", 500, 520);
			this.botonGuardar.addActionListener(this);
			this.botonCancelar = new MyBotonFormulario("Cancelar", 595, 520);
			this.botonCancelar.addActionListener(this);
			this.botonSalir = new MyBotonFormulario("Salir", 690, 520);
			this.botonSalir.addActionListener(this);




			getContentPane().add(this.lTitulo);
			getContentPane().add(this.separador);
			getContentPane().add(this.lBuscar);
			getContentPane().add(this.tBuscar);
			getContentPane().add(this.panelMantenimiento);
			getContentPane().add(this.botonNuevo);
			getContentPane().add(this.botonModificar);
			getContentPane().add(this.botonEliminar);
			getContentPane().add(this.botonGuardar);
			getContentPane().add(this.botonCancelar);
			getContentPane().add(this.botonSalir);



			this.controlador.inicializar(this);



			this.setVisible(true);



	}

	public void seleccionarFilaTabla(){
		filas = this.getTabla().getRowCount();
		if(filas>0){
			int row = this.getTabla().getSelectedRow();
			if(row>-1){
				this.controlador.cargarTablaMantenimiento(Integer.parseInt(this.getTabla().getValueAt(row, 0).toString().trim()));
			}else{
				JOptionPane.showMessageDialog(null, "¡Debes seleccionar una fila!");
				this.getTabla().requestFocus();
				this.getTabla().changeSelection (this.getTabla().getRowCount () - this.getModelo().getRowCount(), 0, false, false );
			}
		}else{
			JOptionPane.showMessageDialog(null, "¡Debes buscar por descripción!");
			this.gettBuscar().requestFocus();
		}

	}

	public void salir(){
		this.controlador.salir();
	}

	public JTable getTabla() {
		return tabla;
	}

	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}

	public MyTextFieldTexto gettBuscar() {
		return tBuscar;
	}

	public void settBuscar(MyTextFieldTexto tBuscar) {
		this.tBuscar = tBuscar;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public void setModelo(DefaultTableModel modelo) {
		this.modelo = modelo;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public MyBotonFormulario getBotonNuevo() {
		return botonNuevo;
	}

	public void setBotonNuevo(MyBotonFormulario botonNuevo) {
		this.botonNuevo = botonNuevo;
	}

	public MyBotonFormulario getBotonModificar() {
		return botonModificar;
	}

	public void setBotonModificar(MyBotonFormulario botonModificar) {
		this.botonModificar = botonModificar;
	}

	public MyBotonFormulario getBotonEliminar() {
		return botonEliminar;
	}

	public void setBotonEliminar(MyBotonFormulario botonEliminar) {
		this.botonEliminar = botonEliminar;
	}

	public MyBotonFormulario getBotonGuardar() {
		return botonGuardar;
	}

	public void setBotonGuardar(MyBotonFormulario botonGuardar) {
		this.botonGuardar = botonGuardar;
	}

	public MyBotonFormulario getBotonCancelar() {
		return botonCancelar;
	}

	public void setBotonCancelar(MyBotonFormulario botonCancelar) {
		this.botonCancelar = botonCancelar;
	}

	public MyBotonFormulario getBotonSalir() {
		return botonSalir;
	}

	public void setBotonSalir(MyBotonFormulario botonSalir) {
		this.botonSalir = botonSalir;
	}

	public JPanel getPanelMantenimiento() {
		return panelMantenimiento;
	}

	public void setPanelMantenimiento(JPanel panelMantenimiento) {
		this.panelMantenimiento = panelMantenimiento;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource()==this.getTabla()){
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				seleccionarFilaTabla();
			}
		}

		if(e.getSource()==this.tBuscar){
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				filas = this.getTabla().getRowCount();
				if(filas>0){
					this.getTabla().requestFocus();
					this.getTabla().changeSelection (this.getTabla().getRowCount () - this.getModelo().getRowCount(), 0, false, false );
				}
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN){
				filas = this.getTabla().getRowCount();
				if(filas>0){
					this.getTabla().requestFocus();
					this.getTabla().changeSelection (this.getTabla().getRowCount () - this.getModelo().getRowCount(), 0, false, false );
				}
			}
		}

	}



	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource()==this.gettBuscar()){
			if (this.gettBuscar().getText().isEmpty()){
				//while(this.getModelo().getRowCount()>0)this.getModelo().removeRow(0);
				this.controlador.cargarTablaBusqueda("");
			}else{
				//while(this.getModelo().getRowCount()>0)this.getModelo().removeRow(0);
				this.controlador.cargarTablaBusqueda(this.gettBuscar().getText());
			}
		}

	}


	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource()==this.getBotonNuevo()){
			this.controlador.nuevoRegistro();
		}
		if (e.getSource()==this.getBotonModificar()){
			this.controlador.modificarRegistro();
		}
		if (e.getSource()==this.getBotonEliminar()){
			this.controlador.eliminarRegistro();
		}
		if (e.getSource()==this.getBotonGuardar()){
			this.controlador.guardarRegistro();
		}
		if (e.getSource()==this.getBotonCancelar()){
			this.controlador.cancelarRegistro();
		}
		if (e.getSource()==this.getBotonSalir()){
			this.salir();
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==this.getTabla()){
			if (e.getClickCount() == 2){
				seleccionarFilaTabla();
			}
		}

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
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}




}
