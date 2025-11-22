package view.table.schemas;

import view.table.ColumnDefinition;

public class FacturasSchema {

	///completar con los atributos de la clase facturas cuando este creada 
	//descomentar cuando se cree la clase que manejara las facturas
	
	
	/* public static TableSchema<Facturas> create(){
		
		return new TableSchema.Builder<Facturas>()
		.addColumn(new ColumnDefinition.Builder<Facturas>()
                .header("ID")
                .key("id")
                .value(Facturas::getCodigo)
                .preferredWidth(25)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Facturas>()
                .header("Producto")
                .key("Producto")
                .value(p -> p.getProducto().getNombre())
                .preferredWidth(180)
                .build()
        )
        
        .addColumn(new ColumnDefinition.Builder<Facturas>()
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
	 * 
	 * 
	 * */
}
