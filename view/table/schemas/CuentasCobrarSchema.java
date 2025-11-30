package view.table.schemas;

import model.CuentaCobrar;
import view.table.ColumnDefinition;

public class CuentasCobrarSchema {

    public static TableSchema<CuentaCobrar> create() {
        return new TableSchema.Builder<CuentaCobrar>()
                .addColumn(new ColumnDefinition.Builder<CuentaCobrar>()
                        .header("Cliente")
                        .key("cliente")
                        .value(c -> String.format(c.getIdCliente())) // ajustar si devuelve objeto/Nombre
                        .preferredWidth(160)
                        .build()
                )
                .addColumn(new ColumnDefinition.Builder<CuentaCobrar>()
                        .header("Monto Total")
                        .key("montoTotal")
                        .value(c -> String.format("%.2f", c.getMontoTotal()))
                        .preferredWidth(100)
                        .build()
                )
                .addColumn(new ColumnDefinition.Builder<CuentaCobrar>()
                        .header("Monto Pendiente")
                        .key("montoPendiente")
                        .value(c -> String.format("%.2f", c.getMontoPendiente()))
                        .preferredWidth(120)
                        .build()
                )
                .addColumn(new ColumnDefinition.Builder<CuentaCobrar>()
                        .header("Estado")
                        .key("estado")
                        .value(CuentaCobrar::getEstado)
                        .preferredWidth(100)
                        .build()
                )
                .rowHeight(35)
                .singleSection(true)
                .build();
    }

}