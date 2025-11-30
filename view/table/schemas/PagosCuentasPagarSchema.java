package view.table.schemas;

import model.CuentaPagarPago;
import view.table.ColumnDefinition;

public class PagosCuentasPagarSchema {

    public static TableSchema<CuentaPagarPago> create() {
        return new TableSchema.Builder<CuentaPagarPago>()
                .addColumn(new ColumnDefinition.Builder<CuentaPagarPago>()
                        .header("Número cuenta")
                        .key("idCuenta")
                        .value(p -> Integer.toString(p.getIdCuenta())) // ajustar si getter es distinto
                        .preferredWidth(80)
                        .build()
                )
                .addColumn(new ColumnDefinition.Builder<CuentaPagarPago>()
                        .header("Fecha")
                        .key("fecha")
                        .value(CuentaPagarPago::getFecha)
                        .preferredWidth(120)
                        .build()
                )
                .addColumn(new ColumnDefinition.Builder<CuentaPagarPago>()
                        .header("Monto")
                        .key("monto")
                        .value(p -> String.format("%.2f", p.getMonto()))
                        .preferredWidth(100)
                        .build()
                )
                .rowHeight(30)
                .singleSection(true)
                .build();
    }

}