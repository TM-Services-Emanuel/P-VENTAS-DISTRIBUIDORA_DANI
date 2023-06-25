package Componentes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MiRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        int cant = Integer.parseInt(table.getValueAt(row, 1).toString());
        int idCaja = 0;
        try {
            String FechaI = String.valueOf(Fecha.fechaCorrecta());
            idCaja=Integer.parseInt(generarCodigos.ObtenerCodigo("SELECT ca_id from caja WHERE ca_fechainicio='" + FechaI + "' and ca_indicador='S'"));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        if (cant == idCaja) {
            this.setOpaque(true);
            this.setBackground(new java.awt.Color(153,255,153));
            this.setForeground(Color.BLACK);
        }else {
            this.setOpaque(false);
            this.setBackground(new java.awt.Color(255,255,255));
            this.setForeground(Color.BLACK);
        }
        return this;
    }

}

