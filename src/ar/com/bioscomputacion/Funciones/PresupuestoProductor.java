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

/**
 *
 * @author Caco
 */
public class PresupuestoProductor {

    private int codigoPresupuesto;
    private String numeroComprobante; 
    private int codigoMovimientoCtacte;
    private int codigoProductor;
    private Date fechaPresupuesto;
    private Date fechaVencimiento;
    private Double importeTotalPresupuesto;
    private Double cantidadMiel;
    
    public PresupuestoProductor(String numeroComprobante, int codigoMovimientoCtacte, int codigoProductor, Date fechaPresupuesto, Date fechaVencimiento, Double importeTotalPresupuesto, Double cantidadMiel) {
        
        this.numeroComprobante = numeroComprobante;
        this.codigoMovimientoCtacte = codigoMovimientoCtacte;
        this.codigoProductor = codigoProductor;
        this.fechaPresupuesto = fechaPresupuesto;
        this.fechaVencimiento = fechaVencimiento;
        this.importeTotalPresupuesto = importeTotalPresupuesto;
        this.cantidadMiel = cantidadMiel;
        
    }

    public PresupuestoProductor() {
    }

    public int getCodigoPresupuesto() {
        return codigoPresupuesto;
    }

    public void setCodigoPresupuesto(int codigoPresupuesto) {
        this.codigoPresupuesto = codigoPresupuesto;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public int getCodigoMovimientoCtacte() {
        return codigoMovimientoCtacte;
    }

    public void setCodigoMovimientoCtacte(int codigoMovimientoCtacte) {
        this.codigoMovimientoCtacte = codigoMovimientoCtacte;
    }

    public int getCodigoProductor() {
        return codigoProductor;
    }

    public void setCodigoProductor(int codigoProductor) {
        this.codigoProductor = codigoProductor;
    }

    public Date getFechaPresupuesto() {
        return fechaPresupuesto;
    }

    public void setFechaPresupuesto(Date fechaPresupuesto) {
        this.fechaPresupuesto = fechaPresupuesto;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Double getImporteTotalPresupuesto() {
        return importeTotalPresupuesto;
    }

    public void setImporteTotalPresupuesto(Double importeTotalPresupuesto) {
        this.importeTotalPresupuesto = importeTotalPresupuesto;
    }

    public Double getCantidadMiel() {
        return cantidadMiel;
    }

    public void setCantidadMiel(Double cantidadMiel) {
        this.cantidadMiel = cantidadMiel;
    }
    
    public int mostrarIdPresupuestoProductor() {

        int codigoPresupuestoProductor = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_presupuesto FROM presupuesto_productor order by codigo_presupuesto asc");
            
            while (rs.next()) {

                codigoPresupuestoProductor = rs.getInt("codigo_presupuesto");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
            return codigoPresupuestoProductor;

        }
        catch(Exception e){
            
            return codigoPresupuestoProductor;
        } 

    }

    public int mostrarIdItemAPresupuestar(int codigoPresupuesto) {

        int codigoItemAPresupuestar = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_item_presupuestado FROM items_presupuestados_presupuesto_productor where codigo_presupuesto='"+ codigoPresupuesto +"'");
            
            while (rs.next()) {

                codigoItemAPresupuestar = rs.getInt("codigo_item_presupuestado");
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
            return codigoItemAPresupuestar;

        }
        catch(Exception e){
            
            return codigoItemAPresupuestar;
        } 

    }

    public boolean registrarPresupuestoProductor(PresupuestoProductor presupuestoProductor){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO presupuesto_productor (numero_comprobante, codigo_movimiento_ctacte, codigo_productor, fecha_presupuesto, fecha_vencimiento, importe_total_presupuesto, cantidad_miel) "
                    + "VALUES (?,?,?,?,?,?,?)");
            
            
            pst.setString(1, presupuestoProductor.getNumeroComprobante());
            pst.setInt(2, presupuestoProductor.getCodigoMovimientoCtacte());
            pst.setInt(3, presupuestoProductor.getCodigoProductor());
            pst.setDate(4, presupuestoProductor.getFechaPresupuesto());
            pst.setDate(5, presupuestoProductor.getFechaVencimiento());
            pst.setDouble(6, presupuestoProductor.getImporteTotalPresupuesto());
            pst.setDouble(7, presupuestoProductor.getCantidadMiel());
            
            
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

    public boolean modificarPresupuestoProductor(PresupuestoProductor presupuestoProductor, int codigoPresupuesto) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("UPDATE presupuesto_productor SET numero_comprobante = ?,codigo_movimiento_ctacte = ?,codigo_productor = ?,fecha_presupuesto = ?,fecha_vencimiento = ?,importe_total_presupuesto = ?,cantidad_miel = ? WHERE codigo_presupuesto = '"+ codigoPresupuesto +"'");

            pst.setString(1, presupuestoProductor.getNumeroComprobante());
            pst.setInt(2, presupuestoProductor.getCodigoMovimientoCtacte());
            pst.setInt(3, presupuestoProductor.getCodigoProductor());
            pst.setDate(4, presupuestoProductor.getFechaPresupuesto());
            pst.setDate(5, presupuestoProductor.getFechaVencimiento());
            pst.setDouble(6, presupuestoProductor.getImporteTotalPresupuesto());
            pst.setDouble(7, presupuestoProductor.getCantidadMiel());

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
    
    public boolean eliminarPresupuestoProductor(int codigoPresupuesto) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("DELETE FROM presupuesto_productor WHERE codigo_presupuesto = '"+ codigoPresupuesto +"'");

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

    public Double mostrarImportePresupuesto(int codigoPresupuesto) {
        
        Double importePresupuesto = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT importe_total_presupuesto from presupuesto_productor WHERE codigo_presupuesto = '" + codigoPresupuesto + "'");

            while (rs.next()){
            
                importePresupuesto = rs.getDouble("importe_total_presupuesto");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return importePresupuesto;
            
        }
        
        return importePresupuesto;
    
    }
    
    public Double mostrarPrecioUnitarioPresupuesto(int codigoPresupuesto) {
        
        Double importePresupuesto = 0.00;
        Double cantidadMielPresupuestada = 0.00;
        Double precioUnitarioPresupuestado = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT importe_total_presupuesto, cantidad_miel from presupuesto_productor WHERE codigo_presupuesto = '" + codigoPresupuesto + "'");

            while (rs.next()){
            
                importePresupuesto = rs.getDouble("importe_total_presupuesto");
                cantidadMielPresupuestada = rs.getDouble("cantidad_miel");
                precioUnitarioPresupuestado = importePresupuesto / cantidadMielPresupuestada;
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return precioUnitarioPresupuestado;
            
        }
        
        return precioUnitarioPresupuestado;
    
    }
    
    public Double mostrarImportePagoPresupuesto(int codigoPresupuesto) {
        
        Double importePagoPresupuesto = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT haber from cta_cte_productor WHERE descripcion_movimiento = 'PRESUPUESTO' and comprobante_asociado = '" + codigoPresupuesto + "'");

            while (rs.next()){
            
                importePagoPresupuesto = rs.getDouble("haber");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return importePagoPresupuesto;
            
        }
        
        return importePagoPresupuesto;
    
    }
    
    public String mostrarNombreProductorPresupuesto(int codigoPresupuesto) {
        
        String nombreProductor = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = '" + codigoPresupuesto + "' and descripcion_movimiento = 'PRESUPUESTO'");

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
