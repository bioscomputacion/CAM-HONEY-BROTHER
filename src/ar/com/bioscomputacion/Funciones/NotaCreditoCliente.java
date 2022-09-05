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
public class NotaCreditoCliente {
    
    private int codigo_nota_credito;
    private String tipo_nota_Credito;
    private String numero_comprobante; 
    private int codigo_movimiento_ctacte;
    private int codigo_cliente;
    private Date fecha_nota_credito;
    private Double importe_total_nota_credito;
    private Double cantidad_miel_devuelta;
    

    public NotaCreditoCliente(String numero_comprobante, String tipo_nota_credito, int codigo_movimiento_ctacte, int codigo_cliente, Date fecha_nota_credito, Double importe_total_nota_credito, Double cantidad_miel_devuelta) {
        
        this.numero_comprobante = numero_comprobante;
        this.tipo_nota_Credito = tipo_nota_credito;
        this.codigo_movimiento_ctacte = codigo_movimiento_ctacte;
        this.codigo_cliente = codigo_cliente;
        this.fecha_nota_credito = fecha_nota_credito;
        this.importe_total_nota_credito = importe_total_nota_credito;
        this.cantidad_miel_devuelta = cantidad_miel_devuelta;
        
    }

    public NotaCreditoCliente() {
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

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
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

    public int mostrarIdNotaCreditoCliente() {

        int codigoNoptaCreditoCliente = 0;
        
        try{
 
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_nota_credito FROM nota_credito_cliente order by codigo_nota_credito asc");
            
            while (rs.next()) {

                codigoNoptaCreditoCliente = rs.getInt("codigo_nota_credito");
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);

            return codigoNoptaCreditoCliente;

        }catch(Exception e){
            
            return codigoNoptaCreditoCliente;
        } 

    }

    public boolean registrarNotaCreditoCliente(NotaCreditoCliente notaCreditoCliente){
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();

            PreparedStatement pst = cn.prepareStatement("INSERT INTO nota_credito_cliente (tipo_nota_credito, numero_comprobante, codigo_movimiento_cta_cte, codigo_cliente, fecha_nota_credito, importe_total_nota_credito, cantidad_miel_afectada) "
                    + "VALUES (?,?,?,?,?,?,?)");
            
            
            pst.setString(1, notaCreditoCliente.getTipoNotaCredito());
            pst.setString(2, notaCreditoCliente.getNumero_comprobante());
            pst.setInt(3, notaCreditoCliente.getCodigo_movimiento_ctacte());
            pst.setInt(4, notaCreditoCliente.getCodigo_cliente());
            pst.setDate(5, notaCreditoCliente.getFecha_nota_credito());
            pst.setDouble(6, notaCreditoCliente.getImporte_total_nota_credito());
            pst.setDouble(7, notaCreditoCliente.getCantidad_miel());
            
            
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

    // hacerr los metodos modificar y eliminar notas de credito (de todas las clases)
    
    
    public DefaultTableModel listarNotasCreditoE(String mesConsulta) {

        //el parametro mesConsulta es para filtrar comprobantes por mes!
        //falta hacerlo
        
        DefaultTableModel modelo;

        String[] titulos = {"ID NOTA CREDITO", "FECHA", "IMPORTE"};

        String[] registros = new String[3];

        modelo = new DefaultTableModel(null, titulos) {
            
        };
        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT codigo_nota_credito, fecha_nota_credito, importe_total_nota_credito from nota_credito_cliente WHERE codigo_nota_credito <> '1' and tipo_nota_credito = 'NOTA DE CREDITO E' and fecha_nota_credito BETWEEN '2022-09-01' AND '2022-09-30' ORDER BY codigo_nota_credito");

            while (rs.next()) {
                
                registros[0] = rs.getString("codigo_nota_credito");
                registros[1] = rs.getString("fecha_nota_credito");
                registros[2] = rs.getString("importe_total_nota_credito");

                modelo.addRow(registros);
                
            }
            
            ConexionBD.close(cn);
            ConexionBD.close(st);
            ConexionBD.close(rs);
            
        } catch (Exception e) {
            
        }
        
        return modelo;
        
    }
    
    public Double mostrarImporteNotaCredito(int codigoNotaCredito) {
        
        Double importeFactura = 0.00;

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT importe_total_nota_credito from nota_credito_cliente WHERE codigo_nota_credito = '" + codigoNotaCredito + "'");

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
            ResultSet rs = st.executeQuery("SELECT importe_total_nota_credito, cantidad_miel_afectada from nota_credito_cliente WHERE codigo_nota_credito = '" + codigoNotaCredito + "'");

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
    
    public String mostrarNombreClienteNotaCredito(int codigoNotaCredito) {
        
        String nombreCliente = "";

        
        try {
            
            ConexionBD mysql = new ConexionBD();
            Connection cn = mysql.getConexionBD();
            
            Statement st = cn.createStatement();
            //select r.nombre from cta_cte_productor c join productor p on c.codigo_productor = p.cod_productor JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = 28 and descripcion_movimiento = "FACTURA A"
            ResultSet rs = st.executeQuery("select r.nombre from cta_cte_cliente c join cliente p on c.codigo_cliente = p.cod_cliente JOIN persona r on p.cod_persona = r.cod_persona where comprobante_asociado = '" + codigoNotaCredito + "' and descripcion_movimiento = 'NOTA DE CREDITO E'");

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