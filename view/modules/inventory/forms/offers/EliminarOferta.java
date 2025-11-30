package view.modules.inventory.forms.offers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Oferta;
import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;

import javax.swing.JLabel;
import javax.swing.JComboBox;

public class EliminarOferta extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("unused")
	private final AplicationContext context;
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	private JComboBox<Oferta> selectOferta ;
	public static void main(String[] args) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public EliminarOferta(AplicationContext context, Dahsboard dahsboard ) {
		
		this.context = context;
		this.dahsboard = dahsboard;
		setBounds(100, 100, 450, 253);
		setResizable(false);
		setBackground(new Color(56,56,56));
		getContentPane().setBackground(new Color(56,56,56));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(56,56,56));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);  
		{
			JLabel lbEliminarOferta = new JLabel("Desactivar Oferta");
			lbEliminarOferta.setFont(Fonts.bold);
			lbEliminarOferta.setForeground(Color.WHITE);
			lbEliminarOferta.setBounds(10, 24, 186, 21);
			contentPanel.add(lbEliminarOferta);
		}
		{
			JLabel lbOferta = new JLabel("Oferta");
			lbOferta.setFont(Fonts.custom);
			lbOferta.setForeground(Color.WHITE);
			lbOferta.setBounds(10, 92, 97, 12);
			contentPanel.add(lbOferta);
		}
		{
			selectOferta = new JComboBox<>();
			selectOferta.setFont(Fonts.custom);
			
			for(Oferta f: context.getOfertasController().listarOfertas()) {
				if(f.getActivo() == 1) {
					selectOferta.addItem(f);
				}
			}
			selectOferta.setSelectedItem(null);
			selectOferta.setBounds(10, 129, 404, 26);
			contentPanel.add(selectOferta);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnGuardar = new JButton("Guardar");
				BtnStyle.primary(btnGuardar, new Color(0,0,0));
				btnGuardar.setFont(Fonts.custom);
				btnGuardar.addActionListener(e -> {
					if(selectOferta.getSelectedItem() != null) {
						if(new Messages(dahsboard, "Esta por desactivar una oferta, ¿Desea continuar?").messageWarning()) {
							Oferta f = (Oferta) selectOferta.getSelectedItem();
							if(context.getOfertasController().desactivarOferta(f.getIdOferta())) {
								new Messages(dahsboard, "Oferta desactivada con exito").messageAlert();
								selectOferta.setSelectedItem(null);
							}else {
								new Messages(dahsboard, "Error al desactivar la oferta").messageError();
							}
						}else {
							new Messages(dahsboard, "Accion cancelada").messageCancelaciones();
						}
					}else {
						new Messages(dahsboard, "Debe seleccionar una oferta antes de continuar").messageError();
					}
					
				});
				buttonPane.add(btnGuardar);
				
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				BtnStyle.primary(btnCancelar, new Color(0,0,0));
				btnCancelar.setFont(Fonts.custom);
				btnCancelar.addActionListener(e-> this.dispose());
				buttonPane.add(btnCancelar);
			}
		}
	}

}
