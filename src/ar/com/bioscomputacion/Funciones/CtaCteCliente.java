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
 * @author carlo
 */
public class CtaCteCliente {
    
    private int codigoCliente;
    private int codigoMovimiento;
    private Date fechaMovimiento;
    private String descripcionMovimiento; 
    private int comprobanteAsociado;
    private String numeroComprobante;
    private Double cantidadMiel;
    private Double debe;
    private Double haber;
    private Double saldo;
    private String estadoMovimiento;
    private String observacion;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();
    

    public CtaCteCliente(int codigoCliente, int codigoMovimiento, Date fechaMovimiento, String descripcionMovimiento, int comprobanteAsociado, String numeroComprobante, Double cantidadMiel, Double debe, Double haber, Double saldo, String estadoMovimiento, String observacion) {
        
        this.codigoCliente = codigoCliente;
        this.codigoMovimiento = codigoMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.descripcionMovimiento = descripcionMovimiento;
        this.comprobanteAsociado = comprobanteAsociado;
        this.numeroComprobante = numeroComprobante;
        this.cantidadMiel = cantidadMiel;
        this.debe = debe;
        this.haber = haber;
        this.saldo = saldo;
        this.estadoMovimiento = estadoMovimiento;
        this.observacion = observacion;
        
    }

    public CtaCteCliente() {
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
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

    public Double getCantidadMiel() {
        return cantidadMiel;
    }

    public void setCantidadMiel(Double cantidadMiel) {
        this.cantidadMiel = cantidadMiel;
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
    
    public int mostrarIdMovimiento(int codigoCliente) {

        int codigoMovimiento = 0;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_movimiento FROM cta_cte_cliente where codigo_cliente='"+ codigoCliente+ "'");
            
            while (rs.next()) {

                codigoMovimiento = rs.getInt("codigo_movimiento");
                
            }
            
            return codigoMovimiento;

        }catch(Exception e){
            
            return codigoMovimiento;
        } 

    }

    public boolean registrarMovimientoCtaCteCliente(CtaCteCliente ctacteCliente){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO cta_cte_cliente (codigo_cliente, codigo_movimiento, fecha_movimiento, descripcion_movimiento, comprobante_asociado, numero_comprobante, cantidad_miel, debe, haber, saldo, estado_movimiento, observacion) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            
            
            pst.setInt(1, ctacteCliente.getCodigoCliente());
            pst.setInt(2, ctacteCliente.getCodigoMovimiento());
            pst.setDate(3, ctacteCliente.getFechaMovimiento());
            pst.setString(4, ctacteCliente.getDescripcionMovimiento());
            pst.setInt(5, ctacteCliente.getComprobanteAsociado());
            pst.setString(6, ctacteCliente.getNumeroComprobante());
            pst.setDouble(7, ctacteCliente.getCantidadMiel());
            pst.setDouble(8, ctacteCliente.getDebe());
            pst.setDouble(9, ctacteCliente.getHaber());
            pst.setDouble(10, ctacteCliente.getSaldo());
            pst.setString(11, ctacteCliente.getEstadoMovimiento());
            pst.setString(12, ctacteCliente.getObservacion());
            
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
    

    public DefaultTableModel listarMovimientosCtaCteCliente(int codigoCliente){
        
        DefaultTableModel modelo;
        
        String[] titulos = {"ID CLIENTE", "ID MOVIMIENTO", "FECHA", "REFERENCIA", "COMPROBANTE ASOCIADO", "N° COMPROB.", "KGS. MIEL", "IMPORTE", "PAGADO", "SALDO", "ESTADO MOVIMIENTO", "OBSERVACION"};

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
            ResultSet rs = st.executeQuery("SELECT * FROM cta_cte_cliente WHERE codigo_cliente='" + codigoCliente + "'");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_cliente");
                registros[1] = rs.getString("codigo_movimiento");
                registros[2] = rs.getString("fecha_movimiento");
                registros[3] = rs.getString("descripcion_movimiento");
                registros[4] = rs.getString("comprobante_asociado");
                registros[5] = rs.getString("numero_comprobante");
                registros[6] = rs.getString("cantidad_miel");
                registros[7] = rs.getString("debe");
                registros[8] = rs.getString("haber");
                registros[9] = rs.getString("saldo");
                registros[10] = rs.getString("estado_movimiento");
                registros[11] = rs.getString("observacion");

                modelo.addRow(registros);
                
            }
            
            //ConexionBD.close(cn);
            //ConexionBD.close(st);
            //ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }
    
    public Double mostrarSaldoCtaCteCliente(int codigoCliente) {

        Double saldo = 0.00;
        
        try {

            Statement st = cn.createStatement();
            Statement st2 = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT saldo FROM cta_cte_cliente WHERE codigo_cliente='"+ codigoCliente +"'");

            while (rs.next()) {
                
                saldo = saldo + rs.getDouble("saldo");
                
            }
            
            return saldo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

        return saldo;
        
    }
    
    public Double mostrarSaldoMielImpagaACliente(int codigoCliente) {

        Double saldo = 0.00;
        String comprobante = "CREDITO";
        String estadoComprobante = "PENDIENTE";
        
        try {

            Statement st = cn.createStatement();
            Statement st2 = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM cta_cte_cliente WHERE codigo_cliente='" +codigoCliente+ "' and descripcion_movimiento='" +comprobante+ "' and estado_movimiento='"+estadoComprobante+"'");

            while (rs.next()) {
                
                saldo = saldo + rs.getDouble("cantidad_miel");
                
            }
            
            return saldo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

        return saldo;
        
    }

    /*public boolean pagarComprobantes(int cliente, int movimiento, Double pago){
        
        sSQL = "UPDATE ctas_ctes_clientes SET saldo = ? , haber = ? , estado_movimiento = ?   WHERE codigo_cliente =? AND codigo_movimiento =? ";

        try {

            PreparedStatement pst = cn.prepareStatement(sSQL);

            
            pst.setDouble(1, mostrarSaldoActualDeUnaFactura(cliente, movimiento) - pago);
            pst.setDouble(2, mostrarPagoActualDeUnaFactura(cliente, movimiento) + pago);
            if(mostrarSaldoActualDeUnaFactura(cliente, movimiento) - pago == 0.00)
            {
                pst.setString(3, "CANCELADA");
            }
            else
            {
                pst.setString(3, "PENDIENTE");
            }
            pst.setInt(4, cliente);
            pst.setInt(5, movimiento);

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
        
    }*/
    
    public boolean actualizarSaldoComprobanteCliente(int movimiento, int cliente, double debeComprobante, double pagoRealizado, double montoPagado) {

        try {


            PreparedStatement pst = cn.prepareStatement("UPDATE cta_cte_cliente SET haber = ?, saldo = ?, estado_movimiento = ? WHERE codigo_cliente = '"+ cliente +"' and codigo_movimiento = '"+ movimiento +"'");

            //tengo que sumar el valor del pago realizado al valor que ya se encuentra en el campo haber
            //y tengo que guardar en el campo saldo la resta entre el campo debe y el nuevo valor del campo haber
            
            double haberActualizado = pagoRealizado + montoPagado;
            double saldoActualizado = debeComprobante - haberActualizado;
            
            pst.setDouble(1, haberActualizado);
            pst.setDouble(2, debeComprobante - haberActualizado);
            
            //System.out.println(debe);
            //System.out.println(pago);
            //System.out.println(pago);
            
            if (saldoActualizado == 0){
                
                //
                pst.setString(3, "CANCELADO");
                
            }
            else{
                
                //
                pst.setString(3, "PENDIENTE");
                
            }

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
