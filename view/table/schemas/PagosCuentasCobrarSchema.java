package view.table.schemas;

import model.CuentaCobrarPago;
import view.table.ColumnDefinition;

public class PagosCuentasCobrarSchema {

    public static TableSchema<CuentaCobrarPago> create() {
        return new TableSchema.Builder<CuentaCobrarPago>()
                .addColumn(new ColumnDefinition.Builder<CuentaCobrarPago>()
                        .header("Número cuenta")
                        .key("idCuenta")
                        .value(p -> Integer.toString(p.getIdCuenta())) // ajustar nombre del getter si es distinto
                        .preferredWidth(80)
                        .build()
                )
                .addColumn(new ColumnDefinition.Builder<CuentaCobrarPago>()
                        .header("Fecha")
                        .key("fecha")
                        .value(CuentaCobrarPago::getFecha) // puede ser String o Date->toString
                        .preferredWidth(120)
                        .build()
                )
                .addColumn(new ColumnDefinition.Builder<CuentaCobrarPago>()
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