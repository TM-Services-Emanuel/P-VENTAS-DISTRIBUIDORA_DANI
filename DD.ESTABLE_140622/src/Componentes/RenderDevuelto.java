package Componentes;

import java.awt.Component;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderDevuelto extends DefaultTableCellRenderer {
    private static DecimalFormatSymbols simbolos;
    public static DecimalFormat formato;
    
        
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.setBackground(new java.awt.Color(255,178,0));
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

