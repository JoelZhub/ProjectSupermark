package view.table;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import view.table.schemas.TableSchema;

import java.util.List;

public class TableFactory {

	public static <T> JTable createTable(List<T> data, TableSchema<T> schema) {

		UniversalTableModel<T> model =  new UniversalTableModel<>(data, schema);
		
		JTable table = new JTable(model);
		
		table.setRowHeight(schema.getRowHeight());
		
		if(schema.getSingleSection()) {
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}else {
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		}
		
		configureColumnWidths(table, schema);
		
		return table;
		
	}
	
	public static <T> void  configureColumnWidths(JTable table, TableSchema<T> schema) {
		
		for(int i = 0; i < schema.getColums().size(); i++) {
			ColumnDefinition<T> col = schema.getColums().get(i);
			
			if(col.getPreferredWidth() > 0) {	
				TableColumn column  = table.getColumnModel().getColumn(i);
				column.setPreferredWidth(col.getPreferredWidth());
			}
		}
		
	}
	
}
