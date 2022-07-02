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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlo
 */
public class ItemDevueltoDevolucionProductor {

    private int codigoItemDevuelto;
    private int codigoDevolucion;
    private String descripcionItemDevuelto;
    private Double cantidadItemDevuelto;

    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();

    public ItemDevueltoDevolucionProductor(int codigoItemDevuelto, int codigoDevolucion, String descripcionItemDevuelto, Double cantidadItemDevuelto) {

        this.codigoItemDevuelto = codigoItemDevuelto;
        this.codigoDevolucion = codigoDevolucion;
        this.descripcionItemDevuelto = descripcionItemDevuelto;
        this.cantidadItemDevuelto = cantidadItemDevuelto;
        
    }

    public ItemDevueltoDevolucionProductor() {
    }

    public int getCodigoItemDevuelto() {
        return codigoItemDevuelto;
    }

    public void setCodigoItemDevuelto(int codigoItemDevuelto) {
        this.codigoItemDevuelto = codigoItemDevuelto;
    }

    public int getCodigoDevolucion() {
        return codigoDevolucion;
    }

    public void setCodigoDevolucion(int codigoDevolucion) {
        this.codigoDevolucion = codigoDevolucion;
    }

    public String getDescripcionItemDevuelto() {
        return descripcionItemDevuelto;
    }

    public void setDescripcionItemDevuelto(String descripcionItemDevuelto) {
        this.descripcionItemDevuelto = descripcionItemDevuelto;
    }

    public Double getCantidadItemDevuelto() {
        return cantidadItemDevuelto;
    }

    public void setCantidadItemDevuelto(Double cantidadItemDevuelto) {
        this.cantidadItemDevuelto = cantidadItemDevuelto;
    }

    public int mostrarIdItemDevuelto(int codigoDevolucion) {

        int codigoItemDevuelto = 0;
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_item_devuelto FROM items_devueltos_devolucion_productor where codigo_devolucion='"+codigoDevolucion+"'");
            
            while(rs.next()){
                
                codigoItemDevuelto = rs.getInt("codigo_item_devuelto");
                
            }
            
        } catch (Exception e) {
            
            return codigoItemDevuelto;
            
        }
        
        return codigoItemDevuelto;
        
    }

    public boolean devolverItem(ItemDevueltoDevolucionProductor itemDevuelto){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO items_devueltos_devolucion_productor (codigo_item_devuelto, codigo_devolucion, descripcion_item_devuelto, cantidad_item_devuelto) "
                    + "VALUES (?,?,?,?)");
            
            pst.setInt(1, itemDevuelto.getCodigoItemDevuelto());
            pst.setInt(2, itemDevuelto.getCodigoDevolucion());
            pst.setString(3, itemDevuelto.getDescripcionItemDevuelto());
            pst.setDouble(4, itemDevuelto.getCantidadItemDevuelto());
            
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
    
    public DefaultTableModel listarItemsDevueltos(int devolucion) {

        DefaultTableModel modelo;

        String[] titulos = {"ID", "ID DEVOLUCION", "DESCRIPCION", "CANTIDAD"};

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
            ResultSet rs = st.executeQuery("SELECT * FROM items_devueltos_devolucion_productor WHERE codigo_devolucion = '"+devolucion+"'  ORDER BY codigo_item_devuelto ASC");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_item_devuelto");
                registros[1] = rs.getString("codigo_credito");
                registros[2] = rs.getString("descripcion_item_devuelto");
                registros[3] = rs.getString("cantidad_item_devuelto");

                modelo.addRow(registros);
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }
    
}
