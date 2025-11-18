package view.forms.CRUD;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DatePickerSettings.DateArea;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;
import view.components.BtnStyle;
import view.components.FrameDragger;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;


import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;

import model.Categoria;

public class crearProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlatLightLaf.setup();
					crearProducto frame = new crearProducto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	public crearProducto() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		new FrameDragger(this);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 776, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelDatosForms = new JPanel();
		panelDatosForms.setBackground(new Color(255, 255, 255));
		panelDatosForms.setBounds(0, 0, 776, 481);
		contentPane.add(panelDatosForms);
		panelDatosForms.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Crear");
		lblNewLabel.setBounds(106, 23, 270, 25);
		panelDatosForms.add(lblNewLabel);
		
		JLabel lbLogo = new JLabel("");
		lbLogo.setIcon(new ImageIcon("resources\\img\\agregar-producto.png"));
		lbLogo.setBounds(26, 10, 84, 64);
		panelDatosForms.add(lbLogo);
		
		JPanel panelInformacionBase = new JPanel();
		panelInformacionBase.setBackground(new Color(255, 255, 255));
		panelInformacionBase.setBounds(10, 84, 700, 307);
		panelDatosForms.add(panelInformacionBase);
		panelInformacionBase.setLayout(null);
		
		JLabel lbCodigoProducto = new JLabel("Código ");
		lbCodigoProducto.setBounds(10, 61, 100, 12);
		panelInformacionBase.add(lbCodigoProducto);
		
		JLabel lbInformacionBase = new JLabel("Información  Base");
		lbInformacionBase.setBounds(10, 23, 141, 12);
		panelInformacionBase.add(lbInformacionBase);
		
		JFormattedTextField formattedTextFieldCodigo = new JFormattedTextField();
		formattedTextFieldCodigo.setBounds(10, 95, 312, 25);
		panelInformacionBase.add(formattedTextFieldCodigo);
		
		JLabel lbNombreProducto = new JLabel("  Nombre");
		lbNombreProducto.setBounds(10, 143, 100, 12);
		panelInformacionBase.add(lbNombreProducto);
		
		textField = new JTextField();
		textField.setBounds(10, 177, 312, 25);
		panelInformacionBase.add(textField);
		textField.setColumns(10);
		
		JLabel lbUnidad = new JLabel("Unidad");
		lbUnidad.setBounds(10, 223, 100, 12);
		panelInformacionBase.add(lbUnidad);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 257, 312, 25);
		panelInformacionBase.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lbCategoria = new JLabel("Categoría ");
		lbCategoria.setBounds(354, 61, 100, 12);
		panelInformacionBase.add(lbCategoria);
		
		JComboBox<Categoria> comboBoxCategoria = new JComboBox<>();
		comboBoxCategoria.setBounds(354, 95, 312, 25);
		panelInformacionBase.add(comboBoxCategoria);
		
		JLabel lbPrecio = new JLabel("Precio");
		lbPrecio.setBounds(354, 143, 100, 12);
		panelInformacionBase.add(lbPrecio);
		
		JFormattedTextField formattedTextFieldPrecio = new JFormattedTextField();
		formattedTextFieldPrecio.setBounds(354, 180, 312, 25);
		panelInformacionBase.add(formattedTextFieldPrecio);
		
		JLabel lbCantidadProducto = new JLabel("Cantidad ");
		lbCantidadProducto.setBounds(354, 223, 100, 12);
		panelInformacionBase.add(lbCantidadProducto);
		
		JSpinner cantidadProductoSpinner = new JSpinner();
		cantidadProductoSpinner.setBounds(354, 257, 312, 25);
		panelInformacionBase.add(cantidadProductoSpinner);
	
		DatePickerSettings settings = new DatePickerSettings();
		
		Color colorSeleccion = new Color(138, 43, 226);
		
		
		DatePicker dateComponent = new DatePicker(settings);
		dateComponent.setBounds(346, 33, 312, 18);
		panelInformacionBase.add(dateComponent);
		
		JButton Continuar = new JButton("Continuar");
		BtnStyle.primary(Continuar, new Color(64, 128, 128));
		Continuar.setBounds(10, 401, 163, 31);
		panelDatosForms.add(Continuar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(547, 401, 163, 31);
		BtnStyle.primary(btnGuardar, new Color(64, 128, 128));
		btnGuardar.setVisible(false);
		panelDatosForms.add(btnGuardar);
		
		JButton btnClose = new JButton("");
		BtnStyle.flat(btnClose);
		btnClose.setIcon(new ImageIcon("resources\\img\\clos.png"));
		btnClose.setBounds(711, 10, 65, 30);
		panelDatosForms.add(btnClose);
		
		JPanel panelDetallesDelProducto = new JPanel();
		panelDetallesDelProducto.setBackground(new Color(255, 255, 255));
		panelDetallesDelProducto.setBounds(10, 84, 700, 307);
		panelDetallesDelProducto.setVisible(false);
		panelDatosForms.add(panelDetallesDelProducto);
		panelDetallesDelProducto.setLayout(null);
		
		JLabel lbDetalles = new JLabel("Detalles del producto");
		lbDetalles.setBounds(10, 23, 140, 30);
		panelDetallesDelProducto.add(lbDetalles);
		
		JLabel lblNuevoProducto = new JLabel("nuevo producto");
		lblNuevoProducto.setBounds(106, 49, 270, 25);
		panelDatosForms.add(lblNuevoProducto);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(10, 401, 163, 31);
		btnRegresar.setVisible(false);
		panelDatosForms.add(btnRegresar);
	}
}
