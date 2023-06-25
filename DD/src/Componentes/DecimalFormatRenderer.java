/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.awt.Component;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ADMIN
 */
public class DecimalFormatRenderer extends DefaultTableCellRenderer {
        
    private static DecimalFormatSymbols simbolos;
    private static DecimalFormat formato;
        
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        simbolos.setGroupingSeparator(',');
        formato = new DecimalFormat("#,###", simbolos);
        value = formato.format(Integer.parseInt((String) value));
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
