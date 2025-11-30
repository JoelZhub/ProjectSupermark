package view.modules.finance;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import control.CuentaCobrarController;
import control.CuentaPagarController;
import model.CuentaCobrar;
import model.CuentaCobrarPago;
import model.CuentaPagar;
import model.CuentaPagarPago;
import model.OperationType;
import model.SubModulo;
import view.AplicationContext;
import view.components.AssetManager;
import view.components.Filtros;
import view.components.Fonts;
import view.components.Messages;
import view.components.BtnStyle;
import view.dashboard.Dahsboard;
import view.formFactory.FormFactory;
import view.table.TableFactory;
import view.table.UniversalTableModel;
import view.table.schemas.CuentasCobrarSchema;
import view.table.schemas.CuentasPagarSchema;
import view.table.schemas.PagosCuentasCobrarSchema;
import view.table.schemas.PagosCuentasPagarSchema;
import view.table.schemas.TableSchema;
import view.table.style.TableStyle;
import view.table.style.TableStyleConfigure;

public class FinanceModule extends JPanel implements ActionListener, MouseListener {
    private final AplicationContext context;
    private final Dahsboard dahsboard;

    private JScrollPane scrollPaneCuentasCobrar, scrollPaneCuentasPagar;
    private List<CuentaCobrar> cobrarData;
    private JTable tableCobrar;
    private TableSchema<CuentaCobrar> cobrarSchema;
    private List<CuentaPagar> pagarData;
    private JTable tablePagar;
    private TableSchema<CuentaPagar> pagarSchema;

    private JPanel panelContenedorAccionesCrud;
    private JButton btnCrearCxC, btnRegistrarPagoCxC, btnRefrescarCxC;
    private JButton btnCrearCxP, btnRegistrarPagoCxP, btnRefrescarCxP;
    private JTextField textFieldSearchCobrar, textFieldSearchPagar;

    private CuentaCobrarController cuentaCobrarController;
    private CuentaPagarController cuentaPagarController;

    public FinanceModule(Dahsboard dahsboard, AplicationContext context) {

        setLayout(null);
		setBackground(null);
        this.context = context;
        this.dahsboard = dahsboard;

        this.cuentaCobrarController = context.getCuentaCobrarController();
        this.cuentaPagarController = context.getCuentaPagarController();

        //crearPanelOperacionesCurd();
        crearPanelViewPortCuentasCobrar();
        crearPanelViewPortCuentasPagar();
        crearPanelOperacionesOfPr();
    }

    public void crearPanelViewPortCuentasCobrar() {
        cobrarData = cuentaCobrarController.listarCuentas();
        cobrarSchema = CuentasCobrarSchema.create();
        tableCobrar = TableFactory.createTable(cobrarData, cobrarSchema);
        tableCobrar.setBackground(TableStyleConfigure.COLOR_ROW_BG);

        tableCobrar.setShowVerticalLines(true);
        tableCobrar.setGridColor(new Color(0xE5E7EB));
        tableCobrar.setBorder(BorderFactory.createEmptyBorder());
        tableCobrar.setShowGrid(false);
        tableCobrar.setIntercellSpacing(new Dimension(0, 0));

        TableStyle.apply(tableCobrar);
        tableCobrar.setBounds(0, 0, 250, 253);

        scrollPaneCuentasCobrar = new JScrollPane(tableCobrar);
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

        pagarData = cuentaPagarController.listarCuentas();
        pagarSchema = CuentasPagarSchema.create();
        tablePagar = TableFactory.createTable(pagarData, pagarSchema);
        tablePagar.setBackground(TableStyleConfigure.COLOR_ROW_BG);

        tablePagar.setShowVerticalLines(true);
        tablePagar.setGridColor(new Color(0xE5E7EB));
        tablePagar.setBorder(BorderFactory.createEmptyBorder());
        tablePagar.setShowGrid(false);
        tablePagar.setIntercellSpacing(new Dimension(0, 0));

        TableStyle.apply(tablePagar);
        tablePagar.setBounds(0, 0, 250, 253);

        scrollPaneCuentasPagar = new JScrollPane(tablePagar);
        scrollPaneCuentasPagar.setBounds(515, 107, 481, 320);
        scrollPaneCuentasPagar.getViewport().setBorder(null);
        scrollPaneCuentasPagar.getViewport().setBackground(null);
        scrollPaneCuentasPagar.setOpaque(false);
        scrollPaneCuentasPagar.getViewport().setOpaque(false);
        UIManager.put("ScrollBar.showButtons", false);
        UIManager.put("ScrollBar.width", 12);

        add(scrollPaneCuentasPagar);
    }

    public void crearPanelOperacionesOfPr() {
		panelContenedorAccionesCrud = new JPanel();
        panelContenedorAccionesCrud.setLayout(null);
        panelContenedorAccionesCrud.setOpaque(true);
        panelContenedorAccionesCrud.setBackground(null);
        panelContenedorAccionesCrud.setBounds(10, 10, 1043, 36);

        crearBtns();
        panelContenedorAccionesCrud.add(btnCrearCxC);
        panelContenedorAccionesCrud.add(btnRegistrarPagoCxC);
        panelContenedorAccionesCrud.add(btnRefrescarCxC);
        panelContenedorAccionesCrud.add(btnCrearCxP);
        panelContenedorAccionesCrud.add(btnRegistrarPagoCxP);
        panelContenedorAccionesCrud.add(btnRefrescarCxP);

        panelContenedorAccionesCrud.add(textFieldSearchCobrar);
        panelContenedorAccionesCrud.add(textFieldSearchPagar);

        add(panelContenedorAccionesCrud);
	}

    public void crearBtns() {
        btnCrearCxC = new JButton("Nuevo CxC");
        btnCrearCxC.setIcon(AssetManager.icon("etiqueta.png", 18, 18));
        btnCrearCxC.setBounds(0, 0, 120, 35);
        btnCrearCxC.setFocusPainted(false);
        btnCrearCxC.setBorder(BorderFactory.createLineBorder(new Color(73, 230, 115), 2, true));
        BtnStyle.second(btnCrearCxC);
        btnCrearCxC.addActionListener(this);

        btnRegistrarPagoCxC = new JButton("Registrar Pago CxC");
        btnRegistrarPagoCxC.setBounds(130, 0, 160, 35);
        btnRegistrarPagoCxC.setBorder(BorderFactory.createLineBorder(new Color(88, 177, 237), 2, true));
        BtnStyle.second(btnRegistrarPagoCxC);
        btnRegistrarPagoCxC.addActionListener(this);

        btnRefrescarCxC = new JButton();
        btnRefrescarCxC.setBackground(null);
        btnRefrescarCxC.setBorder(BorderFactory.createLineBorder(new Color(140, 255, 179), 2, true));
        btnRefrescarCxC.setIcon(AssetManager.icon("actualizar.png", 28, 28));
        btnRefrescarCxC.setBounds(300, 0, 50, 35);
        btnRefrescarCxC.addActionListener(this);

        btnCrearCxP = new JButton("Nuevo CxP");
        btnCrearCxP.setBounds(360, 0, 120, 35);
        btnCrearCxP.setIcon(AssetManager.icon("etiqueta.png", 18, 18));
        btnCrearCxP.setBorder(BorderFactory.createLineBorder(new Color(73, 230, 115), 2, true));
        BtnStyle.second(btnCrearCxP);
        btnCrearCxP.addActionListener(this);

        btnRegistrarPagoCxP = new JButton("Registrar Pago CxP");
        btnRegistrarPagoCxP.setBounds(490, 0, 160, 35);
        btnRegistrarPagoCxP.setBorder(BorderFactory.createLineBorder(new Color(88, 177, 237), 2, true));
        BtnStyle.second(btnRegistrarPagoCxP);
        btnRegistrarPagoCxP.addActionListener(this);

        btnRefrescarCxP = new JButton();
        btnRefrescarCxP.setBackground(null);
        btnRefrescarCxP.setBorder(BorderFactory.createLineBorder(new Color(140, 255, 179), 2, true));
        btnRefrescarCxP.setIcon(AssetManager.icon("actualizar.png", 28, 28));
        btnRefrescarCxP.setBounds(660, 0, 50, 35);
        btnRefrescarCxP.addActionListener(this);

        textFieldSearchCobrar = new JTextField("Search CxC");
        textFieldSearchCobrar.setBounds(730, 4, 150, 26);
        textFieldSearchCobrar.addMouseListener(this);
        textFieldSearchCobrar.addActionListener(this);
        Filtros.aplicarFiltroNumericoTextField(textFieldSearchCobrar);
        textFieldSearchCobrar.setFont(Fonts.custom);

        textFieldSearchPagar = new JTextField("Search CxP");
        textFieldSearchPagar.setBounds(890, 4, 150, 26);
        textFieldSearchPagar.addMouseListener(this);
        textFieldSearchPagar.addActionListener(this);
        Filtros.aplicarFiltroNumericoTextField(textFieldSearchPagar);
        textFieldSearchPagar.setFont(Fonts.custom);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        /*if (src == textFieldSearchCobrar) handleCobrarSearch();
        else if (src == textFieldSearchPagar) handlePagarSearch();*/
        if (src == btnRefrescarCxC) refreshCuentasCobrar();
        else if (src == btnRefrescarCxP) refreshCuentasPagar();
        else if (src == btnCrearCxC) openCrearCuentaCobrarDialog();
        else if (src == btnCrearCxP) openCrearCuentaPagarDialog();
        else if (src == btnRegistrarPagoCxC) openRegistrarPagoCxC();
        else if (src == btnRegistrarPagoCxP) openRegistrarPagoCxP();
    }

    private void openCrearCuentaCobrarDialog() {
        new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), SubModulo.CUENTA_COBRAR, OperationType.CREATE, dahsboard).setVisible(true);
        refreshCuentasCobrar();
    }

    private void openCrearCuentaPagarDialog() {
        new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), SubModulo.CUENTA_PAGAR, OperationType.CREATE, dahsboard).setVisible(true);
        refreshCuentasPagar();
    }

    private void openRegistrarPagoCxC() {
        new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), SubModulo.PAGO_CXC, OperationType.CREATE, dahsboard).setVisible(true);
        refreshCuentasCobrar();
    }

    private void openRegistrarPagoCxP() {
        new FormFactory(context).crearForm(context.getNavigation().getModuloActual(), SubModulo.PAGO_CXP, OperationType.CREATE, dahsboard).setVisible(true);
        refreshCuentasPagar();
    }

    /*private void handleCobrarSearch() {
        String txt = textFieldSearchCobrar.getText().trim();
        if (!txt.matches("\\d+")) {
            new Messages(dahsboard, "Ingrese un ID válido").messageError();
            return;
        }
        int id = Integer.parseInt(txt);
        var cuenta = cuentaCobrarController.buscarCuenta(id);
        if (cuenta == null) {
            new Messages(dahsboard, "Cuenta por cobrar no encontrada").messageError();
            return;
        }
        ((UniversalTableModel<CuentaCobrar>) tableCobrar.getModel()).setData(List.of(cuenta));
    }

    private void handlePagarSearch() {
        String txt = textFieldSearchPagar.getText().trim();
        if (!txt.matches("\\d+")) {
            new Messages(dahsboard, "Ingrese un ID válido").messageError();
            return;
        }
        int id = Integer.parseInt(txt);
        var cuenta = cuentaPagarController.buscarCuenta(id);
        if (cuenta == null) {
            new Messages(dahsboard, "Cuenta por pagar no encontrada").messageError();
            return;
        }
        ((UniversalTableModel<CuentaPagar>) tablePagar.getModel()).setData(List.of(cuenta));
    }*/

    private void refreshCuentasCobrar() {
        ((UniversalTableModel<CuentaCobrar>) tableCobrar.getModel()).setData(cuentaCobrarController.listarCuentas());
    }

    private void refreshCuentasPagar() {
        ((UniversalTableModel<CuentaPagar>) tablePagar.getModel()).setData(cuentaPagarController.listarCuentas());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // no-op
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // no-op
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // no-op
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == textFieldSearchCobrar) {
            textFieldSearchCobrar.setText("");
        }
        if (e.getSource() == textFieldSearchPagar) {
            textFieldSearchPagar.setText("");
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == textFieldSearchCobrar) {
            textFieldSearchCobrar.setText("Search CxC");
        }
        if (e.getSource() == textFieldSearchPagar) {
            textFieldSearchPagar.setText("Search CxP");
        }
    }
}