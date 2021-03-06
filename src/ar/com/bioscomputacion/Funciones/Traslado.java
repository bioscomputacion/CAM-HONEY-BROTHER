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
 * @author Caco
 */
public class Traslado {
    
    private int codigo_traslado;
    private String numero_comprobante;
    private String descripcion_item_trasladado; 
    private Double cantidad_item_trasladado;
    private String observacion_traslado;
    private int origen_traslado;
    private int destino_traslado;
    private Date fecha_traslado;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();

    public Traslado(String numero_comprobante, String descripcion_item_trasladado, Double cantidad_item_trasladado, String observacion_traslado, int origen_traslado, int destino_traslado, Date fecha_traslado) {
        
        this.numero_comprobante = numero_comprobante;
        this.descripcion_item_trasladado = descripcion_item_trasladado;
        this.cantidad_item_trasladado = cantidad_item_trasladado;
        this.observacion_traslado = observacion_traslado;
        this.origen_traslado = origen_traslado;
        this.destino_traslado = destino_traslado;
        this.fecha_traslado = fecha_traslado;
        
    }

    public Traslado() {
    }

    public int getCodigo_traslado() {
        return codigo_traslado;
    }

    public void setCodigo_traslado(int codigo_traslado) {
        this.codigo_traslado = codigo_traslado;
    }

    public String getNumero_comprobante() {
        return numero_comprobante;
    }

    public void setNumero_comprobante(String numero_comprobante) {
        this.numero_comprobante = numero_comprobante;
    }

    public String getDescripcion_item_trasladado() {
        return descripcion_item_trasladado;
    }

    public void setDescripcion_item_trasladado(String descripcion_item_trasladado) {
        this.descripcion_item_trasladado = descripcion_item_trasladado;
    }

    public Double getCantidad_item_trasladado() {
        return cantidad_item_trasladado;
    }

    public void setCantidad_item_trasladado(Double cantidad_item_trasladado) {
        this.cantidad_item_trasladado = cantidad_item_trasladado;
    }

    public String getObservacion_traslado() {
        return observacion_traslado;
    }

    public void setObservacion_traslado(String observacion_traslado) {
        this.observacion_traslado = observacion_traslado;
    }

    public int getOrigen_traslado() {
        return origen_traslado;
    }

    public void setOrigen_traslado(int origen_traslado) {
        this.origen_traslado = origen_traslado;
    }

    public int getDestino_traslado() {
        return destino_traslado;
    }

    public void setDestino_traslado(int destino_traslado) {
        this.destino_traslado = destino_traslado;
    }

    public Date getFecha_traslado() {
        return fecha_traslado;
    }

    public void setFecha_traslado(Date fecha_traslado) {
        this.fecha_traslado = fecha_traslado;
    }

    public int mostrarIdTraslado() {

        int codigoTraslado = 0;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_traslado FROM traslado order by codigo_traslado asc");
            
            while (rs.next()) {

                codigoTraslado = rs.getInt("codigo_traslado");
                
            }
            
            return codigoTraslado;

        }catch(Exception e){
            
            return codigoTraslado;
        } 

    }
    
    public boolean registrarTrasladoMiel(Traslado traslado){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO traslado (numero_comprobante, descripcion_item_trasladado, cantidad_item_trasladado, observacion_traslado, origen_traslado, destino_traslado, fecha_traslado) "
                    + "VALUES (?,?,?,?,?,?,?)");
            
            
            pst.setString(1, traslado.getNumero_comprobante());
            pst.setString(2, traslado.getDescripcion_item_trasladado());
            pst.setDouble(3, traslado.getCantidad_item_trasladado());
            pst.setString(4, traslado.getObservacion_traslado());
            pst.setInt(5, traslado.getOrigen_traslado());
            pst.setInt(6, traslado.getDestino_traslado());
            pst.setDate(7, traslado.getFecha_traslado());
            
            
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
    
    public boolean modificarTrasladoMiel(Traslado traslado, int codigoTraslado) {

        try {


            PreparedStatement pst = cn.prepareStatement("UPDATE traslado SET numero_comprobante = ?,fecha_ingreso = ?,cantidad_miel = ?,observacion = ? WHERE codigo_traslado = '"+ codigoTraslado +"'");

            pst.setString(1, traslado.getNumero_comprobante());
            pst.setString(2, traslado.getDescripcion_item_trasladado());
            pst.setDouble(3, traslado.getCantidad_item_trasladado());
            pst.setString(4, traslado.getObservacion_traslado());
            pst.setInt(5, traslado.getOrigen_traslado());
            pst.setInt(6, traslado.getDestino_traslado());
            pst.setDate(7, traslado.getFecha_traslado());

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

    public boolean eliminarTrasladoMiel(int codigoTraslado) {

        try {

            PreparedStatement pst = cn.prepareStatement("DELETE FROM traslado WHERE codigo_traslado = '"+ codigoTraslado +"'");

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
