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
 * @author carlo
 */
public class DevolucionProductor {
    
    private int codigo_devolucion;
    private String numero_comprobante; 
    private int codigo_movimiento_ctacte;
    private int codigo_productor;
    private Date fecha_devolucion;
    private Double cantidad_miel;
    
    public DevolucionProductor(String numero_comprobante, int codigo_movimiento_ctacte, int codigo_productor, Date fecha_devolucion, Double cantidad_miel) {

        this.numero_comprobante = numero_comprobante;
        this.codigo_movimiento_ctacte = codigo_movimiento_ctacte;
        this.codigo_productor = codigo_productor;
        this.fecha_devolucion = fecha_devolucion;
        this.cantidad_miel = cantidad_miel;
        
    }

    public int getCodigo_devolucion() {
        return codigo_devolucion;
    }

    public void setCodigo_devolucion(int codigo_devolucion) {
        this.codigo_devolucion = codigo_devolucion;
    }

    public String getNumero_comprobante() {
        return numero_comprobante;
    }

    public void setNumero_comprobante(String numero_comprobante) {
        this.numero_comprobante = numero_comprobante;
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

    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public Double getCantidad_miel() {
        return cantidad_miel;
    }

    public void setCantidad_miel(Double cantidad_miel) {
        this.cantidad_miel = cantidad_miel;
    }

    public DevolucionProductor() {
    }
    
    public int mostrarIdDevolucionProductor() {

        int codigoDevolucionProductor = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_devolucion FROM devolucion_productor order by codigo_devolucion asc");
            
            while (rs.next()) {

                codigoDevolucionProductor = rs.getInt("codigo_devolucion");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
            return codigoDevolucionProductor;

        }
        catch(Exception e){
            
            return codigoDevolucionProductor;
            
        } 

    }

    public int mostrarIdItemAFacturarACredito(int codigoCredito) {

        int codigoItemAFacturarACredito = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_item_facturado FROM items_facturados_factura_productor where codigo_factura='"+ codigoCredito +"'");
            
            while (rs.next()) {

                codigoItemAFacturarACredito = rs.getInt("codigo_item_facturado");

            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
            return codigoItemAFacturarACredito;

        }catch(Exception e){
            
            return codigoItemAFacturarACredito;
        } 

    }

    public boolean registrarDevolucionProductor(DevolucionProductor devolucionProductor){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO devolucion_productor (numero_comprobante, codigo_movimiento_ctacte, codigo_productor, fecha_devolucion, cantidad_miel) "
                    + "VALUES (?,?,?,?,?)");
            
            
            pst.setString(1, devolucionProductor.getNumero_comprobante());
            pst.setInt(2, devolucionProductor.getCodigo_movimiento_ctacte());
            pst.setInt(3, devolucionProductor.getCodigo_productor());
            pst.setDate(4, devolucionProductor.getFecha_devolucion());
            pst.setDouble(5, devolucionProductor.getCantidad_miel());
            
            
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

    public boolean modificarCreditoProductor(CreditoProductor creditoProductor, int codigoCredito) {

        try {


            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("UPDATE credito_productor SET numero_comprobante = ?,codigo_movimiento_ctacte = ?,codigo_productor = ?,fecha_credito = ?,fecha_vencimiento = ?,cantidad_miel = ? WHERE codigo_credito = '"+ codigoCredito +"'");

            pst.setString(1, creditoProductor.getNumero_comprobante());
            pst.setInt(2, creditoProductor.getCodigo_movimiento_ctacte());
            pst.setInt(3, creditoProductor.getCodigo_productor());
            pst.setDate(4, creditoProductor.getFecha_credito());
            pst.setDate(5, creditoProductor.getFecha_vencimiento());
            pst.setDouble(6, creditoProductor.getCantidad_miel());

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

    public boolean eliminarCreditoProductor(int codigoCredito) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("DELETE FROM credito_productor WHERE codigo_credito = '"+ codigoCredito +"'");

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
    
    public DefaultTableModel listarDevolucionesAProductores(String mesConsulta) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID DEVOLUCION", "FECHA"};

        String[] registros = new String[2];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_devolucion, fecha_devolucion from devolucion_productor WHERE codigo_devolucion <> '1' and fecha_devolucion BETWEEN '2022-09-01' AND '2022-09-30' ORDER BY codigo_devolucion");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_devolucion");
                registros[1] = rs.getString("fecha_devolucion");

                modelo.addRow(registros);
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }
    
    public String mostrarNombreProductorDevolucion(int codigoDevolucion) {
        
        String nombreProductor = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = '" + codigoDevolucion + "' and descripcion_movimiento = 'DEVOLUCION'");

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
