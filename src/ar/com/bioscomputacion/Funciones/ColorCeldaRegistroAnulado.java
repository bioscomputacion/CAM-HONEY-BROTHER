/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Funciones;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author carlo
 */
public class ColorCeldaRegistroAnulado extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col){
        
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
        setHorizontalAlignment(SwingConstants.CENTER);
        
        if (table.getValueAt(row, 10).toString().equals("ANULADO")){
            
            setBackground(new Color(255, 0, 51));
            setForeground(Color.BLACK);
        
        }
        else{
            
            setBackground(new Color(146, 208, 80));
            setForeground(Color.BLACK);
        
        }
        
        if (Selected){
        
            setBackground(new Color(30, 129, 176));
            
        }
        
        return this;
    
    }
    
}
