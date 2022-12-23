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
public class CtaCteProductor {
    
    private int codigoProductor;
    private int codigoMovimiento;
    private Date fechaMovimiento;
    private String descripcionMovimiento; 
    private int comprobanteAsociado;
    private String numeroComprobante;
    private String comprobanteAfectado;
    private Double cantidadMiel;
    private Double debe;
    private Double haber;
    private Double saldo;
    private String estadoMovimiento;
    private String observacion;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn= mysql.getConexionBD();


    public CtaCteProductor(int codigoProductor, int codigoMovimiento, Date fechaMovimiento, String descripcionMovimiento, int comprobanteAsociado, String numeroComprobante, String comprobanteAfectado, Double cantidadMiel, Double debe, Double haber, Double saldo, String estadoMovimiento, String observacion) {
        
        this.codigoProductor = codigoProductor;
        this.codigoMovimiento = codigoMovimiento;
        this.fechaMovimiento = fechaMovimiento;
        this.descripcionMovimiento = descripcionMovimiento;
        this.comprobanteAsociado = comprobanteAsociado;
        this.numeroComprobante = numeroComprobante;
        this.comprobanteAfectado = comprobanteAfectado;
        this.cantidadMiel = cantidadMiel;
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

    public String getComprobanteAfectado() {
        return comprobanteAfectado;
    }

    public void setComprobanteAfectado(String comprobanteAfectado) {
        this.comprobanteAfectado = comprobanteAfectado;
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

    public Double getCantidadMiel() {
        return cantidadMiel;
    }

    public void setCantidadMiel(Double cantidadMiel) {
        this.cantidadMiel = cantidadMiel;
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
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO cta_cte_productor (codigo_productor, codigo_movimiento, fecha_movimiento, descripcion_movimiento, comprobante_asociado, numero_comprobante, comprobante_afectado, cantidad_miel, debe, haber, saldo, estado_movimiento, observacion) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            
            pst.setInt(1, ctacteProductor.getCodigoProductor());
            pst.setInt(2, ctacteProductor.getCodigoMovimiento());
            pst.setDate(3, ctacteProductor.getFechaMovimiento());
            pst.setString(4, ctacteProductor.getDescripcionMovimiento());
            pst.setInt(5, ctacteProductor.getComprobanteAsociado());
            pst.setString(6, ctacteProductor.getNumeroComprobante());
            pst.setString(7, ctacteProductor.getComprobanteAfectado());
            pst.setDouble(8, ctacteProductor.getCantidadMiel());
            pst.setDouble(9, ctacteProductor.getDebe());
            pst.setDouble(10, ctacteProductor.getHaber());
            pst.setDouble(11, ctacteProductor.getSaldo());
            pst.setString(12, ctacteProductor.getEstadoMovimiento());
            pst.setString(13, ctacteProductor.getObservacion());
            
            
            int N = pst.executeUpdate();

            if (N != 0) {
                return true;
            } else {
                return false;
            }
            
            
        } catch (Exception e) {
            
            e.printStackTrace(System.out);

        }
        
        return false;
    }
    
    public boolean cancelarCompraConsignacionCtaCte(int codigoMovimiento, int codigoProductor){

        try {

            PreparedStatement pst = cn.prepareStatement("UPDATE cta_cte_productor SET estado_movimiento = ? WHERE codigo_productor = '"+ codigoProductor +"' and codigo_movimiento = '"+ codigoMovimiento +"'");
            pst.setString(1, "CANCELADO");

            int N = pst.executeUpdate();

            if (N != 0) {
                
                //ConexionBD.close(cn);
                //ConexionBD.close(pst);
                return true;
                
            } else {
                
                //ConexionBD.close(cn);
                //ConexionBD.close(pst);
                return false;
                
            }
        } catch (SQLException ex) {
            
            ex.printStackTrace(System.out);
            
        }

        return false;
        
    }
    
    public boolean modificarMovimientoCtaCteProductor(CtaCteProductor ctacteProductor, int codigoMovimiento) {

        try {


            PreparedStatement pst = cn.prepareStatement("UPDATE cta_cte_productor SET fecha_movimiento = ?, descripcion_movimiento = ?, comprobante_asociado = ?, numero_comprobante = ?, cantidad_miel = ?, debe = ?, haber = ?, saldo = ?, estado_movimiento = ?, observacion = ? WHERE codigo_productor = '"+ codigoProductor +"'codigo_movimiento = '"+ codigoMovimiento +"'");

            pst.setDate(1, ctacteProductor.getFechaMovimiento());
            pst.setString(2, ctacteProductor.getDescripcionMovimiento());
            pst.setInt(3, ctacteProductor.getComprobanteAsociado());
            pst.setString(4, ctacteProductor.getNumeroComprobante());
            pst.setString(5, ctacteProductor.getComprobanteAfectado());
            pst.setDouble(6, ctacteProductor.getCantidadMiel());
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
        } catch (SQLException ex) {
            
            ex.printStackTrace(System.out);
            
        }

        return false;
    }
    
    public String obtenerEstadoComprobanteCtaCteProductor(int codigoProductor, int codigoMovimiento){
        
        String estadoComprobante = "";
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT estado_movimiento FROM cta_cte_productor WHERE codigo_productor='" + codigoProductor + "' and codigo_movimiento='"+ codigoMovimiento+"'");
            
            while (rs.next()) {

                estadoComprobante = rs.getString("estado_movimiento");
                
            }
            
            return estadoComprobante;

        }
        catch(Exception e){
            
            //JOptionPane.showMessageDialog(null, e);
            return estadoComprobante;
            
        } 
        
    }

    public DefaultTableModel listarMovimientosCtaCteProductor(int codigoProductor){
        
        DefaultTableModel modelo;
        
        String[] titulos = {"ID PRODUCTOR", "ID MOVIMIENTO", "FECHA", "REFERENCIA", "COMPROBANTE ASOCIADO", "N° COMPROB.", "COMPROBANTE AFECTADO","KGS. MIEL COMPRADOS", "KGS. MIEL IMPAGOS", "IMPORTE", "PAGADO", "SALDO", "ESTADO MOVIMIENTO", "OBSERVACION"};

        String[] registros = new String[14];

        modelo = new DefaultTableModel(null, titulos) {
            
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                
                if (columnas == 13) {
                    
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
                registros[6] = rs.getString("comprobante_afectado");
                registros[7] = rs.getString("cantidad_miel");
                registros[9] = rs.getString("debe");
                registros[10] = rs.getString("haber");
                registros[11] = rs.getString("saldo");
                registros[12] = rs.getString("estado_movimiento");
                registros[13] = rs.getString("observacion");
                
                String comprobante = rs.getString("descripcion_movimiento");
                int codigoMovimiento = 0;
                codigoMovimiento = Integer.valueOf(rs.getString("codigo_movimiento").toString());
                int codigoComprobante = 0;
                codigoComprobante = Integer.valueOf(rs.getString("comprobante_asociado").toString());
                String estado = "";
                estado = obtenerEstadoComprobanteCtaCteProductor(codigoComprobante, codigoMovimiento);
                
                if (comprobante.equals("CONSIGNACION")){
                    
                    if (estado.equals("CANCELADO")){
                            
                        registros[8] = "0.00";
                            
                    }
                    else{

                        registros[8] = String.valueOf(rs.getDouble("cantidad_miel") - obtenerDetalleMielDescontadaCompraConsignacion(codigoComprobante));
                    
                    }
                }
                else{
                    
                    if (comprobante.equals("FACTURA A") || comprobante.equals("FACTURA C") || comprobante.equals("PRESUPUESTO")){
                        
                        estado = obtenerEstadoComprobanteCtaCteProductor(rs.getInt("codigo_productor"), rs.getInt("codigo_movimiento"));
                        if (estado.equals("CANCELADO")){
                            
                            registros[8] = "0.00";
                            
                        }
                        else{
                            
                            registros[8] = String.valueOf(rs.getDouble("cantidad_miel"));
                            
                        }
                        
                    }
                    else{

                        registros[8] = "0.00";

                    }
                }
                
                modelo.addRow(registros);
                
            }
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }
    
    //ESTE METODO ESTA PERFECTO
    public Double mostrarSaldoCtaCteProductor(int codigoProductor) {

        Double saldo = 0.00;
        
        try {

            Statement st = cn.createStatement();
            Statement st2 = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT saldo FROM cta_cte_productor WHERE codigo_productor='" + codigoProductor + "' and (estado_movimiento <> 'ANULADO' and estado_movimiento <> 'CANCELADO')");

            while (rs.next()) {
                
                saldo = saldo + rs.getDouble("saldo");
                
            }
            
            return saldo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

        return saldo;
        
    }
    
    //ESTE METODO ESTA PERFECTO
    public Double mostrarSaldoMielImpagaAProductor(int codigoProductor) {

        //almacena toda la miel financiada en el total de compras en consignacion realizadas
        Double totalMielFinanciada = 0.00;
        Double totalMielDescontada = 0.00;
        Double saldoMielImpaga = 0.00;
       
        try {

            Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT cantidad_miel, comprobante_asociado FROM cta_cte_productor WHERE codigo_productor='" +codigoProductor+ "' and descripcion_movimiento='CONSIGNACION' and estado_movimiento='PENDIENTE'");

            while (rs.next()) {
                
                //voy obteniendo el total de miel financiado
                totalMielFinanciada = totalMielFinanciada + rs.getDouble("cantidad_miel");
                //voy consultando para cada compra el total de miel devuelta y / o facturada
                totalMielDescontada = totalMielDescontada + obtenerDetalleMielDescontadaCompraConsignacion(rs.getInt("comprobante_asociado"));
                
            }
            //una vez hecho este recorrido ya tengo el total de miel financiada por el productor consultado
            //a este total hay que restarle el total de miel facturada y/o devuelta al mismo productor
            
            saldoMielImpaga = totalMielFinanciada - totalMielDescontada;
            return saldoMielImpaga;

        } catch (Exception e) {
            
            JOptionPane.showConfirmDialog(null, e);
            
        }

        return saldoMielImpaga;
        
    }

    //ESTE METODO ESTA PERFECTO
    public Double obtenerDetalleMielDescontadaCompraConsignacion(int codigoComprobante){
        
        Double mielDescontada = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel_afectada) as cantidad_miel_afectada FROM comprobantes_relacionados_compra_credito WHERE codigo_compra_consignacion='" + codigoComprobante + "' and estado_comprobante_facturacion <> 'ANULADO'");
            
            while (rs.next()) {

                mielDescontada = mielDescontada+rs.getDouble("cantidad_miel_afectada");
                
            }
            
            return mielDescontada;

        }
        catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return mielDescontada;
            
        } 
        
    }

    public boolean actualizarSaldoComprobanteProductor(int movimiento, int productor, double debeComprobante, double pagoRealizado, double montoPagado) {

        try {


            PreparedStatement pst = cn.prepareStatement("UPDATE cta_cte_productor SET haber = ?, saldo = ?, estado_movimiento = ? WHERE codigo_productor = '"+ productor +"' and codigo_movimiento = '"+ movimiento +"'");

            //tengo que sumar el valor del pago realizado al valor que ya se encuentra en el campo haber
            //y tengo que guardar en el campo saldo la resta entre el campo debe y el nuevo valor del campo haber
            
            double haberActualizado = pagoRealizado + montoPagado;
            double saldoActualizado = debeComprobante - haberActualizado;
            
            pst.setDouble(1, haberActualizado);
            pst.setDouble(2, debeComprobante - haberActualizado);
            
            if (saldoActualizado == 0){
                
                pst.setString(3, "CANCELADO");
                
            }
            else{
                
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
    
    public boolean actualizarSaldoComprobanteProductorPorPagoAnulado(int movimiento, int productor, double debeComprobante, double pagoRealizado, double haberComprobante) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            PreparedStatement pst = cn.prepareStatement("UPDATE cta_cte_productor SET haber = ?, saldo = ?, estado_movimiento = ? WHERE codigo_productor = '"+ productor +"' and codigo_movimiento = '"+ movimiento +"'");

            //tengo que sumar el valor del pago realizado al valor que ya se encuentra en el campo haber
            //y tengo que guardar en el campo saldo la resta entre el campo debe y el nuevo valor del campo haber
            
            double haberActualizado = haberComprobante - pagoRealizado;
            double saldoActualizado = debeComprobante - haberActualizado;
            
            pst.setDouble(1, haberActualizado);
            pst.setDouble(2, saldoActualizado);
            pst.setString(3, "PENDIENTE");

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
    
    public boolean actualizarEstadoConsignacionPorFacturacionAnulada(int movimiento, int productor) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            PreparedStatement pst = cn.prepareStatement("UPDATE cta_cte_productor SET estado_movimiento = ? WHERE codigo_productor = '"+ productor +"' and codigo_movimiento = '"+ movimiento +"'");

            //tengo que sumar el valor del pago realizado al valor que ya se encuentra en el campo haber
            //y tengo que guardar en el campo saldo la resta entre el campo debe y el nuevo valor del campo haber
            
            pst.setString(1, "PENDIENTE");

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
    
    public boolean actualizarEstadoComprobanteProductor(int movimiento, int productor) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            PreparedStatement pst = cn.prepareStatement("UPDATE cta_cte_productor SET estado_movimiento = ? WHERE codigo_productor = '"+ productor +"' and codigo_movimiento = '"+ movimiento +"'");

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
