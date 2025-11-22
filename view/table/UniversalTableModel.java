package view.table;
import javax.swing.table.AbstractTableModel;

import view.table.schemas.TableSchema;

import java.util.List;

//esta clase es la que construlle la table deseada a partir de lo que se requiera

public class UniversalTableModel<T> extends AbstractTableModel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final List<T> data;
	private final TableSchema<T> schema;
	
	public UniversalTableModel(List<T> data, TableSchema<T> schema) {
		this.data = data;
		this.schema = schema;
	}
	
	
	
	@Override
	public int getRowCount() {
		return data == null ? 0  : data.size();
	}

	@Override
	public int getColumnCount() {
		return schema.getColums().size();
	}

	@Override
	public String getColumnName(int column) {
		return schema.getColums().get(column).getHeader();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		T object = data.get(rowIndex);
		ColumnDefinition<T> col  = schema.getColums().get(columnIndex);
		return col.getValueGetter().apply(object);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return schema.getColums().get(columnIndex).getEditable();
	}
}
