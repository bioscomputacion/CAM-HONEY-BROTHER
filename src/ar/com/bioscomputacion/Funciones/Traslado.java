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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caco
 */
public class Traslado {
    
    private int codigo_traslado;
    private String numero_comprobante;
    private String descripcion_item_trasladado; 
    private Double cantidad_item_trasladado;
    private String observacion_traslado;
    private int origen_traslado;
    private int destino_traslado;
    private Date fecha_traslado;
    
    public Traslado(String numero_comprobante, String descripcion_item_trasladado, Double cantidad_item_trasladado, String observacion_traslado, int origen_traslado, int destino_traslado, Date fecha_traslado) {
        
        this.numero_comprobante = numero_comprobante;
        this.descripcion_item_trasladado = descripcion_item_trasladado;
        this.cantidad_item_trasladado = cantidad_item_trasladado;
        this.observacion_traslado = observacion_traslado;
        this.origen_traslado = origen_traslado;
        this.destino_traslado = destino_traslado;
        this.fecha_traslado = fecha_traslado;
        
    }

    public Traslado() {
    }

    public int getCodigo_traslado() {
        return codigo_traslado;
    }

    public void setCodigo_traslado(int codigo_traslado) {
        this.codigo_traslado = codigo_traslado;
    }

    public String getNumero_comprobante() {
        return numero_comprobante;
    }

    public void setNumero_comprobante(String numero_comprobante) {
        this.numero_comprobante = numero_comprobante;
    }

    public String getDescripcion_item_trasladado() {
        return descripcion_item_trasladado;
    }

    public void setDescripcion_item_trasladado(String descripcion_item_trasladado) {
        this.descripcion_item_trasladado = descripcion_item_trasladado;
    }

    public Double getCantidad_item_trasladado() {
        return cantidad_item_trasladado;
    }

    public void setCantidad_item_trasladado(Double cantidad_item_trasladado) {
        this.cantidad_item_trasladado = cantidad_item_trasladado;
    }

    public String getObservacion_traslado() {
        return observacion_traslado;
    }

    public void setObservacion_traslado(String observacion_traslado) {
        this.observacion_traslado = observacion_traslado;
    }

    public int getOrigen_traslado() {
        return origen_traslado;
    }

    public void setOrigen_traslado(int origen_traslado) {
        this.origen_traslado = origen_traslado;
    }

    public int getDestino_traslado() {
        return destino_traslado;
    }

    public void setDestino_traslado(int destino_traslado) {
        this.destino_traslado = destino_traslado;
    }

    public Date getFecha_traslado() {
        return fecha_traslado;
    }

    public void setFecha_traslado(Date fecha_traslado) {
        this.fecha_traslado = fecha_traslado;
    }

    public int mostrarIdTraslado() {

        int codigoTraslado = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_traslado FROM traslado order by codigo_traslado asc");
            
            while (rs.next()) {

                codigoTraslado = rs.getInt("codigo_traslado");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
            return codigoTraslado;

        }catch(Exception e){
            
            return codigoTraslado;
        } 

    }
    
    public boolean registrarTrasladoMiel(Traslado traslado){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO traslado (numero_comprobante, descripcion_item_trasladado, cantidad_item_trasladado, observacion_traslado, origen_traslado, destino_traslado, fecha_traslado) "
                    + "VALUES (?,?,?,?,?,?,?)");
            
            
            pst.setString(1, traslado.getNumero_comprobante());
            pst.setString(2, traslado.getDescripcion_item_trasladado());
            pst.setDouble(3, traslado.getCantidad_item_trasladado());
            pst.setString(4, traslado.getObservacion_traslado());
            pst.setInt(5, traslado.getOrigen_traslado());
            pst.setInt(6, traslado.getDestino_traslado());
            pst.setDate(7, traslado.getFecha_traslado());
            
            
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
    
    public boolean modificarTrasladoMiel(Traslado traslado, int codigoTraslado) {

        try {


            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("UPDATE traslado SET numero_comprobante = ?,fecha_ingreso = ?,cantidad_miel = ?,observacion = ? WHERE codigo_traslado = '"+ codigoTraslado +"'");

            pst.setString(1, traslado.getNumero_comprobante());
            pst.setString(2, traslado.getDescripcion_item_trasladado());
            pst.setDouble(3, traslado.getCantidad_item_trasladado());
            pst.setString(4, traslado.getObservacion_traslado());
            pst.setInt(5, traslado.getOrigen_traslado());
            pst.setInt(6, traslado.getDestino_traslado());
            pst.setDate(7, traslado.getFecha_traslado());

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

    public boolean eliminarTrasladoMiel(int codigoTraslado) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("DELETE FROM traslado WHERE codigo_traslado = '"+ codigoTraslado +"'");

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

    public DefaultTableModel listarTraslados(String mesConsulta) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID TRASLADO", "FECHA", "N° COMPROBANTE", "MOTIVO", "KGS. TRASLADADOS", "LOCACION ORIGEN", "ORIGEN", "LOCACION DESTINO", "DESTINO"};

        String[] registros = new String[9];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_traslado, numero_comprobante, cantidad_item_trasladado, observacion_traslado, origen_traslado, destino_traslado, fecha_traslado from traslado where codigo_traslado <> '67' order by codigo_traslado");
            Locacion locacion = new Locacion();

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_traslado");
                registros[1] = rs.getString("fecha_traslado");
                registros[2] = rs.getString("numero_comprobante");
                registros[3] = rs.getString("observacion_traslado");
                registros[4] = rs.getString("cantidad_item_trasladado");
                registros[5] = rs.getString("origen_traslado");
                registros[7] = rs.getString("destino_traslado");
                int codigo_locacion_origen = rs.getInt("origen_traslado");
                int codigo_locacion_destino = rs.getInt("destino_traslado");
                registros[6] = locacion.mostrarNombreLocacion(codigo_locacion_origen);
                registros[8] = locacion.mostrarNombreLocacion(codigo_locacion_destino);
                
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
