package Componentes;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderCentrar extends DefaultTableCellRenderer {
    
    DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(column).setCellRenderer(cellRenderer);
        
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

