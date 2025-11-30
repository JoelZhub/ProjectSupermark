package view.modules.billing.forms;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JPanel;
import view.AplicationContext;
import view.dashboard.Dahsboard;
import java.awt.Color;

public class CrearFactura extends JDialog {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private final JPanel contentPanel = new JPanel();
	private final AplicationContext context;
	private final Dahsboard dahsboard;
	

	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CrearFactura(AplicationContext context, Dahsboard dahsboard, int modo) {
	    this.context = context;
	    this.dahsboard = dahsboard;
	    setBounds(100, 100, 1098, 818);
	    setBackground(Color.WHITE);
	    getContentPane().setBackground(Color.WHITE);
	    setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
	    getContentPane().setLayout(new BorderLayout());

	    if (modo == 1) {
	        cargarModoEndConsumer();
	    } else {
	        cargarModoAlternativo(); 
	    }
	}
	
	private void cargarModoEndConsumer() {
	    EndConsumer panelEndConsumer = new EndConsumer(context, dahsboard);
	    getContentPane().add(panelEndConsumer, BorderLayout.CENTER);
	}

	private void cargarModoAlternativo() {
	   CorporateClient panelCorporate = new CorporateClient(context, dahsboard);
	    getContentPane().add(panelCorporate, BorderLayout.CENTER);
	}


	
}
