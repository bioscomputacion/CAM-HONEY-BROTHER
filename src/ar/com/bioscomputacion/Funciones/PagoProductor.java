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
    
}
