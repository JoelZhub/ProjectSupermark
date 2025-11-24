package view.table.schemas;
import model.Oferta;
import view.table.ColumnDefinition;

public class OfertaSchema {

	public static TableSchema<Oferta> create(){
		
		return new TableSchema.Builder<Oferta>()
				.addColumn(new ColumnDefinition.Builder<Oferta>()
		                .header("ID")
		                .key("id")
		                .value(Oferta::getIdProducto)
		                .preferredWidth(25)
		                .build()
		        )
		        .addColumn(new ColumnDefinition.Builder<Oferta>()
		                .header("Porcentaje")
		                .key("porcentaje")
		                .value(Oferta::getPorcentaje)
		                .preferredWidth(35)
		                .build()
		        )
		        .addColumn(new ColumnDefinition.Builder<Oferta>()
		                .header("Fecha Inicio")
		                .key("fechaInicio")
		                .value(Oferta::getFechaInicio)   
		                .preferredWidth(35)
		                .build()
		        )
		        .addColumn(new ColumnDefinition.Builder<Oferta>()
		                .header("Fecha fin")
		                .key("fechaFin")
		                .value(Oferta::getFechaFin) 
		                .preferredWidth(35)
		                .build()
		        )
		        .rowHeight(28)
		        .singleSection(true)
		        .build();	
		
	}
	
}
