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
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();

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

    public IngresoMielPropia(String numeroComprobante, Date fechaIngreso, Double cantidadMiel) {
        
        this.numeroComprobante = numeroComprobante;
        this.fechaIngreso = fechaIngreso;
        this.cantidadMiel = cantidadMiel;
        
    }

    public IngresoMielPropia() {
    }
    
    public int mostrarIdIngresoMielPropia() {

        int codigoIngresoMielPropia = 0;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_ingreso FROM ingreso_miel_propia order by codigo_ingreso asc");
            
            while (rs.next()) {

                codigoIngresoMielPropia = rs.getInt("codigo_ingreso");
                
            }
            
            return codigoIngresoMielPropia;

        }catch(Exception e){
            
            return codigoIngresoMielPropia;
        } 

    }

    public int mostrarIdItemAIngresar(int codigoIngreso) {

        int codigoItemAIngresar = 0;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_item_ingresado FROM items_ingresados_ingreso_miel where codigo_ingreso='"+ codigoIngreso +"'");
            
            while (rs.next()) {

                codigoItemAIngresar = rs.getInt("codigo_item_ingresado");

            }
            
            return codigoItemAIngresar;

        }catch(Exception e){
            
            return codigoItemAIngresar;
        } 

    }

    public boolean registrarIngresoMielPropia(IngresoMielPropia ingresoMiel){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO ingreso_miel_propia (numero_comprobante, fecha_ingreso, cantidad_miel) "
                    + "VALUES (?,?,?)");
            
            
            pst.setString(1, ingresoMiel.getNumeroComprobante());
            pst.setDate(2, ingresoMiel.getFechaIngreso());
            pst.setDouble(3, ingresoMiel.getCantidadMiel());
            
            
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

    public boolean modificarIngresoMielPropia(IngresoMielPropia ingresoMiel, int codigoIngreso) {

        try {


            PreparedStatement pst = cn.prepareStatement("UPDATE ingreso_miel_propia SET numero_comprobante = ?,fecha_ingreso = ?,cantidad_miel = ? WHERE codigo_ingreso = '"+ codigoIngreso +"'");

            pst.setString(1, ingresoMiel.getNumeroComprobante());
            pst.setDate(2, ingresoMiel.getFechaIngreso());
            pst.setDouble(3, ingresoMiel.getCantidadMiel());

            int N = pst.executeUpdate();

            if (N != 0) {
                
                //ConexionBD.close(cn);
                //ConexionBD.close(pst);
                return true;
                
            } else {
                
                //ConexionBD.close(cn);
                //ConexionBD.close(pst);
                return false;
                
            }
        } catch (SQLException ex) {
            
            ex.printStackTrace(System.out);
            
        }

        return false;
    }

    public boolean eliminarIngresoMielPropia(int codigoIngreso) {

        try {

            PreparedStatement pst = cn.prepareStatement("DELETE FROM ingreso_miel_propia WHERE codigo_ingreso = '"+ codigoIngreso +"'");

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

}
