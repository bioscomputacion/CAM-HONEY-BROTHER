/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author carlo
 */
public class CompensacionStock {
    
    private Double cantidad_miel_impaga_vendida;

    public CompensacionStock(Double cantidad_miel_impaga_vendida) {
        
        this.cantidad_miel_impaga_vendida = cantidad_miel_impaga_vendida;
        
    }

    public CompensacionStock() {
    }
    
    public Double getCantidad_miel_impaga_vendida() {
        
        return cantidad_miel_impaga_vendida;
        
    }

    public void setCantidad_miel_impaga_vendida(Double cantidad_miel_impaga_vendida) {
        
        this.cantidad_miel_impaga_vendida = cantidad_miel_impaga_vendida;
        
    }

    public boolean actualizarCantidadMielImpagaVendida(Double cantidadMielImpagaFacturada) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            PreparedStatement pst = cn.prepareStatement("UPDATE compensacion_stock SET cantidad_miel_impaga_vendida = ?");
            
            pst.setDouble(1, cantidadMielImpagaFacturada);
            
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

    public Double consultarCantidadMielImpagaVendida() {

        Double cantidadMielImpagaVendida = 0.00;

        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT cantidad_miel_impaga_vendida FROM compensacion_stock");

            while (rs.next()) {
                
                cantidadMielImpagaVendida = rs.getDouble("cantidad_miel_impaga_vendida");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return cantidadMielImpagaVendida;
            
        }
        
        return cantidadMielImpagaVendida;
        
    }

}
