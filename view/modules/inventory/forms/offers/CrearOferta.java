package view.modules.inventory.forms.offers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.github.lgooddatepicker.components.DatePicker;

import model.Oferta;
import model.Producto;
import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Filtros;
import view.components.Fonts;
import view.components.Messages;
import view.components.ValidateDate;
import view.dashboard.Dahsboard;

public class CrearOferta extends JDialog  implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNombre;
	private JTextField textFieldMonto;
	@SuppressWarnings("unused")
	private JButton btnGuardar, btnCancelar;
	private final AplicationContext context;
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	private DatePicker dateFechaInicio, dateFechaFin;
	private JComboBox<Producto> selectProducto;
	

	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public CrearOferta(AplicationContext context, Dahsboard dahsboard) {
		
		this.context = context;
		this.dahsboard = dahsboard;
		setResizable(false);
		setBounds(100, 100, 730, 449);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		setBackground(new Color(56,56,56));
		getContentPane().setBackground(new Color(56,56,56));
		contentPanel.setBackground(new Color(56,56,56));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(null);
		{
			JLabel lbCrearOferta = new JLabel("Crear Oferta");
			lbCrearOferta.setBounds(20, 22, 265, 44);
			lbCrearOferta.setForeground(Color.WHITE);
			lbCrearOferta.setFont(Fonts.bold);
			contentPanel.add(lbCrearOferta);
		}
		{
			JLabel lbNombreOferta = new JLabel("Nombre Oferta");
			lbNombreOferta.setFont(Fonts.custom);
			lbNombreOferta.setForeground(Color.WHITE);
			lbNombreOferta.setBounds(20, 97, 157, 23);
			contentPanel.add(lbNombreOferta);
		}
		{
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(20, 151, 301, 26);
			contentPanel.add(textFieldNombre);
			textFieldNombre.setColumns(10);
		}
		{
			JLabel lbProducto = new JLabel("Producto");
			lbProducto.setBounds(350, 97, 157, 23);
			lbProducto.setForeground(Color.WHITE);
			lbProducto.setFont(Fonts.custom);
			contentPanel.add(lbProducto);
		}
		{
			
			
			selectProducto = new JComboBox<>();
			
			//rellenar select de productos con los registrados y activos en la BD
			//comence hacerlo asi por seleccion y luego entonces obtengo el id del elemento seleccionado con los metodos que crearon
			//por el motivo de que me puse a pensar de que un usario no se va a saber los id de los elementos como tal
			// y como el profe dijo que  quitaramos la columna id de la tabla.  si encuentra este metodo aplicado en otras clases es por este motivo.
			
			for(Producto p : context.getProductoController().listarProductos()) {
				if(p.getCantida() > 0 && p.getActivo() == 1) {
					selectProducto.addItem(p);
				}
			}
			selectProducto.setBounds(350, 151, 294, 26);
			selectProducto.setSelectedItem(null);
			selectProducto.setFont(Fonts.custom);
			contentPanel.add(selectProducto);
		}
		{
			JLabel lbMonto = new JLabel("Monto");
			lbMonto.setFont(Fonts.custom);
			lbMonto.setForeground(Color.WHITE);
			lbMonto.setBounds(20, 197, 157, 23);
			contentPanel.add(lbMonto);
		}
		{
			textFieldMonto = new JTextField();
			Filtros.aplicarFiltroNumericoTextField(textFieldMonto);
			textFieldMonto.setColumns(10);
			textFieldMonto.setBounds(20, 243, 627, 26);
			contentPanel.add(textFieldMonto);
		}
		{
			JLabel lbFechaInicio = new JLabel("Fecha Inicio");
			lbFechaInicio.setForeground(Color.WHITE);
			lbFechaInicio.setFont(Fonts.custom);
			lbFechaInicio.setBounds(20, 293, 157, 23);
			contentPanel.add(lbFechaInicio);
		}
		
		dateFechaInicio = new DatePicker();
		dateFechaInicio.setBounds(20, 335, 286, 26);
		
		dateFechaInicio.addDateChangeListener(e -> {
		    LocalDate fi = e.getNewDate();   

		    if (!ValidateDate.validarFechaInicio(fi)) {
		    	dateFechaInicio.setDate(null);   
		    }
		});
		contentPanel.add(dateFechaInicio);
		
		dateFechaFin = new DatePicker();
		dateFechaFin.setBounds(350, 335, 286, 26);
		dateFechaFin.addDateChangeListener(e -> {
		    LocalDate fi = dateFechaInicio.getDate(); 
		    LocalDate ff = e.getNewDate();            
		    if (!ValidateDate.validarFechaFin(fi, ff)) {
		    	dateFechaFin.setDate(null);
		    }
		});
		contentPanel.add(dateFechaFin);
		
		JLabel lbFechaFin = new JLabel("Fecha Fin");
		lbFechaFin.setFont(Fonts.custom);
		lbFechaFin.setForeground(Color.WHITE);
		lbFechaFin.setBounds(350, 293, 157, 23);
		contentPanel.add(lbFechaFin);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnGuardar = new JButton("Guardar");
				btnGuardar.setForeground(Color.WHITE);
				btnGuardar.setFont(Fonts.custom);
				btnGuardar.addActionListener(this);
				BtnStyle.primary(btnGuardar, new Color(0, 0, 0));
				buttonPane.add(btnGuardar);
			
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setFont(Fonts.custom);
				btnCancelar.setForeground(Color.WHITE);
				btnCancelar.addActionListener(e -> this.dispose());
				BtnStyle.primary(btnCancelar, new Color(0, 0, 0));
				buttonPane.add(btnCancelar);
			}
		}
	}


	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btnGuardar) {
			
			if(selectProducto.getSelectedItem()  != null) {
				double monto = 0;
				
				if(textFieldMonto.getText().trim().matches("\\d+")) {
					
					monto = Double.parseDouble(textFieldMonto.getText().trim());
				}
				Producto  p = (Producto) selectProducto.getSelectedItem();
				LocalDate ld = dateFechaInicio.getDate();
				Date sqlDate = Date.valueOf(ld);
				LocalDate ld2 = dateFechaFin.getDate();
				Date sqlDate2 = Date.valueOf(ld);
				Oferta of = new Oferta(textFieldNombre.getText(), monto, sqlDate, sqlDate2, p.getCodigo());
				
				
				if(context.getOfertasController().validarDatosOfertas(of)) {
					if(context.getOfertasController().crearOferta(of)) {
						new Messages(dahsboard, "Oferta creada con exito").messageAlert();
						limpiarCampos();
					}else {
						new Messages(dahsboard, "Error al crear la oferta").messageError();
					}
				}else {
					new Messages(dahsboard, "Rellene los campos correctamente").messageError();
				}
				
		}else {
			new Messages(dahsboard, "Seleccione un producto al cual aplicarle la oferta")
			.messageError();
		}
	}
		
	}
	
	private  void limpiarCampos() {
		
		textFieldMonto.setText("");
		textFieldNombre.setText("");
		dateFechaInicio.setDate(null);
		dateFechaFin.setDate(null);
		selectProducto.setSelectedItem(null);
		
	}
}
