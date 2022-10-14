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
public class NotaCreditoProductor {
    
    private int codigo_nota_credito;
    private String tipo_nota_Credito;
    private String numero_comprobante; 
    private int codigo_movimiento_ctacte;
    private int codigo_productor;
    private Date fecha_nota_credito;
    private Double importe_total_nota_credito;
    private Double cantidad_miel_devuelta;
    private int codigo_comprobante_pagado;
    

    public NotaCreditoProductor(String numero_comprobante, String tipo_nota_credito, int codigo_movimiento_ctacte, int codigo_productor, Date fecha_nota_credito, Double importe_total_nota_credito, Double cantidad_miel_devuelta, int codigo_comprobante_pagado) {
        
        this.numero_comprobante = numero_comprobante;
        this.tipo_nota_Credito = tipo_nota_credito;
        this.codigo_movimiento_ctacte = codigo_movimiento_ctacte;
        this.codigo_productor = codigo_productor;
        this.fecha_nota_credito = fecha_nota_credito;
        this.importe_total_nota_credito = importe_total_nota_credito;
        this.cantidad_miel_devuelta = cantidad_miel_devuelta;
        this.codigo_comprobante_pagado = codigo_comprobante_pagado;
        
    }

    public NotaCreditoProductor() {
    }

    public String getTipoNotaCredito() {
        return tipo_nota_Credito;
    }

    public void tipo_nota_Credito(String tipoNotaCredito) {
        this.tipo_nota_Credito = tipoNotaCredito;
    }

    public int getCodigo_nota_credito() {
        return codigo_nota_credito;
    }

    public void setCodigo_nota_credito(int codigoNotaCredito) {
        this.codigo_nota_credito = codigoNotaCredito;
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

    public Date getFecha_nota_credito() {
        return fecha_nota_credito;
    }

    public void setFecha_nota_credito(Date fecha_nota_credito) {
        this.fecha_nota_credito = fecha_nota_credito;
    }

    public Double getImporte_total_nota_credito(){
        return importe_total_nota_credito;
    }

    public void setImporte_total_nota_credito(Double importe_total_nota_credito) {
        this.importe_total_nota_credito = importe_total_nota_credito;
    }

    public Double getCantidad_miel() {
        return cantidad_miel_devuelta;
    }

    public void setCantidad_miel(Double cantidad_miel) {
        this.cantidad_miel_devuelta = cantidad_miel;
    }

    public String getTipo_nota_Credito() {
        return tipo_nota_Credito;
    }

    public void setTipo_nota_Credito(String tipo_nota_Credito) {
        this.tipo_nota_Credito = tipo_nota_Credito;
    }

    public Double getCantidad_miel_devuelta() {
        return cantidad_miel_devuelta;
    }

    public void setCantidad_miel_devuelta(Double cantidad_miel_devuelta) {
        this.cantidad_miel_devuelta = cantidad_miel_devuelta;
    }

    public int getCodigo_comprobante_pagado() {
        return codigo_comprobante_pagado;
    }

    public void setCodigo_comprobante_pagado(int codigo_comprobante_pagado) {
        this.codigo_comprobante_pagado = codigo_comprobante_pagado;
    }

    
    public int mostrarIdNotaCreditoProductor() {

        int codigoNoptaCreditoProductor = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_nota_credito FROM nota_credito_productor order by codigo_nota_credito asc");
            
            while (rs.next()) {

                codigoNoptaCreditoProductor = rs.getInt("codigo_nota_credito");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);

            return codigoNoptaCreditoProductor;

        }catch(Exception e){
            
            return codigoNoptaCreditoProductor;
        } 

    }

    public boolean registrarNotaCreditoProductor(NotaCreditoProductor notaCreditoProductor){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            PreparedStatement pst = cn.prepareStatement("INSERT INTO nota_credito_productor (tipo_nota_credito, numero_comprobante, codigo_movimiento_cta_cte, codigo_productor, fecha_nota_credito, importe_total_nota_credito, cantidad_miel_afectada, codigo_comprobante_pagado) "
                    + "VALUES (?,?,?,?,?,?,?,?)");
            
            
            pst.setString(1, notaCreditoProductor.getTipoNotaCredito());
            pst.setString(2, notaCreditoProductor.getNumero_comprobante());
            pst.setInt(3, notaCreditoProductor.getCodigo_movimiento_ctacte());
            pst.setInt(4, notaCreditoProductor.getCodigo_productor());
            pst.setDate(5, notaCreditoProductor.getFecha_nota_credito());
            pst.setDouble(6, notaCreditoProductor.getImporte_total_nota_credito());
            pst.setDouble(7, notaCreditoProductor.getCantidad_miel());
            pst.setInt(8, notaCreditoProductor.getCodigo_comprobante_pagado());
            
            
            
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

    public boolean modificarFacturaProductor(NotaCreditoProductor facturaProductor, int codigoFactura) {

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

    public DefaultTableModel listarNotasCreditoA(LocalDate fechaInicial, LocalDate fechaFinal) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID NOTA CREDITO", "FECHA", "N° COMPROBANTE", "ID PRODUCTOR", "PRODUCTOR","IMPORTE", "KGS. MIEL", "COMPROBANTE ASOCIADO"};

        String[] registros = new String[8];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT f.codigo_nota_credito, f.numero_comprobante, f.fecha_nota_credito, f.codigo_productor, o.nombre, f.importe_total_nota_credito, f.cantidad_miel_afectada, f.codigo_comprobante_pagado from nota_credito_productor f join productor p on f.codigo_productor = p.cod_productor join persona o on p.cod_persona = o.cod_persona  WHERE f.codigo_nota_credito <> '1' and f.tipo_nota_credito = 'NOTA DE CREDITO A' and f.fecha_nota_credito BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' ORDER BY f.codigo_nota_credito");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_nota_credito");
                registros[1] = rs.getString("fecha_nota_credito");
                registros[2] = rs.getString("numero_comprobante");
                registros[3] = rs.getString("codigo_productor");
                registros[4] = rs.getString("nombre");
                registros[5] = rs.getString("importe_total_nota_credito");
                registros[6] = rs.getString("cantidad_miel_afectada");
                registros[7] = rs.getString("codigo_comprobante_pagado");
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
    
    public DefaultTableModel listarNotasCreditoC(LocalDate fechaInicial, LocalDate fechaFinal) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID NOTA CREDITO", "FECHA", "N° COMPROBANTE", "ID PRODUCTOR", "PRODUCTOR","IMPORTE", "KGS. MIEL", "COMPROBANTE ASOCIADO"};

        String[] registros = new String[8];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT f.codigo_nota_credito, f.numero_comprobante, f.fecha_nota_credito, f.codigo_productor, o.nombre, f.importe_total_nota_credito, f.cantidad_miel_afectada, f.codigo_comprobante_pagado from nota_credito_productor f join productor p on f.codigo_productor = p.cod_productor join persona o on p.cod_persona = o.cod_persona  WHERE f.codigo_nota_credito <> '1' and f.tipo_nota_credito = 'NOTA DE CREDITO C' and f.fecha_nota_credito BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' ORDER BY f.codigo_nota_credito");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_nota_credito");
                registros[1] = rs.getString("fecha_nota_credito");
                registros[2] = rs.getString("numero_comprobante");
                registros[3] = rs.getString("codigo_productor");
                registros[4] = rs.getString("nombre");
                registros[5] = rs.getString("importe_total_nota_credito");
                registros[6] = rs.getString("cantidad_miel_afectada");
                registros[7] = rs.getString("codigo_comprobante_pagado");
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
    
    public Double mostrarImporteNotaCredito(int codigoNotaCredito) {
        
        Double importeFactura = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT importe_total_nota_credito from nota_credito_productor WHERE codigo_nota_credito = '" + codigoNotaCredito + "'");

            while (rs.next()){
            
                importeFactura = rs.getDouble("importe_total_nota_credito");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return importeFactura;
            
        }
        
        return importeFactura;
    
    }
    
    public Double mostrarPrecioUnitarioNotaCredito(int codigoNotaCredito) {
        
        Double importeNotaCredito = 0.00;
        Double cantidadMielAfectada = 0.00;
        Double precioUnitarioFacturado = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT importe_total_nota_credito, cantidad_miel_afectada from nota_credito_productor WHERE codigo_nota_credito = '" + codigoNotaCredito + "'");

            while (rs.next()){
            
                importeNotaCredito = rs.getDouble("importe_total_nota_credito");
                cantidadMielAfectada = rs.getDouble("cantidad_miel_afectada");
                precioUnitarioFacturado = importeNotaCredito / cantidadMielAfectada;
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return precioUnitarioFacturado;
            
        }
        
        return precioUnitarioFacturado;
    
    }
    
    public String mostrarNombreProductorNotaCredito(int codigoNotaCredito) {
        
        String nombreProductor = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = '" + codigoNotaCredito + "' and descripcion_movimiento = 'NOTA DE CREDITO A' or 'NOTA DE CREDITO C'");

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
    
    public int mostrarCodigoComprobanteAfectadoNC(int codigoNotaCredito) {
        
        int codigoComprobanteAfectado = 0;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_comprobante_pagado from nota_credito_productor where codigo_nota_credito = '"+ codigoNotaCredito +"'");

            while (rs.next()){
            
                codigoComprobanteAfectado = rs.getInt("codigo_comprobante_pagado");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return codigoComprobanteAfectado;
            
        }
        
        return codigoComprobanteAfectado;
    
    }
    
    public String mostrarNumeroFacturaAfectadaNC(int codigoNotaCredito) {
        
        String numeroFactura = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT f.numero_comprobante from nota_credito_productor n join factura_productor f on n.codigo_comprobante_pagado = f.codigo_factura where n.codigo_nota_credito = '"+ codigoNotaCredito +"'");

            while (rs.next()){
            
                numeroFactura = rs.getString("numero_comprobante");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return numeroFactura;
            
        }
        
        return numeroFactura;
    
    }
    
}