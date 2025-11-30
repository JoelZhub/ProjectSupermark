package view.table.schemas;

import model.Factura;
import view.AplicationContext;
import view.table.ColumnDefinition;

public class FacturasSchema {

	///completar con los atributos de la clase facturas cuando este creada 
	//descomentar cuando se cree la clase que manejara las facturas
	private static AplicationContext context;
	
	@SuppressWarnings("static-access")
	public static void setContext(AplicationContext context) {
		FacturasSchema.context = context;
	}

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
       
        .addColumn(new ColumnDefinition.Builder<Factura>()
                .header("Nombre Cliente")
                .key("idCliente")
                .value(p -> {
                	
                	if(p.getClienteId() == 0) return "No Aplica";
                	return  context.getClienteController().buscarCliente(p.getClienteId()).getNombre()  ;
                	
                })
                .preferredWidth(80)
                .build()
        )
        .addColumn(new ColumnDefinition.Builder<Factura>()
                .header("N.Caja")
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
