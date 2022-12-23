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
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlo
 */
public class AnulacionComprobanteProductor {
    
    private int codigo_anulacion;
    private Date fecha_anulacion;
    private String numero_comprobante;
    private String tipo_comprobante_anulado;
    private int codigo_comprobante_anulado;
    private String numero_comprobante_anulado;
    private Double importe_dinero_anulado; 
    private Double cantidad_miel_anulada;
    private String observacion;

    public AnulacionComprobanteProductor(Date fecha_anulacion, String numero_comprobante, String tipo_comprobante_anulado, int codigo_comprobante_anulado, String numero_comprobante_anulado, Double importe_dinero_anulado, Double cantidad_miel_anulada, String observacion) {
        
        this.fecha_anulacion = fecha_anulacion;
        this.numero_comprobante = numero_comprobante;
        this.tipo_comprobante_anulado = tipo_comprobante_anulado;
        this.codigo_comprobante_anulado = codigo_comprobante_anulado;
        this.numero_comprobante_anulado = numero_comprobante_anulado;
        this.importe_dinero_anulado = importe_dinero_anulado;
        this.cantidad_miel_anulada = cantidad_miel_anulada;
        this.observacion = observacion;
        
    }

    public AnulacionComprobanteProductor() {
    }

    public int getCodigo_anulacion() {
        return codigo_anulacion;
    }

    public void setCodigo_anulacion(int codigo_anulacion) {
        this.codigo_anulacion = codigo_anulacion;
    }

    public Date getFecha_anulacion() {
        return fecha_anulacion;
    }

    public void setFecha_anulacion(Date fecha_anulacion) {
        this.fecha_anulacion = fecha_anulacion;
    }

    public String getNumero_comprobante() {
        return numero_comprobante;
    }

    public void setNumero_comprobante(String numero_comprobante) {
        this.numero_comprobante = numero_comprobante;
    }

    public String getTipo_comprobante_anulado() {
        return tipo_comprobante_anulado;
    }

    public void setTipo_comprobante_anulado(String tipo_comprobante_anulado) {
        this.tipo_comprobante_anulado = tipo_comprobante_anulado;
    }

    public int getCodigo_comprobante_anulado() {
        return codigo_comprobante_anulado;
    }

    public void setCodigo_comprobante_anulado(int codigo_comprobante_anulado) {
        this.codigo_comprobante_anulado = codigo_comprobante_anulado;
    }

    public String getNumero_comprobante_anulado() {
        return numero_comprobante_anulado;
    }

    public void setNumero_comprobante_anulado(String numero_comprobante_anulado) {
        this.numero_comprobante_anulado = numero_comprobante_anulado;
    }

    public Double getImporte_dinero_anulado() {
        return importe_dinero_anulado;
    }

    public void setImporte_dinero_anulado(Double importe_dinero_anulado) {
        this.importe_dinero_anulado = importe_dinero_anulado;
    }

    public Double getCantidad_miel_anulada() {
        return cantidad_miel_anulada;
    }

    public void setCantidad_miel_anulada(Double cantidad_miel_anulada) {
        this.cantidad_miel_anulada = cantidad_miel_anulada;
    }
    
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int mostrarIdAnulacionComprobante() {

        int codigoAnulacion = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_anulacion FROM anulacion_comprobante_productor order by codigo_anulacion asc");
            
            while (rs.next()) {

                codigoAnulacion = rs.getInt("codigo_anulacion");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);

            return codigoAnulacion;

        }catch(Exception e){
            
            return codigoAnulacion;
        } 

    }

    public boolean registrarAnulacionComprobante(AnulacionComprobanteProductor anulacion){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            PreparedStatement pst = cn.prepareStatement("INSERT INTO anulacion_comprobante_productor (fecha_anulacion, numero_comprobante, tipo_comprobante_anulado, codigo_comprobante_anulado, numero_comprobante_anulado, importe_dinero_anulado, cantidad_miel_anulada, observacion) "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            
            
            pst.setDate(1, anulacion.getFecha_anulacion());
            pst.setString(2, anulacion.getNumero_comprobante());
            pst.setString(3, anulacion.getTipo_comprobante_anulado());
            pst.setInt(4, anulacion.getCodigo_comprobante_anulado());
            pst.setString(5, anulacion.getNumero_comprobante_anulado());
            pst.setDouble(6, anulacion.getImporte_dinero_anulado());
            pst.setDouble(7, anulacion.getCantidad_miel_anulada());
            pst.setString(8, anulacion.getObservacion());
            
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

    public DefaultTableModel listarAnulacionesDeComprobantes(LocalDate fechaInicial, LocalDate fechaFinal) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID ANULACION", "FECHA", "N° COMPROBANTE", "TIPO COMPROBANTE ANULADO", "COD COMPROBANTE ANULADO", "N° COMPROBANTE ANULADO", "IMPORTE", "KGS. MIEL", "OBSERVACION"};

        String[] registros = new String[9];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_anulacion, fecha_anulacion, numero_comprobante, tipo_comprobante_anulado, codigo_comprobante_anulado, numero_comprobante_anulado, importe_anulado, observacion from anulacion_comprobante_productor where codigo_anulacion <> '1' and fecha_anulacion BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' order by codigo_anulacion");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_anulacion");
                registros[1] = rs.getString("fecha_anulacion");
                registros[2] = rs.getString("numero_comprobante");
                registros[3] = rs.getString("tipo_comprobante_anulado");
                registros[4] = rs.getString("codigo_comprobante_anulado");
                registros[5] = rs.getString("numero_comprobante_anulado");
                registros[6] = rs.getString("importe_dinero_anulado");
                registros[7] = rs.getString("cantidad_miel_anulada");
                registros[8] = rs.getString("observacion");
                
                modelo.addRow(registros);
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            
        }
        
        return modelo;
        
    }
    
    public String mostrarObservacionAnulacionComprobante(int codigoAnulacion) {
        
        String observacion = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select observacion from anulacion_comprobante_productor where codigo_anulacion = '" + codigoAnulacion + "'");

            while (rs.next()){
            
                observacion = rs.getString("observacion");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return observacion;
            
        }
        
        return observacion;
    
    }
    
    public String mostrarTipoComprobanteAnulacionComprobante(int codigoAnulacion) {
        
        String tipoComprobante = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select tipo_comprobante_anulado from anulacion_comprobante_productor where codigo_anulacion = '" + codigoAnulacion + "'");

            while (rs.next()){
            
                tipoComprobante = rs.getString("tipo_comprobante_anulado");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return tipoComprobante;
            
        }
        
        return tipoComprobante;
    
    }
    
    public String mostrarNumeroComprobanteAnulacionComprobante(int codigoAnulacion) {
        
        String numeroComprobante = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select numero_comprobante_anulado from anulacion_comprobante_productor where codigo_anulacion = '" + codigoAnulacion + "'");

            while (rs.next()){
            
                numeroComprobante = rs.getString("numero_comprobante_anulado");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return numeroComprobante;
            
        }
        
        return numeroComprobante;
    
    }
    
}
