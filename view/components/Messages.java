package view.components;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Messages {

	//gestion de mesanjes dentro de la app -> mensajes de alerta, advertencia, etc
	//si requiere otro tipo de mensaje favor agregar el mismo siguiendo la logica de los demas
	
	
	private static  String message = "";
	@SuppressWarnings("unused")
	private JFrame frame;
	private JDialog dialog;
	private boolean confirme  = false;
	
	@SuppressWarnings("static-access")
	public Messages(JFrame frame, String message) {
		this.message = message;
		this.frame = frame;
		
		dialog = new JDialog(frame, true);
		this.dialog.setSize(700, 160);
		this.dialog.setLocationRelativeTo(frame);
		this.dialog.setResizable(false);
		this.dialog.setModal(true);
	}
	
	public boolean messageAvisos() {
		
//		/90,110,140
		builderDialog(new Color(90,110,140));
		return showAnGet();
	}
	public  boolean messageAlert() {
		builderDialog(new Color(125, 201, 149));
		return showAnGet();

	}
	public  boolean messageWarning() {
		builderDialog(new Color(255, 183, 66));
		return showAnGet();

	}
	public  boolean messageError() {

		builderDialog(new Color(222, 56, 55));		return showAnGet();
	}
	public boolean messageCancelaciones() {
		
		builderDialog(new Color(66, 133, 244));
		return  showAnGet();
	}
	
	private void builderDialog(Color bg) {
		
        JPanel panel = new JPanel();
        panel.setBackground(bg);
        panel.setLayout(new BorderLayout(10, 10));

        JLabel lbl = new JLabel(message, SwingConstants.CENTER);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(Fonts.bold);
        
        JButton btn = new JButton("OK");
        btn.setFont(Fonts.custom);
        BtnStyle.flat(btn);
        btn.setForeground(Color.white);
        btn.addActionListener(e -> {
        	confirme = true;
        	dialog.dispose();
        });

        panel.add(lbl, BorderLayout.CENTER);
        panel.add(btn, BorderLayout.SOUTH);
        
       
        dialog.setContentPane(panel);
        
	}
	
	private  boolean showAnGet() {
		dialog.setVisible(true);
		return confirme;
	}
	
	
	
}
