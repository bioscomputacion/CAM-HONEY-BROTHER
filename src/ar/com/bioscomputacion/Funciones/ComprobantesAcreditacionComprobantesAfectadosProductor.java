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
public class ComprobantesAcreditacionComprobantesAfectadosProductor {
    
    private int codigo_productor;
    private String tipo_comprobante_acreditacion;
    private int codigo_comprobante_acreditacion;
    private String tipo_comprobante_afectado_credito;
    private int codigo_comprobante_afectado_credito;
    private Double importe_acreditado;
    private String estado_acreditacion;

    public ComprobantesAcreditacionComprobantesAfectadosProductor(int codigo_productor, String tipo_comprobante_acreditacion, int codigo_comprobante_acreditacion, String tipo_comprobante_afectado_credito, int codigo_comprobante_afectado_credito, Double importe_acreditado, String estado_acreditacion) {
        
        this.codigo_productor = codigo_productor;
        this.tipo_comprobante_acreditacion = tipo_comprobante_acreditacion;
        this.codigo_comprobante_acreditacion = codigo_comprobante_acreditacion;
        this.tipo_comprobante_afectado_credito = tipo_comprobante_afectado_credito;
        this.codigo_comprobante_afectado_credito = codigo_comprobante_afectado_credito;
        this.importe_acreditado = importe_acreditado;
        this.estado_acreditacion = estado_acreditacion;
        
    }

    public ComprobantesAcreditacionComprobantesAfectadosProductor() {
    }

    public int getCodigo_productor() {
        return codigo_productor;
    }

    public void setCodigo_productor(int codigo_productor) {
        this.codigo_productor = codigo_productor;
    }

    public String getTipo_comprobante_acreditacion() {
        return tipo_comprobante_acreditacion;
    }

    public void setTipo_comprobante_acreditacion(String tipo_comprobante_acreditacion) {
        this.tipo_comprobante_acreditacion = tipo_comprobante_acreditacion;
    }

    public int getCodigo_comprobante_acreditacion() {
        return codigo_comprobante_acreditacion;
    }

    public void setCodigo_comprobante_acreditacion(int codigo_comprobante_acreditacion) {
        this.codigo_comprobante_acreditacion = codigo_comprobante_acreditacion;
    }

    public String getTipo_comprobante_afectado_credito() {
        return tipo_comprobante_afectado_credito;
    }

    public void setTipo_comprobante_afectado_credito(String tipo_comprobante_afectado_credito) {
        this.tipo_comprobante_afectado_credito = tipo_comprobante_afectado_credito;
    }

    public int getCodigo_comprobante_afectado_credito() {
        return codigo_comprobante_afectado_credito;
    }

    public void setCodigo_comprobante_afectado_credito(int codigo_comprobante_afectado_credito) {
        this.codigo_comprobante_afectado_credito = codigo_comprobante_afectado_credito;
    }

    public Double getImporte_acreditado() {
        return importe_acreditado;
    }

    public void setImporte_acreditado(Double importe_acreditado) {
        this.importe_acreditado = importe_acreditado;
    }

    public String getEstado_acreditacion() {
        return estado_acreditacion;
    }

    public void setEstado_acreditacion(String estado_acreditacion) {
        this.estado_acreditacion = estado_acreditacion;
    }
    
    public boolean registrarRelacionCreditoComprobanteAfectado(ComprobantesAcreditacionComprobantesAfectadosProductor relacion){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO comprobantes_acreditacion_comprobantes_afectados_productor (codigo_productor, tipo_comprobante_acreditacion, codigo_comprobante_acreditacion, tipo_comprobante_afectado_credito, codigo_comprobante_afectado_credito, importe_acreditado, estado_acreditacion) "
                    + "VALUES (?,?,?,?,?,?,?)");
            
            
            pst.setInt(1, relacion.getCodigo_productor());
            pst.setString(2, relacion.getTipo_comprobante_acreditacion());
            pst.setInt(3, relacion.getCodigo_comprobante_acreditacion());
            pst.setString(4, relacion.getTipo_comprobante_afectado_credito());
            pst.setInt(5, relacion.getCodigo_comprobante_afectado_credito());
            pst.setDouble(6, relacion.getImporte_acreditado());
            pst.setString(7, relacion.getEstado_acreditacion());
            
            
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
    
    public boolean actualizarEstadoRelacionComprobanteAcreditacionComprobanteAfectado(String tipoComprobante, int codigoComprobante) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            PreparedStatement pst = cn.prepareStatement("UPDATE comprobantes_acreditacion_comprobantes_afectados_productor SET estado_acreditacion = ? WHERE tipo_comprobante_acreditacion = '"+ tipoComprobante +"' and codigo_comprobante_acreditacion = '"+ codigoComprobante +"'");

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
    
}
