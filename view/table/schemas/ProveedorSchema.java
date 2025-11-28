package view.table.schemas;

import model.Proveedor;
import view.table.ColumnDefinition;

public class ProveedorSchema {

	
	public static TableSchema<Proveedor> create(){
		
		return new TableSchema.Builder<Proveedor>()
				
		.addColumn(new ColumnDefinition.Builder<Proveedor>()
			                .header("Rnc")
			                .key("rcn")
			                .value(Proveedor::getRncProveedor)   
			                .preferredWidth(60)
			                .build()
		)
        .addColumn(new ColumnDefinition.Builder<Proveedor>()
                .header("Proveedor")
                .key("proveedor")
                .value(Proveedor::getNombre)
                .preferredWidth(120)
                .build()
        )
       
        .addColumn(new ColumnDefinition.Builder<Proveedor>()
                .header("País")
                .key("pais")
                .value(Proveedor::getPais) 
                .preferredWidth(80)
                .build()
        )
        
        .addColumn(new ColumnDefinition.Builder<Proveedor>()
                .header("Activo")
                .key("Activo")
                .value( p -> p.getActivo() == 0 ? "Bloqueado" : "Disponible")
                .preferredWidth(60)
                .build()
        )
        .rowHeight(35)
        .singleSection(true)
        .build();		  
	}
}
