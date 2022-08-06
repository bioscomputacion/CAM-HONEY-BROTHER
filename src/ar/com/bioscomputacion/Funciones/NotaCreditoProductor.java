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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Caco
 */
public class NotaCreditoProductor {
    
    private int codigo_nota_credito;
    private String tipo_nota_Credito;
    private String numero_comprobante; 
    private int codigo_movimiento_ctacte;
    private int codigo_productor;
    private Date fecha_nota_credito;
    private Double importe_total_nota_credito;
    private Double cantidad_miel_devuelta;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    

    public NotaCreditoProductor(String numero_comprobante, String tipo_nota_credito, int codigo_movimiento_ctacte, int codigo_productor, Date fecha_nota_credito, Double importe_total_nota_credito, Double cantidad_miel_devuelta) {
        
        this.numero_comprobante = numero_comprobante;
        this.tipo_nota_Credito = tipo_nota_credito;
        this.codigo_movimiento_ctacte = codigo_movimiento_ctacte;
        this.codigo_productor = codigo_productor;
        this.fecha_nota_credito = fecha_nota_credito;
        this.importe_total_nota_credito = importe_total_nota_credito;
        this.cantidad_miel_devuelta = cantidad_miel_devuelta;
        
    }

    public NotaCreditoProductor() {
    }

    public String getTipoNotaCredito() {
        return tipo_nota_Credito;
    }

    public void tipo_nota_Credito(String tipoNotaCredito) {
        this.tipo_nota_Credito = tipoNotaCredito;
    }

    public int getCodigo_nota_credito() {
        return codigo_nota_credito;
    }

    public void setCodigo_nota_credito(int codigoNotaCredito) {
        this.codigo_nota_credito = codigoNotaCredito;
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

    public Date getFecha_nota_credito() {
        return fecha_nota_credito;
    }

    public void setFecha_nota_credito(Date fecha_nota_credito) {
        this.fecha_nota_credito = fecha_nota_credito;
    }

    public Double getImporte_total_nota_credito(){
        return importe_total_nota_credito;
    }

    public void setImporte_total_nota_credito(Double importe_total_nota_credito) {
        this.importe_total_nota_credito = importe_total_nota_credito;
    }

    public Double getCantidad_miel() {
        return cantidad_miel_devuelta;
    }

    public void setCantidad_miel(Double cantidad_miel) {
        this.cantidad_miel_devuelta = cantidad_miel;
    }

    public int mostrarIdNotaCreditoProductor() {

        int codigoNoptaCreditoProductor = 0;
        
        try{
 
            //esta no cierra
            /*ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();*/
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_nota_credito FROM nota_credito_productor order by codigo_nota_credito asc");
            
            while (rs.next()) {

                codigoNoptaCreditoProductor = rs.getInt("codigo_nota_credito");
                
            }
            
            return codigoNoptaCreditoProductor;

            /*ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);*/

        }catch(Exception e){
            
            return codigoNoptaCreditoProductor;
        } 

    }

    public boolean registrarNotaCreditoProductor(NotaCreditoProductor notaCreditoProductor){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO nota_credito_productor (tipo_nota_credito, numero_comprobante, codigo_movimiento_cta_cte, codigo_productor, fecha_nota_credito, importe_total_nota_credito, cantidad_miel_afectada) "
                    + "VALUES (?,?,?,?,?,?,?)");
            
            
            pst.setString(1, notaCreditoProductor.getTipoNotaCredito());
            pst.setString(2, notaCreditoProductor.getNumero_comprobante());
            pst.setInt(3, notaCreditoProductor.getCodigo_movimiento_ctacte());
            pst.setInt(4, notaCreditoProductor.getCodigo_productor());
            pst.setDate(5, notaCreditoProductor.getFecha_nota_credito());
            pst.setDouble(6, notaCreditoProductor.getImporte_total_nota_credito());
            pst.setDouble(7, notaCreditoProductor.getCantidad_miel());
            
            
            int N = pst.executeUpdate();

            if (N != 0) {
                
                return true;
                
            } else {
                
                return false;
                
            }
            
            //ConexionBD.close(cn);
            //ConexionBD.close(pst);
            
        } catch (Exception e) {
            
        }
        
        return false;
    }

    public boolean modificarFacturaProductor(NotaCreditoProductor facturaProductor, int codigoFactura) {

        /*try {

            PreparedStatement pst = cn.prepareStatement("UPDATE factura_productor SET tipo_factura = ?,numero_comprobante = ?,codigo_movimiento_ctacte = ?,codigo_productor = ?,fecha_factura = ?,fecha_vencimiento = ?,importe_total_factura = ?,cantidad_miel_facturada = ? WHERE codigo_factura = '"+ codigoFactura +"'");

            pst.setString(1, facturaProductor.getNumero_comprobante());
            pst.setString(2, facturaProductor.getTipoFactura());
            pst.setInt(3, facturaProductor.getCodigo_movimiento_ctacte());
            pst.setInt(4, facturaProductor.getCodigo_productor());
            pst.setDate(5, facturaProductor.getFecha_factura());
            pst.setDouble(7, facturaProductor.getImporte_total_factura());
            pst.setDouble(8, facturaProductor.getCantidad_miel());

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

        return false;*/
        return false;
    }

    public boolean eliminarFacturaProductor(int codigoFactura) {

        try {

            PreparedStatement pst = cn.prepareStatement("DELETE FROM factura_productor WHERE codigo_factura = '"+ codigoFactura +"'");

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