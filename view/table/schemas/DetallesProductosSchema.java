package view.table.schemas;

import model.Detalles;
import view.table.ColumnDefinition;

public class DetallesProductosSchema {

	public static TableSchema<Detalles> create(){
		
		return new TableSchema.Builder<Detalles>()
				//cuando se crre el controlador de detalles de producto, cambiar este campo
				//por el nombre del producto
				.addColumn(new ColumnDefinition.Builder<Detalles>()
		                .header("ID")
		                .key("id")
		                .value(Detalles::getIdProducto)
		                .preferredWidth(40)
		                .build()
		        )
		        .addColumn(new ColumnDefinition.Builder<Detalles>()
		                .header("Marca")
		                .key("marca")
		                .value(Detalles::getMarca)
		                .preferredWidth(80)
		                .build()
		        )
		        .addColumn(new ColumnDefinition.Builder<Detalles>()
		                .header("Proeevedor")
		                .key("Proevedor")
		                .value(p -> p.getProveedor().getNombre())   
		                .preferredWidth(80)
		                .build()
		        )
		        .addColumn(new ColumnDefinition.Builder<Detalles>()
		                .header("Origen")
		                .key("origen")
		                .value(Detalles::getOrigen) 
		                .preferredWidth(60)
		                .build()
		        )
		        .addColumn(new ColumnDefinition.Builder<Detalles>()
		                .header("Fecha")
		                .key("fecha")
		                .value(Detalles::getFechaAgregado)
		                .preferredWidth(50)
		                .build()
		        )
		        .rowHeight(28)
		        .singleSection(true)
		        .build();	
		
	}
	
}
