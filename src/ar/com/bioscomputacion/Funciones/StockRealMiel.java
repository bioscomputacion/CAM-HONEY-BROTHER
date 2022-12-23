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
import java.sql.SQLException;
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
    private int id_comprobante_asociado;
    private String numero_comprobante_asociado;
    private Double cantidad_miel;
    private int locacion_miel;
    private int miel_deposito_productor;
    private String estado_compra;
    private String estado_movimiento;
    
    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();

    public StockRealMiel(int codigo_movimiento, Date fecha_movimiento, String tipo_movimiento, String comprobante_asociado, int id_comprobante_asociado, String numero_comprobante_asociado, Double cantidad_miel, int locacion_miel, int miel_deposito_productor, String estado_compra, String estado_movimiento) {
        
        this.codigo_movimiento = codigo_movimiento;
        this.fecha_movimiento = fecha_movimiento;
        this.tipo_movimiento = tipo_movimiento;
        this.comprobante_asociado = comprobante_asociado;
        this.id_comprobante_asociado = id_comprobante_asociado;
        this.numero_comprobante_asociado = numero_comprobante_asociado;
        this.cantidad_miel = cantidad_miel;
        this.locacion_miel = locacion_miel;
        this.miel_deposito_productor = miel_deposito_productor;
        this.estado_compra = estado_compra;
        this.estado_movimiento = estado_movimiento;
        
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

    public int getId_comprobante_asociado() {
        return id_comprobante_asociado;
    }

    public void setId_comprobante_asociado(int numero_comprobante_asociado) {
        this.id_comprobante_asociado = numero_comprobante_asociado;
    }

    public String getNumero_comprobante_asociado() {
        return numero_comprobante_asociado;
    }

    public void setNumero_comprobante_asociado(String numero_comprobante_asociado) {
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

    public String getEstado_compra() {
        return estado_compra;
    }

    public void setEstado_compra(String estado_compra) {
        this.estado_compra = estado_compra;
    }

    public String getEstado_movimiento() {
        return estado_movimiento;
    }

    public void setEstado_movimiento(String estado_movimiento) {
        this.estado_movimiento = estado_movimiento;
    }
    
    public boolean registrarMovimientoStock(StockRealMiel stockMiel){
        
        try {
            
            //ConexionBD mysql = new ConexionBD();
            //Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO stock_real_miel (fecha_movimiento, tipo_movimiento, comprobante_asociado, id_comprobante_asociado, numero_comprobante_asociado, cantidad_miel, locacion_miel, miel_deposito_productor, estado_compra, estado_movimiento) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)");
            
            pst.setDate(1, stockMiel.getFecha_movimiento());
            pst.setString(2, stockMiel.getTipo_movimiento());
            pst.setString(3, stockMiel.getComprobante_asociado());
            pst.setInt(4, stockMiel.getId_comprobante_asociado());
            pst.setString(5, stockMiel.getNumero_comprobante_asociado());
            pst.setDouble(6, stockMiel.getCantidad_miel());
            pst.setInt(7, stockMiel.getLocacion_miel());
            pst.setInt(8, stockMiel.getMiel_deposito_productor());
            pst.setString(9, stockMiel.getEstado_compra());
            pst.setString(10, stockMiel.getEstado_movimiento());
            
            int N = pst.executeUpdate();

            if (N != 0) {
                
                return true;
                
            } else {
                
                return false;
                
            }
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
        }
        
        return false;
        
    }
    
    public boolean eliminarMovimientoStock(int codigoMovimientoStock) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("DELETE FROM stock_real_miel WHERE codigo_movimiento = '"+ codigoMovimientoStock +"'");

            int N = pst.executeUpdate();

            ConexionBD.close(cn);
            ConexionBD.close(pst);
            
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
    
    public boolean modificarTipoYNumeroComprobanteMovimientoStock(int codigoMovimientoStock, String comprobanteAsociado, int idComprobanteAsociado, String numeroComprobanteAsociado) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("UPDATE stock_real_miel SET comprobante_asociado = ?, id_comprobante_asociado = ?, numero_comprobante_asociado = ? WHERE codigo_movimiento = '"+ codigoMovimientoStock +"'");

            pst.setString(1, comprobanteAsociado);
            pst.setInt(2, idComprobanteAsociado);
            pst.setString(3, numeroComprobanteAsociado);
            
            int N = pst.executeUpdate();

            ConexionBD.close(cn);
            ConexionBD.close(pst);
            
            if (N != 0) {
                
                return true;
                
            } else {
                
                return false;
                
            }
        } catch (SQLException ex) {
            
            
        }

        return false;
    }

    public int mostrarIdMovimientoStock() {

        int codigoMovimiento = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_movimiento FROM stock_real_miel order by codigo_movimiento asc");
            
            while (rs.next()) {

                codigoMovimiento = rs.getInt("codigo_movimiento");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);

            return codigoMovimiento;

        }catch(Exception e){
            
            return codigoMovimiento;
        } 

    }

    public Double obtenerDetalleDescuentosEnConsignaciones(int codigoLocacion){
        
        //esto es para obtener el detalle de lo que se ha descontado de cada compra en consignacion, para
        //que al levantar los datos de la tabla stock de miel la cantidad sea real!
        
        Double mielDescontadaConsignaciones = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_comprobante_asociado, cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento='COMPRA' and comprobante_asociado='CONSIGNACION')");
            
            while (rs.next()) {

                mielDescontadaConsignaciones = rs.getDouble("cantidad_miel");
                
            }
            
            return mielDescontadaConsignaciones;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return mielDescontadaConsignaciones;
            
        } 
        
    }

    //DESDE ACA: SIRVEN LOS METODOS QUE VOY INDICANDO
    
    //SIRVE!!!
    //Devuelve toda la miel que ha ingresado en la locacion indicada (ya sea por compras, ingresos de miel propia
    //o traslados desde otra locacion)
    public Double obtenerDetalleIngresoMiel(int codigoLocacion){
        
        //cuando se trata de compras en consignacion, deberia restar a la miel consignada en las mismas,
        //las facturas y devoluciones por las que son afectadas, para que la cantidad stockeada referida
        //por dicha compra en consignacion sea real! Seria realizar algo muy parecido a lo que se hace
        //cuando se listan los movimietos en la cta. cte. de la empresa con un productor dado
        
        Double ingresoMiel = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            //ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and (tipo_movimiento='COMPRA' or tipo_movimiento='INGRESO' or tipo_movimiento='TRASLADO - DESTINO' or tipo_movimiento='VENTA - DESTINO')");
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento in ('COMPRA', 'INGRESO', 'TRASLADO - DESTINO', 'VENTA - DESTINO', 'AUMENTO DE STOCK PAGO POR FACTURACION') and estado_movimiento <> 'ANULADO'");
            
            while (rs.next()) {

                ingresoMiel = rs.getDouble("cantidad_miel");
                
            }
            
            return ingresoMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return ingresoMiel;
            
        } 
        
    }

    //SIRVE!!!
    //Devuelve toda la miel que ha egresado en la locacion indicada
    //(ya sea por ventas, devoluciones o traslados desde otra locacion)
    public Double obtenerDetalleEgresoMiel(int codigoLocacion){
        
        Double egresoMiel = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento in ('VENTA', 'TRASLADO - ORIGEN', 'DEVOLUCION', 'FACTURACION', 'VENTA - ORIGEN', 'DESCUENTO DE STOCK IMPAGO POR FACTURACION') and estado_movimiento <> 'ANULADO'");
            //ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where miel_deposito_productor='"+ codigoProductor +"' and tipo_movimiento in ('COMPRA', 'INGRESO', 'TRASLADO - DESTINO') and estado_movimiento <> 'ANULADO'");
            
            while (rs.next()) {

                egresoMiel = rs.getDouble("cantidad_miel");
                
            }
            
            return egresoMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return egresoMiel;
            
        } 
        
    }
    

    //SIRVE!!!
    //Devuelve toda la miel PAGA que ha ingresado en la locacion indicada (ya sea por compras, ingresos de miel propia
    //o traslados a otra locacion) 
    public Double obtenerDetalleIngresoMielPaga(int codigoLocacion){
        
        Double ingresoMiel = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento in ('COMPRA', 'INGRESO', 'TRASLADO - DESTINO', 'VENTA - DESTINO', 'AUMENTO DE STOCK PAGO POR FACTURACION') and estado_compra='FACTURADA' and estado_movimiento <> 'ANULADO'");
            
            while (rs.next()) {

                ingresoMiel = rs.getDouble("cantidad_miel");
                
            }
            
            return ingresoMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return ingresoMiel;
            
        } 
        
    }

    //SIRVE!!!
    //Devuelve toda la miel PAGA que ha egresado en la locacion indicada (ya sea por ventas
    //o traslados a otra locacion) 
    public Double obtenerDetalleEgresoMielPaga(int codigoLocacion){
        
        Double egresoMiel = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            //ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and (tipo_movimiento='VENTA' or tipo_movimiento='TRASLADO - ORIGEN' or tipo_movimiento='DEVOLUCION' or tipo_movimiento = 'VENTA - ORIGEN' or tipo_movimiento = 'ANULACION') and estado_compra='FACTURADA'");
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento in ('VENTA', 'TRASLADO - ORIGEN', 'DEVOLUCION', 'VENTA - ORIGEN') and estado_compra='FACTURADA' and estado_movimiento <> 'ANULADO'");
            
            while (rs.next()) {

                egresoMiel = rs.getDouble("cantidad_miel");
                
            }
            
            return egresoMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return egresoMiel;
            
        } 
        
    }

    //SIRVE!!!
    //Devuelve toda la miel IMPAGA que ha ingresado en la locacion indicada (ya sea por compras
    //o traslados desde otra locacion) 
    public Double obtenerDetalleIngresoMielImpaga(int codigoLocacion){
        
        Double ingresoMiel = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento IN ('COMPRA', 'INGRESO', 'TRASLADO - DESTINO', 'VENTA - DESTINO') and estado_compra='SIN FACTURAR' and estado_movimiento <> 'ANULADO'");
            
            while (rs.next()) {

                ingresoMiel = rs.getDouble("cantidad_miel");
                
            }
            
            return ingresoMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return ingresoMiel;
            
        } 
        
    }

    //SIRVE!!!
    //Devuelve toda la miel IMPAGA que ha egresado en la locacion indicada (ya sea por ventas
    //o traslados a otra locacion) 
    public Double obtenerDetalleEgresoMielImpaga(int codigoLocacion){
        
        Double egresoMiel = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento in ('VENTA', 'TRASLADO - ORIGEN', 'DEVOLUCION', 'DESCUENTO DE STOCK IMPAGO POR FACTURACION', 'VENTA - ORIGEN') and estado_compra='SIN FACTURAR' and estado_movimiento <> 'ANULADO'");
            
            while (rs.next()) {

                egresoMiel = rs.getDouble("cantidad_miel");
                
            }
            
            return egresoMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return egresoMiel;
            
        } 
        
    }

    //SIRVE!!!
    //Devuelve toda la miel que ha ingresado en la locacion indicada
    //(ya sea por compras, ingresos o traslados desde otra locacion)
    public Double obtenerDetalleIngresoMielLocacionProductor(int codigoProductor){
        
        Double ingresoMiel = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where miel_deposito_productor='"+ codigoProductor +"' and tipo_movimiento in ('COMPRA', 'INGRESO', 'TRASLADO - DESTINO') and estado_movimiento <> 'ANULADO'");
            
            while (rs.next()) {

                ingresoMiel = rs.getDouble("cantidad_miel");
                
            }
            
            return ingresoMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return ingresoMiel;
            
        } 
        
    }

    //SIRVE!!!
    //Devuelve toda la miel que ha egresado en la locacion indicada
    //(ya sea por ventas, devoluciones o traslados desde otra locacion)
    public Double obtenerDetalleEgresoMielLocacionProductor(int codigoProductor){
        
        Double egresoMiel = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where miel_deposito_productor='"+ codigoProductor +"' and tipo_movimiento in ('VENTA', 'TRASLADO - ORIGEN', 'DEVOLUCION', 'FACTURACION') and estado_movimiento <> 'ANULADO'");
            
            while (rs.next()) {

                egresoMiel = rs.getDouble("cantidad_miel");
                
            }
            
            return egresoMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return egresoMiel;
            
        } 
        
    }
    
    //SIRVE!!!
    //Devuelve toda la miel PAGA que ha ingresado en la locacion indicada (ya sea por compras, ingresos de miel propia
    //o traslados a otra locacion) 
    public Double obtenerDetalleIngresoMielPagaLocacionProductor(int codigoProductor){
        
        Double ingresoMiel = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            //ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento in ('COMPRA', 'INGRESO', 'TRASLADO - DESTINO', 'VENTA - DESTINO') and estado_movimiento <> 'ANULADO'");
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where miel_deposito_productor='"+ codigoProductor +"' and tipo_movimiento in ('COMPRA', 'INGRESO', 'TRASLADO - DESTINO') and estado_compra='FACTURADA' and estado_movimiento <> 'ANULADO'");
            
            while (rs.next()) {

                ingresoMiel = rs.getDouble("cantidad_miel");
                
            }
            
            return ingresoMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return ingresoMiel;
            
        } 
        
    }

    //SIRVE!!!
    //Devuelve toda la miel PAGA que ha egresado en la locacion indicada (ya sea por ventas
    //o traslados a otra locacion) 
    public Double obtenerDetalleEgresoMielPagaLocacionProductor(int codigoProductor){
        
        Double egresoMiel = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where miel_deposito_productor='"+ codigoProductor +"' and tipo_movimiento in ('VENTA', 'TRASLADO - ORIGEN', 'DEVOLUCION') and estado_compra='FACTURADA' and estado_movimiento <> 'ANULADO'");
            
            while (rs.next()) {

                egresoMiel = rs.getDouble("cantidad_miel");
                
            }
            
            return egresoMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return egresoMiel;
            
        } 
        
    }

    //SIRVE!!!
    //Devuelve toda la miel IMPAGA que ha ingresado en la locacion indicada (ya sea por compras
    //o traslados desde otra locacion) 
    public Double obtenerDetalleIngresoMielImpagaLocacionProductor(int codigoProductor){
        
        Double ingresoMiel = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            //ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento='"+ tipoMovimiento +"' and estado_compra<>'"+ estadoCompra +"'");
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where miel_deposito_productor='"+ codigoProductor +"' and tipo_movimiento IN ('COMPRA', 'INGRESO', 'TRASLADO - DESTINO') and estado_compra='SIN FACTURAR' and estado_movimiento <> 'ANULADO'");
            
            while (rs.next()) {

                ingresoMiel = rs.getDouble("cantidad_miel");
                
            }
            
            return ingresoMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return ingresoMiel;
            
        } 
        
    }

    //SIRVE!!!
    //Devuelve toda la miel IMPAGA que ha egresado en la locacion indicada (ya sea por ventas
    //o traslados a otra locacion) 
    public Double obtenerDetalleEgresoMielImpagaLocacionProductor(int codigoProductor){
        
        Double egresoMiel = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where miel_deposito_productor='"+ codigoProductor +"' and tipo_movimiento IN ('VENTA', 'TRASLADO - ORIGEN', 'DEVOLUCION', 'FACTURACION') and estado_compra='SIN FACTURAR' and estado_movimiento <> 'ANULADO'");
            
            while (rs.next()) {

                egresoMiel = rs.getDouble("cantidad_miel");
                
            }
            
            return egresoMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return egresoMiel;
            
        } 
        
    }

    //SIRVE!!!
    //Devuelve: devuelve stock total, stock miel paga y stock miel impaga por locacion
    public DefaultTableModel mostrarDetalleStockLocacion() {

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
            ResultSet rs = st.executeQuery("select codigo_locacion, nombre_locacion from locacion where codigo_locacion <> '1' order by codigo_locacion asc");

            double ingresoMiel, egresoMiel, ingresoMielPaga, egresoMielPaga, ingresoMielImpaga, egresoMielImpaga, saldoMiel, saldoMielPaga, saldoMielImpaga = 0.00;
            int locacion = 0;
            
            StockRealMiel stock = new StockRealMiel();
            
            while (rs.next()) {
                
                locacion = rs.getInt("codigo_locacion");
                //stock total de miel
                ingresoMiel = stock.obtenerDetalleIngresoMiel(locacion);
                egresoMiel = stock.obtenerDetalleEgresoMiel(locacion);
                //stock total PAGO de miel
                ingresoMielPaga = stock.obtenerDetalleIngresoMielPaga(locacion);
                egresoMielPaga = stock.obtenerDetalleEgresoMielPaga(locacion);
                //stock total IMPAGO (consignacion) de miel
                ingresoMielImpaga = stock.obtenerDetalleIngresoMielImpaga(locacion);
                egresoMielImpaga = stock.obtenerDetalleEgresoMielImpaga(locacion);
                //saldos!
                saldoMiel = ingresoMiel - egresoMiel;
                saldoMielPaga = ingresoMielPaga - egresoMielPaga;
                saldoMielImpaga = ingresoMielImpaga - egresoMielImpaga;
                registros[0] = rs.getString("codigo_locacion");
                registros[1] = rs.getString("nombre_locacion");
                registros[2] = String.valueOf(saldoMiel);
                registros[3] = String.valueOf(saldoMielPaga);
                registros[4] = String.valueOf(saldoMielImpaga);
                
                modelo.addRow(registros);
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }
    
    //SIRVE!!!
    //Devuelve: devuelve stock total, stock miel paga y stock miel impaga por locacion
    public DefaultTableModel mostrarDetalleStockProductor() {

        DefaultTableModel modelo;

        String[] titulos = {"ID", "NOMBRE", "PROVINCIA", "STOCK TOTAL", "STOCK PAGO", "STOCK EN CONSIGNACION"};

        String[] registros = new String[6];

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
            ResultSet rs = st.executeQuery("select p.cod_productor, s.nombre, s.estado_provincia from productor p join persona s on p.cod_persona = s.cod_persona  where p.cod_productor <> '1' order by p.cod_productor asc");

            double ingresoMiel, egresoMiel, ingresoMielPaga, egresoMielPaga, ingresoMielImpaga, egresoMielImpaga, saldoMiel, saldoMielPaga, saldoMielImpaga = 0.00;
            int productor = 0;
            StockRealMiel stock = new StockRealMiel();
            
            while (rs.next()) {
                
                productor = rs.getInt("cod_productor");
                //stock total de miel
                ingresoMiel = stock.obtenerDetalleIngresoMielLocacionProductor(productor);
                egresoMiel = stock.obtenerDetalleEgresoMielLocacionProductor(productor);
                //en este punto veo: si existe miel en el productor lo cargo a la grilla, con todos los datos de dicha miel
                //sino no va a mostrarse en la grilla de prodcutores con miel de la empresa acopiada
                if (ingresoMiel - egresoMiel != 0){

                    //stock total PAGO de miel
                    ingresoMielPaga = stock.obtenerDetalleIngresoMielPagaLocacionProductor(productor);
                    egresoMielPaga = stock.obtenerDetalleEgresoMielPagaLocacionProductor(productor);
                    //stock total IMPAGO (consignacion) de miel
                    ingresoMielImpaga = stock.obtenerDetalleIngresoMielImpagaLocacionProductor(productor);
                    egresoMielImpaga = stock.obtenerDetalleEgresoMielImpagaLocacionProductor(productor);
                    //saldos!
                    saldoMiel = ingresoMiel - egresoMiel;
                    saldoMielPaga = ingresoMielPaga - egresoMielPaga;
                    saldoMielImpaga = ingresoMielImpaga - egresoMielImpaga;
                    registros[0] = rs.getString("cod_productor");
                    registros[1] = rs.getString("nombre");
                    registros[2] = rs.getString("estado_provincia");
                    registros[3] = String.valueOf(saldoMiel);
                    registros[4] = String.valueOf(saldoMielPaga);
                    registros[5] = String.valueOf(saldoMielImpaga);

                    modelo.addRow(registros);

                }
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }

    //SIRVE!!!
    //Devuelve: devuelve locacion donde se encuentra depositada la miel comprada en el comprobante consultado
    public int obtenerLocacionMielADevolverOFacturar(int codigoConsignacion){
        
        int locacionMiel = 0;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select locacion_miel from stock_real_miel where comprobante_asociado = 'CONSIGNACION' and id_comprobante_asociado = " + codigoConsignacion);
            
            while (rs.next()) {

                locacionMiel = rs.getInt("locacion_miel");
                
            }
            
            return locacionMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return locacionMiel;
            
        } 
        
    }
    
    //SIRVE!!!
    //Devuelve: devuelve locacion donde se encuentra depositada la miel comprada en el comprobante consultado
    public int obtenerLocacionMielADevolverEnNotaCredito(int codigoFactura){
        
        int locacionMiel = 0;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select locacion_miel from stock_real_miel where comprobante_asociado in ('FACTURA A', 'FACT. A / CONSIG.', 'FACTURA C', 'FACT. C / CONSIG.') and id_comprobante_asociado = " + codigoFactura);
            
            while (rs.next()) {

                locacionMiel = rs.getInt("locacion_miel");
                
            }
            
            return locacionMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return locacionMiel;
            
        } 
        
    }

    //SIRVE!!!
    //Devuelve: devuelve locacion donde se encuentra depositada la miel comprada en el comprobante consultado
    public int obtenerLocacionMielADevolverEnCreditoPresupuesto(int codigoPresupuesto){
        
        int locacionMiel = 0;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select locacion_miel from stock_real_miel where comprobante_asociado in ('PRESUPUESTO', 'PRESUP. / CONSIG.') and id_comprobante_asociado = " + codigoPresupuesto);
            
            while (rs.next()) {

                locacionMiel = rs.getInt("locacion_miel");
                
            }
            
            return locacionMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return locacionMiel;
            
        } 
        
    }

    //Devuelve: devuelve locacion donde se encuentra depositada la miel comprada en el comprobante consultado
    public int obtenerLocacionMielADevolverEnIngreso(int codigoIngreso){
        
        int locacionMiel = 0;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select locacion_miel from stock_real_miel where comprobante_asociado = 'INGRESO' and id_comprobante_asociado = " + codigoIngreso);
            
            while (rs.next()) {

                locacionMiel = rs.getInt("locacion_miel");
                
            }
            
            return locacionMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return locacionMiel;
            
        } 
        
    }

    //Devuelve: devuelve locacion donde se encuentra depositada la miel comprada en el comprobante consultado
    public int obtenerLocacionMielADevolverPorNotaCreditoAnulada(int codigoNotaCredito){
        
        int locacionMiel = 0;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select locacion_miel from stock_real_miel where (comprobante_asociado = 'NOTA DE CREDITO A' or comprobante_asociado = 'NOTA DE CREDITO C') and id_comprobante_asociado = "+ codigoNotaCredito);
            
            while (rs.next()) {

                locacionMiel = rs.getInt("locacion_miel");
                
            }
            
            return locacionMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return locacionMiel;
            
        } 
        
    }

    public int obtenerLocacionMielADevolverPorCreditoPresupuestoAnulado(int codigoCreditoPresupuesto){
        
        int locacionMiel = 0;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select locacion_miel from stock_real_miel where comprobante_asociado = 'CREDITO DE PRESUPUESTO' and id_comprobante_asociado = '"+ codigoCreditoPresupuesto + "'");
            
            while (rs.next()) {

                locacionMiel = rs.getInt("locacion_miel");
                
            }
            
            return locacionMiel;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return locacionMiel;
            
        } 
        
    }

    //CHEQUEAAAR BIEEEEN: VER DESDE ACA QUE HAY QUE ACOMODAR, NO BORRAR NADA!
    public Double obtenerDetalleMielComprada(int codigoLocacion){
        
        Double mielComprada = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            //ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento='"+ tipoMovimiento +"' and estado_compra<>'"+ estadoCompra +"'");
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and (tipo_movimiento='COMPRA' or tipo_movimiento='INGRESO')");
            
            while (rs.next()) {

                mielComprada = rs.getDouble("cantidad_miel");
                
            }
            
            return mielComprada;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return mielComprada;
            
        } 
        
    }
    
    //Sirve??????????
    //metodos para devolver miel comprada: el primero devuelve la miel comprada que esta paga - el segundo la miel comprada impaga
    public Double obtenerDetalleMielCompradaPaga(int codigoLocacion){
        
        Double mielComprada = 0.00;
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where miel_deposito_productor='"+ codigoLocacion +"' and tipo_movimiento='COMPRA' or tipo_movimiento='INGRESO' and estado_compra='FACTURADA'");
            
            while (rs.next()) {

                mielComprada = rs.getDouble("cantidad_miel");
                
            }
            
            return mielComprada;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return mielComprada;
            
        }   
        
    }

    //Sirve??????????
    public Double obtenerDetalleMielCompradaCredito(int codigoLocacion){
        
        Double mielComprada = 0.00;
        String tipoMovimiento = "COMPRA";
        String estadoCompra = "SIN FACTURAR";
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento='"+ tipoMovimiento +"' and estado_compra='"+ estadoCompra +"'");
            
            while (rs.next()) {

                mielComprada = rs.getDouble("cantidad_miel");
                
            }
            
            return mielComprada;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return mielComprada;
            
        } 
        
    }

    //Sirve??????????
    public Double obtenerDetalleMielVendida(int codigoLocacion){
        
        Double mielVendida = 0.00;
        //String tipoMovimiento = "VENTA";
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento='VENTA'");
            
            while (rs.next()) {

                mielVendida = rs.getDouble("cantidad_miel");
                
            }
            
            return mielVendida;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return mielVendida;
            
        } 
        
    }

    //Sirve??????????
    public Double obtenerDetalleMielRecibidaTraslado(int codigoLocacion){
        
        Double mielRecibida = 0.00;
        //String tipoMovimiento = "TRASLADO - DESTINO";
        
        try{
 
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT SUM(cantidad_miel) as cantidad_miel FROM stock_real_miel where locacion_miel='"+ codigoLocacion +"' and tipo_movimiento='TRASLADO - DESTINO'");
            
            while (rs.next()) {

                mielRecibida = rs.getDouble("cantidad_miel");
                
            }
            
            return mielRecibida;

        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            return mielRecibida;
            
        } 
        
    }
    
    //Sirve??????????
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

    //sirve!
    public Double calcularTotalStockGlobal() {

        Double totalStockGlobal = 0.00;

        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select codigo_locacion from locacion where (categoria <> 'EMBARQUE' and categoria <> 'DEPOSITO DE EXPORTADOR INTERNO' and categoria <> 'EXPORTACION') order by codigo_locacion asc");

            double ingresoMiel, egresoMiel, saldoMiel = 0.00;
            int locacion = 0;
            
            StockRealMiel stock = new StockRealMiel();
            
            while (rs.next()) {
                
                locacion = rs.getInt("codigo_locacion");
                ingresoMiel = stock.obtenerDetalleIngresoMiel(locacion);
                egresoMiel = stock.obtenerDetalleEgresoMiel(locacion);
                saldoMiel = ingresoMiel - egresoMiel;
                totalStockGlobal = totalStockGlobal + saldoMiel;
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return totalStockGlobal;
        
    }
    
    //sirve!
    
    public Double calcularTotalStockGlobalPago() {

        Double totalStockGlobalPago = 0.00;

        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select codigo_locacion from locacion where (categoria <> 'EMBARQUE' and categoria <> 'EXPORTACION') order by codigo_locacion asc");

            double ingresoMielPaga, egresoMielPaga, saldoMiel = 0.00;
            int locacion = 0;
            
            StockRealMiel stock = new StockRealMiel();
            
            while (rs.next()) {
                
                locacion = rs.getInt("codigo_locacion");
                //deberia crear un metodo que devuelva la miel comprada pero facturada y/o presupuestada (que se toma como paga)
                ingresoMielPaga = stock.obtenerDetalleIngresoMielPaga(locacion);
                egresoMielPaga = stock.obtenerDetalleEgresoMielPaga(locacion);
                saldoMiel = ingresoMielPaga - egresoMielPaga;
                totalStockGlobalPago = totalStockGlobalPago + saldoMiel;
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return totalStockGlobalPago;
        
    }
    
    //sirve!
    public Double calcularTotalStockGlobalCredito() {

        Double totalStockGlobalImpago = 0.00;

        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select codigo_locacion from locacion where (categoria <> 'EMBARQUE' and categoria <> 'EXPORTACION') order by codigo_locacion asc");

            double ingresoMielImpaga, egresoMielImpaga, saldoMiel = 0.00;
            int locacion = 0;
            
            StockRealMiel stock = new StockRealMiel();
            
            while (rs.next()) {
                
                locacion = rs.getInt("codigo_locacion");
                //deberia crear un metodo que devuelva la miel comprada pero a credito / no facturada (que se toma como impaga)
                ingresoMielImpaga = stock.obtenerDetalleIngresoMielImpaga(locacion);
                egresoMielImpaga = stock.obtenerDetalleEgresoMielImpaga(locacion);
                saldoMiel = ingresoMielImpaga - egresoMielImpaga;
                totalStockGlobalImpago = totalStockGlobalImpago + saldoMiel;
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return totalStockGlobalImpago;
        
    }

    //sirve!
    public Double calcularTotalStockEnProductores() {

        Double totalStock = 0.00;

        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select codigo_locacion from locacion where categoria = 'DEPOSITO DE PRODUCTOR' order by codigo_locacion asc");

            double ingresoMiel, egresoMiel, saldoMiel = 0.00;
            int locacion = 0;
            
            StockRealMiel stock = new StockRealMiel();
            
            while (rs.next()) {
                
                locacion = rs.getInt("codigo_locacion");
                ingresoMiel = stock.obtenerDetalleIngresoMiel(locacion);
                egresoMiel = stock.obtenerDetalleEgresoMiel(locacion);
                saldoMiel = ingresoMiel - egresoMiel;
                totalStock = totalStock + saldoMiel;
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return totalStock;
        
    }
    
    public Double calcularTotalStockPagoEnProductores() {

        Double totalStockPago = 0.00;

        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select codigo_locacion from locacion where categoria = 'DEPOSITO DE PRODUCTOR' order by codigo_locacion asc");

            double ingresoMielPaga, egresoMielPaga, saldoMiel = 0.00;
            int locacion = 0;
            
            StockRealMiel stock = new StockRealMiel();
            
            while (rs.next()) {
                
                locacion = rs.getInt("codigo_locacion");
                //deberia crear un metodo que devuelva la miel comprada pero facturada y/o presupuestada (que se toma como paga)
                ingresoMielPaga = stock.obtenerDetalleIngresoMielPaga(locacion);
                egresoMielPaga = stock.obtenerDetalleEgresoMielPaga(locacion);
                saldoMiel = ingresoMielPaga - egresoMielPaga;
                totalStockPago = totalStockPago + saldoMiel;
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return totalStockPago;
        
    }
    
    //sirve!
    public Double calcularTotalStockCreditoEnProductores() {

        Double totalStockImpago = 0.00;

        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select codigo_locacion from locacion where categoria = 'DEPOSITO DE PRODUCTOR' order by codigo_locacion asc");

            double ingresoMielImpaga, egresoMielImpaga, saldoMiel = 0.00;
            int locacion = 0;
            
            StockRealMiel stock = new StockRealMiel();
            
            while (rs.next()) {
                
                locacion = rs.getInt("codigo_locacion");
                //deberia crear un metodo que devuelva la miel comprada pero a credito / no facturada (que se toma como impaga)
                ingresoMielImpaga = stock.obtenerDetalleIngresoMielImpaga(locacion);
                egresoMielImpaga = stock.obtenerDetalleEgresoMielImpaga(locacion);
                saldoMiel = ingresoMielImpaga - egresoMielImpaga;
                totalStockImpago = totalStockImpago + saldoMiel;
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return totalStockImpago;
        
    }

    public Double calcularTotalStockEmbarque() {

        Double totalStockGlobalEmbarcado = 0.00;

        try {
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select codigo_locacion from locacion where categoria = 'EMBARQUE' order by codigo_locacion asc");

            double ingresoMiel, egresoMiel, ingresoMielPaga, egresoMielPaga, ingresoMielImpaga, egresoMielImpaga, saldoMiel = 0.00;
            int locacion = 0;
            
            StockRealMiel stock = new StockRealMiel();
            
            while (rs.next()) {
                
                locacion = rs.getInt("codigo_locacion");
                ingresoMiel = stock.obtenerDetalleIngresoMiel(locacion);
                egresoMiel = stock.obtenerDetalleEgresoMiel(locacion);
                //ingresoMielPaga = stock.obtenerDetalleMielComprada(locacion);
                //egresoMielPaga = stock.obtenerDetalleMielVendida(locacion);
                //ingresoMielImpaga = stock.obtenerDetalleMielRecibidaTraslado(locacion);
                //egresoMielImpaga = stock.obtenerDetalleMielEnviadaTraslado(locacion);
                saldoMiel = ingresoMiel - egresoMiel;
                totalStockGlobalEmbarcado = totalStockGlobalEmbarcado + saldoMiel;
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return totalStockGlobalEmbarcado;
        
    }

    public DefaultTableModel mostrarDetalleMovimientosStockLocacion(int codigoLocacion) {

        DefaultTableModel modelo;

        String[] titulos = {"FECHA", "REFERENCIA", "COMPROBANTE", "ID COMPROBANTE", "N° COMPROBANTE", "KGS. MIEL", "ID LOCACION", "LOCACION","ID PRODUCTOR", "PRODUCTOR", "ESTADO_MOVIMIENTO"};

        String[] registros = new String[11];

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
            ResultSet rs = st.executeQuery("select fecha_movimiento, tipo_movimiento, comprobante_asociado, id_comprobante_asociado, numero_comprobante_asociado, cantidad_miel, locacion_miel, miel_deposito_productor, estado_movimiento from stock_real_miel where locacion_miel='"+ codigoLocacion +"' order by codigo_movimiento asc");
            Locacion locacion = new Locacion();
            Productor productor = new Productor();
            FacturaProductor factura = new FacturaProductor();

            while (rs.next()) {
                
                registros[0] = rs.getString("fecha_movimiento");
                registros[1] = rs.getString("tipo_movimiento");
                registros[2] = rs.getString("comprobante_asociado");
                registros[3] = rs.getString("id_comprobante_asociado");
                registros[4] = rs.getString("numero_comprobante_asociado");
                registros[5] = rs.getString("cantidad_miel");
                registros[6] = rs.getString("locacion_miel");
                registros[7] = locacion.mostrarNombreLocacion(rs.getInt("locacion_miel"));
                registros[8] = rs.getString("miel_deposito_productor");
                if (rs.getInt("miel_deposito_productor") != 0){

                    registros[9] = productor.mostrarNombreProductor(rs.getInt("miel_deposito_productor"));
                    
                }
                registros[10] = rs.getString("estado_movimiento");
                
                modelo.addRow(registros);
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }

    public boolean actualizarEstadoMovimientoStock(String tipoMovimiento, String referencia, int idComprobante) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            PreparedStatement pst = cn.prepareStatement("UPDATE stock_real_miel SET estado_movimiento = ? WHERE tipo_movimiento = '"+ tipoMovimiento +"' and comprobante_asociado = '"+ referencia +"' and id_comprobante_asociado = '" + idComprobante + "'");

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
    
    /*public boolean actualizarEstadoMovimientoStock(String tipoMovimiento, String referencia, int idComprobante) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            PreparedStatement pst = cn.prepareStatement("UPDATE stock_real_miel SET estado_movimiento = ? WHERE tipo_movimiento = '"+ tipoMovimiento +"' and comprobante_asociado = '"+ referencia +"' and id_comprobante_asociado = '" + idComprobante + "'");

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
        
    }*/
    
}
