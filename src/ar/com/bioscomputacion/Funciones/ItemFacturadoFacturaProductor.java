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
public class ItemFacturadoFacturaProductor {
    
    private int codigoItemFacturado;
    private int codigoFactura;
    private String descripcionItemFacturado;
    private Double cantidadItemFacturado;
    private Double importeItemFacturado;
    private Double totalItemFacturado;

    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    
    public ItemFacturadoFacturaProductor(int codigoItemFacturado, int codigoFactura, String descripcionItemFacturado, Double cantidadItemFacturado, Double importeItemFacturado, Double totalItemFacturado) {
        this.codigoItemFacturado = codigoItemFacturado;
        this.codigoFactura = codigoFactura;
        this.descripcionItemFacturado = descripcionItemFacturado;
        this.cantidadItemFacturado = cantidadItemFacturado;
        this.importeItemFacturado = importeItemFacturado;
        this.totalItemFacturado = totalItemFacturado;
    }

    public ItemFacturadoFacturaProductor() {
        
    }

    public int getCodigoItemFacturado() {
        return codigoItemFacturado;
    }

    public void setCodigoItemFacturado(int codigoItemFacturado) {
        this.codigoItemFacturado = codigoItemFacturado;
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public String getDescripcionItemFacturado() {
        return descripcionItemFacturado;
    }

    public void setDescripcionItemFacturado(String descripcionItemFacturado) {
        this.descripcionItemFacturado = descripcionItemFacturado;
    }

    public double getCantidadItemFacturado() {
        return cantidadItemFacturado;
    }

    public void setCantidadItemFacturado(double cantidadItemFacturado) {
        this.cantidadItemFacturado = cantidadItemFacturado;
    }

    public Double getImporteItemFacturado() {
        return importeItemFacturado;
    }

    public void setImporteItemFacturado(Double importeItemFacturado) {
        this.importeItemFacturado = importeItemFacturado;
    }

    public Double getTotalItemFacturado() {
        return totalItemFacturado;
    }

    public void setTotalItemFacturado(Double totalItemFacturado) {
        this.totalItemFacturado = totalItemFacturado;
    }
    
    public int mostrarIdItemFacturado(int codigoFactura) {

        int codigoItemFacturado = 0;
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_item_facturado FROM items_facturados_factura_productor where codigo_factura='"+codigoFactura+"'");
            
            while(rs.next()){
                
                codigoItemFacturado = rs.getInt("codigo_item_facturado");
                
            }
            
        } catch (Exception e) {
            
            return codigoItemFacturado;
            
        }
        
        return codigoItemFacturado;
        
    }

    public boolean facturarItem(ItemFacturadoFacturaProductor itemFacturado){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO items_facturados_factura_productor (codigo_item_facturado, codigo_factura, descripcion_item_facturado, cantidad_item_facturado, importe_item_facturado, total_item_facturado) "
                    + "VALUES (?,?,?,?,?,?)");
            
            pst.setInt(1, itemFacturado.getCodigoItemFacturado());
            pst.setInt(2, itemFacturado.getCodigoFactura());
            pst.setString(3, itemFacturado.getDescripcionItemFacturado());
            pst.setDouble(4, itemFacturado.getCantidadItemFacturado());
            pst.setDouble(5, itemFacturado.getImporteItemFacturado());
            pst.setDouble(6, itemFacturado.getTotalItemFacturado());
            
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
