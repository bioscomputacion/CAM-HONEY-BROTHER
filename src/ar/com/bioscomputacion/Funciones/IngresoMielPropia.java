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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carlo
 */
public class IngresoMielPropia {
    
    private int codigoIngreso;
    private String numeroComprobante; 
    private Date fechaIngreso;
    private Double cantidadMiel;
    private int locacionMiel;
    private String observacion;
    
    public int getCodigoIngreso() {
        return codigoIngreso;
    }

    public void setCodigoIngreso(int codigoIngreso) {
        this.codigoIngreso = codigoIngreso;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Double getCantidadMiel() {
        return cantidadMiel;
    }

    public void setCantidadMiel(Double cantidadMiel) {
        this.cantidadMiel = cantidadMiel;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    public int getLocacionMiel() {
        return locacionMiel;
    }

    public void setLocacionMiel(int locacionMiel) {
        this.locacionMiel = locacionMiel;
    }
    
    public IngresoMielPropia(String numeroComprobante, Date fechaIngreso, Double cantidadMiel, int locacionMiel, String observacion) {
        
        this.numeroComprobante = numeroComprobante;
        this.fechaIngreso = fechaIngreso;
        this.cantidadMiel = cantidadMiel;
        this.locacionMiel = locacionMiel;
        this.observacion = observacion;
        
    }

    public IngresoMielPropia() {
    }
    
    public int mostrarIdIngresoMielPropia() {

        int codigoIngresoMielPropia = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_ingreso FROM ingreso_miel_propia order by codigo_ingreso asc");
            
            while (rs.next()) {

                codigoIngresoMielPropia = rs.getInt("codigo_ingreso");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
            return codigoIngresoMielPropia;

        }catch(Exception e){
            
            return codigoIngresoMielPropia;
        } 

    }

    public boolean registrarIngresoMielPropia(IngresoMielPropia ingresoMiel){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO ingreso_miel_propia (numero_comprobante, fecha_ingreso, cantidad_miel, locacion_miel, observacion) "
                    + "VALUES (?,?,?,?,?)");
            
            
            pst.setString(1, ingresoMiel.getNumeroComprobante());
            pst.setDate(2, ingresoMiel.getFechaIngreso());
            pst.setDouble(3, ingresoMiel.getCantidadMiel());
            pst.setInt(4, ingresoMiel.getLocacionMiel());
            pst.setString(5, ingresoMiel.getObservacion());
            
            
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

    public boolean modificarIngresoMielPropia(IngresoMielPropia ingresoMiel, int codigoIngreso) {

        try {


            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("UPDATE ingreso_miel_propia SET numero_comprobante = ?,fecha_ingreso = ?,cantidad_miel = ?, locacion_miel = ?, observacion = ? WHERE codigo_ingreso = '"+ codigoIngreso +"'");

            pst.setString(1, ingresoMiel.getNumeroComprobante());
            pst.setDate(2, ingresoMiel.getFechaIngreso());
            pst.setDouble(3, ingresoMiel.getCantidadMiel());
            pst.setInt(4, ingresoMiel.getLocacionMiel());
            pst.setString(5, ingresoMiel.getObservacion());

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

    public boolean eliminarIngresoMielPropia(int codigoIngreso) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("DELETE FROM ingreso_miel_propia WHERE codigo_ingreso = '"+ codigoIngreso +"'");

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

    public DefaultTableModel listarIngresosMiel(LocalDate fechaInicial, LocalDate fechaFinal) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID INGRESO", "FECHA", "N° COMPROBANTE", "KGS. INGRESADOS", "ID LOCACION", "LOCACION"};

        String[] registros = new String[6];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT i.codigo_ingreso, i.fecha_ingreso, i.numero_comprobante, i.cantidad_miel, i.locacion_miel, l.nombre_locacion from ingreso_miel_propia i join locacion l on i.locacion_miel = l.codigo_locacion where codigo_ingreso <> '4' and i.fecha_ingreso BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' order by codigo_ingreso");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_ingreso");
                registros[1] = rs.getString("fecha_ingreso");
                registros[2] = rs.getString("numero_comprobante");
                registros[3] = rs.getString("cantidad_miel");
                registros[4] = rs.getString("locacion_miel");
                registros[5] = rs.getString("nombre_locacion");
                
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
    
    public String mostrarObservacionIngreso(int codigoIngreso) {
        
        String observacion = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select observacion from ingreso_miel_propia where codigo_ingreso = '" + codigoIngreso + "'");

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
    
    //Devuelve: devuelve locacion donde se encuentra depositada la miel comprada en el comprobante consultado
    public int obtenerLocacionMielIngreso(int codigoIngreso){
        
        int locacionMiel = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select locacion_miel from stock_real_miel where comprobante_asociado = 'INGRESO' and id_comprobante_asociado = " + codigoIngreso);
            
            while (rs.next()) {

                locacionMiel = rs.getInt("locacion_miel");
                
            }
            
            return locacionMiel;

        }catch(Exception e){
            
            return locacionMiel;
            
        } 
        
    }

    public String mostrarNumeroComprobanteIngreso(int codigoIngreso) {
        
        String numeroComprobante = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT numero_comprobante from ingreso_miel_propia WHERE codigo_ingreso = '" + codigoIngreso + "'");

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
    
    public Double mostrarCantidadMielIngreso(int codigoIngreso) {
        
        Double cantidadMielIngresada = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT cantidad_miel from ingreso_miel_propia WHERE codigo_ingreso = '" + codigoIngreso + "'");

            while (rs.next()){
            
                cantidadMielIngresada = rs.getDouble("cantidad_miel");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return cantidadMielIngresada;
            
        }
        
        return cantidadMielIngresada;
    
    }
    
    public Date mostrarFechaIngreso(int codigoIngreso) {
        
        Date fecha = Date.valueOf(LocalDate.now());

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT fecha_ingreso from ingreso_miel_propia WHERE codigo_ingreso = '" + codigoIngreso + "'");

            while (rs.next()){
            
                fecha = rs.getDate("fecha_ingreso");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return fecha;
            
        }
        
        return fecha;
    
    }
    
}
