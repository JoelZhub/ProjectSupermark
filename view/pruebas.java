package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class pruebas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pruebas frame = new pruebas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public pruebas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 700);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(298, 55, 1030, 129);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(300, 210, 1090, 460);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPaneProductos = new JScrollPane();
		scrollPaneProductos.setBounds(10, 107, 481, 320);
		panel_1.add(scrollPaneProductos);
		
		JScrollPane scrollPaneOfertas = new JScrollPane();
		scrollPaneOfertas.setBounds(515, 107, 388, 207);
		panel_1.add(scrollPaneOfertas);
		
		JScrollPane scrollPaneAlertCantidad = new JScrollPane();
		scrollPaneAlertCantidad.setBounds(939, 106, 141, 125);
		panel_1.add(scrollPaneAlertCantidad);
		
		JLabel lbCantidadProductosStockBajo = new JLabel("Productos con stock bajo: ");
		lbCantidadProductosStockBajo.setBounds(939, 237, 141, 35);
		panel_1.add(lbCantidadProductosStockBajo);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(515, 71, 388, 34);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnCrearOferta = new JButton("Nueva ofrta");
		btnCrearOferta.setBounds(75, 0, 61, 34);
		panel_2.add(btnCrearOferta);
		
		JButton btnEliminarOferta = new JButton("Nueva oferta");
		btnEliminarOferta.setBounds(152, 0, 61, 34);
		panel_2.add(btnEliminarOferta);
		
		JButton btnEditarOferta = new JButton("Nueva oferta");
		btnEditarOferta.setBounds(0, 0, 61, 34);
		panel_2.add(btnEditarOferta);
		
		textField = new JTextField();
		textField.setText("Search");
		textField.setColumns(10);
		textField.setBounds(230, 6, 158, 24);
		panel_2.add(textField);

	}
}
