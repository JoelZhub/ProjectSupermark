package view.table.schemas;

import model.Factura;
import view.table.ColumnDefinition;

public class FacturasSchema {

	///completar con los atributos de la clase facturas cuando este creada 
	//descomentar cuando se cree la clase que manejara las facturas
	
	
	 public static TableSchema<Factura> create(){
		
		return new TableSchema.Builder<Factura>()
        .addColumn(new ColumnDefinition.Builder<Factura>()
                .header("Fecha")
                .key("fecha")
                .value(Factura::getFecha)
                .preferredWidth(180)
                .build()
        )
        
        .addColumn(new ColumnDefinition.Builder<Factura>()
                .header("Total")
                .key("total")
                .value(p -> p.getTotal())   
                .preferredWidth(90)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Factura>()
                .header("Metodo de pago")
                .key("metodoPago")
                .value(Factura::getMetodoPago) 
                .preferredWidth(120)
                .build()
        )
        // cambiar esto por el nombre del cliente y
        .addColumn(new ColumnDefinition.Builder<Factura>()
                .header("IDCliente")
                .key("idCliente")
                .value(Factura::getClienteId)
                .preferredWidth(80)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Factura>()
                .header("IDCajero")
                .key("idCajero")
                .value(Factura::getCajeroId)
                .preferredWidth(80)
                .build()
        )
        .rowHeight(28)
        .singleSection(true)
        .build();		  
	}
	
}
