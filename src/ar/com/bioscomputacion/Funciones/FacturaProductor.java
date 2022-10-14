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
 * @author Caco
 */
public class FacturaProductor {
    
    private int codigo_factura;
    private String tipoFactura;
    private String numero_comprobante; 
    private int codigo_movimiento_ctacte;
    private int codigo_productor;
    private Date fecha_factura;
    private Date fecha_vencimiento;
    private Double importe_total_factura;
    private Double cantidad_miel_facturada;
    
    public FacturaProductor(String tipo_factura, String numero_comprobante, int codigo_movimiento_ctacte, int codigo_productor, Date fecha_factura, Date fecha_vencimiento, Double importe_total_factura, Double cantidad_miel_facturada) {
        
        this.tipoFactura = tipo_factura;
        this.numero_comprobante = numero_comprobante;
        this.codigo_movimiento_ctacte = codigo_movimiento_ctacte;
        this.codigo_productor = codigo_productor;
        this.fecha_factura = fecha_factura;
        this.fecha_vencimiento = fecha_vencimiento;
        this.importe_total_factura = importe_total_factura;
        this.cantidad_miel_facturada = cantidad_miel_facturada;
        
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public FacturaProductor() {
    }

    public int getCodigo_factura() {
        return codigo_factura;
    }

    public void setCodigo_factura(int codigo_factura) {
        this.codigo_factura = codigo_factura;
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

    public Date getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(Date fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public Date getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(Date fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Double getImporte_total_factura() {
        return importe_total_factura;
    }

    public void setImporte_total_factura(Double importe_total_factura) {
        this.importe_total_factura = importe_total_factura;
    }

    public Double getCantidad_miel() {
        return cantidad_miel_facturada;
    }

    public void setCantidad_miel(Double cantidad_miel) {
        this.cantidad_miel_facturada = cantidad_miel;
    }

    public int mostrarIdFacturaProductor() {

        int codigoFacturaProductor = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_factura FROM factura_productor order by codigo_factura asc");
            
            while (rs.next()) {

                codigoFacturaProductor = rs.getInt("codigo_factura");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);

            return codigoFacturaProductor;

        }catch(Exception e){
            
            return codigoFacturaProductor;
        } 

    }

    public int mostrarIdItemAFacturar(int codigoFactura) {

        int codigoItemAFacturar = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_item_facturado FROM items_facturados_factura_productor where codigo_factura='"+ codigoFactura +"'");
            
            while (rs.next()) {

                codigoItemAFacturar = rs.getInt("codigo_item_facturado");
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);

            return codigoItemAFacturar;

        }catch(Exception e){
            
            return codigoItemAFacturar;
        } 

    }

    public boolean registrarFacturaProductor(FacturaProductor facturaProductor){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            PreparedStatement pst = cn.prepareStatement("INSERT INTO factura_productor (tipo_factura, numero_comprobante, codigo_movimiento_ctacte, codigo_productor, fecha_factura, fecha_vencimiento, importe_total_factura, cantidad_miel_facturada) "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            
            
            pst.setString(1, facturaProductor.getTipoFactura());
            pst.setString(2, facturaProductor.getNumero_comprobante());
            pst.setInt(3, facturaProductor.getCodigo_movimiento_ctacte());
            pst.setInt(4, facturaProductor.getCodigo_productor());
            pst.setDate(5, facturaProductor.getFecha_factura());
            pst.setDate(6, facturaProductor.getFecha_vencimiento());
            pst.setDouble(7, facturaProductor.getImporte_total_factura());
            pst.setDouble(8, facturaProductor.getCantidad_miel());
            
            
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

    public boolean modificarFacturaProductor(FacturaProductor facturaProductor, int codigoFactura) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("UPDATE factura_productor SET tipo_factura = ?,numero_comprobante = ?,codigo_movimiento_ctacte = ?,codigo_productor = ?,fecha_factura = ?,fecha_vencimiento = ?,importe_total_factura = ?,cantidad_miel_facturada = ? WHERE codigo_factura = '"+ codigoFactura +"'");

            pst.setString(1, facturaProductor.getNumero_comprobante());
            pst.setString(2, facturaProductor.getTipoFactura());
            pst.setInt(3, facturaProductor.getCodigo_movimiento_ctacte());
            pst.setInt(4, facturaProductor.getCodigo_productor());
            pst.setDate(5, facturaProductor.getFecha_factura());
            pst.setDate(6, facturaProductor.getFecha_vencimiento());
            pst.setDouble(7, facturaProductor.getImporte_total_factura());
            pst.setDouble(8, facturaProductor.getCantidad_miel());

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

    public boolean eliminarFacturaProductor(int codigoFactura) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("DELETE FROM factura_productor WHERE codigo_factura = '"+ codigoFactura +"'");

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
    
    public DefaultTableModel listarFacturasA(LocalDate fechaInicial, LocalDate fechaFinal) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID FACTURA", "FECHA", "N° COMPROBANTE", "ID PRODUCTOR", "PRODUCTOR","IMPORTE", "KGS. MIEL"};

        String[] registros = new String[7];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT f.codigo_factura, f.numero_comprobante, f.fecha_factura, f.codigo_productor, o.nombre, f.importe_total_factura, f.cantidad_miel_facturada from factura_productor f join productor p on f.codigo_productor = p.cod_productor join persona o on p.cod_persona = o.cod_persona  WHERE f.codigo_factura <> '52' and f.tipo_factura = 'FACTURA A' and f.fecha_factura BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' ORDER BY f.codigo_factura");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_factura");
                registros[1] = rs.getString("fecha_factura");
                registros[2] = rs.getString("numero_comprobante");
                registros[3] = rs.getString("codigo_productor");
                registros[4] = rs.getString("nombre");
                registros[5] = rs.getString("importe_total_factura");
                registros[6] = rs.getString("cantidad_miel_facturada");
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
    
    public DefaultTableModel listarFacturasC(LocalDate fechaInicial, LocalDate fechaFinal) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID FACTURA", "FECHA", "N° COMPROBANTE", "ID PRODUCTOR", "PRODUCTOR","IMPORTE", "KGS. MIEL"};

        String[] registros = new String[7];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT f.codigo_factura, f.numero_comprobante, f.fecha_factura, f.codigo_productor, o.nombre, f.importe_total_factura, f.cantidad_miel_facturada from factura_productor f join productor p on f.codigo_productor = p.cod_productor join persona o on p.cod_persona = o.cod_persona  WHERE f.codigo_factura <> '41' and f.tipo_factura = 'FACTURA C' and f.fecha_factura BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' ORDER BY f.codigo_factura");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_factura");
                registros[1] = rs.getString("fecha_factura");
                registros[2] = rs.getString("numero_comprobante");
                registros[3] = rs.getString("codigo_productor");
                registros[4] = rs.getString("nombre");
                registros[5] = rs.getString("importe_total_factura");
                registros[6] = rs.getString("cantidad_miel_facturada");
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
    
    //COPIAR DESDE ACA PARA ALGUNOS DE LOS DEMAS COMPROBANTES
    
    public Double mostrarImporteFactura(int codigoFactura) {
        
        Double importeFactura = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT importe_total_factura from factura_productor WHERE codigo_factura = '" + codigoFactura + "'");

            while (rs.next()){
            
                importeFactura = rs.getDouble("importe_total_factura");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return importeFactura;
            
        }
        
        return importeFactura;
    
    }
    
    public Double mostrarPrecioUnitarioFactura(int codigoFactura) {
        
        Double importeFactura = 0.00;
        Double cantidadMielfacturada = 0.00;
        Double precioUnitarioFacturado = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT importe_total_factura, cantidad_miel_facturada from factura_productor WHERE codigo_factura = '" + codigoFactura + "'");

            while (rs.next()){
            
                importeFactura = rs.getDouble("importe_total_factura");
                cantidadMielfacturada = rs.getDouble("cantidad_miel_facturada");
                precioUnitarioFacturado = importeFactura / cantidadMielfacturada;
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return precioUnitarioFacturado;
            
        }
        
        return precioUnitarioFacturado;
    
    }
    
    public Double mostrarImportePagoFactura(String tipoFactura, int codigoFactura) {
        
        Double importePagoFactura = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT haber from cta_cte_productor WHERE descripcion_movimiento = '" + tipoFactura + "' and comprobante_asociado = '" + codigoFactura + "'");

            while (rs.next()){
            
                importePagoFactura = rs.getDouble("haber");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return importePagoFactura;
            
        }
        
        return importePagoFactura;
    
    }
    
    public String mostrarNombreProductorFacturaA(int codigoFactura) {
        
        String nombreProductor = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = '" + codigoFactura + "' and descripcion_movimiento = 'FACTURA A'");

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
    
    public String mostrarNombreProductorFacturaC(int codigoFactura) {
        
        String nombreProductor = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = '" + codigoFactura + "' and descripcion_movimiento = 'FACTURA C'");

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