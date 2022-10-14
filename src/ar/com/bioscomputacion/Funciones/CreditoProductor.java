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
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author carlo
 */
public class CreditoProductor {

    private int codigo_credito;
    private String numero_comprobante; 
    private int codigo_movimiento_ctacte;
    private int codigo_productor;
    private Date fecha_credito;
    private Date fecha_vencimiento;
    private Double cantidad_miel;
    
    public CreditoProductor(String numero_comprobante, int codigo_movimiento_ctacte, int codigo_productor, Date fecha_credito, Date fecha_vencimiento, Double cantidad_miel) {

        this.numero_comprobante = numero_comprobante;
        this.codigo_movimiento_ctacte = codigo_movimiento_ctacte;
        this.codigo_productor = codigo_productor;
        this.fecha_credito = fecha_credito;
        this.fecha_vencimiento = fecha_vencimiento;
        this.cantidad_miel = cantidad_miel;
        
    }

    public CreditoProductor() {
    }

    public int getCodigo_credito() {
        return codigo_credito;
    }

    public void setCodigo_credito(int codigo_credito) {
        this.codigo_credito = codigo_credito;
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

    public Date getFecha_credito() {
        return fecha_credito;
    }

    public void setFecha_credito(Date fecha_credito) {
        this.fecha_credito = fecha_credito;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Double getCantidad_miel() {
        return cantidad_miel;
    }

    public void setCantidad_miel(Double cantidad_miel) {
        this.cantidad_miel = cantidad_miel;
    }

    public int mostrarIdCreditoProductor() {

        int codigoCreditoProductor = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_credito FROM credito_productor order by codigo_credito asc");
            
            while (rs.next()) {

                codigoCreditoProductor = rs.getInt("codigo_credito");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
            return codigoCreditoProductor;

        }catch(Exception e){
            
            return codigoCreditoProductor;
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

    public boolean registrarCreditoProductor(CreditoProductor creditoProductor){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO credito_productor (numero_comprobante, codigo_movimiento_ctacte, codigo_productor, fecha_credito, fecha_vencimiento, cantidad_miel) "
                    + "VALUES (?,?,?,?,?,?)");
            
            
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

    public DefaultTableModel listarConsignacionesDeProductores(LocalDate fechaInicial, LocalDate fechaFinal) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID CONSIGNACION", "FECHA", "N° COMPROBANTE", "ID PRODUCTOR", "PRODUCTOR", "KGS. MIEL"};

        String[] registros = new String[6];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT f.codigo_credito, f.numero_comprobante, f.fecha_credito, f.codigo_productor, o.nombre, f.cantidad_miel from credito_productor f join productor p on f.codigo_productor = p.cod_productor join persona o on p.cod_persona = o.cod_persona  WHERE f.codigo_credito <> '35' and f.fecha_credito BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' ORDER BY f.codigo_credito");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_credito");
                registros[1] = rs.getString("fecha_credito");
                registros[2] = rs.getString("numero_comprobante");
                registros[3] = rs.getString("codigo_productor");
                registros[4] = rs.getString("nombre");
                registros[5] = rs.getString("cantidad_miel");
                //ver como cargo la locacion donde se acopio la miel facturada en el comprobante
                //registros[6] = rs.getString("");

                modelo.addRow(registros);
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }
    
    public String mostrarNombreProductorCredito(int codigoCredito) {
        
        String nombreProductor = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = '" + codigoCredito + "' and descripcion_movimiento = 'CONSIGNACION'");

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
