package view.table.schemas;

import model.Producto;
import view.table.ColumnDefinition;

public class UsuariosSchema {

	//descomentar cuando se reciba la clase que gestiona la lista de usuarios
	
	
	/* public static TableSchema<Usuarios> create(){
	 * 
	 * return new TableSchema.Builder<Usuarios>()
	 * .addColumn(new ColumnDefinition.Builder<Usuarios>()
                .header("ID")
                .key("id")
                .value(Usuarios::getId)
                .preferredWidth(80)
                .build()
        )
        
         .addColumn(new ColumnDefinition.Builder<Usuarios>()
                .header("Nombre")
                .key("nombre")
                .value(Usuarios::getNombre)
                .preferredWidth(180)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Usuarios>()
                .header("Correo")
                .key("correo")
                .value(p -> p.getCorreo())   
                .preferredWidth(90)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Usuarios>()
                .header("Rol")
                .key("rol")
                .value(p -> p.getRol()) 
                .preferredWidth(120)
                .build()
        )
        
        .rowHeight(28)
        .singleSection(true)
        .build();	
	 * 
	 * }
	 * 
	 * */
}
