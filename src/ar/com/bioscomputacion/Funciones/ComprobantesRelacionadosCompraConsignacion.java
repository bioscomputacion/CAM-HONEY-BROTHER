/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author carlo
 */
public class ComprobantesRelacionadosCompraConsignacion {
    
    private int codigoProductor;
    private int codigoCompra;
    private int codigoComprobanteRelacionado;
    private String tipoComprobanteRelacionado;
    private Double cantidadMielAfectada ;

    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();

    public ComprobantesRelacionadosCompraConsignacion(int codigoProductor, int codigoCompra, int codigo_comprobante_relacionado, String tipo_comprobante_relacionado, Double cantidadMielAfectada) {

        this.codigoProductor = codigoProductor;
        this.codigoCompra = codigoCompra;
        this.codigoComprobanteRelacionado = codigo_comprobante_relacionado;
        this.tipoComprobanteRelacionado = tipo_comprobante_relacionado;
        this.cantidadMielAfectada = cantidadMielAfectada;
        
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
        return codigoCompra;
    }

    public void setCodigoCompra(int codigoCompra) {
        this.codigoCompra = codigoCompra;
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

    public boolean relacionarComprobanteACompraConsignacion(ComprobantesRelacionadosCompraConsignacion comprobanteRelacionado){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO comprobantes_relacionados_compra_credito (codigo_productor, codigo_compra_consignacion, codigo_comprobante_relacionado, tipo_comprobante_relacionado, cantidad_miel_afectada) "
                    + "VALUES (?,?,?,?,?)");
            
            
            pst.setInt(1, comprobanteRelacionado.getCodigoProductor());
            pst.setInt(2, comprobanteRelacionado.getCodigoCompra());
            pst.setInt(3, comprobanteRelacionado.getCodigo_comprobante_relacionado());
            pst.setString(4, comprobanteRelacionado.getTipo_comprobante_relacionado());
            pst.setDouble(5, comprobanteRelacionado.getCantidadMielAfectada());
            
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
