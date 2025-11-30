package view.table.schemas;

import model.CuentaPagar;
import view.table.ColumnDefinition;

public class CuentasPagarSchema {

    public static TableSchema<CuentaPagar> create() {

        return new TableSchema.Builder<CuentaPagar>()
                .addColumn(new ColumnDefinition.Builder<CuentaPagar>()
                        .header("Proveedor")
                        .key("idProveedor")
                        .value(c -> Integer.toString(c.getIdProveedor()))
                        .preferredWidth(100)
                        .build()
                )
                .addColumn(new ColumnDefinition.Builder<CuentaPagar>()
                        .header("Monto Total")
                        .key("montoTotal")
                        .value(c -> String.format("%.2f", c.getMontoTotal()))
                        .preferredWidth(100)
                        .build()
                )
                .addColumn(new ColumnDefinition.Builder<CuentaPagar>()
                        .header("Monto Pendiente")
                        .key("montoPendiente")
                        .value(c -> String.format("%.2f", c.getMontoPendiente()))
                        .preferredWidth(120)
                        .build()
                )
                .addColumn(new ColumnDefinition.Builder<CuentaPagar>()
                        .header("Estado")
                        .key("estado")
                        .value(CuentaPagar::getEstado)
                        .preferredWidth(100)
                        .build()
                )
                .rowHeight(35)
                .singleSection(true)
                .build();
    }

}