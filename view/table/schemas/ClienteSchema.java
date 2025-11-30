package view.table.schemas;
import model.Cliente;
import view.table.ColumnDefinition;

public class ClienteSchema {

	public static TableSchema<Cliente> create(){
		
		return new TableSchema.Builder<Cliente>()
			
		.addColumn(new ColumnDefinition.Builder<Cliente>()
			                .header("Identificacion")
			                .key("identificiacion")
			                .value(Cliente::getIdentificacion)
			                .preferredWidth(120)
			                .build()
				)
			       
        .addColumn(new ColumnDefinition.Builder<Cliente>()
                .header("Nombre")
                .key("nombre")
                .value(Cliente::getNombre)
                .preferredWidth(120)
                .build()
        )
       
        .addColumn(new ColumnDefinition.Builder<Cliente>()
                .header("Clasificacion")
                .key("clasificacion")
                .value(Cliente::getClasificacion) 
                .preferredWidth(80)
                .build()
        )
        
        .addColumn(new ColumnDefinition.Builder<Cliente>()
                .header("Telefono")
                .key("telefono")
                .value(Cliente::getTelefono)
                .preferredWidth(60)
                .build()
        )
        
        .addColumn(new ColumnDefinition.Builder<Cliente>()
                .header("Activo")
                .key("Activo")
                .value( p -> p.getActivo() == 0 ? "Bloqueado" : "Activo")
                .preferredWidth(60)
                .build()
        )
        .rowHeight(35)
        .singleSection(true)
        .build();		  
	}
}
