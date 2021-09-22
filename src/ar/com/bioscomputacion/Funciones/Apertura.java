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
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author braya
 */
public class Apertura {

    ConexionBD mysql = new ConexionBD();
    Connection cn = mysql.getConexionBD();

    private int cod_apertura;
    private int cod_usuario;
    private Timestamp fecha;
    private Double saldo;

    public Apertura() {
    }

    public Apertura(int cod_usuario, Timestamp fecha, Double saldo) {
        this.cod_usuario = cod_usuario;
        this.fecha = fecha;
        this.saldo = saldo;
    }

    public int getCod_apertura() {
        return cod_apertura;
    }

    public void setCod_apertura(int cod_apertura) {
        this.cod_apertura = cod_apertura;
    }

    public int getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(int cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public boolean abrirCaja(Apertura datos) {

        try {
            PreparedStatement pst = cn.prepareStatement("INSERT INTO apertura (cod_usuario,fecha,saldo_apertura) VALUES (?,?,?)");
            
            PreparedStatement pst2 = cn.prepareStatement("INSERT INTO planilla (nom_vendedor,fecha_movimiento,rubro,observacion,tipo_moneda,ingresos,egresos)"
                    + " VALUES ((SELECT p.nombre FROM persona p INNER JOIN usuario u ON p.cod_persona = u.cod_persona AND u.cod_usuario ='"+datos.getCod_usuario()+"'),?,?,?,?,?,?)");

            PreparedStatement pst3 = cn.prepareStatement("UPDATE caja SET estado='ABIERTA'");
            
            
            pst.setInt(1, datos.getCod_usuario());
            pst.setTimestamp(2, datos.getFecha());
            pst.setDouble(3, datos.getSaldo());

            pst2.setTimestamp(1, datos.getFecha());
            pst2.setString(2, "OTROS");
            pst2.setString(3, "APERTURA CAJA");
            pst2.setString(4, "EFECTIVO");
            pst2.setDouble(5, datos.getSaldo());
            pst2.setDouble(6, 0.00);

            int N = pst.executeUpdate();
            int N2 = pst2.executeUpdate();
            int N3 = pst3.executeUpdate();

            if (N != 0 || N2 != 0) {
                return true;
            } else {

                return false;
            }

        } catch (Exception e) {

            System.out.println(e);
            return false;
        }

    }
    
    public boolean isCajaAbierta(){
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre,estado FROM caja WHERE estado='ABIERTA'");
            
            while(rs.next()){
                return true;
            }
            
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
