package view.modules.inventory.forms.products;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Producto;
import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class DeleteProducto extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	@SuppressWarnings("unused")
	private JComboBox<Producto> selectProducto;
	@SuppressWarnings("unused")
	private final AplicationContext context;
	
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
		
		JLabel lbDesabilitar = new JLabel("Bloquear producto");
		lbDesabilitar.setBounds(10, 20, 242, 24);
		lbDesabilitar.setFont(Fonts.bold);
		lbDesabilitar.setForeground(Color.WHITE);
		contentPanel.add(lbDesabilitar);
		
		JLabel lbProducto = new JLabel("Seleccione el producto");
		lbProducto.setBounds(10, 72, 220, 24);
		lbProducto.setFont(Fonts.custom);
		lbProducto.setForeground(Color.WHITE);
		contentPanel.add(lbProducto);
		
		selectProducto = new JComboBox<>();
		
		for(Producto p : context.getProductoController().listarProductos()) {	
			if(p.getActivo() == 1) {
				selectProducto.addItem(p);
			}
		}
		selectProducto.setSelectedItem(null);
		selectProducto.setBounds(10, 118, 394, 31);
		contentPanel.add(selectProducto);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Guardar");
				BtnStyle.primary(btnGuardar, new Color(0, 0, 0));
				btnGuardar.setFont(Fonts.custom);
				
				
				btnGuardar.addActionListener(e -> {
					if(selectProducto.getSelectedItem() != null) {
						Producto p = (Producto)  selectProducto.getSelectedItem() ;
						if(new Messages(dahsboard, "Esta por desactivar este producto ¿Desea continuar?").messageWarning()) {
							String message = context.getProductoController().inhabilitarProducto(p.getCodigo());
							new Messages(dahsboard, message).messageAvisos();
							return;
						}else {
							new Messages(dahsboard, "Accion cancelada").messageCancelaciones();
							return;
						}
						
						
					}else {
						
						new Messages(dahsboard, "Seleccione un producto valido").messageError();
						return;
					}
				
				});
				buttonPane.add(btnGuardar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				BtnStyle.primary(btnCancelar, new Color(0, 0, 0));
				btnCancelar.setFont(Fonts.custom);
				btnCancelar.addActionListener(e -> this.dispose());
				buttonPane.add(btnCancelar);
			
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void aplicarFiltroNumericoPrecio(JTextField id) {
	}
}
