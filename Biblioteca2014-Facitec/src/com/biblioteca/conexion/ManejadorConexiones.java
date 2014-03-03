package com.biblioteca.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class ManejadorConexiones {

	public static Connection obtenerConnection(){
		Connection c = null;
		ResourceBundle bunble = ResourceBundle.getBundle("Connections");
		String host = bunble.getString("host");
		String port = bunble.getString("port");
		String dbId = bunble.getString("dbId");
		String user = bunble.getString("user");
		String password = bunble.getString("passwd");
		String url = null;
		String driverName = "org.postgresql.Driver";
			try {
				Class.forName(driverName);
				url = "jdbc:postgresql://" + host + ":" + port + "/" + dbId; 
				c = DriverManager.getConnection(url, user, password);			
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Error ClassNotFount: "+e);
				e.printStackTrace();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error SQL: "+e);
				e.printStackTrace();
			}
//			System.out.println("OK - conectado");
		return c;
	}
}
