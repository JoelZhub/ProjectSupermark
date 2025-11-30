package view.table.schemas;

import model.User;
import view.table.ColumnDefinition;

public class UsuariosSchema {

	//descomentar cuando se reciba la clase que gestiona la lista de usuarios
	
//	
	 public static TableSchema<User> create(){
	  
	  return new TableSchema.Builder<User>()
         .addColumn(new ColumnDefinition.Builder<User>()
                .header("Nombre")
                .key("nombre")
                .value(User::getNombre)
                .preferredWidth(180)
                .build()
        )
   
         .addColumn(new ColumnDefinition.Builder<User>()
                 .header("Email")
                 .key("email")
                 .value(User::getEmail)
                 .preferredWidth(80)
                 .build()
         )
        .addColumn(new ColumnDefinition.Builder<User>()
                .header("Rol")
                .key("rol")
                .value(p -> p.getRol()) 
                .preferredWidth(120)
                .build()
        )
        .rowHeight(70)
        .singleSection(true)
        .build();	
	  
	  }
	  
	 
}
