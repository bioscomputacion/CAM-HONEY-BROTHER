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
public class AnulacionPresupuestoProductor {
    
    private int codigo_anulacion;
    private String numero_comprobante; 
    private int codigo_movimiento_cta_cte;
    private int codigo_productor;
    private Date fecha_anulacion;
    private Double importe_total_anulacion;
    private Double cantidad_miel_afectada;

    public AnulacionPresupuestoProductor(String numero_comprobante, int codigo_movimiento_cta_cte, int codigo_productor, Date fecha_anulacion, Double importe_total_anulacion, Double cantidad_miel_afectada) {
        
        this.numero_comprobante = numero_comprobante;
        this.codigo_movimiento_cta_cte = codigo_movimiento_cta_cte;
        this.codigo_productor = codigo_productor;
        this.fecha_anulacion = fecha_anulacion;
        this.importe_total_anulacion = importe_total_anulacion;
        this.cantidad_miel_afectada = cantidad_miel_afectada;
        
    }

    public int getCodigo_anulacion() {
        return codigo_anulacion;
    }

    public void setCodigo_anulacion(int codigo_anulacion) {
        this.codigo_anulacion = codigo_anulacion;
    }

    public String getNumero_comprobante() {
        return numero_comprobante;
    }

    public void setNumero_comprobante(String numero_comprobante) {
        this.numero_comprobante = numero_comprobante;
    }

    public int getCodigo_movimiento_cta_cte() {
        return codigo_movimiento_cta_cte;
    }

    public void setCodigo_movimiento_cta_cte(int codigo_movimiento_cta_cte) {
        this.codigo_movimiento_cta_cte = codigo_movimiento_cta_cte;
    }

    public int getCodigo_productor() {
        return codigo_productor;
    }

    public void setCodigo_productor(int codigo_productor) {
        this.codigo_productor = codigo_productor;
    }

    public Date getFecha_anulacion() {
        return fecha_anulacion;
    }

    public void setFecha_anulacion(Date fecha_anulacion) {
        this.fecha_anulacion = fecha_anulacion;
    }

    public Double getImporte_total_anulacion() {
        return importe_total_anulacion;
    }

    public void setImporte_total_anulacion(Double importe_total_anulacion) {
        this.importe_total_anulacion = importe_total_anulacion;
    }

    public Double getCantidad_miel_afectada() {
        return cantidad_miel_afectada;
    }

    public void setCantidad_miel_afectada(Double cantidad_miel_afectada) {
        this.cantidad_miel_afectada = cantidad_miel_afectada;
    }

    public AnulacionPresupuestoProductor() {
    }
    
    public int mostrarIdAnulacionPresupuestoProductor() {

        int codigoAnulacion = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_anulacion FROM anulacion_presupuesto_productor order by codigo_anulacion asc");
            
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

    public boolean registrarAnulacionPresupuestoProductor(AnulacionPresupuestoProductor anulacionPresupuesto){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            PreparedStatement pst = cn.prepareStatement("INSERT INTO anulacion_presupuesto_productor (numero_comprobante, codigo_movimiento_cta_cte, codigo_productor, fecha_anulacion, importe_total_anulacion, cantidad_miel_afectada) "
                    + "VALUES (?,?,?,?,?,?)");
            
            
            pst.setString(1, anulacionPresupuesto.getNumero_comprobante());
            pst.setInt(2, anulacionPresupuesto.getCodigo_movimiento_cta_cte());
            pst.setInt(3, anulacionPresupuesto.getCodigo_productor());
            pst.setDate(4, anulacionPresupuesto.getFecha_anulacion());
            pst.setDouble(5, anulacionPresupuesto.getImporte_total_anulacion());
            pst.setDouble(6, anulacionPresupuesto.getCantidad_miel_afectada());
            
            
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

    public boolean modificarAnulacionPresupuestoProductor(AnulacionPresupuestoProductor anulacionPresupuesto, int codigoAnulacion) {

        /*try {

            PreparedStatement pst = cn.prepareStatement("UPDATE factura_productor SET tipo_factura = ?,numero_comprobante = ?,codigo_movimiento_ctacte = ?,codigo_productor = ?,fecha_factura = ?,fecha_vencimiento = ?,importe_total_factura = ?,cantidad_miel_facturada = ? WHERE codigo_factura = '"+ codigoFactura +"'");

            pst.setString(1, facturaProductor.getNumero_comprobante());
            pst.setString(2, facturaProductor.getTipoFactura());
            pst.setInt(3, facturaProductor.getCodigo_movimiento_ctacte());
            pst.setInt(4, facturaProductor.getCodigo_productor());
            pst.setDate(5, facturaProductor.getFecha_factura());
            pst.setDouble(7, facturaProductor.getImporte_total_factura());
            pst.setDouble(8, facturaProductor.getCantidad_miel());

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

        return false;*/
        return false;
    }

    public boolean eliminarAnulacionPresupuestoProductor(int codigoAnulacion) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("DELETE FROM anulacion_presupuesto_productor WHERE codigo_anulacion = '"+ codigoAnulacion +"'");

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

    public DefaultTableModel listarAnulacionesPresupuestosProductores(String mesConsulta) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID ANULACION", "FECHA", "N° COMPROBANTE", "ID PRODUCTOR", "PRODUCTOR","IMPORTE", "KGS. MIEL"};

        String[] registros = new String[7];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT f.codigo_anulacion, f.numero_comprobante, f.fecha_anulacion, f.codigo_productor, o.nombre, f.importe_total_anulacion, f.cantidad_miel_afectada from anulacion_presupuesto_productor f join productor p on f.codigo_productor = p.cod_productor join persona o on p.cod_persona = o.cod_persona  WHERE f.codigo_anulacion <> '0' and f.fecha_anulacion BETWEEN '2022-09-01' AND '2022-09-30' ORDER BY f.codigo_anulacion");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_anulacion");
                registros[1] = rs.getString("fecha_anulacion");
                registros[2] = rs.getString("numero_comprobante");
                registros[3] = rs.getString("codigo_productor");
                registros[4] = rs.getString("nombre");
                registros[5] = rs.getString("importe_total_anulacion");
                registros[6] = rs.getString("cantidad_miel_afectada");
                //ver como cargo la locacion donde se acopio la miel facturada en el comprobante
                //registros[6] = rs.getString("");

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
    
    public Double mostrarImporteAnulacionPresupuestoProductor(int codigoAnulacion) {
        
        Double importeAnulacion = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT importe_total_anulacion from anulacion_presupuesto_productor WHERE codigo_anulacion = '" + codigo_anulacion + "'");

            while (rs.next()){
            
                importeAnulacion = rs.getDouble("importe_total_anulacion");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return importeAnulacion;
            
        }
        
        return importeAnulacion;
    
    }
    
    public Double mostrarPrecioUnitarioAnulacionPresupuestoProductor(int codigoAnulacion) {
        
        Double importeAnulacion = 0.00;
        Double cantidadMielAfectada = 0.00;
        Double precioUnitarioFacturado = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT importe_total_anulacion, cantidad_miel_afectada from anulacion_presupuesto_productor WHERE codigo_anulacion = '" + codigoAnulacion + "'");

            while (rs.next()){
            
                importeAnulacion = rs.getDouble("importe_total_anulacion");
                cantidadMielAfectada = rs.getDouble("cantidad_miel_afectada");
                precioUnitarioFacturado = importeAnulacion / cantidadMielAfectada;
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return precioUnitarioFacturado;
            
        }
        
        return precioUnitarioFacturado;
    
    }
    
    public String mostrarNombreProductorAnulacionPresupuestoProductor(int codigoAnulacion) {
        
        String nombreProductor = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = '" + codigoAnulacion + "' and descripcion_movimiento = 'ANULACION'");

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