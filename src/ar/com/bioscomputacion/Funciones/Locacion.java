/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Funciones;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caco
 */
public class Locacion {
    
    private int codigo_locacion;
    private String nombre_locacion; 
    private String ubicacion_locacion;
    private String observacion;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();

    public Locacion(String nombre_locacion, String ubicacion_locacion, String observacion) {
        this.nombre_locacion = nombre_locacion;
        this.ubicacion_locacion = ubicacion_locacion;
        this.observacion = observacion;
    }

    public Locacion() {
    }

    public int getCodigo_locacion() {
        return codigo_locacion;
    }

    public void setCodigo_locacion(int codigo_locacion) {
        this.codigo_locacion = codigo_locacion;
    }

    public String getNombre_locacion() {
        return nombre_locacion;
    }

    public void setNombre_locacion(String nombre_locacion) {
        this.nombre_locacion = nombre_locacion;
    }

    public String getUbicacion_locacion() {
        return ubicacion_locacion;
    }

    public void setUbicacion_locacion(String ubicacion_locacion) {
        this.ubicacion_locacion = ubicacion_locacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public ConexionBD getMysql() {
        return mysql;
    }

    public void setMysql(ConexionBD mysql) {
        this.mysql = mysql;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    public boolean registrarLocacion(Locacion locacion){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO locacion (nombre_locacion, ubicacion_locacion, observacion) "
                    + "VALUES (?,?,?)");
            
            pst.setString(1, locacion.getNombre_locacion());
            pst.setString(2, locacion.getUbicacion_locacion());
            pst.setString(3, locacion.getObservacion());
            
            int N = pst.executeUpdate();

            if (N != 0) {
                
                return true;
                
            } else {
                
                return false;
                
            }
            
            
        } catch (Exception e) {
            
        }
        
        return false;
        
    }
    
    public DefaultTableModel listarLocaciones(String buscar) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        return modelo;
    
    }
    
    public JComboBox cargarComboLocaciones() {

        JComboBox modelo = new JComboBox();

        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select nombre_locacion from locacion");

            modelo.addItem("SELECCIONAR");
            
            while (rs.next()) {
                
                modelo.addItem(rs.getString(nombre_locacion));
                
            }
            
            //ConexionBD.close(cn);
            //ConexionBD.close(st);
            //ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
    }
    
}
