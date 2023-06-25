package Componentes;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RenderPagos extends DefaultTableCellRenderer {
    
        
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        value = (String.valueOf(value));   
        if(value.equals("PAGO PENDIENTE")){        
            this.setForeground(new java.awt.Color(255,0,0));
        }else if(value.equals("PAGO PARCIAL")){
            this.setForeground(new java.awt.Color(255,102,0));
        }else if(value.equals("PAGO ABONADO")){
            this.setForeground(new java.awt.Color(0,102,102));
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

