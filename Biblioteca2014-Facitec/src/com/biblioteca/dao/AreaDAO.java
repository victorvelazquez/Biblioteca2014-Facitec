package com.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.biblioteca.conexion.ManejadorConexiones;
import com.biblioteca.dto.AreaDTO;





public class AreaDAO {
	private Connection c;
	private ResultSet rs;
	private PreparedStatement sentencia;
	private Statement stm;
	private boolean procesado;
	
	public void insertar(AreaDTO area){
		this.c = ManejadorConexiones.obtenerConnection();
		try {			
			String sql = "INSERT INTO areas(are_descri, are_obse) VALUES (?, ?);";
			sentencia =  c.prepareStatement(sql);
			this.sentencia.setString(1, area.getAre_descri());
			this.sentencia.setString(2, area.getAre_obse());
			sentencia.executeUpdate();
	} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error :"+e.getMessage());
			e.printStackTrace();
		}
		desconectar();
	}
	
	public void actualizar(AreaDTO area){
		this.c = ManejadorConexiones.obtenerConnection();
		try {			
			String sql = "UPDATE areas SET are_descri=?, are_obse=? WHERE are_codigo=?;";
			sentencia =  c.prepareStatement(sql);
			this.sentencia.setString(1, area.getAre_descri());
			this.sentencia.setString(2, area.getAre_obse());
			this.sentencia.setInt(3, area.getAre_codigo());
			sentencia.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error :"+e.getMessage());
			e.printStackTrace();
		}
		desconectar();
	}
	
	public void eliminar(int id){
		this.c = ManejadorConexiones.obtenerConnection();
		try {			
			String sql = "DELETE FROM areas WHERE are_codigo=?;";
			sentencia =  c.prepareStatement(sql);
			this.sentencia.setInt(1, id);
			sentencia.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error :"+e.getMessage());
			e.printStackTrace();
		}
		desconectar();
	}
	
	public AreaDTO buscarPorId(int id){
		AreaDTO area = null;
		c = ManejadorConexiones.obtenerConnection();
		String sql = "SELECT * FROM areas WHERE are_codigo = ?;";
		try {
			sentencia = c.prepareStatement(sql);
			sentencia.setInt(1, id);
			ResultSet rs = sentencia.executeQuery();
			if (rs.next()){
				area = new AreaDTO();
				area.setAre_codigo(rs.getInt("are_codigo"));
				area.setAre_descri(rs.getString("are_descri").trim());
				area.setAre_obse(rs.getString("are_obse").trim());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error :"+e.getMessage());
			e.printStackTrace();
		}
		desconectar();
		return area;
	}
	
	
	
	public int ultimoCodigo(){
		int ultimoCodigo = 0;
		this.c = ManejadorConexiones.obtenerConnection();
		try {
			this.stm = c.createStatement();
			String sql = "SELECT are_codigo FROM areas ORDER BY are_codigo DESC LIMIT 1";
			this.rs = stm.executeQuery (sql);
			if ( this.rs.next ()){
				ultimoCodigo = rs.getInt("are_codigo");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error :"+e.getMessage());
			e.printStackTrace();
		}
	desconectar();
	return ultimoCodigo;
	
	}
	
	public ArrayList<AreaDTO> buscarPorDescripcion(String descripcion){
		ArrayList<AreaDTO> lista = new ArrayList<AreaDTO>();
		c = ManejadorConexiones.obtenerConnection();
		String sql = "SELECT * FROM areas WHERE are_descri ILIKE ? ORDER BY are_descri ASC;";
		try {
			sentencia = c.prepareStatement(sql);
			sentencia.setString(1, "%"+descripcion+"%");
			ResultSet rs = sentencia.executeQuery();
			while (rs.next()){
				AreaDTO area = new AreaDTO();
				area.setAre_codigo(rs.getInt("are_codigo"));
				area.setAre_descri(rs.getString("are_descri").trim());
				area.setAre_obse(rs.getString("are_obse").trim());
				lista.add(area);
			}			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error :"+e.getMessage());
			e.printStackTrace();
		}
		desconectar();
		return lista;
	}
	
	public boolean buscarPorDescripcionIgual(AreaDTO area){
		boolean existe = false;
		c = ManejadorConexiones.obtenerConnection();
		String sql = "SELECT * FROM areas WHERE are_codigo<>? AND are_descri=? ORDER BY are_codigo ASC";
		try {
			sentencia = c.prepareStatement(sql);
			sentencia.setInt(1, area.getAre_codigo());
			sentencia.setString(2, area.getAre_descri());
			ResultSet rs = sentencia.executeQuery();
			if (!rs.next()){
			}else{
				existe = true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error :"+e.getMessage());
			e.printStackTrace();
		}
		desconectar();
		return existe;
	}
	
	public void desconectar(){
		try {
			if (rs != null) {
				rs.close ();
			}
			if (sentencia != null) {
				sentencia.close ();
			}
			if (c != null) {
				c.close ();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Exception: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	

}
