package view.table;

import java.util.function.Function;


//esta clase gestiona las columnas de las diversas tables que se mostraran en los diversos modulos

public class ColumnDefinition<T> {

	private final String header;
	private final String key;
	private final Function<T, Object> valueGetter;
	private boolean editable;
	private final int preferredWidth;
	
	public ColumnDefinition(Builder<T> builder) {
		
		this.header = builder.header;
		this.key = builder.key;
		this.valueGetter = builder.valueGetter;
		this.editable = builder.editable;
		this.preferredWidth = builder.preferredWidth;
	}
	
	
	public String getHeader() {
		return header;
	}
	
	public String getKey() {
		return key; 
	}
	
	public Function<T, Object> getValueGetter(){
		return valueGetter;
	}
	
	public boolean getEditable() {
		return editable;
		
	}
	
	public int getPreferredWidth() {
		return preferredWidth;
	}
	
	
	//clase interna que gestiona el cuerpo de cada columna
	public static class  Builder<T>{
		
		private String header;
		private String key;
		private Function<T, Object> valueGetter;
		private boolean editable = false;
		private int preferredWidth = 1;
		
		public Builder<T> header(String header){
			this.header = header;
			return this;
		}
		
		public Builder<T> key(String key){
			this.key = key;
			return this;
		}
		public Builder<T> value(Function<T, Object> getter){
			this.valueGetter = getter;
			return this;
		}
		
		public Builder<T> editable(boolean editable){
			this.editable = editable;
			return this;
		}
		
		public Builder<T> preferredWidth(int width){
			this.preferredWidth = width;
			return this;
		}
		
		public ColumnDefinition<T> build() {
			if(header == null || key == null || valueGetter == null) {
				throw new IllegalStateException("Header, key y valueGetter son obligatorios.");
			}
			return new ColumnDefinition<>(this);
			
		}
	}
	
}
