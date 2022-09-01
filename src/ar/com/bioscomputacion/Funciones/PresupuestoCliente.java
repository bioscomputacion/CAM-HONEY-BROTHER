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
 * @author carlo
 */
public class PresupuestoCliente {

    private int codigoPresupuesto;
    private String numeroComprobante; 
    private int codigoMovimientoCtacte;
    private int codigoCliente;
    private Date fechaPresupuesto;
    private Date fechaVencimiento;
    private Double importeTotalPresupuesto;
    private Double cantidadMiel;

    public PresupuestoCliente(String numeroComprobante, int codigoMovimientoCtacte, int codigoCliente, Date fechaPresupuesto, Date fechaVencimiento, Double importeTotalPresupuesto, Double cantidadMiel) {
        
        this.numeroComprobante = numeroComprobante;
        this.codigoMovimientoCtacte = codigoMovimientoCtacte;
        this.codigoCliente = codigoCliente;
        this.fechaPresupuesto = fechaPresupuesto;
        this.fechaVencimiento = fechaVencimiento;
        this.importeTotalPresupuesto = importeTotalPresupuesto;
        this.cantidadMiel = cantidadMiel;
        
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

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
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

    public PresupuestoCliente() {
    }

    public int mostrarIdPresupuestoCliente() {

        int codigoPresupuestoCliente = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_presupuesto FROM presupuesto_cliente order by codigo_presupuesto asc");
            
            while (rs.next()) {

                codigoPresupuestoCliente = rs.getInt("codigo_presupuesto");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
            return codigoPresupuestoCliente;

        }
        catch(Exception e){
            
            return codigoPresupuestoCliente;
        } 

    }

    public boolean registrarPresupuestoCliente(PresupuestoCliente presupuestoCliente){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO presupuesto_Cliente (numero_comprobante, codigo_movimiento_ctacte, codigo_cliente, fecha_presupuesto, fecha_vencimiento, importe_total_presupuesto, cantidad_miel) "
                    + "VALUES (?,?,?,?,?,?,?)");
            
            
            pst.setString(1, presupuestoCliente.getNumeroComprobante());
            pst.setInt(2, presupuestoCliente.getCodigoMovimientoCtacte());
            pst.setInt(3, presupuestoCliente.getCodigoCliente());
            pst.setDate(4, presupuestoCliente.getFechaPresupuesto());
            pst.setDate(5, presupuestoCliente.getFechaVencimiento());
            pst.setDouble(6, presupuestoCliente.getImporteTotalPresupuesto());
            pst.setDouble(7, presupuestoCliente.getCantidadMiel());
            
            
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

    public boolean modificarPresupuestoCliente(PresupuestoCliente presupuestoCliente, int codigoPresupuesto) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("UPDATE presupuesto_cliente SET numero_comprobante = ?,codigo_movimiento_ctacte = ?,codigo_cliente = ?,fecha_presupuesto = ?,fecha_vencimiento = ?,importe_total_presupuesto = ?,cantidad_miel = ? WHERE codigo_presupuesto = '"+ codigoPresupuesto +"'");

            pst.setString(1, presupuestoCliente.getNumeroComprobante());
            pst.setInt(2, presupuestoCliente.getCodigoMovimientoCtacte());
            pst.setInt(3, presupuestoCliente.getCodigoCliente());
            pst.setDate(4, presupuestoCliente.getFechaPresupuesto());
            pst.setDate(5, presupuestoCliente.getFechaVencimiento());
            pst.setDouble(6, presupuestoCliente.getImporteTotalPresupuesto());
            pst.setDouble(7, presupuestoCliente.getCantidadMiel());

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
    
    public boolean eliminarPresupuestoCliente(int codigoPresupuesto) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("DELETE FROM presupuesto_cliente WHERE codigo_presupuesto = '"+ codigoPresupuesto +"'");

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
            ResultSet rs = st.executeQuery("SELECT importe_total_presupuesto from presupuesto_cliente WHERE codigo_presupuesto = '" + codigoPresupuesto + "'");

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
            ResultSet rs = st.executeQuery("SELECT importe_total_presupuesto, cantidad_miel from presupuesto_cliente WHERE codigo_presupuesto = '" + codigoPresupuesto + "'");

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
            ResultSet rs = st.executeQuery("SELECT haber from cta_cte_cliente WHERE descripcion_movimiento = 'PRESUPUESTO' and comprobante_asociado = '" + codigoPresupuesto + "'");

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
    
    public String mostrarNombreClientePresupuesto(int codigoPresupuesto) {
        
        String nombreCliente = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select r.nombre from cta_cte_cliente c join cliente p on c.codigo_cliente = p.cod_cliente JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = '" + codigoPresupuesto + "' and descripcion_movimiento = 'PRESUPUESTO'");

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
    
}
