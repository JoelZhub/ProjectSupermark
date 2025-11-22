package view.table.schemas;

import model.Producto;
import view.table.ColumnDefinition;

public class ProductosSchema {

	
	public static TableSchema<Producto> create(){
		
		return new TableSchema.Builder<Producto>()
		.addColumn(new ColumnDefinition.Builder<Producto>()
                .header("ID")
                .key("id")
                .value(Producto::getCodigo)
                .preferredWidth(80)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Producto>()
                .header("Nombre")
                .key("nombre")
                .value(Producto::getNombre)
                .preferredWidth(180)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Producto>()
                .header("Precio")
                .key("precio")
                .value(p -> p.getPrecio())   
                .preferredWidth(90)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Producto>()
                .header("Categoría")
                .key("categoria")
                .value(p -> p.getCategoria()) 
                .preferredWidth(120)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Producto>()
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
	
}
