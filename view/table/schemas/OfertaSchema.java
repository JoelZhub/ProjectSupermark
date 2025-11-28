package view.table.schemas;
import model.Oferta;
import view.table.ColumnDefinition;

public class OfertaSchema {

	public static TableSchema<Oferta> create(){
		
		return new TableSchema.Builder<Oferta>()
			
				.addColumn(new ColumnDefinition.Builder<Oferta>()
		                .header("Oferta")
		                .key("oferta")
		                .value(Oferta::getOferta)
		                .preferredWidth(55)
		                .build()
		        )
		        .addColumn(new ColumnDefinition.Builder<Oferta>()
		                .header("Monto")
		                .key("monto")
		                .value(p -> p.getPorcentaje())
		                .preferredWidth(45)
		                .build()
		        )
		        .addColumn(new ColumnDefinition.Builder<Oferta>()
		                .header("Fecha Inicio")
		                .key("fechaInicio")
		                .value(Oferta::getFechaInicio)   
		                .preferredWidth(50)
		                .build()
		        )
		        .addColumn(new ColumnDefinition.Builder<Oferta>()
		                .header("Fecha Fin")
		                .key("fechaFin")
		                .value(Oferta::getFechaFin) 
		                .preferredWidth(50)
		                .build()
		        )
		        
		        .addColumn(new ColumnDefinition.Builder<Oferta>()
		                .header("Activo")
		                .key("activo")
		                .value(Oferta::getActivo) 
		                .preferredWidth(25)
		                .build()
		        
		          )
		        .rowHeight(28)
		        .singleSection(true)
		        .build();	
		
	}
	
}
