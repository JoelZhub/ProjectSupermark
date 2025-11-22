package view.table.schemas;

import java.util.ArrayList;
import java.util.List;

import view.table.ColumnDefinition;

//clase que genera el esquma de cada table trabaja a partir de las columnas definidas en la clase correspondiente

public class TableSchema<T> {

	private final List<ColumnDefinition<T>> colums; // clase que define las columnas
	private final int rowHeight;
	private final boolean singleSection;
	
	public TableSchema(Builder<T> builder){
		
		this.colums  = builder.colums;
		this.rowHeight = builder.rowHeight;
		this.singleSection = builder.singleSection;
	}
	
	public List<ColumnDefinition<T>> getColums(){	
		return colums;
	}
	
	public int getRowHeight() {
		return rowHeight; 
	}
	
	public boolean getSingleSection() {
		return singleSection;
	}
	
	
	//clase interna que gestiona el cuerpo de cada esquema
	public static class Builder<T>{
		
		private final  List<ColumnDefinition<T>> colums = new ArrayList<>();
		private int rowHeight = 28;
		private boolean singleSection = true;
		
		
		public Builder<T> addColumn(ColumnDefinition<T> column){
			colums.add(column);
			return this;
		}
		
		public Builder<T> rowHeight(int height){
			this.rowHeight = height;
			return this;
		}
		
		public Builder<T> singleSection(boolean singleSection){
				this.singleSection = singleSection;
				return this;
		}
		
		
		public TableSchema<T> build(){
			
			if(colums.isEmpty()) {
				  throw new IllegalStateException("Un schema necesita al menos una columna.");
			}
			return new TableSchema<>(this);
		}
		
		
	}
	
	
}

