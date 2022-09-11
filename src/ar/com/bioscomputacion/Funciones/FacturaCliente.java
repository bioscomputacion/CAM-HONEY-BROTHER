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
public class FacturaCliente {
    
    private int codigo_factura;
    private String tipo_factura;
    private String numero_comprobante; 
    private int codigo_movimiento_ctacte;
    private int codigo_cliente;
    private Date fecha_factura;
    private Date fecha_vencimiento;
    private Double importe_total_factura;
    private Double cantidad_miel;
    
    public FacturaCliente(String tipo_factura, String numero_comprobante, int codigo_movimiento_ctacte, int codigo_cliente, Date fecha_factura, Date fecha_vencimiento, Double importe_total_factura, Double cantidad_miel) {
        
        this.codigo_factura = codigo_factura;
        this.tipo_factura = tipo_factura;
        this.numero_comprobante = numero_comprobante;
        this.codigo_movimiento_ctacte = codigo_movimiento_ctacte;
        this.codigo_cliente = codigo_cliente;
        this.fecha_factura = fecha_factura;
        this.fecha_vencimiento = fecha_vencimiento;
        this.importe_total_factura = importe_total_factura;
        this.cantidad_miel = cantidad_miel;
        
    }

    public FacturaCliente() {
    }

    public int getCodigo_factura() {
        return codigo_factura;
    }

    public void setCodigo_factura(int codigo_factura) {
        this.codigo_factura = codigo_factura;
    }

    public String getTipo_factura() {
        return tipo_factura;
    }

    public void setTipo_factura(String tipo_factura) {
        this.tipo_factura = tipo_factura;
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

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
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
        return cantidad_miel;
    }

    public void setCantidad_miel(Double cantidad_miel) {
        this.cantidad_miel = cantidad_miel;
    }

    public int mostrarIdFacturaCliente() {

        int codigoFacturaCliente = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_factura FROM factura_cliente order by codigo_factura asc");
            
            while (rs.next()) {

                codigoFacturaCliente = rs.getInt("codigo_factura");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
            return codigoFacturaCliente;

        }
        catch(Exception e){
            
            return codigoFacturaCliente;
            
        } 

    }

    public int mostrarIdItemAFacturar(int codigoFactura) {

        int codigoItemAFacturar = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_item_facturado FROM items_facturados_factura_cliente where codigo_factura='"+ codigoFactura +"'");
            
            while (rs.next()) {

                codigoItemAFacturar = rs.getInt("codigo_item_facturado");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
            return codigoItemAFacturar;

        }
        catch(Exception e){
            
            return codigoItemAFacturar;
            
        } 

    }

    public boolean registrarFacturaCliente(FacturaCliente facturaCliente){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("INSERT INTO factura_cliente (tipo_factura, numero_comprobante, codigo_movimiento_ctacte, codigo_cliente, fecha_factura, fecha_vencimiento, importe_total_factura, cantidad_miel) "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            
            
            pst.setString(1, facturaCliente.getTipo_factura());
            pst.setString(2, facturaCliente.getNumero_comprobante());
            pst.setInt(3, facturaCliente.getCodigo_movimiento_ctacte());
            pst.setInt(4, facturaCliente.getCodigo_cliente());
            pst.setDate(5, facturaCliente.getFecha_factura());
            pst.setDate(6, facturaCliente.getFecha_vencimiento());
            pst.setDouble(7, facturaCliente.getImporte_total_factura());
            pst.setDouble(8, facturaCliente.getCantidad_miel());
            
            
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

    public boolean modificarFacturaCliente(FacturaCliente facturaCliente, int codigoFactura) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("UPDATE factura_cliente SET tipo_factura = ?,numero_comprobante = ?,codigo_movimiento_ctacte = ?,codigo_cliente = ?,fecha_factura = ?,fecha_vencimiento = ?,importe_total_factura = ?,cantidad_miel = ? WHERE codigo_factura = '"+ codigoFactura +"'");

            pst.setString(1, facturaCliente.getTipo_factura());
            pst.setString(2, facturaCliente.getNumero_comprobante());
            pst.setInt(3, facturaCliente.getCodigo_movimiento_ctacte());
            pst.setInt(4, facturaCliente.getCodigo_cliente());
            pst.setDate(5, facturaCliente.getFecha_factura());
            pst.setDate(6, facturaCliente.getFecha_vencimiento());
            pst.setDouble(7, facturaCliente.getImporte_total_factura());
            pst.setDouble(8, facturaCliente.getCantidad_miel());

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

    public boolean eliminarFacturaCliente(int codigoFactura) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("DELETE FROM factura_cliente WHERE codigo_factura = '"+ codigoFactura +"'");

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

    public DefaultTableModel listarFacturasE(String mesConsulta) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID FACTURA", "FECHA", "N° COMPROBANTE", "ID CLIENTE", "CLIENTE","IMPORTE", "KGS. MIEL"};

        String[] registros = new String[7];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT f.codigo_factura, f.numero_comprobante, f.fecha_factura, f.codigo_cliente, o.nombre, f.importe_total_factura, f.cantidad_miel from factura_cliente f join cliente p on f.codigo_cliente = p.cod_cliente join persona o on p.cod_persona = o.cod_persona  WHERE f.codigo_factura <> '18' and f.fecha_factura BETWEEN '2022-09-01' AND '2022-09-30' ORDER BY f.codigo_factura");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_factura");
                registros[1] = rs.getString("fecha_factura");
                registros[2] = rs.getString("numero_comprobante");
                registros[3] = rs.getString("codigo_cliente");
                registros[4] = rs.getString("nombre");
                registros[5] = rs.getString("importe_total_factura");
                registros[6] = rs.getString("cantidad_miel");
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
    
    public Double mostrarImporteFactura(int codigoFactura) {
        
        Double importeFactura = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT importe_total_factura from factura_cliente WHERE codigo_factura = '" + codigoFactura + "'");

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
            ResultSet rs = st.executeQuery("SELECT importe_total_factura, cantidad_miel from factura_cliente WHERE codigo_factura = '" + codigoFactura + "'");

            while (rs.next()){
            
                importeFactura = rs.getDouble("importe_total_factura");
                cantidadMielfacturada = rs.getDouble("cantidad_miel");
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
    
    public Double mostrarImportePagoFactura(int codigoFactura) {
        
        Double importePagoFactura = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT haber from cta_cte_cliente WHERE descripcion_movimiento = 'FACTURA E' and comprobante_asociado = '" + codigoFactura + "'");

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
    
    public String mostrarNombreClienteFactura(int codigoFactura) {
        
        String nombreCliente = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select r.nombre from cta_cte_cliente c join cliente p on c.cod_cliente = p.cod_cliente JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = '" + codigoFactura + "' and descripcion_movimiento = 'FACTURA E'");

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
