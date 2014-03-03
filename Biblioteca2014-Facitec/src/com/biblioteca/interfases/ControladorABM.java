package com.biblioteca.interfases;

import com.biblioteca.componentes.MyVentanaABM;



public interface ControladorABM {
	public void inicializar(MyVentanaABM ventana);
	public void cargarTablaBusqueda(String descripcion);
	public void cargarTablaMantenimiento(int codigo);
	public void nuevoRegistro();
	public void modificarRegistro();
	public void eliminarRegistro();
	public void guardarRegistro();
	public void cancelarRegistro();
	public void salir();

}
