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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlo
 */
public class AjusteCompensacionStock {
    
    private int codigo_locacion;
    private Double stock_miel_pago;
    private Double stock_miel_impago;
    private Double stock_miel_impago_vendido;

    public AjusteCompensacionStock(int codigo_locacion, Double stock_miel_pago, Double stock_miel_impago, Double stock_miel_impago_vendido) {
        
        this.codigo_locacion = codigo_locacion;
        this.stock_miel_pago = stock_miel_pago;
        this.stock_miel_impago = stock_miel_impago;
        this.stock_miel_impago_vendido = stock_miel_impago_vendido;
        
    }

    public AjusteCompensacionStock() {
    }
    
    public int getCodigo_locacion() {
        return codigo_locacion;
    }

    public void setCodigo_locacion(int codigo_locacion) {
        this.codigo_locacion = codigo_locacion;
    }

    public Double getStock_miel_pago() {
        return stock_miel_pago;
    }

    public void setStock_miel_pago(Double stock_miel_pago) {
        this.stock_miel_pago = stock_miel_pago;
    }

    public Double getStock_miel_impago() {
        return stock_miel_impago;
    }

    public void setStock_miel_impago(Double stock_miel_impago) {
        this.stock_miel_impago = stock_miel_impago;
    }

    public Double getStock_miel_impago_vendido() {
        return stock_miel_impago_vendido;
    }

    public void setStock_miel_impago_vendido(Double stock_miel_impago_vendido) {
        this.stock_miel_impago_vendido = stock_miel_impago_vendido;
    }

    public boolean registrarValoresMielLocacion(AjusteCompensacionStock ajuste){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            PreparedStatement pst = cn.prepareStatement("INSERT INTO ajuste_compensacion_stock (codigo_locacion, stock_miel_pago, stock_miel_impago, stock_miel_impago_vendido) "
                    + "VALUES (?,?,?,?)");
            
            
            pst.setInt(1, ajuste.getCodigo_locacion());
            pst.setDouble(2, ajuste.getStock_miel_pago());
            pst.setDouble(3, ajuste.getStock_miel_impago());
            pst.setDouble(4, ajuste.getStock_miel_impago_vendido());
            
            
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

    public boolean modificarValoresMielLocacion(AjusteCompensacionStock ajuste, int codigoLocacion) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            PreparedStatement pst = cn.prepareStatement("UPDATE ajuste_compensacion_stock SET stock_miel_pago = ?, stock_miel_impago = ?, stock_miel_impago_vendido = ? WHERE codigo_locacion = '"+ codigoLocacion +"'");

            pst.setDouble(1, ajuste.getStock_miel_pago());
            pst.setDouble(2, ajuste.getStock_miel_impago());
            pst.setDouble(3, ajuste.getStock_miel_impago_vendido());

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

    public boolean eliminarAnulacionPresupuestoProductor(int codigoLocacion) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("DELETE FROM ajuste_compensacion_stock WHERE codigo_locacion = '"+ codigoLocacion +"'");

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

    public Double consultarCantidadMielPagaLocacion(int codigoLocacion) {

        Double cantidadMielPaga = 0.00;

        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT stock_miel_pago from ajuste_compensacion_stock  WHERE codigo_locacion = '"+ codigoLocacion+"'");

            while (rs.next()) {
                
                cantidadMielPaga = rs.getDouble("stock_miel_pago");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {

            return cantidadMielPaga;

        }
        
        return cantidadMielPaga;
        
    }
    
    public Double consultarCantidadMielImpagaLocacion(int codigoLocacion) {

        Double cantidadMielImpaga = 0.00;

        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT stock_miel_impago from ajuste_compensacion_stock  WHERE codigo_locacion = '"+ codigoLocacion+"'");

            while (rs.next()) {
                
                cantidadMielImpaga = rs.getDouble("stock_miel_impago");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {

            return cantidadMielImpaga;

        }
        
        return cantidadMielImpaga;
        
    }
    
    public Double consultarCantidadMielImpagaVendidaLocacion(int codigoLocacion) {

        Double cantidadMielImpagaVendida = 0.00;

        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT stock_miel_impago_vendido from ajuste_compensacion_stock  WHERE codigo_locacion = '"+ codigoLocacion+"'");

            while (rs.next()) {
                
                cantidadMielImpagaVendida = rs.getDouble("stock_miel_impago_vendido");
                
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
