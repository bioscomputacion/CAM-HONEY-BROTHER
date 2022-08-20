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

/**
 *
 * @author carlo
 */
public class IngresoMielPropia {
    
    private int codigoIngreso;
    private String numeroComprobante; 
    private Date fechaIngreso;
    private Double cantidadMiel;
    private String observacion;
    
    public int getCodigoIngreso() {
        return codigoIngreso;
    }

    public void setCodigoIngreso(int codigoIngreso) {
        this.codigoIngreso = codigoIngreso;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Double getCantidadMiel() {
        return cantidadMiel;
    }

    public void setCantidadMiel(Double cantidadMiel) {
        this.cantidadMiel = cantidadMiel;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    public IngresoMielPropia(String numeroComprobante, Date fechaIngreso, Double cantidadMiel, String observacion) {
        
        this.numeroComprobante = numeroComprobante;
        this.fechaIngreso = fechaIngreso;
        this.cantidadMiel = cantidadMiel;
        this.observacion = observacion;
        
    }

    public IngresoMielPropia() {
    }
    
    public int mostrarIdIngresoMielPropia() {

        int codigoIngresoMielPropia = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_ingreso FROM ingreso_miel_propia order by codigo_ingreso asc");
            
            while (rs.next()) {

                codigoIngresoMielPropia = rs.getInt("codigo_ingreso");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
            return codigoIngresoMielPropia;

        }catch(Exception e){
            
            return codigoIngresoMielPropia;
        } 

    }

    public boolean registrarIngresoMielPropia(IngresoMielPropia ingresoMiel){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO ingreso_miel_propia (numero_comprobante, fecha_ingreso, cantidad_miel, observacion) "
                    + "VALUES (?,?,?,?)");
            
            
            pst.setString(1, ingresoMiel.getNumeroComprobante());
            pst.setDate(2, ingresoMiel.getFechaIngreso());
            pst.setDouble(3, ingresoMiel.getCantidadMiel());
            pst.setString(4, ingresoMiel.getObservacion());
            
            
            int N = pst.executeUpdate();

            ConexionBD.close(cn);
            ConexionBD.close(pst);
            
            if (N != 0) {
                
                return true;
                
            } else {
                
                return false;
                
            }
            
            
        } catch (Exception e) {
            
        }
        
        return false;
        
    }

    public boolean modificarIngresoMielPropia(IngresoMielPropia ingresoMiel, int codigoIngreso) {

        try {


            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("UPDATE ingreso_miel_propia SET numero_comprobante = ?,fecha_ingreso = ?,cantidad_miel = ?,observacion = ? WHERE codigo_ingreso = '"+ codigoIngreso +"'");

            pst.setString(1, ingresoMiel.getNumeroComprobante());
            pst.setDate(2, ingresoMiel.getFechaIngreso());
            pst.setDouble(3, ingresoMiel.getCantidadMiel());
            pst.setString(3, ingresoMiel.getObservacion());

            int N = pst.executeUpdate();

            ConexionBD.close(cn);
            ConexionBD.close(pst);
            
            if (N != 0) {
                
                return true;
                
            } else {
                
                return false;
                
            }
        } catch (SQLException ex) {
            
            ex.printStackTrace(System.out);
            
        }

        return false;
    }

    public boolean eliminarIngresoMielPropia(int codigoIngreso) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("DELETE FROM ingreso_miel_propia WHERE codigo_ingreso = '"+ codigoIngreso +"'");

            int N = pst.executeUpdate();

            ConexionBD.close(cn);
            ConexionBD.close(pst);
            
            if (N != 0) {
                
                return true;
                
            } else {
                
                return false;
                
            }
            
        } catch (SQLException ex) {
            
            ex.printStackTrace(System.out);
            
        }

        return false;
    }

}
