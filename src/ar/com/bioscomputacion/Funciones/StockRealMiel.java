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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caco
 */
public class StockRealMiel {
    
    private int codigo_movimiento;
    private Date fecha_movimiento; 
    private String tipo_movimiento;
    private int comprobante_asociado;
    private Double cantidad_miel;
    private int locacion_miel;
    private String observacion;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();

    public StockRealMiel(int codigo_movimiento, Date fecha_movimiento, String tipo_movimiento, int comprobante_asociado, Double cantidad_miel, int locacion_miel, String observacion) {
        this.codigo_movimiento = codigo_movimiento;
        this.fecha_movimiento = fecha_movimiento;
        this.tipo_movimiento = tipo_movimiento;
        this.comprobante_asociado = comprobante_asociado;
        this.cantidad_miel = cantidad_miel;
        this.locacion_miel = locacion_miel;
        this.observacion = observacion;
    }

    public StockRealMiel() {
    }

    public int getCodigo_movimiento() {
        return codigo_movimiento;
    }

    public void setCodigo_movimiento(int codigo_movimiento) {
        this.codigo_movimiento = codigo_movimiento;
    }

    public Date getFecha_movimiento() {
        return fecha_movimiento;
    }

    public void setFecha_movimiento(Date fecha_movimiento) {
        this.fecha_movimiento = fecha_movimiento;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public int getComprobante_asociado() {
        return comprobante_asociado;
    }

    public void setComprobante_asociado(int comprobante_asociado) {
        this.comprobante_asociado = comprobante_asociado;
    }

    public Double getCantidad_miel() {
        return cantidad_miel;
    }

    public void setCantidad_miel(Double cantidad_miel) {
        this.cantidad_miel = cantidad_miel;
    }

    public int getLocacion_miel() {
        return locacion_miel;
    }

    public void setLocacion_miel(int locacion_miel) {
        this.locacion_miel = locacion_miel;
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
    
    public boolean registrarMovimientoStock(StockRealMiel stockMiel){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO stock_real_miel (fecha_movimiento, tipo_movimiento, comprobante_asociado, cantidad_miel, locacion_miel, observacion) "
                    + "VALUES (?,?,?,?,?,?)");
            
            pst.setDate(1, stockMiel.getFecha_movimiento());
            pst.setString(2, stockMiel.getTipo_movimiento());
            pst.setInt(3, stockMiel.getComprobante_asociado());
            pst.setDouble(4, stockMiel.getCantidad_miel());
            pst.setDouble(5, stockMiel.getLocacion_miel());
            pst.setString(6, stockMiel.getObservacion());
            
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
    
    public String obtenerNombreLocacion(int codigoLocacion){
        
        String nombreLocacion="";
        
        try {
            
            //SELECT SUM(cantidad_miel) from stock_real_miel WHere locacion_miel=1
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre_locacion from locacion WHERE codigo_locacion='" + codigoLocacion + "'");

            while (rs.next()) {
                
                nombreLocacion = rs.getString("nombre_locacion");

            }
            
            //ConexionBD.close(cn);
            //ConexionBD.close(st);
            //ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return nombreLocacion;
        
    }
    
    //Y SI DEVUELVE UN STRING????
    public String obtenerDetalleStockLocacion(int codigoLocacion){
        
        String detalleStockLocacion="0.00";
        
        try {
            
            //SELECT SUM(cantidad_miel) from stock_real_miel WHere locacion_miel=1
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) from stock_real_miel WHERE locacion_miel='" + codigoLocacion + "'");

            while (rs.next()) {
                
                detalleStockLocacion = rs.getString("SUM(cantidad_miel)");

            }
            
            //ConexionBD.close(cn);
            //ConexionBD.close(st);
            //ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return detalleStockLocacion;
        
    }
    
}
