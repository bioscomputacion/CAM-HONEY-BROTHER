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
    
    public DefaultTableModel listarItemsFinanciados(int compraConsignacion) {

        DefaultTableModel modelo;

        String[] titulos = {"ID", "CODIGO_COMPRA", "DESCRIPCION", "CANTIDAD"};

        String[] registros = new String[4];

        modelo = new DefaultTableModel(null, titulos) {
            
            /*@Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 21) {
                    return true;
                } else {
                    return false;
                }

            }*/

        };
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM items_facturados_credito_productor WHERE codigo_credito = '"+compraConsignacion+"'  ORDER BY codigo_item_financiado ASC");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_item_financiado");
                registros[1] = rs.getString("codigo_credito");
                registros[2] = rs.getString("descripcion_item_financiado");
                registros[3] = rs.getString("cantidad_item_financiado");

                modelo.addRow(registros);
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }

    public Double obtenerCantidadFacturadaItemFinanciado(int factura, int itemFacturado) {

        //metodo para calcular la cantidad de miel facturada para el item y la compra consultados
        
        Double cantidadFacturadaItem = 0.00;
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_item_facturado) as cantidad_item_facturado FROM items_facturados_factura_productor WHERE codigo_factura = '"+ factura+ "' and codigo_item_facturado = '"+ itemFacturado +"'");

            while (rs.next()) {
                
                cantidadFacturadaItem = rs.getDouble("cantidad_item_facturado");
                
            }
            
            //ConexionBD.close(cn);
            //ConexionBD.close(st);
            //ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return cantidadFacturadaItem;
        
    }

    public Double obtenerCantidadPresupuestadaItemFinanciado(int presupuesto, int itemPresupuestado) {

        //metodo para calcular la cantidad de miel presupuestada para el item y la compra consultados
        
        Double cantidadPresupuestadaItem = 0.00;
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_item_presupuestado) as cantidad_item_presupuestado FROM items_facturados_presupuesto_productor WHERE codigo_presupuesto = '"+ presupuesto+ "' and codigo_item_presupuestado = '"+ itemPresupuestado +"'");

            while (rs.next()) {
                
                cantidadPresupuestadaItem = rs.getDouble("cantidad_item_presupuestado");
                
            }
            
            //ConexionBD.close(cn);
            //ConexionBD.close(st);
            //ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return cantidadPresupuestadaItem;
        
    }

    public Double obtenerCantidadDevueltaItemFinanciado(int devolucion, int itemDevuelto) {

        //metodo para calcular la cantidad de miel devuelta para el item y la compra consultados
        
        Double cantidadDevueltaItem = 0.00;
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_item_devuelto) as cantidad_item_devuelto FROM items_devueltos_devolucion_productor WHERE codigo_devolucion = '"+ devolucion+ "' and codigo_item_devuelto = '"+ itemDevuelto +"'");

            while (rs.next()) {
                
                cantidadDevueltaItem = rs.getDouble("cantidad_item_devuelto");
                
            }
            
            //ConexionBD.close(cn);
            //ConexionBD.close(st);
            //ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return cantidadDevueltaItem;
        
    }

}
