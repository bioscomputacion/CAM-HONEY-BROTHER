/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Funciones;

import ar.com.bioscomputacion.Formularios.FrmGestionStockMiel;
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
public class StockRealMiel {
    
    private int codigo_movimiento;
    private Date fecha_movimiento; 
    private String tipo_movimiento;
    private String comprobante_asociado;
    private int numero_comprobante_asociado;
    private Double cantidad_miel;
    private int locacion_miel;
    private int miel_deposito_productor;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();

    public StockRealMiel(int codigo_movimiento, Date fecha_movimiento, String tipo_movimiento, String comprobante_asociado, int numero_comprobante_asociado, Double cantidad_miel, int locacion_miel, int miel_deposito_productor) {
        this.codigo_movimiento = codigo_movimiento;
        this.fecha_movimiento = fecha_movimiento;
        this.tipo_movimiento = tipo_movimiento;
        this.comprobante_asociado = comprobante_asociado;
        this.numero_comprobante_asociado = numero_comprobante_asociado;
        this.cantidad_miel = cantidad_miel;
        this.locacion_miel = locacion_miel;
        this.miel_deposito_productor = miel_deposito_productor;
    }

    public StockRealMiel() {
    }

    public int getCodigo_movimiento() {
        return codigo_movimiento;
    }

    public void setCodigo_movimiento(int codigo_movimiento) {
        this.codigo_movimiento = codigo_movimiento;
    }

    public Date getFecha_movimiento() {
        return fecha_movimiento;
    }

    public void setFecha_movimiento(Date fecha_movimiento) {
        this.fecha_movimiento = fecha_movimiento;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public String getComprobante_asociado() {
        return comprobante_asociado;
    }

    public void setComprobante_asociado(String comprobante_asociado) {
        this.comprobante_asociado = comprobante_asociado;
    }

    public int getNumero_comprobante_asociado() {
        return numero_comprobante_asociado;
    }

    public void setNumero_comprobante_asociado(int numero_comprobante_asociado) {
        this.numero_comprobante_asociado = numero_comprobante_asociado;
    }

    public Double getCantidad_miel() {
        return cantidad_miel;
    }

    public void setCantidad_miel(Double cantidad_miel) {
        this.cantidad_miel = cantidad_miel;
    }

    public int getLocacion_miel() {
        return locacion_miel;
    }

    public void setLocacion_miel(int locacion_miel) {
        this.locacion_miel = locacion_miel;
    }

    public int getMiel_deposito_productor() {
        return miel_deposito_productor;
    }

    public void setMiel_deposito_productor(int miel_deposito_productor) {
        this.miel_deposito_productor = miel_deposito_productor;
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
    
    public boolean registrarMovimientoStock(StockRealMiel stockMiel){
        
        System.out.println("llega por aca");
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO stock_real_miel (fecha_movimiento, tipo_movimiento, comprobante_asociado, numero_comprobante_asociado, cantidad_miel, locacion_miel, miel_deposito_productor) "
                    + "VALUES (?,?,?,?,?,?,?)");
            
            pst.setDate(1, stockMiel.getFecha_movimiento());
            pst.setString(2, stockMiel.getTipo_movimiento());
            pst.setString(3, stockMiel.getComprobante_asociado());
            pst.setInt(4, stockMiel.getNumero_comprobante_asociado());
            pst.setDouble(5, stockMiel.getCantidad_miel());
            pst.setInt(6, stockMiel.getLocacion_miel());
            pst.setInt(7, stockMiel.getMiel_deposito_productor());
            
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
    
    public Double obtenerDetalleMielComprada(int codigoLocacion){
        
        Double mielComprada = 0.00;
        String tipoMovimiento = "COMPRA";
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento='"+ tipoMovimiento +"'");
            
            while (rs.next()) {

                mielComprada = rs.getDouble("cantidad_miel");
                
            }
            
            return mielComprada;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return mielComprada;
            
        } 
        
    }
    
    public Double obtenerDetalleMielVendida(int codigoLocacion){
        
        Double mielComprada = 0.00;
        String tipoMovimiento = "VENTA";
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento='"+ tipoMovimiento +"'");
            
            while (rs.next()) {

                mielComprada = rs.getDouble("cantidad_miel");
                
            }
            
            return mielComprada;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return mielComprada;
            
        } 
        
    }

    public Double obtenerDetalleMielRecibidaTraslado(int codigoLocacion){
        
        Double mielComprada = 0.00;
        String tipoMovimiento = "TRASLADO - DESTINO";
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento='"+ tipoMovimiento +"'");
            
            while (rs.next()) {

                mielComprada = rs.getDouble("cantidad_miel");
                
            }
            
            return mielComprada;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return mielComprada;
            
        } 
        
    }
    
    public Double obtenerDetalleMielEnviadaTraslado(int codigoLocacion){
        
        Double mielComprada = 0.00;
        String tipoMovimiento = "TRASLADO - ORIGEN";
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento='"+ tipoMovimiento +"'");
            
            while (rs.next()) {

                mielComprada = rs.getDouble("cantidad_miel");
                
            }
            
            return mielComprada;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return mielComprada;
            
        } 
        
    }

    public Double obtenerDetalleMielCompradaDepositadaLocacionProductor(int codigoProductor){
        
        Double mielComprada = 0.00;
        String tipoMovimiento = "COMPRA";
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where miel_deposito_productor='"+ codigoProductor +"' and tipo_movimiento='"+ tipoMovimiento +"'");
            
            while (rs.next()) {

                mielComprada = rs.getDouble("cantidad_miel");
                
            }
            
            return mielComprada;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return mielComprada;
            
        } 
        
    }

    public Double obtenerDetalleMielTrasladadaDesdeLocacionProductor(int codigoProductor){
        
        Double mielComprada = 0.00;
        String tipoMovimiento = "TRASLADO - ORIGEN";
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where miel_deposito_productor='"+ codigoProductor +"' and tipo_movimiento='"+ tipoMovimiento +"'");
            
            while (rs.next()) {

                mielComprada = rs.getDouble("cantidad_miel");
                
            }
            
            return mielComprada;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return mielComprada;
            
        } 
        
    }

    public DefaultTableModel mostrarDetalleStock() {

        DefaultTableModel modelo;

        String[] titulos = {"ID", "LOCACION", "STOCK TOTAL", "STOCK PAGO", "STOCK EN CONSIGNACION"};

        String[] registros = new String[5];

        modelo = new DefaultTableModel(null, titulos) {
            
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 5) {
                    return true;
                } else {
                    return false;
                }

            }

        };
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select codigo_locacion, nombre_locacion from locacion order by codigo_locacion asc");

            double mielComprada, mielVendida, mielRecibida, mielEnviada, saldoMiel = 0.00;
            int locacion = 0;
            
            StockRealMiel stock = new StockRealMiel();
            
            while (rs.next()) {
                
                locacion = rs.getInt("codigo_locacion");
                mielComprada = stock.obtenerDetalleMielComprada(locacion);
                mielVendida = stock.obtenerDetalleMielVendida(locacion);
                mielRecibida = stock.obtenerDetalleMielRecibidaTraslado(locacion);
                mielEnviada = stock.obtenerDetalleMielEnviadaTraslado(locacion);
                saldoMiel = mielComprada + mielRecibida - mielVendida - mielEnviada;
                registros[0] = rs.getString("codigo_locacion");
                registros[1] = rs.getString("nombre_locacion");
                registros[2] = String.valueOf(saldoMiel);
                registros[3] = "0.00";
                registros[4] = "0.00";
                /*System.out.println(mielComprada);
                System.out.println(mielVendida);
                System.out.println(saldoMiel);*/
                
                modelo.addRow(registros);
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }
    
    public Double calcularTotalStockGlobal() {

        Double totalStockGlobal = 0.00;

        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select codigo_locacion from locacion order by codigo_locacion asc");

            double mielComprada, mielVendida, mielRecibida, mielEnviada, saldoMiel = 0.00;
            int locacion = 0;
            
            StockRealMiel stock = new StockRealMiel();
            
            while (rs.next()) {
                
                locacion = rs.getInt("codigo_locacion");
                mielComprada = stock.obtenerDetalleMielComprada(locacion);
                mielVendida = stock.obtenerDetalleMielVendida(locacion);
                mielRecibida = stock.obtenerDetalleMielRecibidaTraslado(locacion);
                mielEnviada = stock.obtenerDetalleMielEnviadaTraslado(locacion);
                saldoMiel = mielComprada + mielRecibida - mielVendida - mielEnviada;
                totalStockGlobal = totalStockGlobal + saldoMiel;
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return totalStockGlobal;
        
    }
    
    public DefaultTableModel mostrarDetalleMovimientosStockLocacion(int codigoLocacion) {

        DefaultTableModel modelo;

        String[] titulos = {"FECHA", "REFERENCIA", "COMPROBANTE", "N° COMPROBANTE", "KGS. MIEL"};

        String[] registros = new String[5];

        modelo = new DefaultTableModel(null, titulos) {
            
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 5) {
                    return true;
                } else {
                    return false;
                }

            }

        };
        
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select fecha_movimiento, tipo_movimiento, comprobante_asociado, numero_comprobante_asociado, cantidad_miel from stock_real_miel where locacion_miel='"+ codigoLocacion +"' order by codigo_movimiento asc");

            while (rs.next()) {
                
                registros[0] = rs.getString("fecha_movimiento");
                registros[1] = rs.getString("tipo_movimiento");
                registros[2] = rs.getString("comprobante_asociado");
                registros[3] = rs.getString("numero_comprobante_asociado");
                registros[4] = rs.getString("cantidad_miel");
                
                modelo.addRow(registros);
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }

    
    
}
