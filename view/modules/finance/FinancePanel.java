package view.modules.finance;

import view.AplicationContext;
import view.dashboard.Dahsboard;
import view.table.schemas.CuentasCobrarSchema;
import view.table.schemas.CuentasPagarSchema;

import view.table.TableFactory;
import view.table.schemas.TableSchema;
import view.table.style.TableStyle;
import view.table.style.TableStyleConfigure;
import model.CuentaCobrar;

import javax.swing.*;
import java.awt.*;
import model.CuentaPagar;

import java.util.List;

public class FinancePanel extends JPanel {
    private final AplicationContext context;
    private final Dahsboard dahsboard;
    private JScrollPane scrollPaneCuentasCobrar, scrollPaneCuentasPagar;
    private List<CuentaCobrar> cobrarData;
    private JTable table;
    private TableSchema<CuentaCobrar> cobrarSchema;
    private List<CuentaPagar> pagarData;
    private TableSchema<CuentaPagar> pagarSchema;

    public FinancePanel(AplicationContext context, Dahsboard dahsboard) {
        this.context = context;
        this.dahsboard = dahsboard;
        crearPanelViewPortCuentasCobrar();
        crearPanelViewPortCuentasPagar();
    }

     public void crearPanelViewPortCuentasCobrar() {
        cobrarData = context.getCuentaCobrarController().listarCuentas();
        cobrarSchema = CuentasCobrarSchema.create();
        table = TableFactory.createTable(cobrarData, cobrarSchema);
        table.setBackground(TableStyleConfigure.COLOR_ROW_BG);

        table.setShowVerticalLines(true);
        table.setGridColor(new Color(0xE5E7EB));
        table.setBorder(BorderFactory.createEmptyBorder());
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));

        TableStyle.apply(table);
        table.setBounds(0, 0, 250, 253);

        scrollPaneCuentasCobrar = new JScrollPane(table);
        scrollPaneCuentasCobrar.setBounds(10, 107, 481, 320);
        scrollPaneCuentasCobrar.getViewport().setBorder(null);
        scrollPaneCuentasCobrar.getViewport().setBackground(null);
        scrollPaneCuentasCobrar.setOpaque(false);
        scrollPaneCuentasCobrar.getViewport().setOpaque(false);
        UIManager.put("ScrollBar.showButtons", false);
        UIManager.put("ScrollBar.width", 12);

        add(scrollPaneCuentasCobrar);
    }

    public void crearPanelViewPortCuentasPagar() {
		
		pagarData =  context.getCuentaPagarController().listarCuentas();
		pagarSchema = CuentasPagarSchema.create();
		table = TableFactory.createTable(pagarData, pagarSchema);
		table.setBackground(TableStyleConfigure.COLOR_ROW_BG);
		
		table.setShowVerticalLines(true);
		table.setGridColor(new Color(0xE5E7EB));
		table.setBorder(BorderFactory.createEmptyBorder());
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		
	    TableStyle.apply(table);
		table.setBounds(0, 0,  250, 253);

		scrollPaneCuentasPagar = new JScrollPane(table);
		scrollPaneCuentasPagar.setBounds(10, 107, 481, 320);
		scrollPaneCuentasPagar.getViewport().setBorder(null);
		scrollPaneCuentasPagar.getViewport().setBackground(null);
		scrollPaneCuentasPagar.setOpaque(false);
		scrollPaneCuentasPagar.getViewport().setOpaque(false);
		UIManager.put("ScrollBar.showButtons", false);
		UIManager.put("ScrollBar.width", 12);
		
		add(scrollPaneCuentasPagar);
	}

}