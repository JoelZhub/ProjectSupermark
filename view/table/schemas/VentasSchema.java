package view.table.schemas;

import view.table.ColumnDefinition;

public class VentasSchema {

	
	//cambiar los campos por la clase de ventas
	
	/*
	 * public static TableSchema<Ventas> create(){
		
		return new TableSchema.Builder<Ventas>()
		.addColumn(new ColumnDefinition.Builder<Ventas>()
                .header("ID")
                .key("id")
                .value(Ventas::getCodigo)
                .preferredWidth(80)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Ventas>()
                .header("Nombre")
                .key("nombre")
                .value(Producto::getNombre)
                .preferredWidth(180)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Ventas>()
                .header("Precio")
                .key("precio")
                .value(p -> p.getPrecio())   
                .preferredWidth(90)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Ventas>()
                .header("Categoría")
                .key("categoria")
                .value(p -> p.getCategoria()) 
                .preferredWidth(120)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Ventas>()
                .header("Cantidad")
                .key("cantidad")
                .value(Producto::getCantida)
                .preferredWidth(80)
                .build()
        )
        .rowHeight(28)
        .singleSection(true)
        .build();		  
	}
	
	 * */
}
