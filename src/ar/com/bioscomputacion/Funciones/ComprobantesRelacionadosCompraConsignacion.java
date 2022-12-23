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
public class ComprobantesRelacionadosCompraConsignacion {
    
    private int codigoProductor;
    private int codigoCompraConsignacion;
    private int codigoComprobanteRelacionado;
    private String tipoComprobanteRelacionado;
    private Double cantidadMielAfectada;
    private String estado_comprobante_facturacion;

    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();

    public ComprobantesRelacionadosCompraConsignacion(int codigoProductor, int codigoCompra, int codigo_comprobante_relacionado, String tipo_comprobante_relacionado, Double cantidadMielAfectada, String estado_comprobante_facturacion) {

        this.codigoProductor = codigoProductor;
        this.codigoCompraConsignacion = codigoCompra;
        this.codigoComprobanteRelacionado = codigo_comprobante_relacionado;
        this.tipoComprobanteRelacionado = tipo_comprobante_relacionado;
        this.cantidadMielAfectada = cantidadMielAfectada;
        this.estado_comprobante_facturacion = estado_comprobante_facturacion;
        
    }

    public ComprobantesRelacionadosCompraConsignacion() {
    }

    public int getCodigoProductor() {
        return codigoProductor;
    }

    public void setCodigoProductor(int codigoProductor) {
        this.codigoProductor = codigoProductor;
    }
    
    public int getCodigoCompra() {
        return codigoCompraConsignacion;
    }

    public void setCodigoCompra(int codigoCompra) {
        this.codigoCompraConsignacion = codigoCompra;
    }

    public int getCodigo_comprobante_relacionado() {
        return codigoComprobanteRelacionado;
    }

    public void setCodigo_comprobante_relacionado(int codigo_comprobante_relacionado) {
        this.codigoComprobanteRelacionado = codigo_comprobante_relacionado;
    }

    public String getTipo_comprobante_relacionado() {
        return tipoComprobanteRelacionado;
    }

    public void setTipo_comprobante_relacionado(String tipo_comprobante_relacionado) {
        this.tipoComprobanteRelacionado = tipo_comprobante_relacionado;
    }

    public Double getCantidadMielAfectada() {
        return cantidadMielAfectada;
    }

    public void setCantidadMielAfectada(Double cantidadMielAfectada) {
        this.cantidadMielAfectada = cantidadMielAfectada;
    }

    public int getCodigoCompraConsignacion() {
        return codigoCompraConsignacion;
    }

    public void setCodigoCompraConsignacion(int codigoCompraConsignacion) {
        this.codigoCompraConsignacion = codigoCompraConsignacion;
    }

    public int getCodigoComprobanteRelacionado() {
        return codigoComprobanteRelacionado;
    }

    public void setCodigoComprobanteRelacionado(int codigoComprobanteRelacionado) {
        this.codigoComprobanteRelacionado = codigoComprobanteRelacionado;
    }

    public String getTipoComprobanteRelacionado() {
        return tipoComprobanteRelacionado;
    }

    public void setTipoComprobanteRelacionado(String tipoComprobanteRelacionado) {
        this.tipoComprobanteRelacionado = tipoComprobanteRelacionado;
    }

    public String getEstado_comprobante_facturacion() {
        return estado_comprobante_facturacion;
    }

    public void setEstado_comprobante_facturacion(String estado_comprobante_facturacion) {
        this.estado_comprobante_facturacion = estado_comprobante_facturacion;
    }
    
    public boolean relacionarComprobanteACompraConsignacion(ComprobantesRelacionadosCompraConsignacion comprobanteRelacionado){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO comprobantes_relacionados_compra_credito (codigo_productor, codigo_compra_consignacion, codigo_comprobante_relacionado, tipo_comprobante_relacionado, cantidad_miel_afectada, estado_comprobante_facturacion) "
                    + "VALUES (?,?,?,?,?,?)");
            
            
            pst.setInt(1, comprobanteRelacionado.getCodigoProductor());
            pst.setInt(2, comprobanteRelacionado.getCodigoCompra());
            pst.setInt(3, comprobanteRelacionado.getCodigo_comprobante_relacionado());
            pst.setString(4, comprobanteRelacionado.getTipo_comprobante_relacionado());
            pst.setDouble(5, comprobanteRelacionado.getCantidadMielAfectada());
            //si el estado es ANULADO este registro no resta miel a la consignacion involucrada
            pst.setString(6, comprobanteRelacionado.getEstado_comprobante_facturacion());
            
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

    public boolean actualizarEstadoRelacionConsignacionComprobanteFacturacion(String tipoComprobante, int codigoComprobante) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            PreparedStatement pst = cn.prepareStatement("UPDATE comprobantes_relacionados_compra_credito SET estado_comprobante_facturacion = ? WHERE tipo_comprobante_relacionado = '"+ tipoComprobante +"' and codigo_comprobante_relacionado = '"+ codigoComprobante +"'");

            pst.setString(1, "ANULADO");
            
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
            
        }

        return false;
        
    }
    
    public int mostrarCodigoConsignacionAfectadaPorFactura(int codigoFactura) {
        
        int codigoComprobante = 0;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select codigo_compra_consignacion from comprobantes_relacionados_compra_credito where tipo_comprobante_relacionado in ('FACTURA A', 'FACTURA C') and codigo_comprobante_relacionado = '"+ codigoFactura +"'");

            while (rs.next()){
            
                codigoComprobante = rs.getInt("codigo_compra_consignacion");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return codigoComprobante;
            
        }
        
        return codigoComprobante;
    
    }
    
    public int mostrarCodigoConsignacionAfectadaPorPresupuesto(int codigoPresupuesto) {
        
        int codigoComprobante = 0;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select codigo_compra_consignacion from comprobantes_relacionados_compra_credito where tipo_comprobante_relacionado = 'PRESUPUESTO' and codigo_comprobante_relacionado = '"+ codigoPresupuesto +"'");

            while (rs.next()){
            
                codigoComprobante = rs.getInt("codigo_compra_consignacion");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return codigoComprobante;
            
        }
        
        return codigoComprobante;
    
    }
    
}
