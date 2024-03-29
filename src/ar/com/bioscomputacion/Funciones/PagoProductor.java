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
import java.time.LocalDate;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caco
 */
public class PagoProductor {
    
    private int codigo_pago;
    private int codigo_movimiento_ctacte;
    private int codigo_productor;
    private Date fecha_pago;
    private String metodoPago;
    private String observacion;
    private Double monto_pago;
    private int codigo_comprobante_pagado;
    private String tipo_comprobante_pagado;
    
    public PagoProductor(int codigo_movimiento_ctacte, int codigo_productor, Date fecha_pago, String metodo_pago, String observacion, Double monto_pago, int codigo_comprobante_pagado, String tipo_comprobante_pagado) {
        
        this.codigo_movimiento_ctacte = codigo_movimiento_ctacte;
        this.codigo_productor = codigo_productor;
        this.fecha_pago = fecha_pago;
        this.metodoPago = metodo_pago;
        this.observacion = observacion;
        this.monto_pago = monto_pago;
        this.codigo_comprobante_pagado = codigo_comprobante_pagado;
        this.tipo_comprobante_pagado = tipo_comprobante_pagado;
        
    }

    public PagoProductor() {
        
    }

    public int getCodigo_pago() {
        return codigo_pago;
    }

    public void setCodigo_pago(int codigo_pago) {
        this.codigo_pago = codigo_pago;
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

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Double getMonto_pago() {
        return monto_pago;
    }

    public void setMonto_pago(Double monto_pago) {
        this.monto_pago = monto_pago;
    }

    public int getCodigo_comprobante_pagado() {
        return codigo_comprobante_pagado;
    }

    public void setCodigo_comprobante_pagado(int codigo_comprobante_pagado) {
        this.codigo_comprobante_pagado = codigo_comprobante_pagado;
    }

    public String getTipo_comprobante_pagado() {
        return tipo_comprobante_pagado;
    }

    public void setTipo_comprobante_pagado(String tipo_comprobante_pagado) {
        this.tipo_comprobante_pagado = tipo_comprobante_pagado;
    }
    
    public boolean registrarPagoProductor(PagoProductor pagoProductor){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO pago_productor (codigo_movimiento_ctacte, codigo_productor, fecha_pago, metodo_pago, observacion, monto_pago, codigo_comprobante_pagado, tipo_comprobante_pagado) "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            
            
            pst.setInt(1, pagoProductor.getCodigo_movimiento_ctacte());
            pst.setInt(2, pagoProductor.getCodigo_productor());
            pst.setDate(3, pagoProductor.getFecha_pago());
            pst.setString(4, pagoProductor.getMetodoPago());
            pst.setString(5, pagoProductor.getObservacion());
            pst.setDouble(6, pagoProductor.getMonto_pago());
            pst.setDouble(7, pagoProductor.getCodigo_comprobante_pagado());
            pst.setString(8, pagoProductor.getTipo_comprobante_pagado());
            
            
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
    
    public int mostrarIdPagoAProductor() {

        int codigoPagoAProductor = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_pago FROM pago_productor order by codigo_pago asc");
            
            while (rs.next()) {

                codigoPagoAProductor = rs.getInt("codigo_pago");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
            return codigoPagoAProductor;

        }catch(Exception e){
            
            return codigoPagoAProductor;
        } 

    }
    
    public DefaultTableModel listarPagosAProductores(LocalDate fechaInicial, LocalDate fechaFinal) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID PAGO", "FECHA", "COMPROBANTE_ASOCIADO", "TIPO COMPROBANTE ABONADO", "ID PRODUCTOR", "PRODUCTOR", "IMPORTE"};

        String[] registros = new String[7];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.codigo_pago, p.fecha_pago, p.codigo_comprobante_pagado, p.tipo_comprobante_pagado, p.codigo_productor, s.nombre, p.monto_pago from pago_productor p join productor r on p.codigo_productor = r.cod_productor join persona s on r.cod_persona = s.cod_persona WHERE codigo_pago <> '5' and fecha_pago BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' ORDER BY codigo_pago");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_pago");
                registros[1] = rs.getString("fecha_pago");
                registros[2] = rs.getString("codigo_comprobante_pagado");
                registros[3] = rs.getString("tipo_comprobante_pagado");
                registros[4] = rs.getString("codigo_productor");
                registros[5] = rs.getString("nombre");
                registros[6] = rs.getString("monto_pago");
                
                //ver como puedo traer los datos del comprobante abonado por el pago en cuestion

                modelo.addRow(registros);
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            System.out.println("error");
            
        }
        
        return modelo;
        
    }
    
    public String mostrarNombreProductorPago(int codigoPago) {
        
        String nombreProductor = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = '" + codigoPago + "' and descripcion_movimiento = 'PAGO'");

            while (rs.next()){
            
                nombreProductor = rs.getString("nombre");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return nombreProductor;
            
        }
        
        return nombreProductor;
    
    }
    
    public int mostrarCodigoProductorPago(int codigoPago) {
        
        int codigoProductor = 0;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select codigo_productor from pago_productor where codigo_pago = '" + codigoPago + "'");

            while (rs.next()){
            
                codigoProductor = rs.getInt("codigo_productor");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return codigoProductor;
            
        }
        
        return codigoProductor;
    
    }
    
    public String mostrarNumeroFacturaAfectadoPago(int codigoPago) {
        
        String numeroComprobante = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT f.numero_comprobante from pago_productor p join factura_productor f on p.codigo_comprobante_pagado = f.codigo_factura where p.codigo_pago = '"+ codigoPago +"'");

            while (rs.next()){
            
                numeroComprobante = rs.getString("numero_comprobante");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return numeroComprobante;
            
        }
        
        return numeroComprobante;
    
    }
    
    public String mostrarNumeroPresupuestoAfectadoPago(int codigoPago) {
        
        String numeroComprobante = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT f.numero_comprobante from pago_productor p join presupuesto_productor f on p.codigo_comprobante_pagado = f.codigo_presupuesto where p.codigo_pago = '"+ codigoPago +"'");

            while (rs.next()){
            
                numeroComprobante = rs.getString("numero_comprobante");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return numeroComprobante;
            
        }
        
        return numeroComprobante;
    
    }
    
    public int mostrarCodigoComprobanteAfectadoPago(int codigoPago) {
        
        int codigoComprobante = 0;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_comprobante_pagado from pago_productor where codigo_pago = '"+ codigoPago +"'");

            while (rs.next()){
            
                codigoComprobante = rs.getInt("codigo_comprobante_pagado");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return codigoComprobante;
            
        }
        
        return codigoComprobante;
    
    }
    
    public String mostrarTipoComprobanteAfectadoPago(int codigoPago) {
        
        String tipoComprobante = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT tipo_comprobante_pagado from pago_productor where codigo_pago = '"+ codigoPago +"'");

            while (rs.next()){
            
                tipoComprobante = rs.getString("tipo_comprobante_pagado");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return tipoComprobante;
            
        }
        
        return tipoComprobante;
    
    }
    
    public Double mostrarImportePago(int codigoPago) {
        
        Double importePago = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT monto_pago from pago_productor WHERE codigo_pago = '" + codigoPago + "'");

            while (rs.next()){
            
                importePago = rs.getDouble("monto_pago");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return importePago;
            
        }
        
        return importePago;
    
    }
    
    public Date mostrarFechaPago(int codigoPago) {
        
        Date fecha = Date.valueOf(LocalDate.now());

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT fecha_pago from pago_productor WHERE codigo_pago = '" + codigoPago + "'");

            while (rs.next()){
            
                fecha = rs.getDate("fecha_pago");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return fecha;
            
        }
        
        return fecha;
    
    }
    
    public int mostrarCodigoMovimientoEnCtaCtePagoProductor(int codigoPago) {
        
        int codigoMovimientoCtaCte = 0;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select codigo_movimiento from cta_cte_productor where descripcion_movimiento = 'PAGO' and comprobante_asociado = '" + codigoPago + "'");

            while (rs.next()){
            
                codigoMovimientoCtaCte = rs.getInt("codigo_movimiento");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return codigoMovimientoCtaCte;
            
        }
        
        return codigoMovimientoCtaCte;
    
    }
    
}
