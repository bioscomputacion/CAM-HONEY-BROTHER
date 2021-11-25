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
public class CtaCteProductor {
    
    private int codigoProductor;
    private int codigoMovimiento;
    private Date fechaMovimiento;
    private String descripcionMovimiento; 
    private int comprobanteAsociado;
    private String numeroComprobante;
    private Double debe;
    private Double haber;
    private Double saldo;
    private String estadoMovimiento;
    private String observacion;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();


    public CtaCteProductor(int codigoProductor, int codigoMovimiento, Date fechaMovimiento, String descripcionMovimiento, int comprobanteAsociado, String numeroComprobante, Double debe, Double haber, Double saldo, String estadoMovimiento, String observacion) {
        this.codigoProductor = codigoProductor;
        this.codigoMovimiento = codigoMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.descripcionMovimiento = descripcionMovimiento;
        this.comprobanteAsociado = comprobanteAsociado;
        this.numeroComprobante = numeroComprobante;
        this.debe = debe;
        this.haber = haber;
        this.saldo = saldo;
        this.estadoMovimiento = estadoMovimiento;
        this.observacion = observacion;
    }

    public CtaCteProductor() {
    }

    public int getCodigoProductor() {
        return codigoProductor;
    }

    public void setCodigoProductor(int codigoProductor) {
        this.codigoProductor = codigoProductor;
    }

    public int getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(int codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getDescripcionMovimiento() {
        return descripcionMovimiento;
    }

    public void setDescripcionMovimiento(String descripcionMovimiento) {
        this.descripcionMovimiento = descripcionMovimiento;
    }

    public int getComprobanteAsociado() {
        return comprobanteAsociado;
    }

    public void setComprobanteAsociado(int comprobanteAsociado) {
        this.comprobanteAsociado = comprobanteAsociado;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Double getDebe() {
        return debe;
    }

    public void setDebe(Double debe) {
        this.debe = debe;
    }

    public Double getHaber() {
        return haber;
    }

    public void setHaber(Double haber) {
        this.haber = haber;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getEstadoMovimiento() {
        return estadoMovimiento;
    }

    public void setEstadoMovimiento(String estadoMovimiento) {
        this.estadoMovimiento = estadoMovimiento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public ConexionBD getMysql() {
        return mysql;
    }

    public void setMysql(ConexionBD mysql) {
        this.mysql = mysql;
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    public int mostrarIdMovimiento(int codigoProductor) {

        int codigoMovimiento = 0;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_movimiento FROM cta_cte_productor where codigo_productor='"+codigoProductor+"'");
            
            while (rs.next()) {

                codigoMovimiento = rs.getInt("codigo_movimiento");
                
            }
            
            return codigoMovimiento;

        }catch(Exception e){
            
            return codigoMovimiento;
        } 

    }

    public boolean registrarMovimientoCtaCteProductor(CtaCteProductor ctacteProductor){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO cta_cte_productor (codigo_productor, codigo_movimiento, fecha_movimiento, descripcion_movimiento, comprobante_asociado, numero_comprobante, debe, haber, saldo, estado_movimiento, observacion) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            
            
            pst.setInt(1, ctacteProductor.getCodigoProductor());
            pst.setInt(2, ctacteProductor.getCodigoMovimiento());
            pst.setDate(3, ctacteProductor.getFechaMovimiento());
            pst.setString(4, ctacteProductor.getDescripcionMovimiento());
            pst.setInt(5, ctacteProductor.getComprobanteAsociado());
            pst.setString(6, ctacteProductor.getNumeroComprobante());
            pst.setDouble(7, ctacteProductor.getDebe());
            pst.setDouble(8, ctacteProductor.getHaber());
            pst.setDouble(9, ctacteProductor.getSaldo());
            pst.setString(10, ctacteProductor.getEstadoMovimiento());
            pst.setString(11, ctacteProductor.getObservacion());
            
            
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
    

    public DefaultTableModel listarMovimientosCtaCteProductor(int codigoProductor){
        
        DefaultTableModel modelo;
        
        String[] titulos = {"ID PRODUCTOR", "ID MOVIMIENTO", "FECHA", "REFERENCIA", "COMPROBANTE ASOCIADO", "N° COMPROB.", "IMPORTE", "PAGADO", "SALDO", "ESTADO MOVIMIENTO", "OBSERVACION", "PAGO A REALIZAR"};

        String[] registros = new String[12];

        modelo = new DefaultTableModel(null, titulos) {
            
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 11) {
                    return true;
                } else {
                    return false;
                }

            }

        };
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cta_cte_productor WHERE codigo_productor='" + codigoProductor + "'");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_productor");
                registros[1] = rs.getString("codigo_movimiento");
                registros[2] = rs.getString("fecha_movimiento");
                registros[3] = rs.getString("descripcion_movimiento");
                registros[4] = rs.getString("comprobante_asociado");
                registros[5] = rs.getString("numero_comprobante");
                registros[6] = rs.getString("debe");
                registros[7] = rs.getString("haber");
                registros[8] = rs.getString("saldo");
                registros[9] = rs.getString("estado_movimiento");
                registros[10] = rs.getString("observacion");
                registros[11] = "0.00";

                modelo.addRow(registros);
                
            }
            
            //ConexionBD.close(cn);
            //ConexionBD.close(st);
            //ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }
    
    public Double mostrarSaldoCtaCteProductor(int codigoProductor) {

        Double saldo = 0.00;
        
        try {

            Statement st = cn.createStatement();
            Statement st2 = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT saldo FROM cta_cte_productor WHERE codigo_productor='"+codigoProductor+"'");

            while (rs.next()) {
                
                saldo = saldo + rs.getDouble("saldo");
                
            }
            
            return saldo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

        return saldo;
        
    }

}
