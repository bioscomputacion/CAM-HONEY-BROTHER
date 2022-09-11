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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlo
 */
public class PagoCliente {
    
    private int codigo_pago;
    private int codigo_movimiento_ctacte;
    private int codigo_cliente;
    private Date fecha_pago;
    private String metodoPago;
    private String observacion;
    private Double monto_pago;
    private int codigo_comprobante_pagado;
    private String tipo_comprobante_pagado;
    
    public PagoCliente(int codigo_movimiento_ctacte, int codigo_cliente, Date fecha_pago, String metodoPago, String observacion, Double monto_pago, int codigo_comprobante_pagado, String tipo_comprobante_pagado) {
        
        this.codigo_movimiento_ctacte = codigo_movimiento_ctacte;
        this.codigo_cliente = codigo_cliente;
        this.fecha_pago = fecha_pago;
        this.metodoPago = metodoPago;
        this.observacion = observacion;
        this.monto_pago = monto_pago;
        this.codigo_comprobante_pagado = codigo_comprobante_pagado;
        this.tipo_comprobante_pagado = tipo_comprobante_pagado;
        
    }

    public PagoCliente() {
        
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

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
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
    
    public boolean registrarPagoCliente(PagoCliente pagoCliente){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO pago_cliente (codigo_movimiento_ctacte, codigo_cliente, fecha_pago, metodo_pago, observacion, monto_pago, codigo_comprobante_pagado, tipo_comprobante_pagado) "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            
            
            pst.setInt(1, pagoCliente.getCodigo_movimiento_ctacte());
            pst.setInt(2, pagoCliente.getCodigo_cliente());
            pst.setDate(3, pagoCliente.getFecha_pago());
            pst.setString(4, pagoCliente.getMetodoPago());
            pst.setString(5, pagoCliente.getObservacion());
            pst.setDouble(6, pagoCliente.getMonto_pago());
            pst.setDouble(7, pagoCliente.getCodigo_comprobante_pagado());
            pst.setString(8, pagoCliente.getTipo_comprobante_pagado());
            
            
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
    
    public int mostrarIdPagoACliente() {

        int codigoPagoDeCliente = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_pago FROM pago_cliente order by codigo_pago asc");
            
            while (rs.next()) {

                codigoPagoDeCliente = rs.getInt("codigo_pago");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
            return codigoPagoDeCliente;

        }catch(Exception e){
            
            return codigoPagoDeCliente;
        } 

    }
    
    public String mostrarNombreClientePago(int codigoPago) {
        
        String nombreCliente = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select r.nombre from cta_cte_cliente c join cliente p on c.cod_cliente = p.cod_cliente JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = '" + codigoPago + "' and descripcion_movimiento = 'PAGO'");

            while (rs.next()){
            
                nombreCliente = rs.getString("nombre");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return nombreCliente;
            
        }
        
        return nombreCliente;
    
    }
    
    public DefaultTableModel listarPagosAClientes(String mesConsulta) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID PAGO", "FECHA", "COMPROBANTE_ASOCIADO", "TIPO COMPROBANTE ABONADO", "ID CLIENTE", "PRODUCTOR", "IMPORTE"};

        String[] registros = new String[7];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT p.codigo_pago, p.fecha_pago, p.codigo_comprobante_pagado, p.tipo_comprobante_pagado, p.codigo_cliente, s.nombre, p.monto_pago from pago_cliente p join cliente r on p.codigo_cliente = r.cod_cliente join persona s on r.cod_persona = s.cod_persona WHERE codigo_pago <> '1' and (fecha_pago BETWEEN '2022-09-01' AND '2022-09-30') ORDER BY codigo_pago");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_pago");
                registros[1] = rs.getString("fecha_pago");
                registros[2] = rs.getString("codigo_comprobante_pagado");
                registros[3] = rs.getString("tipo_comprobante_pagado");
                registros[4] = rs.getString("codigo_cliente");
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
    
}
