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
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author carlo
 */
public class FacturaCliente {
    
    private int codigo_factura;
    private String tipo_factura;
    private String numero_comprobante; 
    private int codigo_movimiento_ctacte;
    private int codigo_cliente;
    private Date fecha_factura;
    private Date fecha_vencimiento;
    private Double importe_total_factura;
    private Double cantidad_miel;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();

    public FacturaCliente(String tipo_factura, String numero_comprobante, int codigo_movimiento_ctacte, int codigo_cliente, Date fecha_factura, Date fecha_vencimiento, Double importe_total_factura, Double cantidad_miel) {
        
        this.codigo_factura = codigo_factura;
        this.tipo_factura = tipo_factura;
        this.numero_comprobante = numero_comprobante;
        this.codigo_movimiento_ctacte = codigo_movimiento_ctacte;
        this.codigo_cliente = codigo_cliente;
        this.fecha_factura = fecha_factura;
        this.fecha_vencimiento = fecha_vencimiento;
        this.importe_total_factura = importe_total_factura;
        this.cantidad_miel = cantidad_miel;
        
    }

    public FacturaCliente() {
    }

    public int getCodigo_factura() {
        return codigo_factura;
    }

    public void setCodigo_factura(int codigo_factura) {
        this.codigo_factura = codigo_factura;
    }

    public String getTipo_factura() {
        return tipo_factura;
    }

    public void setTipo_factura(String tipo_factura) {
        this.tipo_factura = tipo_factura;
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

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
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

    public Double getCantidad_miel() {
        return cantidad_miel;
    }

    public void setCantidad_miel(Double cantidad_miel) {
        this.cantidad_miel = cantidad_miel;
    }

    public int mostrarIdFacturaCliente() {

        int codigoFacturaCliente = 0;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_factura FROM factura_cliente order by codigo_factura asc");
            
            while (rs.next()) {

                codigoFacturaCliente = rs.getInt("codigo_factura");
                
            }
            
            return codigoFacturaCliente;

        }
        catch(Exception e){
            
            return codigoFacturaCliente;
            
        } 

    }

    public int mostrarIdItemAFacturar(int codigoFactura) {

        int codigoItemAFacturar = 0;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_item_facturado FROM items_facturados_factura_cliente where codigo_factura='"+ codigoFactura +"'");
            
            while (rs.next()) {

                codigoItemAFacturar = rs.getInt("codigo_item_facturado");
                
            }
            
            return codigoItemAFacturar;

        }
        catch(Exception e){
            
            return codigoItemAFacturar;
            
        } 

    }

    public boolean registrarFacturaCliente(FacturaCliente facturaCliente){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO factura_cliente (tipo_factura, numero_comprobante, codigo_movimiento_ctacte, codigo_cliente, fecha_factura, fecha_vencimiento, importe_total_factura, cantidad_miel) "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            
            
            pst.setString(1, facturaCliente.getTipo_factura());
            pst.setString(2, facturaCliente.getNumero_comprobante());
            pst.setInt(3, facturaCliente.getCodigo_movimiento_ctacte());
            pst.setInt(4, facturaCliente.getCodigo_cliente());
            pst.setDate(5, facturaCliente.getFecha_factura());
            pst.setDate(6, facturaCliente.getFecha_vencimiento());
            pst.setDouble(7, facturaCliente.getImporte_total_factura());
            pst.setDouble(8, facturaCliente.getCantidad_miel());
            
            
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

    public boolean modificarFacturaCliente(FacturaCliente facturaCliente, int codigoFactura) {

        try {

            PreparedStatement pst = cn.prepareStatement("UPDATE factura_cliente SET tipo_factura = ?,numero_comprobante = ?,codigo_movimiento_ctacte = ?,codigo_cliente = ?,fecha_factura = ?,fecha_vencimiento = ?,importe_total_factura = ?,cantidad_miel = ? WHERE codigo_factura = '"+ codigoFactura +"'");

            pst.setString(1, facturaCliente.getTipo_factura());
            pst.setString(2, facturaCliente.getNumero_comprobante());
            pst.setInt(3, facturaCliente.getCodigo_movimiento_ctacte());
            pst.setInt(4, facturaCliente.getCodigo_cliente());
            pst.setDate(5, facturaCliente.getFecha_factura());
            pst.setDate(6, facturaCliente.getFecha_vencimiento());
            pst.setDouble(7, facturaCliente.getImporte_total_factura());
            pst.setDouble(8, facturaCliente.getCantidad_miel());

            int N = pst.executeUpdate();

            if (N != 0) {
                
                ConexionBD.close(cn);
                ConexionBD.close(pst);
                return true;
                
            } else {
                
                ConexionBD.close(cn);
                ConexionBD.close(pst);
                return false;
                
            }
            
        } catch (SQLException ex) {
            
            ex.printStackTrace(System.out);
            
        }

        return false;
    }

    public boolean eliminarFacturaCliente(int codigoFactura) {

        try {

            PreparedStatement pst = cn.prepareStatement("DELETE FROM factura_cliente WHERE codigo_factura = '"+ codigoFactura +"'");

            int N = pst.executeUpdate();

            if (N != 0) {
                
                ConexionBD.close(cn);
                ConexionBD.close(pst);
                return true;
                
            } else {
                
                ConexionBD.close(cn);
                ConexionBD.close(pst);
                return false;
                
            }
            
        } catch (SQLException ex) {
            
            ex.printStackTrace(System.out);
            
        }

        return false;
    }

    
}
