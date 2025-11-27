package view.table.schemas;

import model.Producto;
import view.table.ColumnDefinition;

public class ProductosSchemaStockBajo {

public static TableSchema<Producto> create(){
		
		return new TableSchema.Builder<Producto>()
		.addColumn(new ColumnDefinition.Builder<Producto>()
                .header("Producto")
                .key("nombreP")
                .value(Producto::getNombre)
                .preferredWidth(120)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Producto>()
                .header("Cantidad")
                .key("cantidad")
                .value(Producto::getCantida)
                .preferredWidth(40)
                .build()
        )
        .rowHeight(28)
        .singleSection(true)
        .build();		  
	}
}
