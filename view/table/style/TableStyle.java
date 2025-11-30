package view.table.style;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class TableStyle {

	
	public static void apply(JTable table) {
		
		table.setRowHeight(TableStyleConfigure.ROW_HEIGHT);
		table.setShowGrid(false);
	    table.setIntercellSpacing(new Dimension(4, 4));
	    
        //table.setBorder(BorderFactory.createLineBorder(TableStyleConfigure.COLOR_TABLE_BORDER));
        
        table.setSelectionBackground(TableStyleConfigure.COLOR_SELECTION_BG);
        table.setSelectionForeground(TableStyleConfigure.COLOR_SELECTION_TEXT);

        table.setFont(TableStyleConfigure.FONT_CELL);
        table.setForeground(TableStyleConfigure.COLOR_TEXT);
        table.setBackground(TableStyleConfigure.COLOR_ROW_BG);
        
        
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), TableStyleConfigure.HEADER_HEIGHT));
        header.setFont(TableStyleConfigure.FONT_HEADER);
        header.setBackground(TableStyleConfigure.COLOR_HEADER_BG);
        header.setForeground(TableStyleConfigure.COLOR_HEADER_TEXT);
        header.setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, TableStyleConfigure.COLOR_HEADER_BORDER_BOTTOM
        ));
        
        
        table.setDefaultRenderer(Object.class, new CorporateCellRenderer());

	}
	
	
    private static class CorporateCellRenderer extends DefaultTableCellRenderer {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int col
        ) {
            Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, col
            );

            c.setFont(TableStyleConfigure.FONT_CELL);
//           setBorder(BorderFactory.createEmptyBorder(
//                    2,
//                    TableStyleConfigure.CELL_PADDING,
//                    3,
//                    TableStyleConfigure.CELL_PADDING
//            ));

            if (isSelected) {
                c.setBackground(TableStyleConfigure.COLOR_SELECTION_BG);
                c.setForeground(TableStyleConfigure.COLOR_SELECTION_TEXT);
            } else {
                c.setBackground(TableStyleConfigure.COLOR_ROW_BG);
                c.setForeground(TableStyleConfigure.COLOR_TEXT);

//                setBorder(BorderFactory.createMatteBorder(
//                        0,
//                        TableStyleConfigure.CELL_PADDING,
//                        1,
//                        TableStyleConfigure.CELL_PADDING,
//                        TableStyleConfigure.COLOR_ROW_BORDER
//                ));
            }

            return c;
        }
    }
	
}
