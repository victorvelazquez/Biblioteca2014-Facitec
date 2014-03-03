package com.biblioteca.controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import com.biblioteca.componentes.MyTextFieldTexto;
import com.biblioteca.componentes.MyVentanaABM;
import com.biblioteca.dao.AreaDAO;
import com.biblioteca.dto.AreaDTO;
import com.biblioteca.interfases.ControladorABM;

public class ControladorAreasABM implements ActionListener, ControladorABM{
	private MyVentanaABM vAreasABM;
	private AreaDTO aDTO;
	private AreaDAO aDAO;
	private JLabel lCodigo;
	private JLabel lDescripcion;
	private JLabel lObse;
	private MyTextFieldTexto tCodigo;
	private MyTextFieldTexto tDescripcion;
	private MyTextFieldTexto tObse;
	private boolean modificar;
	private boolean nuevo;

	public ControladorAreasABM(){
		this.aDTO = new AreaDTO();
		this.aDAO =  new AreaDAO();
		String columna[]=new String[]{"Código","Descripción","Observacion"};
		this.vAreasABM = new MyVentanaABM(this, "Areas", columna);		
	}

	public void inicializar(MyVentanaABM ventana) {
		this.vAreasABM = ventana;

		//-> Iniciamos los labels, texfields y textArea para el ABM de Areas. Cada uno con sus caracteristicas.
		this.lCodigo = new JLabel("Código:");
		this.lCodigo.setBounds(10, 20, 80, 25);
		this.lDescripcion = new JLabel("Descripción:");
		this.lDescripcion.setBounds(10, 55, 80, 25);
		this.lObse = new JLabel("Observación:");
		this.lObse.setBounds(10, 90, 80, 25);
		this.tCodigo = new MyTextFieldTexto(100, 20, 100);
		this.tDescripcion = new MyTextFieldTexto(100, 55, 260);
		this.tObse = new MyTextFieldTexto(100, 90, 260);

		//-> Agregamos los labels y textfields al panelMantenimiento de la VentanaABM (vAreasABM)
		this.vAreasABM.getPanelMantenimiento().add(this.lCodigo);
		this.vAreasABM.getPanelMantenimiento().add(lDescripcion);
		this.vAreasABM.getPanelMantenimiento().add(lObse);
		this.vAreasABM.getPanelMantenimiento().add(tCodigo);
		this.vAreasABM.getPanelMantenimiento().add(tDescripcion);
		this.vAreasABM.getPanelMantenimiento().add(tObse);

		this.reiniciar();

	}

	public void reiniciar(){
		this.modificar = false;
		this.nuevo = false;
		this.vAreasABM.gettBuscar().setText("");
		this.cargarTablaBusqueda("");
		this.vAreasABM.gettBuscar().requestFocus();

		//-> Habilitamos, desabilitamos los componentes al iniciar la ventana
		this.vAreasABM.getBotonNuevo().setEnabled(true);
		this.vAreasABM.getBotonModificar().setEnabled(false);
		this.vAreasABM.getBotonEliminar().setEnabled(false);
		this.vAreasABM.getBotonGuardar().setEnabled(false);
		this.tCodigo.setEditable(false);
		this.tDescripcion.setEditable(false);
		this.tObse.setEditable(false);
		this.tCodigo.setText("");
		this.tDescripcion.setText("");
		this.tObse.setText("");
	}

	public void cargarTablaBusqueda(String descripcion) {
		this.vAreasABM.getTabla().getColumnModel().getColumn(1).setPreferredWidth(150);
		this.vAreasABM.getTabla().getColumnModel().getColumn(2).setPreferredWidth(300);
		ArrayList<AreaDTO> lista = this.aDAO.buscarPorDescripcion(descripcion);
		while(this.vAreasABM.getModelo().getRowCount()>0)this.vAreasABM.getModelo().removeRow(0);
		for( int i = 0 ; i < lista.size() ; i++ ){
			AreaDTO area = lista.get(i);
			Object[] fila = new Object[3];
			fila[0]= area.getAre_codigo();
			fila[1]= area.getAre_descri();
			fila[2]= area.getAre_obse();
			this.vAreasABM.getModelo().addRow(fila);
		}
	}

	public void cargarTablaMantenimiento(int codigo){
		this.aDTO = this.aDAO.buscarPorId(codigo);
		String codigoString = Integer.toString(this.aDTO.getAre_codigo());
		this.tCodigo.setText(codigoString);
		this.tDescripcion.setText(this.aDTO.getAre_descri());
		this.tObse.setText(this.aDTO.getAre_obse());
		this.vAreasABM.getBotonModificar().setEnabled(true);
		this.vAreasABM.getBotonEliminar().setEnabled(true);
		this.tDescripcion.setEditable(false);
		this.tObse.setEditable(false);
	}

	public void nuevoRegistro(){
		this.reiniciar();
		this.nuevo = true;
		this.vAreasABM.getBotonNuevo().setEnabled(false);
		this.vAreasABM.getBotonGuardar().setEnabled(true);
		String codigo = Integer.toString(aDAO.ultimoCodigo()+1);
		this.tCodigo.setText(codigo);
		this.tDescripcion.setEditable(true);
		this.tObse.setEditable(true);
		this.tDescripcion.requestFocus();
	}

	public void modificarRegistro(){
		this.modificar = true;
		this.vAreasABM.getBotonNuevo().setEnabled(true);
		this.vAreasABM.getBotonModificar().setEnabled(false);
		this.vAreasABM.getBotonEliminar().setEnabled(true);
		this.vAreasABM.getBotonGuardar().setEnabled(true);
		this.tDescripcion.setEditable(true);
		this.tObse.setEditable(true);
	}

	public void eliminarRegistro(){
		if (JOptionPane.showConfirmDialog(new JFrame(),
				"¿Quieres eliminar este registro?", "Eliminar",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			this.aDAO.eliminar(Integer.parseInt(this.tCodigo.getText()));
			this.reiniciar();
		}
	}

	public void guardarRegistro(){
		if(this.tDescripcion.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "¡Debes ingresar una descripción!");
			this.tDescripcion.requestFocus();
		}else{

			this.aDTO.setAre_descri(this.tDescripcion.getText().trim());
			this.aDTO.setAre_obse(this.tObse.getText().trim());
			if(this.modificar==false){
				this.aDAO.insertar(aDTO);
				this.reiniciar();
			}else{
				this.aDAO.actualizar(aDTO);
				this.reiniciar();
			}
		}
	}

	public void cancelarRegistro(){
		this.reiniciar();
	}

	public void salir(){
		if(this.vAreasABM.getBotonGuardar().isEnabled()){
			if (JOptionPane.showConfirmDialog(new JFrame(),
					"¿Quieres salir de esta ventana sin guardar los cambios?", "Salir",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				this.vAreasABM.dispose();
			}
		}else{
			this.vAreasABM.dispose();
		}
	}





	public void actionPerformed(ActionEvent e){


	}




	public static void main(String[] args) {
		ControladorAreasABM c = new ControladorAreasABM();
	}








}
