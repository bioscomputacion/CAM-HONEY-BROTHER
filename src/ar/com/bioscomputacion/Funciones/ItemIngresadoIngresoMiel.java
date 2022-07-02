/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author carlo
 */
public class ItemIngresadoIngresoMiel {
    
    private int codigoItemIngresado;
    private int codigoIngreso;
    private String descripcionItemIngresado;
    private Double cantidadItemIngresado;

    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();

    public ItemIngresadoIngresoMiel(int codigoItemIngresado, int codigoIngreso, String descripcionItemIngresado, Double cantidadItemIngresado) {
        
        this.codigoItemIngresado = codigoItemIngresado;
        this.codigoIngreso = codigoIngreso;
        this.descripcionItemIngresado = descripcionItemIngresado;
        this.cantidadItemIngresado = cantidadItemIngresado;
        
    }

    public int getCodigoItemIngresado() {
        return codigoItemIngresado;
    }

    public void setCodigoItemIngresado(int codigoItemIngresado) {
        this.codigoItemIngresado = codigoItemIngresado;
    }

    public int getCodigoIngreso() {
        return codigoIngreso;
    }

    public void setCodigoIngreso(int codigoIngreso) {
        this.codigoIngreso = codigoIngreso;
    }

    public String getDescripcionItemIngresado() {
        return descripcionItemIngresado;
    }

    public void setDescripcionItemIngresado(String descripcionItemIngresado) {
        this.descripcionItemIngresado = descripcionItemIngresado;
    }

    public Double getCantidadItemIngresado() {
        return cantidadItemIngresado;
    }

    public void setCantidadItemIngresado(Double cantidadItemIngresado) {
        this.cantidadItemIngresado = cantidadItemIngresado;
    }

    public ItemIngresadoIngresoMiel() {
    }
    
    public int mostrarIdItemFinanciado(int codigoCredito) {

        int codigoItemFinanciado = 0;
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_item_financiado FROM items_facturados_credito where codigo_credito='"+codigoCredito+"'");
            
            while(rs.next()){
                
                codigoItemFinanciado = rs.getInt("codigo_item_financiado");
                
            }
            
        } catch (Exception e) {
            
            return codigoItemFinanciado;
            
        }
        
        return codigoItemFinanciado;
        
    }

    public boolean ingresarItem(ItemIngresadoIngresoMiel itemIngresado){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO items_ingresados_ingreso_miel_propia (codigo_item_ingresado, codigo_ingreso, descripcion_item_ingresado, cantidad_item_ingresado) "
                    + "VALUES (?,?,?,?)");
            
            pst.setInt(1, itemIngresado.getCodigoItemIngresado());
            pst.setInt(2, itemIngresado.getCodigoIngreso());
            pst.setString(3, itemIngresado.getDescripcionItemIngresado());
            pst.setDouble(4, itemIngresado.getCantidadItemIngresado());
            
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
    
    
}
