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
 * @author Caco
 */
public class ItemFacturadoPresupuestoProductor {
    
    private int codigoItemPresupuestado;
    private int codigoPresupuesto;
    private String descripcionItemPresupuestado;
    private Double cantidadItemPresupuestado;
    private Double importeItemPresupuestado;
    private Double totalItemPresupuestado;

    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    
    public ItemFacturadoPresupuestoProductor(int codigoItemPresupuestado, int codigoPresupuesto, String descripcionItemPresupuestado, Double cantidadItemPresupuestado, Double importeItemPresupuestado, Double totalItemPresupuestado) {
        this.codigoItemPresupuestado = codigoItemPresupuestado;
        this.codigoPresupuesto = codigoPresupuesto;
        this.descripcionItemPresupuestado = descripcionItemPresupuestado;
        this.cantidadItemPresupuestado = cantidadItemPresupuestado;
        this.importeItemPresupuestado = importeItemPresupuestado;
        this.totalItemPresupuestado = totalItemPresupuestado;
    }

    public ItemFacturadoPresupuestoProductor() {
        
    }

    public int getCodigoItemPresupuestado() {
        return codigoItemPresupuestado;
    }

    public void setCodigoItemPresupuestado(int codigoItemPresupuestado) {
        this.codigoItemPresupuestado = codigoItemPresupuestado;
    }

    public int getCodigoPresupuesto() {
        return codigoPresupuesto;
    }

    public void setCodigoPresupuesto(int codigoPresupuesto) {
        this.codigoPresupuesto = codigoPresupuesto;
    }

    public String getDescripcionItemPresupuestado() {
        return descripcionItemPresupuestado;
    }

    public void setDescripcionItemPresupuestado(String descripcionItemPresupuestado) {
        this.descripcionItemPresupuestado = descripcionItemPresupuestado;
    }

    public Double getCantidadItemPresupuestado() {
        return cantidadItemPresupuestado;
    }

    public void setCantidadItemPresupuestado(Double cantidadItemPresupuestado) {
        this.cantidadItemPresupuestado = cantidadItemPresupuestado;
    }

    public Double getImporteItemPresupuestado() {
        return importeItemPresupuestado;
    }

    public void setImporteItemPresupuestado(Double importeItemPresupuestado) {
        this.importeItemPresupuestado = importeItemPresupuestado;
    }

    public Double getTotalItemPresupuestado() {
        return totalItemPresupuestado;
    }

    public void setTotalItemPresupuestado(Double totalItemPresupuestado) {
        this.totalItemPresupuestado = totalItemPresupuestado;
    }

    
    public int mostrarIdItemPresupuestado(int codigoPresupuesto) {

        int codigoItemPresupuestado = 0;
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_item_presupuestado FROM items_facturados_presupuesto_productor where codigo_presupuesto='"+codigoPresupuesto+"'");
            
            while(rs.next()){
                
                codigoItemPresupuestado = rs.getInt("codigo_item_presupuestado");
                
            }
            
        } catch (Exception e) {
            
            return codigoItemPresupuestado;
            
        }
        
        return codigoItemPresupuestado;
        
    }

    public boolean presupuestarItem(ItemFacturadoPresupuestoProductor itemPresupuestado){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO items_facturados_presupuesto_productor (codigo_item_presupuestado, codigo_presupuesto, descripcion_item_presupuestado, cantidad_item_presupuestado, importe_item_presupuestado, total_item_presupuestado) "
                    + "VALUES (?,?,?,?,?,?)");
            
            pst.setInt(1, itemPresupuestado.getCodigoItemPresupuestado());
            pst.setInt(2, itemPresupuestado.getCodigoPresupuesto());
            pst.setString(3, itemPresupuestado.getDescripcionItemPresupuestado());
            pst.setDouble(4, itemPresupuestado.getCantidadItemPresupuestado());
            pst.setDouble(5, itemPresupuestado.getImporteItemPresupuestado());
            pst.setDouble(6, itemPresupuestado.getTotalItemPresupuestado());
            
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
    
    /*
    public boolean quitarItem(int factura, int item) {

        sSQL = "DELETE FROM items_facturados_factura WHERE codigo_factura = ? AND codigo_item_facturado = ?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, factura);
            pst.setInt(2, item);
            int N = pst.executeUpdate();

            if (N != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }

    }

    public DefaultTableModel mostrarItemsFacturados(String datos){
        
        DefaultTableModel modelo;

        String[] titulos
                = {"CODIGO", "FACTURA", "DESCRIPCION", "CANTIDAD",
                    "IMPORTE", "SUBTOTAL"};

        String[] registros = new String[6];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "SELECT codigo_item_facturado,codigo_factura, descripcion_item_facturado,cantidad_item_facturado,importe_item_facturado,total_item_facturado FROM items_facturados_factura"
                +" WHERE codigo_factura='"+datos+"'";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registros[0] = rs.getString("codigo_item_facturado");
                registros[1] = rs.getString("codigo_factura");
                registros[2] = rs.getString("descripcion_item_facturado");
                registros[3] = rs.getString("cantidad_item_facturado");
                registros[4] = rs.getString("importe_item_facturado");
                registros[5] = rs.getString("total_item_facturado");

                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
    }
    
    public boolean cancelarItemsFacturados(int datos) {

        sSQL = "DELETE FROM items_facturados_factura WHERE codigo_factura =?";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, datos);
            
            int N = pst.executeUpdate();
            if (N != 0) {
                return true;
            } else {

                return false;
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
            return false;
        }

    }
    */
    
}
