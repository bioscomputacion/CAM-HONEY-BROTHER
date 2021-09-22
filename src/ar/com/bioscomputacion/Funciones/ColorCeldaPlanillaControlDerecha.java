/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bioscomputacion.Funciones;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Brayan Godoy
 */
public class ColorCeldaPlanillaControlDerecha extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
        setHorizontalAlignment(SwingConstants.RIGHT);
        if (!table.getValueAt(row, 7).toString().equals("0.00")) {

            setBackground(new Color(249, 137, 113));
            setForeground(Color.black);

        } else if (table.getValueAt(row, 7).toString().equals("0.00")) {
            setBackground(new Color(146, 208, 80));
            setForeground(Color.black);
        }

        return this;

    }

}
