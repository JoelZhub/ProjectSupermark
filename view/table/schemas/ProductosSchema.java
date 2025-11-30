package view.table.schemas;

import model.Producto;
import view.table.ColumnDefinition;

public class ProductosSchema {

	
	public static TableSchema<Producto> create(){
		
		return new TableSchema.Builder<Producto>()
        .addColumn(new ColumnDefinition.Builder<Producto>()
                .header("Producto")
                .key("nombreP")
                .value(Producto::getNombre)
                .preferredWidth(150)
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
                .preferredWidth(50)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Producto>()
                .header("Activo")
                .key("Activo")
                .value( p ->
                		{
                			if(p.getActivo() == 0) {
                				return "Bloqueado";
                			}
                			if(p.getCantida() == 0 && p.getActivo() == 1) {
                				return "Sin Stock";
                			}
							return "Disponible";
                			
                		}
                		
                		)
                .preferredWidth(80)
                .build()
        )
        .rowHeight(35)
        .singleSection(true)
        .build();		  
	}
	
}
