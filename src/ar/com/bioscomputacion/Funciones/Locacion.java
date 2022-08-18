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
import java.sql.SQLException;
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
    private String categoria;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();

    public Locacion(String nombre_locacion, String ubicacion_locacion, String observacion, String categoria) {
        
        this.nombre_locacion = nombre_locacion;
        this.ubicacion_locacion = ubicacion_locacion;
        this.observacion = observacion;
        this.categoria = categoria;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean registrarLocacion(Locacion locacion){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO locacion (nombre_locacion, ubicacion_locacion, observacion, categoria) "
                    + "VALUES (?,?,?,?)");
            
            pst.setString(1, locacion.getNombre_locacion());
            pst.setString(2, locacion.getUbicacion_locacion());
            pst.setString(3, locacion.getObservacion());
            pst.setString(4, locacion.getCategoria());
            
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

    public boolean eliminarLocacion(int codigoLocacion) {

        try {

            PreparedStatement pst;
            pst = cn.prepareStatement("DELETE FROM locacion WHERE codigo_locacion ='"+ codigoLocacion +"'");

            int N = pst.executeUpdate();

            if (N != 0) {
                
                ConexionBD.close(cn);
                ConexionBD.close(pst);
                return true;
                
            } else {
                
                ConexionBD.close(cn);
                ConexionBD.close(pst);
                return false;
                
            }
            
        } catch (SQLException ex) {
            
            ex.printStackTrace(System.out);
            
        }

        return false;
    }
    
    public boolean modificarLocacion(Locacion locacion, int codigoLocacion) {

        try {

            PreparedStatement pst = cn.prepareStatement("update locacion set nombre_locacion = ?, ubicacion_locacion = ?, observacion = ?, categoria = ? where codigo_locacion = ?");
            
            pst.setString(1, locacion.getNombre_locacion());
            pst.setString(2, locacion.getUbicacion_locacion());
            pst.setString(3, locacion.getObservacion());
            pst.setString(4, locacion.getCategoria());
            pst.setInt(5, codigoLocacion);
            
            int N = pst.executeUpdate();
            
            if (N != 0) {
                
                //ConexionBD.close(cn);
                //ConexionBD.close(pst);
                //ConexionBD.close(pst2);
                return true;
                
            } else {
                
                //ConexionBD.close(cn);
                //ConexionBD.close(pst);
                //ConexionBD.close(pst2);
                return false;
                
            }
            
        } catch (SQLException ex) {
            
            ex.printStackTrace(System.out);
            
        }

        return false;
    }
    
    
    public DefaultTableModel listarLocaciones(String buscar) {
        
        DefaultTableModel modelo;

        String[] titulos = {"ID LOCACION", "NOMBRE", "UBICACION", "OBSERVACION", "CATEGORIA"};

        String[] registros = new String[5];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from locacion WHERE nombre_locacion LIKE '%" + buscar + "%' ORDER BY codigo_locacion ASC");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_locacion");
                registros[1] = rs.getString("nombre_locacion");
                registros[2] = rs.getString("ubicacion_locacion");
                registros[3] = rs.getString("observacion");
                registros[4] = rs.getString("categoria");

                modelo.addRow(registros);
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
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

    public String mostrarCategoriaLocacion(int codigoLocacion) {
        
        String categoriaLocacion = "";

        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT categoria from locacion WHERE codigo_locacion = '" + codigoLocacion + "'");

            while (rs.next()){
            
                categoriaLocacion = rs.getString("categoria");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return categoriaLocacion;
            
        }
        
        return categoriaLocacion;
    
    }
    
    public String mostrarNombreLocacion(int codigoLocacion) {
        
        String nombreLocacion = "";

        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre_locacion from locacion WHERE codigo_locacion = '" + codigoLocacion + "'");

            while (rs.next()){
            
                nombreLocacion = rs.getString("nombre_locacion");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return nombreLocacion;
            
        }
        
        return nombreLocacion;
    
    }
    
}
