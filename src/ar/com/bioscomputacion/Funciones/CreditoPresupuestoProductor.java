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
public class CreditoPresupuestoProductor {
    
    private int codigo_credito_presupuesto;
    private String numero_comprobante; 
    private int codigo_movimiento_cta_cte;
    private int codigo_productor;
    private Date fecha_credito_presupuesto;
    private Double importe_total_credito_presupuesto;
    private Double cantidad_miel_afectada;
    private int codigo_comprobante_pagado;

    public CreditoPresupuestoProductor(String numero_comprobante, int codigo_movimiento_cta_cte, int codigo_productor, Date fecha_credito_presupuesto, Double importe_total_credito_presupuesto, Double cantidad_miel_afectada, int codigo_comprobante_pagado) {
        
        this.numero_comprobante = numero_comprobante;
        this.codigo_movimiento_cta_cte = codigo_movimiento_cta_cte;
        this.codigo_productor = codigo_productor;
        this.fecha_credito_presupuesto = fecha_credito_presupuesto;
        this.importe_total_credito_presupuesto = importe_total_credito_presupuesto;
        this.cantidad_miel_afectada = cantidad_miel_afectada;
        this.codigo_comprobante_pagado = codigo_comprobante_pagado;
        
    }

    public int getCodigo_credito_presupuesto() {
        return codigo_credito_presupuesto;
    }

    public void setCodigo_credito_presupuesto(int codigo_credito_presupuesto) {
        this.codigo_credito_presupuesto = codigo_credito_presupuesto;
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

    public Date getFecha_credito_presupuesto() {
        return fecha_credito_presupuesto;
    }

    public void setFecha_credito_presupuesto(Date fecha_credito_presupuesto) {
        this.fecha_credito_presupuesto = fecha_credito_presupuesto;
    }

    public Double getImporte_total_credito_presupuesto() {
        return importe_total_credito_presupuesto;
    }

    public void setImporte_total_credito_presupuesto(Double importe_total_credito_presupuesto) {
        this.importe_total_credito_presupuesto = importe_total_credito_presupuesto;
    }

    public Double getCantidad_miel_afectada() {
        return cantidad_miel_afectada;
    }

    public void setCantidad_miel_afectada(Double cantidad_miel_afectada) {
        this.cantidad_miel_afectada = cantidad_miel_afectada;
    }

    public int getCodigo_comprobante_pagado() {
        return codigo_comprobante_pagado;
    }

    public void setCodigo_comprobante_pagado(int codigo_comprobante_pagado) {
        this.codigo_comprobante_pagado = codigo_comprobante_pagado;
    }

    public CreditoPresupuestoProductor() {
    }
    
    public int mostrarIdCreditoPresupuestoProductor() {

        int codigoCreditoPresupuesto = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_credito_presupuesto FROM credito_presupuesto_productor order by codigo_credito_presupuesto asc");
            
            while (rs.next()) {

                codigoCreditoPresupuesto = rs.getInt("codigo_credito_presupuesto");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);

            return codigoCreditoPresupuesto;

        }catch(Exception e){
            
            return codigoCreditoPresupuesto;
        } 

    }

    public boolean registrarCreditoPresupuestoProductor(CreditoPresupuestoProductor creditoPresupuesto){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            PreparedStatement pst = cn.prepareStatement("INSERT INTO credito_presupuesto_productor (numero_comprobante, codigo_movimiento_cta_cte, codigo_productor, fecha_credito_presupuesto, importe_total_credito_presupuesto, cantidad_miel_afectada, codigo_comprobante_pagado) "
                    + "VALUES (?,?,?,?,?,?,?)");
            
            
            pst.setString(1, creditoPresupuesto.getNumero_comprobante());
            pst.setInt(2, creditoPresupuesto.getCodigo_movimiento_cta_cte());
            pst.setInt(3, creditoPresupuesto.getCodigo_productor());
            pst.setDate(4, creditoPresupuesto.getFecha_credito_presupuesto());
            pst.setDouble(5, creditoPresupuesto.getImporte_total_credito_presupuesto());
            pst.setDouble(6, creditoPresupuesto.getCantidad_miel_afectada());
            pst.setInt(7, creditoPresupuesto.getCodigo_comprobante_pagado());
            
            
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

    public boolean modificarCreditoPresupuestoProductor(CreditoPresupuestoProductor creditoPresupuesto, int codigoCreditoPresupuesto) {

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

    public boolean eliminarCreditoPresupuestoProductor(int codigoCreditoPresupuesto) {

        try {

            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            PreparedStatement pst = cn.prepareStatement("DELETE FROM credito_presupuesto_productor WHERE codigo_credito_presupuesto = '"+ codigoCreditoPresupuesto +"'");

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

    public DefaultTableModel listarCreditosPresupuestosProductores(LocalDate fechaInicial, LocalDate fechaFinal) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID CREDITO", "FECHA", "N° COMPROBANTE", "ID PRODUCTOR", "PRODUCTOR","IMPORTE", "KGS. MIEL", "COMPROBANTE ASOCIADO", "COMPROBANTE AFECTADO"};

        String[] registros = new String[9];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT f.codigo_credito_presupuesto, f.numero_comprobante, f.fecha_credito_presupuesto, f.codigo_productor, o.nombre, f.importe_total_credito_presupuesto, f.cantidad_miel_afectada, f.codigo_comprobante_pagado, g.numero_comprobante as presupuesto from credito_presupuesto_productor f join productor p on f.codigo_productor = p.cod_productor join persona o on p.cod_persona = o.cod_persona join presupuesto_productor g on f.codigo_comprobante_pagado = g.codigo_presupuesto WHERE f.codigo_credito_presupuesto <> '0' and f.fecha_credito_presupuesto BETWEEN '"+fechaInicial+"' AND '"+fechaFinal+"' ORDER BY f.codigo_credito_presupuesto");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_credito_presupuesto");
                registros[1] = rs.getString("fecha_credito_presupuesto");
                registros[2] = rs.getString("numero_comprobante");
                registros[3] = rs.getString("codigo_productor");
                registros[4] = rs.getString("nombre");
                registros[5] = rs.getString("importe_total_credito_presupuesto");
                registros[6] = rs.getString("cantidad_miel_afectada");
                registros[7] = rs.getString("codigo_comprobante_pagado");
                registros[8] = rs.getString("presupuesto");

                modelo.addRow(registros);
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            
        }
        
        return modelo;
        
    }
    
    public Double mostrarImporteCreditoPresupuestoProductor(int codigoCreditoPresupuesto) {
        
        Double importeCreditoPresupuesto = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT importe_total_credito_presupuesto from credito_presupuesto_productor WHERE codigo_credito_presupuesto = '" + codigoCreditoPresupuesto + "'");

            while (rs.next()){
            
                importeCreditoPresupuesto = rs.getDouble("importe_total_credito_presupuesto");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return importeCreditoPresupuesto;
            
        }
        
        return importeCreditoPresupuesto;
    
    }
    
    public Double mostrarPrecioUnitarioCreditoPresupuestoProductor(int codigoCreditoPresupuesto) {
        
        Double importeCreditoPrespuesto = 0.00;
        Double cantidadMielAfectada = 0.00;
        Double precioUnitarioAcreditado = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT importe_total_credito_presupuesto, cantidad_miel_afectada from credito_presupuesto_productor WHERE codigo_credito_presupuesto = '" + codigoCreditoPresupuesto + "'");

            while (rs.next()){
            
                importeCreditoPrespuesto = rs.getDouble("importe_total_credito_presupuesto");
                cantidadMielAfectada = rs.getDouble("cantidad_miel_afectada");
                precioUnitarioAcreditado = importeCreditoPrespuesto / cantidadMielAfectada;
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return precioUnitarioAcreditado;
            
        }
        
        return precioUnitarioAcreditado;
    
    }
    
    public String mostrarNombreProductorCreditoPresupuestoProductor(int codigoCreditoPresupuesto) {
        
        String nombreProductor = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select r.nombre from credito_presupuesto_productor n join productor p on n.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where n.codigo_credito_presupuesto= '"+ codigoCreditoPresupuesto +"'");

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
    
    public int mostrarCodigoComprobanteAfectadoCreditoPresupuesto(int codigoCreditoPresupuesto) {
        
        int codigoComprobanteAfectado = 0;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_comprobante_pagado from credito_presupuesto_productor where codigo_credito_presupuesto = '"+ codigoCreditoPresupuesto +"'");

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
    
    public String mostrarNumeroPrespuestoAfectadoCreditoPresupuesto(int codigoCreditoPresupuesto) {
        
        String numeroFactura = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT f.numero_comprobante from credito_presupuesto_productor n join presupuesto_productor f on n.codigo_comprobante_pagado = f.codigo_presupuesto where n.codigo_credito_presupuesto = '"+ codigoCreditoPresupuesto +"'");

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
    
    public String mostrarNumeroComprobanteCreditoPresupuesto(int codigoCreditoPresupuesto) {
        
        String numeroComprobante = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT numero_comprobante from credito_presupuesto_productor WHERE codigo_credito_presupuesto = '" + codigoCreditoPresupuesto + "'");

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
    
    public Date mostrarFechaCreditoPresupuesto(int codigoCreditoPresupuesto) {
        
        Date fecha = Date.valueOf(LocalDate.now());

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT fecha_credito_presupuesto from credito_presupuesto_productor WHERE codigo_credito_presupuesto = '" + codigoCreditoPresupuesto + "'");

            while (rs.next()){
            
                fecha = rs.getDate("fecha_credito_presupuesto");
                
            }

            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
            return fecha;
            
        }
        
        return fecha;
    
    }
    
    public int mostrarCodigoProductorCreditoPresupuesto(int codigoCreditoPresupuesto) {
        
        int codigoProductor = 0;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select codigo_productor from credito_presupuesto_productor where codigo_credito_presupuesto = '" + codigoCreditoPresupuesto + "'");

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
    
    public int mostrarCodigoMovimientoEnCtaCteCreditoPresupuestoProductor(int codigoCreditoPresupuesto) {
        
        int codigoMovimientoCtaCte = 0;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select codigo_movimiento from cta_cte_productor where descripcion_movimiento = 'CREDITO PRESUPUESTO' and comprobante_asociado = '" + codigoCreditoPresupuesto + "'");

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