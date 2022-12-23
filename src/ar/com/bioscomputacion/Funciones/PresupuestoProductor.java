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
import javax.swing.table.DefaultTableModel;

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

    public DefaultTableModel listarPresupuestosDeProductores(LocalDate fechaInicial, LocalDate fechaFinal) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID PRESUPUESTO", "FECHA", "N° COMPROBANTE", "ID PRODUCTOR", "PRODUCTOR", "IMPORTE", "KGS. MIEL"};

        String[] registros = new String[7];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT f.codigo_presupuesto, f.numero_comprobante, f.fecha_presupuesto, f.codigo_productor, o.nombre, f.importe_total_presupuesto, f.cantidad_miel from presupuesto_productor f join productor p on f.codigo_productor = p.cod_productor join persona o on p.cod_persona = o.cod_persona  WHERE f.codigo_presupuesto <> '17' and f.fecha_presupuesto BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' ORDER BY f.codigo_presupuesto");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_presupuesto");
                registros[1] = rs.getString("fecha_presupuesto");
                registros[2] = rs.getString("numero_comprobante");
                registros[3] = rs.getString("codigo_productor");
                registros[4] = rs.getString("nombre");
                registros[5] = rs.getString("importe_total_presupuesto");
                registros[6] = rs.getString("cantidad_miel");
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
    
    public String mostrarNumeroComprobantePresupuesto(int codigoPresupuesto) {
        
        String numeroComprobante = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT numero_comprobante from presupuesto_productor WHERE codigo_presupuesto = '" + codigoPresupuesto + "'");

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
    
    public Date mostrarFechaPresupuesto(int codigoPresupuesto) {
        
        Date fecha = Date.valueOf(LocalDate.now());

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT fecha_presupuesto from presupuesto_productor WHERE codigo_presupuesto = '" + codigoPresupuesto + "'");

            while (rs.next()){
            
                fecha = rs.getDate("fecha_presupuesto");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return fecha;
            
        }
        
        return fecha;
    
    }
    
    public boolean chequearAcreditacionesSobrePresupuesto(int codigoPresupuesto){
        
        boolean presupuestoAfectado = false;
        int cantidadComprobantes = 0;
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select tipo_comprobante_acreditacion \n" +
            "from comprobantes_acreditacion_comprobantes_afectados_productor \n" +
            "where codigo_comprobante_afectado_credito = '"+ codigoPresupuesto +"' \n" +
            "and (tipo_comprobante_afectado_credito = 'PRESUPUESTO') and estado_acreditacion <> 'ANULADO'");

            while (rs.next()){
            
                cantidadComprobantes = cantidadComprobantes + 1;
                
            }
            
            if (cantidadComprobantes > 0){
                
                presupuestoAfectado = true;
            
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return presupuestoAfectado;
            
        }
        
        return presupuestoAfectado;
    
    }
    
    public int mostrarCodigoProductorPresupuesto(int codigoPresupuesto) {
        
        int codigoProductor = 0;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select codigo_productor from presupuesto_productor where codigo_presupuesto = '" + codigoPresupuesto + "'");

            while (rs.next()){
            
                codigoProductor = rs.getInt("codigo_productor");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return codigoProductor;
            
        }
        
        return codigoProductor;
    
    }
    
    public int mostrarCodigoMovimientoEnCtaCtePresupuestoProductor(int codigoPresupuesto) {
        
        int codigoMovimientoCtaCte = 0;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select codigo_movimiento from cta_cte_productor where descripcion_movimiento = 'PRESUPUESTO' and comprobante_asociado = '" + codigoPresupuesto + "'");

            while (rs.next()){
            
                codigoMovimientoCtaCte = rs.getInt("codigo_movimiento");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return codigoMovimientoCtaCte;
            
        }
        
        return codigoMovimientoCtaCte;
    
    }
    
}
