package view.table.schemas;

import model.Producto;
import view.table.ColumnDefinition;

public class VentasSchema {

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
                				return "No Disponible";
                			}
                			if(p.getCantida() == 0 && p.getActivo() == 1) {
                				return "No Disponible";
                			}
							return "Disponible";
                		}
                		
                		)
                .preferredWidth(80)
                .build()
        )
        .rowHeight(80)
        .singleSection(true)
        .build();		  
	}

}
