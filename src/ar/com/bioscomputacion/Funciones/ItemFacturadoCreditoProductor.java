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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlo
 */
public class ItemFacturadoCreditoProductor {

    private int codigoItemFinanciado;
    private int codigoCredito;
    private String descripcionItemFinanciado;
    private Double cantidadItemFinanciado;

    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();

    public ItemFacturadoCreditoProductor(int codigoItemFinanciado, int codigoCredito, String descripcionItemFinanciado, Double cantidadItemFinanciado) {
        
        this.codigoItemFinanciado = codigoItemFinanciado;
        this.codigoCredito = codigoCredito;
        this.descripcionItemFinanciado = descripcionItemFinanciado;
        this.cantidadItemFinanciado = cantidadItemFinanciado;
        
    }

    public ItemFacturadoCreditoProductor() {
    }

    public int getCodigoItemFinanciado() {
        return codigoItemFinanciado;
    }

    public void setCodigoItemFinanciado(int codigoItemFinanciado) {
        this.codigoItemFinanciado = codigoItemFinanciado;
    }

    public int getCodigoCredito() {
        return codigoCredito;
    }

    public void setCodigoCredito(int codigoCredito) {
        this.codigoCredito = codigoCredito;
    }

    public String getDescripcionItemFinanciado() {
        return descripcionItemFinanciado;
    }

    public void setDescripcionItemFinanciado(String descripcionItemFinanciado) {
        this.descripcionItemFinanciado = descripcionItemFinanciado;
    }

    public Double getCantidadItemFinanciado() {
        return cantidadItemFinanciado;
    }

    public void setCantidadItemFinanciado(Double cantidadItemFinanciado) {
        this.cantidadItemFinanciado = cantidadItemFinanciado;
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

    public boolean financiarItem(ItemFacturadoCreditoProductor itemFinanciado){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO items_facturados_credito_productor (codigo_item_financiado, codigo_credito, descripcion_item_financiado, cantidad_item_financiado) "
                    + "VALUES (?,?,?,?)");
            
            pst.setInt(1, itemFinanciado.getCodigoItemFinanciado());
            pst.setInt(2, itemFinanciado.getCodigoCredito());
            pst.setString(3, itemFinanciado.getDescripcionItemFinanciado());
            pst.setDouble(4, itemFinanciado.getCantidadItemFinanciado());
            
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
