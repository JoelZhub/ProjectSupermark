package view.modules.products.forms;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Fonts;
import view.components.Messages;
import view.components.NumericFilter;
import view.dashboard.Dahsboard;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DeleteProducto extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	@SuppressWarnings("unused")
	private final AplicationContext context;
	private JTextField textFieldId;
	
	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public DeleteProducto(Dahsboard dahsboard, AplicationContext context ) {
		this.dahsboard = dahsboard;
		this.context = context;
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		setBackground(new Color(56,56,56));
		setBounds(100, 100, 450, 262);
		getContentPane().setBackground(new Color(56,56,56));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(56,56,56));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbEliminar = new JLabel("Eliminar Producto");
		lbEliminar.setBounds(10, 20, 242, 24);
		lbEliminar.setFont(Fonts.bold);
		lbEliminar.setForeground(Color.WHITE);
		contentPanel.add(lbEliminar);
		
		JLabel lbEliminarId = new JLabel("Ingrese el id del producto");
		lbEliminarId.setBounds(10, 72, 220, 24);
		lbEliminarId.setFont(Fonts.custom);
		lbEliminarId.setForeground(Color.WHITE);
		contentPanel.add(lbEliminarId);
		
		textFieldId = new JTextField();
		aplicarFiltroNumericoPrecio(textFieldId);
		
		textFieldId.addActionListener(e -> {
			
				
			
		});
		textFieldId.setBounds(10, 120, 372, 26);
		contentPanel.add(textFieldId);
		textFieldId.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(56,56,56));
			buttonPane.setLayout(new GridLayout(1, 2, 10, 0));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.setBorder(null);
		
				BtnStyle.primary(btnGuardar, new Color(102, 237, 142));
				btnGuardar.setFont(Fonts.custom);
				btnGuardar.setForeground(Color.WHITE);
				btnGuardar.setPreferredSize(new Dimension(200, 40));
				btnGuardar.setBounds(0, 10, 200, 30);
				btnGuardar.addActionListener(e -> {
					int id;	
				if(textFieldId.getText().trim().matches("\\d+")) {
						
						id = Integer.parseInt(textFieldId.getText().trim());
						
						if(new Messages(dahsboard, "Esta por eliminar este producto desea continuar?").messageWarning()) {
							String message = context.getProductoController().eliminar(id);
							new Messages(dahsboard, message).messageAvisos();
						}else {
							new Messages(dahsboard, "Accion Cancelada").messageCancelaciones();
							return;
						}
						
					}else {
						new Messages(dahsboard, "Ingrese un id valido").messageError();
						return;
					}
					
				});
				buttonPane.add(btnGuardar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setBorder(null);
				btnCancelar.setPreferredSize(new Dimension(200, 40));
				BtnStyle.primary(btnCancelar, new Color(204, 204, 204));
				btnCancelar.setFont(Fonts.custom);
				btnCancelar.setForeground(Color.WHITE);
				btnCancelar.addActionListener(e -> this.dispose());
				buttonPane.add(btnCancelar);
			
			}
		}
	}
	
	private void aplicarFiltroNumericoPrecio(JTextField id) {
		((AbstractDocument) id.getDocument()).setDocumentFilter(new NumericFilter());
	}
}
