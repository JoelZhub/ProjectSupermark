package view.modules.billing.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.AplicationContext;
import view.components.BtnStyle;
import view.components.Fonts;
import view.components.Messages;
import view.dashboard.Dahsboard;
import javax.swing.JLabel;

public class tipoFactura extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	@SuppressWarnings("unused")
	private final AplicationContext context;
	@SuppressWarnings("unused")
	private final Dahsboard dahsboard;
	
	
	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public tipoFactura(AplicationContext context, Dahsboard dahsboard ) {
		this.context = context;
		this.dahsboard = dahsboard;
		setBounds(100, 100, 408, 243);
		setBackground(Color.WHITE);
		contentPanel.setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbTipoFactura = new JLabel("Indique el tipo de factura a crear");
			lbTipoFactura.setFont(Fonts.bold);
			lbTipoFactura.setBounds(10, 10, 322, 23);
			contentPanel.add(lbTipoFactura);
		}
		{
			JButton btnCompradorFinal = new JButton("Comprador Final");
			btnCompradorFinal.addActionListener(e->{
				
				if(context.getClienteController().listarClientes().size() > 0) {
					new CrearFactura(context, dahsboard,1).setVisible(true);
					this.dispose();
				}else {
					
					new Messages(dahsboard, "No hay cliente registrados a los cuales generar una factura de este tipo").messageError();
					return;
				}
			});
			btnCompradorFinal.setFont(Fonts.custom);
			BtnStyle.primary(btnCompradorFinal, new Color(56,56,56));
			btnCompradorFinal.setBounds(10, 69, 322, 31);
			contentPanel.add(btnCompradorFinal);
		}
		{
			JButton btnCompradorFiscal = new JButton("Comprador Fiscal");
			btnCompradorFiscal.addActionListener(e->{
				new CrearFactura(context, dahsboard, 0).setVisible(true);
				this.dispose();
			});
			btnCompradorFiscal.setBounds(10, 141, 322, 31);
			btnCompradorFiscal.setFont(Fonts.custom);
			BtnStyle.primary(btnCompradorFiscal, new Color(56,56,56));
			contentPanel.add(btnCompradorFiscal);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
