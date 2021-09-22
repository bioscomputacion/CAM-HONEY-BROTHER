/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Funciones;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author braya
 */
public class ConexionBD {
    
    public String db = "pintureriamoreno";
    public String url = "jdbc:mysql://127.0.0.1/" + db;
    public String user = "root";
    public String pass = "root";
    
    Connection link = null;

    public ConexionBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            link = DriverManager.getConnection(this.url, this.user, this.pass);

        } catch (ClassNotFoundException e) {
            System.out.println("Ocurrio un error en: = " + e);

        }catch (SQLException e) {
            System.out.println("Ocurrio un error en: = " + e);
        }
    }
    
    public Connection getConexionBD() {
        return link;
    }
    
}
