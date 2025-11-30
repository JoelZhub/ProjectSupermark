package view.modules.inventory.forms.offers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.github.lgooddatepicker.components.DatePicker;
import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Filtros;
import view.components.Fonts;
import view.components.Messages;
import view.components.ValidateDate;
import view.dashboard.Dahsboard;
import model.Oferta;
import  model.Producto;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class EditarOferta extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldOferta;
	private JTextField textFieldMonto;
	@SuppressWarnings("unused")
	private final AplicationContext context;
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	private DatePicker dateFechaInicio;
	private DatePicker dateFechaFin;
	private JComboBox<Producto> selectProducto;
	private JComboBox<Oferta> selectOferta;
	private Producto p;
	private JRadioButton btnRActivaOferta, btnRInactivaOferta;
	private JButton btnGuardar, btnCancelar;
	private Oferta f;
	private int id;
	private int activo = 0;
	
	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EditarOferta(AplicationContext context, Dahsboard dahsboard ) {
		
		this.context = context;
		this.dahsboard = dahsboard;
		setResizable(false);
		setBounds(100, 100, 620, 506);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		setBackground(new Color(56,56,56));
		getContentPane().setBackground(new Color(56,56,56));
		contentPanel.setBackground(new Color(56,56,56));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(null);
		{
			JLabel lbEditarOferta = new JLabel("Editar Oferta");
			lbEditarOferta.setBounds(21, 23, 163, 32);
			lbEditarOferta.setFont(Fonts.bold);
			lbEditarOferta.setForeground(Color.WHITE);
			contentPanel.add(lbEditarOferta);
		}
		{
			JLabel lbOferta = new JLabel("Oferta");
			lbOferta.setBounds(21, 166, 126, 18);
			lbOferta.setForeground(Color.WHITE);
			lbOferta.setFont(Fonts.custom);
			contentPanel.add(lbOferta);
		}
		{
			textFieldOferta = new JTextField();
			textFieldOferta.setBounds(21, 208, 254, 26);
			contentPanel.add(textFieldOferta);
			textFieldOferta.setColumns(10);
		}
		{
			JLabel lbMonto = new JLabel("Monto");
			lbMonto.setForeground(Color.WHITE);
			lbMonto.setFont(Fonts.custom);
			lbMonto.setBounds(21, 244, 126, 18);
			contentPanel.add(lbMonto);
		}
		{
			textFieldMonto = new JTextField();
			Filtros.aplicarFiltroNumericoTextField(textFieldMonto);
			textFieldMonto.setColumns(10);
			textFieldMonto.setBounds(21, 286, 254, 26);
			contentPanel.add(textFieldMonto);
		}
		{
			JLabel lbFechaInicio = new JLabel("Fecha Inicio");
			lbFechaInicio.setFont(Fonts.custom);
			lbFechaInicio.setForeground(Color.WHITE);
			lbFechaInicio.setBounds(329, 81, 126, 18);
			contentPanel.add(lbFechaInicio);
		}
		
		dateFechaInicio = new DatePicker();
//		dateFechaInicio.addDateChangeListener(e -> {
//		    LocalDate fi = e.getNewDate();   
//
//		    if (!ValidateDate.validarFechaInicio(fi)) {
//		    	dateFechaInicio.setDate(null);   
//		    }
//		});
		dateFechaInicio.setBounds(329, 120, 242, 26);
		contentPanel.add(dateFechaInicio);
		
		dateFechaFin = new DatePicker();
		dateFechaFin.addDateChangeListener(e -> {
		    LocalDate fi = dateFechaInicio.getDate(); 
		    LocalDate ff = e.getNewDate();            
		    if (!ValidateDate.validarFechaFin(fi, ff)) {
		    	dateFechaFin.setDate(dateFechaInicio.getDate());
		    }
		});
		dateFechaFin.setBounds(329, 208, 242, 26);
		contentPanel.add(dateFechaFin);
		
		JLabel lbFechaFin = new JLabel("Fecha Fin");
		lbFechaFin.setBounds(329, 166, 126, 18);
		lbFechaFin.setForeground(Color.WHITE);
		lbFechaFin.setFont(Fonts.custom);
		contentPanel.add(lbFechaFin);
		
		JLabel lbEstado = new JLabel("Estado");
		lbEstado.setBounds(21, 343, 126, 12);
		lbEstado.setFont(Fonts.custom);
		lbEstado.setForeground(Color.WHITE);
		contentPanel.add(lbEstado);
		
		btnRActivaOferta = new JRadioButton("Activa");
		btnRActivaOferta.setBackground(null);
		btnRActivaOferta.setFont(Fonts.custom);
		btnRActivaOferta.setForeground(Color.WHITE);
		btnRActivaOferta.setBounds(21, 375, 102, 20);
		contentPanel.add(btnRActivaOferta);
		
		btnRInactivaOferta = new JRadioButton("Inactiva");
		btnRInactivaOferta.setBounds(141, 376, 102, 20);
		btnRInactivaOferta.setBackground(null);
		btnRInactivaOferta.setFont(Fonts.custom);
		btnRInactivaOferta.setForeground(Color.WHITE);
		contentPanel.add(btnRInactivaOferta);
		
		ButtonGroup group = new ButtonGroup();
		group.add(btnRActivaOferta);
		group.add(btnRInactivaOferta);
		
		JLabel lbProducto = new JLabel("Producto");
		lbProducto.setFont(Fonts.custom);
		lbProducto.setForeground(Color.WHITE);
		lbProducto.setBounds(330, 251, 126, 18);
		contentPanel.add(lbProducto);
		
		selectProducto = new JComboBox<>();
		selectProducto.setFont(Fonts.custom);
	
		for(Producto p : context.getProductoController().listarProductos()) {
			if(p.getActivo() == 1) {
				selectProducto.addItem(p);
			}
		}
		
		selectProducto.setSelectedItem(null);
		selectProducto.setBounds(330, 286, 254, 26);
		contentPanel.add(selectProducto);
		
		JLabel lbSeleccionOferta = new JLabel("Seleccione la oferta");
		lbSeleccionOferta.setBounds(21, 81, 217, 18);
		lbSeleccionOferta.setFont(Fonts.custom);
		lbSeleccionOferta.setForeground(Color.WHITE);
		contentPanel.add(lbSeleccionOferta);
		
		selectOferta = new JComboBox<>();
		selectOferta.setFont(Fonts.custom);
		
		for(Oferta f: context.getOfertasController().listarOfertas()) {
			if(f.getActivo() == 1) {
				selectOferta.addItem(f);
			}
		}
		selectOferta.setSelectedItem(null);
		selectOferta.addActionListener(this);
		selectOferta.setBounds(21, 119, 254, 26);
		contentPanel.add(selectOferta);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.setFont(Fonts.custom);
				btnGuardar.addActionListener(this);
				BtnStyle.primary(btnGuardar, new Color(0,0,0));
				buttonPane.add(btnGuardar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setFont(Fonts.custom);
				BtnStyle.primary(btnCancelar, new Color(0,0,0));
				btnCancelar.addActionListener(e -> this.dispose());
				buttonPane.add(btnCancelar);
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void LimpiarCampos() {
		
		textFieldOferta.setText("");
		textFieldMonto.setText("");
		
		selectOferta.setSelectedItem(null);
		selectProducto.setSelectedItem(null);
		
		btnRInactivaOferta.setSelected(false);
		btnRActivaOferta.setSelected(false);
		
		dateFechaFin.setDate(null);
		dateFechaInicio.setDate(null);
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == selectOferta) {
			
			f = (Oferta) selectOferta.getSelectedItem();
			id = f.getIdOferta();
			textFieldOferta.setText(f.getOferta());
			textFieldMonto.setText(f.getPorcentaje() + "");
			p = context.getProductoController().buscar(f.getIdProducto());
			selectProducto.setSelectedItem(p);
			
			LocalDate f1 = Instant.ofEpochMilli(f.getFechaInicio().getTime())
			        .atZone(ZoneId.systemDefault())
			        .toLocalDate();

			LocalDate f2 = Instant.ofEpochMilli(f.getFechaFin().getTime())
			        .atZone(ZoneId.systemDefault())
			        .toLocalDate();
            		
            dateFechaInicio.setDate(f1);   
			dateFechaFin.setDate(f2);
			
			if(f.getActivo() == 1) {
				btnRActivaOferta.setSelected(true);
			}else {
				btnRInactivaOferta.setSelected(true);
			}
			
			
			
		}
		if(e.getSource() == btnGuardar) {
			
			if(selectOferta.getSelectedItem() != null) {
				Producto p = (Producto) selectProducto.getSelectedItem();
	
				double monto = Double.parseDouble(textFieldMonto.getText());
				
				if(btnRActivaOferta.isSelected()) {
					activo = 1;
				}else {
					activo = 0;
				}
				LocalDate ld = dateFechaInicio.getDate();
				Date sqlDate = Date.valueOf(ld);
				LocalDate ld2 = dateFechaFin.getDate();
				Date sqlDate2 = Date.valueOf(ld2);
				
				var of = new Oferta(
						id,
						textFieldOferta.getText(),
						p.getCodigo(),
						monto,
						sqlDate,
						sqlDate2,
						activo
						);			
				
					if(context.getOfertasController().validarDatosOfertas(of)){
						if(new Messages(dahsboard, "Esta por editar la información de este elemento ¿Seguro que desea continuar?").messageWarning()) {	
							if(context.getOfertasController().editarOferta(of)) {
								new Messages(dahsboard, "Oferta editada con exito").messageAlert();
								//LimpiarCampos();
								return;
							}else {
								new Messages(dahsboard, "Error al editar la oferta").messageError();
								return;
							}
							
						}else {
							
							new Messages(dahsboard, "Accion Cancelada").messageCancelaciones();
							return;
						}
											
					}else {
						new Messages(dahsboard, "Rellene los campos correctamente").messageError();
						return;
					}	
			}else {
				
				new Messages(dahsboard, "Selecciona una oferta valida").messageError();
				return;
			}
			
		}
		
	}
}
