package Componentes;

import java.awt.Component;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderCantidadconPuntos extends DefaultTableCellRenderer {
    private static DecimalFormatSymbols simbolos;
    public static DecimalFormat formato;
    
        
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        simbolos.setGroupingSeparator(',');
        formato = new DecimalFormat("#,###.##", simbolos);
        value = formato.format(Double.parseDouble(String.valueOf(value)));
        //this.setBackground(new java.awt.Color(231,253,235));
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

