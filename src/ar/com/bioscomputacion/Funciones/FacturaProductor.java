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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Caco
 */
public class FacturaProductor {
    
    private int codigo_factura;
    private String numero_comprobante; 
    private int codigo_movimiento_ctacte;
    private int codigo_productor;
    private Date fecha_factura;
    private Date fecha_vencimiento;
    private Double importe_total_factura;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    

    public FacturaProductor(int codigo_factura, String numero_comprobante, int codigo_movimiento_ctacte, int codigo_productor, Date fecha_factura, Date fecha_vencimiento,Double importe_total_factura) {
        this.codigo_factura = codigo_factura;
        this.numero_comprobante = numero_comprobante;
        this.codigo_movimiento_ctacte = codigo_movimiento_ctacte;
        this.codigo_productor = codigo_productor;
        this.fecha_factura = fecha_factura;
        this.fecha_vencimiento = fecha_vencimiento;
        this.importe_total_factura = importe_total_factura;
    }

    public FacturaProductor() {
    }

    public int getCodigo_factura() {
        return codigo_factura;
    }

    public void setCodigo_factura(int codigo_factura) {
        this.codigo_factura = codigo_factura;
    }

    public String getNumero_comprobante() {
        return numero_comprobante;
    }

    public void setNumero_comprobante(String numero_comprobante) {
        this.numero_comprobante = numero_comprobante;
    }

    public int getCodigo_movimiento_ctacte() {
        return codigo_movimiento_ctacte;
    }

    public void setCodigo_movimiento_ctacte(int codigo_movimiento_ctacte) {
        this.codigo_movimiento_ctacte = codigo_movimiento_ctacte;
    }

    public int getCodigo_productor() {
        return codigo_productor;
    }

    public void setCodigo_productor(int codigo_productor) {
        this.codigo_productor = codigo_productor;
    }

    public Date getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(Date fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Double getImporte_total_factura() {
        return importe_total_factura;
    }

    public void setImporte_total_factura(Double importe_total_factura) {
        this.importe_total_factura = importe_total_factura;
    }

    public int mostrarIdFacturaProductor() {

        int codigoFacturaProductor = 0;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_factura FROM factura_productor");
            
            while (rs.next()) {

                codigoFacturaProductor = rs.getInt("codigo_factura");
            }
            
            return codigoFacturaProductor;

        }catch(Exception e){
            
            return codigoFacturaProductor;
        } 

    }

    public int mostrarIdItemAFacturar(int codigoFactura) {

        int codigoItemAFacturar = 0;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_item_facturado FROM items_facturados_factura_productor where codigo_factura='"+codigoFactura+"'");
            
            while (rs.next()) {

                codigoItemAFacturar = rs.getInt("codigo_item_facturado");
            }
            
            return codigoItemAFacturar;

        }catch(Exception e){
            
            return codigoItemAFacturar;
        } 

    }

    public boolean registrarFacturaProductor(FacturaProductor facturaProductor){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO factura_productor (numero_comprobante, codigo_movimiento_ctacte, codigo_productor, fecha_factura, fecha_vencimiento, importe_total_factura) "
                    + "VALUES (?,?,?,?,?,?)");
            
            
            pst.setString(1, facturaProductor.getNumero_comprobante());
            pst.setInt(2, facturaProductor.getCodigo_movimiento_ctacte());
            pst.setInt(3, facturaProductor.getCodigo_productor());
            pst.setDate(4, facturaProductor.getFecha_factura());
            pst.setDate(5, facturaProductor.getFecha_vencimiento());
            pst.setDouble(6, facturaProductor.getImporte_total_factura());
            
            
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
